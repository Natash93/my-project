package by.itacademy.nnaralenkova.project.ui;

import by.itacademy.nnaralenkova.project.pages.CategoriesPage;
import by.itacademy.nnaralenkova.project.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTest extends BaseTest {
    private MainPage mainPage;

    private CategoriesPage categoriesPage;

    @BeforeMethod
    public void setup() {
        mainPage = new MainPage();
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

    @AfterMethod
    public void quit() {
        mainPage.quit();
    }
}
