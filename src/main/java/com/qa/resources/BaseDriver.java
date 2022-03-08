package com.qa.resources;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;

import com.qa.utils.ContentReaderUtil;

public class BaseDriver {

	public static final ThreadLocal<WebDriver> webdrivers = new ThreadLocal<WebDriver>();

	static {
		String driverpath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", driverpath + "/webdriver/chromedriver.exe");
		System.setProperty("webdriver.edge.driver", driverpath + "/webdriver/msedgedriver.exe");
	}

	public static WebDriver getWebDriver() {

		WebDriver driver = null;
		String browser = System.getProperty("testbrowser");
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

	public static String getContent(String propertyFileName, String propertyName){
     String propertyValue=null;
      try {
    	  propertyValue= ContentReaderUtil.getPropertyFile(propertyFileName, propertyName);
	} catch (IOException e) {
		e.printStackTrace();
	}
      return propertyValue;
	}

	@AfterMethod
	public void tearDown() {
		// To ensure driver is available before closing
		if (webdrivers.get() != null) {
		    webdrivers.get().close();
		    webdrivers.get().quit();
		}
	}

	
	public void getScreenShotPath(String testName,WebDriver driver) throws IOException {
		TakesScreenshot t= (TakesScreenshot) driver;
		File source = t.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"\\reports\\"+testName+".png";
		FileUtils.copyFile(source, new File(path));
		
	}

}
