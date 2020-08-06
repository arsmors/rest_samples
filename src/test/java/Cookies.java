import io.restassured.http.Cookie;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.when;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
//import static sun.net.NetProperties.get;
//import static sun.net.NetProperties.get;
import static org.hamcrest.Matchers.*;

public class Cookies {

    @Test
    //to get response headers
    public void testResponseHeaders() {
        Response response = get("http://jsonplaceholder.typicode.com/photos");

        //to get single header
        String headerCFRAY = response.getHeader("CF-RAY");
        System.out.println("Header: " + headerCFRAY);

        System.out.println("");

        //to get all headers
        Headers headers = response.getHeaders();
        for (Header h : headers) {
            System.out.println(h.getName() + ":" + h.getValue());
        }
    }

    @Test
    //to get cookies
    public void testCookies() {
        Response response = get("http://jsonplaceholder.typicode.com/photos");
        Map<String, String> cookies = response.getCookies();

        for (Map.Entry<String, String> entry : cookies.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    @Test
    //to get detailed cookies
    public void testDetailedCookies() {
        Response response = get("http://jsonplaceholder.typicode.com/photos");

        Cookie a = response.getDetailedCookie("__cfduid");
        System.out.println("Detailed: " + a.hasExpiryDate());
        System.out.println("Detailed: " + a.getExpiryDate());
        System.out.println("Detailed: " + a.hasValue());
    }
}
