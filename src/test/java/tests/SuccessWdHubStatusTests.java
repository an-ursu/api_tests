package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import utils.AuthUtils;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class SuccessWdHubStatusTests extends TestBase {

    @Test
    public void checkStatusCodeTest() {
        given()
                .log().all()
                .auth().basic(AuthUtils.getValidLogin(), AuthUtils.getValidPassword())
                .when()
                .get("/status")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void checkContentTypeTest() {
        given()
                .log().all()
                .auth().basic(AuthUtils.getValidLogin(), AuthUtils.getValidPassword())
                .when()
                .get("/status")
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void checkSchemaTest() {
        given()
                .log().all()
                .auth().basic(AuthUtils.getValidLogin(), AuthUtils.getValidPassword())
                .when()
                .get("/status")
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/wd_hub_status_response_schema.json"));

    }

    @Test
    public void checkMessageTest() {
        given()
                .log().all()
                .auth().basic(AuthUtils.getValidLogin(), AuthUtils.getValidPassword())
                .when()
                .get("/status")
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/wd_hub_status_response_schema.json"))
                .body("value", hasKey("message"))
                .body("value.message", containsString("Selenoid 1.11.3 built at 2024-05-25_12:34:40PM"));

    }

    @Test
    public void checkReadinessTest() {
        given()
                .log().all()
                .auth().basic(AuthUtils.getValidLogin(), AuthUtils.getValidPassword())
                .when()
                .get("/status")
                .then()
                .log().all()
                .statusCode(200)
                .body("value", hasKey("ready"))
                .body("value.ready", is(true));

    }


}
