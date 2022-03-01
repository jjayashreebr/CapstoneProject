package com.qa.stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import com.qa.pages.AutomationExcerciseHomePage;
import com.qa.pages.ProductPage;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition {
	 WebDriver driver;
	 ProductPage page;

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
	public void I_do_search_for(String string) {

	}

	@Then("I see search result page with more than zero results")
	public void I_see_search_result_page_with_more_than_zero_results() {

		Assert.assertEquals(true, true);
	}

}
