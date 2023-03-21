package by.teacmeskills.page;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CheckoutOverviewPage extends BasePage{
    private static final By CHECKOUT_OVERVIEW_TITLE = By.xpath("//span[text()='Checkout: Overview']");
    private static final By FINISH_BUTTON = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpened() {
        log.info("Checking if checkoutOverviewPage is opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_OVERVIEW_TITLE));
        return driver.findElement(CHECKOUT_OVERVIEW_TITLE).isDisplayed();
    }

    public CheckoutCompletePage finish() {
        log.info("Finishing checkout");
        driver.findElement(FINISH_BUTTON).click();
        return new CheckoutCompletePage(driver);
    }
}
