package GoogleMapsSerializationWithPOJO;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class specBuilderTest {

    public static void main(String[] args) {

        {

            RestAssured.baseURI = "https://rahulshettyacademy.com";

            addPlacePOJO p = new addPlacePOJO(); //Creating addPlacePOJO class object

            //Start of SERIALIZATION
            //Setting all the direct JSON body values
            p.setAccuracy(50);
            p.setAddress("29, side layout, cohen 09");
            p.setName("Frontline house");
            p.setPhone_number("(+91) 983 893 3937");
            p.setWebsite("https://rahulshettyacademy.com ");
            p.setLanguage("French-IN");

            //Setting Types values
            List<String> typesList = new ArrayList<String>();
            typesList.add("shoe park");
            typesList.add("shop");
            p.setTypes(typesList);

            //Setting location values
            location l = new location(); //Creating location class object and passing this object to setLocation method
            l.setLat(-38.383494);
            l.setLng(33.427362);
            p.setLocation(l);

            RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
                    addQueryParam("key", "qaclick123").
                    setContentType(ContentType.JSON).build();

            ResponseSpecification respspec = new ResponseSpecBuilder().expectStatusCode(200).
                    expectContentType(ContentType.JSON).build();

            RequestSpecification res = given().spec(req).body(p);

            Response response = res.when().post("/maps/api/place/add/json").
                    then().spec(respspec).extract().response();
            String responseString = response.asString();
            System.out.println(responseString);
        }
    }
}
