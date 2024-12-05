package files;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class complexJSONParser {

    @Test
    public void jsonParse() {

        JsonPath js = new JsonPath(payload.CoursePrice());

        //1. Print No of courses returned by API
        int count = js.getInt("courses.size()");
        System.out.println(count);

        //2. Print Purchase Amount
        int purchaseAmount = js.get("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);

        //3. Print Title of the first course
        String firstCourse = js.get("courses[0].title");
        System.out.println(firstCourse);

        //4. Print All course titles and their respective Prices
        for (int i=0; i<js.getInt("courses.size()"); i++)
        {
            String Course = js.get("courses["+i+"].title");
            System.out.println("Course Number " +(i+1) + ": "+ Course);
            System.out.println("Course Price: " + js.getInt("courses["+i+"].price"));
        }

        //5. Print no of copies sold by RPA Course
        // The position of the course 'RPA' may change from one index to another as in the JSON body
        //So how do we do it?
        for (int i=0; i<js.getInt("courses.size()"); i++)
        {
            String title = js.get("courses["+i+"].title").toString();
            if(title.equalsIgnoreCase("RPA")) {
                String copiesSold = js.get("courses[" + i + "].copies").toString();
                System.out.println("Copies sold by RPA = " + copiesSold);
                break;
            }
        }

        //6. Verify if Sum of all Course prices matches with Purchase Amount
        int sum = 0;
        for (int i=0; i<js.getInt("courses.size()"); i++)
        {
            int price = js.getInt("courses["+i+"].price");
            int copies = js.getInt("courses["+i+"].copies");
            sum = sum + price*copies;
        }
        Assert.assertEquals(sum, purchaseAmount);
        if(sum == purchaseAmount) {
            System.out.println("Matches");
        }
        else {
            System.out.println("Doesn't Match");
        }
    }
}
