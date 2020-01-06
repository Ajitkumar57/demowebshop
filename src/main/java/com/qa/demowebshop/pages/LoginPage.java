package com.qa.demowebshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.demowebshop.base.BasePage;
import com.qa.demowebshop.util.ElementUtil;

public class LoginPage extends BasePage 
{
  WebDriver driver;
  ElementUtil elementUtil;
  
  By login=By.xpath("//a[text()='Log in']");
  By emailLogin=By.xpath("//input[@name='Email']");
  By passwordLogin=By.xpath("//input[@name='Password']");
  By loginBtn=By.xpath("//input[@value='Log in']");
  
  public LoginPage(WebDriver driver)
  {
	this.driver=driver;
	elementUtil=new ElementUtil(driver);
  }
  
  public void loginLinkClick()
  {
	  elementUtil.doClick(login);
  }


	
}
