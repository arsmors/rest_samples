import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.when;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
//import static sun.net.NetProperties.get;
//import static sun.net.NetProperties.get;
import static org.hamcrest.Matchers.*;

public class Groovy {

    @Test
    //verify some expected name present in response or not
    public void testPresenceOfElements() {
        given().
                get("http://jsonplaceholder.typicode.com/photos").
                then().
                body("title", hasItems("accusamus beatae ad facilis cum similique qui sunt",
                        "officia porro iure quia iusto qui ipsa ut modi")).log().all();
    }

    @Test
    //checking length of all code coming in response
    public void testLengthOfResponse() {
        when().
                get("https://reqres.in/api/users").
                then().
                body("data.id*.length().sum()", greaterThan(2));

    }

    @Test
    //to get all attribut as List
    public void testGetResponseAsList() {
        String response = get("https://reqres.in/api/users").asString();

        List<String> ls = from(response).getList("data.first_name");
        System.out.println("ListSize: " + ls.size());
        for (String name : ls) {
            if (name.equals("George"))
                System.out.println("Found my name");
        }
    }

    @Test
    //get all names that is greater that something
    public void testGreaterThan() {
        String response = get("https://reqres.in/api/users").asString();
        List<String> ls = from(response).getList("data.findAll {it.first_name.length()>3}.first_name");
        System.out.println(ls);
    }
}
