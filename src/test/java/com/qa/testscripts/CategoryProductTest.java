package com.qa.testscripts;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.pages.AutomationExcerciseHomePage;
import com.qa.pages.CategoryProductPage;
import com.qa.pages.ProductPage;
import com.qa.utils.BaseDriver;


public class CategoryProductTest extends BaseDriver {

	WebDriver driver;

	/* 1.Verify that all categories are visible on left side bar Given : when user
	 * click on products link in homepage Then : It shows all the categories
	 */

	@Test(groups = { "frontend", "category" ,"regression"})
	public void verifyAllCategoryIsShown() throws IOException {
		 driver = BaseDriver.getWebDriver();
		ProductPage page = new AutomationExcerciseHomePage(driver).open().clickProductLink();
		List<WebElement> myList = page.getCategoryList();
		myList.stream().map(s -> s.getText()).forEach(System.out::println);
		Assert.assertTrue(myList.size() == 30);
	}

	/*
	 * 2. Verify that category “women” is clicked ,it expands and shows all sub
	 * categories Given : when user click on product link in homepage When : user
	 * clicks in a <category> Then: it shows all the subcategories
	 */

	@Test(groups = { "frontend", "category","regression" }, dataProvider = "categoryData")
	public void verifyAllSubCategoryIsShown(String category, String[] subCategory) throws IOException {
		driver = BaseDriver.getWebDriver();
		ProductPage page = new AutomationExcerciseHomePage(driver)
				.open()
				.clickProductLink();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		int actual= page.getSubCategoryCount(category);
		Assert.assertEquals(actual,subCategory.length);
    }

	/*
	 * 3. Verify that sub category in “women” is clicked ,it should corresponding
	 * search page title. Given : when user click on product link in homepage When :
	 * user clicks in a "subcategory" Then: it shows corresponding subcategory page
	 */

	@Test(groups = { "frontend", "category","regression" }, dataProvider = "subcategoryData")
	public void verifySubCategorySearch(String category, String subCategory, String expectedTitle) throws IOException, InterruptedException {
		driver = BaseDriver.getWebDriver();
		ProductPage page = new AutomationExcerciseHomePage(driver)
				.open()
				.clickProductLink();

		String actualTitle=null;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

	    CategoryProductPage cpage=page.getCategoryList( category, subCategory);
	    actualTitle= cpage.getCategoryPageTitle();
        Assert.assertEquals(actualTitle, expectedTitle);

	}



	@DataProvider(name = "subcategoryData")
	public Object[][] subcategoryData() {

		Object[][] obj = { { "WOMEN", "Dress" ,"WOMEN - DRESS PRODUCTS"} ,{"MEN","Jeans","MEN - JEANS PRODUCTS"}};

		return obj;
	}



	@DataProvider(name = "categoryData")
	public Object[][] categoryData() {

		HashMap<String, String[]> map = new HashMap<>();
		String[] arr1 = { "DRESS", "TOPS", "SAREE" };
		map.put("WOMEN", arr1);
		String[] arr2 = { "TSHIRTS", "JEANS" };
		map.put("MEN", arr2);
		String[] arr3 = { "DRESS", "TOPS & SHIRTS" };
		map.put("KIDS", arr3);
		Object[][] arr = new Object[map.size()][2];
		Set<Entry<String, String[]>> entries = map.entrySet();
		Iterator<Entry<String, String[]>> entriesIterator = entries.iterator();
		int i = 0;
		while (entriesIterator.hasNext()) {
			Map.Entry<String, String[]> mapping = entriesIterator.next();
			arr[i][0] = mapping.getKey();
			arr[i][1] = mapping.getValue();
			i++;
		}
		return arr;

	}

}
