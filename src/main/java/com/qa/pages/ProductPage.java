package com.qa.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.locators.ProductPageLocators;

public class ProductPage extends ProductPageLocators {
	WebDriver driver=null;

	public ProductPage(WebDriver driver) {
		this.driver=driver;
	}

	public WebElement getSearchTextBox() {
		return driver.findElement(SEARCH_TEXT_BOX);
	}

	public List<WebElement> getProductList(){
		return driver.findElements(RESULT_LIST);
	}
	
	public String getPageTitleChange() {
		return driver.findElement(PAGE_TITLE_CHANGE).getText();
	}

	public ProductPage clickSearch() {
		 driver.findElement(SEARCH_BUTTON).click();
		 return new ProductPage(driver);
	}

	public WebElement getSearchButton() {
		return driver.findElement(SEARCH_BUTTON);
	}

}
