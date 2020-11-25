package testRuns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import appModule.CreateUser_Action;
import appModule.Delete_Action;
import appModule.EditUser_Action;
import appModule.Login_Action;
import appModule.Output;
import appModule.ScreenShot_Action;
import appModule.Timeloop;
import utility.Constant;
import utility.ExcelUtils;
/*
 * Author: Maxine Flook
 * Selenium tests V1
 */
public class Run10 
	{	
	public static void main(String[] args) throws Exception 
		{		
		// Launch Chrome (Precondition)		
		String exePath = "C:\\Selenium\\chromedriver.exe";									// Set Variable for path Where Chrome.exe is stored
		System.setProperty("webdriver.chrome.driver", exePath);								// Set Path to open Chrome from
		WebDriver driver = new ChromeDriver();												// Open a new instance of Chrome
		String ReportText = "1) Run10 Chrome Launched";										// Set up report text variable
		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				
		// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)				
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet3");	// Look in the Excel sheet for test data
				
		// Open the login website using the Url (Precondition)
		driver.get(Constant.URL);															// Open the login website using the Url
		ReportText = "2) " + Constant.URL + " Website Opened";								// Set up report text variable
		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				
		// 4.0 Login to Address Server in the cloud
		Login_Action.Execute(driver);														// Login using the appModule Login_Action = 3)
		
		// Test if Login occurred
		String whatiscurrenturl = driver.getCurrentUrl();									// Set variable to the current web address
		String testurl1 = "https://services-sandbox.hopewiser.com/hostedmgr/home";			// Set Variable to path
		String testurl2 = "https://services-sandbox.hopewiser.com/hostedmgr/logon?error=true";// Set Variable to path
		if (whatiscurrenturl.contains(testurl1))											// Test if Home page exists
			{
			ReportText = "3) Login Passed: " + whatiscurrenturl;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "LoginPass10_";														// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl.contains(testurl2))										// Test if logon page exists
				{
				ReportText = "3) Login Failed: " + whatiscurrenturl;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginFail10_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			else
				{
				ReportText = "3) Unknown Error: " + whatiscurrenturl;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginError10_";												// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			}
				
        // Select the Hopewiser Tests account link				
		driver.findElement(By.xpath("//a[@href='/hostedmgr/acct']")).click();				// Click the account link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/acct");	// Ensure the correct page is displayed in a sensible time
				
		// Search for Hopewiser Test				
		driver.findElement(By.xpath("//input[@id='textfilteraccount-table']")).sendKeys("Hopewiser Test");  // Enter the user name
		Thread.sleep(3000);																   	// Pause 3 seconds for page update
		ReportText = "4) Searched for Hopewiser Test";										// Set up report text variable
		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
		
		// Select Hopewiser Test from the table					
		driver.findElement(By.xpath("//span[contains(.,'Hopewiser Test')]")).click();		// Click on Hopewiser Test link	
		Timeloop.Execute(driver, "/details");												// Ensure the correct page is displayed in a sensible time
	
		// Select Users tab				
		driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[5]/a")).click();  // Click Users tab
		Timeloop.Execute(driver, "/user");													// Ensure the correct page is displayed in a sensible time
				
        // Click on create a new button				
		driver.findElement(By.xpath("//a[contains(.,'Create New')]")).click();				// Click on create a new button
				
		// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method				
		ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet16");// Look in the Excel sheet for test data
				
		// 3.3 Create user
		CreateUser_Action.Execute(driver);													// Create the user using the appModule CreateUser_Actio
		
		// Test if creation occurred
		String whatiscurrenturlC = driver.getCurrentUrl();									// Set variable to the current web address
		String testurlC1 = "https://services-sandbox.hopewiser.com/hostedmgr/acct/";		// Set variable to path
		String testurlC2 = "https://services-sandbox.hopewiser.com/hostedmgr/home";			// Set variable to path
		if (whatiscurrenturlC.contains(testurlC1))											// Test if Home page exists
			{
			ReportText = "5) Creation Passed: " + whatiscurrenturlC;						// Set up report text variable
			Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "CreatePass10_";													// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturlC.contains(testurlC2))										// Test if logon page exists
				{	
				ReportText = "5) Creation Failed: " + whatiscurrenturlC;					// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "CreateFail10_";												// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			else
				{
				ReportText = "5) Unknown Error: " + whatiscurrenturlC;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "CreateError10_";												// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			}
				
        // Edit the user				
		driver.findElement(By.xpath("//a[contains(.,'Qatest-ca-ha')]")).click();			// Click the user
		Timeloop.Execute(driver, "/user");													// Ensure the correct page is displayed in a sensible time
				
		driver.findElement(By.xpath("//a[contains(.,'Edit')]")).click();					// Click the Edit button
		Timeloop.Execute(driver, "/edit");													// Ensure the correct page is displayed in a sensible time
				
        //This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method				
		ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet17");// Look in the Excel sheet for test data
				
		// 3.1a Ensure the user can be Edited
		EditUser_Action.Execute(driver);													// Edit the user using the appModule EditUser_Action
		
		// Test if edit occurred
		String whatiscurrenturlE = driver.getCurrentUrl();									// Set variable to the current web address
		String testurlE1 = "https://services-sandbox.hopewiser.com/hostedmgr/acct/";		// Set variable to path
		String testurlE2 = "https://services-sandbox.hopewiser.com/hostedmgr/home";			// Set variable to path
		if (whatiscurrenturlE.contains(testurlE1))											// Test if Account page exists
			{
			ReportText = "6) Edit Passed: " + whatiscurrenturlE;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "EditPass10_";														// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturlE.contains(testurlE2))										// Test if home page exists
				{	
				ReportText = "6) Edit Failed: " + whatiscurrenturlE;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "EditFail10_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			else
				{
				ReportText = "6) Unknown Error: " + whatiscurrenturlE;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "EditError10_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			}
				
        // Find the User				
		driver.findElement(By.xpath("//input[@id='textfilteruser-table']")).sendKeys("Qatest-ca-ha");  // Enter the user to be found
		Thread.sleep(3000);																   	// Pause 3 seconds for page update
		
		// Select the User
		driver.findElement(By.xpath("//span[contains(.,'Qatest-ca-ha')]")).click();			// Click the user
		Timeloop.Execute(driver, "/details");												// FF Ensure the correct page is displayed in a sensible time
		
		// 3.2a Delete the user
		Delete_Action.Execute(driver);														// Delete the user using the appModule Delete_Action
		
		// Test if delete occurred
		String whatiscurrenturlD = driver.getCurrentUrl();									// Set variable to the current web address
		String testurlD1 = "https://services-sandbox.hopewiser.com/hostedmgr/acct/";		// Set variable to path (before & after delete pages are /acct/ pages
		if (whatiscurrenturlD.contains(testurlD1))											// Test if Account page exists
			{
			ReportText = "7) Delete Passed: " + whatiscurrenturlD;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "DeletePass10_";													// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
			try 
				{	
				driver.findElement(By.xpath("//span[contains(.,'Qatest-ca-ha')]")).click();// Cost Centre Test should not exist so exception should be raised
				}
			catch (Exception e)
				{
				ReportText = "8) Qatest-ca-ha is deleted";									// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				}
			ReportText = "9) if 8 is missing Qatest-ca-ha has not been deleted";			// Set up report text variable
			Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
			}
		else
			{	
			ReportText = "7) Unknown Error: " + whatiscurrenturlD;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "DeleteError10_";													// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
			System.exit(1);																	// Drop out of script			
			}
				
		// 4.1 Log Off
		driver.findElement(By.xpath("//a[@href='/hostedmgr/secure/logout']")).click(); 		// Click the Log Off link
		ReportText = "10) Logged off";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
		
		// Close the Browser and kill task (Postcondition)
		driver.quit();																		// Close the Browser
		ReportText = "11) Closed Browser";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console					
		}
	}