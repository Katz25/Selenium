package pageObject;

import org.openqa.selenium.*;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class CreateUser 
	{	
	private static WebElement element = null;

	public static WebElement txtbx_Name(WebDriver driver)
		{	
		element = driver.findElement(By.xpath("//input[@id='name']"));		
		return element ;	
		}
	
	public static WebElement txtbx_EmailAddress(WebDriver driver)
		{
		element = driver.findElement(By.xpath("//input[@id='notifyemail']"));		
		return element;	
		}
	
	public static WebElement txtbx_UserName(WebDriver driver)
		{	
		element = driver.findElement(By.xpath("//input[@id='login']"));	
		return element;	
		}
	
      public static WebElement txtbx_Password(WebDriver driver)
      	{
		element = driver.findElement(By.xpath("//input[@id='password']"));
		return element;	
      	}

    public static WebElement txtbx_ConfirmPassword(WebDriver driver)
    	{
    	element = driver.findElement(By.xpath("//input[@id='confirmpassword']"));
    	return element;	
    	}
    
    public static WebElement txtbx_AdminLevel(WebDriver driver)
    	{	
		element = driver.findElement(By.xpath("//select[@id='adminlevel']"));
		return element;		
    	}
		
    public static WebElement txtbx_DefaultAddressReference(WebDriver driver)
    	{
    	element = driver.findElement(By.xpath("//select[@id='defaultmafalias']"));
    	return element;
    	}
    
    public static WebElement btn_CreateUser(WebDriver driver)
    	{
		element = driver.findElement(By.xpath("//input[@class='ym-button ym-danger']"));
		return element;
    	}
	}