package by.itacademy.nnaralenkova.project.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage {

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

    @FindBy(className = "styles_modalConfirmationWrapper__LM_v6")
    private List<WebElement> deleteConfirmationPopups;

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

        waitUntil((driver) -> deleteConfirmationPopups.isEmpty());
    }
}
