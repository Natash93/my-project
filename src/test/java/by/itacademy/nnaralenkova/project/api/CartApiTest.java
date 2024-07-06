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
                        "accessToken", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxNDQ4NTQwNyIsImFubyI6MCwiaWF0IjoxNzIwMjcyMTc1Ljg3MTIzNCwiZXhwIjoxNzIwMjcyNzc1Ljg3MTIzNCwidmVyIjowLjF9.CY6LCuEn-R_NKEvg7v9FMz2hIPKt8Qj5VrIsgyRGpaM"
                ).cookie(
                        "refreshToken", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI3Y2Y2OWU1NS1mYjg0LTRhOGMtOGUxZi0wYTEyMzk5YTMxMjEiLCJzdWIiOiIxNDQ4NTQwNyIsImFubyI6MCwiaWF0IjoxNzIwMjcxNTYzLjMwMjA0MSwiZXhwIjoxNzUxODA3NTYzLjMwMjA0MSwidmVyIjowLjEsInByZW1pdW0iOjB9.WFA_yDLjAX5U_SdSKP0j2mGnLI-QQUskd-VyqHIdkeg"
                )
                .contentType(ContentType.JSON)
                .body("{\"id\": 8932393, \"type\": \"product\"}")
                .expect().statusCode(204)
                .when().post("/carts/items")
                .then().log().status();
    }

}
