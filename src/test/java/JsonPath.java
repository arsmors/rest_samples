import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

import static io.restassured.path.json.JsonPath.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
//import static sun.net.NetProperties.get;
//import static sun.net.NetProperties.get;
import static org.hamcrest.Matchers.*;

public class JsonPath {
    @Test
    //extract details as String and fetching further details using json path
    public void testJsonPath1() {
        String responseAsString =
                when().get("http://jsonplaceholder.typicode.com/photos").
                        then().
                        extract().asString();

        List<Integer> albumIds = from(responseAsString).get("id");
        System.out.println(albumIds.size());

    }

//    @Test
//    //extract details as String and fetching further details using JsonPath
//    public void testJsonPath2() {
//        String json=
//                when().
//                        get("https://reqres.in/api/users").
//                        then().
//                        extract().asString();
//
//        JsonPath jsonPath = new JsonPath(json).setRoot("data");
//        List<String>list = jsonPath.get("first_name");
//        System.out.println(list.size());
//    }


}
