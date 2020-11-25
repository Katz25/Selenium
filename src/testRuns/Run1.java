package testRuns;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;

import utility.Constant;
import utility.ExcelUtils;

import appModule.Output;
import appModule.Register_Action;
import appModule.ScreenShot_Action;
/*
 * Author: Maxine Flook
 * Selenium tests V1
 */
public class Run1 
	{	
	public static void main(String[] args) throws Exception 
		{
		// Display Start date
		LocalDate Today = LocalDate.now();														// The current date
 		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");				// Create object of SimpleDateFormat class and decide the format 
 		String Date = dateFormat.format(Today);													// Now format the date
		String ReportText = "Todays Date: "+Date;												// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// Launch Chrome (Precondition)
		String exePath = "C:\\Selenium\\chromedriver.exe";										// Set Variable for path Where Chrome.exe is stored
		System.setProperty("webdriver.chrome.driver", exePath);									// Set Path to open Chrome from
		WebDriver driver = new ChromeDriver();													// Open a new instance of Chrome
		ReportText = "1) Run1 Chrome Launched";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
		ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet1");	// Look in the Excel sheet for test data
		
		// Register for Address Server in the cloud  (Precondition) 	
		driver.get(Constant.URL2); 																// Open the registration website using the Url		
		ReportText = "2) " + Constant.URL2 + " Website Opened";									// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
        driver.findElement(By.xpath("//input[@id='agreeterms']")).click();						// Go to My Account
		Thread.sleep(3000);																		// Pause 3 seconds for page update
		
		// 1.1 Fill in the register new account dialog using appModule Register_Action		
		Register_Action.Execute(driver);														// Fill in the register new account dialog using appModule Register_Action
		
		// Test if title of page is https://services-sandbox.hopewiser.com/hostedmgr/register-success or https://services-sandbox.hopewiser.com/hostedmgr/register-fail
		String whatiscurrenturl = driver.getCurrentUrl();										// Set variable to the current web address
		String testurl1 = "https://services-sandbox.hopewiser.com/hostedmgr/register-success";	// Set variable to path
		String testurl2 = "https://services-sandbox.hopewiser.com/hostedmgr/register-fail";		// Set variable to path
		if (whatiscurrenturl.contains(testurl1))												// Test if Success page exists
			{
			ReportText = "3.0) Registration Passed";											// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console	
			ReportText = "3.1) Please wait for the Hopewiser Account Activation email.";		// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			ReportText = "3.2) Please check the Mapped drive U is accessible before starting the rest of the test suite."; // Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "RegPass1_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver,SS);												// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{
			ReportText = "3.0) Registration Failed";											// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			if (whatiscurrenturl.contains(testurl2))											// Test if fail page exists
				{
				ReportText = "3.1) Already Registered";											// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				ReportText = "3.2) Delete the users and the QA Testing Account from the database and try Run1 again";	// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "RegFail1_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver,SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			else
				{
				ReportText = "3.1) Unknown Error: " + whatiscurrenturl;							// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "RegError1_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver,SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			}
		
		// Close the Browser and kill task (Postcondition)
		driver.quit();																			// Close the Browser
		ReportText = "4) Closed Browser";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		}
	}