package com.qa.testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/main/java/com/qa/swiggy/features/LocationEntry.feature",
		glue = {"com/qa/swiggy/stepDefinitions"},
		plugin = {"pretty", "html:target/cucumber-reports.html"},
		stepNotifications = true
		)

public class LocationEntry_TestRunner {

}
