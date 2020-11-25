package appModule;

import appModule.Output;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObject.CreateBundle;
import utility.ExcelUtils;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class EditBundle_Action 
	{	
	public static void Execute(WebDriver driver,int n) throws Exception
		{		
		// These lines get the values from the test data Excel sheet, passing parameters (Row number, Column number) to utility.ExcelUtils.getCellData method.		
		//String sCostCentre = ExcelUtils.getCellData(1, 2);
		//String sPlan = ExcelUtils.getCellData(1, 3);
		//String sBundleType = ExcelUtils.getCellData(1, 4);
		String sClicks = ExcelUtils.getCellData(1, 6);
		String rClicks = sClicks.replaceAll("[{}]","");
		
		// These lines fill in the Create/Edit Account form.
		CreateBundle.txtbx_Clicks(driver).clear();												// Empty value from Clicks field
		CreateBundle.txtbx_Clicks(driver).sendKeys(rClicks);									// Enter value into Clicks field
		
		//<input id="startdate" name="startdate" type="date" value="">
 		LocalDate Today = LocalDate.now();														// The current date
 		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");				// Create object of SimpleDateFormat class and decide the format 
 		String Date = dateFormat.format(Today);													// Now format the date
		String ReportText = "C) Start date: "+Date;												// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		driver.findElement(By.xpath("//input[@id='startdate']")).sendKeys(Date);  				// Enter the Start Date

		//<input id="enddate" name="enddate" required="required" type="date" value="">
 		LocalDate Today2 = LocalDate.now();														// The current date
 		LocalDate in8days = Today2.plus(n, ChronoUnit.DAYS);									// Add 8 days to the current date
 		Date = dateFormat.format(in8days);														// Now format the date
		ReportText = "C) plus date:"+Date;														// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		driver.findElement(By.xpath("//input[@id='enddate']")).sendKeys(Date);  				// Enter the End Date
		
		String SS = "EditBundle_";																// Set variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Screenshot using appModule.ScreenShot_Action
		
		// Create a Bundle
		CreateBundle.btn_SaveBundle(driver).click();  											// Click the Create button
		Thread.sleep(3000);																		// Pause 3 seconds for page update		
		ReportText = "E) Edited Bundle";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		}
	}