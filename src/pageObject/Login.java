package pageObject;

import org.openqa.selenium.*;

import org.openqa.selenium.WebDriver;
 
import org.openqa.selenium.WebElement;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */ 
//https://services-sandbox.hopewiser.com/hostedmgr/logon
public class Login 
	{
	private static WebElement element = null;
	
	//<input type="text" name="j_username" id="j_username" size="20" required="required">
    public static WebElement txtbx_UserName(WebDriver driver)
    	{    	
    	element = driver.findElement(By.xpath("//input[@id='j_username']"));    	 
        return element;
    	}
    //<input type="password" name="j_password" id="j_password" size="20">
    public static WebElement txtbx_Password(WebDriver driver)
    	{  	 
        element = driver.findElement(By.xpath("//input[@id='j_password']"));
        return element;
    	}
    
	//<input type="submit" class="ym-button ym-danger" value="Log&nbsp;On">
    public static WebElement btn_LogIn(WebDriver driver)
    	{   	 
        element = driver.findElement(By.xpath("//input[@class='ym-button ym-danger']"));
        return element;    
    	}
	}