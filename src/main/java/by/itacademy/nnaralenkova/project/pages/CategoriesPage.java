package by.itacademy.nnaralenkova.project.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public class CategoriesPage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger();

    @FindBy(xpath = "//*[@data-testid=\"category-name\"]")
    private WebElement categoryName;

    @FindBy(id = "minPrice")
    private WebElement minPriceInput;

    @FindBy(id = "maxPrice")
    private WebElement maxPriceInput;

    @FindBy(xpath = "//p[@data-testid=\"card-current-price\"]")
    private List<WebElement> productsPrices;

    @FindBy(css = "#snackbar-container>div")
    private WebElement snackbar;

    @FindBy(id = "snackbar-container")
    private WebElement snackbarLayout;

    @FindBy(className = "styles_categoryButton__BbUU3")
    private List<WebElement> categoriesInCatalogue;

    @FindBy(xpath = "//*[@href = \"https://www.21vek.by/bikes/\"]")
    private WebElement subcategory;

    @FindBy(css = ".popmechanic-reset .popmechanic-close")
    private WebElement closePromoPopupButton;

    @FindBy(className = "ListingProduct_product__WBPsd")
    private List<WebElement> productCardsInCategory;

    @FindBy(css = "[data-testid=\"card-info-a\"]")
    private List<WebElement> productsNames;

    public String getCategoryName() {
        return categoryName.getText();
    }

    public void closePromoPopup() {
        try {
            waitUntil(10, (driver) -> closePromoPopupButton.isDisplayed());
            closePromoPopupButton.click();
        } catch (NoSuchElementException | TimeoutException e) {
            LOGGER.info("No promo Popup was shown");
        }
    }

    public void addToCart(int... indexes) {
        for (int i : indexes) {
            addToCart(i);
        }
    }

    public void addToCart(int index) {
        WebElement addToCartButton = productCardsInCategory.get(index)
                .findElement(By.cssSelector("button[data-testid=\"in-basket-button\"]"));
        scrollToElement(addToCartButton);
        addToCartButton.click();

        WebElement addedToCartButton = productCardsInCategory.get(index)
                .findElement(By.className("Button-module__pink-secondary"));
        waitUntil((driver) -> addedToCartButton != null);
    }

    public void chooseFirstSuggestedItem(String text) {
        new FluentWait<>(driver)
                .ignoring(NoSuchElementException.class)
                .withTimeout(Duration.ofSeconds(10))
                .until((t) -> {
                    WebElement firstSuggestedItem = driver.findElement(By.xpath("//*[@class=\"SearchSuggestList_listItem__C2I5H\"][1]//*[text() = \"" + text + "\"]"));
                    boolean isDisplayed = firstSuggestedItem.isDisplayed();
                    if (isDisplayed) firstSuggestedItem.click();
                    return isDisplayed;
                });
    }

    public void pickCategoryInCatalogue(int index) {
        scrollToElement(categoriesInCatalogue.get(index));
        categoriesInCatalogue.get(index).click();
    }

    public void chooseSubcategory() {
        scrollToElement(subcategory);
        subcategory.click();
    }

    public void setMinPrice(String min) {
        clickWithJS(minPriceInput);
        minPriceInput.sendKeys(min);
    }

    public void setMaxPrice(String max) {
        clickWithJS(maxPriceInput);
        maxPriceInput.sendKeys(max);
    }

    public List<Integer> getProductsPrices() {
        return productsPrices.stream()
                .map((el) -> el.getText())
                .map((text) -> Integer.parseInt(text.substring(0, text.indexOf(","))))
                .toList();
    }

    public void waitForSnackbar() {
        waitUntil((t) -> snackbar.isDisplayed());
    }

    public void waitForSnackbarDismissed() {
        waitUntil((t) -> snackbarLayout.findElements(By.tagName("div")).isEmpty());
    }

    public String getProductName(int index) {
        return productsNames.get(index).getText();
    }
}
