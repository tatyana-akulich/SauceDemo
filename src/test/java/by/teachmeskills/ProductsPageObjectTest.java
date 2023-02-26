package by.teachmeskills;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductsPageObjectTest extends BaseTest {
    @Test
    public void checkAmountOfProducts() {
        assertThat(login().getProducts())
                .as("List shouldn't be empty")
                .isNotEmpty()
                .as("Amount of products on the page is wrong, should be 6")
                .hasSize(6);
    }

    @Test
    public void checkProductPresentOnProductPage() {
        String productName = "Sauce Labs Backpack";
        List<WebElement> productsInCartNames = login().getProductNames();
        List<String> names = productsInCartNames.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        assertThat(names).as("Element should be displayed on the product page")
                .contains(productName);
    }
}
