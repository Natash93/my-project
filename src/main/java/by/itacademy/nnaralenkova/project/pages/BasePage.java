package by.itacademy.nnaralenkova.project.pages;

import by.itacademy.nnaralenkova.project.util.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
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
}
