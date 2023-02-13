package by.teachmeskills;

import by.teachmeskills.steps.Login;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest {

    private final static String ITEM_CARD_LOCATOR_PATTERN = "//div[text()='%s']/ancestor::div[@class = 'inventory_item']";
    private final static String ITEM_IN_CART_LOCATOR_PATTERN = "//div[text()='%s']/ancestor::div[@class='cart_item']";
    private final static String REMOVE_FROM_CART_LOCATOR_PATTERN = ITEM_CARD_LOCATOR_PATTERN + "//button[text()='Remove']";
    private final static String ADD_TO_CART_LOCATOR_PATTERN = ITEM_CARD_LOCATOR_PATTERN + "//button[text()='Add to cart']";
    private final static String ITEM_PRICE_LOCATOR_PATTERN = ITEM_CARD_LOCATOR_PATTERN + "//div[@class='inventory_item_price']";
    private final static String ITEM_PRICE_IN_CART_LOCATOR_PATTERN = ITEM_IN_CART_LOCATOR_PATTERN + "//div[@class='inventory_item_price']";
    private final static String ITEM_NAME_IN_CART_LOCATOR = "//div[text()='%s']";

    @BeforeClass
    public void passRegistration() {
        Login login = new Login();
        login.loginWithValidCredentials(driver);
    }

    @DataProvider
    public Object[][] products() {
        return new Object[][]{{"Sauce Labs Backpack"}, {"Sauce Labs Fleece Jacket"}};
    }

    @Test(dataProvider = "products")
    public void checkAddItemToCartButtonAfterClickChangesToRemove(String productName) {
        addItemToCart(productName);
        assertTrue(driver.findElement(By.xpath(String.format(REMOVE_FROM_CART_LOCATOR_PATTERN, productName))).isDisplayed(),
                "Add button wasn't changed to remove");
        removeItemFromCartFromProductListPage(productName);
    }

    @Test(dataProvider = "products")
    public void checkRemoveButtonAfterClickChangesToAddToCart(String productName) {
        addItemToCart(productName);
        removeItemFromCartFromProductListPage(productName);
        assertTrue(driver.findElement(By.xpath(String.format(ADD_TO_CART_LOCATOR_PATTERN, productName))).isDisplayed(),
                "Add to cart button should be displayed");
    }

    @Test(dataProvider = "products")
    public void checkItemInCart(String productName) {
        addItemToCart(productName);
        passToCart();
        String itemNameInCartLocator = "//div[text()='%s']";
        assertTrue(driver.findElement(By.xpath(String.format(itemNameInCartLocator, productName))).isDisplayed(),
                "There is no such item in the cart");
        passToProductList();
        removeItemFromCartFromProductListPage(productName);
    }

    @Test(dataProvider = "products")
    public void checkPriceInCart(String productName) {
        String expectedPrice = driver.findElement(By.xpath(String.format(ITEM_PRICE_LOCATOR_PATTERN, productName))).getText();
        addItemToCart(productName);
        passToCart();
        String actualPrice = driver.findElement(By.xpath(String.format(ITEM_PRICE_IN_CART_LOCATOR_PATTERN, productName))).getText();
        assertEquals(actualPrice, expectedPrice, "Prices in catalog and in the cart are different");
        passToProductList();
        removeItemFromCartFromProductListPage(productName);
    }

    private void addItemToCart(String name) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART_LOCATOR_PATTERN, name))).click();
    }

    private void removeItemFromCartFromProductListPage(String name) {
        driver.findElement(By.xpath(String.format(REMOVE_FROM_CART_LOCATOR_PATTERN, name))).click();
    }

    private void passToCart() {
        driver.findElement(By.id("shopping_cart_container")).click();
    }

    private void passToProductList() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("inventory_sidebar_link")).click();
    }
}
