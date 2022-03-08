package com.qa.testscripts;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.pages.AutomationExcerciseHomePage;
import com.qa.pages.CheckoutPage;
import com.qa.pages.LoginPage;
import com.qa.pages.PaymentPage;
import com.qa.pages.ProductPage;
import com.qa.pages.ViewCartPage;
import com.qa.pages.ViewItemPage;
import com.qa.resources.BaseDriver;

public class CheckoutTest extends BaseDriver {
	WebDriver driver;
	@Test(groups= {"cart","regression"})
	public void verifyCartAsSignedInCustomer() throws IOException {
		driver = BaseDriver.getWebDriver();
		String email = "xx@x";
		String password = "xxxxx";
		LoginPage loginPage = new AutomationExcerciseHomePage(driver)
				 .open()
				 .clickSignInLink()
				 .getLoginModule();
		 AutomationExcerciseHomePage homepage=
				 loginPage.setEmailLoginTextBox(email)
		                  .setPasswordTextBox(password)
		                  .clickLogin();
		ProductPage productPage =homepage.open().clickProductLink();

		ViewItemPage viewItemPage = productPage.clickViewItemOfSeondProduct();
		
		String expectedProduct = viewItemPage.getProductName();
		System.out.println(expectedProduct);
		ViewCartPage viewCartPage=viewItemPage
				.clickAddtoCart()
				.waitForModalPopUpPage()
				.clickViewCart();

		CheckoutPage checkOutPage=viewCartPage.clickProceedToCheckout();
		windowScroll(driver);
		
		String actualProduct=checkOutPage.getProductDescription();
		Assert.assertEquals(actualProduct, expectedProduct);
	}
	
	public void windowScroll(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
	}
	
	@Test(groups= {"cart","regression"})
	public void verifyCartAsGuestCustomer() throws IOException {
		driver = BaseDriver.getWebDriver();
					
		ProductPage productPage =new AutomationExcerciseHomePage(driver)
				.open()
				.clickProductLink();

		ViewItemPage viewItemPage = productPage.clickViewItemOfSeondProduct();
		ViewCartPage viewCartPage=viewItemPage
				.clickAddtoCart()
				.waitForModalPopUpPage()
				.clickViewCart();

	     String signinMsg=viewCartPage
	     .clickProceedToCheckoutWithOutSignin()
	     .waitForModalCartPopup()
	     .getModelCartMsg();
	     
	     Assert.assertEquals(signinMsg, getContent("viewcart","signinmsgvalidation"));
	    }
	
	@Test(groups= {"cart","regression"})
	public void verifyPlaceOrder() throws IOException {
		driver = BaseDriver.getWebDriver();
		String email = "xx@x";
		String password = "xxxxx";
		LoginPage loginPage = new AutomationExcerciseHomePage(driver)
				 .open()
				 .clickSignInLink()
				 .getLoginModule();
		 AutomationExcerciseHomePage homepage=
				 loginPage.setEmailLoginTextBox(email)
		                  .setPasswordTextBox(password)
		                  .clickLogin();
		ProductPage productPage =homepage.open().clickProductLink();

		ViewItemPage viewItemPage = productPage.clickViewItemOfSeondProduct();
		
		ViewCartPage viewCartPage=viewItemPage
				.clickAddtoCart()
				.waitForModalPopUpPage()
				.clickViewCart();

		CheckoutPage checkOutPage=viewCartPage.clickProceedToCheckout();
		windowScroll(driver);
		
		PaymentPage paymentPage =checkOutPage.clickPlaceOrder();
		
		Assert.assertEquals(paymentPage.getTitle(), getContent("payment","pagetitle"));
	
	}
	


}
