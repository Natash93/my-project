package by.itacademy.nnaralenkova.project.ui;

import by.itacademy.nnaralenkova.project.pages.CartPage;
import by.itacademy.nnaralenkova.project.pages.CategoriesPage;
import by.itacademy.nnaralenkova.project.pages.MainPage;
import org.testng.Assert;
import org.testng.ITestResult;
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
    public void testAddToCartAndClickPlaceOrder() {
        mainPage.selectPromotedCategory(3);
        categoriesPage.closePromoPopup();
        categoriesPage.addToCart(2);

        Assert.assertEquals(mainPage.getHeaderCartCount(), "1");

        mainPage.goToCart();
        cartPage.placeAnOrder();

        Assert.assertTrue(cartPage.isContinueOrderButtonEnabled());
    }

    @Test
    public void checkProductAddedToCart() {
        mainPage.selectPromotedCategory(4);
        categoriesPage.closePromoPopup();
        categoriesPage.addToCart(1);
        String productName = categoriesPage.getProductName(1);
        mainPage.goToCart();

        Assert.assertEquals(cartPage.getCartItemName(0), productName);
    }

    @Test
    public void checkAddFromCatalogueAndDeleteFromCart() {
        mainPage.openCatalogue();
        categoriesPage.pickCategoryInCatalogue(14);
        categoriesPage.closePromoPopup();
        categoriesPage.chooseSubcategory();
        categoriesPage.addToCart(5, 7, 13, 26);

        Assert.assertEquals(mainPage.getHeaderCartCount(), "4");

        mainPage.goToCart();
        cartPage.deleteFromCart(0);
        cartPage.deleteFromCart(0);

        Assert.assertEquals(mainPage.getHeaderCartCount(), "2");
    }

    @AfterMethod
    @Override
    public void afterMethod(ITestResult result) {
        super.afterMethod(result);
        mainPage.quit();
    }
}
