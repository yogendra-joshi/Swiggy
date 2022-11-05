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
import com.qa.swiggy.pages.HomePage;
import com.qa.swiggy.pages.LocationEntryPage;
import com.relevantcodes.extentreports.LogStatus;

public class HomePageTest extends TestBase{
	
	LocationEntryPage locationEntryPage;
	HomePage homePage;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeTest
	public void InitiateReporting() {
		setExtent("HomePageTest");
	}
	
	@BeforeMethod
	public void setUp() {
		Initialization();
		locationEntryPage = new LocationEntryPage();
		homePage = locationEntryPage.LocationEntry(prop.getProperty("TestLocation"));
	}
	
	@Test
	public void FastestDeliveryTest() {
		extentTest = extent.startTest("FastestDeliveryTest");
		homePage.ApplyFilter();
		extentTest.log(LogStatus.INFO, "Applied Filter with Test Cuisine");
		String FirstRestaurantName = homePage.getFirstRestaurantName(HomePage.DeliveryTimeSort);
		extentTest.log(LogStatus.INFO, "The Delivery Time is sorted in ascending order");
		extentTest.log(LogStatus.INFO, "The name of the restaurant displayed at First is " + FirstRestaurantName);
		String FastDelivery = homePage.getFastDelivery();
		extentTest.log(LogStatus.INFO, "The minimum delivery time is compared among the restaurants displayed in the lot");
		extentTest.log(LogStatus.INFO, "The minimum delivery time is " + FastDelivery);
		String RestaurantName = homePage.getRestaurantName(FastDelivery).getText();
		extentTest.log(LogStatus.INFO, "The Restaurant name with minimum delivery Time is found to be " + RestaurantName);
		Assert.assertEquals(RestaurantName, FirstRestaurantName);
		extentTest.log(LogStatus.INFO, "Restaurant displayed at first is as expected");
	}
	
	@Test
	public void CheapestRestaurantTest() {
		extentTest = extent.startTest("CheapestRestaurantTest");
		homePage.ApplyFilter();
		extentTest.log(LogStatus.INFO, "Applied Filter with Test Cuisine");
		String FirstRestaurantName = homePage.getFirstRestaurantName(HomePage.CostLowToHighSort);
		extentTest.log(LogStatus.INFO, "The Cost: Low to High is sorted");
		extentTest.log(LogStatus.INFO, "The name of the restaurant displayed at First is " + FirstRestaurantName);
		String CheapestRestaurant = homePage.getCheapestRestrnt();
		extentTest.log(LogStatus.INFO, "The Cheapest restaurant is compared among the restaurants displayed in the lot");
		extentTest.log(LogStatus.INFO, "The Cheapest price is " + CheapestRestaurant);
		String RestaurantName = homePage.getRestaurantName(CheapestRestaurant).getText();
		extentTest.log(LogStatus.INFO, "The Restaurant name with minimum price is found to be " + RestaurantName);
		Assert.assertEquals(RestaurantName, FirstRestaurantName);
		extentTest.log(LogStatus.INFO, "Restaurant displayed at first is as expected");
	}
	
	@Test
	public void ExpensiveRestaurantTest() {
		extentTest = extent.startTest("ExpensiveRestaurantTest");
		homePage.ApplyFilter();
		extentTest.log(LogStatus.INFO, "Applied Filter with Test Cuisine");
		String FirstRestaurantName = homePage.getFirstRestaurantName(HomePage.CostHighToLowSort);
		extentTest.log(LogStatus.INFO, "The Cost: High to Low is sorted");
		extentTest.log(LogStatus.INFO, "The name of the restaurant displayed at First is " + FirstRestaurantName);
		String ExpensiveRest = homePage.getExpensiveRestrnt();
		extentTest.log(LogStatus.INFO, "The Expensive restaurant is compared among the restaurants displayed in the lot");
		extentTest.log(LogStatus.INFO, "The Expensive price is " + ExpensiveRest);
		String RestaurantName = homePage.getRestaurantName(ExpensiveRest).getText();
		extentTest.log(LogStatus.INFO, "The Restaurant name with minimum price is found to be " + RestaurantName);
		Assert.assertEquals(RestaurantName, FirstRestaurantName);
		extentTest.log(LogStatus.INFO, "Restaurant displayed at first is as expected");
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
