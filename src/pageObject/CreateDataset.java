package pageObject;

import org.openqa.selenium.*;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class CreateDataset 
	{	
	private static WebElement element = null;														// Initiate Element

	public static WebElement txtbx_Name(WebDriver driver)
		{	
		//<input id="companyname" name="companyname" required="required" type="text" value="" maxlength="60">
		WebElement element = driver.findElement(By.xpath("//input[@id='name']"));					// Select the Dataset Name box
		return element ;	
		}
	
	public static WebElement cbobx_Type(WebDriver driver)
		{	
		//<select id="type" name="type"> 	//<option value="" selected="selected"></option>		//<option value="atlas3">Address</option><option value="bankcode">Bankcoder</option>
		WebElement element = driver.findElement(By.xpath("//select[@id='type']"));					// Select the Dataset Type (Service) Combo box
		return element ;	
		}
	
	public static WebElement txtbx_Description(WebDriver driver)
		{	
		// <textarea id="description" name="description" rows="6"></textarea>
		WebElement element = driver.findElement(By.xpath("//textarea[@id='description']"));			// Select the Description box
		return element ;	
		}
    
    public static WebElement btn_CreateBundle(WebDriver driver)
    	{
    	//<input class="ym-button ym-danger" value="Create" type="submit">
		element = driver.findElement(By.xpath("//input[@value='Create']"));							// Select the Create Button
		return element ;
    	}
		
	public static WebElement btn_BackToList(WebDriver driver)
		{
	    //<a href="/hostedmgr/dataset" class="ym-button">Back&nbsp;to&nbsp;List</a>
		element = driver.findElement(By.xpath("//a[@href='/hostedmgr/dataset']"));					// Select the Back to List Button
		return element ;
		}
	}