package com.qa.locators;

import org.openqa.selenium.By;

public class RegistrationPageLocators {

	public static final By MR_RADIO_BUTTON = By.id("id_gender1");
	public static final By MRS_RADIO_BUTTON = By.id("uniform-id_gender2");
	public static final By CUSTOMER_NAME = By.id("name");

	public static final By EMAIL = By.id("email");

	public static final By PASSWORD = By.id("password");
	public static final By DAYS = By.id("days");
	public static final By MONTHS = By.id("months");
	public static final By YEARS = By.id("years");

	public static final By NEWSLETTER_SIGNUP = By.id("newsletter");
	public static final By SPECIAL_OFFER = By.id("uniform-optin");
	public static final By FIRST_NAME = By.id("first_name");
	public static final By LAST_NAME = By.id("last_name");

	public static final By COMPANY = By.id("company");
	public static final By ADDDRESS_1 = By.id("address1");
	public static final By ADDRESS_2 = By.id("address2");

	public static final By CITY = By.id("city");
	public static final By STATE = By.id("state");
	public static final By ZIPCODE = By.id("zipcode");

	public static final By COUNTRY = By.id("country");
	public static final By HOME_PHONE = By.id("phone");
	public static final By MOBILE_PHONE = By.id("mobile_number");
	public static final By CREATE_ACCOUNT = By.xpath("//button[@data-qa='create-account']");

}
