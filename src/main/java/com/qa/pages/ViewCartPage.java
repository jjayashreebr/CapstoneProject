package com.qa.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.locators.ViewCartPageLocators;

public class ViewCartPage extends ViewCartPageLocators {
	WebDriver driver=null;

	public ViewCartPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public String getProductName() {
		return driver.findElement(PRODUCT_DETAIL_IN_VIEW_CART).getText();
	}
	public String getProductQuantity() {
		return driver.findElement(PRODUCT_QUANTITY_IN_VIEW_CART).getText();
	}
	
	public CheckoutPage clickProceedToCheckout() {
		driver.findElement(PROCEED_TO_CHECKOUT).click();
		return new CheckoutPage(driver);
	}
	
	public ViewCartPage clickProceedToCheckoutWithOutSignin() {
		driver.findElement(PROCEED_TO_CHECKOUT).click();
		return new ViewCartPage(driver);
	}
	
	public CheckoutModalPage waitForModalCartPopup() {
		driver.switchTo().activeElement();
		return new CheckoutModalPage(driver);
	}

	public boolean verifyContainsProduct(String expectedProduct) {
		List<WebElement> myList= driver.findElements(PRODUCT_DETAIL_IN_VIEW_CART);
		List<String> myNewList=myList.stream().map(s->s.getText())
		.filter(s->s.equals(expectedProduct))
		.collect(Collectors.toList());
		return myNewList.contains(expectedProduct);
	}

}
