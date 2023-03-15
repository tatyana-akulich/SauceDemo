package by.teachmeskills;

import by.teacmeskills.page.CheckoutPage;
import by.teacmeskills.page.LoginPage;
import by.teacmeskills.page.ProductsPage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutTest extends BaseTest {

    @Test
    public void checkContinueShoppingButtonReturnsUserOnProductsPage() {
        ProductsPage productsPage = new LoginPage(driver).open()
                .loginAsDefaultUser()
                .openCart()
                .clickContinueShopping();
        assertThat(productsPage.isPageOpened()).as("The \"Continue shopping\" button doesn't open Products page")
                .isTrue();
    }

    @Test
    public void checkCheckoutButtonOpensCheckoutPage() {
        CheckoutPage checkout = new LoginPage(driver).open()
                .loginAsDefaultUser()
                .openCart()
                .clickCheckout();
        assertThat(checkout.isOpened()).as("The \"Checkout button\" does not open checkout page")
                .isTrue();
    }
}

