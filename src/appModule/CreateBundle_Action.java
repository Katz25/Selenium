package appModule;

import appModule.Output;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pageObject.CreateBundle;
import utility.ExcelUtils;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class CreateBundle_Action 
	{	
	public static void Execute(WebDriver driver,int n)throws Exception
		{		
		// These lines get the values from the test data Excel sheet, passing parameters (Row number, Column number) to utility.ExcelUtils.getCellData method.		
		String sCostCentre = ExcelUtils.getCellData(1, 2);
		String sPlan = ExcelUtils.getCellData(1, 3);
		String sBundleType = ExcelUtils.getCellData(1, 4);
		String sClicks = ExcelUtils.getCellData(1, 6);											// Doesn't turn numeric excel value into String
		String rClicks = sClicks.replaceAll("[{}]","");											// Turns the spreadsheet int into a string
		
		// These lines fill in the Create/Edit Account form.
		CreateBundle.cbobx_CC(driver).sendKeys(sCostCentre);									// Enter value into Cost Centre field
		//String ReportText = "C) Cost Centre: "+ sCostCentre;									// Set up report text variable
		//Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		CreateBundle.cbobx_Plan(driver).sendKeys(sPlan);										// Select Plan
		//ReportText = "C) Plan: "+ sPlan;														// Set up report text variable
		//Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		CreateBundle.cbobx_BT(driver).sendKeys(sBundleType);									// Enter value into Bundle Type field
		//ReportText = "C) Bundle Type: " + sBundleType;										// Set up report text variable
		//Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		//String ReportText = "No. Clicks: "+ rClicks;											// Set up report text variable
		//Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		CreateBundle.txtbx_Clicks(driver).sendKeys(rClicks);									// Enter value into Clicks field		

		//<input id="startdate" name="startdate" type="date" value="">
 		LocalDate Today = LocalDate.now();														// The current date
 		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");				// Create object of SimpleDateFormat class and decide the format 
 		String Date = dateFormat.format(Today);													// Now format the date
		String ReportText = "C) Start date: "+Date;												// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		driver.findElement(By.xpath("//input[@id='startdate']")).sendKeys(Date);  				// Enter the Start Date
		
		//<input id="startdate" name="startdate" type="date" value="">
 		LocalDate in8days = Today.plus(n, ChronoUnit.DAYS);										// Add 8 days to the current date
 		Date = dateFormat.format(in8days);														// Now format the date
		ReportText = "C) plus date: "+Date;														// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		driver.findElement(By.xpath("//input[@id='enddate']")).sendKeys(Date);  				// Enter the End Date
		// Create Screenshot png file in C:\Users\User\Documents\Automation results	
		String SS = "CreateBundle_";															// Set variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Screenshot using appModule.ScreenShot_Action
		
		// Create a Bundle
		CreateBundle.btn_CreateBundle(driver).click();  										// Click the Create button
		Thread.sleep(5000);																		// Pause 5 seconds for page update		
		ReportText = "C) Created a Bundle";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		}
	}