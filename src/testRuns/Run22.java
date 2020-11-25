package testRuns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import appModule.Login_Action;
import appModule.Output;
import appModule.CreateCostCentre_Action;
import appModule.Delete_Action;
import appModule.ScreenShot_Action;
import appModule.Timeloop;
import utility.Constant;
import utility.ExcelUtils;
/*
 * Author: Maxine Flook
 * Selenium tests V1
 */
public class Run22 
	{
    public static void main(String[] args) throws Exception 
   	{   	
    	// Launch Chrome (Precondition)
    	String exePath = "C:\\Selenium\\chromedriver.exe";										// Set Variable for path Where Chrome.exe is stored
    	System.setProperty("webdriver.chrome.driver", exePath);									// Set Path to open Chrome from
    	WebDriver driver = new ChromeDriver();													// Open a new instance of Chrome
		String ReportText = "1) Run22 Chrome Launched";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
    	
    	//This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet3");
        
       	// Open the login website using the Url (Precondition)
    	driver.get(Constant.URL);																// Open the login website using the Url				
		ReportText = "2) " + Constant.URL + " Website Opened";									// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
    			
		// 4.0 Login using the appModule Login_Action
		Login_Action.Execute(driver);															// Login using the appModule Login_Action
		
		// Test if Login occurred
		String whatiscurrenturl = driver.getCurrentUrl();										// Set variable to the current web address
		String testurl1 = "https://services-sandbox.hopewiser.com/hostedmgr/home";				// Set Variable to path
		String testurl2 = "https://services-sandbox.hopewiser.com/hostedmgr/logon?error=true";	// Set Variable to path
		if (whatiscurrenturl.contains(testurl1))												// Test if Home page exists
			{
			ReportText = "3) Login Passed: " + whatiscurrenturl;								// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "LoginPass22_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl.contains(testurl2))											// Test if logon page exists
				{
				ReportText = "3) Login Failed: " + whatiscurrenturl;							// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginFail22_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			else
				{
				ReportText = "3) Unknown Error: " + whatiscurrenturl;							// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginError22_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			}
    	
		// 5.3 Select the left hand Accounts link 
    	// <a href="/hostedmgr/acct">Accounts</a>	
    	driver.findElement(By.xpath("//a[@href='/hostedmgr/acct']")).click();					// Click the Accounts link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/acct");		// Ensure the correct page is displayed in a sensible time
		ReportText = "4) Click the Account link";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		String SS = "AccountsPage22_";															// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Search for Hopewiser Test
		// <input id="textfilteraccount-table" type="text" title="Searches each table cell. Supports multi-character (*) and single character (?) wildcards">				
		driver.findElement(By.xpath("//input[@id='textfilteraccount-table']")).sendKeys("Hopewiser Test");  // Enter the user name
		Thread.sleep(3000);																   		// Pause 3 seconds for page update
		ReportText = "5) Searched for Hopewiser Test";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
				
		// Select Hopewiser Test link from the list				
		driver.findElement(By.xpath("//span[contains(.,'Hopewiser Test')]")).click();			// Click the user
		Timeloop.Execute(driver, "/details");													// Ensure the correct page is displayed in a sensible time
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountDetailsPage22_";															// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the Cost Centres tab
		//*[@id="main"]/div/div/section[2]/article/div/div[1]/ul/li[3]/a
		driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[3]")).click();// Click the Cost Centres tab
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "CostCentresPage22_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the Create New Button
		// <a href="/hostedmgr/acct/319/cost/create" class="ym-button ym-small">Create&nbsp;New</a>
		try
			{
			driver.findElement(By.xpath("//a[@href='/hostedmgr/acct/319/cost/create']")).click();// Click the Create New button
			}
		catch (Exception e)
			{
			driver.findElement(By.xpath("//a[@class='ym-button ym-small']")).click();			// Click the Create New button
			}
		
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData1,"Sheet26");
    	
		// Create a Costcentre using the appModule CreateCostCentre_Action
    	CreateCostCentre_Action.Execute(driver);												// Create a Cost Centre using the appModule CreateCostCentre_Action
		
		// 5.2 Search for costcentre
		//<input id="textfiltercost-table" type="text" title="Searches each table cell. Supports multi-character (*) and single character (?) wildcards">		
		driver.findElement(By.xpath("//input[@id='textfiltercost-table']")).sendKeys("Cost Centre Test"); // Enter the Cost centre name
		Thread.sleep(3000);																   		// Pause 3 seconds for page update
		ReportText = "6) Searched for Cost Centre Test";										// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// Select costcentre from the table
		//<span style="color:black;background-color:yellow">Cost Centre Test</span>
		driver.findElement(By.xpath("//span[contains(.,'Cost Centre Test')]")).click();  		// Enter the Cost centre name
		Timeloop.Execute(driver, "/details");													// Ensure the correct page is displayed in a sensible time
		
		// Delete the Cost centre
		Delete_Action.Execute(driver);															// Delete the Cost centre using the appModule Delete_Action
		
		// Test if delete occurred
		String whatiscurrenturlD = driver.getCurrentUrl();										// Set variable to the current web address
		String testurlD1 = "https://services-sandbox.hopewiser.com/hostedmgr/acct/";			// Set variable to path (before & after delete pages are /acct/ pages
		if (whatiscurrenturlD.contains(testurlD1))												// Test if Account page exists
			{
			ReportText = "7) Delete Passed: " + whatiscurrenturlD;								// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "DeletePass22_";																// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
			try 
				{	
				driver.findElement(By.xpath("//span[contains(.,'Cost Centre Test')]")).click();	// if Cost Centre Test doesn't exist an exception is raised, Cost Centre Test should not exist
				}
			catch (Exception e)
				{
				ReportText = "8) Cost Centre Test is deleted";									// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				}
			ReportText = "9) if 8 is missing Cost Centre Test has not been deleted";			// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			}
		else
			{	
			ReportText = "7) Unknown Error: " + whatiscurrenturlD;								// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "DeleteError22_";																// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
			System.exit(1);																		// Drop out of script
			}
		
		// 4.1 Log Off
		driver.findElement(By.xpath("//a[@href='/hostedmgr/secure/logout']")).click(); 			// Click the Log Off link
		ReportText = "10) Logged off";															// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// Close the Browser and kill task (Postcondition)
    	driver.quit();																			// Close the Browser
		ReportText = "11) Closed Browser";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		}
	}