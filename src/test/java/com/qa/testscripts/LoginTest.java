package com.qa.testscripts;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.pages.AutomationExcerciseHomePage;
import com.qa.pages.LoginPage;
import com.qa.utils.BaseDriver;
import com.qa.utils.BodyConstruction;

import io.restassured.path.json.JsonPath;

public class LoginTest extends BaseDriver {

	/*
	 * Given: when sign up link is clicked in home page
	 *
	 * when: empty name and empty email is provided
	 *
	 * then: field validation error must be displayed
	 */
	@Test(groups = { "frontend" })
	public void verifyLoginWithEmptyCredentials() throws IOException {
		WebDriver driver = BaseDriver.getWebDriver();

		LoginPage lpage = new AutomationExcerciseHomePage(driver).open().clickSignInLink();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		SoftAssert softAssert = new SoftAssert();

		WebElement name = lpage.getEmailLoginTextBox();
		String actualUserNameValidationMessage = (String) js.executeScript("return arguments[0].validationMessage;",
				name);
		String expectedName = getContent("loginpage", "validation_msg");
		softAssert.assertEquals(actualUserNameValidationMessage, expectedName);

		WebElement email = lpage.getPasswordTextBox();
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

		WebElement email = lpage.getEmailLoginTextBox();
		email.sendKeys("JamesBond");

		lpage.getPasswordTextBox().sendKeys("James");

		lpage.clickLogin();

		String actualEmailValidationMessage = (String) js.executeScript("return arguments[0].validationMessage;",
				email);
		String expectedEmail = getContent("loginpage", "email_validation_msg");

		Assert.assertTrue(actualEmailValidationMessage.contains(expectedEmail));
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
	 * Verify Login with valid details – API validation PreCondition – create user
	 * account using API Given : when request to
	 * https://automationexercise.com/api/verifyLogin is sent with request
	 * parameters valid email and valid password when : post request is made then :
	 * response must be 200.
	 */

	@Test(dataProvider = "registrationData")
	public void verifyLoginWithValidCredential(HashMap<String, Object> map) throws IOException {
		String bodyText = BodyConstruction.bodyForCreateUser(map);
		Map<String, String> header = new HashMap<>();
		header.put("Accept", "application/json");
		header.put("Content-Type", "application/x-www-form-urlencoded");
		String email = map.get("email").toString();
		String name = map.get("name").toString();
		String password = map.get("password").toString();
		// create account
		String response = given().relaxedHTTPSValidation().headers(header).body(bodyText).when()
				.post("https://automationexercise.com/api/createAccount").then().assertThat().statusCode(200).extract()
				.response().asString();
		System.out.println(response);

		// verify login
		bodyText = "email=" + email + "&password=" + password;
		response = given().relaxedHTTPSValidation().headers(header).body(bodyText).when()
				.post("https://automationexercise.com/api/verifyLogin").then().assertThat().statusCode(200).extract()
				.response().asString();
		System.out.println(response);

		// delete account - clean up
		bodyText = "email=" + email + "&password=" + password;
		response = given().relaxedHTTPSValidation().headers("Content-Type", "application/x-www-form-urlencoded")
				.header("Accept", "application/json").body(bodyText).when()
				.delete("https://automationexercise.com/api/deleteAccount").then().assertThat().statusCode(200)
				.extract().response().asString();
		System.out.println(response);

	}

	/*
	 * Verify Login without email details – API validation Given : when request to
	 * https://automationexercise.com/api/verifyLogin is sent with no request
	 * parameters when : post request is made then : response must be 400.
	 * 
	 */

	@Test
	public void verifyLoginWithEmptyEmailCredential() throws IOException {

		Map<String, String> header = new HashMap<>();
		header.put("Accept", "application/json");
		header.put("Content-Type", "application/x-www-form-urlencoded");

		String password = "test@123";

		// verify login with empty email
		String bodyText = "password=" + password;

		String response = given().relaxedHTTPSValidation().headers(header).body(bodyText).when()
				.post("https://automationexercise.com/api/verifyLogin").then().assertThat().statusCode(200).extract()
				.response().asString();
		JsonPath js = new JsonPath(response);
		Assert.assertEquals(js.getInt("responseCode"), 400);
		Assert.assertEquals(js.get("message").toString(),
				"Bad request, email or password parameter is missing in POST request.");

	}

	/*
	 * 5.Delete account using Verify Login – API validation Given : when request to
	 * https://automationexercise.com/api/verifyLogin is sent when : delete request
	 * is made then : response must be 405
	 */
	@Test
	public void verifyDeleteLogin() throws IOException {
		Map<String, String> header = new HashMap<>();
		header.put("Accept", "application/json");
		header.put("Content-Type", "application/x-www-form-urlencoded");

		String response = given().relaxedHTTPSValidation().headers(header).when()
				.delete("https://automationexercise.com/api/verifyLogin").then().assertThat().statusCode(200).extract()
				.response().asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		Assert.assertEquals(js.getInt("responseCode"), 405);
		Assert.assertEquals(js.get("message").toString(), "This request method is not supported.");
	}

	/*
	 * Verify Login invalid email and invalid password details – API validation
	 * Given : when request to https://automationexercise.com/api/verifyLogin is
	 * sent with invalid request parameters when : post request is made then :
	 * response must be 404.
	 */

	@Test
	public void verifyLoginWithInvalidCredentials() throws IOException {
		Map<String, String> header = new HashMap<>();
		header.put("Accept", "application/json");
		header.put("Content-Type", "application/x-www-form-urlencoded");
		String bodyText = "email=abc@a.com&password=password";
		String response = given().relaxedHTTPSValidation().headers(header).body(bodyText).when()
				.post("https://automationexercise.com/api/verifyLogin").then().assertThat().statusCode(200).extract()
				.response().asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		Assert.assertEquals(js.getInt("responseCode"), 404);
		Assert.assertEquals(js.get("message").toString(), "User not found!");

	}

	/*
	 * 7.Verify login with valid user and valid password – Front End Given – when
	 * user click on signup/login on hompage 
	 * When – valid email and password is given 
	 * Then- it should show user name in logged in as .
	 */
	@Test(dataProvider = "registrationData")
	public void verifyLoginShowsProperLoggedInMessage(HashMap<String, Object> map) throws IOException {
		String bodyText = BodyConstruction.bodyForCreateUser(map);
		Map<String, String> header = new HashMap<>();
		header.put("Accept", "application/json");
		header.put("Content-Type", "application/x-www-form-urlencoded");
		String email = map.get("email").toString();
		String name = map.get("name").toString();
		String password = map.get("password").toString();
		// create account
		String response = given().relaxedHTTPSValidation().headers(header).body(bodyText).when()
				.post("https://automationexercise.com/api/createAccount").then().assertThat().statusCode(200).extract()
				.response().asString();
		System.out.println(response);
		
		WebDriver driver = BaseDriver.getWebDriver();
		LoginPage lpage = new AutomationExcerciseHomePage(driver).open().clickSignInLink();
        lpage.getEmailLoginTextBox().sendKeys(email);
		lpage.getPasswordTextBox().sendKeys(password);
		AutomationExcerciseHomePage homepage=lpage.clickLogin();
		Assert.assertEquals(homepage.getcustomerName(),name);
		homepage.clickLogoutLink();
		
		// delete account - clean up
		 bodyText = "email=" + email + "&password=" + password;
		 response = given().relaxedHTTPSValidation().headers("Content-Type", "application/x-www-form-urlencoded")
				.header("Accept", "application/json").body(bodyText).when()
				.delete("https://automationexercise.com/api/deleteAccount").then().assertThat().statusCode(200)
				.extract().response().asString();
		System.out.println(response);

}
	}
