package com.qa.locators;

import org.openqa.selenium.By;

public class ProductPageLocators {
	public static final By ADD_TO_CART_BUTTON =By.xpath("//*[@data-product-id='1']");
	public static final By VIEW_ITEM_BUTTON=By.xpath("//*[@href='/product_details/2']");
	
	public static final By VIEW_CART_BUTTON_IN_MODEL =By.xpath("//div [@id='cartModal'] //a[@href='/view_cart']");

	public static final By SEARCH_TEXT_BOX = By.cssSelector("input#search_product");

	public static final By SEARCH_BUTTON = By.cssSelector("button#submit_search");
	public static final By PAGE_TITLE_CHANGE = By.cssSelector("div.features_items h2.title.text-center");

	public static final By RESULT_LIST = By.xpath("//div[@id='cartModal']/following-sibling::div/div/div/div/p");

	public static final By PRODUCT_NAME= By
			.cssSelector("div.features_items div.col-sm-4  div.productinfo.text-center p");
	public static final By BRAND_LIST = By.xpath("//*[contains(@href,'/brand_products')]");
	public static final By CATEGORY_LIST = By.xpath("//h2[text()='Category']/following-sibling::div[@id='accordian']  //h4/a");
}
