package by.teacmeskills.page;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Log4j2
public class CartPage extends HeaderPage {

    private final static By TITLE = By.xpath("//span[text()='Your Cart']");
    private final static By CHECKOUT_BUTTON = By.id("checkout");
    private final static By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");

    private final static String CART_ITEM_NAME_LOCATOR = "//div[text()='%s']";
    private final static String CART_ITEM_LOCATOR_PATTERN = "//div[text()='%s']/ancestor::div[@class='cart_item']";
    private final static String CART_ITEM_PRICE_LOCATOR_PATTERN =
            CART_ITEM_LOCATOR_PATTERN + "//div[@class='inventory_item_price']";
    private final static String CART_ITEM_REMOVE_LOCATOR_PATTERN =
            CART_ITEM_LOCATOR_PATTERN + "//button[@class='btn btn_secondary btn_small cart_button']";

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpened() {
        log.info("Checking if cartPage is opened");
        return driver.findElement(TITLE).isDisplayed();
    }

    public ProductsPage clickContinueShopping() {
        log.info("Going from cartPage to productPage");
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
        return new ProductsPage(driver);
    }

    public CheckoutPage clickCheckout() {
        log.info("Going from cartPage to checkoutPage");
        driver.findElement(CHECKOUT_BUTTON).click();
        return new CheckoutPage(driver);
    }

    public CartPage removeItem(String productName) {
        log.info("Removing {} from cart", productName);
        driver.findElement(By.xpath(String.format(CART_ITEM_REMOVE_LOCATOR_PATTERN, productName))).click();
        return this;
    }

    public String getItemPrice(String productName) {
        log.info("Taking price of {} in cart", productName);
        return driver.findElement(By.xpath(String.format(CART_ITEM_PRICE_LOCATOR_PATTERN, productName))).getText();
    }

    public boolean isItemInCart(String productName) {
        log.info("Checking if {} is in cart", productName);
        return driver.findElements(By.xpath(String.format(CART_ITEM_NAME_LOCATOR, productName))).size() > 0;
    }

    public WebElement getItem(String productName) {
        log.info("Getting webElement with name {} from cart", productName);
        return driver.findElement(By.xpath(String.format(CART_ITEM_NAME_LOCATOR, productName)));
    }
}
