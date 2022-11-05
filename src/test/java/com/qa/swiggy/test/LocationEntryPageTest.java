package com.qa.swiggy.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.swiggy.base.TestBase;
import com.qa.swiggy.pages.HomePage;
import com.qa.swiggy.pages.LocationEntryPage;
import com.qa.swiggy.util.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class LocationEntryPageTest extends TestBase{
	
	LocationEntryPage locationEntryPage;
	HomePage homePage;
	
	public LocationEntryPageTest() {
		super();
	}
	
	@BeforeTest
	public void InitiateReporting() {
		setExtent("LocationEntryPageTest");
	}
	
	@BeforeMethod
	public void setUp() {
		Initialization();
		locationEntryPage = new LocationEntryPage();
	}
	
	@DataProvider
	public Object[][] getLocationTestData() {
		Object data[][] = TestUtil.getTestData("Location");
		return data;
	}
	
	@Test (dataProvider = "getLocationTestData")
	public void LocationEntryTest(String Location) throws InterruptedException {
		extentTest = extent.startTest("LocationEntryTest");
		homePage = locationEntryPage.LocationEntry(Location);
		extentTest.log(LogStatus.INFO, "Location " + Location + " entered and clicked");
		if(!Location.contains("Mundakayam")) {
			waitForVisibility(HomePage.SelectedLocation);
			extentTest.log(LogStatus.INFO, "Restaurants in " + Location + " are visible now");
			Assert.assertTrue(HomePage.SwiggyLogo.isDisplayed());
			extentTest.log(LogStatus.INFO, "Swiggy Logo is displayed in the page");
			
		}
		else {
			Assert.assertTrue(LocationEntryPage.NoServiceError.isDisplayed());
			extentTest.log(LogStatus.INFO, "No Service Error is displayed for " + Location);
			Assert.assertTrue(locationEntryPage.NoServiceMsgCheck());
			extentTest.log(LogStatus.INFO, "No Service Error message is displayed as exepcted");
		}
		
	}
	
	@Test
	public void FindLocationTest() {
		extentTest = extent.startTest("FindLocationTest");
		homePage = locationEntryPage.FindButtonClick();
		waitForVisibility(HomePage.SwiggyLogo);
		
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
