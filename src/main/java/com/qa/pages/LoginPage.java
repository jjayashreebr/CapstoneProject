package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.locators.LoginPageLocators;

public class LoginPage extends LoginPageLocators {
	WebDriver driver=null;

	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}

	public String getTitle() {
		return driver.getTitle();
	}


	public String getValidationMsg() {
		return driver.findElement(SIGN_UP_ERROR).getText();
	}
	public WebElement getNameTextBox() {
		return driver.findElement(NAME_TEXT_BOX);
	}
	public WebElement getEmailTextBox() {
		return driver.findElement(EMAIL_TEXT_BOX);
	}

	public RegistrationPage clickCreateAccount() {
		 driver.findElement(SIGN_UP_BUTTON).click();
		 return new RegistrationPage(driver);
	}


}
