package com.qa.swiggy.stepDefinitions;

import org.testng.Assert;

import com.qa.swiggy.base.TestBase;
import com.qa.swiggy.pages.LoginPage;
import com.qa.swiggy.pages.SignUpPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignUpPageSteps extends TestBase {
	
	LoginPage loginPage;
	SignUpPage signUpPage;
	String EnteredNumber;
	
	

	@Before
    public void user_launches_the_application()  {
		TestBase.Initialization();
		
		
    }

	@Given("^user opens the signUp page$")
    public void user_opens_the_signup_page()  {
		signUpPage = new SignUpPage();
    	
    }

	@Then("^already existing details are entered and proceeded, display appropriate message$")
    public void already_existing_details_are_entered_and_proceeded_display_appropriate_message() {
		boolean flag = signUpPage.SignUpWithExistingDetails();
    	Assert.assertTrue(flag);
    }
	
	@When("^user clicks on login page link$")
    public void user_clicks_on_login_page_link() {
		loginPage = signUpPage.LoginToYourAccountClick();
	}
	
	@Then("^verify the login page fields$")
    public void verify_the_login_page_fields() {
		Assert.assertTrue(LoginPage.PhoneNumberTextBox.isDisplayed());
		Assert.assertTrue(LoginPage.LoginButtonUnderMobile.isDisplayed());
	}
	
	@When("^user signs up with new details and click continue$")
    public void user_signs_up_with_new_details_and_click_continue() throws InterruptedException {
		EnteredNumber = signUpPage.SignUpWithNewDetails();
		
	}

	 @Then("^Verify the navigation to login page with given number prefilled in the phone number text box$")
	 public void verify_the_navigation_to_login_page_with_given_number_prefilled_in_the_phone_number_text_box() {
		 loginPage = new LoginPage();
		 String ActualNumber = LoginPage.PhoneNumberTextBox.getAttribute("value").toString();
		 Assert.assertEquals(ActualNumber, EnteredNumber, "Number not matched");
		 Assert.assertTrue(LoginPage.OTPTextBox.isDisplayed());
		 Assert.assertTrue(LoginPage.VerifyOTPButton.isDisplayed());
			
	 }
	 
	 
   @After
    public void user_quits_the_session()  {
    	driver.quit();
    }

}
