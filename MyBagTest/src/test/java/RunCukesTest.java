/**
 * Created by tescu on 10/1/15.
 */
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/addToBag.feature",
        format = {"pretty", "html:target/report/",
                "json:target/report/cucu_json_report.json",
                "junit:target/report/cucumber_junit_report.xml"})
public class RunCukesTest {
}