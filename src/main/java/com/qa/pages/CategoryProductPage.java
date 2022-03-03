package com.qa.pages;

import org.openqa.selenium.WebDriver;
import com.qa.locators.CategoryProductPageLocators;


public class CategoryProductPage extends CategoryProductPageLocators {
	WebDriver driver=null;

	public CategoryProductPage(WebDriver driver) {
		this.driver=driver;
	}



	public String getCategoryPageTitle() {
		return driver.findElement(CATEGORY_PRODUCT_LIST_TITLE).getText();
	}
	

}
