package com.qa.locators;

import org.openqa.selenium.By;

public class ProductPageLocators {

	public  static final By SEARCH_TEXT_BOX = By.cssSelector("input#search_product");
	
	public  static final By SEARCH_BUTTON = By.cssSelector("button#submit_search");
   public static final By PAGE_TITLE_CHANGE = By.cssSelector("div.features_items h2.title.text-center");

  public static final By RESULT_LIST = By.xpath("//div[@id='cartModal']/following-sibling::div/div/div/div/p");
}
