package by.teacmeskills.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HeaderPage extends BasePage {

    private final static By MENU_BUTTON = By.id("react-burger-menu-btn");
    private final static By MENU_CATALOG_LINK = By.id("inventory_sidebar_link");
    private final static By MENU_ABOUT_LINK = By.id("about_sidebar_link");
    private final static By MENU_LOGOUT_LINK = By.id("logout_sidebar_link");
    private final static By MENU_RESET_LINK = By.id("reset_sidebar_link");
    private final static By CLOSE_MENU_BUTTON = By.id("react-burger-cross-btn");

    private final static By CART_ICON = By.id("shopping_cart_container");
    private final static By CART_BADGE = By.xpath("//span[@class = 'shopping_cart_badge']");

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    public CartPage openCart() {
        driver.findElement(CART_ICON).click();
        return new CartPage(driver);
    }

    public int getAmountOfItemsInCart() {
        List<WebElement> cartBadges = driver.findElements(CART_BADGE);
        if (cartBadges.size() == 1) {
            return Integer.parseInt(cartBadges.get(0).getText());
        }
        return 0;
    }

    public HeaderPage resetData() {
        driver.findElement(MENU_BUTTON).click();
        driver.findElement(MENU_RESET_LINK).click();
        driver.findElement(CLOSE_MENU_BUTTON).click();
        return this;
    }
}
