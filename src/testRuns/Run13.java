package testRuns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import appModule.CreateUser_Action;
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
public class Run13 
	{
    public static void main(String[] args) throws Exception 
    	{
    	// Launch Chrome  (Precondition)
    	String exePath = "C:\\Selenium\\chromedriver.exe";										// Set Variable for path Where Chrome.exe is stored
    	System.setProperty("webdriver.chrome.driver", exePath);									// Set Path to open Chrome from
    	WebDriver driver = new ChromeDriver();													// Open a new instance of Chrome
		String ReportText = "1) Run13 Chrome Launched";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
    	
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition) 	
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet3");		// Look in the Excel sheet for test data
    	
    	// Open the login website using the Url (Precondition)
    	driver.get(Constant.URL);																// Open the login website using the Url
		ReportText = "2) " + Constant.URL + " Website Opened";									// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
    	
		// 4.0 Login to Address Server in the cloud
		Login_Action.Execute(driver);															// Login using the appModule Login_Action
		
		// Test if Login occurred
		String whatiscurrenturl = driver.getCurrentUrl();										// Set variable to the current web address
		String testurl1 = "https://services-sandbox.hopewiser.com/hostedmgr/home";				// Set Variable to path
		String testurl2 = "https://services-sandbox.hopewiser.com/hostedmgr/logon";				// Set Variable to path
		if (whatiscurrenturl.equals(testurl1))													// Test if Home page exists
			{
			ReportText = "3) Login Passed: " + whatiscurrenturl;								// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "LoginPass13_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl.equals(testurl2))												// Test if logon page exists
				{
				ReportText = "3) Login Failed: " + whatiscurrenturl;							// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results 
				String SS = "LoginFail13_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			else
				{
				ReportText = "3) Unknown Error: " + whatiscurrenturl;							// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginError13_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			}
    	
    	// Select the Accounts link   	
    	driver.findElement(By.xpath("//a[@href='/hostedmgr/acct']")).click();					// Click the Accounts link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/acct");		// Ensure the correct page is displayed in a sensible time
    	
    	// Search for Hopewiser Test   	
    	driver.findElement(By.xpath("//input[@id='textfilteraccount-table']")).sendKeys("Hopewiser Test"); // Enter Hopewiser Test for searching
    	Thread.sleep(3000);																   		// Pause 3 seconds for page update
		ReportText = "4) Searched for Hopewiser Test";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// Select Hopewiser Test from the table	   	
    	driver.findElement(By.xpath("//span[contains(.,'Hopewiser Test')]")).click();			// Click Hopewiser Test link	
		Timeloop.Execute(driver, "/details");													// Ensure the correct page is displayed in a sensible time
    	
    	// Select Users tab	   	
    	driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[5]/a")).click(); // Click Users tab
		Timeloop.Execute(driver, "/user");														// Ensure the correct page is displayed in a sensible time
    	
        // Create a new user    	
    	driver.findElement(By.xpath("//a[contains(.,'Create New')]")).click();  				// Click Create New Button
    	
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method   	
    	ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet22");	// Look in the Excel sheet for test data
    	
    	// 3.3 Create user
    	CreateUser_Action.Execute(driver);														// Create the user using the appModule CreateUser_Action
    	
		// Test if creation occurred
		String whatiscurrenturlC = driver.getCurrentUrl();										// Set variable to the current web address
		String testurlC1 = "https://services-sandbox.hopewiser.com/hostedmgr/acct/";			// Set variable to path
		String testurlC2 = "https://services-sandbox.hopewiser.com/hostedmgr/home";				// Set variable to path
		if (whatiscurrenturlC.contains(testurlC1))												// Test if acct page exists
			{
			ReportText = "5) Creation Passed: " + whatiscurrenturlC;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "CreatePass13_";														// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturlC.contains(testurlC2))											// Test if home page exists
				{	
				ReportText = "5) Creation Failed: " + whatiscurrenturlC;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "CreateFail13_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			else
				{
				ReportText = "5) Unknown Error: " + whatiscurrenturlC;							// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "CreateError13_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			}
    	
        // Select the Accounts link    	
    	driver.findElement(By.xpath("//a[@href='/hostedmgr/acct']")).click(); 					// Click the Accounts link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/acct");		// Ensure the correct page is displayed in a sensible time
    	
    	// Search for Hopewiser Test    	
    	driver.findElement(By.xpath("//input[@id='textfilteraccount-table']")).sendKeys("Hopewiser Test"); // Enter Hopewiser Test for searching
    	Thread.sleep(3000);																   		// Pause 3 seconds for page update
		ReportText = "6) Searched for Hopewiser Test";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// Select Hopewiser Test from the table	     	
    	driver.findElement(By.xpath("//span[contains(.,'Hopewiser Test')]")).click();			// Click Hopewiser Test link
		Timeloop.Execute(driver, "/details");													// Ensure the correct page is displayed in a sensible time
    	
    	// Select Users tab   	
    	driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[5]/a")).click(); // Click Users tab
		Timeloop.Execute(driver, "/user");														// Ensure the correct page is displayed in a sensible time
    	
        // Create a new user    	
    	driver.findElement(By.xpath("//a[contains(.,'Create New')]")).click();  				// Click Create New Button
    	
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method    	
    	ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet23");	// Look in the Excel sheet for test data
    	
    	// 3.3 Create user for Run 14 and 15
    	CreateUser_Action.Execute(driver);														// Create the user using the appModule CreateUser_Action
    	
		// Test if creation occurred
		String whatiscurrenturlC2 = driver.getCurrentUrl();										// Set variable to the current web address
		String testurlC3 = "https://services-sandbox.hopewiser.com/hostedmgr/acct/";			// Set variable to path
		String testurlC4 = "https://services-sandbox.hopewiser.com/hostedmgr/home";				// Set variable to path
		if (whatiscurrenturlC2.contains(testurlC3))												// Test if acct page exists
			{
			ReportText = "7) Creation Passed: " + whatiscurrenturlC2;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "CreatePass13_";														// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturlC.contains(testurlC4))											// Test if home page exists
				{	
				ReportText = "7) Creation Failed: " + whatiscurrenturlC2;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "CreateFail13_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			else
				{
				ReportText = "7) Unknown Error: " + whatiscurrenturlC2;							// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "CreateError13_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			}
    	
        // Select the Accounts link    	
    	driver.findElement(By.xpath("//a[@href='/hostedmgr/acct']")).click(); 					// Click the Accounts link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/acct");		// Ensure the correct page is displayed in a sensible time
    	
    	// Search for Hopewiser Test   	
    	driver.findElement(By.xpath("//input[@id='textfilteraccount-table']")).sendKeys("Hopewiser Test"); // Enter Hopewiser Test for searching
    	Thread.sleep(3000);																   		// Pause 3 seconds for page update
		ReportText = "8) Searched for Hopewiser Test";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// Select Hopewiser Test from the table	    	
    	driver.findElement(By.xpath("//span[contains(.,'Hopewiser Test')]")).click();			// Click Hopewiser Test link
		Timeloop.Execute(driver, "/details");													// Ensure the correct page is displayed in a sensible time
    	
    	// Select Users tab	
    	driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[5]/a")).click(); // Click Users tab
		Timeloop.Execute(driver, "/user");														// Ensure the correct page is displayed in a sensible time
    	
        // Create a new user    	
    	driver.findElement(By.xpath("//a[contains(.,'Create New')]")).click(); 					// Click Create New Button
    	
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method   	
    	ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet24");	// Look in the Excel sheet for test data
    	
    	// 3.3 Create the user for Run 17
    	CreateUser_Action.Execute(driver);														// Create the user using the appModule CreateUser_Action
    	
		// Test if creation occurred
		String whatiscurrenturlC3 = driver.getCurrentUrl();										// Set variable to the current web address
		String testurlC5 = "https://services-sandbox.hopewiser.com/hostedmgr/acct/";			// Set variable to path
		String testurlC6 = "https://services-sandbox.hopewiser.com/hostedmgr/home";				// Set variable to path
		if (whatiscurrenturlC3.contains(testurlC5))												// Test if acct page exists
			{
			ReportText = "9) Creation Passed: " + whatiscurrenturlC3;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "CreatePass13_";														// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturlC3.contains(testurlC6))											// Test if home page exists
				{	
				ReportText = "9) Creation Failed: " + whatiscurrenturlC3;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "CreateFail13_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			else
				{
				ReportText = "9) Unknown Error: " + whatiscurrenturlC3;							// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "CreateError13_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			}
    	
        // Select the Accounts link   	
    	driver.findElement(By.xpath("//a[@href='/hostedmgr/acct']")).click();					// Click the Accounts link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/acct");		// Ensure the correct page is displayed in a sensible time
    	
    	// Search for Hopewiser Test    	
    	driver.findElement(By.xpath("//input[@id='textfilteraccount-table']")).sendKeys("Hopewiser Test"); // Enter Hopewiser Test for searching
    	Thread.sleep(3000);																   		// Pause 3 seconds for page update
		ReportText = "10) Searched for Hopewiser Test";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// Select Hopewiser Test from the table	  	
    	driver.findElement(By.xpath("//span[contains(.,'Hopewiser Test')]")).click();			// Click Hopewiser Test link
		Timeloop.Execute(driver, "/details");													// Ensure the correct page is displayed in a sensible time
    	
    	// Select Users tab  	
    	driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[5]/a")).click();	// Click Users tab
		Timeloop.Execute(driver, "/user");														// Ensure the correct page is displayed in a sensible time
    	
        // Create a new user   	
    	driver.findElement(By.xpath("//a[contains(.,'Create New')]")).click();					// Click Create New button
    	
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method    	
    	ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet25");	// Look in the Excel sheet for test data
    	
    	// 3.3 Create user for Run 20
    	CreateUser_Action.Execute(driver);														// Create the user using the appModule CreateUser_Action
    	
		// Test if creation occurred
		String whatiscurrenturlC4 = driver.getCurrentUrl();										// Set variable to the current web address
		String testurlC7 = "https://services-sandbox.hopewiser.com/hostedmgr/acct/";			// Set variable to path
		String testurlC8 = "https://services-sandbox.hopewiser.com/hostedmgr/home";				// Set variable to path
		if (whatiscurrenturlC4.contains(testurlC7))												// Test if Account page exists
			{
			ReportText = "11) Creation Passed: " + whatiscurrenturlC4;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "CreatePass13_";														// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturlC.contains(testurlC8))											// Test if home page exists
				{	
				ReportText = "11) Creation Failed: " + whatiscurrenturlC4;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "CreateFail13_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			else
				{
				ReportText = "11) Unknown Error: " + whatiscurrenturlC4;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "CreateError13_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			}
    	
        // Select the Accounts link				
		driver.findElement(By.xpath("//a[@href='/hostedmgr/acct']")).click();					// Click the Accounts link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/acct");		// Ensure the correct page is displayed in a sensible time
				
		// Search for Hopewiser Test				
		driver.findElement(By.xpath("//input[@id='textfilteraccount-table']")).sendKeys("Hopewiser Test"); // Enter Hopewiser Test for searching
		Thread.sleep(3000);																   		// Pause 3 seconds for page update
		ReportText = "12) Searched for Hopewiser Test";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// Select Hopewiser Test from the table					
		driver.findElement(By.xpath("//span[contains(.,'Hopewiser Test')]")).click();			// Click Hopewiser Test link	
		Timeloop.Execute(driver, "/details");													// Ensure the correct page is displayed in a sensible time
	
		// Select Users tab				
		driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[5]/a")).click(); // Click Users tab
		Timeloop.Execute(driver, "/user");														// Ensure the correct page is displayed in a sensible time
				
        // Create a new user			
		driver.findElement(By.xpath("//a[contains(.,'Create New')]")).click();					// Click Create New button
				
		// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method				
		ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet29");	// Look in the Excel sheet for test data
				
		// 3.3 Create user for Run 23
		CreateUser_Action.Execute(driver);														// Create the user using the appModule CreateUser_Actio
		
		// Test if creation occurred
		String whatiscurrenturlC5 = driver.getCurrentUrl();										// Set variable to the current web address
		String testurlC9 = "https://services-sandbox.hopewiser.com/hostedmgr/acct/";			// Set variable to path
		String testurlC10 = "https://services-sandbox.hopewiser.com/hostedmgr/home";			// Set variable to path
		if (whatiscurrenturlC5.contains(testurlC9))												// Test if Home page exists
			{	
			ReportText = "13) Creation Passed: " + whatiscurrenturlC5;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "CreatePass13_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturlC5.contains(testurlC10))										// Test if logon page exists
				{	
				ReportText = "13) Creation Failed: " + whatiscurrenturlC5;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "CreateFail13";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			else
				{
				ReportText = "13) Unknown Error: " + whatiscurrenturlC5;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "CreateError13_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			}
		
        // Select the Accounts link				
		driver.findElement(By.xpath("//a[@href='/hostedmgr/acct']")).click();					// Click the Accounts link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/acct");		// Ensure the correct page is displayed in a sensible time
				
		// Search for Hopewiser Test				
		driver.findElement(By.xpath("//input[@id='textfilteraccount-table']")).sendKeys("Hopewiser Test"); // Enter Hopewiser Test for searching
		Thread.sleep(3000);																   		// Pause 3 seconds for page update
		ReportText = "14) Searched for Hopewiser Test";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// Select Hopewiser Test from the table					
		driver.findElement(By.xpath("//span[contains(.,'Hopewiser Test')]")).click();			// Click Hopewiser Test link	
		Timeloop.Execute(driver, "/details");													// Ensure the correct page is displayed in a sensible time
	
		// Select Users tab				
		driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[5]/a")).click(); // Click Users tab
		Timeloop.Execute(driver, "/user");														// Ensure the correct page is displayed in a sensible time
				
        // Create a new user				
		driver.findElement(By.xpath("//a[contains(.,'Create New')]")).click();					// Click Create New button
				
		// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method				
		ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet30");	// Look in the Excel sheet for test data
				
		// 3.3 Create user for Run 24
		CreateUser_Action.Execute(driver);														// Create the user using the appModule CreateUser_Actio
		
		// Test if creation occurred
		String whatiscurrenturlC6 = driver.getCurrentUrl();										// Set variable to the current web address
		String testurlC11 = "https://services-sandbox.hopewiser.com/hostedmgr/acct/";			// Set variable to path
		String testurlC12 = "https://services-sandbox.hopewiser.com/hostedmgr/home";			// Set variable to path
		if (whatiscurrenturlC6.contains(testurlC11))											// Test if Home page exists
			{	
			ReportText = "15) Creation Passed: " + whatiscurrenturlC6;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "CreatePass13_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturlC6.contains(testurlC12))										// Test if logon page exists
				{	
				ReportText = "15) Creation Failed: " + whatiscurrenturlC6;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "CreateFail13_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			else
				{
				ReportText = "15) Unknown Error: " + whatiscurrenturlC6;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "CreateError13_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			}
    	
    	// 4.1 Log off    	
    	driver.findElement(By.xpath("//a[@href='/hostedmgr/secure/logout']")).click(); 			// Click the Log Off link
		ReportText = "16) Logged off";															// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
    	
		// Close the Browser and kill task (Postcondition)
		driver.quit();																			// Close the Browser
		ReportText = "17) Closed Browser";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
    	}
	}