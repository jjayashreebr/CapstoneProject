package com.qa.locators;

import org.openqa.selenium.By;

public class ViewCartPageLocators {

	public static final By PRODUCT_DETAIL_IN_VIEW_CART = By
			.cssSelector("table#cart_info_table tbody tr td:nth-child(2) h4");

	public static final By PRODUCT_DETAIL = By.cssSelector(".product-information h2");
	public static final By PRODUCT_QUANTITY_IN_VIEW_CART = By
			.cssSelector("table#cart_info_table tbody tr td:nth-child(4) button");
}
