package by.itacademy.nnaralenkova.project.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class DriverManager {
    static WebDriver driver;
    static ThreadLocal<WebDriver> localDriver = new ThreadLocal<>();
    private static final Logger LOGGER = LogManager.getLogger(DriverManager.class);
    private static final String BROWSER_CHROME = "chrome";

    public synchronized static WebDriver getDriver() {
        if (localDriver.get() == null) {
            driver = DriverFactory.createDriver(BROWSER_CHROME);
            //EventFiringDecorator<WebDriver> decorator = new EventFiringDecorator(new ElementActionListener());
            //driver = decorator.decorate(driver);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            localDriver.set(driver);
            return driver;
        } else {
            return localDriver.get();
        }
    }

    public static void quitDriver() {
        localDriver.get().quit();
        localDriver.set(null);
    }
}
