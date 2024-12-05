package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BugTest {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://harshaitcareer9.atlassian.net/";

        String apikey = "Basic aGFyc2hhLml0Y2FyZWVyOUBnbWFpbC5jb206QVRBVFQzeEZmR0YwOW00b3luZW5FamI5ejJyS3czN29wMGhkX1BZMEFndWtsQ3RXMjJ0VHJEYl9DaGFfQWFGR2VRVVZLN2RUZ3Z0MEd0RTVBYTN1M2VaOTRXM3ZHbmlVeVRyMlJGS1F5Njh4V1BlWUtiMGxta2tUd3VZcDdTNFRZRFZVSUI1d1pWRjlUMENnT1V5ZFR1aEk5Vk13Q3o5SUJJc09aT1N4RGItS3dkQkxPM3hTdm44PUJEREFBN0RD";
        String createIssueResponse = given().log().all().header("Content-Type","application/json").
                header("Authorization", apikey).
                body(payload.createBug("Issue-9")).
        when().post("rest/api/3/issue").
        then().log().all().assertThat().statusCode(201).extract().response().asString();

        JsonPath js = reUsableMethods.rawToJSON(createIssueResponse);
        String bugID = js.getString("id");
        System.out.println(bugID);

        given().log().all().
                header("X-Atlassian-Token", "no-check").
                header("Authorization", apikey).
                multiPart("file", new File("C:\\Users\\AIN143\\Desktop\\Image.jpg")).
        when().post("rest/api/3/issue/"+bugID+"/attachments").
        then().log().all().statusCode(200);
    }
}
