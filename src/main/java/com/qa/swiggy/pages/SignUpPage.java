package com.qa.swiggy.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.swiggy.base.TestBase;
import com.qa.swiggy.util.TestUtil;

public class SignUpPage extends TestBase {
	
	//pageFactory - OR
	@FindBy(xpath = "//a[text()='Sign up']")
	WebElement SignUpBtn;
	
	@FindBy(id="mobile")
	public static WebElement PhoneNumberTextBox;
	
	@FindBy(id="name")
	WebElement NameTextBox;
	
	@FindBy(id="email")
	WebElement EMailTextBox;
	
	@FindBy(id = "password")
	WebElement PasswordTextBox;
	
	@FindBy(xpath = "//button[text()='Have a referral code?']")
	WebElement HaveAReferraCodeBtn;
	
	@FindBy(id = "referral")
	WebElement ReferralTextBox;
	
	@FindBy(xpath="//a[@class='a-ayg']")
	WebElement ContinueBtn;
	
	@FindBy(xpath = "//a[text()='login to your account']")
	WebElement LoginToYourAccountBtn;
	
	@FindBy(xpath = "//div[text()='Mobile number already exists']")
	WebElement MobileNumberAlreadyExistsMessage;
	
	@FindBy(xpath = "//span[contains(@class, 'close')]")
	public static WebElement Close;
	

	public SignUpPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void SignUpFieldVerification() {
		Assert.assertTrue(PhoneNumberTextBox.isDisplayed());
		Assert.assertTrue(NameTextBox.isDisplayed());
		Assert.assertTrue(EMailTextBox.isDisplayed());
		Assert.assertTrue(PasswordTextBox.isDisplayed());
		HaveAReferraCodeBtn.click();
		Assert.assertTrue(ReferralTextBox.isDisplayed());
		Assert.assertTrue(ContinueBtn.isDisplayed());
		
	}
	
	public boolean SignUpWithExistingDetails() {
		SignUpBtn.click();
		PhoneNumberTextBox.sendKeys(prop.getProperty("validPhoneNumber"));
		NameTextBox.sendKeys(prop.getProperty("Name"));
		EMailTextBox.sendKeys(prop.getProperty("Email"));
		PasswordTextBox.sendKeys(prop.getProperty("Password"));
		ContinueBtn.click();
		waitForVisibility(MobileNumberAlreadyExistsMessage);
		return MobileNumberAlreadyExistsMessage.isDisplayed();
	}
	
	public LoginPage LoginToYourAccountClick() {
		SignUpBtn.click();
		LoginToYourAccountBtn.click();
		return new LoginPage();
	}
	
	public String SignUpWithNewDetails() {
		SignUpBtn.click();
		String InvalidMobileNumber = prop.getProperty("invalidPhoneNumber");
		PhoneNumberTextBox.sendKeys(InvalidMobileNumber);
		NameTextBox.sendKeys(prop.getProperty("Name"));
		EMailTextBox.sendKeys(TestUtil.RandomStringGenerator()+"@gmail.com");
		PasswordTextBox.sendKeys(prop.getProperty("Password"));
		ContinueBtn.click();
		return InvalidMobileNumber;
		
	}
	
	public LocationEntryPage SignUpClose() {
		Close.click();
		return new LocationEntryPage();
	}
}
