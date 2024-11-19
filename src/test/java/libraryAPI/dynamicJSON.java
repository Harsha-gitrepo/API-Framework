package libraryAPI;

import files.payload;
import files.reUsableMethods;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class dynamicJSON {

    @Test
    public void AddBook(){
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().log().all().header("Content-Type", "application/json").
                body(payload.AddBook()).
        when().post("Library/Addbook.php ").
        then().log().all().assertThat().statusCode(200).
                extract().response().asString();
        JsonPath js = reUsableMethods.rawToJSON(response);
        String id = js.get("ID");
        System.out.println(id);
    }
}
