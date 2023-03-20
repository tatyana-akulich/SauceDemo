package by.teachmeskills;

import by.teacmeskills.page.CartPage;
import by.teacmeskills.page.ProductsPage;
import by.teacmeskills.step.LoginSteps;
import by.teacmeskills.step.ProductsSteps;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest {

    @BeforeClass
    public void passRegistration() {
        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.loginAsStandardUser();
    }

    @DataProvider
    public Object[][] products() {
        return new Object[][]{{"Sauce Labs Backpack"}, {"Sauce Labs Fleece Jacket"}};
    }

    @Test(dataProvider = "products")
    public void checkAddItemToCartButtonAfterClickChangesToRemove(String productName) {
        ProductsSteps productsSteps = new ProductsSteps(driver);
        productsSteps.addProductToCart(productName);
        assertTrue(new ProductsPage(driver).getDeleteButton(productName).isDisplayed(),
                "Add button wasn't changed to remove");
        productsSteps.deleteProductFromCart(productName);
    }

    @Test(dataProvider = "products")
    public void checkRemoveButtonAfterClickChangesToAddToCart(String productName) {
        new ProductsSteps(driver)
                .addProductToCart(productName)
                .removeItemFromCartFromProductListPage(productName);
        assertTrue(new ProductsPage(driver).getAddButton(productName).isDisplayed(),
                "Add to cart button should be displayed");
    }

    @Step("Add item to cart")
    @Severity(SeverityLevel.BLOCKER)
    @Test(dataProvider = "products")
    public void checkItemInCart(String productName) {
        ProductsSteps productsSteps = new ProductsSteps(driver);
        productsSteps.addProductToCart(productName)
                .openCart();
        assertTrue(new CartPage(driver).isItemInCart(productName),
                "There is no such item in the cart");
        productsSteps.deleteProductFromCart(productName);
    }

    @Test(dataProvider = "products")
    public void checkPriceInCart(String productName) {
        String expectedPrice = new ProductsPage(driver).getPrice(productName);
        new ProductsSteps(driver).addProductToCart(productName)
                .openCart();
        String actualPrice = new CartPage(driver).getItemPrice(productName);
        assertEquals(actualPrice, expectedPrice, "Prices in catalog and in the cart are different");
        new ProductsSteps(driver).deleteProductFromCart(productName);
    }

    @Test
    public void checkContinueShoppingButton() {
        assertThat(new LoginSteps(driver)
                .loginAsStandardUser()
                .openCart()
                .clickContinueShopping()
                .isPageOpened())
                .as("Product page should be opened after clicking ContinueShopping button")
                .isTrue();
    }

    @Test
    public void checkAddRemoveProductButton() {
        String productName = "Sauce Labs Bolt T-Shirt";
        CartPage cartPage = new ProductsSteps(driver)
                .addProductToCart(productName)
                .openCart();
        assertThat(cartPage.isItemInCart(productName))
                .as("Added product should be displayed in cart")
                .isTrue();
        assertThat(cartPage.removeItem(productName).isItemInCart(productName))
                .as("Removed product shouldn't be displayed in cart")
                .isFalse();
    }
}
