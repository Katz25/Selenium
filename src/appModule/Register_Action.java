package appModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObject.Register;
import utility.ExcelUtils;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class Register_Action 
	{	
	public static void Execute(WebDriver driver) throws Exception
		{			
		//These lines get the values from the test data Excel sheet, passing parameters (Row number, Column number) to utility.ExcelUtils.getCellData method.		
		String sCompanyName = ExcelUtils.getCellData(1, 1);		
		String sCountry = ExcelUtils.getCellData(1, 2);		
		String sPostCode = ExcelUtils.getCellData(1, 3);		
		String sAddress = ExcelUtils.getCellData(1, 4);		
		String sTown = ExcelUtils.getCellData(1, 5);		
		String sCounty = ExcelUtils.getCellData(1, 6);		
		String sYourName = ExcelUtils.getCellData(1, 7);		
		String sEmailAddress = ExcelUtils.getCellData(1, 8);		
		String sDesiredUserName = ExcelUtils.getCellData(1, 9);		
		String sSetaPassword = ExcelUtils.getCellData(1, 10);		
		String sConfirmPassword = ExcelUtils.getCellData(1, 11);
		
		// These lines fill in the registration form.
		Register.txtbx_CompanyName(driver).sendKeys(sCompanyName);		
		Register.txtbx_Country(driver).sendKeys(sCountry);		
		Register.txtbx_PostCode(driver).sendKeys(sPostCode);		
		Register.txtbx_Address(driver).sendKeys(sAddress);		
		Register.txtbx_Town(driver).sendKeys(sTown);		
		Register.txtbx_County(driver).sendKeys(sCounty);		
		Register.txtbx_YourName(driver).sendKeys(sYourName);		
		Register.txtbx_EmailAddress(driver).sendKeys(sEmailAddress);	
		driver.findElement(By.id("login")).clear();		
		Register.txtbx_DesiredUserName(driver).sendKeys(sDesiredUserName);		
		Register.txtbx_SetaPassword(driver).sendKeys(sSetaPassword);		
		Register.txtbx_ConfirmPassword(driver).sendKeys(sConfirmPassword);
		String section = "Register_";										// Register variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, section);							// Register Screenshot using appModule.ScreenShot_Action
		
		// Click the button to save
		Register.btn_Register(driver).click();								// Click the Save button
		Thread.sleep(3000);													// Pause 3 seconds for page update
		String ReportText = "R) Registered "+ sDesiredUserName;				// Set up report text variable
		Output.FilePlusConsole(ReportText); 								// Print validation text to File and Console
		}
	}