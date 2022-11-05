package com.qa.swiggy.stepDefinitions;

import org.testng.Assert;

import com.qa.swiggy.base.TestBase;
import com.qa.swiggy.pages.HomePage;
import com.qa.swiggy.pages.LocationEntryPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LocationEntrySteps extends TestBase {

	LocationEntryPage locationEntryPage;
	HomePage homePage;

	
	 @Given("^user enters (.*) in the Enter your Delivery Location field$")
	    public void user_enters_in_the_enter_your_delivery_location_field(String location) {
		locationEntryPage = new LocationEntryPage();
		homePage = locationEntryPage.LocationEntry(location);
	 }
	 
	 @Then("^navigate to home page$")
	    public void navigate_to_home_page() {
		waitForVisibility(HomePage.SelectedLocation);
		Assert.assertTrue(HomePage.SwiggyLogo.isDisplayed());
	 }
	 
	 @Then("^display no service error message$")
	    public void display_no_service_error_message() {
		 Assert.assertTrue(LocationEntryPage.NoServiceError.isDisplayed());
		 Assert.assertTrue(locationEntryPage.NoServiceMsgCheck());
	 }
	 
	 @After
	    public void user_quits_the_session()  {
	    	driver.quit();
	    }
	
	
}
