package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
        (
                features =".//Features//",
                glue = "stepDefinitions",
                dryRun = false,
                monochrome = true,
                stepNotifications = true,
               // tags = "@Rashmika",
                plugin = {"pretty",
                        "html:target/cucumber-report/niks.html"
                }

        )
public class TestRun {
}
