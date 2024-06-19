package org.example.ui;

import by.itacademy.nnaralenkova.project.pages.CategoriesPage;
import by.itacademy.nnaralenkova.project.pages.LoginPage;
import by.itacademy.nnaralenkova.project.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.*;

public class Tests extends BaseTest {
    private MainPage mainPage;
    private LoginPage loginPage;

    private CategoriesPage categoriesPage;

    @BeforeMethod
    public void setup() {
        mainPage = new MainPage();
        loginPage = new LoginPage();
        categoriesPage = new CategoriesPage();
        mainPage.openMainPage();
        mainPage.acceptCookies();
    }

    @Test
    public void productSearch() {
        String input = "Глюкофоны";
        mainPage.inputSearchInputFieldWithJs(input);
        categoriesPage.chooseFirstSuggestedItem(input);

        Assert.assertEquals(categoriesPage.getCategoryName(), input);

        String minPrice = "200";
        String maxPrice = "300";

        categoriesPage.setMinPrice(minPrice);
        categoriesPage.waitForSnackbar();
        categoriesPage.waitForSnackbarDismissed();
        categoriesPage.setMaxPrice(maxPrice);
        categoriesPage.waitForSnackbar();

        List<Integer> prices = categoriesPage.getProductsPrices();

        Assert.assertListNotContains(
                prices,
                (price) -> price < 200 || price > 300,
                "Prices do not match: " + prices
        );

    }

    @Test
    public void addToCart() {
        mainPage.selectPromotedCategory(3);
        mainPage.addToCart(0);

        Assert.assertEquals(mainPage.getHeaderCartCount(), "1");
    }

    @Test
    public void checkProductInCart() {
        mainPage.selectPromotedCategory(4);
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
