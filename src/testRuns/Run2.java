package testRuns;

import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import appModule.Login_Action;
import appModule.Output;
import appModule.CreateUser_Action;
import appModule.Delete_Action;
import appModule.EditUser_Action;
import appModule.ScreenShot_Action;
import appModule.Timeloop;

import utility.Constant;
import utility.ExcelUtils;
/*
 * Author: Maxine Flook
 * Selenium tests V1
 */
public class Run2 
	{
	public static void main(String[] args) throws Exception 
		{		
		// Launch Chrome (Precondition)
		String exePath = "C:\\Selenium\\chromedriver.exe";									// Set variable to path where Chrome.exe is stored
		System.setProperty("webdriver.chrome.driver", exePath);								// Set Path to open Chrome from
		WebDriver driver = new ChromeDriver();												// Open a new instance of Chrome
		String ReportText = "1) Run2 Chrome Launched";										// Set up report text variable
		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				
		// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");	// Look in the Excel sheet for test data
		
		// Open the login page (Precondition)
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
			String SS = "LoginPass2_";														// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl.equals(testurl2))											// Test if logon page exists
				{
				ReportText = "3) Login Failed: " + whatiscurrenturl;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				ReportText = "3.1) Please wait for the Hopewiser Account Activation email and use it to Activate the Account.";	// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginFail2_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			else
				{
				ReportText = "3) Unknown Error: " + whatiscurrenturl;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginError2_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			}
				
		// Click the Create User button
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/home");	// Ensure the correct page is displayed in a sensible time	
		//html/body/div/div/div/section[2]/article/div/form/a  //*[@id="t-yourdetails"]/a 
		driver.findElement(By.xpath("//a[contains(.,'Create')]")).click();  				// Click the Create User button
		Timeloop.Execute(driver, "/user/create");											// Ensure the correct page is displayed in a sensible time				
		ReportText = "4) Create User Button clicked";										// Set up report text variable
		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				
		// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method				
		ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet2");// Look in the Excel sheet for test data
				
		// Create a new user
		CreateUser_Action.Execute(driver);													// Create a new user using appModule CreateUser_Action 
		
		// Test if creation occurred
		String whatiscurrenturlC = driver.getCurrentUrl();									// Set variable to the current web address
		String testurlC1 = "https://services-sandbox.hopewiser.com/hostedmgr/home";			// Set variable to path for passed user creation
		String testurlC2 = "https://services-sandbox.hopewiser.com/hostedmgr/account/";		// Set variable to path for failed user creation
		if (whatiscurrenturlC.contains(testurlC1))											// Test if home page exists
			{
			ReportText = "5) Creation Passed: " + whatiscurrenturlC;						// Set up report text variable
			Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "CreatePass2_";														// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturlC.contains(testurlC2))										// Test if logon page exists
				{	
				ReportText = "5) Creation Failed: " + whatiscurrenturlC;					// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				ReportText = "5.1) Please wait for the Hopewiser Account Activation email and use it to Activate the Account.";	// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "CreateFail2_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			else
				{
				ReportText = "5) Unknown Error: " + whatiscurrenturlC;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "CreateError2_";												// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			}
		
		// Select the Required User from the table
		//*[@id="collapse1"]/div/div/table[2]/tbody/tr[2]/td[5]/a
		driver.findElement(By.xpath("//div/div/table[2]/tbody/tr[2]/td[5]/a")).click(); 	// Click the user from the list
		Timeloop.Execute(driver, "/details");												// Ensure the correct page is displayed in a sensible time
		ReportText = "6) User Found";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				
		// Edit the User
		//<a class="ym-button ym-small" style="width: 10em;" href="/hostedmgr/account/503/cost/778/user/2112/edit"><div style="text-align: center;">Edit</div></a>
		driver.findElement(By.xpath("//a[contains(.,'Edit')]")).click(); 					// Click Edit Account
		Timeloop.Execute(driver, "/edit");													// Ensure the correct page is displayed in a sensible time		
		ReportText = "7) Edit User";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				
		// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method				
		ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet3"); // Look in the Excel sheet for test data
		
		// Edit the user
		EditUser_Action.Execute(driver);													// Edit the user using appModule EditUser_Action  

		// Test if edit occurred
		String whatiscurrenturlE = driver.getCurrentUrl();									// Set variable to the current web address
		String testurlE1 = "https://services-sandbox.hopewiser.com/hostedmgr/account/";		// Set variable to path
		String testurlE2 = "https://services-sandbox.hopewiser.com/hostedmgr/home";			// Set variable to path
		if (whatiscurrenturlE.contains(testurlE1))											// Test if Account page exists
			{
			ReportText = "8) Edit Passed: " + whatiscurrenturlE;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "EditFail2_";														// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturlE.contains(testurlE2))										// Test if home page exists
				{	
				ReportText = "8) Edit Failed: " + whatiscurrenturlE;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "EditFail2_";													// Create variable to help name the Screenshot				
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			else
				{
				ReportText = "8) Unknown Error: " + whatiscurrenturlE;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "EditError2_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			}
				
		// Go back to the Account Page				
		driver.findElement(By.xpath("//a[@href='/hostedmgr/home']")).click(); 				// Click the Your Account Button		
		ReportText = "9) Home link selected";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				
		// Select the Required User from the table
		driver.findElement(By.xpath("//div/div/table[2]/tbody/tr[2]/td[5]/a")).click(); 	// Click on the Required User row
		Timeloop.Execute(driver, "/details");												// Ensure the correct page is displayed in a sensible time				
		ReportText = "10) User Found";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				
		// Delete the required User				
		Delete_Action.Execute(driver);  													// Delete the user using the appModule Delete_Action

		// Test if delete occurred
		String whatiscurrenturlD = driver.getCurrentUrl();									// Set variable to the current web address
		String testurlD1 = "https://services-sandbox.hopewiser.com/hostedmgr/home";			// Set variable to path
		String testurlD2 = "https://services-sandbox.hopewiser.com/hostedmgr/account/";		// Set variable to path
		if (whatiscurrenturlD.contains(testurlD1))											// Test if home page exists
			{
			ReportText = "11) Delete Passed: " + whatiscurrenturlD;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "DeletePass2_";														// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturlD.contains(testurlD2))										// Test if account page exists
				{	
				ReportText = "11) Delete Failed: " + whatiscurrenturlD;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "DeleteFail2_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			else
				{
				ReportText = "11) Unknown Error: " + whatiscurrenturlD;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results 
				String SS = "DeleteError2_";												// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			}
			
		// 4.1 Log Off
		driver.findElement(By.xpath("//a[@href='/hostedmgr/secure/logout']")).click(); 		// Click the Log Off link
		ReportText = "12) Logged off";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				
		// Close the Browser and kill task (Postcondition)
		driver.quit();																		// Close the Browser
		ReportText = "13) Closed Browser";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
		}
	}