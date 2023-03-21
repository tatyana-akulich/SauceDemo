package by.teachmeskills;

import by.teacmeskills.page.ProductsPage;
import by.teacmeskills.step.LoginSteps;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Step("Login as standard user")
    @Test
    public void checkValidCredentials() {
        new LoginSteps(driver).loginAsStandardUser();
        Assert.assertTrue(new ProductsPage(driver).isPageOpened(),
                "Wrong reaction for valid credentials");
    }

    @Test(dataProvider = "invalidCredentials")
    public void checkInvalidCredentialsValidation(String login, String password, String message) {
        new LoginSteps(driver).loginAs(login, password);
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'error')]")).isDisplayed(), message);
    }

    @DataProvider
    public Object[][] invalidCredentials() {
        return new Object[][]{
                {"", "", "Empty fields shouldn't be accepted"},
                {" ", " ", "Only whitespaces shouldn't be accepted"},
                {"standard_user", "", "Empty password shouldn't be accepted"},
                {"", "secret_sauce", "Empty login shouldn't be accepted"},
                {"abcd", "secret_sauce", "Invalid login shouldn't be accepted"},
                {"locked_out_user", "secret_sauce1",
                        "Password with extra symbols shouldn't be accepted"},
                {"locked_out_user", "  secret_sauce ",
                        "Password with extra whitespaces shouldn't be accepted"}, //program doesn't trim initial/final whitespaces
                {"abcd1234", "dhfjkt", "Invalid credentials shouldn't be accepted"}};
    }
}
