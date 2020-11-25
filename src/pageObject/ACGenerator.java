package pageObject;

import org.openqa.selenium.*;

import org.openqa.selenium.WebDriver;
 
import org.openqa.selenium.WebElement;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */ 
//https://services-sandbox.hopewiser.com/hostedmgr/logon
public class ACGenerator 
	{
	private static WebElement element = null;
	
	//<input id="username" name="username" type="text">
    public static WebElement txtbx_UserName(WebDriver driver)
    	{    	
    	element = driver.findElement(By.xpath("//input[@id='username']"));    	 
        return element;
    	}
    
	//<input id="password" name="password" type="text">
    public static WebElement txtbx_Password(WebDriver driver)
    	{  	 
        element = driver.findElement(By.xpath("//input[@id='password']"));
        return element;
    	}
    
	//<button class="ym-button ym-danger" onclick="btn_onclick()">Generate my Code</button>
    public static WebElement btn_GmyCode(WebDriver driver)
    	{   	 
        element = driver.findElement(By.xpath("//button[@class='ym-button ym-danger']"));
        return element;    
    	}
    
	//<input id="authcode" name="authcode" readonly="readonly" type="text">
    public static WebElement txtbx_code(WebDriver driver)
    	{  	 
        element = driver.findElement(By.xpath("//input[@id='authcode']"));
        return element;
    	}
	}