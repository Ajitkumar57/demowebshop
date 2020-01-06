package com.qa.demowebshop.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.demowebshop.base.BasePage;
import com.qa.demowebshop.pages.RegistrationPage;
import com.qa.demowebshop.util.Constants;
import com.qa.demowebshop.util.ExcelUtil;

public class RegistrationPageTest 
{

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	RegistrationPage registrationPage;
	
	
	
	@BeforeTest
	public void setUp()
	{
		basePage=new BasePage();
		prop=basePage.init_properties();
		driver=basePage.init_driver(prop);
		registrationPage=new RegistrationPage(driver);
	}
	
	@Test(priority=1,enabled=true)
	public void verifyHomePageTitleTest()
	{
		String title=registrationPage.HomePageTitle();
		System.out.println("Home Page title is :"+title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
		
	}
	@Test(priority=2,enabled=true)
	public void verifyisRegisterLinkExistsTest()
	{
		Assert.assertTrue(registrationPage.isRegisterLinkExists(), "Register Link is present");
		
	}
	@Test(priority=3,enabled=true)
	public void verifyRegisterLinkclickTest()
	{
		registrationPage.isRegisterLinkClick();
	}
	
	@Test(priority=4,enabled=true)
	public void verifyRegistationPageTitleTest()
	{
		String title1=registrationPage.RegistationPageTitle();
		System.out.println("Registartion Page Title is :"+title1);
		Assert.assertEquals(title1,Constants.REGISTRATION_PAGE_TITLE);
	}
	
	@DataProvider
	public Object[][] getRegisterTestData()
	{
		Object data[][]=ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
		return data;
	}
	@Test(priority=5,dataProvider="getRegisterTestData")
	public void createNewRegisterTest(String firstname,String lastname,String email,String password,String confirmPassword)
	{
		registrationPage.createNewRegistration(firstname, lastname, email, password, confirmPassword);
		  
	}
	
	@AfterTest
	public void quitBrowser()
	{
		//driver.quit();
	}
	
}
