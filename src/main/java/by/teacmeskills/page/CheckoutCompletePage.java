package by.teacmeskills.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutCompletePage extends HeaderPage {
    private static final By CHECKOUT_COMPLETE_TITLE = By.xpath("//span[text()='Checkout: Complete!']");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public ProductsPage backHome() {
        driver.findElement(MENU_BUTTON).click();
        driver.findElement(MENU_CATALOG_LINK).click();
        return new ProductsPage(driver);
    }

    public boolean isOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CHECKOUT_COMPLETE_TITLE));
        return driver.findElement(CHECKOUT_COMPLETE_TITLE).isDisplayed();
    }
}
