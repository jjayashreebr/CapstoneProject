package com.qa.AutomationExercise;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.page.HomePage;
import com.qa.page.LoginPage;
import com.qa.page.RegistrationPage;
import com.qa.utils.BaseDriverUtils;

public class RegistrationTest extends BaseDriverUtils{

	/*
	 * 1- Login to Automation Practice Homepage Website 2- Press on Sign In button
	 * 3- Enter Valid Email (mostafa@gmail.com) 4- Press on Create an Account button
	 * 5- Enter Valid First Name 6- Enter Valid Last Name 7- Valid Email is already
	 * being filled automatically (mostafa@gmail.com) 8- Enter Valid Password (more
	 * than or equal 5 Character) 9- Enter Valid Address 10- Enter Valid City 11-
	 * Select Valid State from Dropdown List 12- Enter Valid Zip Code (11757) 13-
	 * Valid Country is being selected by default 14- Enter Valid Mobile 15- Enter a
	 * Valid address alias 16- Press Register Button
	 */
	
	WebDriver driver;
	String homePage = "http://automationpractice.com/index.php";

	@BeforeMethod
	public void setUp() {
        driver = BaseDriverUtils.getWebDriver("chrome");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
        driver.get(homePage);
	}
	@Test
	public void verifyPageTitle() {
		
		System.out.println(driver.getTitle());
		HomePage page = new HomePage(driver);
		LoginPage lpage = page.clickSignInLink();
		lpage.getEmailTextBox().sendKeys("mostafa@gmail.com");
		RegistrationPage rpage=lpage.clickCreateAccount();

	}
	
	
	@Test
	public void verifyRegisterUserWithValidData() {
		
		

	}
	
	@AfterMethod
	public void tearDown() {
		//driver.quit();
	}
}
