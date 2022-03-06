package com.qa.pages;

import org.openqa.selenium.WebDriver;

import com.qa.locators.CheckoutPageLocators;

public class CheckoutPage extends CheckoutPageLocators {

	WebDriver driver=null;

	public CheckoutPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public String getProductDescription() {
		return driver.findElement(PRODUCT_DETAIL_IN_CHECKOUT).getText();
	}
	
	public PaymentPage clickPlaceOrder() {
		driver.findElement(PLACE_ORDER_BUTTON).click();	
		return new PaymentPage(driver);
	}
	
	
}
