package by.teachmeskills;

import by.teacmeskills.page.ProductsPage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HeaderTest extends BaseTest {

    @Test
    public void checkAmountOfItemsInCartIcon() {
        int amountOfItems = login().getAmountOfItemsInCart();
        assertThat(new ProductsPage(driver).addProductToCart("Sauce Labs Backpack").getAmountOfItemsInCart())
                .as("Amount of elements in cart should be 1 (only 1 item was added)")
                .isEqualTo(amountOfItems + 1);
    }

    @Test
    public void checkResetOption() {
        assertThat(login().addProductToCart("Sauce Labs Bike Light").resetData().getAmountOfItemsInCart()).isEqualTo(0);
    }
}
