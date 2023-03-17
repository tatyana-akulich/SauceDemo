package by.teachmeskills;

import by.teacmeskills.page.CheckoutCompletePage;
import by.teacmeskills.page.LoginPage;
import by.teacmeskills.step.CheckoutSteps;
import by.teacmeskills.step.LoginSteps;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SuccessOrderFlowTest extends BaseTest {

    private static final String SAUCE_LABS_ONESIE = "Sauce Labs Onesie";

    @Test
    public void checkSuccessOneProductOrderFlow() {
        String productName = System.getenv("ProductName");
        if (StringUtils.isEmpty(productName)) {
            productName = SAUCE_LABS_ONESIE;
        }

        new LoginSteps(driver)
                .loginAsStandardUser()
                .addProductToCart(productName)
                .openCart()
                .clickContinueShopping();
        CheckoutCompletePage finalPage = new CheckoutSteps(driver)
                .fillInData(faker.name().firstName(), faker.name().lastName(), faker.address().zipCode())
                .finish();
        assertThat(finalPage.isOpened()).as("Final page has not been opened after checkout")
                .isTrue();
    }
}
