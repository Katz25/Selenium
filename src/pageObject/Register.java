package pageObject;

import org.openqa.selenium.*;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class Register 
	{
	private static WebElement element = null;
	
	public static WebElement txtbx_CompanyName(WebDriver driver)
		{		
		element = driver.findElement(By.xpath("//input[@id='companyname']"));		
		return element;		
		}
	
	public static WebElement txtbx_Country(WebDriver driver)
		{
		element = driver.findElement(By.xpath("//select[@id='country']"));		
		return element;		
		}
	
	public static WebElement txtbx_PostCode(WebDriver driver)
		{		
		element = driver.findElement(By.xpath("//input[@class='ym-inline']"));	
		return element;	
		}
	
	public static WebElement txtbx_Address(WebDriver driver)
		{		
		element = driver.findElement(By.xpath("//input[@id='address1']"));		
		return element;		
		}
	
	public static WebElement txtbx_Town(WebDriver driver)
		{		
		element = driver.findElement(By.xpath("//input[@id='city']"));		
		return element;		
		}
	
	public static WebElement txtbx_County(WebDriver driver)
		{		
		element = driver.findElement(By.xpath("//input[@id='state']"));		
		return element;		
		}
	
	public static WebElement txtbx_YourName(WebDriver driver)
		{		
		element = driver.findElement(By.xpath("//input[@id='username']"));		
		return element;		
		}
	
	public static WebElement txtbx_EmailAddress(WebDriver driver)
		{		
		element = driver.findElement(By.xpath("//input[@id='email']"));		
		return element;		
		}
		
	public static WebElement txtbx_DesiredUserName(WebDriver driver)
		{		
		element = driver.findElement(By.xpath("//input[@id='login']"));		
		return element;		
		}
	
	public static WebElement txtbx_SetaPassword(WebDriver driver)
		{		
		element = driver.findElement(By.xpath("//input[@id='password']"));		
		return element;		
		}
	
	public static WebElement txtbx_ConfirmPassword(WebDriver driver)
		{		
		element = driver.findElement(By.xpath("//input[@id='confirmpassword']"));		
		return element;		
		}
	
	public static WebElement btn_Register(WebDriver driver)
		{		
		element = driver.findElement(By.xpath("//input[@class='ym-button ym-danger']"));		
		return element;	
		}
	}