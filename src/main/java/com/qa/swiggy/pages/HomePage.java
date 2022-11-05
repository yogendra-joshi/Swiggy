package com.qa.swiggy.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.swiggy.base.TestBase;



public class HomePage extends TestBase {
	
	@FindBy(xpath= "//a[@title='Swiggy']")
	public static WebElement SwiggyLogo;
	
	@FindBy(xpath = "//span[@class='_3HusE']")
	public static WebElement SelectedLocation;
	
	@FindBy(xpath = "//span[text()='Filters']")
	public static WebElement Filters;
	
	@FindBy(xpath = "//a[text()='SHOW RESTAURANTS']")
	WebElement ShowRestaurant;
	
	@FindBy(xpath = "//div[text()='CLEAR ALL']")
	public static WebElement ClearAll;
	
	@FindBy(xpath = "//div[text()='Delivery Time']")
	public static WebElement DeliveryTimeSort;
	
	@FindBy(xpath = "//div[text()='Cost: Low to High']")
	public static WebElement CostLowToHighSort;
	
	@FindBy(xpath = "//div[text()='Cost: High to Low']")
	public static WebElement CostHighToLowSort;
	
	@FindBy(xpath = "//div[@class='_9uwBC wY0my']/following::"
			+ "div[text()!='•' and contains(text(), 'MINS')]")
	List<WebElement> DeliveryTime;
	
	@FindBy(xpath = "//div[@class='_9uwBC wY0my']/following::"
			+ "div[text()!='•' and contains(text(), ' FOR ')]")
	List<WebElement> Cost;
	
	@FindBy(xpath = "(//div[@class='nA6kb'])[1]")
	WebElement FirstRestaurantDisplay;
	
	@FindBy(xpath = "//span[text()='Search']")
	WebElement SearchRestaurant;
	
	@FindBy(xpath = "//input[@class='_2FkHZ']")
	WebElement SearchTextBox;
	
	@FindBy(xpath = "//div[@class='RNzoC']")
	List<WebElement> RestaurantNames;
	
	public WebElement RestaurantCard(String RestaurantName) {
		String restaurantCardXpath = "//div[contains(text(), '"+RestaurantName+"')]";
		WebElement RestaurantCardDisplay = driver.findElement(By.xpath(restaurantCardXpath));
		return RestaurantCardDisplay;
	}
	
	public RestaurantPage RestaurantPageDisplay(String searchRestaurantKey) {
		SearchRestaurant.click();
		SearchTextBox.sendKeys(searchRestaurantKey);
		for(int i = 0; i<RestaurantNames.size(); i++) {
			if(RestaurantNames.get(i).getText().contains(searchRestaurantKey))
				RestaurantNames.get(i).click();
				RestaurantCard(searchRestaurantKey).click();
		}
		return new RestaurantPage();
	}
	
	
	public String getFirstRestaurantName(WebElement SortByTimeorCost) {
		SortByTimeorCost.click();
		return FirstRestaurantDisplay.getText();
	}
	
	public WebElement Cuisines(String cuisine) {
		String cuisineXpath = "//div[text()='"+cuisine+"']/following::"
				+ "div//input[@value='"+cuisine+"']/parent::label";
		WebElement CuisineCheckbox = driver.findElement(By.xpath(cuisineXpath));
		return CuisineCheckbox;
	}
	
	public void ApplyFilter() {
		Filters.click();
		WebElement TestCusine = Cuisines(prop.getProperty("TestCuisine"));
		waitForVisibility(TestCusine);
		TestCusine.click();
		ShowRestaurant.click();
		
	}
	
	public WebElement getRestaurantName(String TimeOrPrice) {
		String RestaurantNameXpath = "//div[text()='"+TimeOrPrice+"']/parent::"
				+ "div/preceding::div[@class='nA6kb']";
		WebElement restaurantName = driver.findElement(By.xpath(RestaurantNameXpath));
		return restaurantName;
	}
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String getFastDelivery() {
		/*long minDeliveryTime = DeliveryTime
			.stream()
				.mapToLong(e -> Long.parseLong(e.getText().trim().replace(" MINS", "")))
					.min()
						.getAsLong();*/
		long minDeliveryTime = Long.parseLong
				(DeliveryTime.get(0).getText().trim().replace(" MINS", ""));
		for (WebElement e : DeliveryTime) {
			long deliveryTime = Long.parseLong(e.getText().trim().replace(" MINS", ""));
			if(deliveryTime<minDeliveryTime) {
				minDeliveryTime = deliveryTime;
			}
		}
		
		String MinDeliveryTime = minDeliveryTime +" MINS";
		return MinDeliveryTime;
		
	}
	
	public String getCheapestRestrnt() {
		/*long minCheapestRest = Cost
				.stream()
					.mapToLong(e -> Long.parseLong(e.getText().trim().replace(" FOR TWO", "").replace("₹", "")))
						.min()
							.getAsLong();*/
		long minCheapestRest = Long.parseLong
				(Cost.get(0).getText().trim().replace(" FOR TWO", "").replace("₹", ""));
		for (WebElement e : Cost) {
			long minRate = Long.parseLong(e.getText().trim().replace(" FOR TWO", "").replace("₹", ""));
			if(minRate<minCheapestRest) {
				minCheapestRest = minRate;
			}
		}
		String CheapestRate = "₹" + minCheapestRest + " FOR TWO";
		return CheapestRate;
		
	}
	
	public String getExpensiveRestrnt() {
		/*long maxExpensiveRest = Cost
				.stream()
					.mapToLong(e -> Long.parseLong(e.getText().trim().replace(" FOR TWO", "").replace("₹", "")))
						.max()
							.getAsLong();*/
		long maxExpensiveRest = Long.parseLong
				(Cost.get(0).getText().trim().replace(" FOR TWO", "").replace("₹", ""));
		for (WebElement e : Cost) {
			long maxrate = Long.parseLong(e.getText().trim().replace(" FOR TWO", "").replace("₹", ""));
			if(maxrate>maxExpensiveRest) {
				maxExpensiveRest = maxrate;
			}
		}
		String ExpensiveRate = "₹" + maxExpensiveRest + " FOR TWO";
		return ExpensiveRate;
		
	}
	
	
	public void homePageURLCheck() {
		String ActualUrl = driver.getCurrentUrl();
		String url = prop.getProperty("homepageurl");
		Assert.assertEquals(ActualUrl, url);
	}
	
	

}
