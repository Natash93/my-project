package by.itacademy.nnaralenkova.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public class CategoriesPage extends BasePage{

    @FindBy(css = ".styles_catalogButton__z9L_j")
    private WebElement catalogueEnterButton;

    @FindBy(css = "[data-testid=\"category-name\"]")
    private WebElement categoryName;

    @FindBy(id = "minPrice")
    private WebElement minPriceInput;

    @FindBy(id = "maxPrice")
    private WebElement maxPriceInput;

    @FindBy(css = "[data-testid=\"apply-products-filters\"]")
    private WebElement applyFiltersButton;

    @FindBy(css = "p[data-testid=\"card-current-price\"]")
    private List<WebElement> productsPrices;

    @FindBy(css = "#snackbar-container>div")
    private WebElement snackbar;

    @FindBy(id = "snackbar-container")
    private WebElement snackbarLayout;

    @FindBy (css = ".styles_catalogButton__z9L_j")
    private WebElement openCatalogueButton;

    @FindBy(className = "styles_categoryButton__BbUU3")
    private List<WebElement> categoriesInCatalogue;

    @FindBy(xpath = "//*[@href = \"https://www.21vek.by/bikes/\"]")
    private WebElement subcategory;


    public String getCategoryName() {
        return categoryName.getText();
    }

    public void chooseFirstSuggestedItem(String text) {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .until((t) -> {
                    WebElement firstSuggestedItem = driver.findElement(By.xpath("//*[@class=\"SearchSuggestList_listItem__C2I5H\"][1]//*[text() = \"" + text + "\"]"));
                    boolean isDisplayed = firstSuggestedItem.isDisplayed();
                    if (isDisplayed) firstSuggestedItem.click();
                    return isDisplayed;
                });
    }
     public void openCatalogue (){
         openCatalogueButton.click();
     }
    public void pickCategoryInCatalogue(int index) {
        scrollToElement(categoriesInCatalogue.get(index));
        categoriesInCatalogue.get(index).click();
    }
    private void scrollToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
    public void chooseSubcategory(){
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

    public void applyFilters() {
        applyFiltersButton.click();
    }

    public List<Integer> getProductsPrices() {
        return productsPrices.stream()
                .map((el) -> el.getText())
                .map((text) -> Integer.parseInt(text.substring(0, text.indexOf(","))))
                .toList();
    }

    public void waitForSnackbar() {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .until((t) -> snackbar.isDisplayed());
    }

    public void waitForSnackbarDismissed() {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(50))
                .until((t) -> snackbarLayout.findElements(By.tagName("div")).isEmpty());
    }
}
