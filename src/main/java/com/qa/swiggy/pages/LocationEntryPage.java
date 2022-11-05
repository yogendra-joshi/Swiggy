package com.qa.swiggy.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.swiggy.base.TestBase;

public class LocationEntryPage extends TestBase {

	@FindBy(id = "location")
	public static WebElement EnterLocation;
	
	@FindBy(xpath = "//div[@class='_1b8uz']")
	public static WebElement NoServiceError;
	
	@FindBy(xpath = "//div[text()='Clear']")
	WebElement ClearLocation;
	
	@FindBy(xpath = "//span[text()='FIND FOOD']")
	WebElement FindFood;
	
	@FindBy(xpath = "//div[text()= 'Enter your delivery location']")
	WebElement EnterDeliveryLocWarningMsg;
	
	@FindBy(xpath = "//div[@class='_1oLDb']//descendant::span[contains(@class, '_2W-T9')]")
	List<WebElement> SearchLocationResults;
	
	public LocationEntryPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	
	public HomePage LocationEntry(String Location) {
		EnterLocation.sendKeys(Location);
		for (int i = 0; i<SearchLocationResults.size(); i++) {
			if(SearchLocationResults.get(i).getText().contains(Location)) {
				SearchLocationResults.get(i).click();
				}
		}
		return new HomePage();
	}
	
	public HomePage FindButtonClick() {
		EnterLocation.sendKeys(prop.getProperty("TestLocation"));
		FindFood.click();
		return new HomePage();
		
	}
	
	//Sorry! We don't serve at your location currently.
	
	public boolean NoServiceMsgCheck() {
		String NoServiceMsg = NoServiceError.getText();
		String ActualMsg = "Sorry! We don't serve at your location currently.";
		return NoServiceMsg.equals(ActualMsg);
	}
}
