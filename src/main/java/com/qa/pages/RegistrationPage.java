package com.qa.pages;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.Select;

import com.qa.locators.RegistrationPageLocators;

public class RegistrationPage extends RegistrationPageLocators {
	WebDriver driver=null;
	public RegistrationPage(WebDriver driver) {
		this.driver=driver;
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public RegistrationPage setEmailTextBox(String email) {
		 driver.findElement(EMAIL).sendKeys(email);
		 return new RegistrationPage(driver);
	}


	
	
	public RegistrationPage setNameTextBox(String customerName) {
		 driver.findElement(CUSTOMER_NAME).sendKeys(customerName);
		 return new RegistrationPage(driver);
	}
	public RegistrationPage setFirstTNameTextBox(String firstName) {
		 driver.findElement(FIRST_NAME).sendKeys(firstName);
		 return new RegistrationPage(driver);
	}
	public RegistrationPage setLastNameTextBox(String lastName) {
		 driver.findElement(LAST_NAME).sendKeys(lastName);
		return new RegistrationPage(driver);
	}
	public RegistrationPage setCountrySelectBox(String country) {
		Select select = new Select(driver.findElement(COUNTRY));
		if(country.equalsIgnoreCase("USA"))
		    {select.selectByIndex(1);}
		 return new RegistrationPage(driver);
	}
	public RegistrationPage setStateTextBox(String state) {
		 driver.findElement(STATE).sendKeys(state);;
		return new RegistrationPage(driver);
	}

	public RegistrationPage setAddressTextBox(String address) {
		 driver.findElement(ADDDRESS_1).sendKeys(address);
		 return new RegistrationPage(driver);
	}

	public RegistrationPage setCityTextBox(String city) {
		 driver.findElement(CITY).sendKeys(city);
		return new RegistrationPage(driver);
	}

	public RegistrationPage setMobileTextBox(String mobile) {
		 driver.findElement(MOBILE_PHONE).sendKeys(mobile);
		return new RegistrationPage(driver);
	}

	public RegistrationPage setZipcodeTextBox(String zip) {
		 driver.findElement(ZIPCODE).sendKeys(zip);
		return new RegistrationPage(driver);
	}


	public RegistrationPage setPasswordTextBox(String password) {
		 driver.findElement(PASSWORD).sendKeys(password);;
		return new RegistrationPage(driver);
	}
	public RegistrationPage getMonthSelectorBox() {
		 driver.findElement(MONTHS);
		return new RegistrationPage(driver);
	}
	public RegistrationPage getDaySelectBox() {
		 driver.findElement(DAYS);
		return new RegistrationPage(driver);
	}
	public RegistrationPage getYearSelectBox() {
		 driver.findElement(YEARS);
		return new RegistrationPage(driver);
	}

	public RegistrationPage getSpecialOfferCheckBox() {
		 driver.findElement(SPECIAL_OFFER);
		 return new RegistrationPage(driver);
	}
	public RegistrationPage getNewsletterSignUpBox() {
		 driver.findElement(NEWSLETTER_SIGNUP);
		 return new RegistrationPage(driver);
	}


	public SuccessAccountCreatedPage clickCreateAccount() {
		 driver.findElement(CREATE_ACCOUNT).click();
		 return new SuccessAccountCreatedPage(driver);
	}


}
