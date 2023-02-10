package by.teachmeskills.steps;

import by.teachmeskills.LoginTest;
import by.teachmeskills.utils.Credentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
    private Credentials validCredentials;

    public Login() {
        validCredentials = Credentials.getValidCredentials();
    }

    public void loginWithValidCredentials(WebDriver driver) {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys(validCredentials.getLogin());
        driver.findElement(By.id("password")).sendKeys(validCredentials.getPassword());
        driver.findElement(By.id("login-button")).submit();
    }
}
