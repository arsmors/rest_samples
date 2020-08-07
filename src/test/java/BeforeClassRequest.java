import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.lessThan;

public class BeforeClassRequest {
    RequestSpecification requestSpec;

    @org.testng.annotations.BeforeClass
    public void setup() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addParam("parameter1", "parameterValaue");
        builder.addHeader("header1", "headerValue");
        requestSpec = builder.build();
    }

    @Test
    public void testRRequest1() {
        given()
                .spec(requestSpec)
                .when()
                .get("https://api.fonts.com/rest/json/Accounts")
                .then()
                .statusCode(400)
                .log().all();
    }
}
