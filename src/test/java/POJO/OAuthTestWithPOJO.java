package POJO;

import io.restassured.path.json.JsonPath;

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

        System.out.println(gc.getInstructor());
        System.out.println(gc.getLinkedin());

    }
}
