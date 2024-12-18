package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features",
        glue = {"stepDefinitions"},
        tags = "@DeletePlace",
        plugin = "json:target/jsonReports/cucumber-reports.json"
)
public class TestRunner {
}
