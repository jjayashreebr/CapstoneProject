package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.locators.ViewItemPageLocators;

public class ViewItemPage extends ViewItemPageLocators {
	WebDriver driver=null;

	public ViewItemPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public ViewItemPage clickAddtoCart() {
		 driver.findElement(ADD_TO_CART_VIEW_ITEM).click();
		 return new ViewItemPage(driver);
	}
	
	
	public CartModalPopUpPage waitForModalPopUpPage() {
		driver.switchTo().activeElement();
		return new CartModalPopUpPage(driver);
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



	public ViewItemPage setProductQuantity(String string) {
		driver.findElement(QUANTITY_VIEW_ITEM).clear();
		driver.findElement(QUANTITY_VIEW_ITEM).sendKeys(string);
		 return new ViewItemPage(driver);
	}

	
}
