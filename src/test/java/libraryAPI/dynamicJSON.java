package libraryAPI;

import files.payload;
import files.reUsableMethods;
import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;

import java.util.Iterator;

import static io.restassured.RestAssured.*;

public class dynamicJSON {

    @Test(dataProvider = "BooksData")
    public void AddBook(String aisle, String isbn){
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().log().all().header("Content-Type", "application/json").
                body(payload.AddBook(aisle, isbn)).
        when().post("Library/Addbook.php ").
        then().log().all().assertThat().statusCode(200).
                extract().response().asString();
        JsonPath js = reUsableMethods.rawToJSON(response);
        String id = js.get("ID");
        System.out.println(id);
    }

    @DataProvider(name = "BooksData")
    public Object [][] getData()
    {
        //array --> Collection of elements
        //multi-dimensional array --> Collection of arrays
       return new Object [][] {{"sdasd", "1231"}, {"sdadvv", "3424"}, {"sdasd", "1231"}};
    }
}
