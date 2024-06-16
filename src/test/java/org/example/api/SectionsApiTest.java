package org.example.api;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SectionsApiTest {
    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://gate.21vek.by/index/";
    }

    @Test
    public void testSpecialOffersSale() {
        given().log().uri().header("Accept", "application/json")
                .param("type", "sale")
                .expect().statusCode(200)
                .when().get("/v1/special-offers")
                .then().log().status()
                .body("[0].discountTypes", Matchers.hasSize(1))
                .body("[0].discountTypes", Matchers.hasItem("sale"))
                .body("[0].categories", Matchers.hasSize(Matchers.greaterThan(0)));
    }
}
