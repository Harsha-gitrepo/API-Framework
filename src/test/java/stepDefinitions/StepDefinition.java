package stepDefinitions;

//Using the POJO classes from the APIBAsics/GoogleMapsSerializationWithPOJO/addPlacePOJO class
//Instead of recreating all the POJO classes, we are just reusing them
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class StepDefinition extends utils{

    //Declaring below variables globally, so that they can be accessed in all the given, when and then methods
    RequestSpecification res;
    ResponseSpecification respspec;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String place_id;

    @Given("Add Place Payload {string} {string} {string}")
    public void add_place_payload(String name, String language, String address) throws IOException {

        res = given().spec(requestSpec()).body(data.addPlacePayLoad(name, language, address));
    }


    @When("user calls {string} with {string} http request")
    public void user_calls_with_post_http_request(String resource, String httpMethod) {

        APIResources resourceAPI = APIResources.valueOf(resource);
        System.out.println(resourceAPI.getResource());

        respspec = new ResponseSpecBuilder().expectStatusCode(200).
                expectContentType(ContentType.JSON).build();

        if (httpMethod.equalsIgnoreCase("Post"))
        {
            response = res.when().post(resourceAPI.getResource());
        }
        else if (httpMethod.equalsIgnoreCase("Get"))
        {
            response = res.when().get(resourceAPI.getResource());
        }
    }


    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1)
    {
        assertEquals(response.getStatusCode(), 200);
    }


    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue)
    {
        assertEquals(getJSONPath(response, keyValue).toString(), expectedValue);
    }


    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException
    {
        //get API call
        place_id =  getJSONPath(response, "place_id");
        res = given().spec(requestSpec()).queryParam("place_id", place_id);
        user_calls_with_post_http_request(resource, "GET");
        String actualName =  getJSONPath(response, "name");
        assertEquals(actualName, expectedName);
    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {

        res = given().spec(requestSpec()).body(data.deletePlacePayLoad(place_id));
    }
}
