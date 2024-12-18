package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before (value = "@DeletePlace")
    public void beforeScenario() throws IOException {
        //Write code that will fetch place id
        //Execute this code only when place id is null
        StepDefinition sd = new StepDefinition();
        if (sd.place_id == null) {
            sd.add_place_payload("Harsha", "India", "Asia");
            sd.user_calls_with_post_http_request("AddPlaceAPI", "POST");
            sd.verify_place_id_created_maps_to_using("Harsha", "GetPlaceAPI");
        }
    }
}
