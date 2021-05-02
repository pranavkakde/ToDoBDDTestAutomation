package com.test.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseFunctions {
	WebDriver driver;
	
	public BaseFunctions(WebDriver driver){
		this.driver = driver;
	}
	
	public void click(WebElement element) {
		element.click();
	}
	public void clickByXpath(String xpath) {
		getElement(By.xpath(xpath)).click();
	}
	public boolean isTextMatching(WebElement element, String textToMatch) {
		boolean retVal = false;
		try {
			retVal=element.getText().contains(textToMatch);
		}catch(Exception e) {
			retVal=false;
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return retVal;		
	}
	public void typeText(WebElement element, String value) {
		try {
			element.sendKeys(value);	
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	public boolean isVisibleByXpath(String xpath) {
		return getElement(By.xpath(xpath)).isDisplayed();
	}
	public boolean isEnabledByXpath(String xpath) {
		return getElement(By.xpath(xpath)).isEnabled();
	}
	public boolean isElementVisible(WebElement element) {
		boolean retVal = false;
		try {
			retVal=element.isDisplayed();
		}catch(Exception e) {
			retVal=false;
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return retVal;
	}
	public boolean isPageVisible(String pageTitle) {
		boolean retVal = false;
		try {
			retVal=driver.getTitle().contains(pageTitle);
		}catch(Exception e) {
			retVal=false;
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return retVal;
	}
	private WebElement getElement(By element) {
		WebElement elem = null;
		try {
			elem = driver.findElement(element);	
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}		
		return elem;
	}
}
