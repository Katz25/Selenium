/**
 * 
 */
package appModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class Find_Registration_Action 
	{

	private static WebDriver driver = null;
	
	public static void main(String[] args) 
		{			
		Boolean iselementpresent = driver.findElements(By.linkText("addrsvr2.hopewiser.com")).size()!= 0;		
		if (iselementpresent = true)
			{
			System.out.println("PHPMyAdmin Opened");
			}
		else
			{
			System.out.println("PHPMyAdmin Not Opened");
			}		
		System.out.println(iselementpresent);					
		driver.findElement(By.tagName("SQL")).click(); 										// Click the SQL tab
	}
}