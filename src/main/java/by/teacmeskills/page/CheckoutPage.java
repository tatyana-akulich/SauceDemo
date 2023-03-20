package by.teacmeskills.page;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CheckoutPage extends HeaderPage {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private static final By CHECKOUT_TITLE = By.xpath("//span[text()='Checkout: Your Information']");
    private static final By FIRST_NAME_FIELD = By.id("first-name");
    private static final By LAST_NAME_FIELD = By.id("last-name");
    private static final By POSTAL_CODE_FIELD = By.id("postal-code");
    private static final By CONTINUE_BUTTON = By.id("continue");

    public CheckoutPage fillInFirstName(String firstName) {
        log.info("Filling firstName field with {}", firstName);
        driver.findElement(FIRST_NAME_FIELD).sendKeys(firstName);
        return this;
    }

    public CheckoutPage fillInLastName(String lastName) {
        log.info("Filling lastName field with {}", lastName);
        driver.findElement(LAST_NAME_FIELD).sendKeys(lastName);
        return this;
    }

    public CheckoutPage fillInPostalCode(String postalCode) {
        log.info("Filling postalCode field with {}", postalCode);
        driver.findElement(POSTAL_CODE_FIELD).sendKeys(postalCode);
        return this;
    }

    public CheckoutOverviewPage clickSubmit() {
        log.info("Proceeding to checkoutOverview");
        driver.findElement(CONTINUE_BUTTON).submit();
        return new CheckoutOverviewPage(driver);
    }

    public boolean isOpened() {
        log.info("Checking if checkoutPage is opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_TITLE));
        return driver.findElement(CHECKOUT_TITLE).isDisplayed();
    }
}
