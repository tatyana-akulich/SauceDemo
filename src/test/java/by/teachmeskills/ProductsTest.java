package by.teachmeskills;

import by.teacmeskills.page.LoginPage;
import by.teacmeskills.page.ProductsPage;
import by.teacmeskills.step.LoginSteps;
import io.qameta.allure.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsTest extends BaseTest {
    @Epic("Functionality of products page")
    @Feature("Depiction of elements on products page")
    @Story("User should be able to see 6 products on each page")
    @Test
    public void checkAmountOfProducts() {
        assertThat(new LoginSteps(driver).loginAsStandardUser().getProducts())
                .as("List shouldn't be empty")
                .isNotEmpty()
                .as("Amount of products on the page is wrong, should be 6")
                .hasSize(6);
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void checkProductPresentOnProductPage() {
        String productName = "Sauce Labs Backpack";
        List<WebElement> productsInCartNames = new LoginSteps(driver)
                .loginAsStandardUser()
                .getProductNames();
        List<String> names = productsInCartNames.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        assertThat(names).as("Element should be displayed on the product page")
                .contains(productName);
    }
}
