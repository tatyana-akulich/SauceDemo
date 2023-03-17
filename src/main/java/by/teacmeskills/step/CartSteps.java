package by.teacmeskills.step;

import by.teacmeskills.page.CartPage;
import by.teacmeskills.page.CheckoutPage;
import by.teacmeskills.page.ProductsPage;
import org.openqa.selenium.WebDriver;

public class CartSteps {
    private WebDriver driver;
    private CartPage cartPage;

    public CartSteps(WebDriver driver) {
        this.driver = driver;
        cartPage = new LoginSteps(driver).loginAsStandardUser().openCart();
    }

    public ProductsPage goToCatalog() {
        return cartPage.clickContinueShopping();
    }

    public CheckoutPage checkout() {
        return cartPage.clickCheckout();
    }

    public CartPage deleteItem(String productName) {
        return cartPage.removeItem(productName);
    }
}
