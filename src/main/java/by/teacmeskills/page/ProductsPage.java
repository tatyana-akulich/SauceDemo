package by.teacmeskills.page;

import by.teacmeskills.util.PropertiesLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductsPage extends HeaderPage {
    private final static By PRODUCTS_TITLE = By.xpath("//span[text() = 'Products']");
    private final static By ALL_PRODUCTS = By.xpath("//div[@class = 'inventory_item']");
    private final static By PRODUCT_NAMES = By.xpath("//div[@class = 'inventory_item_name']");
    private static final By SORT_FILTER = By.xpath("//select[@data-test='product_sort_container']");

    private final static String ITEM_CARD_LOCATOR_PATTERN = "//div[text()='%s']/ancestor::div[@class = 'inventory_item']";
    private final static String ITEM_PRICE_LOCATOR_PATTERN = ITEM_CARD_LOCATOR_PATTERN + "//div[@class='inventory_item_price']";
    private final static String ADD_TO_CART_LOCATOR_PATTERN = ITEM_CARD_LOCATOR_PATTERN + "//button[text()='Add to cart']";
    private final static String REMOVE_FROM_CART_LOCATOR_PATTERN = ITEM_CARD_LOCATOR_PATTERN + "//button[text()='Remove']";

    public ProductsPage(WebDriver driver) {
        super(driver);
        isPageOpened();
    }

    public ProductsPage open() {
        driver.get(PropertiesLoader.loadProperties().getProperty("base.url") + "/inventory.html");
        isPageOpened();
        waitForPageLoaded();
        return this;
    }

    public boolean isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCTS_TITLE));
        return driver.findElement(PRODUCTS_TITLE).isDisplayed();
    }

    public List<WebElement> getProducts() {
        return driver.findElements(ALL_PRODUCTS);
    }

    public ProductsPage addProductToCart(String productName) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART_LOCATOR_PATTERN, productName))).click();
        return this;
    }

    public List<WebElement> getProductNames() {
        return driver.findElements(PRODUCT_NAMES);
    }

    public ProductsPage sortProducts(ProductSortFilter sortOption) {
        Select select = new Select(driver.findElement(SORT_FILTER));
        select.selectByVisibleText(sortOption.getOption());
        return new ProductsPage(driver);
    }

    public WebElement getAddButton(String productName) {
        return driver.findElement(By.xpath(String.format(ADD_TO_CART_LOCATOR_PATTERN, productName)));
    }

    public WebElement getDeleteButton(String productName) {
        return driver.findElement(By.xpath(String.format(REMOVE_FROM_CART_LOCATOR_PATTERN, productName)));
    }

    public CartPage passToCart() {
        driver.findElement(By.id("shopping_cart_container")).click();
        return new CartPage(driver);
    }

    public ProductsPage addItemToCart(String name) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART_LOCATOR_PATTERN, name))).click();
        return this;
    }

    public ProductsPage removeItemFromCartFromProductListPage(String name) {
        driver.findElement(By.xpath(String.format(REMOVE_FROM_CART_LOCATOR_PATTERN, name))).click();
        return this;
    }

    public String getPrice (String productName){
        return driver.findElement(By.xpath(String.format(ITEM_PRICE_LOCATOR_PATTERN, productName))).getText();
    }
}
