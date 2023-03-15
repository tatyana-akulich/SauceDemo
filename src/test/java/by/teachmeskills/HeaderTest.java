package by.teachmeskills;

import by.teacmeskills.page.ProductsPage;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HeaderTest extends BaseTest {
    @TmsLink("SHARELANE-4")
    @Test
    public void checkAmountOfItemsInCartIcon() {
        int amountOfItems = login().getAmountOfItemsInCart();
        assertThat(new ProductsPage(driver).addProductToCart("Sauce Labs Backpack").getAmountOfItemsInCart())
                .as("Amount of elements in cart should be 1 (only 1 item was added)")
                .isEqualTo(amountOfItems + 1);
    }

    @Issue("?selectedIssue=SQA-1")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void checkResetOption() {
        assertThat(login().addProductToCart("Sauce Labs Bike Light").resetData().getAmountOfItemsInCart()).isEqualTo(0);
    }
}
