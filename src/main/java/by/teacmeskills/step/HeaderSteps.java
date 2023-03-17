package by.teacmeskills.step;

import by.teacmeskills.page.CartPage;
import by.teacmeskills.page.HeaderPage;
import by.teacmeskills.page.LoginPage;
import by.teacmeskills.page.ProductsPage;
import org.openqa.selenium.WebDriver;

public class HeaderSteps {
    private WebDriver driver;
    private HeaderPage headerPage;

    public HeaderSteps(WebDriver driver) {
        this.driver = driver;
        headerPage = new HeaderPage(driver);
    }

    public HeaderPage resetData(){
        return headerPage.resetData();
    }

    public CartPage openCart(){
        return headerPage.openCart();
    }

    public LoginPage logout(){
        return headerPage.logout();
    }

    public ProductsPage goToCatalog(){
        return headerPage.goToProductPage();
    }


}
