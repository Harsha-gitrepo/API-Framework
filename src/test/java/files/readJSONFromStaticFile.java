package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class readJSONFromStaticFile {

    public static void main(String[] args) throws IOException {
        RestAssured.baseURI = "https://rahulshettyacademy.com";


        //ADD PLACE
        String response = given().log().all().queryParam("key","qaclick123").
                header("Content-Type", "application/json").
                body(new String(Files.readAllBytes(Paths.get("C:\\Users\\AIN143\\Desktop\\addPlace.txt")))).
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
