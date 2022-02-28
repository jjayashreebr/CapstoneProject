package com.qa.pages;

import org.openqa.selenium.WebDriver;

import com.qa.locators.AutomationExcerciseHomePageLocators;


public class AutomationExcerciseHomePage extends AutomationExcerciseHomePageLocators {
	WebDriver driver=null;
	public AutomationExcerciseHomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	public AutomationExcerciseHomePage open() {
		driver.get("https://www.automationexercise.com/");
		
		return this;
	}

	public String getTitle() {
		return driver.getTitle();
	}
	
	public LoginPage clickSignInLink() {
	 	driver.findElement(LOGIN_SIGNUP_LINK).click();
	 	return new LoginPage(driver);
	}


}
