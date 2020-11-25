package appModule;

import appModule.Output;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObject.CreatePlan;
import utility.ExcelUtils;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class EditPlan_Action 
	{	
	public static void Execute(WebDriver driver,int n) throws Exception
		{		
		// These lines get the values from the test data Excel sheet, passing parameters (Row number, Column number) to utility.ExcelUtils.getCellData method.		
		String sPlanName = ExcelUtils.getCellData(1, 1);
		String sDesc = ExcelUtils.getCellData(1, 2);
		//String sMAF = ExcelUtils.getCellData(1, 3);
		//String sClicks = ExcelUtils.getCellData(1, 4);
		//String sDuration = ExcelUtils.getCellData(1, 5);
		//String sCalc = ExcelUtils.getCellData(1, 6);
		//String sPricing = ExcelUtils.getCellData(1, 6);
		
		// These lines fill in the Create/Edit Account form.
		CreatePlan.txtbx_Name(driver).sendKeys(sPlanName);										// Enter value into Plan field
		CreatePlan.txtbx_Description(driver).sendKeys(sDesc);									// Enter Description		
		//CreatePlan.cbobx_MAF(driver).sendKeys(sMAF);											// Select MAF
		//CreatePlan.txtbx_Clicks(driver).sendKeys(sClicks);										// Enter Clicks
		//CreatePlan.txtbx_Duration(driver).sendKeys(sDuration);									// Enter Duration
		//CreatePlan.txtbx_Calc(driver).sendKeys(sCalc);											// Enter Type of Calculator
		//CreatePlan.txtbx_Pricing(driver).sendKeys(sPricing);									// Enter Pricing
		
		//<input id="startdate" name="startdate" type="date" value="">
 		LocalDate Today = LocalDate.now();														// The current date
 		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");				// Create object of SimpleDateFormat class and decide the format 
 		String Date = dateFormat.format(Today);													// Now format the date
		String ReportText = "C) Start date: "+Date;												// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		driver.findElement(By.xpath("//input[@id='startdate']")).sendKeys(Date);  				// Enter the Start Date
		
		//<input id="enddate" name="enddate" required="required" type="date" value="">
 		LocalDate Today2 = LocalDate.now();														// The current date
 		LocalDate in8days = Today2.plus(n, ChronoUnit.DAYS);									// Add 8 days to the current date
 		dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");									// Create object of SimpleDateFormat class and decide the format 
 		Date = dateFormat.format(in8days);														// Now format the date
		ReportText = "C) plus date:"+Date;														// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		driver.findElement(By.xpath("//input[@id='enddate']")).sendKeys(Date);  				// Enter the End Date
				
		String SS = "Plan_";																	// Set variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Login Screenshot using appModule.ScreenShot_Action
		
		// Create an Account
		CreatePlan.btn_CreatePlan(driver).click();  											// Click the Create button
		Thread.sleep(3000);																		// Pause 3 seconds for page update		
		ReportText = "C) Created an Account called: "+ sPlanName;								// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		}
	}