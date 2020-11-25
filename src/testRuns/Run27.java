package testRuns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import appModule.CreateBundle_Action;
import appModule.Delete_Action;
import appModule.EditBundle_Action;
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
public class Run27 
	{
    public static void main(String[] args) throws Exception 
   		{   	
    	// Launch Chrome (Precondition)
    	String exePath = "C:\\Selenium\\chromedriver.exe";
    	System.setProperty("webdriver.chrome.driver", exePath);
    	WebDriver driver = new ChromeDriver();
		String ReportText = "1) Run27 Chrome Launched";												// Set up report text variable
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
			String SS = "LoginPass27_";																// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl.contains(testurl2))												// Test if logon page exists
				{
				ReportText = "3) Login Failed: " + whatiscurrenturl;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginFail27_";															// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			else
				{
				ReportText = "3) Unknown Error: " + whatiscurrenturl;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginError27_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}  	
		
    	// 17.0 Select the left hand Accounts link 
    	// <a href="/hostedmgr/acct">Accounts</a>	
    	driver.findElement(By.xpath("//a[@href='/hostedmgr/acct']")).click();						// Click the Your Account link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/acct");			// Ensure the correct page is displayed in a sensible time
		ReportText = "4) Click the Account link";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		String SS = "AccountsPage27_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Search for Hopewiser Test	
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet12");			// Look in the Excel sheet for test data
		String sUserName = ExcelUtils.getCellData(1, 1);											// Get the User Name from TestData.xlsx Sheet12
		driver.findElement(By.xpath("//input[@id='textfilteraccount-table']")).sendKeys(sUserName); // Enter the user name
		Thread.sleep(3000);																   			// Pause 3 seconds for page update
		ReportText = "5) Searched for Hopewiser Test";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
				
		// Select Hopewiser Test from the table	
		//*[@id="account-table"]/tbody/tr[22]/td[1]/a/span/span  ///html/body/div/div/div/section[2]/article/div/table/tbody/tr[24]/td[1]/a
		driver.findElement(By.xpath("//span[contains(.,'Hopewiser Test')]")).click();				// Click the First Account in the table (tr number changes)
		Timeloop.Execute(driver, "/details");														// Ensure the correct page is displayed in a sensible time
			
		// Select Bundles tab
		//*[@id="main"]/div/div/section[2]/article/div/div[1]/ul/li[4]/a  ///html/body/div/div/div/section[2]/article/div/div[1]/ul/li[4]/a
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
		SS = "CreateBundlePage27_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		int PlusDays = 15;																			// Set number of days to add to todays date
		// 17.0 Create Bundle using the appModule CreateBundle_Action
    	ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet34");		// Look in the Excel sheet for test data
		CreateBundle_Action.Execute(driver,PlusDays);												// Create Bundle using the appModule CreateBundle_Action
		
		// Test if Create occurred
		String whatiscurrenturl2 = driver.getCurrentUrl();											// Set variable to the current web address
		String testurlC1 = "https://services-sandbox.hopewiser.com/hostedmgr/acct/319/cost/549/bund";// Set variable to path
		String testurlC2 = "https://services-sandbox.hopewiser.com/hostedmgr/acct/319/bund/create";	// Set variable to path
		if (whatiscurrenturl2.equals(testurlC1))													// Test if bund page exists
			{
			ReportText = "8) Create Bundle Passed: " + whatiscurrenturl2;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results		
			SS = "BundlePass27_";																	// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl2.equals(testurlC2))												// Test if create page exists
				{
				ReportText = "8) Create Bundle Failed: " + whatiscurrenturl2;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "CreateBundleFail27_";															// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			else
				{
				ReportText = "8) Unknown Error: " + whatiscurrenturl2;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "CreateBundleError27_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}
		
		// 17.1 Select the highest Bundle
		//*[@id="bundle-table"]/tbody/tr[1]/td[1]/a
		driver.findElement(By.xpath("//tbody/tr[1]/td[1]/a")).click();								// Click the newest link from the table		
		Timeloop.Execute(driver, "/details");														// Ensure the correct page is displayed in a sensible time
		ReportText = "9) Open the newest Bundle";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		//Select the Edit Button
		//<a href="/hostedmgr/acct/319/cost/549/bund/1702/edit" class="ym-button">Edit</a>  //*[@id="main"]/div/div/section[2]/article/div/div[2]/div[17]/a[1]
		try
			{
			driver.findElement(By.xpath("//a[contains(.,'Edit')]")).click();						// Click the Edit Button		
			}
		catch (Exception e)
			{
			driver.findElement(By.xpath("//div/div/section[2]/article/div/div[2]/div[17]/a[1]")).click(); // Click the Edit Button
			}
		Thread.sleep(3000);																			// Pause 3 seconds for page update		
		ReportText = "10) Selected the Edit Button";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Edit the values of the bundle to match the TestData from Sheet34
		EditBundle_Action.Execute(driver,8);														// Edit Bundle using the appModule EditBundle_Action_Action
		
		// Test if Edit occurred
		String whatiscurrenturl3 = driver.getCurrentUrl();											// Set variable to the current web address
		String testurlE1 = "https://services-sandbox.hopewiser.com/hostedmgr/acct/319/cost/549/bund";// Set variable to path
		String testurlE2 = "/edit";																	// Set variable to path
		if (whatiscurrenturl3.equals(testurlE1))													// Test if bund page exists
			{
			ReportText = "11) Create Bundle Passed: " + whatiscurrenturl3;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "EditBundlePass27_";																// Edit variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Edit Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl3.equals(testurlE2))												// Test if edit page exists
				{
				ReportText = "11) Create Bundle Failed: " + whatiscurrenturl3;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				//Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "EditBundleFail27_";															// Edit variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Edit Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			else
				{
				ReportText = "11) Unknown Error: " + whatiscurrenturl3;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "EditBundleError27_";															// Edit variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Edit Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}
		
	   	// 17.2 Select the left hand Accounts link 
    	// <a href="/hostedmgr/acct">Accounts</a>	
    	driver.findElement(By.xpath("//a[@href='/hostedmgr/acct']")).click();						// Click the Your Account link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/acct");			// Ensure the correct page is displayed in a sensible time
		ReportText = "12) Click the Account link";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountsPage27_";																		// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Search for Hopewiser Test	
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet12");			// Look in the Excel sheet for test data
		sUserName = ExcelUtils.getCellData(1, 1);													// Get the User Name From TestData.xlsx Sheet12
		driver.findElement(By.xpath("//input[@id='textfilteraccount-table']")).sendKeys(sUserName); // Enter the user name
		Thread.sleep(3000);																   			// Pause 3 seconds for page update
		ReportText = "13) Searched for Hopewiser Test";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Select HopewiserQAAutomation from the table	
		//*[@id="account-table"]/tbody/tr[22]/td[1]/a/span/span //*[@id="account-table"]/tbody/tr[23]/td[1]/a/span/span
		driver.findElement(By.xpath("//span[contains(.,'Hopewiser Test')]")).click();				// Click the First Account in the table (tr number changes)
		Timeloop.Execute(driver, "/details");														// Ensure the correct page is displayed in a sensible time
		
		// Select Bundles tab
		//*[@id="main"]/div/div/section[2]/article/div/div[1]/ul/li[4]/a
		driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[4]/a")).click(); // Click Bundles tab
		Timeloop.Execute(driver, "/bund");															// Ensure the correct page is displayed in a sensible time
		ReportText = "14) Select Bundle from table";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
				
		//*[@id="bundle-table"]/tbody/tr[1]/td[1]/a
		driver.findElement(By.xpath("//tbody/tr[1]/td[1]/a")).click();								// Click the newest link from the table		
		Timeloop.Execute(driver, "/details");														// Ensure the correct page is displayed in a sensible time	
		ReportText = "15) Open the newest Bundle";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Delete Bundle using the appModule Delete_Action
		Delete_Action.Execute(driver);																// Delete using the appModule Delete_Action
		
		// Test if Delete occurred
		String whatiscurrenturl4 = driver.getCurrentUrl();											// Set variable to the current web address
		String testurlD1 = "https://services-sandbox.hopewiser.com/hostedmgr/acct/319/cost/549/bund";// Set variable to path
		String testurlD2 = "/delete";																// Set variable to path
		if (whatiscurrenturl4.equals(testurlD1))													// Test if bund page exists
			{
			ReportText = "16) Delete Bundle Passed: " + whatiscurrenturl4;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results		
			SS = "DeleteBundlePass27_";																// Delete variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Delete Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl4.equals(testurlD2))												// Test if delete page exists
				{
				ReportText = "16) Delete Bundle Failed: " + whatiscurrenturl4;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "DeleteBundleFail27_";															// Delete variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Delete Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			else
				{
				ReportText = "16) Unknown Error: " + whatiscurrenturl4;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "DeleteBundleError27_";														// Delete variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Delete Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}
    	
		// 4.1 Log Off
		driver.findElement(By.xpath("//a[@href='/hostedmgr/secure/logout']")).click(); 				// Click the Log Off link
		ReportText = "17) Logged off";																// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Close the Browser and kill task (Postcondition)
    	driver.quit();																				// Close the Browser
		ReportText = "18) Closed Browser";															// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
   		}
	}