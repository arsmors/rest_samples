import io.restassured.mapper.ObjectMapperType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Serialization {

    @Test
    //contentType() is converting HashMap object into Json
    public void testSerializationUsingHashMao() {
        Map<String, String> inputJson = new HashMap<>();
        inputJson.put("firstName", "A");
        inputJson.put("lastName", "B");
        inputJson.put("age", "30");

        given()
                .contentType("application/json")
                .body(inputJson)
                .when()
                .post("http://www.thomas-bayer.com/restnames/countries.groovy")
                .then()
                .statusCode(200);
    }

    @Test
    //for bigger JSON object it is easier to send java object rather creating a MAP
    public void testSerializationUsingContentType() {
        ZebraRequestClassNew obj = new ZebraRequestClassNew();
        obj.setAge(10);
        obj.setWeight(100);
        obj.setHome("India");

        given()
                .contentType("application/json")
                .body(obj)
                .when()
                .post("http://www.thomas-bayer.com/restnames/countries.groovy")
                .then()
                .statusCode(200)
                .contentType("application/xml").log().all();
    }

    @Test
    //Here converting java object into json using jackson2
    public void testSerializationUsingExplicitSerializer() {
        ZebraRequestClassNew obj = new ZebraRequestClassNew();
        obj.setAge(10);
        obj.setWeight(100);
        obj.setHome("India");

        given()
                .body(obj, ObjectMapperType.JACKSON_2)
                .when()
                .post("http://www.thomas-bayer.com/restnames/countries.groovy")
                .then()
                .statusCode(200)
                .contentType("application/xml").log().all();
    }

    @Test
    //Content-type base de-serialization process using .as()
    public void testDeserializationUsingContentType() {
        ZebraRequestClassNew reqObj = new ZebraRequestClassNew();
        reqObj.setAge(10);
        reqObj.setWeight(100);
        reqObj.setHome("India");

        ZebraResponseClass respObj =
                given()
                        .body(reqObj)
                        .when()
                        .post("http://www.thomas-bayer.com/restnames/countries.groovy")
                        .as(ZebraResponseClass.class);

        respObj.setRegId(1101);
        Assert.assertTrue(respObj.getRegId() >0);


    }
}
