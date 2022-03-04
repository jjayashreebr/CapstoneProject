package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.locators.ViewItemPageLocators;

public class ViewItemPage extends ViewItemPageLocators {
	WebDriver driver=null;

	public ViewItemPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public ViewCartPage clickAddtoCart() {
		 driver.findElement(ADD_TO_CART_VIEW_ITEM).click();
		 return new ViewCartPage(driver);
	}
	
	public WebElement getAddtoCartEle() {
		return driver.findElement(ADD_TO_CART_VIEW_ITEM);
	}
	
	public String getProductName() {
		return driver.findElement(PRODUCT_DETAIL_VIEW_ITEM).getText();
	}

	public WebElement getProductQuantity() {
		return driver.findElement(QUANTITY_VIEW_ITEM);
	}

	public ViewCartPage clickViewCart() {
		 driver.findElement(VIEW_CART_BUTTON_IN_MODEL).click();
		 return new ViewCartPage(driver);
	}

	
	public ViewItemPage clickContinueShopping() {
		 driver.findElement(CONTINUE_BUTTON_IN_MODEL).click();
		 return new ViewItemPage(driver);
	}
}
