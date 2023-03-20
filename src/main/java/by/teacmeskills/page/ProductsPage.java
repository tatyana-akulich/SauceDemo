package by.teacmeskills.page;

import by.teacmeskills.util.PropertiesLoader;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

@Log4j2
public class ProductsPage extends HeaderPage {
    private final static By PRODUCTS_TITLE = By.xpath("//span[text() = 'Products']");
    private final static By ALL_PRODUCTS = By.xpath("//div[@class = 'inventory_item']");
    private final static By PRODUCT_NAMES = By.xpath("//div[@class = 'inventory_item_name']");
    private static final By SORT_FILTER = By.xpath("//select[@data-test='product_sort_container']");

    private final static String ITEM_CARD_LOCATOR_PATTERN = "//div[text()='%s']/ancestor::div[@class = 'inventory_item']";
    private final static String ITEM_PRICE_LOCATOR_PATTERN = ITEM_CARD_LOCATOR_PATTERN + "//div[@class='inventory_item_price']";
    private final static String ADD_TO_CART_LOCATOR_PATTERN = ITEM_CARD_LOCATOR_PATTERN + "//button[text()='Add to cart']";
    private final static String REMOVE_FROM_CART_LOCATOR_PATTERN = ITEM_CARD_LOCATOR_PATTERN + "//button[text()='Remove']";

    private final static String LINK_TO_DESCRIPTION_LOCATOR = "//div[text() = '%s']";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public ProductsPage open() {
        log.info("Opening productPage using base.url from properties");
        driver.get(PropertiesLoader.loadProperties().getProperty("base.url") + "/inventory.html");
        isPageOpened();
        waitForPageLoaded();
        return this;
    }

    public boolean isPageOpened() {
        log.info("Checking if productPage is opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCTS_TITLE));
        return driver.findElement(PRODUCTS_TITLE).isDisplayed();
    }

    public List<WebElement> getProducts() {
        log.info("Getting list of webElements products in catalog");
        return driver.findElements(ALL_PRODUCTS);
    }

    public ProductsPage addProductToCart(String productName) {
        log.info("Adding {} to cart", productName);
        driver.findElement(By.xpath(String.format(ADD_TO_CART_LOCATOR_PATTERN, productName))).click();
        return this;
    }

    public List<WebElement> getProductNames() {
        log.info("Getting list of product names in catalog");
        return driver.findElements(PRODUCT_NAMES);
    }

    public ProductsPage sortProducts(ProductSortFilter sortOption) {
        log.info("Sorting products using option {}", sortOption);
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

    public ProductsPage addItemToCart(String name) {
        log.info("Adding {} to cart", name);
        driver.findElement(By.xpath(String.format(ADD_TO_CART_LOCATOR_PATTERN, name))).click();
        return this;
    }

    public ProductsPage removeItemFromCartFromProductListPage(String name) {
        log.info("Removing {} from cart from productPage", name);
        driver.findElement(By.xpath(String.format(REMOVE_FROM_CART_LOCATOR_PATTERN, name))).click();
        return this;
    }

    public String getPrice(String productName) {
        log.info("Getting price of {} in catalog", productName);
        return driver.findElement(By.xpath(String.format(ITEM_PRICE_LOCATOR_PATTERN, productName))).getText();
    }

    public ProductDescriptionPage openDescriptionPage(String productName) {
        log.info("Opening description page for {}", productName);
        driver.findElement(By.xpath(String.format(LINK_TO_DESCRIPTION_LOCATOR, productName))).click();
        return new ProductDescriptionPage(driver);
    }
}
