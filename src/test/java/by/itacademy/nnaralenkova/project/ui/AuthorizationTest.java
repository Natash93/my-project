package by.itacademy.nnaralenkova.project.ui;

import by.itacademy.nnaralenkova.project.pages.LoginPage;
import by.itacademy.nnaralenkova.project.pages.MainPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AuthorizationTest extends BaseTest {
    private MainPage mainPage;
    private LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        mainPage = new MainPage();
        loginPage = new LoginPage();
        mainPage.openMainPage();
        mainPage.acceptCookies();
    }

    @Test
    public void testUnregisteredEmailLogin() {
        mainPage.openEmailLoginForm();
        loginPage.loginWithCredentials("123@qq.hh", "qwertyu");

        Assert.assertEquals(loginPage.getWrongCredsErrorMessage(), "Проверьте электронную почту или \nзарегистрируйтесь");
    }

    @Test
    public void testEmptyPasswordLogin() {
        mainPage.openEmailLoginForm();
        loginPage.loginWithCredentials("123@qq.hh", "");

        Assert.assertEquals(loginPage.getEmptyPasswordErrorMessage(), "Пароль не указан");
    }

    @Test
    public void testWrongPasswordLogin() {
        mainPage.openEmailLoginForm();
        loginPage.loginWithCredentials("123@qq.hh", "!");

        Assert.assertEquals(loginPage.getEmptyPasswordErrorMessage(), "Неправильный пароль. \nСбросить пароль?");
    }

    @Test
    public void testExistingCredsLogin() {
        String email = "n79372122@gmail.com";
        String password = "94f64ef4";
        mainPage.openEmailLoginForm();
        loginPage.loginWithCredentials(email, password, true);
        mainPage.openLoginForm();

        Assert.assertEquals(loginPage.getUserDropDownTitle(), "Аккаунт");
        Assert.assertEquals(loginPage.getUserDropDownSubtitle(), email);
    }

    @AfterMethod
    @Override
    public void afterMethod(ITestResult result) {
        super.afterMethod(result);
        mainPage.quit();
    }
}
