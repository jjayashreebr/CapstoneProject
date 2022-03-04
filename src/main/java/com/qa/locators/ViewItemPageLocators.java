package com.qa.locators;

import org.openqa.selenium.By;

public class ViewItemPageLocators {

	public static final By VIEW_CART_BUTTON_IN_MODEL = By.xpath("//div [@id='cartModal'] //a[@href='/view_cart']");

	public static final By PRODUCT_DETAIL_VIEW_ITEM = By.cssSelector(".product-information h2");
	public static final By ADD_TO_CART_VIEW_ITEM = By.cssSelector("button.btn.btn-default.cart");
	public static final By QUANTITY_VIEW_ITEM = By.id("quantity");
	public static final By CONTINUE_BUTTON_IN_MODEL = By.xpath("//button[text()='Continue Shopping']");
}
