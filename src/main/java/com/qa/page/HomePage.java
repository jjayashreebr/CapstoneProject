package com.qa.page;

import org.openqa.selenium.WebDriver;


import com.qa.locators.HomePageLocators;

public class HomePage extends HomePageLocators {
	WebDriver driver=null;
	public HomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	public LoginPage clickSignInLink() {
	 	driver.findElement(LOGIN_LINK).click();
	 	return new LoginPage(driver);
	}
	

}
