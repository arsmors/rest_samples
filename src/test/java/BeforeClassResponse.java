import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.lessThan;

public class BeforeClassResponse {
    ResponseSpecification responseSpec;

    @org.testng.annotations.BeforeClass
    public void setup() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder();
        builder.expectStatusCode(200);
        builder.expectHeader("Content-Type", "application/json; charset=utf-8");
        builder.expectHeader("Cache-Control", "max-age=43200");
        responseSpec = builder.build();
    }

    @Test
    public void testResponse1() {
        when()
                .get("http://jsonplaceholder.typicode.com/photos/1")
                .then()
                .spec(responseSpec)
                .time(lessThan(4000L));
    }

    @Test
    public void testResponse2() {
        when()
                .get("http://jsonplaceholder.typicode.com/photos/1")
                .then()
                .spec(responseSpec);
    }
}
