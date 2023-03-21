package by.teacmeskills.page;

import by.teacmeskills.util.PropertiesLoader;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

@Log4j2
public class LoginPage extends BasePage {
    private static final By USER_NAME = By.id("user-name");
    private static final By PASSWORD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR = By.xpath("//h3[@data-test='error']");

    public static final String PASSWORD_ERROR = "Epic sadface: Password is required";
    public static final String USER_NAME_ERROR = "Epic sadface: Username is required";
    public static final String USER_LOCKED_OUT_ERROR = "Epic sadface: Sorry, this user has been locked out.";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        log.info("Opening loginPage using base.url from properties");
        Properties properties = PropertiesLoader.loadProperties();
        driver.get(properties.getProperty("base.url"));
        return this;
    }

    public boolean isOpened() {
        log.info("Checking if loginPage is opened");
        return driver.findElement(LOGIN_BUTTON).isDisplayed();
    }

    public LoginPage enterLogin(String userName) {
        log.info("Entering login - {}", userName);
        driver.findElement(USER_NAME).sendKeys(userName);
        return this;
    }

    public LoginPage enterPassword(String password) {
        log.info("Entering password - {}", password);
        driver.findElement(PASSWORD).sendKeys(password);
        return this;
    }

    public ProductsPage submitCredentials() {
        log.info("Submitting credentials");
        driver.findElement(LOGIN_BUTTON).click();
        return new ProductsPage(driver);
    }

    public String getErrorText() {
        return driver.findElement(ERROR).getText();
    }
}
