package com.qa.demowebshop.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

public class ElementUtil {
	WebDriver driver;
	WebDriverWait wait;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 15);
	}

	public String doGetTitle() {
		String title = null;
		try {
			title = driver.getTitle();
		} catch (Exception e) {
			System.out.println("Some Exception got occured while getting the title of the page");
			System.out.println(e.getMessage());
		}
		return title;
	}
	
	public void doClick(By locator) {
		try {
			getElement(locator).click();
		} catch (Exception e) {
			System.out.println("some exception got occurred while clicking on the webelement : " + locator);
			System.out.println(e.getMessage());

		}
	}


	@Step("getting tht element with : {0}")
	public WebElement getElement(By locator) {
		waitForElementPresent(locator);
		WebElement element = null;
		try {
			element = driver.findElement(locator);
		} catch (Exception e) {
			System.out.println("some exception got occurred while creating the webelement : " + locator);
			System.out.println(e.getMessage());
		}
		return element;
	}

	public boolean doIsDisplayed(By locator) {

		boolean flag = false;
		try {
			flag = getElement(locator).isDisplayed();
		} catch (Exception e) {
			System.out
					.println("some exception got occurred while checking isDisplayed for the webelement : " + locator);
			System.out.println(e.getMessage());
		}
		return flag;
	}
	
	public void doActionsSendKeys(By locator, String...value)
	{
		try
		{
			Actions action=new Actions(driver);
			action.sendKeys(getElement(locator),value).build().perform();
			
		}catch(Exception e)
		{
			System.out.println("some exception got occurred while passing the values to the webelement : " + locator);
			System.out.println(e.getMessage());
		}
		
	}

	public void waitForPageTitle(String title) {
		wait.until(ExpectedConditions.titleContains(title));
	}

	public void waitForElementPresent(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

}
