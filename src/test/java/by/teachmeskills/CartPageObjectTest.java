package by.teachmeskills;

import by.teacmeskills.page.CartPage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CartPageObjectTest extends BaseTest {
    @Test
    public void checkContinueShoppingButton(){
       assertThat(login().openCart().clickContinueShopping().isPageOpened())
               .as("Product page should be opened after clicking ContinueShopping button")
               .isTrue();
    }

    @Test
    public void checkAddRemoveProductButton(){
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
