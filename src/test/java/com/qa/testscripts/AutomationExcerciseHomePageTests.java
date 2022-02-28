package com.qa.testscripts;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.pages.AutomationExcerciseHomePage;
import com.qa.utils.BaseDriver;

public class AutomationExcerciseHomePageTests extends BaseDriver {

	/* Verify page title */
	@Test
	public void verifyPageTitle() throws IOException {
		WebDriver driver = BaseDriver.getWebDriver();
		AutomationExcerciseHomePage page = new AutomationExcerciseHomePage(driver).open();
		Assert.assertEquals(page.getTitle(), getContent("homepage", "pagetitle"));
	}
}
