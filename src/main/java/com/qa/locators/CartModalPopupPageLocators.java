package com.qa.locators;

import org.openqa.selenium.By;

public class CartModalPopupPageLocators {
	public static final By VIEW_CART_BUTTON_IN_MODEL = By.xpath("//div [@id='cartModal'] //a[@href='/view_cart']");
	public static final By CONTINUE_BUTTON_IN_MODEL = By.xpath("//button[text()='Continue Shopping']");
}
