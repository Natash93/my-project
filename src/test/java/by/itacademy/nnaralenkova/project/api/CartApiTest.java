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
        given().log().uri().header("Accept", "application/json").cookie(
                        "accessToken", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ2ZXIiOjAuMSwiaWF0IjoxNzIxMTMzMjc1LCJzdWIiOiIzNmM1ZDBkZS03YzA3LTQ3NTItYjNmNi04MTRlZTlmYzJlMTgiLCJleHAiOjE3NTI2ODAwNzUsImFubyI6MX0.X8t1Tx1QeBEDlCnSDjtoacnVNmaTCVjph9NuJehhtZQ"
                ).cookie(
                        "refreshToken", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ2ZXIiOjAuMSwiaWF0IjoxNzIxMTMzMjc1LCJqdGkiOiIzNmM1ZDBkZS03YzA3LTQ3NTItYjNmNi04MTRlZTlmYzJlMTgiLCJleHAiOjE3NTI2ODAwNzUsImFubyI6MX0.5WhZpJP1lcJOiG7nIfLnT5MbU7WydxJSEjTRZkAEWk8"
                )
                .contentType(ContentType.JSON)
                .body("{\"id\": 8932393, \"type\": \"product\"}")
                .expect().statusCode(204)
                .when().post("/carts/items")
                .then().log().status();
    }

}
