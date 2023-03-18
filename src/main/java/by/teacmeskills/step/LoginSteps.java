package by.teacmeskills.step;

import by.teacmeskills.page.LoginPage;
import by.teacmeskills.page.ProductsPage;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;

    private static final String STANDARD_USER = "standard_user";
    private static final String LOCKED_OUT_USER = "locked_out_user";
    private static final String PERFORMANCE_GLITCH_USER = "performance_glitch_user";
    private static final String STANDARD_PASSWORD = "secret_sauce";

    public LoginSteps(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
    }

    public ProductsPage loginAs(String name, String password) {
        return loginPage.open()
                .enterLogin(name)
                .enterPassword(password)
                .submitCredentials();
    }

    public ProductsPage loginAsStandardUser() {
        loginPage.open();
        return loginAs(STANDARD_USER, STANDARD_PASSWORD);
    }

    public LoginPage loginAsLockedOutUser() {
        loginPage.open()
                .enterLogin(LOCKED_OUT_USER)
                .enterPassword(STANDARD_PASSWORD)
                .submitCredentials();
        return loginPage;
    }

    public ProductsPage loginAsPerformanceGlitchUser() {
        loginPage.open();
        return loginAs(PERFORMANCE_GLITCH_USER, STANDARD_PASSWORD);
    }
}
