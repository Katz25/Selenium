package appModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObject.EditUser;
import utility.ExcelUtils;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class EditUserCostCentreAdmin_Action 
	{	
	public static void Execute(WebDriver driver) throws Exception
		{	
		//These lines get the values from the test data Excel sheet, passing parameters (Row number, Column number) to utility.ExcelUtils.getCellData method.	
		String sName = ExcelUtils.getCellData(1, 1);	
		String sEmailAddress = ExcelUtils.getCellData(1, 2);		
		String sAdminLevel = ExcelUtils.getCellData(1, 3);	
		String sDefaultAddressReference = ExcelUtils.getCellData(1, 4);

		// These lines fill in the form.
		driver.findElement(By.xpath("//input[@id='name']")).clear();  						// Clear the name field			
		EditUser.txtbx_Name(driver).sendKeys(sName);  										// Enter the Company Name from TestData1 file	
		driver.findElement(By.xpath("//input[@id='notifyemail']")).clear();	 				// Clear the Email Address field
		EditUser.txtbx_EmailAddress(driver).sendKeys(sEmailAddress); 						// Enter the email address from TestData1 file
		EditUser.txtbx_AdminLevel(driver).sendKeys(sAdminLevel); 							// Enter the Admin Level from TestData1 file		
		EditUser.txtbx_DefaultAddressReference(driver).sendKeys(sDefaultAddressReference);	// Enter the Default Address Reference from TestData1 file
		String SS = "EditCC_";																// Edit variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);												// Edit Screenshot using appModule.ScreenShot_Action
		
		// Save the User details
		EditUser.btn_EditUser(driver).click();												// Click the Save button
		Thread.sleep(3000);																   	// Pause 3 seconds for page update	
		String ReportText = "E) Edited "+ sName;											// Set up report text variable
		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
		}
	}