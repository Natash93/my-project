package by.itacademy.nnaralenkova.project.ui;

import by.itacademy.nnaralenkova.project.pages.CartPage;
import by.itacademy.nnaralenkova.project.pages.CategoriesPage;
import by.itacademy.nnaralenkova.project.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {
    private MainPage mainPage;
    private CategoriesPage categoriesPage;
    private CartPage cartPage;

    @BeforeMethod
    public void setup() {
        mainPage = new MainPage();
        mainPage.openMainPage();
        mainPage.acceptCookies();
        categoriesPage = new CategoriesPage();
        cartPage = new CartPage();
    }

    @Test
    public void addToCart() {
        mainPage.selectPromotedCategory(3);
        mainPage.closePromoPopup();
        cartPage.addToCart(0);

        Assert.assertEquals(cartPage.getHeaderCartCount(), "1");
        mainPage.goToCart();
        cartPage.placeAnOrder();

        Assert.assertTrue(cartPage.isContinueOrderButtonEnabled());
    }

    @Test
    public void checkProductInCart() {
        mainPage.selectPromotedCategory(4);
        mainPage.closePromoPopup();
        cartPage.addToCart(0);
        String productName = mainPage.getProductName(0);
        mainPage.goToCart();

        Assert.assertEquals(cartPage.getCartItemName(0), productName);
    }

    @Test
    public void deleteProductFromCart() {
        categoriesPage.openCatalogue();
        categoriesPage.pickCategoryInCatalogue(14);
        mainPage.closePromoPopup();
        categoriesPage.chooseSubcategory();

        cartPage.addToCart(5, 7, 13, 24);
        Assert.assertEquals(cartPage.getHeaderCartCount(), "4");
        mainPage.goToCart();
        cartPage.deleteFromCart(0);
        cartPage.deleteFromCart(0);
        Assert.assertEquals(cartPage.getHeaderCartCount(), "2");
    }

    @AfterMethod
    public void quit() {
        mainPage.quit();
    }
}
