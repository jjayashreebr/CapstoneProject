package com.qa.testscripts;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.pages.AutomationExcerciseHomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.RegistrationPage;
import com.qa.pages.SuccessAccountCreatedPage;
import com.qa.utils.BaseDriver;
import com.qa.utils.BodyConstruction;

public class RegistrationTest extends BaseDriver {

	/* Verify sign up link on home page takes to SignUp/Login Page */
	@Test
	public void verifyPageTitle() throws IOException {
		WebDriver driver = BaseDriver.getWebDriver();
		LoginPage lpage = new AutomationExcerciseHomePage(driver).open().clickSignInLink();

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

		LoginPage lpage = new AutomationExcerciseHomePage(driver).open().clickSignInLink();

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

		LoginPage lpage = new AutomationExcerciseHomePage(driver).open().clickSignInLink();

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
	 *
	 * And : Delete the Account using API for clean up.
	 */

	@Test(dataProvider = "registrationData",groups = { "frontend", "regression" })
	public void verifyRegistartionWithValidCredentials(HashMap<String, Object> map) throws IOException, InterruptedException {

		WebDriver driver = BaseDriver.getWebDriver();

		LoginPage lpage = new AutomationExcerciseHomePage(driver).open().clickSignInLink();
		String name = map.get("name").toString();
		String email = map.get("email").toString();
		String firstName = map.get("firstname").toString();
		String password =map.get("password").toString();

		lpage.getNameTextBox().sendKeys(name);
		lpage.getEmailTextBox().sendKeys(email);
		RegistrationPage rpage = lpage.clickCreateAccount();

		Assert.assertEquals(rpage.getTitle(), getContent("signuppage", "pagetitle"));

		Assert.assertEquals(rpage.getNameTextBox().getAttribute("value"), name);

		Assert.assertEquals(rpage.getEmailTextBox().getAttribute("value"), email);

		rpage.getPasswordTextBox().sendKeys(password);

		rpage.getFirstTNameTextBox().sendKeys(firstName);

		rpage.getLastNameTextBox().sendKeys(map.get("lastname").toString());

		rpage.getAddressTextBox().sendKeys(map.get("address").toString());

		Select select = new Select(rpage.getCountrySelectBox());
		if(map.get("country").toString().equalsIgnoreCase("USA"))
		    {select.selectByIndex(1);}

		rpage.getStateTextBox().sendKeys(map.get("state").toString());

		rpage.getCityTextBox().sendKeys(map.get("city").toString());

		rpage.getZipcodeTextBox().sendKeys(map.get("zipcode").toString());

		rpage.getMobileTextBox().sendKeys(map.get("mobile").toString());

		SuccessAccountCreatedPage success = rpage.clickCreateAccount();

		System.out.println(success.getSuccessMsgBox());

		AutomationExcerciseHomePage homepage=success.clickContinueButton();
		Assert.assertEquals(homepage.getcustomerName(),firstName);
		homepage.clickLogoutLink();

		//delete account - clean up
		String bodyText= "email="+email+"&password="+password;
		String response=given().relaxedHTTPSValidation()
				.headers("Content-Type","application/x-www-form-urlencoded")
				.header("Accept","application/json")
				.body(bodyText)
				.when()
		.delete("https://automationexercise.com/api/deleteAccount").then().assertThat().statusCode(200)
		.extract().response().asString();
        System.out.println(response);

	}



	@DataProvider(name = "registrationData")
	public Object[][] registrationData() {
		Map<Integer, HashMap<String, Object>> customerInfo = null;
		try {
			customerInfo = com.qa.utils.ExcelHelper.getExcelData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<Integer> keyid = customerInfo.keySet();
		Object[][] obj = new Object[keyid.size()][1];
        for (int i = 0; i < keyid.size(); i++) {
         obj[i][0] = customerInfo.get(keyid.iterator().next());
		}
    	return obj;
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
	 *
	 * Given : when user clicks on sign up link in homepage
	 *
	 * when : existing email id is provided
	 *
	 * then : appropriate validation message must be displayed.
	 */
	@Test(dataProvider = "registrationData")
	public void verifyRegistrationWithExistingUser(HashMap<String, Object> map) throws IOException {
		String bodyText =BodyConstruction.bodyForCreateUser(map);
		Map<String, String> header = new HashMap<>();
		header.put("Accept", "application/json");
		header.put("Content-Type", "application/x-www-form-urlencoded");
        String email =map.get("email").toString();
        String name =map.get("name").toString();
        //String password =map.get("password").toString();
		String response = given().relaxedHTTPSValidation()
				.headers(header)
				.body(bodyText)
				.when()
				.post("https://automationexercise.com/api/createAccount").then().assertThat().statusCode(200)
				.extract().response().asString();
		System.out.println(response);

		 response = given().relaxedHTTPSValidation().queryParam("email", email).when()
				.get("https://automationexercise.com/api/getUserDetailByEmail").then().assertThat().statusCode(200)
				.extract().response().asString();
		System.out.println(response);

		WebDriver driver = BaseDriver.getWebDriver();

		LoginPage lpage = new AutomationExcerciseHomePage(driver).open().clickSignInLink();


		lpage.getNameTextBox().sendKeys(name);
		lpage.getEmailTextBox().sendKeys(email);
		lpage.clickCreateAccount();
		Assert.assertEquals(lpage.getValidationMsg(),getContent("signuppage", "existing_email_validation_msg"));

	}

}
