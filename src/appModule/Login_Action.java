package appModule;

import appModule.Output;
import org.openqa.selenium.WebDriver;
import pageObject.Homepage;
import pageObject.Login;
import utility.ExcelUtils;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class Login_Action 
	{	
	public static void Execute(WebDriver driver) throws Exception
		{		
		// These lines get the values from the test data Excel sheet, passing parameters (Row number, Column number) to utility.ExcelUtils.getCellData method.		
		String sUserName = ExcelUtils.getCellData(1, 1);		
		String sPassword = ExcelUtils.getCellData(1, 2);
		
		// These lines fill in the Login form.
		Homepage.lnk_MyAccount(driver).click();  				// Click the Home link.		
		Login.txtbx_UserName(driver).sendKeys(sUserName); 		// Enter the username		
		Login.txtbx_Password(driver).sendKeys(sPassword); 		// Enter the password
		String SS = "Login_";									// Login variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);					// Login Screenshot using appModule.ScreenShot_Action
		
		// Login
		Login.btn_LogIn(driver).click();  						// Click the login button
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/home");		// FF Ensure the correct page is displayed in a sensible time
		String ReportText = "L) Logged in with "+ sUserName;	// Set up report text variable
		Output.FilePlusConsole(ReportText); 					// Print validation text to File and Console
		}
	}