package testRuns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import appModule.Login_Action;
import appModule.Output;
import appModule.Delete_Action;
import appModule.ScreenShot_Action;

import utility.Constant;
import utility.ExcelUtils;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class Run6 
	{
	public static void main(String[] args) throws Exception 
		{
		// Launch Chrome (Precondition)		
		String exePath = "C:\\Selenium\\chromedriver.exe";									// Set Variable for path Where Chrome.exe is stored
		System.setProperty("webdriver.chrome.driver", exePath);								// Set Path to open Chrome from
		WebDriver driver = new ChromeDriver();												// Open a new instance of Chrome
		String ReportText = "1) Run6 Chrome Launched";										// Set up report text variable
		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				
		// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)				
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");	// Look in the Excel sheet for test data
				
		// Open the login website using the Url (Precondition)
		driver.get(Constant.URL);															// Open the login website using the Url
		ReportText = "2) " + Constant.URL + " Website Opened";								// Set up report text variable
		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				
		// 4.0 Login to Address Server in the cloud	
		Login_Action.Execute(driver);														// Login using the appModule Login_Action
		
		// Test if Login occurred
		String whatiscurrenturl = driver.getCurrentUrl();									// Set variable to the current web address
		String testurl1 = "https://services-sandbox.hopewiser.com/hostedmgr/home";			// Set variable to path
		String testurl2 = "https://services-sandbox.hopewiser.com/hostedmgr/logon";			// Set variable to path
		if (whatiscurrenturl.equals(testurl1))												// Test if Home page exists
			{
			ReportText = "3) Login Passed: " + whatiscurrenturl;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "LoginPass6_";														// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl.equals(testurl2))											// Test if logon page exists
				{
				ReportText = "3) Login Failed: " + whatiscurrenturl;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginFail6_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			else
				{
				ReportText = "3) Unknown Error: " + whatiscurrenturl;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginError6_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			}
				
		// 3.2 Click on the Required User				
		driver.findElement(By.xpath("//div/div/table[2]/tbody/tr[2]/td[5]/a")).click();		// Select the View/Edit button
		Thread.sleep(3000);																   	// Pause 3 seconds for page update
		
		// 3.2a Delete the User
		Delete_Action.Execute(driver);														// Delete the User using the appModule Delete_Action
		
		// Test if delete occurred
		String whatiscurrenturlD = driver.getCurrentUrl();									// Set variable to the current web address
		String testurlD1 = "https://services-sandbox.hopewiser.com/hostedmgr/home";			// Set variable to path
		String testurlD2 = "https://services-sandbox.hopewiser.com/hostedmgr/account/";		// Set variable to path
		if (whatiscurrenturlD.contains(testurlD1))											// Test if home page exists
			{
			ReportText = "4) Delete Passed: " + whatiscurrenturlD;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "DeletePass6_";														// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturlD.contains(testurlD2))										// Test if account page exists
				{	
				ReportText = "4) Delete Failed: " + whatiscurrenturlD;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "DeleteFail6_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			else
				{
				ReportText = "4) Unknown Error: " + whatiscurrenturlD;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "DeleteError6_";												// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			}
				
		// 4.1 Log Off				
		driver.findElement(By.xpath("//a[@href='/hostedmgr/secure/logout']")).click(); 		// Click the Log Off link
		ReportText = "5) Logged off";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				
		// Close the Browser and kill task (Postcondition)
		driver.quit();																		// Close the Browser
		ReportText = "6) Closed Browser";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console			
		}
	}