package com.qa.locators;

import org.openqa.selenium.By;

public class LoginPageLocators {

	public  static final By NAME_TEXT_BOX = By.xpath("//input[@data-qa=\"signup-name\"]");
	public  static final By EMAIL_TEXT_BOX = By.xpath("//input[@data-qa=\"signup-email\"]");
    public  static final By SIGN_UP_BUTTON = By.xpath("//button[@data-qa=\"signup-button\"]");
    public  static final By SIGN_UP_ERROR = By.xpath(" //p[contains(text(),'Email Address already exist!')]");
    
 
}
