package GoogleMapsSerializationWithPOJO;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class serializeTest {

    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String response = given().log().all().queryParam("key", "qaclick123").body().
        when().post("/maps/api/place/add/json").
        then().assertThat().statusCode(200).extract().response();

        System.out.println(response);


    }
}
