package com.qa.stepDefinitions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import com.qa.pages.AutomationExcerciseHomePage;
import com.qa.pages.ProductPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition {
	 WebDriver driver;
	 ProductPage page;
	 List<WebElement> myList;
	 String placeholder;
	 Boolean flag=false;

	 @Before
	    public void setUp() {
		    String driverpath = System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver", driverpath + "/webdriver/chromedriver.exe");
			ChromeOptions options1 = new ChromeOptions();
			options1.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(options1);
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	    }

	@Given("I am  on Product page to do a single keyword search")
	public void I_am_on_Product_page_to_do_a_single_keyword_search() {
		//WebDriver driver = BaseDriver.getWebDriver();

		page = new AutomationExcerciseHomePage(driver)
				.open()
				.clickProductLink();

	}

	@When("I do search for {string}")
	public void I_do_search_for(String searchText) {
		page.getSearchTextBox().sendKeys(searchText);
		page.clickSearch();
		myList = page.getProductList();
		//myList.stream().map(s->s.getText()).forEach(System.out::println);
		}
	
	@When("I click on search text box")
	public void I_click_on_search_text_box(){
		placeholder=page.getSearchTextBox().getAttribute("placeholder");
	
	}

	@Then("I see search result page with more than zero results")
	public void I_see_search_result_page_with_more_than_zero_results() {

		Assert.assertEquals(page.getPageTitleChange(), "SEARCHED PRODUCTS");
		Assert.assertTrue(myList.size()>=1);
	}
	
	

	@And("I click on search button")
	public void I_click_on_search_button(){
		flag=page.getSearchButton().isEnabled();
	}
	
	@Then("correct validation message must be shown")
	public void correct_validation_message_must_be_shown() {

		Assert.assertEquals(page.getPageTitleChange(), "SEARCHED PRODUCTS");
		Assert.assertTrue(myList.size()<=0);
	}
	
	@Then("Search text must have placeholder and Search button must be clickable")
	public void Search_text_must_have_placeholder_and_Search_button_must_be_clickable() {
		Assert.assertTrue(flag);
		Assert.assertEquals(placeholder, "Search Product");
	}
	
	//ALL PRODUCTS
	
	@Then("All products must be shown")
	public void All_products_must_be_shown() {

		Assert.assertEquals(page.getPageTitleChange(), "ALL PRODUCTS");
		Assert.assertTrue(myList.size()>=0);
	}
	
	 @After
	    public void teardown() {
	 
	        driver.quit();
	    }

}
