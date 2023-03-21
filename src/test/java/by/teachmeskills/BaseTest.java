package by.teachmeskills;

import by.teacmeskills.page.LoginPage;
import by.teacmeskills.page.ProductsPage;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
@Log4j2
public class BaseTest {
    protected WebDriver driver;
    protected Faker faker;

    @BeforeClass
    public void setUp(ITestContext testContext) {
        String headlessOnJenkins = System.getenv("HEADLESS");
        log.info("Environmental variable from Jenkins - {}", headlessOnJenkins);
        boolean isHeadless = false;
        if (StringUtils.isNotEmpty(headlessOnJenkins)) {
            isHeadless = Boolean.parseBoolean(headlessOnJenkins);
        }
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (isHeadless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5L));
        faker = new Faker();
        testContext.setAttribute("driver", driver);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
