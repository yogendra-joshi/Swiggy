package com.qa.swiggy.test;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.swiggy.base.TestBase;
import com.qa.swiggy.pages.HomePage;
import com.qa.swiggy.pages.LocationEntryPage;
import com.qa.swiggy.pages.RestaurantPage;

public class RestaurantPageTest extends TestBase {
	LocationEntryPage locationEntryPage;
	HomePage homePage;
	RestaurantPage restaurantPage;
	
	public RestaurantPageTest() {
		super();
	}
	
	@BeforeTest
	public void InitiateReporting() {
		setExtent("RestaurantPageTest");
	}
	
	@BeforeMethod
	public void setUp() {
		Initialization();
		locationEntryPage = new LocationEntryPage();
		homePage = locationEntryPage.LocationEntry(prop.getProperty("TestLocation"));
		restaurantPage = homePage.RestaurantPageDisplay(prop.getProperty("TestRestaurant"));
	}
	
	
	@Test 
	public void VerifyAddButtonTest() {
		extentTest = extent.startTest("VerifyAddButtonTest");
		restaurantPage.VerifyAddButton();
	}
	
	@Test
	public void AssertAddButtonTest() throws InterruptedException {
		extentTest = extent.startTest("AssertAddButtonTest");
		restaurantPage.AssertAddbutton();
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
