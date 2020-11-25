package testRuns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import appModule.CreateAccount_Action;
import appModule.Delete_Action;
import appModule.EditAccount_Action;
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
public class Run26 
	{
    public static void main(String[] args) throws Exception 
    	{   	
    	// Launch Chrome (Precondition)
    	String exePath = "C:\\Selenium\\chromedriver.exe";
    	System.setProperty("webdriver.chrome.driver", exePath);										// Set Path to open Chrome from
    	WebDriver driver = new ChromeDriver();														// Open a new instance of ChromeDriver
		String ReportText = "1) Run26 Chrome Launched";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
        
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"SuperUser");		// Look in the Excel sheet for test data
        
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
			String SS = "LoginPass26_";																// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl.contains(testurl2))												// Test if logon page exists
				{
				ReportText = "3) Login Failed: " + whatiscurrenturl;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginFail26_";															// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			else
				{
				ReportText = "3) Unknown Error: " + whatiscurrenturl;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginError26_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}  	
    	
    	// 15.0a Select the left hand Accounts link 
    	// <a href="/hostedmgr/acct">Accounts</a>	
    	driver.findElement(By.xpath("//a[@href='/hostedmgr/acct']")).click();						// Click the Your Account link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/acct");			// Ensure the correct page is displayed in a sensible time
		ReportText = "4) Click the Account link";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		String SS = "AccountsPage26_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the Create New Button
		//<a href="/hostedmgr/plan/create" class="ym-button">Create&nbsp;New</a>
		driver.findElement(By.xpath("//a[@class='ym-button']")).click();  							// Click the Create New Button
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/acct/create");	// Ensure the correct page is displayed in a sensible time
		ReportText = "5) Create New Button clicked";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "CreateAccountPage26_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet32");		// Look in the Excel sheet for test data
    	
		// Create Account using the appModule CreateAccount_Action
		CreateAccount_Action.Execute(driver);														// Create the Account using the appModule CreateAccount_Action
		
		// Test if Creation occurred
		String whatiscurrenturlC = driver.getCurrentUrl();											// Set variable to the current web address
		String testurlC1 = "https://services-sandbox.hopewiser.com/hostedmgr/acct";					// Set Variable to path
		String testurlC2 = "https://services-sandbox.hopewiser.com/hostedmgr/acct/create";			// Set Variable to path
		if (whatiscurrenturlC.contains(testurlC2))													// Test if Create page exists
			{
			ReportText = "6) Creation Failed: " + whatiscurrenturlC;								// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "CreationFail26_";																	// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			System.exit(1);																			// Drop out of script			
			}
		else
			{	
			if (whatiscurrenturlC.contains(testurlC1))												// Test if acct page exists
				{
				ReportText = "6) Creation Passed: " + whatiscurrenturlC;							// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "CreationPass26_";																// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				}
			else
				{
				ReportText = "6) Unknown Error: " + whatiscurrenturlC;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "CreationError26_";															// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}  	
		
    	// 15.1 Select the left hand Accounts link 
    	// <a href="/hostedmgr/acct">Accounts</a>	
    	driver.findElement(By.xpath("//a[@href='/hostedmgr/acct']")).click();						// Click the Your Account link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/acct");			// Ensure the correct page is displayed in a sensible time
		ReportText = "7) Click the Account link";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountsPage26_";																		// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet32");		// Look in the Excel sheet for test data
    	
		// Search for HopewiserAccountQA
		String sAccountName = ExcelUtils.getCellData(1, 1);											// Get the Account name from TestData1 Sheet32 
		String sCountry = ExcelUtils.getCellData(1, 2);												// Get the country from TestData1 Sheet32
		String sPostCode = ExcelUtils.getCellData(1, 3);											// Get the Postcode from TestData1 Sheet32
		String sAddress = ExcelUtils.getCellData(1, 4);												// Get the Address from TestData1 Sheet32
		String sTown = ExcelUtils.getCellData(1, 5);												// Get the Town name from TestData1 Sheet32
		String sCounty = ExcelUtils.getCellData(1, 6);												// Get the county name from TestData1 Sheet32
		
		// <input id="textfilteraccount-table" type="text" title="Searches each table cell. Supports multi-character (*) and single character (?) wildcards">				
		driver.findElement(By.xpath("//input[@id='textfilteraccount-table']")).sendKeys(sAccountName); // Enter the user name
		Thread.sleep(3000);																   			// Pause 3 seconds for page update
		ReportText = "8) Searched for " + sAccountName;												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountSearchPage26_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select HopewiserAccountTest link from the list
		//driver.findElement(By.xpath("//tbody/tr[28]/td[1]/a/span/span")).click();					// Click the First Account in the table (tr number changes)		
		driver.findElement(By.xpath("//span[contains(.,'HopewiserAccountTest')]")).click();			// Click the First Account in the table (tr number changes)
		Timeloop.Execute(driver, "/details");														// Ensure the correct page is displayed in a sensible time
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountDetailsPage26_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the Edit Account Details button 
		//<a href="/hostedmgr/acct/460/edit" class="ym-button">Edit</a>
		try
			{
			driver.findElement(By.xpath("//a[@href='/hostedmgr/account/460/edit']")).click();		// Click the Edit Account Details button
			}
		catch (Exception e)
			{
			//driver.findElement(By.xpath("//a[@class='Edit')]")).click();
			driver.findElement(By.xpath("//a[contains(.,'Edit')]")).click();						// Click the Edit Account Details button
			}

		ReportText = "9) The Edit Account Details button is clicked";								// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "EditAccountPage26_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Check and Edit the Edit Account Page Details
		// Check Company Name
		// <input id="companyname" name="companyname" required="required" type="text" value="HopewiserAccountTest" maxlength="60">		
		WebElement TxtBoxContent = driver.findElement(By.xpath("//input[@id='companyname']"));  	// Setup getText
		String Txt = TxtBoxContent.getAttribute("value");											// Get the text in the field
		if (sAccountName.equals(Txt))																// Check the text matches the expected
			{
			ReportText = "10) Company check passed: " + Txt;										// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			}
		else
			{
			ReportText = "10) Company check failed: " + sAccountName + " " +  Txt;					// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			System.exit(1);																			// Drop out of script
			}	
		
		// Check Country Exists	
		//driver.findElement(By.xpath("//select[@id='country']"));
		TxtBoxContent = driver.findElement(By.xpath("//select[@id='country']"));  					// Setup getText
		Txt = TxtBoxContent.getAttribute("value");													// Get the text in the field
		if (sCountry.equals(Txt))																	// Check the text matches the expected
			{
			ReportText = "11) Country check passed: " + Txt;										// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			}
		else
			{
			ReportText = "11) Country check failed: " + sCountry + " " + Txt;						// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			System.exit(1);																			// Drop out of script
			}
		
		// Check Postcode Exists	
		TxtBoxContent = driver.findElement(By.xpath("//input[@id='postcode']"));  					// Setup getText
		Txt = TxtBoxContent.getAttribute("value");													// Get the text in the field
		if (sPostCode.equals(Txt))																	// Check the text matches the expected
			{
			ReportText = "12) Postcode check passed: " + Txt;										// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			}
		else
			{
			ReportText = "12) Postcode check failed: " + sPostCode + " " + Txt;						// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			System.exit(1);																			// Drop out of script
			}
		
		// Check Address Exists	
		TxtBoxContent = driver.findElement(By.xpath("//input[@id='address1']"));  					// Setup getText
		Txt = TxtBoxContent.getAttribute("value");													// Get the text in the field
		if (sAddress.equals(Txt))																	// Check the text matches the expected
			{
			ReportText = "13) Address check passed: " + Txt;										// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			}
		else
			{
			ReportText = "13) Address check failed: "  + sAddress + " " + Txt;						// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			System.exit(1);																			// Drop out of script
			}
		
		// Check Town Exists	
		//<input id="city" name="city" required="required" type="text" value="TEST" maxlength="30">
		TxtBoxContent = driver.findElement(By.xpath("//input[@id='city']"));  						// Setup getText
		Txt = TxtBoxContent.getAttribute("value");													// Get the text in the field
		if (sTown.equals(Txt))																		// Check the text matches the expected
			{
			ReportText = "14) City check passed: " + Txt;											// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			}
		else
			{
			ReportText = "14) City check failed: " + sTown + " " + Txt;								// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			System.exit(1);																			// Drop out of script
			}
		
		// Check County Exists
		//<input id="state" name="state" type="text" value="TEST" maxlength="30">
		TxtBoxContent = driver.findElement(By.xpath("//input[@id='state']"));  						// Setup getText
		Txt = TxtBoxContent.getAttribute("value");													// Get the text in the field
		if (sCounty.equals(Txt))																	// Check the text matches the expected
			{
			ReportText = "15) County check passed: " + Txt;											// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			}
		else
			{
			ReportText = "15) County check failed: " + sCounty + " " + Txt;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			System.exit(1);																			// Drop out of script
			}
		
		// Select the Terms and Conditions check box
		//<input id="agreeterms" name="agreeterms" type="checkbox" value="true"> //*[@id="agreeterms"]
		driver.findElement(By.xpath("//div/input[@id='agreeterms']")).click();						// Tick the Terms and Conditions check box
		driver.findElement(By.xpath("//div/input[@id='agreeterms']")).click();						// Tick the Terms and Conditions check box
		//driver.findElement(By.xpath("//div/input[@id='agreeterms']")).click();					// Tick the Terms and Conditions check box
		
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet33");		// Look in the Excel sheet for test data
    	
		// Edit Account using the appModule EditAccount_Action
		EditAccount_Action.Execute(driver);															// Edit the Account using the appModule EditAccount_Action
		
		// Test if Edit occurred
		String whatiscurrenturlE = driver.getCurrentUrl();											// Set variable to the current web address
		String testurlE1 = "https://services-sandbox.hopewiser.com/hostedmgr/acct";					// Set Variable to path
		String testurlE2 = "https://services-sandbox.hopewiser.com/hostedmgr/acct/create";			// Set Variable to path
		if (whatiscurrenturlE.contains(testurlE2))													// Test if Create page exists
			{
			ReportText = "16) Edit Failed: " + whatiscurrenturlE;									// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "CEditFail26_";																	// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			System.exit(1);																			// Drop out of script			
			}
		else
			{	
			if (whatiscurrenturlE.contains(testurlE1))												// Test if acct page exists
				{
				ReportText = "16) Edit Passed: " + whatiscurrenturlE;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "EditPass26_";																	// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				}
			else
				{
				ReportText = "16) Unknown Error: " + whatiscurrenturlE;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "EditError26_";																// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}  	
		
    	// 15.2 Select the left hand Accounts link 
    	// <a href="/hostedmgr/acct">Accounts</a>	
    	driver.findElement(By.xpath("//a[@href='/hostedmgr/acct']")).click();						// Click the Your Account link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/acct");			// Ensure the correct page is displayed in a sensible time
		ReportText = "17) Click the Account link";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountsPage26_";																		// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// <input id="textfilteraccount-table" type="text" title="Searches each table cell. Supports multi-character (*) and single character (?) wildcards">				
		driver.findElement(By.xpath("//input[@id='textfilteraccount-table']")).sendKeys(sAccountName + "Edit"); // Enter the user name
		Thread.sleep(3000);																   			// Pause 3 seconds for page update
		ReportText = "18) Searched for " + sAccountName + "Edit";									// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountSearchPage26_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select HopewiserAccountTestEdit link from the list
		//<span style="color:black;background-color:yellow">HopewiserAccountTest</span>   			//*[@id="account-table"]/tbody/tr[25]/td[1]/a/span/span
		//driver.findElement(By.xpath("html/body/div/div/div/section[2]/article/div/table/tbody/tr[28]/td[1]/a/span/span")).click(); // Click the First Account in the table (tr number changes)
		driver.findElement(By.xpath("//span[contains(.,'HopewiserAccountTestEdit')]")).click();		// Click the First Account in the table (tr number changes)
		Timeloop.Execute(driver, "/details");														// Ensure the correct page is displayed in a sensible time
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountDetailsPage26_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Delete the required Plan with the appModule Delete_Action
		Delete_Action.Execute(driver);																// Delete the required Plan with the appModule Delete_Action
		
		// Test if delete occurred
		String whatiscurrenturlD = driver.getCurrentUrl();											// Set variable to the current web address
		String testurlD1 = "https://services-sandbox.hopewiser.com/hostedmgr/acct";					// Set variable to path
		String testurlD2 = "https://services-sandbox.hopewiser.com/hostedmgr/acct/460/details";		// Set variable to path
		if (whatiscurrenturlD.contains(testurlD2))													// Test if home page exists
			{
			ReportText = "19) Delete Failed: " + whatiscurrenturlD;									// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "DeleteFail26_";																	// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturlD.contains(testurlD1))												// Test if account page exists
				{	
				ReportText = "19) Delete Passed: " + whatiscurrenturlD;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "DeletePass26_";																// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				}
			else
				{
				ReportText = "19) Unknown Error: " + whatiscurrenturlD;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "DeleteError26_";																// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}
				
		// 4.1 Log Off
		driver.findElement(By.xpath("//a[@href='/hostedmgr/secure/logout']")).click(); 				// Click the Log Off link
		ReportText = "20) Logged off";																// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Close the Browser and kill task (Postcondition)
    	driver.quit();																				// Close the Browser
		ReportText = "21) Closed Browser";															// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console   	
    	}
	}