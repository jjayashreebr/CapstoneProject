package com.qa.locators;

import org.openqa.selenium.By;



public class AutomationExcerciseHomePageLocators {
	public final static By LOGIN_SIGNUP_LINK = By.xpath("//a[contains(@href,\"login\")]");
	public final static By PRODUCT_LINK = By.xpath("//a[contains(@href,\"products\")]");
	public final static By LOGGED_IN_TEXT = By.cssSelector(".fa.fa-user+b");
	public final static By LOGOUT_LINK = By.xpath("//a[contains(@href,\"logout\")]");
	
}
