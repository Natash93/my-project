package by.itacademy.nnaralenkova.project.pages;

import by.itacademy.nnaralenkova.project.util.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.function.Function;

public abstract class BasePage {
    static final int DEFAULT_WAIT_TIME = 5;
    static final long DEFAULT_POLLING_INTERVAL = 100;

    protected WebDriver driver;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void openMainPage() {
        driver.get("https://www.21vek.by/");
    }

    public void quit() {
        DriverManager.quitDriver();
    }

    protected void clickWithJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    protected void scrollToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    protected void waitUntil(int waitInSec, Function<WebDriver, Boolean> isTrue){
        new FluentWait<>(driver)
                .pollingEvery(Duration.ofMillis(DEFAULT_POLLING_INTERVAL))
                .withTimeout(Duration.ofSeconds(waitInSec))
                .until(isTrue);
    }

    protected void waitUntil(Function<WebDriver, Boolean> isTrue){
        waitUntil(DEFAULT_WAIT_TIME, isTrue);
    }
}
