import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
//import static javax.swing.UIManager.get;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
//import static sun.net.NetProperties.get;

public class SchemaCheck {

    @Test
    //verify response type
    public void testContentType() {

        given().
                get("http://jsonplaceholder.typicode.com/photos/1").
                then().
                statusCode(200).
                contentType(ContentType.JSON);
    }


}
