package by.itacademy.nnaralenkova.project.ui;

import by.itacademy.nnaralenkova.project.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {
    private MainPage mainPage;

    @BeforeMethod
    public void setup() {
        mainPage = new MainPage();
        mainPage.openMainPage();
        mainPage.acceptCookies();
    }
    @Test
    public void addToCart() {
        mainPage.selectPromotedCategory(3);
        mainPage.closePromoPopup();
        mainPage.addToCart(0);


        Assert.assertEquals(mainPage.getHeaderCartCount(), "1");
    }

    @Test
    public void checkProductInCart() {
        mainPage.selectPromotedCategory(4);
        mainPage.closePromoPopup();
        mainPage.addToCart(0);
        String productName = mainPage.getProductName(0);
        mainPage.goToCart();

        Assert.assertEquals(mainPage.getCartItemName(0), productName);
    }

    @AfterMethod
    public void quit() {
        mainPage.quit();
    }
}
