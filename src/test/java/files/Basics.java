package files;

import files.payload;
import files.reUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.util.Locale;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basics {

    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";


        //ADD PLACE
        String response = given().log().all().queryParam("key","qaclick123").
                header("Content-Type", "application/json").
                body(payload.AddPlace()).
        when().post("/maps/api/place/add/json").
        then().log().all().assertThat().statusCode(200).
                body("scope", equalTo("APP")).extract().response().asString();
        System.out.println(response);

        // Retrieving the 'place_id' from the JSON body
        JsonPath js = new JsonPath(response);
        String placeID = js.getString("place_id");
        System.out.println(placeID);

        //UPDATE PLACE
        given().log().all().queryParam("key", "qaclick123").
                header("Content-Type", "application/json").
                body(payload.UpdatePlace(placeID)).
        when().put("maps/api/place/update/json").
        then().assertThat().log().all().statusCode(200).
                body("msg", equalTo("Address successfully updated"));

        //GET Place
        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").
                queryParam("place_id", placeID).
        when().get("maps/api/place/get/json").
        then().assertThat().log().all().statusCode(200).extract().response().asString();

        JsonPath js1 = reUsableMethods.rawToJSON(getPlaceResponse);
        String actualAddress = js1.getString("address");
        System.out.println(actualAddress);

        Assert.assertEquals(actualAddress, "70 Summer walk, USA");
    }
}
