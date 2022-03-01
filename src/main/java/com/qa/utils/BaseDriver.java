package com.qa.utils;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;

public class BaseDriver {

	public static final ThreadLocal<WebDriver> webdrivers = new ThreadLocal<>();

	static {
		String driverpath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", driverpath + "/webdriver/chromedriver.exe");
		System.setProperty("webdriver.edge.driver", driverpath + "/webdriver/msedgedriver.exe");
	}

	public static WebDriver getWebDriver() throws IOException {

		WebDriver driver = null;

		// Global configs from pom.xml - browser
		String browser = System.getProperty("testbrowser");
		System.out.println("Pom value-----------------" + browser);

		switch (browser) {
		case "edge":
			EdgeOptions options = new EdgeOptions();
			options.setAcceptInsecureCerts(true);
			driver = new EdgeDriver(options);
			break;
		case "chrome":
			ChromeOptions options1 = new ChromeOptions();
			options1.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(options1);
			break;
		default:
			ChromeOptions options11 = new ChromeOptions();
			options11.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(options11);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));

		webdrivers.set(driver);
		return webdrivers.get();
	}

	public static String getContent(String propertyFileName, String propertyName) throws IOException {

      return ContentReaderUtil.getPropertyFile(propertyFileName, propertyName);
	}

	@AfterMethod
	public void tearDown() {
		// To ensure driver is available before closing
		if (webdrivers.get() != null) {
			//webdrivers.get().close();
		}
	}

}
