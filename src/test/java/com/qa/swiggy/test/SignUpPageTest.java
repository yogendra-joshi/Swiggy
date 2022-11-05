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

public class SignUpPageTest extends TestBase {
	
	LoginPage loginPage;
	SignUpPage signUpPage;
	
	public SignUpPageTest() {
		super();
	}
	
	@BeforeTest
	public void InitiateReporting() {
		setExtent("SignUpPageTest");
	}
	
	@BeforeMethod
	public void setUp() {
		Initialization();
		loginPage = new LoginPage();
		signUpPage = new SignUpPage();
		
	}
	
	//Verifies if the message "Mobile Number Already Exists" is displayed when signed up with already existing details
	@Test
	public void SignUpWithExistingDetailsTest() {
		extentTest = extent.startTest("SignUpWithExistingDetailsTest");
		boolean flag = signUpPage.SignUpWithExistingDetails();
		extentTest.log(LogStatus.INFO, "Signed Up with already existing mobile details");
		Assert.assertTrue(flag);
		extentTest.log(LogStatus.INFO, "'Mobile Number Already Exists' hover got displayed");
	}
	
	//Verifies if Login Page fields and buttons are displayed when Login to your account link is clicked
	@Test
	public void LoginToYourAccountClickTest() {
		extentTest = extent.startTest("LoginToYourAccountClickTest");
		loginPage = signUpPage.LoginToYourAccountClick();
		extentTest.log(LogStatus.INFO, "Login to your Account link is visible and clicked");
		Assert.assertTrue(loginPage.PhoneNumberTextBox.isDisplayed());
		extentTest.log(LogStatus.INFO, "Phone Number Text Box is displayed");
		Assert.assertTrue(loginPage.LoginButtonUnderMobile.isDisplayed());
		extentTest.log(LogStatus.INFO, "Login Button is displayed");
	}
	
	//Verifies if OTP details are displayed when all mandatory fields are entered with details in sign up page and Continue button is clicked
	@Test
	public void SignUpWithNewDetailsTest() {
		extentTest = extent.startTest("SignUpWithNewDetailsTest");
		String EnteredNumber = signUpPage.SignUpWithNewDetails();
		extentTest.log(LogStatus.INFO, "Signed Up with a new Phone number and continue button is clicked");
		extentTest.log(LogStatus.INFO, "Entered Phone number is stored");
		String ActualNumber = LoginPage.PhoneNumberTextBox.getAttribute("value").toString();
		extentTest.log(LogStatus.INFO, "Page navigates to OTP page and the pre displayed number is stored");
		Assert.assertEquals(ActualNumber, EnteredNumber, "Number not matched");
		extentTest.log(LogStatus.INFO, "Entered Phone number and Pre displayed number matches");
		Assert.assertTrue(LoginPage.OTPTextBox.isDisplayed());
		extentTest.log(LogStatus.INFO, "OTP text box is displayed");
		Assert.assertTrue(LoginPage.VerifyOTPButton.isDisplayed());
		extentTest.log(LogStatus.INFO, "Verify OTP button is displayed");
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
