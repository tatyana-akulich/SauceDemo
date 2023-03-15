package by.teachmeskills;

import by.teachmeskills.steps.Login;
import by.teacmeskills.page.CartPage;
import by.teacmeskills.page.ProductsPage;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest {

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
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addItemToCart(productName);
        assertTrue(new ProductsPage(driver).getDeleteButton(productName).isDisplayed(),
                "Add button wasn't changed to remove");
        productsPage.removeItemFromCartFromProductListPage(productName);
    }

    @Test(dataProvider = "products")
    public void checkRemoveButtonAfterClickChangesToAddToCart(String productName) {
        new ProductsPage(driver)
                .addItemToCart(productName)
                .removeItemFromCartFromProductListPage(productName);
        assertTrue(new ProductsPage(driver).getAddButton(productName).isDisplayed(),
                "Add to cart button should be displayed");
    }

    @Step("Add item to cart")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "products")
    public void checkItemInCart(String productName) {
        CartPage cartPage = new ProductsPage(driver)
                .addItemToCart(productName)
                .passToCart();
        String itemNameInCartLocator = "//div[text()='%s']";
        assertTrue(driver.findElement(By.xpath(String.format(itemNameInCartLocator, productName))).isDisplayed(),
                "There is no such item in the cart");
        cartPage.clickContinueShopping();
        new ProductsPage(driver).removeItemFromCartFromProductListPage(productName);
    }

    @Test(dataProvider = "products")
    public void checkPriceInCart(String productName) {
        String expectedPrice = new ProductsPage(driver).getPrice(productName);
        CartPage cartPage = new ProductsPage(driver)
                .addItemToCart(productName)
                .passToCart();
        String actualPrice = new CartPage(driver).getItemPrice(productName);
        assertEquals(actualPrice, expectedPrice, "Prices in catalog and in the cart are different");
        cartPage.clickContinueShopping();
        new ProductsPage(driver).removeItemFromCartFromProductListPage(productName);
    }

    /*private void passToProductList() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("inventory_sidebar_link")).click();
    }*/

    @Test
    public void checkContinueShoppingButton() {
        assertThat(login().openCart().clickContinueShopping().isPageOpened())
                .as("Product page should be opened after clicking ContinueShopping button")
                .isTrue();
    }

    @Test
    public void checkAddRemoveProductButton() {
        String productName = "Sauce Labs Bolt T-Shirt";
        CartPage cartPage = login().addProductToCart(productName).openCart();
        assertThat(cartPage.isItemInCart(productName))
                .as("Added product should be displayed in cart")
                .isTrue();
        assertThat(cartPage.removeItem(productName).isItemInCart(productName))
                .as("Removed product shouldn't be displayed in cart")
                .isFalse();
    }
}
