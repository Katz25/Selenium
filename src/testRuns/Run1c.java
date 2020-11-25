package testRuns;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import appModule.Activate_Action;
import appModule.Output;
import appModule.ScreenShot_Action;
import appModule.Timeloop;

import utility.Constant;
import utility.ExcelUtils;
/*
 * Author: Maxine Flook
 * Selenium tests V1
 */
public class Run1c 
	{	
	public static void main(String[] args) throws Exception 
		{
		// Launch Chrome (Precondition)
		String exePath = "C:\\Selenium\\chromedriver.exe";										// Set variable to path where Chrome.exe is stored
		System.setProperty("webdriver.chrome.driver", exePath);									// Set Path to open Chrome from
		WebDriver driver = new ChromeDriver();													// Open a new instance of Chrome
		String ReportText = "1) Run1c Chrome Launched";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
	  	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");		// Look in the Excel sheet for test data
		String sUserName = ExcelUtils.getCellData(1, 1);
		
		// Open the login website using the Url (Precondition)
		// Must add the 'ActivateAccount' url below for this script to work.  Get this from the Activate Password email created by Run1.
		try
			{
	        Class.forName("com.mysql.jdbc.Driver");												// Instantiate com.mysql.JDBC.Driver
	        Connection conn = DriverManager.getConnection("jdbc:mysql://10.0.0.19:3306/hosted", "qatest", "test"); // Log on to the hosted SQL Database 
	        Statement stmt = conn.createStatement();											// Instantiate a Statement
	        String SQLCode = "SELECT acct_activationcode FROM account WHERE acct_companyname = 'QA Testing Account'"; // Create the SQL script
	        ResultSet rs = stmt.executeQuery(SQLCode);											// Set up how to run the sql script
	        String ActivateCode = null;															// Instantiate the var forgotCode as null
	        while ( rs.next() ) 
	         	{
	        	ActivateCode = rs.getString("acct_activationcode");									// Run the SQL Statement and get the string it found
	            }
	        stmt.close();																		// End the current Statement 
	        conn.close();																		// End the current Connection 
	        if (ActivateCode == null)																// If forgotCode = null something has gone wrong 
	        	{
				ReportText = "2) New Password was not found for: "+sUserName;					// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				System.exit(1);																	// Drop out of script
	        	}
	        String ActivateAccount = "https://services-sandbox.hopewiser.com/hostedmgr/activate/"+ActivateCode; // Set up the first page to open
			driver.get(ActivateAccount);														// Open the login website using the Url
			ReportText = "2) " + ActivateAccount + " Website Opened";							// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			}
		catch (Exception e)
			{
			ReportText = "2) The ActivateAccount variable has not been updated";				// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			System.exit(1);																		// Drop out of script
			}
		
		// 1.1 Login to Address Server in the cloud
		Activate_Action.Execute(driver);														// Activate Registration using the appModule Activate_Action = 3)
				
		// Testing if Registration occurred
		String whatiscurrenturl = driver.getCurrentUrl();										// Set variable to the current web address
		String Passurl = "https://services-sandbox.hopewiser.com/get-started/";					// Set variable to path
		String Failurl = "https://services-sandbox.hopewiser.com/hostedmgr/activate/";			// Set variable to path
		if (whatiscurrenturl.equals(Passurl))													// Test if home page exists
			{
			ReportText = "4) Registration Passed: " + whatiscurrenturl;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "RegistrationPass1c_";													// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{
			if (whatiscurrenturl.contains(Failurl))												// Test if logon page exists
				{
				ReportText = "4) Registration Failed: " + whatiscurrenturl;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				ReportText = "4.0) The User Name or Password is incorrect on this web site";	// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				ReportText = "4.1) Account Activation has probably not been completed.";		// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "RegistrationFail1c_";												// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			else
				{
				ReportText = "4) Unknown Error: " + whatiscurrenturl;							// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "RegistrationError1c_";												// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			}
		
		// 1.2 From the Start of the free trial page Create Cost Centre
		driver.findElement(By.partialLinkText("Your Account")).click();        					// Click Your Account tab
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/home");		// Ensure the correct page is displayed in a sensible time
		// Edit an Account / Cost centre
		driver.findElement(By.xpath("//a[contains(.,'Edit')]")).click();    					// Click Edit Account Details Button on Account details page
		Timeloop.Execute(driver, "/edit");														// Ensure the correct page is displayed in a sensible time
		ReportText = "5) Click the Edit Account Details button";								// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		// Cost Centres Tab
		driver.findElement(By.xpath("//a[contains(.,'Cost Centres')]")).click();       			// Click Cost Centres tab
		Timeloop.Execute(driver, "/managecost");												// Ensure the correct page is displayed in a sensible time
		ReportText = "6) Cost Centre tab opened";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		try
			{
			// <a class="ym-button ym-danger" href="/hostedmgr/account/406/cost/561/use">Use Cost Centres</a>
			driver.findElement(By.xpath("//a[@class='ym-button ym-danger']")).click();    		// Click the Use Cost Centre button
			}
		catch (Exception e)
			{
			// <a class="ym-button ym-small" href="/hostedmgr/account/400/cost/create">Create&nbsp;Cost&nbsp;Centre</a>
			driver.findElement(By.xpath("//a[@class='ym-button ym-small']")).click();    		// Click the Create Cost Centre button (use when all data has not been deleted)
			}
		
		Thread.sleep(3000);																		// Pause 3 seconds for page update
		ReportText = "7) Use Cost Centres button clicked";										// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// Name the Cost Centre
		driver.findElement(By.xpath("//input[@id='name']")).clear();			        		// Clear the Cost Centre Name field
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("DefaultCC");      		// Enter DefaultCC for the New Cost Centre Name field
		Thread.sleep(3000);																		// Pause 3 seconds for page update
				
		// Save the New Cost Centre
		// <input type="submit" class="ym-button ym-danger" value="Save">
		driver.findElement(By.xpath("//input[@class='ym-button ym-danger']")).click(); 			// Click the Save button		
		Timeloop.Execute(driver, "/managecost");												// Ensure the correct page is displayed in a sensible time
		ReportText = "8) Saved Cost Centre";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// 4.1 Log Off
		driver.findElement(By.xpath("//a[@href='/hostedmgr/secure/logout']")).click(); 			// Click the Log Off link
		ReportText = "9) Logged off";															// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// Close the Browser and kill task (Postcondition)
		driver.quit();																			// Close the Browser
		ReportText = "10) Closed Browser";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console	
		}
	}