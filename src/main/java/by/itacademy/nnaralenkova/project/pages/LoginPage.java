package by.itacademy.nnaralenkova.project.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class LoginPage extends BasePage {
    @FindBy(css = ".userToolsText")
    private WebElement loginLink;

    @FindBy(css = "button[data-testid='loginButton']")
    private WebElement signInButton;

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


    public void openEmailLoginForm() {
        loginLink.click();
        signInButton.click();
    }

    public void inputCredentials(String email, String password) {
        emailInputField.sendKeys(email);
        passwordInputField.sendKeys(password);
    }

    public void loginWithCredentials(String email, String password) {
        inputCredentials(email, password);
        loginButton.click();
    }

    public String getWrongCredsErrorMessage() {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .until((t) -> wrongEmailOrPasswordErrorMessage.isDisplayed());
        return wrongEmailOrPasswordErrorMessage.getText();
    }

    public String getEmptyPasswordErrorMessage() {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .until((t) -> emptyPasswordErrorMessage.isDisplayed());
        return emptyPasswordErrorMessage.getText();
    }


}
