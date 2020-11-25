package testRuns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import appModule.CreateBundle_Action;
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
public class Run35 
	{
    public static void main(String[] args) throws Exception 
    	{   	
    	// Launch Chrome (Precondition)
    	String exePath = "C:\\Selenium\\chromedriver.exe";											// Set variable to path where Chrome.exe is stored
    	System.setProperty("webdriver.chrome.driver", exePath);										// Set Path for open Chrome from
    	WebDriver driver = new ChromeDriver();														// Open a new instance of Chrome
		String ReportText = "1) Run35 Chrome Launched";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
        
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet3");			// Look in the Excel sheet for test data
        
       	// Open the login website using the Url (Precondition)
    	driver.get(Constant.URL);																	// Open the login website using the Url
		ReportText = "2) " + Constant.URL + " Website Opened";										// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    			
		// 4.0 Login using the appModule Login_Action
		Login_Action.Execute(driver);																// Login using the appModule Login_Action
		
		// Test if Login occurred
		String whatiscurrenturl = driver.getCurrentUrl();											// Set variable to the current web address
		String testurl1 = "https://services-sandbox.hopewiser.com/hostedmgr/home";					// Set Variable to path
		String testurl2 = "https://services-sandbox.hopewiser.com/hostedmgr/logon?error=true";		// Set Variable to path
		if (whatiscurrenturl.contains(testurl1))													// Test if Home page exists
			{
			ReportText = "3) Login Passed: " + whatiscurrenturl;									// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "LoginPass35_";																// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl.contains(testurl2))												// Test if logon page exists
				{
				ReportText = "3) Login Failed: " + whatiscurrenturl;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginFail35_";															// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			else
				{
				ReportText = "3) Unknown Error: " + whatiscurrenturl;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginError35_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}
		
		// 6.3 <a href="/hostedmgr/acct">Accounts</a>	
    	driver.findElement(By.xpath("//a[@href='/hostedmgr/acct']")).click();						// Click the left hand Accounts link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/acct");			// Ensure the correct page is displayed in a sensible time
		ReportText = "4) Click the Account link";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		String SS = "AccountsPage35_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet41");		// Look in the Excel sheet for test data
		String sAccountName = ExcelUtils.getCellData(1, 1);											// Get Account Name from TestData1.xlsx 

		// Search for HopewiserQAAccount
		//<input id="textfilteraccount-table" type="text" title="Searches each table cell. Supports multi-character (*) and single character (?) wildcards">				
		driver.findElement(By.xpath("//input[@id='textfilteraccount-table']")).sendKeys(sAccountName); // Enter the company name
		Thread.sleep(3000);																   			// Pause 3 seconds for page update
		ReportText = "5) Searched for " + sAccountName;												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountSearchPage35_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select HopewiserQAAutomation link from the list
		//<span style="color:black;background-color:yellow">HopewiserQAAutomation</span>  			//*[@id="account-table"]/tbody/tr[26]/td[1]/a/span/span  
		driver.findElement(By.xpath("//span[contains(.,'HopewiserQAAutomation')]")).click();		// Click the First Account in the table (tr number changes)
		Timeloop.Execute(driver, "/details");														// Ensure the correct page is displayed in a sensible time
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountDetailsPage35_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select Bundles tab
		//*[@id="main"]/div/div/section[2]/article/div/div[1]/ul/li[4]/a
		driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[4]/a")).click(); // Click Bundles tab
		Timeloop.Execute(driver, "/bund");															// Ensure the correct page is displayed in a sensible time
		ReportText = "6) Select Bundle from table";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Select Bundles Create New Button
		//<a href="/hostedmgr/acct/319/bund/create" class="ym-button ym-small">Create&nbsp;New</a>
		driver.findElement(By.xpath("//a[@class='ym-button ym-small']")).click();  					// Click the Create New Button
		Timeloop.Execute(driver, "/bund/create");													// Ensure the correct page is displayed in a sensible time
		ReportText = "7) Create New Button clicked";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "CreateBundlePage35_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		int PlusDays = 15;																			// Set number of days to add to todays date
		// Create Bundle using the appModule CreateBundle_Action
    	ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet41");		// Look in the Excel sheet for test data
		CreateBundle_Action.Execute(driver,PlusDays);												// Create Bundle using the appModule CreateBundle_Action
		
		// Test if Create occurred
		String whatiscurrenturl2 = driver.getCurrentUrl();											// Set variable to the current web address
		String testurlC1 = "https://services-sandbox.hopewiser.com/hostedmgr/acct/459/cost/706/bund";// Set variable to path
		String testurlC2 = "/bund/create";															// Set variable to path
		if (whatiscurrenturl2.equals(testurlC1))													// Test if bund page exists
			{
			ReportText = "8) Create Bundle Passed: " + whatiscurrenturl2;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results		
			SS = "BundlePass35_";																	// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl2.contains(testurlC2))												// Test if create page exists
				{
				ReportText = "8) Create Bundle Failed: " + whatiscurrenturl2;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "CreateBundleFail35_";															// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			else
				{
				ReportText = "8) Unknown Error: " + whatiscurrenturl2;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "CreateBundleError35_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}
    	
    	// 12.5 Manual Steps required
		Thread.sleep(3000);																   			// Pause 3 seconds for page update
		ReportText = "9) Wait until after Midnight";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		ReportText = "10) Check if the '14 day' 'AddressServer in the Cloud Plan Expires Soon' email exists"; // Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// 4.1 Log Off
		driver.findElement(By.xpath("//a[@href='/hostedmgr/secure/logout']")).click(); 				// Click the Log Off link
		ReportText = "11) Logged off";																// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Close the Browser and kill task (Postcondition)
    	driver.quit();																				// Close the Browser
		ReportText = "12) Closed Browser";															// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	}
	}