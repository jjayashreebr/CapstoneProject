package com.qa.testscripts;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.pages.AutomationExcerciseHomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductPage;
import com.qa.pages.ViewCartPage;
import com.qa.pages.ViewItemPage;
import com.qa.utils.BaseDriver;

public class AddtoCartTest extends BaseDriver {
	WebDriver driver;

	@Test(groups= {"cart"})
	public void verifyAddtoCartFromProductsPage() throws IOException {
		driver = BaseDriver.getWebDriver();
		ProductPage productPage = new AutomationExcerciseHomePage(driver).open().clickProductLink();
		String expectedProduct = productPage.getProductName();

		System.out.println(expectedProduct);
		ViewCartPage viewCartPage=productPage
				.clickAddtoCart()
				.waitForModalPopUpPage()
				.clickViewCart();
		Assert.assertTrue(viewCartPage.verifyContainsProduct(expectedProduct));
      }

	@Test(groups= {"cart"})
	public void verifyAddtoCartFromViewItem() throws IOException {
		driver = BaseDriver.getWebDriver();
		ProductPage productPage = new AutomationExcerciseHomePage(driver).open().clickProductLink();

		ViewItemPage viewItemPage = productPage.clickViewItemOfSeondProduct();
		String expectedProduct = viewItemPage.getProductName();
		System.out.println(expectedProduct);

		ViewCartPage viewCartPage=viewItemPage
				.clickAddtoCart()
				.waitForModalPopUpPage()
				.clickViewCart();

		String actualProduct = viewCartPage.getProductName();
        Assert.assertEquals(actualProduct, expectedProduct);
	}

	@Test(groups= {"cart"})
	public void verifyAddtoCartByAddingMultipleQuantities() throws IOException {
		driver = BaseDriver.getWebDriver();
		ProductPage productPage = new AutomationExcerciseHomePage(driver).open().clickProductLink();
        ViewItemPage viewItemPage = productPage
        		.clickViewItemOfSeondProduct();
		String expectedProduct = viewItemPage.getProductName();
		viewItemPage.setProductQuantity("2");
		System.out.println(expectedProduct);
		String expectedQuantity ="2";
		ViewCartPage viewCartPage=viewItemPage
				.clickAddtoCart()
				.waitForModalPopUpPage()
				.clickViewCart();
		String actualProduct = viewCartPage.getProductName();
		String actualQuantity =viewCartPage.getProductQuantity();
		Assert.assertEquals(actualQuantity,expectedQuantity);
		Assert.assertEquals(actualProduct, expectedProduct);
	}

	@Test(groups= {"cart"})
	public void verifyAddtoCartByAddingSameProductMultiplyTimes() throws IOException {
		driver = BaseDriver.getWebDriver();
		ProductPage productPage = new AutomationExcerciseHomePage(driver).open().clickProductLink();

		ViewItemPage viewItemPage = productPage.clickViewItemOfSeondProduct();
		String expectedProduct = viewItemPage.getProductName();
		System.out.println(expectedProduct);

		ViewCartPage viewCartPage=	viewItemPage
		.clickAddtoCart()
		.waitForModalPopUpPage()
		.clickContinueShopping()
		.waitForViewItemPage()
		.clickAddtoCart()
		.waitForModalPopUpPage()
		.clickViewCart();

        String actualProduct = viewCartPage.getProductName();
		String quantity =viewCartPage.getProductQuantity();
		
		Assert.assertEquals(quantity,String.valueOf(2));
		Assert.assertEquals(actualProduct, expectedProduct);
	}

	@Test(groups= {"cart"})
	public void verifyAddtoCartByAddingZeroQuantities() throws IOException {
		driver = BaseDriver.getWebDriver();
		ProductPage page = new AutomationExcerciseHomePage(driver).open().clickProductLink();

		ViewItemPage viewItemPage = page.clickViewItemOfSeondProduct();
		String expectedProduct = viewItemPage.getProductName();
		System.out.println(expectedProduct);
		
		viewItemPage.getProductQuantity().clear();
		viewItemPage.clickAddtoCart();
		
		boolean bst = isClickable(viewItemPage.getAddtoCartEle(),driver);
		System.out.println(bst); 
		
	}
	
	public static boolean isClickable(WebElement webe, WebDriver driver){
		try
		{ 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5));
		wait.until(ExpectedConditions.elementToBeClickable(webe));
		   return true;
		 }
		catch (Exception e)
		{
		return false;
		 }
	}
	
	@Test(groups= {"cart","regression"})
	public void verifyAddtoCartAsASignedInCustomer() throws IOException {
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

		ViewCartPage viewCartPage =viewItemPage
				.clickAddtoCart()
				.waitForModalPopUpPage()
				.clickViewCart();
				
	Assert.assertTrue(viewCartPage.verifyContainsProduct(expectedProduct));
		
	}
	
	
	@Test(groups= {"cart","regression"})
	public void verifyAddToCartAsGuestCustomer() throws IOException {
		driver = BaseDriver.getWebDriver();
		String email = "xx@x";
		String password = "xxxxx";
	
		ProductPage productPage =new AutomationExcerciseHomePage(driver).open().clickProductLink();

		ViewItemPage viewItemPage = productPage.clickViewItemOfSeondProduct();
		String expectedProduct = viewItemPage.getProductName();
		System.out.println(expectedProduct);

		viewItemPage
				.clickAddtoCart()
				.waitForModalPopUpPage()
				.clickViewCart();
		
		LoginPage loginPage = new AutomationExcerciseHomePage(driver)
				 .open()
				 .clickSignInLink()
				 .getLoginModule();
		 
		 AutomationExcerciseHomePage homepage=
				 loginPage.setEmailLoginTextBox(email)
		                  .setPasswordTextBox(password)
		                  .clickLogin();
		 
		 ViewCartPage viewCartPage=homepage.clickCart();
		 Assert.assertTrue(viewCartPage.verifyContainsProduct(expectedProduct));
	}

}
