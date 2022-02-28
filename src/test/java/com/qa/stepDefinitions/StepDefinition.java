package com.qa.stepDefinitions;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



import org.testng.Assert;

import com.qa.testrunner.TestRunner;


public class StepDefinition{
	@Given("User is on Product page")
	public void user_is_on_product_page() {
	    // Write code here that turns the phrase above into concrete actions
	   // throw new io.cucumber.java.PendingException();
	}
	
	@When("User enters search as {string}")
	public void user_enters_search_as(String string) {
	    // Write code here that turns the phrase above into concrete actions
	   // throw new io.cucumber.java.PendingException();
	}
	
	@Then("User should be able to login sucessfully")
	public void user_should_be_able_to_login_sucessfully() {
	    // Write code here that turns the phrase above into concrete actions
	   // throw new io.cucumber.java.PendingException();
		Assert.assertEquals(true, true);
	}
  
}


   