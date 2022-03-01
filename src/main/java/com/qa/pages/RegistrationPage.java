package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.locators.RegistrationPageLocators;

public class RegistrationPage extends RegistrationPageLocators {
	WebDriver driver=null;
	public RegistrationPage(WebDriver driver) {
		this.driver=driver;
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public WebElement getEmailTextBox() {
		return driver.findElement(EMAIL);
	}

	public WebElement getNameTextBox() {
		return driver.findElement(CUSTOMER_NAME);
	}
	public WebElement getFirstTNameTextBox() {
		return driver.findElement(FIRST_NAME);
	}
	public WebElement getLastNameTextBox() {
		return driver.findElement(LAST_NAME);
	}
	public WebElement getCountrySelectBox() {
		return driver.findElement(COUNTRY);
	}
	public WebElement getStateTextBox() {
		return driver.findElement(STATE);
	}

	public WebElement getAddressTextBox() {
		return driver.findElement(ADDDRESS_1);
	}

	public WebElement getCityTextBox() {
		return driver.findElement(CITY);
	}

	public WebElement getMobileTextBox() {
		return driver.findElement(MOBILE_PHONE);
	}

	public WebElement getZipcodeTextBox() {
		return driver.findElement(ZIPCODE);
	}


	public WebElement getPasswordTextBox() {
		return driver.findElement(PASSWORD);
	}
	public WebElement getMonthSelectorBox() {
		return driver.findElement(MONTHS);
	}
	public WebElement getDaySelectBox() {
		return driver.findElement(DAYS);
	}
	public WebElement getYearSelectBox() {
		return driver.findElement(YEARS);
	}

	public WebElement getSpecialOfferCheckBox() {
		return driver.findElement(SPECIAL_OFFER);
	}
	public WebElement getNewsletterSignUpBox() {
		return driver.findElement(NEWSLETTER_SIGNUP);
	}


	public SuccessAccountCreatedPage clickCreateAccount() {
		 driver.findElement(CREATE_ACCOUNT).click();
		 return new SuccessAccountCreatedPage(driver);
	}


}
