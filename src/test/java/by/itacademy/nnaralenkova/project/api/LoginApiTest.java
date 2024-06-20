package by.itacademy.nnaralenkova.project.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginApiTest {
    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://www.21vek.by/users";
    }
    @Test
    public void testLoginInvalidCreds() {
        given().log().uri().header("Accept", "application/json")
                .contentType(ContentType.JSON)
                .body("{\"User\": {\"email\": \"qwe@gmail.com\",\"password\": \"12345\"}}")
                .expect().statusCode(200)
                .when().post("/action/login/")
                .then().log().status()
                .body("error", Matchers.notNullValue());
    }
}
