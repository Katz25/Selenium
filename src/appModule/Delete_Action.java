package appModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class Delete_Action 
	{	
	public static void Execute(WebDriver driver) throws Exception
		{		
		// Delete the required User
		//example: https://services-sandbox.hopewiser.com/hostedmgr/acct/319/cost/549/user/1538/details
		String section = "DeleteD1_";												// Identify the first delete button page
		ScreenShot_Action.Execute(driver, section);									// Delete Screenshot using appModule.ScreenShot_Action
		driver.findElement(By.partialLinkText("Delete")).click();					// Click the delete button
		Thread.sleep(3000);															// Pause 3 seconds for page update

		//example: https://services-sandbox.hopewiser.com/hostedmgr/acct/319/cost/549/user/1538/delete
		section = "DeleteD2_";														// Identify the second delete button page
		ScreenShot_Action.Execute(driver, section);									// Delete Screenshot using appModule.ScreenShot_Action
		driver.findElement(By.xpath("//input[@class='ym-button ym-danger']")).click();  // Click the delete button
		Thread.sleep(3000);															// Pause 3 seconds for page update
		// Example: https://services-sandbox.hopewiser.com/hostedmgr/acct/319/cost/549/user
		}
	}