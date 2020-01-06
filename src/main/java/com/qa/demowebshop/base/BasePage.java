package com.qa.demowebshop.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;



import io.github.bonigarcia.wdm.WebDriverManager;



public class BasePage 
{

	WebDriver driver;
	Properties prop;
	
	public static ThreadLocal<WebDriver> tldriver= new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}
	
	/**
	 * This method is used to initlization the driver on the basis of browser name
	 * @param browser
	 * @return driver
	 */
	
	public WebDriver init_driver(Properties prop)
	{
		String browser =prop.getProperty("browser");
		
		if(browser.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			tldriver.set(new ChromeDriver());
		}
		else if(browser.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			tldriver.set(new FirefoxDriver());
		}
		else if(browser.equals("safari"))
		{
			WebDriverManager.getInstance(SafariDriver.class).setup();
			tldriver.set(new SafariDriver());
		}
		else
		{
			System.out.println("Please pass the correct browser name....");
		}
		
		getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
		
	}	
		/**
		 * This method is used to initialize the properties from config.proerties file
		 * 
		 * @return prop
		 */
		public Properties init_properties()
		{
			prop=new Properties();
				try
				{
					FileInputStream ip= new FileInputStream("C:\\Users\\USER\\eclipse-workspace\\SeleniumPracticeFramework\\src\\main\\java\\com\\qa\\demowebshop\\config\\config.properties");
				    prop.load(ip);
				}
				catch (FileNotFoundException e)
				{
					e.printStackTrace();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				return prop;
			}
		
		
		/**
		 * This method is used to Taking the Screenshot  and stored in the screenshot folder
		 * @return
		 */
		
		public String getScreenshot() 
		{
			File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
			File destination = new File(path);
			try 
			{
				FileUtils.copyFile(src, destination);
			} 
			catch (IOException e) 
			{
				System.out.println("Capture  " + e.getMessage());
			}
			return path;
		}
			
		
	}
	
	


