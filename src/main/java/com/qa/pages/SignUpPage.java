package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.locators.LoginPageLocators;

public class SignUpPage extends LoginPageLocators {
	WebDriver driver=null;

	public SignUpPage(WebDriver driver) {
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

	public SignUpPage setNameTextBox(String string) {
		driver.findElement(NAME_TEXT_BOX).sendKeys(string);
		return new SignUpPage(driver);
	}

	public SignUpPage setEmailTextBox(String string) {
		driver.findElement(EMAIL_TEXT_BOX).sendKeys(string);
		return new SignUpPage(driver);
		
	}




}
