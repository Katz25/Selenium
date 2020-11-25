package testRuns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

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
public class Run18 
	{
    public static void main(String[] args) throws Exception 
    	{	
    	// Launch Chrome (Precondition)
    	String exePath = "C:\\Selenium\\chromedriver.exe";											// Set Variable for path Where Chrome.exe is stored
    	System.setProperty("webdriver.chrome.driver", exePath);										// Set Path to open Chrome from
    	WebDriver driver = new ChromeDriver();														// Open a new instance of Chrome
		String ReportText = "1) Run18 Chrome Launched";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    			
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method  (Precondition)  	
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet5");			// Look in the Excel sheet for test data
    	
    	// Open the login website using the Url (Precondition)
    	driver.get(Constant.URL);																	// Open the login website using the Url
		ReportText = "2) " + Constant.URL + " Website Opened";										// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	
    	// 4.0 Login To Address Server in the Cloud
		Login_Action.Execute(driver);																// Login using the appModule Login_Action
		
		// Test if Login occurred
		String whatiscurrenturl = driver.getCurrentUrl();											// Set variable to the current web address
		String testurl1 = "https://services-sandbox.hopewiser.com/hostedmgr/home";					// Set variable to path
		String testurl2 = "https://services-sandbox.hopewiser.com/hostedmgr/logon";					// Set variable to path
		if (whatiscurrenturl.equals(testurl1))														// Test if Home page exists
			{
			ReportText = "3) Login Passed: " + whatiscurrenturl;									// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\User\Documents\Automation results
			String SS = "LoginPass18_";																// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl.contains(testurl2))												// Test if logon page exists
				{
				ReportText = "3) Login Failed: " + whatiscurrenturl;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginFail18_";															// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			else
				{
				ReportText = "3) Unknown Error" + whatiscurrenturl;									// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginError18_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}
    	
		// Open the costcenter Accordion
		// <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">Cost Centre: costcentre</a>
		driver.findElement(By.xpath("//a[contains(.,'Centre:')]")).click();  						// Click the Cost Centre: costcentre header bar
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/home");			// Ensure the correct page is displayed in a sensible time
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		String SS = "CCUsageAccordion18_";															// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Get Clicks Remaining current value for comparison later
		//WebElement td_text2 = driver.findElement(By.xpath("//html/body/div/div/div/section[2]/article/div/form/div/div/div[2]/div/div/table[1]/tbody/tr/td[4]")); // Get to first line of the table 4th item
		WebElement td_text2 = driver.findElement(By.xpath("//html/body/div/div/div/section[2]/article/div/form/div/div/div[2]/div/div/table[1]/tbody/tr[2]/td[4]")); // Get to second line of the table 5th item
		String Clicks_Remaining = td_text2.getText();												// Get the text from this cell
		int Click_Remaining_Num1 = Integer.parseInt(Clicks_Remaining);								// Convert Clicks_Remaining text value into a number
    	ReportText = "4) Clicks Remaining: " + Clicks_Remaining;									// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	
		// Launch Chrome (Precondition)
    	WebDriver Mimic = new ChromeDriver();														// Open a new instance of Chrome
		ReportText = "5) Run18 Second Firefox Launched";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	
    	// Use the Fast Address Mimic url 
    	String searchURL = "https://addrsvr2.hopewiser.com/address-search/";						// Set variable to path
    	String password = Constant.Password;														// Set variable to password via the utility constant scriptdriver.get(searchURL);																// Open the login website using the Url
    	Mimic.get(searchURL);																		// Open the login website using the Url
		Timeloop.Execute(Mimic, "https://addrsvr2.hopewiser.com/address-search/");					// Ensure the correct page is displayed in a sensible time
		ReportText = "6) "+ searchURL +" Website Opened";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	
    	// Log on to the Fast Address Mimic
    	String user = "QAtest-ha-cca";																// Set variable to user name
    	Mimic.findElement(By.id("username")).clear(); 												// Clear the edit box
		Mimic.findElement(By.id("username")).sendKeys(user); 										// Enter the User Name
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
    	
    	Mimic.findElement(By.id("password")).clear(); 												// Clear the edit box
		Mimic.findElement(By.id("password")).sendKeys(password); 									// Enter the Password
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
    	
    	Mimic.findElement(By.xpath("//button[contains(.,'Sign In')]")).click(); 					// Click Sign In button
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
		ReportText = "7) Signed In as: " + user;													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	
    	new Select(Mimic.findElement(By.id("dataset"))).selectByVisibleText("uk-rm-paf-internal");	// Select PAF dataset from the dropdown
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
    	
    	// Search for a post code
    	Mimic.findElement(By.id("input")).clear(); 													// Clear the edit box
		Mimic.findElement(By.id("input")).sendKeys("6,m337ug"); 									// Enter the Premises number and the postcode
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
    	
    	// Find the Address
    	Mimic.findElement(By.xpath("//button[contains(.,'Find Address')]")).click(); 				// Click Find Address button
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
    	
    	// Select the Address
    	Mimic.findElement(By.xpath("//button[contains(.,'Select')]")).click(); 						// Click Select button
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
		ReportText = "8) Address is selected";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "Select18_";																			// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Close the Browser and kill task (Postcondition)
		Mimic.quit();																				// Close the Browser
		ReportText = "9) Closed Browser";															// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Open the costcenter Accordion
		// <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">Cost Centre: costcentre</a>
		driver.navigate().refresh();																// Refresh the page to update the Clicks remaining value
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/home");			// Ensure the correct page is displayed in a sensible time
		driver.findElement(By.xpath("//a[contains(.,'Centre:')]")).click();  						// Click the Cost Centre: costcentre header bar
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "CCUsageAccordion18_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Get Clicks Remaining current value for comparison
		//WebElement td_text4 = driver.findElement(By.xpath("//html/body/div/div/div/section[2]/article/div/form/div/div/div[2]/div/div/table[1]/tbody/tr/td[4]")); // Get to first line of the table 4th item
		WebElement td_text4 = driver.findElement(By.xpath("//html/body/div/div/div/section[2]/article/div/form/div/div/div[2]/div/div/table[1]/tbody/tr[2]/td[4]")); // Get to second line of the table 5th item
		String Clicks_Remaining2 = td_text4.getText();												// Get the text from this cell
		int Click_Remaining_Num2 = Integer.parseInt(Clicks_Remaining2);								// Convert Clicks_Remaining text value into a number
		ReportText = "10) Clicks Remaining: " + Clicks_Remaining2;									// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		int result2 = (Click_Remaining_Num2-Click_Remaining_Num1);									// Calculate clicks used
		ReportText = "11) Clicks Remaining used: " + result2;										// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// 4.1 Log Off
		driver.findElement(By.xpath("//a[@href='/hostedmgr/secure/logout']")).click(); 				// Click the Log Off link
		ReportText = "12) Logged off";																// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Close the Browser and kill task (Postcondition)
		driver.quit();																				// Close the Browser
		ReportText = "13) Closed Browser";															// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console		
    	}
	}