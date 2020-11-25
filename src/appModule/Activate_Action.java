package appModule;

import org.openqa.selenium.WebDriver;

import pageObject.Activate;
import utility.ExcelUtils;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class Activate_Action 
	{	
	public static void Execute(WebDriver driver) throws Exception
		{		
		// These lines get the values from the test data Excel sheet, passing parameters (Row number, Column number) to utility.ExcelUtils.getCellData method.		
		String sUserName = ExcelUtils.getCellData(1, 1);		
		String sPassword = ExcelUtils.getCellData(1, 2);
		
		// These lines fill in the Login form.
		Activate.txtbx_UserName(driver).sendKeys(sUserName); 				// Enter the Username		
		Activate.txtbx_Password(driver).sendKeys(sPassword); 				// Enter the Password
		String SS = "Activate_";											// Variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);								// Screenshot using appModule.ScreenShot_Action
		
		// Activate the Registration
		//<input type="submit" class="ym-button ym-danger" value="Activate">
		Activate.btn_LogIn(driver).click();  								// Click the Activate button
		Thread.sleep(3000);													// Pause 3 seconds for page update		
		String ReportText = "3) Activate Registration with "+ sUserName;	// Set up report text variable
		Output.FilePlusConsole(ReportText); 								// Print validation text to File and Console	
		}
	}