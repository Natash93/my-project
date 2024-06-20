package by.itacademy.nnaralenkova.project.ui;

import by.itacademy.nnaralenkova.project.pages.LoginPage;
import by.itacademy.nnaralenkova.project.pages.MainPage;
import org.testng.Assert;
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
    public void invalidEmailLoginTest() {
        loginPage.openEmailLoginForm();
        loginPage.loginWithCredentials("123@qq.hh", "qwertyu");

        Assert.assertEquals(loginPage.getWrongCredsErrorMessage(), "Проверьте электронную почту или \nзарегистрируйтесь");
    }

    @Test
    public void emptyPasswordLoginTest() {
        loginPage.openEmailLoginForm();
        loginPage.loginWithCredentials("123@qq.hh", "");

        Assert.assertEquals(loginPage.getEmptyPasswordErrorMessage(), "Пароль не указан");
    }

    @Test
    public void wrongPasswordLoginTest() {
        loginPage.openEmailLoginForm();
        loginPage.loginWithCredentials("123@qq.hh", "!");

        Assert.assertEquals(loginPage.getEmptyPasswordErrorMessage(), "Неправильный пароль. \nСбросить пароль?");
    }

    @AfterMethod
    public void quit() {
        mainPage.quit();
    }
}
