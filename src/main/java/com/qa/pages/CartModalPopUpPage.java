package com.qa.pages;

import org.openqa.selenium.WebDriver;
import com.qa.locators.CartModalPopupPageLocators;
;

public class CartModalPopUpPage extends CartModalPopupPageLocators {
	WebDriver driver=null;

	public CartModalPopUpPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public ViewCartPage clickViewCart() {
		 driver.findElement(VIEW_CART_BUTTON_IN_MODEL).click();
		 return new ViewCartPage(driver);
	}
	
	public CartModalPopUpPage clickContinueShopping() {
		 driver.findElement(CONTINUE_BUTTON_IN_MODEL).click();
		 return new CartModalPopUpPage(driver);
	}
	
	public ViewItemPage waitForViewItemPage() {
		driver.switchTo().defaultContent();
		 return new ViewItemPage(driver);
	}

}
