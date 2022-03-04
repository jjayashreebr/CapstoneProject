package com.qa.pages;

import org.openqa.selenium.WebDriver;
import com.qa.locators.ViewCartPageLocators;

public class ViewCartPage extends ViewCartPageLocators {
	WebDriver driver=null;

	public ViewCartPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public String getProductName() {
		return driver.findElement(PRODUCT_DETAIL_IN_VIEW_CART).getText();
	}
	public String getProductQuantity() {
		return driver.findElement(PRODUCT_QUANTITY_IN_VIEW_CART).getText();
	}

}
