package testRuns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import appModule.CreateUser_Action;
import appModule.Delete_Action;
import appModule.EditUserCostCentreAdmin_Action;
import appModule.Login_Action;
import appModule.Output;
import appModule.ScreenShot_Action;
import appModule.Timeloop;

import utility.Constant;
import utility.ExcelUtils;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class Run5 
	{
	public static void main(String[] args) throws Exception 
		{	
		// Launch Chrome (Precondition)		
		String exePath = "C:\\Selenium\\chromedriver.exe";									// Set Variable for path Where Chrome.exe is stored
		System.setProperty("webdriver.chrome.driver", exePath);								// Set Path to open Chrome from
		WebDriver driver = new ChromeDriver();												// Open a new instance of Chrome
		String ReportText = "1) Run5 Chrome Launched";										// Set up report text variable
		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
		
		// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)		
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet2");  // Look in the Excel sheet for test data
		
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
			String SS = "LoginPass5_";														// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl.equals(testurl2))											// Test if logon page exists
				{
				ReportText = "3) Login Failed: " + whatiscurrenturl;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginFail5_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			else
				{
				ReportText = "3) Unknown Error: " + whatiscurrenturl;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginError5_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			}
		
		// Click the Create User button
		//html/body/div/div/div/section[2]/article/div/form/a   <div style="text-align:center">Create&nbsp;User</div> <li id="t-yourdetails"><a href="/hostedmgr/account/530/cost/879/user/2450/details">Your&nbsp;Details</a></li>	
		driver.findElement(By.xpath("//a[contains(.,'Create')]")).click();  				// Click the Create User button
		Timeloop.Execute(driver, "/user/create");											// Ensure the correct page is displayed in a sensible time	
		
		// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method		
		ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet8");// Look in the Excel sheet for test data
		
		// Create the user
		CreateUser_Action.Execute(driver);													// Create the user using the appModule CreateUser_Action
		
		// Test if creation occurred
		String whatiscurrenturlC = driver.getCurrentUrl();									// Set variable to the current web address
		String testurlC1 = "https://services-sandbox.hopewiser.com/hostedmgr/home";			// Set variable to path
		String testurlC2 = "https://services-sandbox.hopewiser.com/hostedmgr/account/";		// Set variable to path
		if (whatiscurrenturlC.contains(testurlC1))											// Test if home page exists
			{
			ReportText = "4) Creation Passed: " + whatiscurrenturlC;						// Set up report text variable
			Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "CreatePass5_";														// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturlC.contains(testurlC2))										// Test if account page exists
				{	
				ReportText = "4) Creation Failed: " + whatiscurrenturlC;					// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "CreateFail5_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			else
				{
				ReportText = "4) Unknown Error: " + whatiscurrenturlC;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "CreateError5_";												// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			}
		
		// Select the Required User from the table
		//<a class="ym-button ym-small" href="/hostedmgr/account/533/cost/882/user/2459/details">View&nbsp;/&nbsp;Edit</a>  		//html/body/div/div/div/section[2]/article/div/form/table[2]/tbody/tr[2]/td[5]/a
		//driver.findElement(By.xpath("//li[@id='t-yourdetails']")).sendKeys();		// Scroll to the bottom of the page
		driver.findElement(By.xpath("//div/div/table[2]/tbody/tr[3]/td[5]/a")).click();		// Click on the User in the table
		Timeloop.Execute(driver, "/details");												// Ensure the correct page is displayed in a sensible time
		ReportText = "5) User Found";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console

		// Edit the User
		//<a class="ym-button ym-small" href="/hostedmgr/home/533">Return&nbsp;to&nbsp;Account&nbsp;Details</a>
		driver.findElement(By.xpath("//a[contains(.,'Edit')]")).click(); 					// Click Edit button
		Timeloop.Execute(driver, "/edit");													// Ensure the correct page is displayed in a sensible time
		ReportText = "6) Edit User";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console

		// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method		
		ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet9");// Look in the Excel sheet for test data
		
		// Edit the required Cost Centre
		EditUserCostCentreAdmin_Action.Execute(driver);										// Edit the required Cost Centre with the appModule EditUserCostCentreAdmin_Action
		
		// Test if edit occurred
		String whatiscurrenturlE = driver.getCurrentUrl();									// Set variable to the current web address
		String testurlE1 = "https://services-sandbox.hopewiser.com/hostedmgr/account/";		// Set variable to path
		String testurlE2 = "https://services-sandbox.hopewiser.com/hostedmgr/home";			// Set variable to path
		if (whatiscurrenturlE.contains(testurlE1))											// Test if account page exists
			{
			ReportText = "7) Edit Passed: " + whatiscurrenturlE;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "EditPass5_";														// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturlE.contains(testurlE2))										// Test if home page exists
				{	
				ReportText = "7) Edit Failed: " + whatiscurrenturlE;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "EditFail5_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			else
				{
				ReportText = "7) Unknown Error: " + whatiscurrenturlE;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "EditError5_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			}
		
        // Go back to the Account Page		
		driver.findElement(By.xpath("//a[@href='/hostedmgr/home']")).click();				// Click the Your Account Button
		Timeloop.Execute(driver, "/home");													// Ensure the correct page is displayed in a sensible time
		
		// 3.2 Select the Required User from the table
		driver.findElement(By.xpath("//div/div/table[2]/tbody/tr[3]/td[5]/a")).click();		// Click on the Required View/Edit button
		Timeloop.Execute(driver, "/details");												// Ensure the correct page is displayed in a sensible time	
		
		// 3.2a Delete the required User	
		Delete_Action.Execute(driver);														// Delete the required User	the appModule Delete_Action
		
		// Test if delete occurred
		String whatiscurrenturlD = driver.getCurrentUrl();									// Set variable to the current web address
		String testurlD1 = "https://services-sandbox.hopewiser.com/hostedmgr/home";			// Set variable to path
		String testurlD2 = "https://services-sandbox.hopewiser.com/hostedmgr/account/";		// Set variable to path
		if (whatiscurrenturlD.contains(testurlD1))											// Test if home page exists
			{
			ReportText = "8) Delete Passed: " + whatiscurrenturlD;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "DeletePass5_";														// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturlD.contains(testurlD2))										// Test if account page exists
				{	
				ReportText = "8) Delete Failed: " + whatiscurrenturlD;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "DeleteFail5_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				}
			else
				{
				ReportText = "8) Unknown Error: " + whatiscurrenturlD;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "DeleteError5_";												// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);										// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																// Drop out of script
				}
			}
		
		// 4.1 Log Off
		driver.findElement(By.xpath("//a[@href='/hostedmgr/secure/logout']")).click(); 		// Click the Log Off link
		ReportText = "9) Logged off";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
		
		// Close the Browser and kill task (Postcondition)
		driver.quit();																		// Close the Browser
		ReportText = "10) Closed Browser";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
		}
	}