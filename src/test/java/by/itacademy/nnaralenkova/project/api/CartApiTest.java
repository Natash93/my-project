package by.itacademy.nnaralenkova.project.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CartApiTest {
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

}
