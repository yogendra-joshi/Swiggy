package com.qa.swiggy.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.swiggy.base.TestBase;

public class LoginPage extends TestBase {

	//pageFactory - OR
		@FindBy(xpath="//a[text()='Login']")
		WebElement LoginBtn;
		
		@FindBy(id="mobile")
		public static WebElement PhoneNumberTextBox;
		
		@FindBy(xpath="//a[@class='a-ayg']")
		public static WebElement LoginButtonUnderMobile;
		
		@FindBy(xpath = "//a[text()='create an account']")
		WebElement CreateanAccountLink;
		
		@FindBy(id = "otp")
		public static WebElement OTPTextBox;
		
		@FindBy(xpath="//a[text()='VERIFY OTP']")
		public static WebElement VerifyOTPButton;
		
		public LoginPage() {
			PageFactory.initElements(driver, this);
		}
		
		
		
		public boolean LoginValidMobile(String MobileNumber) {
			LoginBtn.click();
			PhoneNumberTextBox.sendKeys(MobileNumber);
			LoginButtonUnderMobile.click();
			return OTPTextBox.isDisplayed();
			
		}
		
		public SignUpPage LoginInvalidPhoneNumber(String MobileNumber) {
			LoginBtn.click();
			PhoneNumberTextBox.sendKeys(MobileNumber);
			LoginButtonUnderMobile.click();
			return new SignUpPage();
		}
		
		public SignUpPage ClickCreateAnAccount() {
			LoginBtn.click();
			CreateanAccountLink.click();
			return new SignUpPage();
		}
		
		public LocationEntryPage LoginClose() {
			SignUpPage.Close.click();
			return new LocationEntryPage();
		}
}
