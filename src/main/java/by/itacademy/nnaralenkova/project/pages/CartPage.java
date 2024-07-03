package by.itacademy.nnaralenkova.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage {

    /*@FindBy(css = "button[data-testid=\"in-basket-button\"]")
    private List<WebElement> addToCartButtons;*/

    @FindBy(className = "ListingProduct_product__WBPsd")
    private List<WebElement> productCardsInCategory;

    /*@FindBy(className = "Button-module__pink-secondary")
    private List<WebElement> addedToCartButtons;*/

    @FindBy(className = "headerCartCount")
    private WebElement headerCartCount;

    @FindBy(className = "BasketItem_title__MzCQ9")
    private List<WebElement> cartItemsNames;

    @FindBy(css = "[data-testid=\"basketConfirmation\"]")
    private WebElement placeOrderButton;

    @FindBy(css = ".PriceFooter_continue__JAr4D")
    private WebElement continuePacingOrderButton;

    @FindBy(className = "ButtonsBlock_icon__x_nBQ")
    private List<WebElement> deleteFromCartIcons;

    @FindBy(css = "[data-testid=\"modal-confirmation-button\"]")
    private WebElement deleteConfirmationButton;

    @FindBy (className = "styles_modalConfirmationWrapper__LM_v6")
    private List<WebElement> deleteConfirmationPopups;

    private void scrollToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
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

        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .until((driver) -> addedToCartButton != null);
    }

    public String getHeaderCartCount() {
        scrollToElement(headerCartCount);
        return headerCartCount.getText();
    }
    public String getCartItemName(int index) {
        return cartItemsNames.get(index).getText();
    }
    public void placeAnOrder() {
        placeOrderButton.click();
    }

    public boolean isContinueOrderButtonEnabled() {
        return continuePacingOrderButton.isEnabled();
    }

    public void deleteFromCart(int index) {
        deleteFromCartIcons.get(index).click();
        deleteConfirmationButton.click();
        new FluentWait<>(driver)
                .pollingEvery(Duration.ofMillis(100L))
                .withTimeout(Duration.ofSeconds(5))
                .until((driver) -> deleteConfirmationPopups.isEmpty());
    }
}
