import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
//import static javax.swing.UIManager.get;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
//import static sun.net.NetProperties.get;

public class Test1_Basic {

    @Test
    public void testStatusCode() {
        given().
                get("http://jsonplaceholder.typicode.com/posts/3").
                then().
                statusCode(200).
                log().all();
    }

    @Test
    public void testEqualToFunction() {
        given().
                get("http://jsonplaceholder.typicode.com/posts/3").
                then().
                body("title", equalTo("ea molestias quasi exercitationem repellat qui ipsa sit aut"));
    }

    @Test
    public void testMultipleItems() {
        given().
                get("http://jsonplaceholder.typicode.com/posts").
                then().
                body("title", hasItems("ea molestias quasi exercitationem repellat qui ipsa sit aut",
                        "qui est esse", "eum et est occaecati"));
    }

    @Test
    //To test xml response for single body content
    public void testSingleContent() {
        given().
                get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10/").
                then().
                body("CUSTOMER.ID", equalTo("10")).
                log().all();
    }

    @Test
    //To test xml response for multiple body content
    public void testMultipleContent() {
        given().
                get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10/").
                then().
                body("CUSTOMER.ID", equalTo("10")).
                body("CUSTOMER.FIRSTNAME", equalTo("Sue")).
                body("CUSTOMER.LASTNAME", equalTo("Fuller")).
                body("CUSTOMER.STREET", equalTo("135 Upland Pl.")).
                body("CUSTOMER.CITY", equalTo("Dallas")).
                log().all();
    }

    @Test
    //Compare complete text in one go
    public void testCompleteTextInOneGo() {
        given().
                get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10/").
                then().
                body("CUSTOMER.text()", equalTo("10SueFuller135 Upland Pl.Dallas")).
                log().all();
    }

    @Test
    //xpath can also be used to find values
    public void testUsingXpath() {
        given().
                get("http://www.thomas-bayer.com/sqlrest/CUSTOMER/10/").
                then().
                body(hasXPath("/CUSTOMER/FIRSTNAME"), containsString("Sue")).
                log().all();
    }

    @Test
    //xpath2
    public void testUsingXpath2() {
        given().
                get("http://www.thomas-bayer.com/sqlrest/INVOICE/").
                then().
                body(hasXPath("/INVOICEList/INVOICE[text()='40']")).
                log().all();
    }

    @Test
    //testing without root
    public void testWithoutRoot() {
        given().
                get("https://reqres.in/api/users/2").
                then().
                body("data.email", is("janet.weaver@reqres.in"));
    }

    @Test
    //testing with root
    public void testWithRoot() {
        given().
                get("https://reqres.in/api/users/2").
                then().
                root("data").
                body("email", is("janet.weaver@reqres.in"));
    }

    @Test
    //deattaching root
    public void testDetachRoot() {
        given().
                get("https://reqres.in/api/users/2").
                then().
                root("data").
                body("email", is("janet.weaver@reqres.in")).
                detachRoot("data").
                root("ad").
                body("company", is("StatusCode Weekly"));
    }

    @Test
    //get response as String
    public void testGetResponseAsString() {
        String resposeAsString = get("https://reqres.in/api/users").asString();
        System.out.println("My Response:\n\n\n"+resposeAsString);
    }

    @Test
    //extract detais using path
    public void testExtractDetailsUsingPath() {
        String href=
                when().
                        get("http://jsonplaceholder.typicode.com/photos/1").
                        then().
                        contentType(ContentType.JSON).
                        body("albumId", equalTo(1)).
                        extract().
                        path("url");

        System.out.println(href);

        when().get(href).then().statusCode(200);
    }

    @Test
    //Extract details using path in one line
    public void testExtractPathInOneLine() {
        //type1
        String href1 = get("http://jsonplaceholder.typicode.com/photos/1").path("thumbnailUrl");
        System.out.println("Fetched URL 1: " + href1);
        when().get(href1).then().statusCode(200);
        
        //type2
        String href2 = get("http://jsonplaceholder.typicode.com/photos/1").andReturn().jsonPath().getString("thumbnailUrl");
        System.out.println("Fetched URL 2: " + href2);
        when().get(href2).then().statusCode(200);
    }

    @Test
    //extract details as Response for further use
    public void testExtractdetailsUsingResponse() {
        Response response=
                when().
                        get("http://jsonplaceholder.typicode.com/photos/1").
                        then().
                        extract().
                        response();

        System.out.println("Content Type: " +response.contentType());
        System.out.println("Href: " +response.path("url"));
        System.out.println("Status Code: " +response.statusCode());
    }

}
