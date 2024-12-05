package eCommerceAPITest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
        String token = logResp.getToken();
        System.out.println(logResp.getUserId());
        String userID = logResp.getUserId();

        //ADD PRODUCT
        RequestSpecification addProductBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
                addHeader("Authorization", token).
                build();

        //Passing all the form parameters with the above request specification
        //Adding to the above request specification
        RequestSpecification reqAddProduct = given().log().all().spec(addProductBaseReq)
                .param("productName", "Marathon Shoes-2")
                .param("productAddedBy", userID)
                .param("productCategory", "Fashion")
                .param("productSubCategory", "Shirts")
                .param("productDescription", "Adidas Originals")
                .param("productFor", "Women")
                .param("productPrice", "65000")
                .multiPart("productImage", new File("C://Users//AIN143//Desktop//Image.jpg"));

        //We are not using POJO classes, as the output is having just two keys in the JSON body
        String addProductResponse = reqAddProduct.when().post("/api/ecom/product/add-product")
                .then().log().all().extract().response().asString();
        //Parsing the extracted JSON body
        JsonPath js = new JsonPath(addProductResponse);
        String productID = js.get("productId");

        //CREATE ORDER
        RequestSpecification createOrderBaseReq = new RequestSpecBuilder().
                setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token).
                setContentType(ContentType.JSON).build();

        //Building the body using serialization
        orderDetailsPOJO ordDet = new orderDetailsPOJO();
        ordDet.setCountry("India");
        ordDet.setProductOrderedId(productID);
        List<orderDetailsPOJO> orderDetailsList = new ArrayList<orderDetailsPOJO>();
        orderDetailsList.add(ordDet);

        ordersPOJO orders = new ordersPOJO();
        orders.setOrders(orderDetailsList);

        RequestSpecification reqCreateOrder = given().log().all().spec(createOrderBaseReq).body(orders);
        String responseAddOrder = reqCreateOrder.when().post("/api/ecom/order/create-order")
                .then().log().all().extract().response().asString();
        System.out.println(responseAddOrder);


        //DELETE PRODUCT
        RequestSpecification deleteProdBaseReq = new RequestSpecBuilder().
                setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token).
                setContentType(ContentType.JSON).build();

        RequestSpecification deleteProdReq = given().log().all().spec(deleteProdBaseReq)
                .pathParams("productId",productID);
        String deleteResponse = deleteProdReq.when()
                .delete("/api/ecom/product/delete-product/{productId}").then().log().all()
                .extract().response().asString();
    }
}
