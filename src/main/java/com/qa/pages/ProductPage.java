package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.locators.ProductPageLocators;

public class ProductPage extends ProductPageLocators {
	WebDriver driver=null;

	public ProductPage(WebDriver driver) {
		this.driver=driver;
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public WebElement getSearchTextBox() {
		return driver.findElement(SEARCH_TEXT_BOX);
	}

	
	public String getPageTitleChange() {
		return driver.findElement(PAGE_TITLE_CHANGE).getText();
	}

	public ProductPage clickSearch() {
		 driver.findElement(SEARCH_BUTTON).click();
		 return new ProductPage(driver);
	}


}
