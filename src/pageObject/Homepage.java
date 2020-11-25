package pageObject;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class Homepage {
	
	private static WebElement element = null;

	public static WebElement lnk_MyAccount(WebDriver driver){	
		element = driver.findElement(By.id("j_username"));	
		return element;	
	}
	
	public static WebElement lnk_LogOut(WebDriver driver){	
		element = driver.findElement(By.xpath("//a[@href='/hostedmgr/secure/logout']"));	
		return element;	
	}

}
