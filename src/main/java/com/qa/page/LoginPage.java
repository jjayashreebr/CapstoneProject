package com.qa.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.locators.LoginPageLocators;

public class LoginPage extends LoginPageLocators {
	WebDriver driver=null;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public WebElement getEmailTextBox() {
		return driver.findElement(EMAIL_TEXT_BOX);
	}
	
	public RegistrationPage clickCreateAccount() {
		 driver.findElement(CREATE_ACCOUNT_BUTTON).click();
		 return new RegistrationPage(driver);
	}
	

}
