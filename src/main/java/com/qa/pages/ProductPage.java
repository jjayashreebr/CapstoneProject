package com.qa.pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.locators.ProductPageLocators;

public class ProductPage extends ProductPageLocators {
	WebDriver driver = null;

	public ProductPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getSearchTextBox() {
		return driver.findElement(SEARCH_TEXT_BOX);
	}

	public List<WebElement> getProductList() {
		return driver.findElements(RESULT_LIST);
	}

	public List<WebElement> getBrandList() {
		return driver.findElements(BRAND_LIST);
	}
	
	public ProductPage getBrandProductPageTitle(String brand) {
		 List<WebElement> myList = driver.findElements(BRAND_LIST);
		   
	     for(WebElement element : myList) {
	    	 if(element.getText().contains(brand)) {
	    		 element.click();
	    		// flag=true;
	    		 break;
	    	 }
	     }
	   return new ProductPage(driver);  
	}
	
	public String getBrandListCount(String brand) {
		List<WebElement> myList= driver.findElements(BRAND_LIST);
		List<String> newList = myList.stream()
				.map(s -> s.getText().replaceAll("[\\[\\](){}]", "").replaceAll("[\n\t]", " "))
				.collect(Collectors.toList());
	
		
		String actualCount=null;
	    for(String temp:newList) {
	    	String[] tp =temp.split(" ");
	    	if(tp[1].equals(brand.toUpperCase())){
	    		actualCount=tp[0];
	    		break;
	    	}
	    }
	    if(actualCount==null)
	    {
	    	actualCount="0";
	    }
	    return actualCount;
	}

	
	public  List<WebElement> getCategoryList() {
		return driver.findElements(CATEGORY_LIST);
		
	}
	public int getSubCategoryCount(String category) {
		List<WebElement> myList = driver.findElements(CATEGORY_LIST);
		List<WebElement> mySubList = null;
		for (WebElement ele : myList) {
			String[] linkText = ele.getAttribute("href").split("#");
			if (ele.getText().equals(category)) {
				ele.click();

				mySubList = this.getSubCategoryList(linkText[1]);

				break;
			}
		}

		return mySubList.size();

	}

	public CategoryProductPage getCategoryList(String category, String subCategory) {

		List<WebElement> myList = driver.findElements(CATEGORY_LIST);

		for (WebElement ele : myList) {
			String[] linkText = ele.getAttribute("href").split("#");
			System.out.println(ele.getText());
			if (ele.getText().equals(category)) {
				ele.click();

				System.out.println(linkText[1]);
				List<WebElement> mySubList = this.getSubCategoryList(linkText[1]);
				System.out.println(mySubList.size());
				for (WebElement element : mySubList) {
					if (element.getAttribute("innerHTML").trim().equals(subCategory)) {
						System.out.println("inside");
						element.click();
						break;
					}
				}
				break;
			}
		}
		return new CategoryProductPage(driver);

	}

	public List<WebElement> getSubCategoryList(String category) {
		String loc = "//*[@id=\'" + category + "\']/div/ul/li/a";
		System.out.println(loc);
		return driver.findElements(By.xpath(loc));
	}

	public String getPageTitleChange() {
		return driver.findElement(PAGE_TITLE_CHANGE).getText();
	}

	public String getProductName() {
		return driver.findElement(PRODUCT_NAME).getText();
	}

	public ProductPage clickSearch() {
		driver.findElement(SEARCH_BUTTON).click();
		return new ProductPage(driver);
	}

	public ViewCartPage clickViewCart() {
		driver.findElement(VIEW_CART_BUTTON_IN_MODEL).click();
		return new ViewCartPage(driver);
	}

	public ViewItemPage clickViewItem() {
		driver.findElement(VIEW_ITEM_BUTTON).click();
		return new ViewItemPage(driver);
	}

	public ProductPage clickAddtoCart() {
		driver.findElement(ADD_TO_CART_BUTTON).click();
		return new ProductPage(driver);
	}

	public WebElement getSearchButton() {
		return driver.findElement(SEARCH_BUTTON);
	}

}
