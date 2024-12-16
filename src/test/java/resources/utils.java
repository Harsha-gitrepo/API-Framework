package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class utils {
    public static RequestSpecification req;

    //Creating below method to fetch the values from global.properties file
    public static String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream
                ("D:\\RESTful\\API_Framework\\src\\test\\java\\resources\\global.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }

    public RequestSpecification requestSpec() throws IOException {
        if (req == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).
                    addQueryParam("key", "qaclick123").
                    addFilter(RequestLoggingFilter.logRequestTo(log)).
                    addFilter(ResponseLoggingFilter.logResponseTo(log)).
                    setContentType(ContentType.JSON).build();
            return req;
        }
        return req;
    }

    public String getJSONPath(Response response, String key)
    {
        String respString = response.asString();
        JsonPath js = new JsonPath(respString);
        return js.get(key).toString();
    }

}