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
import com.qa.pages.ProductPage;
import com.qa.pages.ViewCartPage;
import com.qa.pages.ViewItemPage;
import com.qa.utils.BaseDriver;

public class AddtoCartTest extends BaseDriver {
	WebDriver driver;

	@Test
	public void verifyAddtoCartFromProductsPage() throws IOException {
		driver = BaseDriver.getWebDriver();
		ProductPage page = new AutomationExcerciseHomePage(driver).open().clickProductLink();
		String expectedProduct = page.getProductName();

		System.out.println(expectedProduct);
		page.clickAddtoCart();
		System.out.println(driver.getTitle());

		driver.switchTo().activeElement();
		ViewCartPage vpage = page.clickViewCart();
		System.out.println(driver.getTitle());

		String actualProduct = vpage.getProductName();

		System.out.println(actualProduct);

		Assert.assertEquals(actualProduct, expectedProduct);

	}

	@Test
	public void verifyAddtoCartFromViewItem() throws IOException {
		driver = BaseDriver.getWebDriver();
		ProductPage page = new AutomationExcerciseHomePage(driver).open().clickProductLink();

		ViewItemPage vipage = page.clickViewItem();
		String expectedProduct = vipage.getProductName();
		System.out.println(expectedProduct);

		vipage.clickAddtoCart();
		System.out.println(driver.getTitle());
		driver.switchTo().activeElement();

		ViewCartPage vpage = vipage.clickViewCart();
		System.out.println(driver.getTitle());

		String actualProduct = vpage.getProductName();

		System.out.println(actualProduct);

		Assert.assertEquals(actualProduct, expectedProduct);
	}

	@Test
	public void verifyAddtoCartByAddingMultipleQuantities() throws IOException {
		driver = BaseDriver.getWebDriver();
		ProductPage page = new AutomationExcerciseHomePage(driver).open().clickProductLink();

		ViewItemPage vipage = page.clickViewItem();
		String expectedProduct = vipage.getProductName();
		System.out.println(expectedProduct);
		
		vipage.getProductQuantity().clear();
		vipage.getProductQuantity().sendKeys("2");
		vipage.clickAddtoCart();
		
		System.out.println(driver.getTitle());
		driver.switchTo().activeElement();
		ViewCartPage vpage = vipage.clickViewCart();
		System.out.println(driver.getTitle());
		
		String actualProduct = vpage.getProductName();
		
		String quantity =vpage.getProductQuantity();

		System.out.println(quantity);
		Assert.assertEquals(quantity,2);
		Assert.assertEquals(actualProduct, expectedProduct);
	}

	@Test
	public void verifyAddtoCartByAddingSameProductMultiplyTimes() throws IOException {
		driver = BaseDriver.getWebDriver();
		ProductPage page = new AutomationExcerciseHomePage(driver).open().clickProductLink();

		ViewItemPage vipage = page.clickViewItem();
		String expectedProduct = vipage.getProductName();
		System.out.println(expectedProduct);

		vipage.clickAddtoCart();
		
		driver.switchTo().activeElement();
		vipage.clickContinueShopping();

		driver.switchTo().defaultContent();
		vipage.clickAddtoCart();

		driver.switchTo().activeElement();
		vipage.clickContinueShopping();

		driver.switchTo().defaultContent();
		vipage.clickAddtoCart();

		driver.switchTo().activeElement();
		ViewCartPage vpage = vipage.clickViewCart();
		System.out.println(driver.getTitle());
		
		String actualProduct = vpage.getProductName();
		
		String quantity =vpage.getProductQuantity();

		Assert.assertEquals(quantity,3);
		Assert.assertEquals(actualProduct, expectedProduct);
	}

	@Test
	public void verifyAddtoCartByAddingZeroQuantities() throws IOException {
		driver = BaseDriver.getWebDriver();
		ProductPage page = new AutomationExcerciseHomePage(driver).open().clickProductLink();

		ViewItemPage vipage = page.clickViewItem();
		String expectedProduct = vipage.getProductName();
		System.out.println(expectedProduct);
		
		vipage.getProductQuantity().clear();
		vipage.clickAddtoCart();
		
		boolean bst = isClickable(vipage.getAddtoCartEle(),driver);
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

}
