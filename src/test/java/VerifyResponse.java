import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class VerifyResponse {

    @Test
    //status code verification
    public void testStatusInResponse() {
        given().
                get("http://jsonplaceholder.typicode.com/photos").
                then().
                assertThat().
                statusCode(200);

        given().
                get("http://jsonplaceholder.typicode.com/photos").
                then().
                assertThat().
                statusLine("HTTP/1.1 200 OK");

        given().
                get("http://jsonplaceholder.typicode.com/photos").
                then().
                assertThat().
                statusLine(containsString("OK"));
    }

    @Test
    //headers verification
    public void testHeadersInResponse() {
        given().
                get("http://jsonplaceholder.typicode.com/photos").
                then().
                assertThat().
                header("X-Powered-By", "Express");

        given().
                get("http://jsonplaceholder.typicode.com/photos").
                then().
                assertThat().
                header("Vary", equalTo("Origin, Accept-Encoding")).
                and().
                header("Content-Encoding", containsString("gzip"));

    }

    @Test
    //content type verification
    public void testContentTypeInResponse() {
        given().
                get("http://jsonplaceholder.typicode.com/photos").
                then().
                assertThat().
                contentType(ContentType.JSON);
    }

    @Test
    //body text verification
    public void testBodyInResponse() {
        String responseString = get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/02/").asString();
        given().
                get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/02/").
                then().
                assertThat().
                body(equalTo(responseString));
    }

    @Test
    //verification using java 8 lambda
    public void testBodyParametersInResponse() {

        given().
                get("http://jsonplaceholder.typicode.com/photos/1").
                then().
                body("thumbnailUrl", response -> equalTo("https://via.placeholder.com/150/92c952"));

        given().
                get("http://jsonplaceholder.typicode.com/photos/1").
                then().
                body("thumbnailUrl", endsWith("92c952"));

    }
}
