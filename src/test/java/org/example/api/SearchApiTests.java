package org.example.api;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SearchApiTests {
    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://search.21vek.by/api/v2.0/";
    }
    @Test
    public void testSearch() {
        given().log().uri().param("q", "asus")
                .expect().statusCode(200)
                .when().get("/search/suggest")
                .then().log().status()
                .body("total", Matchers.greaterThan(0));
    }

}
