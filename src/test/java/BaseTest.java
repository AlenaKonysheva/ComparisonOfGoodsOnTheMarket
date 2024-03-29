import config.ServerConfig;
import driverfactory.WebDriverFactory;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static driverfactory.Browsers.CHROME;

public class BaseTest {
    protected final Logger logger = LogManager.getLogger(YandexMarketTest.class);
    protected final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;
    protected JavascriptExecutor executor;
    protected final static int EXPLICIT_WAIT_TIME_OUT_TIME = 5;

    /**
     * Локаторы
     */
    private final By accept = By.xpath("//*[text()=\"Понятно\"]/..");

    @Before
    public void setUp() {
        logger.info("Set up test");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = WebDriverFactory.create(String.valueOf(CHROME),options);
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIME_OUT_TIME));
        executor = (JavascriptExecutor)driver;
    }

    @After
    public void setDown() {
        logger.info("Finish test");
        if (driver != null) {
            driver.quit();
            logger.info("Driver closed successfully");
        }
    }

    /**
     * клик по элементу по локатору, если клик не удался, то возможно мешает всплывающее окно, убираем его кликом на
     * кнопку "Понятно"
     * @param by - локатор элемента
     */
    protected void clickToElement(By by) {
        try {
            logger.info(String.format("Кликаем %s",by));
            driver.findElement(by).click();
        } catch (Exception e) {
            logger.info("Failed to click element");
            logger.info("Trying to close the popup that closes the element");
            driver.findElement(accept).click();
            driver.findElement(by).click();
        }
    }

    /**
     * метод позволяет навести курсор на элемент
     * @param by - локатор элемента
     */
    protected void moveToElement(By by) {
        actions.moveToElement(driver.findElement(by)).build().perform();
    }
}
