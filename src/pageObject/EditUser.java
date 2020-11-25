package pageObject;

import org.openqa.selenium.*;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class EditUser 
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
	
	public static WebElement txtbx_CostCentre(WebDriver driver)
		{
		element = driver.findElement(By.xpath("//select[@id='costid']"));		
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
	 
	 //<input id="dailyclickswarn" name="dailyclickswarn" type="text" value="" maxlength="100">
	 public static WebElement txtbx_DailyClicks(WebDriver driver)
	 	{		
		element = driver.findElement(By.xpath("//input[@id='dailyclickswarn']"));		
		return element;
	 	}
	 
	 public static WebElement btn_EditUser(WebDriver driver)
	 	{
		element = driver.findElement(By.xpath("//input[@class='ym-button ym-danger']"));
		return element;
	 	}
	}