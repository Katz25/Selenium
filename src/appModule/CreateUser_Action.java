package appModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObject.CreateUser;
import utility.ExcelUtils;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class CreateUser_Action 
	{
	public static void Execute(WebDriver driver) throws Exception
		{	
		//These lines get the values from the test data Excel sheet, passing parameters (Row number, Column number) to utility.ExcelUtils.getCellData method.	
		String sName = ExcelUtils.getCellData(1, 1);		
		String sEmailAddress = ExcelUtils.getCellData(1, 2);		
		String sUserName = ExcelUtils.getCellData(1, 3);		
		String sPassword = ExcelUtils.getCellData(1, 4);		
		String sConfirmPassword = ExcelUtils.getCellData(1, 5);		
		String sAdminLevel = ExcelUtils.getCellData(1, 6);		
		String sDefaultAddressReference = ExcelUtils.getCellData(1, 7);		
		
		// These lines fill in the form.
		CreateUser.txtbx_Name(driver).sendKeys(sName);		
		CreateUser.txtbx_EmailAddress(driver).sendKeys(sEmailAddress);
		driver.findElement(By.id("login")).clear();		
		CreateUser.txtbx_UserName(driver).sendKeys(sUserName);		
		CreateUser.txtbx_Password(driver).sendKeys(sPassword);		
		CreateUser.txtbx_ConfirmPassword(driver).sendKeys(sConfirmPassword);		
		CreateUser.txtbx_AdminLevel(driver).sendKeys(sAdminLevel);		
		CreateUser.txtbx_DefaultAddressReference(driver).sendKeys(sDefaultAddressReference);
		String SS = "Create_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Click the button to save
		CreateUser.btn_CreateUser(driver).click();												// Click the Create button
		Thread.sleep(3000);																   		// Pause 3 seconds for page update		
		String ReportText = "C) Created "+ sUserName;											// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		}
	}