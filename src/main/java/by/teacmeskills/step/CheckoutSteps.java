package by.teacmeskills.step;

import by.teacmeskills.page.CheckoutCompletePage;
import by.teacmeskills.page.CheckoutOverviewPage;
import by.teacmeskills.page.CheckoutPage;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

@Log4j2
public class CheckoutSteps {
    private WebDriver driver;
    private CheckoutPage checkoutPage;
    private CheckoutOverviewPage checkoutOverviewPage;

    public CheckoutSteps(WebDriver driver) {
        this.driver = driver;
        checkoutPage = new LoginSteps(driver).loginAsStandardUser().openCart().clickCheckout();
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
    }

    public CheckoutOverviewPage fillInData(String firstName, String lastName, String postalCode) {
        log.info("Filling users data: firstName - {}, lastName - {}, postalCode - {}",
                firstName, lastName, postalCode);
        return checkoutPage
                .fillInFirstName(firstName)
                .fillInLastName(lastName)
                .fillInPostalCode(postalCode)
                .clickSubmit();
    }

    public CheckoutCompletePage finishOrder() {
        return checkoutOverviewPage.finish();
    }
}
