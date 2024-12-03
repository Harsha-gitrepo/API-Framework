package eCommerceAPITest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class eCommerceTest {
    public static void main(String[] args) {

        //Basic Request specification
        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
                setContentType(ContentType.JSON).build();

        //Building the body
        LoginRequestPOJO logReq = new LoginRequestPOJO();
        logReq.setUserEmail("harsha.itcareer9@gmail.com");
        logReq.setUserPassword("Harsha@123");

        //Adding up to the request specification
        RequestSpecification reqLogin = given().log().all().spec(req).body(logReq);

        //Extracting the response and saving the response in loginResponsePOJO class
        loginResponsePOJO logResp = reqLogin.when().post("/api/ecom/auth/login").then().log().all().
                extract().response().as(loginResponsePOJO.class);

        System.out.println(logResp.getToken());
    }
}
