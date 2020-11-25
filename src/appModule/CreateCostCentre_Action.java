package appModule;

import org.openqa.selenium.WebDriver;
import pageObject.CreateCC;
import utility.ExcelUtils;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class CreateCostCentre_Action 
	{	
	public static void Execute(WebDriver driver) throws Exception
		{		
		// These lines get the values from the test data Excel sheet, passing parameters (Row number, Column number) to utility.ExcelUtils.getCellData method.		
		String sCostCentre = ExcelUtils.getCellData(1, 3);		
		
		// These lines fill in the CostCentre form.
		CreateCC.txtbx_Name(driver).sendKeys(sCostCentre);						// Enter cost centre name
		String SS = "CostCentre_";											// Login variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);								// Login Screenshot using appModule.ScreenShot_Action
		
		// Create a Cost Centre
		CreateCC.btn_CreateCC(driver).click();  								// Click the Create button
		Thread.sleep(3000);														// Pause 3 seconds for page update		
		String ReportText = "CC) Created a Cost Centre called: "+ sCostCentre;	// Set up report text variable
		Output.FilePlusConsole(ReportText); 									// Print validation text to File and Console
		}
	}