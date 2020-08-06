import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class SetRequestData {

    @Test
    //CONNECT used with HTTPS request
    public void testConnectRequest() {
        when().
                request("CONNECT", "https://api.fonts.com/rest/json/Accounts").
                then().
                statusCode(400);
    }

    @Test
    //GET request we can set query paramenter
    public void testQueryParameters() {
        given().
                queryParam("A", "A val").
                queryParam("B", "B val").
                when().
                get("https://api.fonts.com/rest/json/Accounts").
                then().
                statusCode(400);
    }

    @Test
    //POST request we can set query parameters
    public void testFormParmateres() {
        given().
                formParam("A", "A val").
                formParam("B", "B val").
                when().
                post("https://api.fonts.com/rest/json/Domains").
                then().
                statusCode(400);
    }

    @Test
    //to set query parameters
    public void testSetParmateres() {
        given().
                param("A", "A-value").
                param("B", "B-value").
                when().
                get("https://api.fonts.com/rest/json/Accounts").
                then().
                statusCode(400);
    }


    @Test
    //to set multiple value parameters
    public void testMultiValueParameters() {
        List<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");

        given().
                param("A", "val1", "val2", "val3").
                param("B").
                param("C", list).
                when().
                get("https://api.fonts.com/rest/json/Accounts").
                then().
                statusCode(400);
    }


    @Test
    //path parameters
    public void testSetPathParameters() {
        given().
                pathParam("type", "json").
                pathParam("section", "Domains").
                when().
                post("http://api.fonts.com/rest/{type}/{section}/").
                then().
                statusCode(400);
    }


    @Test
    //cookies can be set in request param
    public void testSetCookiesInRequest() {
        given().
                cookie("__utmt", "1").
                when().
                get("https://api.fonts.com/rest/json/Accounts").
                then().
                statusCode(400);
    }

    @Test
    //headers
    public void testSetHeaders() {
        given().
                headers("k", "v").
                headers("k10", "val1", "val2", "val3").
                when().
                get("https://api.fonts.com/rest/json/Accounts").
                then().
                statusCode(400);
    }

    @Test
    //content type
    public void testSetContentType() {
        given().
                contentType(ContentType.JSON).
                contentType("application/json; charset=utf-8").
                when().
                get("https://api.fonts.com/rest/json/Accounts").
                then().
                statusCode(400);
    }

}
