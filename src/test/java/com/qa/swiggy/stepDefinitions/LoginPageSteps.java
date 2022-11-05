package com.qa.swiggy.stepDefinitions;

import org.testng.Assert;

import com.qa.swiggy.base.TestBase;
import com.qa.swiggy.pages.LoginPage;
import com.qa.swiggy.pages.SignUpPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageSteps extends TestBase{
	
	LoginPage loginPage = new LoginPage();
	SignUpPage signUpPage = new SignUpPage();
	String InvalidPhoneNumber;
	

	@When("^user enters valid phone number in login page and checks the OTP fields$")
    public void user_enters_valid_phone_number_in_login_page_and_checks_the_otp_fields(){
		
		
    	boolean flag = loginPage.LoginValidMobile(prop.getProperty("validPhoneNumber"));
		Assert.assertTrue(flag);
		Assert.assertTrue(loginPage.VerifyOTPButton.isDisplayed());
    }
	
	@When("^invalid phone number is entered in the login page$")
    public void invalid_phone_number_is_entered_in_the_login_page() {
		InvalidPhoneNumber = prop.getProperty("invalidPhoneNumber");
		signUpPage = loginPage.LoginInvalidPhoneNumber(InvalidPhoneNumber);
	}
	
	@Then("^validate the navigation to sign up page and verify the respective fields$")
    public void validate_the_navigation_to_sign_up_page_and_verify_the_respective_fields() {
		signUpPage.SignUpFieldVerification();
		String EnteredNumber = signUpPage.PhoneNumberTextBox.getAttribute("value").toString();
		Assert.assertEquals(InvalidPhoneNumber, EnteredNumber);
	}
	
	@When("^user clicks on Create Account link$")
    public void user_clicks_on_create_account_link() {
		signUpPage = loginPage.ClickCreateAnAccount();
	}
	
	@Then("^Verify the sign up page fields$")
    public void verify_the_sign_up_page_fields() {
		signUpPage.SignUpFieldVerification();
	}
	

	 @After
	 public void user_quits_the_session(){
    	driver.quit();
    }

}
