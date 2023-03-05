package by.teachmeskills;

import by.teachmeskills.utils.Credentials;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void getHomePage() {
        driver.get("https://www.saucedemo.com/");
    }

    @Step("Login as standard user")
    @Test
    public void checkValidCredentials() {
        submitCredentials(Credentials.getValidCredentials());
        Assert.assertTrue(driver.findElement(By.id("react-burger-menu-btn")).isDisplayed(),
                "Wrong reaction for valid credentials");
    }

    @Test(dataProvider = "invalidCredentials")
    public void checkInvalidCredentialsValidation(Credentials data, String message) {
        submitCredentials(data);
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'error')]")).isDisplayed(), message);
    }

    @DataProvider
    public Object[][] invalidCredentials() {
        return new Object[][]{
                {new Credentials("", ""), "Empty fields shouldn't be accepted"},
                {new Credentials(" ", " "), "Only whitespaces shouldn't be accepted"},
                {new Credentials("standard_user", ""), "Empty password shouldn't be accepted"},
                {new Credentials("", "secret_sauce"), "Empty login shouldn't be accepted"},
                {new Credentials("abcd", "secret_sauce"), "Invalid login shouldn't be accepted"},
                {new Credentials("locked_out_user", "secret_sauce1"),
                        "Password with extra symbols shouldn't be accepted"},
                {new Credentials("locked_out_user", "  secret_sauce "),
                        "Password with extra whitespaces shouldn't be accepted"}, //program doesn't trim initial/final whitespaces
                {new Credentials("abcd1234", "dhfjkt"), "Invalid credentials shouldn't be accepted"}};
    }

    private void submitCredentials(Credentials data) {
        driver.findElement(By.id("user-name")).sendKeys(data.getLogin());
        driver.findElement(By.id("password")).sendKeys(data.getPassword());
        driver.findElement(By.id("login-button")).submit();
    }
}
