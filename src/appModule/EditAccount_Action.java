package appModule;

import appModule.Output;
import org.openqa.selenium.WebDriver;
import pageObject.CreateAccount;
import utility.ExcelUtils;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class EditAccount_Action 
	{	
	public static void Execute(WebDriver driver) throws Exception
		{		
		// These lines get the values from the test data Excel sheet, passing parameters (Row number, Column number) to utility.ExcelUtils.getCellData method.		
		String sAccountName = ExcelUtils.getCellData(1, 1);
		String sCountry = ExcelUtils.getCellData(1, 2);
		String sPostCode = ExcelUtils.getCellData(1, 3);
		String sAddress = ExcelUtils.getCellData(1, 4);
		String sTown = ExcelUtils.getCellData(1, 5);
		String sCounty = ExcelUtils.getCellData(1, 6);	
		
		// These lines fill in the Create/Edit Account form.
		CreateAccount.txtbx_Name(driver).clear();							// Clear Company field
		CreateAccount.txtbx_Name(driver).sendKeys(sAccountName);			// Enter value into Company field
		CreateAccount.cbobx_Country(driver).sendKeys(sCountry);				// Select Country
		CreateAccount.txtbx_PostCode(driver).clear();						// Clear Post Code field		
		CreateAccount.txtbx_PostCode(driver).sendKeys(sPostCode);			// Enter value into Post Code field
		CreateAccount.txtbx_Address(driver).clear();						// Clear Address field
		CreateAccount.txtbx_Address(driver).sendKeys(sAddress);				// Enter value into Address field
		CreateAccount.txtbx_Town(driver).clear();							// Clear City field
		CreateAccount.txtbx_Town(driver).sendKeys(sTown);					// Enter value into City field
		CreateAccount.txtbx_County(driver).clear();							// Clear County field
		CreateAccount.txtbx_County(driver).sendKeys(sCounty);				// Enter value into County field
		CreateAccount.checkbx_TandC(driver).click();						// Tick the Terms and Conditions check box
		//CreateAccount.checkbx_TandC(driver).click();						// Tick the Terms and Conditions check box
		
		String SS = "Company_";												// Login variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);								// Login Screenshot using appModule.ScreenShot_Action
		
		// Create a Cost Centre
		CreateAccount.btn_CreateAccount(driver).click();  					// Click the Create button
		Thread.sleep(3000);													// Pause 3 seconds for page update		
		String ReportText = "E) Edited an Account called: "+ sAccountName;	// Set up report text variable
		Output.FilePlusConsole(ReportText); 								// Print validation text to File and Console
		}
	}