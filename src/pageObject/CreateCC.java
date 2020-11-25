package pageObject;

import org.openqa.selenium.*;

public class CreateCC 
	{
	private static WebElement element = null;

	public static WebElement txtbx_Name(WebDriver driver)
		{	
		//<input id="name" name="name" required="required" type="text" value="" maxlength="60">
    	//div/div/input[@id="name"]
		element = driver.findElement(By.xpath("//input[@id='name']"));
		return element ;	
		}
    
    public static WebElement btn_CreateCC(WebDriver driver)
    	{
    	//<input type="submit" class="ym-button ym-danger" value="Create">
		element = driver.findElement(By.xpath("//input[@class='ym-button ym-danger']"));
		return element;
    	}
	}