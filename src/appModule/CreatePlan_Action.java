package appModule;

import appModule.Output;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pageObject.CreatePlan;
import utility.ExcelUtils;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class CreatePlan_Action 
	{	
	public static void Execute(WebDriver driver, int n) throws Exception
		{		
		// These lines get the values from the test data Excel sheet, passing parameters (Row number, Column number) to utility.ExcelUtils.getCellData method.		
		String sPlanName = ExcelUtils.getCellData(1, 1);
		String sDesc = ExcelUtils.getCellData(1, 2);
		//String sMAF = ExcelUtils.getCellData(1, 3);
		String sDatasetRef = ExcelUtils.getCellData(1, 4);
		String sDuration = ExcelUtils.getCellData(1, 5);
		String rDuration = sDuration.replaceAll("[{}]","");										// Turns the spreadsheet int into a string
		String sCalc = ExcelUtils.getCellData(1, 6);
		String sPricing = ExcelUtils.getCellData(1, 7);
		
		// These lines fill in the Create/Edit Account form.
		CreatePlan.txtbx_Name(driver).sendKeys(sPlanName);										// Enter value into Plan field
		CreatePlan.txtbx_Description(driver).sendKeys(sDesc);									// Enter Description		
		CreatePlan.cbobx_MAF(driver).sendKeys("UK Postal Address File");						// Select MAF
		CreatePlan.txtbx_sDatasetRef(driver).sendKeys(sDatasetRef);								// Enter sDatasetRef
		CreatePlan.txtbx_Duration(driver).sendKeys(rDuration);									// Enter Duration
		CreatePlan.txtbx_Calc(driver).sendKeys(sCalc);											// Enter Type of Calculator
		CreatePlan.txtbx_Pricing(driver).sendKeys(sPricing);									// Enter Pricing
		
		//<input name="availstart" class="hasDatepicker" id="availstart" required="required" type="date" value="">
 		LocalDate Today = LocalDate.now();														// The current date
 		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");				// Create object of SimpleDateFormat class and decide the format 
 		String Date = dateFormat.format(Today);													// Now format the date
		String ReportText = "C) Start date: "+Date;												// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		driver.findElement(By.xpath("//input[@id='availstart']")).sendKeys(Date + Keys.TAB);  	// Enter the Start Date
		
		//<input name="availend" class="hasDatepicker" id="availend" type="date" value="">
 		LocalDate in8days = Today.plus(n, ChronoUnit.DAYS);										// Add 8 days to the current date
 		String EndDate = dateFormat.format(in8days);											// Now format the date
		ReportText = "C) End date: "+EndDate;													// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		driver.findElement(By.xpath("//input[@id='availend']")).sendKeys(EndDate);  			// Enter the End Date		
		String SS = "Plan_";																	// Set variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Create an Account
		CreatePlan.btn_CreatePlan(driver).click();  											// Click the Create button
		Thread.sleep(3000);																		// Pause 3 seconds for page update		
		ReportText = "C) Created an Account called: "+ sPlanName;								// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		}
	}