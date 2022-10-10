package Cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",
				plugin ={"json:target/jsonReports/cucumber-report.json",
						"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},	
				glue= {"stepDefinations"},
				tags= "@Regression")

public class TestRunner {
	
}
