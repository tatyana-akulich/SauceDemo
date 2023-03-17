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

    private static final String STANDARD_USER = "standard_user";
    private static final String LOCKED_OUT_USER = "locked_out_user";
    private static final String PERFORMANCE_GLITCH_USER = "performance_glitch_user";
    private static final String STANDARD_PASSWORD = "secret_sauce";

    private static final String DEFAULT_USER = STANDARD_USER;
    private static final String DEFAULT_PASSWORD = STANDARD_PASSWORD;

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

    public ProductsPage loginAsDefaultUser() {
        log.info("Login with default - standard user {} and password {}", DEFAULT_USER, DEFAULT_PASSWORD);
        loginAs(DEFAULT_USER, DEFAULT_PASSWORD);
        return new ProductsPage(driver);
    }

    public ProductsPage loginAs(String userName, String password) {
        log.info("Login with user {} and password {}", userName, password);
        driver.findElement(USER_NAME).sendKeys(userName);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new ProductsPage(driver);
    }

    public ProductsPage loginAsStandardUser() {
        log.info("Login with standard user {} and password {}", DEFAULT_USER, DEFAULT_PASSWORD);
        loginAs(STANDARD_USER, STANDARD_PASSWORD);
        return new ProductsPage(driver);
    }

    public LoginPage loginAsLockedOutUser() {
        log.info("Login with locked user {} and password {}",LOCKED_OUT_USER, STANDARD_PASSWORD);
        loginAs(LOCKED_OUT_USER, STANDARD_PASSWORD);
        return this;
    }

    public ProductsPage loginAsPerformanceGlitchUser() {
        log.info("Login with glitch user {} and password {}",PERFORMANCE_GLITCH_USER, DEFAULT_PASSWORD);
        loginAs(PERFORMANCE_GLITCH_USER, STANDARD_PASSWORD);
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.isPageOpened();
        return productsPage;
    }

    public String getErrorText() {
        return driver.findElement(ERROR).getText();
    }
}
