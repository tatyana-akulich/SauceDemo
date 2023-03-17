package by.teacmeskills.step;

import by.teacmeskills.page.CartPage;
import by.teacmeskills.page.ProductDescriptionPage;
import by.teacmeskills.page.ProductSortFilter;
import by.teacmeskills.page.ProductsPage;
import org.openqa.selenium.WebDriver;

public class ProductsSteps {
    private WebDriver driver;
    private ProductsPage productsPage;

    public ProductsSteps(WebDriver driver) {
        this.driver = driver;
        productsPage = new LoginSteps(driver).loginAsStandardUser();
    }

    public ProductsPage addProductToCart(String productName) {
        return productsPage.addProductToCart(productName);
    }

    public ProductsPage deleteProductFromCart (String productName){
        return productsPage.removeItemFromCartFromProductListPage(productName);
    }

    public CartPage passToCart() {
        return productsPage.openCart();
    }

    public ProductsPage sortProducts (ProductSortFilter filter){
        return productsPage.sortProducts(filter);
    }

    public ProductDescriptionPage openDescriptionPage(String productName){
        return productsPage.openDescriptionPage(productName);
    }
}
