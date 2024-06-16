package org.example.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CartTest {
    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://gate.21vek.by/cart/";
    }

    @Test
    public void testAddToCart() {
        given().log().uri().header("Accept", "application/json")
                .contentType(ContentType.JSON)
                .body("{\"id\": 8932393, \"type\": \"product\"}")
                .expect().statusCode(204)
                .when().post("/carts/items")
                .then().log().status();
    }

    @Test
    public void testGetCartInfoWithoutAuth() {
        given().log().uri().header("Accept", "application/json")
                .expect().statusCode(415)
                .when().get("/carts/info")
                .then().log().status();
    }

}
