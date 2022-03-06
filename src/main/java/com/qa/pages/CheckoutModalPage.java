package com.qa.pages;

import org.openqa.selenium.WebDriver;
import com.qa.locators.CartModalPopupPageLocators;
import com.qa.locators.CheckoutModalLocators;
;

public class CheckoutModalPage extends CheckoutModalLocators {
	WebDriver driver=null;

	public CheckoutModalPage(WebDriver driver) {
		this.driver=driver;
	}


	public CheckoutPage clickContinueOnCart() {
		 driver.findElement(CONTINUE_ON_CART).click();
		 return new CheckoutPage(driver);
	}
	
	public String getModelCartMsg() {
		 
		 return driver.findElement(REGISTER_LOGIN_TO_PROCEED_MSG).getText();
	}


	public LoginPage clickLogin() {
		 driver.findElement(REGISTER_LOGIN).click();
		 return new LoginPage(driver);
	}
	

}
