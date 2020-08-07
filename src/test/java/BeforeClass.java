import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BeforeClass {

    @org.testng.annotations.BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
        RestAssured.basePath = "/photos";
    }

    @Test
    public void test1() {
        given()
                .get("/1")
                .then()
                .statusCode(200)
                .log().all();
    }
}
