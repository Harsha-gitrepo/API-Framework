package stepDefinitions;

import GoogleMapsSerializationWithPOJO.addPlacePOJO;
import GoogleMapsSerializationWithPOJO.location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StepDefinition {

    @Given("Add Place Payload")
    public void add_place_payload() {
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
    }
    @When("user calls {string} with Post http request")
    public void user_calls_with_post_http_request(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
