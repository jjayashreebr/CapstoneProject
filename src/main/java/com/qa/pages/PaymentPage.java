package com.qa.pages;

import org.openqa.selenium.WebDriver;

public class PaymentPage {

	WebDriver driver=null;

	public PaymentPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
}
