package com.qa.swiggy.pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.swiggy.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class RestaurantPage extends TestBase {
	
	@FindBy(xpath = "//div[text()='Cart Empty']")
	WebElement CartEmpty;
	
	@FindBy(xpath = "(//div[text()='ADD'])[1]")
	WebElement FirstAdd;
	
	@FindBy(xpath = "(//div[text()='ADD'])[1]/following::div[1]")
	WebElement FirstPlusButton;
	
	@FindBy(xpath = "(//div[text()='ADD'])[1]/following::div[2]")
	WebElement FirstMinusButton;
	
	@FindBy(xpath = "(//div[text()='ADD'])[1]/following::div[3]")
	WebElement FirstAddedQuantity;
	
	@FindBy(xpath = "//div[text()='Subtotal']/following::div/span[contains(text(), '₹')]")
	WebElement SubTotalAmount;
	
	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement CheckoutButton;
	
	@FindBy(xpath = "(//span[@class='styles_price__2xrhD styles_itemPrice__1Nrpd styles_s__66zLz']/span[text()])[1]")
	WebElement AmountOfFirstDish;
	
	public RestaurantPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void VerifyAddButton() {
		Assert.assertTrue(CartEmpty.isDisplayed());
		extentTest.log(LogStatus.INFO, "Cart Empty is displayed");
		FirstAdd.click();
		extentTest.log(LogStatus.INFO, "Add button is clicked");
		waitForVisibility(FirstAddedQuantity);
		extentTest.log(LogStatus.INFO, "The added quantity is visible now");
		Assert.assertTrue(FirstPlusButton.isDisplayed());
		extentTest.log(LogStatus.INFO, "Plus button is visible now");
		Assert.assertTrue(FirstMinusButton.isDisplayed());
		extentTest.log(LogStatus.INFO, "Minus button is visible now");
		Assert.assertTrue(FirstAddedQuantity.isDisplayed());
		Assert.assertTrue(SubTotalAmount.isDisplayed());
		extentTest.log(LogStatus.INFO, "Sub Total Amount is visible now");
		Assert.assertTrue(CheckoutButton.isDisplayed());
		extentTest.log(LogStatus.INFO, "Check out button is visible now");
		FirstMinusButton.click();
		extentTest.log(LogStatus.INFO, "Minus button is clicked to verify the dish deletion");
		Assert.assertTrue(CartEmpty.isDisplayed());
		extentTest.log(LogStatus.INFO, "Dish is deleted and Cart is empty");
	}
	
	public void AssertAddbutton() throws InterruptedException {
		
		FirstAdd.click();
		extentTest.log(LogStatus.INFO, "Add button is clicked");
		waitForVisibility(FirstAddedQuantity);
		extentTest.log(LogStatus.INFO, "The added quantity is visible now");
		//System.out.println(FirstAddedQuantity.getText());
		
		for(int j = 2; j<7; j++) {
			Thread.sleep(2000);
			FirstPlusButton.click();
			Thread.sleep(2000);
			int i = Integer.parseInt(FirstAddedQuantity.getText());
			Thread.sleep(2000);
			Assert.assertEquals(i, j);
			float TotalAmount = Float.parseFloat(SubTotalAmount.getText().trim().replace("₹", ""));
			float AmountOfDish = Float.parseFloat(AmountOfFirstDish.getText());
			float ExpectedAmount = AmountOfDish * j;
			Assert.assertEquals(TotalAmount, ExpectedAmount);
					
		}
		extentTest.log(LogStatus.INFO, "The validation of add button incrementing the value by 1 is successful");
		extentTest.log(LogStatus.INFO, "The Sub total amount is displayed as expected");
	}

}
