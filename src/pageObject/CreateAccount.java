package pageObject;

import org.openqa.selenium.*;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class CreateAccount {
	
	private static WebElement element = null;														// Initiate Element

	public static WebElement txtbx_Name(WebDriver driver)
		{	
		//<input id="companyname" name="companyname" required="required" type="text" value="" maxlength="60">
		WebElement element = driver.findElement(By.xpath("//input[@id='companyname']"));			// Select the Company Edit box
		return element ;	
		}
	
	public static WebElement cbobx_Country(WebDriver driver)
		{	
		//<select id="country" name="country"><option value=""></option><option value="United Kingdom" selected="selected">United Kingdom</option></select>
		WebElement element = driver.findElement(By.xpath("//select[@id='country']"));	   			// Select the Country Combobox
		return element ;	
		}
	
	public static WebElement txtbx_PostCode(WebDriver driver)
		{	
		//<input id="postcode" name="postcode" type="text" value="" maxlength="250" class="ym-inline">
		WebElement element = driver.findElement(By.xpath("//input[@id='postcode']"));				// Select the Post code Edit box
		return element ;	
		}
	
	public static WebElement txtbx_Address(WebDriver driver)
		{	
		//<input id="address1" name="address1" required="required" type="text" value="" maxlength="50">
		WebElement element = driver.findElement(By.xpath("//input[@id='address1']"));				// Select the Address Edit box
		return element ;	
		}
	
	public static WebElement txtbx_Town(WebDriver driver)
		{	
		//<input id="city" name="city" required="required" type="text" value="TEST" maxlength="30">
		WebElement element = driver.findElement(By.xpath("//input[@id='city']"));					// Select the Town/City Edit box
		return element ;	
		}
	
	public static WebElement txtbx_County(WebDriver driver)
		{	
		//<input id="state" name="state" type="text" value="TEST" maxlength="30">
		WebElement element = driver.findElement(By.xpath("//input[@id='state']"));					// Select the County Edit box																						
		return element ;	
		}
	
	public static WebElement txtbx_Territory(WebDriver driver)
		{	
		//<select id="territory" name="territory"><option value="AU">Australia</option><option value="GB" selected="selected">United Kingdom</option></select>
		WebElement element = driver.findElement(By.xpath("//select[@id='territory']"));				// Select the territory Combo box																						
		return element ;	
		}
	
	public static WebElement checkbx_TandC(WebDriver driver)
		{	
		//<input id="agreeterms" name="agreeterms" type="checkbox" value="true">
		WebElement element = driver.findElement(By.xpath("//input[@id='agreeterms']"));				// Tick the Terms and Conditions check box																										
		return element ;	
		}	
    
    public static WebElement btn_CreateAccount(WebDriver driver)
    	{
    	//<input type="submit" class="ym-button ym-danger" value="Save" onclick="return btnSubmit_onclick();">
		element = driver.findElement(By.xpath("//input[@class='ym-button ym-danger']"));
		return element;
	}
}