import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Logging {

    @Test
    public void testLogging1() {
        given()
                .get("http://jsonplaceholder.typicode.com/photos")
                .then()
                .log().all();
//                .log().body();
//                .log().cookies();
//                .log().headers();
    }

    @Test
    public void testLogging2() {
        given()
                .get("http://jsonplaceholder.typicode.com/photos")
                .then()
                .log().ifError();
    }

    @Test
    public void testLogging3() {
        given()
                .get("http://jsonplaceholder.typicode.com/photos")
                .then()
                .log().ifStatusCodeIsEqualTo(200);
    }

}
