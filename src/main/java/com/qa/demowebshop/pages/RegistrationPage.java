package com.qa.demowebshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.demowebshop.base.BasePage;
import com.qa.demowebshop.util.Constants;
import com.qa.demowebshop.util.ElementUtil;


import io.qameta.allure.Step;

public class RegistrationPage extends BasePage
{

	WebDriver driver;
	ElementUtil elementUtil;
	
	//1.Page Objects / By Locators
	By gender = By.xpath("(//input[@name='Gender'])[1]");
	By firstName =By.id("FirstName");
	By lastName=By.id("LastName");
	By email=By.id("Email");
	By password=By.id("Password");
	By confirmPassword=By.id("ConfirmPassword");
	By register=By.id("register-button");
	By registerLink=By.xpath("//a[text()='Register']");
	By continueBtn=By.xpath("//input[@value='Continue']");
	By logout=By.xpath("//a[text()='Log out']");
	
	
	//2.Page class Constructor
	 public RegistrationPage(WebDriver driver)
	 {
		 this.driver=driver;
		 elementUtil=new ElementUtil(driver);
	 }
	
	 
   //3.Page actions/methods:
	@Step("get Home page title...")
	public String HomePageTitle()
	{
		elementUtil.waitForPageTitle(Constants.HOME_PAGE_TITLE);
		return elementUtil.doGetTitle();
	
	}
	
	@Step("Checking registration link is present or not..")
	public boolean isRegisterLinkExists()
	{
		return elementUtil.doIsDisplayed(registerLink);
	}
	
	
	@Step("clicking in the registerlink")
	public void isRegisterLinkClick()
	{
		elementUtil.doClick(registerLink);
		
	}
	
	@Step("get Registration page title")
	public String RegistationPageTitle()
	{
		elementUtil.waitForPageTitle(Constants.REGISTRATION_PAGE_TITLE);
		return elementUtil.doGetTitle();
	}
	
	@Step("enterning the data into registration form")
	public LoginPage createNewRegistration(String FN,String LN,String mail,String PWD,String CPWD)
	{
		elementUtil.doClick(gender);
		elementUtil.doActionsSendKeys(firstName, FN);
		elementUtil.doActionsSendKeys(lastName, LN);
		elementUtil.doActionsSendKeys(email, mail);
		elementUtil.doActionsSendKeys(password, PWD);
		elementUtil.doActionsSendKeys(confirmPassword, CPWD);
		elementUtil.doClick(register);
		elementUtil.doClick(continueBtn);
		elementUtil.doClick(logout);
		elementUtil.doClick(registerLink);
		return new LoginPage(driver);
	}
	
}
