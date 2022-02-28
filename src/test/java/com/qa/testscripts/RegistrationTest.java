package com.qa.testscripts;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import com.qa.pages.AutomationExcerciseHomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.RegistrationPage;
import com.qa.pages.SuccessAccountCreatedPage;
import com.qa.utils.BaseDriver;

public class RegistrationTest extends BaseDriver {
	
	
	/* Verify sign up link on home page takes to SignUp/Login Page */
	@Test
	public void verifyPageTitle() throws IOException {
		WebDriver driver = BaseDriver.getWebDriver();
		LoginPage lpage = new AutomationExcerciseHomePage(driver)
				.open()
				.clickSignInLink();
		
		Assert.assertEquals(lpage.getTitle(), getContent("loginpage", "pagetitle"));
	}


	/*
	 * Given: when sign up link is clicked in home page
	 *
	 * when: empty name and empty email is provided
	 *
	 * then: field validation error must be displayed
	 */
	@Test(groups = { "frontend" })
	public void verifySignUpWithEmptyCredentials() throws IOException {
		WebDriver driver = BaseDriver.getWebDriver();
		
		LoginPage lpage = new AutomationExcerciseHomePage(driver)
				.open()
				.clickSignInLink();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert softAssert = new SoftAssert();

		WebElement name = lpage.getNameTextBox();
		String actualUserNameValidationMessage = (String) js.executeScript("return arguments[0].validationMessage;",
				name);
		String expectedName = getContent("loginpage", "validation_msg");
		softAssert.assertEquals(actualUserNameValidationMessage, expectedName);

		WebElement email = lpage.getEmailTextBox();
		String actualEmailValidationMessage = (String) js.executeScript("return arguments[0].validationMessage;",
				email);
		String expectedEmail = getContent("loginpage", "validation_msg");
		softAssert.assertEquals(actualEmailValidationMessage, expectedEmail);

		softAssert.assertAll();
	}
	
	
	/*
	 * Given: when sign up link is clicked in home page
	 *
	 * when: valid name and invalid email with out @ is provided
	 *
	 * then: field validation error must be displayed
	 */
	@Test(groups = { "frontend" })
	public void verifyRegistartionWithInvalidEmail() throws IOException {
		WebDriver driver = BaseDriver.getWebDriver();
		
		LoginPage lpage = new AutomationExcerciseHomePage(driver)
				.open()
				.clickSignInLink();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
        lpage.getNameTextBox().sendKeys("James");
        
	    WebElement email = lpage.getEmailTextBox();
		email.sendKeys("JamesBond");
		
		lpage.clickCreateAccount();
		
		String actualEmailValidationMessage = (String) js.executeScript("return arguments[0].validationMessage;",
				email);
		String expectedEmail = getContent("loginpage", "email_validation_msg");
		
		Assert.assertTrue(actualEmailValidationMessage.contains(expectedEmail));
}
	
	/*
	 * Given: when sign up link is clicked in home page
	 *
	 * when: valid name and valid email is provided
	 *
	 * then: Sign up page must be displayed
	 * 
	 * when : valid mandatory information is provided
	 * 
	 * then : Account must be created successfully.He/she must be signed in.
	 */

	@Test(groups = { "frontend","regression"})
	public void verifyRegistartionWithValidCredentials() throws IOException, InterruptedException {
		WebDriver driver = BaseDriver.getWebDriver();
		
		LoginPage lpage = new AutomationExcerciseHomePage(driver)
				.open()
				.clickSignInLink();
	    String name="Test3";
	    String email ="Test3@test.com";
		
        lpage.getNameTextBox().sendKeys(name);
        lpage.getEmailTextBox().sendKeys(email);
		RegistrationPage rpage=lpage.clickCreateAccount();
		
		Assert.assertEquals(rpage.getTitle(), getContent("signuppage", "pagetitle"));
		
				
		Assert.assertEquals(rpage.getNameTextBox().getAttribute("value"),name);
		
		Assert.assertEquals(rpage.getEmailTextBox().getAttribute("value"),email);
		
		rpage.getPasswordTextBox().sendKeys("test@123");
		
		rpage.getFirstTNameTextBox().sendKeys("Test2");
				
		rpage.getLastNameTextBox().sendKeys("Brillio");
		
		rpage.getAddressTextBox().sendKeys("Abc street");
		
		Select select =new Select(rpage.getCountrySelectBox());
		select.selectByIndex(1);
		
		rpage.getStateTextBox().sendKeys("CA");
		
		rpage.getCityTextBox().sendKeys("Santa Clara");
		
		rpage .getZipcodeTextBox().sendKeys("95051");
		
		rpage.getMobileTextBox().sendKeys("123456789");
		
		SuccessAccountCreatedPage success=rpage.clickCreateAccount();
		
		System.out.println(success.getSuccessMsgBox());
		
	}
	
	
	/* 
	 * Given :https://automationexercise.com/api/createAccount
	 * 
	 * when: given with valid credentials 
	 * 
	 * then : should create account and respond with status 201.
	 * 
	 * Given:https://automationexercise.com/api/getUserDetailByEmail
	 * 
	 * when : with created email as query parameter
	 * 
	 * then : status must be 200 with User details in JSON form.
	 */
	@Test 
	public void verifyUserCreation() {
		
		String response = given().relaxedHTTPSValidation().queryParam("email", "Test3@test.com")
				.when().get("https://automationexercise.com/api/getUserDetailByEmail")
				.then().assertThat().statusCode(200).extract()
				.response().asString();
		System.out.println(response);
	}
	
	
	
	
	
}
