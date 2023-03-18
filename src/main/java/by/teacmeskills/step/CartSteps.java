package by.teacmeskills.step;

import by.teacmeskills.page.CartPage;
import by.teacmeskills.page.CheckoutPage;
import org.openqa.selenium.WebDriver;

public class CartSteps {
    private WebDriver driver;
    private CartPage cartPage;
    private ProductsSteps productsSteps;

    public CartSteps(WebDriver driver) {
        this.driver = driver;
        cartPage = new LoginSteps(driver).loginAsStandardUser().openCart();
    }

    public CartPage addProductAndPassToCart(String productName) {
        return new ProductsSteps(driver).addProductToCart(productName).openCart();
    }

    public CheckoutPage checkout() {
        return cartPage.clickCheckout();
    }

    public CartPage deleteItem(String productName) {
        return cartPage.removeItem(productName);
    }
}
