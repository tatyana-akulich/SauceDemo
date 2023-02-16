package by.teacmeskills.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    private static final By USER_NAME = By.id("user-name");
    private static final By PASSWORD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR = By.xpath("//h3[@data-test = 'error]");
    public static final String PASSWORD_ERROR = "Epic sadface: Password is required";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get("https://www.saucedemo.com/");
        return new LoginPage(driver);
    }

    public void loginAs(String username, String password) {
        driver.findElement(USER_NAME).sendKeys(username);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).submit();
    }

    public ProductsPage loginAsStandardUser() {
        loginAs("standard_user", "secret_sauce");
        return new ProductsPage(driver);
    }

    public String getErrorText() {
        WebElement error = driver.findElement(ERROR);
        return error.getText();
    }
}
