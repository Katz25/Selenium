package pageObject;

import org.openqa.selenium.*;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */ 
//https://services-sandbox.hopewiser.com/hostedmgr/logon
public class PHPLogin 
	{
	private static WebElement element = null;
	
    public static WebElement txtbx_UserName(WebDriver driver)
    	{
    	//<input type="text" name="pma_username" id="input_username" value="" size="24" class="textfield">
    	element = driver.findElement(By.xpath("//input[@id='input_username']"));    	 
        return element;
    	}

    public static WebElement txtbx_Password(WebDriver driver)
    	{
        //<input type="password" name="pma_password" id="input_password" value="" size="24" class="textfield">
        element = driver.findElement(By.xpath("//input[@id='input_password']"));
        return element;
    	}
    
    public static WebElement combobx_ServerChoice(WebDriver driver)
		{    
    	//<select name="server" id="select_server"><option value="1" selected="selected">addrsvr2.hopewiser.com</option>
    	element = driver.findElement(By.id("select_server")); // Find server drop down box and enter 'addrsvr2.hopewiser.com'
        return element;
		}
    
    public static WebElement btn_Go(WebDriver driver)
    	{
    	//<input value="Go" type="submit" id="input_go">
        element = driver.findElement(By.xpath("//input[@value='Go']"));
        return element;    
    	}
	}