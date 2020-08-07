import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TimeMeasurement {

    @Test
    public void testResponseTime() {
        long t = given().get("http://jsonplaceholder.typicode.com/photos").time();
        System.out.println("Time(ms): " +t);
    }

    @Test
    public void testResponseTimeUnit() {
        long t = given().get("http://jsonplaceholder.typicode.com/photos/1").timeIn(TimeUnit.MILLISECONDS);
        System.out.println("Time(ms): " +t);
    }

    @Test
    public void testResponseTimeAssertion() {
        ValidatableResponse t = given().get("http://jsonplaceholder.typicode.com/photos").then().time(lessThan(5000L));
        System.out.println("Time(ms): " +t);
    }
}


