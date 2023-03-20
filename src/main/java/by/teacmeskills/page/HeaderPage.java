package by.teacmeskills.page;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Log4j2
public class HeaderPage extends BasePage {

    protected final static By MENU_BUTTON = By.id("react-burger-menu-btn");
    protected final static By MENU_CATALOG_LINK = By.id("inventory_sidebar_link");
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
        log.info("Opening cartPage from HeaderMenu");
        driver.findElement(CART_ICON).click();
        return new CartPage(driver);
    }

    public int getAmountOfItemsInCart() {
        log.info("Getting amount of items in cart from cart icon in HeaderMenu");
        List<WebElement> cartBadges = driver.findElements(CART_BADGE);
        if (cartBadges.size() == 1) {
            return Integer.parseInt(cartBadges.get(0).getText());
        }
        return 0;
    }

    public HeaderPage resetData() {
        log.info("Reseting data using HeaderMenu");
        driver.findElement(MENU_BUTTON).click();
        driver.findElement(MENU_RESET_LINK).click();
        driver.findElement(CLOSE_MENU_BUTTON).click();
        return this;
    }

    public ProductsPage goToProductPage() {
        log.info("Going to product catalog using HeaderMenu");
        driver.findElement(MENU_BUTTON).click();
        driver.findElement(MENU_CATALOG_LINK).click();
        driver.findElement(CLOSE_MENU_BUTTON).click();
        return new ProductsPage(driver);
    }

    public LoginPage logout() {
        log.info("Logout using HeaderMenu");
        driver.findElement(MENU_BUTTON).click();
        driver.findElement(MENU_LOGOUT_LINK).click();
        driver.findElement(CLOSE_MENU_BUTTON).click();
        return new LoginPage(driver);
    }
}
