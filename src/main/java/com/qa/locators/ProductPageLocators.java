package com.qa.locators;

import org.openqa.selenium.By;

public class ProductPageLocators {

	public  static final By NAME_TEXT_BOX = By.xpath("//input[@data-qa=\"signup-name\"]");
	public  static final By EMAIL_TEXT_BOX = By.xpath("//input[@data-qa=\"signup-email\"]");
    public  static final By SIGN_UP_BUTTON = By.xpath("//button[@data-qa=\"signup-button\"]");
}
