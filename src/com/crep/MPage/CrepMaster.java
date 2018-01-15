package com.crep.MPage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CrepMaster {
	
	public static  WebDriver driver=null;
	public static Properties config;
	public static Properties or;
	
	public CrepMaster() throws IOException
	{
		//reading from Configuration.properties 
				FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\ObjectRepository\\Configuration.properties");
				config= new Properties();
				config.load(ip);
				
				//reading from OR.properties
				ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\ObjectRepository\\OR.properties");
				or= new Properties();
				or.load(ip);
				if(driver==null)
				{
				
				if(config.getProperty("browser").equals("firefox"))
						{
							driver = new FirefoxDriver();
						}
				else if(config.getProperty("browser").equals("chrome"))
				{
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\ChromeDriver\\chromedriver.exe");
					driver = new ChromeDriver();
				}
				driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				}
	}
	
	public void input(java.lang.String xpathKey, java.lang.String friendName)
	{
		driver.findElement(By.xpath(or.getProperty(xpathKey))).sendKeys(friendName);
	}
	
	public void click(java.lang.String xapthKey)
	{
		driver.findElement(By.xpath(or.getProperty(xapthKey))).click();
	}
	
	//lets add few validations as well
	
	public boolean isElementPresent(java.lang.String xpathKey)
	{
		try
		{
		driver.findElement(By.xpath(or.getProperty(xpathKey)));
		//return true;
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
					
	}
	
	
	
	
	//Explicit wait
	public void isElementVisible(java.lang.String xpathKey)
	{
		WebElement element= driver.findElement(By.xpath(or.getProperty(xpathKey)));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void isElementClickable(java.lang.String xpathKey)
	{
		WebElement element= driver.findElement(By.xpath(or.getProperty(xpathKey)));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

}
