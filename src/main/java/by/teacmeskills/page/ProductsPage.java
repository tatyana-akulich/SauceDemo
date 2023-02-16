package by.teacmeskills.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductsPage extends HeaderPage {
    private final static By PRODUCTS_TITLE = By.xpath("//span[text() = 'Products']");
    private final static By ALL_PRODUCTS = By.xpath("//div[@class = 'inventory_item']");
    private final static By PRODUCT_NAMES = By.xpath("//div[@class = 'inventory_item_name']");

    private final static String ITEM_CARD_LOCATOR_PATTERN = "//div[text()='%s']/ancestor::div[@class = 'inventory_item']";
    private final static String ITEM_PRICE_LOCATOR_PATTERN = ITEM_CARD_LOCATOR_PATTERN + "//div[@class='inventory_item_price']";
    private final static String ADD_TO_CART_LOCATOR_PATTERN = ITEM_CARD_LOCATOR_PATTERN + "//button[text()='Add to cart']";

    public ProductsPage(WebDriver driver) {
        super(driver);
        isPageOpened();
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
}
