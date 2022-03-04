package com.qa.testscripts;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.pages.AutomationExcerciseHomePage;
import com.qa.pages.ProductPage;
import com.qa.utils.BaseDriver;

import io.restassured.path.json.JsonPath;

public class BrandProductTest extends BaseDriver {

	WebDriver driver;

	/* verify whether all brands are listed */
	@Test(groups = { "integration", "brand","regression" })
	public void verifyAllBrandsAreListed() throws IOException {
		Map<String, String> header = new HashMap<>();
		header.put("Accept", "application/json");

		String response = given().relaxedHTTPSValidation().headers(header).when()
				.get("https://automationexercise.com/api/brandsList").then().assertThat().statusCode(200).extract()
				.response().asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		System.out.println(js.getInt("brands.size()"));
		int len = js.getInt("brands.size()");
		Set<String> myBrandList = new HashSet<String>();
		for (int i = 0; i < len; i++) {
			String temp = js.getString("brands[" + i + "].brand");
			if (!myBrandList.contains(temp)) {
				myBrandList.add(temp);
			}
		}

		driver = BaseDriver.getWebDriver();
		ProductPage page = new AutomationExcerciseHomePage(driver).open().clickProductLink();
		List<WebElement> myList = page.getBrandList();

		Assert.assertEquals(myList.size(), myBrandList.size());
	}

	/* verify whether number of products under each brands are matching the API */
	@Test(dataProvider = "brandNameList",groups = { "integration", "brand","regression" })
	public void verifyProductCountUnderEachBrands(String brand) throws IOException {
		Map<String, String> header = new HashMap<>();
		header.put("Accept", "application/json");

		String response = given().relaxedHTTPSValidation().headers(header).when()
				.get("https://automationexercise.com/api/brandsList").then().assertThat().statusCode(200).extract()
				.response().asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		System.out.println(js.getInt("brands.size()"));
		int len = js.getInt("brands.size()");
		int count = 0;
		// String brand="Polo";
		for (int i = 0; i < len; i++) {
			if (js.getString("brands[" + i + "].brand").equals(brand)) {
				count = count + 1;
			}
		}

		System.out.println(brand + " has the " + count);

		driver = BaseDriver.getWebDriver();
		ProductPage page = new AutomationExcerciseHomePage(driver).open().clickProductLink();

		String actualCount = page.getBrandListCount(brand);
	    Assert.assertEquals(actualCount, String.valueOf(count)) ;
    }

	@DataProvider(name = "brandNameList")
	public String[] getBrandName() {
		String[] list = { "Polo","Madame","HM"};
		return list;
	}
	
	/*3. Verify that brand in “polo” is clicked ,it should corresponding product page title.*/
	@Test(groups = { "frontend", "brand","regresssion" },dataProvider="brandTitleVerificationData")
	public void verifyBrandListing(String brand,String expectedTitle) throws IOException, InterruptedException {
		driver = BaseDriver.getWebDriver();
		ProductPage page = new AutomationExcerciseHomePage(driver)
				.open()
				.clickProductLink();
			
		String actualTitle=null;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
	 
	     actualTitle =  page
	    		 .getBrandProductPageTitle(brand)
	    		 .getPageTitleChange();
	     Assert.assertEquals(actualTitle, expectedTitle);
         }
	
	
	@DataProvider(name = "brandTitleVerificationData")
	public Object[][] brandTitleVerificationData() {

		Object[][] obj = { { "POLO", "BRAND - POLO PRODUCTS"} ,{"KOOKIE","BRAND - KOOKIE KIDS PRODUCTS"}};

		return obj;
	}

}
