import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HandlingSSL_TLS {

    @BeforeClass
    public void setup() {
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    public void testSSL1() {
        given()
                .get("https://www.bupa.com.au/")
                .then()
                .statusCode(200);


        given()
                .relaxedHTTPSValidation()
                .when()
                .get("https://www.bupa.com.au/")
                .then()
                .statusCode(200);
    }

    @Test
    public void testTLS() {
        given()
                .relaxedHTTPSValidation("TLS")
                .when()
                .get("https://www.bupa.com.au/")
                .then()
                .statusCode(200);

    }
}
