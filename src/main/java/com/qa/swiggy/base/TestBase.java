package com.qa.swiggy.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import com.qa.swiggy.util.TestUtil;
import com.qa.swiggy.util.WebDriverListeners;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase  {
	
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver event;
	public static WebDriverListeners Listener;
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	
	public TestBase() {
		try {
			 prop = new Properties();
			 FileInputStream ip = new FileInputStream("D:\\SimpliLearn\\Phase 2 Project\\"
			 		+ "E2EScenarios-POM\\src\\main\\java\\com\\qa\\swiggy\\config\\config.properties");
			 prop.load(ip);
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void Initialization() {
		String browserName = prop.getProperty("browser");
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		event = new EventFiringWebDriver(driver);
		Listener = new WebDriverListeners();
		event.register(Listener);
		driver = event;
		
		
		
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
	}
	
	  public static void waitForVisibility(WebElement element) {
           new WebDriverWait(driver, TestUtil.EXPLICIT_WAIT)
               .until(ExpectedConditions.visibilityOf(element));
	  }
	  
	  public static void setExtent(String TestSetName) {
		  String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		  extent = new ExtentReports(System.getProperty("user.dir")+
				  "/ExtentReports/"+TestSetName+"/SwiggyApplicationTest_" + TestSetName + 
				  "_" + dateName +".html", true);
		  
	  }
	  
	  public static void endReport() {
		  extent.flush();
		  extent.close();
	  }
	  
	  public static String getScreenshot(WebDriver driver, String ScreenshotName) throws IOException {
		  String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		  TakesScreenshot ts = (TakesScreenshot)driver;
		  File source = ts.getScreenshotAs(OutputType.FILE);
		  String destination = System.getProperty("user.dir") +
				  "/FailedTestsScreenshots/" + ScreenshotName + dateName + ".png";
		  File FinalDestination = new File(destination);
		  FileUtils.copyFile(source, FinalDestination);
		  return destination;
	  }
	  
	 


	public static void TestClosureReport(ITestResult result) throws IOException {
	
		 if(result.getStatus()==ITestResult.FAILURE) {
			  extentTest.log(LogStatus.FAIL, "Test Case Failed is " + result.getMethod().getMethodName());
			  extentTest.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
			  
			  String ScreenshotPath = getScreenshot(driver,result.getMethod().getMethodName());
			  extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(ScreenshotPath));
		  }
		  else if (result.getStatus()==ITestResult.SKIP) {
			  extentTest.log(LogStatus.SKIP, "Test Case Skipped is " + result.getMethod().getMethodName());
			  
		  } 
		  else if (result.getStatus()==ITestResult.SUCCESS) {
			  extentTest.log(LogStatus.PASS, "Test Case Passed is " + result.getMethod().getMethodName());
		  }
		  extent.endTest(extentTest);
	}

}
