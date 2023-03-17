package by.teacmeskills.step;

import by.teacmeskills.page.LoginPage;
import by.teacmeskills.page.ProductsPage;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
    }

    public ProductsPage loginAs(String name, String password) {
        return loginPage.open()
                .loginAs(name, password);
    }

    public ProductsPage loginAsStandardUser() {
        return loginPage.open()
                .loginAsStandardUser();
    }

    public LoginPage loginAsLockedOutUser() {
        return loginPage.open()
                .loginAsLockedOutUser();
    }

    public ProductsPage loginAsPerformanceGlitchUser() {
        return loginPage.open()
                .loginAsPerformanceGlitchUser();
    }
}
