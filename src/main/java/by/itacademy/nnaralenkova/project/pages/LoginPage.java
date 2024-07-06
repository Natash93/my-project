package by.itacademy.nnaralenkova.project.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public class LoginPage extends BasePage {

    @FindBy(id = "login-email")
    private WebElement emailInputField;

    @FindBy(id = "login-password")
    private WebElement passwordInputField;

    @FindBy(css = "button[data-testid='loginSubmit']")
    private WebElement loginButton;

    @FindBy(css = ".styles_errorText__LEN7M")
    private WebElement wrongEmailOrPasswordErrorMessage;

    @FindBy(css = ".ErrorMessage-module__message")
    private WebElement emptyPasswordErrorMessage;

    @FindBy(xpath = "//div[@class = \"userToolsTitle\"]")
    private WebElement userDropDownTitle;

    @FindBy(xpath = "//span[@class = \"userToolsSubtitle\"]")
    private WebElement userDropDownSubtitle;

    @FindBy(xpath = "//div[@data-testid=\"modal\"]")
    private List<WebElement> signInDialogue;

    public void inputCredentials(String email, String password) {
        emailInputField.sendKeys(email);
        passwordInputField.sendKeys(password);
    }

    public void loginWithCredentials(String email, String password) {
        inputCredentials(email, password);
        loginButton.click();
    }

    public void loginWithCredentials(String email, String password, boolean waitToDialogueClosed) {
        loginWithCredentials(email, password);
        if (waitToDialogueClosed) {
            waitUntil((d) -> signInDialogue.isEmpty());
        }
    }

    public String getWrongCredsErrorMessage() {
        waitUntil((t) -> wrongEmailOrPasswordErrorMessage.isDisplayed());
        return wrongEmailOrPasswordErrorMessage.getText();
    }

    public String getEmptyPasswordErrorMessage() {
        waitUntil((t) -> emptyPasswordErrorMessage.isDisplayed());
        return emptyPasswordErrorMessage.getText();
    }

    public String getUserDropDownTitle() {
        return userDropDownTitle.getText();
    }

    public String getUserDropDownSubtitle() {
        return userDropDownSubtitle.getText();
    }

}
