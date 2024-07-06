package by.itacademy.nnaralenkova.project.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public class MainPage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger();

    @FindBy(css = ".userToolsText")
    private WebElement loginLink;

    @FindBy(css = "button[data-testid='loginButton']")
    private WebElement signInButton;

    @FindBy(id = "userToolsDropDown")
    private WebElement userDropDown;

    @FindBy(css = ".AgreementCookie_modal__x3nra .Button-module__blue-primary")
    private WebElement acceptCookiesButton;

    @FindBy(css = ".headerCartLabel")
    private WebElement cartEnterButton;

    @FindBy(id = "catalogSearch")
    private WebElement searchInputField;

    @FindBy(xpath = "//li[@class=\"styles_promoItem__aolWq\"]")
    private List<WebElement> promotedCategories;

    @FindBy(css = ".styles_catalogButton__z9L_j")
    private WebElement openCatalogueButton;

    @FindBy(className = "headerCartCount")
    private WebElement headerCartCount;

    public void acceptCookies() {
        try {
            new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(10)).ignoring(NoSuchElementException.class)
                    .until((driver) -> acceptCookiesButton.isDisplayed());
            acceptCookiesButton.click();
        } catch (NoSuchElementException | TimeoutException e) {
            LOGGER.info("AcceptCookiesButton was not shown");
        }
    }

    public void openCatalogue() {
        openCatalogueButton.click();
    }

    public void inputSearchInputFieldWithJs(String search) {
        searchInputField.click();

        String valueText = search.substring(0, search.length() - 1);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('catalogSearch').setAttribute('value', '" + valueText + "')");

        searchInputField.sendKeys(Keys.END);
        searchInputField.sendKeys(search.substring(search.length() - 1));
    }

    public void selectPromotedCategory(int index) {
        promotedCategories.get(index).click();
    }

    public void goToCart() {
        scrollToElement(cartEnterButton);
        cartEnterButton.click();
    }

    public String getHeaderCartCount() {
        scrollToElement(headerCartCount);
        return headerCartCount.getText();
    }

    public void openEmailLoginForm() {
        loginLink.click();
        signInButton.click();
    }

    public void openLoginForm() {
        loginLink.click();
        waitUntil((t) -> userDropDown.isDisplayed());
    }
}
