package DeserializeCoursesWithPOJO;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class OAuthTestWithPOJO {

    public static void main(String[] args) {

        String response = given().formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
                formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").
                formParam("grant_type", "client_credentials").
                formParam("scope", "trust").
                when().log().all().
                post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
        System.out.println(response);

        JsonPath js = new JsonPath(response);
        String access_token = js.getString("access_token");

        getCourse gc = given().log().all().queryParam("access_token", access_token).
                when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(getCourse.class);

        //Start of DESERIALIZATION
        System.out.println(gc.getInstructor());
        System.out.println(gc.getLinkedin());
        System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());

        //Api
        List<Api> apiCourses = gc.getCourses().getApi();
        for (int i=0; i<apiCourses.size(); i++)
        {
            if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices Testing"))
            {
                System.out.println(apiCourses.get(i).getPrice());
            }
        }

        //Web Automation
        ArrayList<String> a = new ArrayList<String>(); //ArrayList is used here, so that the size can be
        //increased dynamically
        List<WebAutomation> w = gc.getCourses().getWebAutomation();
        for (int j=0; j<w.size(); j++)
        {
            a.add(w.get(j).getCourseTitle());
        }

        String [] webCourseTitles = {"Selenium Webdriver Java", "Cypress", "Protractor"}; //Creating this to compare
        //with the output we get
        List<String> expectedList = Arrays.asList(webCourseTitles); //Converting array to arraylist, so that
        //the arraylist 'a' and 'expectedList' can be compared easily

        Assert.assertTrue(a.equals(expectedList));//If true, the code will end without any error,
        //If the expected output doesn't match with the actual output, the code will throw an error
        //To get an error, change the values in the array 'webCourseTitles'

    }
}
