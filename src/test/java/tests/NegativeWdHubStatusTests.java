package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import utils.AuthUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class NegativeWdHubStatusTests extends TestBase {


    @Test
    public void invalidAuthTest() {
        given()
                .log().all()
                .auth().basic(AuthUtils.getInvalidLogin(), AuthUtils.getValidPassword())
                .when()
                .get("/status")
                .then()
                .log().all()
                .statusCode(401);
    }

    @Test
    public void emptyAuthTest() {
        given()
                .log().all()
                .auth().basic(AuthUtils.getEmptyLogin(), AuthUtils.getEmptyPassword())
                .when()
                .get("/status")
                .then()
                .log().all()
                .statusCode(401);
    }

    @Test
    public void checkContentTypeTest() {
        given()
                .log().all()
                .auth().basic(AuthUtils.getInvalidLogin(), AuthUtils.getValidPassword())
                .when()
                .get("/status")
                .then()
                .log().all()
                .statusCode(401)
                .contentType(ContentType.HTML);
    }


    @Test
    public void checkBodyTest() {
        given()
                .log().all()
                .auth().basic(AuthUtils.getInvalidLogin(), AuthUtils.getValidPassword())
                .when()
                .get("/status")
                .then()
                .log().all()
                .statusCode(401)
                .body(not(emptyString()))
                .body(containsString("401 Authorization Required"));
    }








}
