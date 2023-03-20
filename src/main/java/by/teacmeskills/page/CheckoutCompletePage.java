package by.teacmeskills.page;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class CheckoutCompletePage extends HeaderPage {
    private static final By CHECKOUT_COMPLETE_TITLE = By.xpath("//span[text()='Checkout: Complete!']");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public ProductsPage backHome() {
        log.info("Going from checkoutPage to productPage using headerMenu");
        driver.findElement(MENU_BUTTON).click();
        driver.findElement(MENU_CATALOG_LINK).click();
        return new ProductsPage(driver);
    }

    public boolean isOpened() {
        log.info("Checking if checkoutCompletePage is opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_COMPLETE_TITLE));
        return driver.findElement(CHECKOUT_COMPLETE_TITLE).isDisplayed();
    }
}
