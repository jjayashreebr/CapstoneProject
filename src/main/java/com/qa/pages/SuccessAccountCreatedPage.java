package com.qa.pages;

import org.openqa.selenium.WebDriver;

import com.qa.locators.SuccessAccountCreatedPageLocators;

public class SuccessAccountCreatedPage extends SuccessAccountCreatedPageLocators {

	WebDriver driver=null;
	public SuccessAccountCreatedPage(WebDriver driver) {
		this.driver=driver;
	}


	public String getSuccessMsgBox() {
		return driver.findElement(SUCCESS_TEXT).getText();
	}
	
	public AutomationExcerciseHomePage clickContinueButton() {
		 driver.findElement(CONTINUE_BUTTON).click();
		 return new AutomationExcerciseHomePage(driver);
	}
}
