package by.itacademy.nnaralenkova.project.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public class MainPage extends BasePage {

    @FindBy(css = ".AgreementCookie_modal__x3nra .Button-module__blue-primary")
    private WebElement acceptCookiesButton;
    @FindBy(css = ".headerCartLabel")
    private WebElement cartEnterButton;

    @FindBy(id = "catalogSearch")
    private WebElement searchInputField;

    @FindBy(xpath = "//li[@class=\"styles_promoItem__aolWq\"]")
    private List<WebElement> promotedCategories;

    @FindBy(css = "button[data-testid=\"in-basket-button\"]")
    private List<WebElement> addToCartButtons;

    @FindBy(className = "Button-module__pink-secondary")
    private WebElement addedToCartButton;
    @FindBy(className = "headerCartCount")
    private WebElement headerCartCount;
    @FindBy(className = "BasketItem_title__MzCQ9")
    private List<WebElement> cartItemsNames;

    @FindBy(css = "[data-testid=\"card-info-a\"]")
    private List<WebElement> productsNames;

    @FindBy(css = ".popmechanic-reset .popmechanic-close")
    private WebElement closePromoPopupButton;

    private static final Logger LOGGER = LogManager.getLogger();


    public void acceptCookies() {
        try {
            new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class)
                    .until((driver) -> acceptCookiesButton.isDisplayed());
            acceptCookiesButton.click();
        } catch (NoSuchElementException | TimeoutException e) {
            LOGGER.info("AcceptCookiesButton was not shown");
        }
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

    private void scrollToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void addToCart(int index) {
        scrollToElement(addToCartButtons.get(index));
        addToCartButtons.get(index).click();
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .until((driver) -> addedToCartButton.isDisplayed());
    }

    public String getHeaderCartCount() {
        scrollToElement(headerCartCount);
        return headerCartCount.getText();
    }

    public String getProductName(int index) {
        return productsNames.get(index).getText();
    }

    public String getCartItemName(int index) {
        return cartItemsNames.get(index).getText();
    }

    public void goToCart() {
        scrollToElement(cartEnterButton);
        cartEnterButton.click();
    }

    public void closePromoPopup() {
        try {
            new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(5))
                    .ignoring(NoSuchElementException.class)
                    .until((driver) -> closePromoPopupButton.isDisplayed());
            closePromoPopupButton.click();
        } catch (NoSuchElementException | TimeoutException e) {
            LOGGER.info("No promo Popup was shown");
        }
    }
}
