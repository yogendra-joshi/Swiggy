package com.qa.swiggy.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.swiggy.base.TestBase;
import com.qa.swiggy.pages.LoginPage;
import com.qa.swiggy.pages.SignUpPage;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPageTest extends TestBase {
	
	
	
	LoginPage loginPage;
	SignUpPage signUpPage;
	
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeTest
	public void InitiateReporting() {
		setExtent("LoginPageTest");
	}
	
	
	@BeforeMethod
	public void setUp() {
		Initialization();
		loginPage = new LoginPage();
		
		
	}
	
	//Clicks Login button with Valid Phone number --> Verifies the OTP text box and Verify OTP button
	@Test
	public void loginPageValidPhoneNumberTest() {
		extentTest = extent.startTest("loginPageValidPhoneNumberTest");
		boolean flag = loginPage.LoginValidMobile(prop.getProperty("validPhoneNumber"));
		extentTest.log(LogStatus.INFO, "Logged in valid mobile number");
		Assert.assertTrue(flag);
		extentTest.log(LogStatus.INFO, "Verified the OTP Textbox");
		Assert.assertTrue(loginPage.VerifyOTPButton.isDisplayed());
		extentTest.log(LogStatus.INFO, "Verified the OTP Button");
	}
	
	//Clicks Login button with invalid Phone number --> Verifies the page navigation to sign up page and validate the fields in the sign up page
	@Test
	public void loginPageInvalidPhoneNumberTest() {
		extentTest = extent.startTest("loginPageInvalidPhoneNumberTest");
		String InvalidPhoneNumber = prop.getProperty("invalidPhoneNumber");
		signUpPage = loginPage.LoginInvalidPhoneNumber(InvalidPhoneNumber);
		extentTest.log(LogStatus.INFO, "Logged in invalid mobile number");
		signUpPage.SignUpFieldVerification();
		extentTest.log(LogStatus.INFO, "Verified the SignUp Fields");
		String EnteredNumber = signUpPage.PhoneNumberTextBox.getAttribute("value").toString();
		Assert.assertEquals(InvalidPhoneNumber, EnteredNumber);
		extentTest.log(LogStatus.INFO, "Phone numbers matched");
		
	}
	
	//Clicks on Create an account link from login page --> Verifies the page navigation to sign up page and validate the fields in the sign up page
	@Test
	public void ClickCreateAnAccountTest() {
		extentTest = extent.startTest("ClickCreateAnAccountTest");
		signUpPage = loginPage.ClickCreateAnAccount();
		extentTest.log(LogStatus.INFO, "Clicked on Create Account");
		signUpPage.SignUpFieldVerification();
		extentTest.log(LogStatus.INFO, "Verified the SignUp Fields");
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		
		TestBase.TestClosureReport(result);
		driver.quit();
	}
	
	

	@AfterTest
	public void CloseExtent() {
		endReport();
	}

}
