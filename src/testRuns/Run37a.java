package testRuns;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import appModule.Output;
import appModule.ScreenShot_Action;
import appModule.Timeloop;

import utility.Constant;
import utility.ExcelUtils; 
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class Run37a 
	{
    public static void main(String[] args) throws Exception 
    	{   	
		// Run36 must be run before this test can be run to create a new Forgotten password email.
		// Launch Chrome (Precondition)
		String exePath = "C:\\Selenium\\chromedriver.exe";										// Set variable to path where Chrome.exe is stored
		System.setProperty("webdriver.chrome.driver", exePath);									// Set Path to open Chrome from
		WebDriver driver = new ChromeDriver();													// Open a new instance of Chrome
		String ReportText = "1) Run37a Chrome Launched";										// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
	  	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet16");		// Look in the Excel sheet for test data
    	String sUserName = ExcelUtils.getCellData(1, 1);
		String sPassword = ExcelUtils.getCellData(1, 2);
		
		// Open the login website using the Url (Precondition)
		// Must add the 'Hopewiser Forgotten Password' url below for this script to work.  Get this from the Hopewiser Forgotten Password email created by Run36.
		try
			{
	        Class.forName("com.mysql.jdbc.Driver");												// Instantiate com.mysql.JDBC.Driver
	        Connection conn = DriverManager.getConnection("jdbc:mysql://10.0.0.19:3306/hosted", "qatest", "test"); // Log on to the hosted SQL Database 
	        Statement stmt = conn.createStatement();											// Instantiate a Statement
	        String SQLCode = "SELECT user_forgotcode FROM users WHERE user_login = '"+sUserName+"'"; // Create the SQL script
	        ResultSet rs = stmt.executeQuery(SQLCode);											// Set up how to run the sql script
	        String forgotCode = null;															// Set forgotCode to null
	        while ( rs.next() ) 
         		{
	        	forgotCode = rs.getString("user_forgotcode");									// Run the SQL Statement and get the string it found
         		}
	        stmt.close();																		// End the current Statement 
	        conn.close();																		// End the current Connection 
	        if (forgotCode == null)																// If forgotCode = null something has gone wrong 
        		{
	        	ReportText = "2) New Password was not found for : "+sUserName;					// Set up report text variable
	        	Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
	        	System.exit(1);																	// Drop out of script
        		}
	        String HForgottenPassword = "https://services-sandbox.hopewiser.com/hostedmgr/resetpassword/"+forgotCode; // Set up the first page to open
			driver.get(HForgottenPassword);														// Open the login website using the Url
			ReportText = "2) " + HForgottenPassword + " Website Opened";						// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			}
		catch (Exception e)
			{
			ReportText = "2) The HForgottenPassword variable has not been updated";				// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			System.exit(1);																		// Drop out of script
			}
		
		try
			{
			// Enter New Password
			// <input id="password" name="password" required="required" type="password" value="" maxlength="50">
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys(sPassword);		// Enter a value From TestData Sheet16			
			Thread.sleep(1000);																	// Pause 1 seconds for page update
			ReportText = "3) The correct page has been opened";									// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			}
		catch (Exception e)
			{
			ReportText = "3) The HForgottenPassword variable has not been updated";				// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			System.exit(1);																		// Drop out of script
			}
		
		// Enter Confirm New Password
		// <input id="confirmpassword" name="confirmpassword" required="required" type="password" value="" maxlength="50">
		String FakePassword = sPassword + "f";
		driver.findElement(By.xpath("//input[@id='confirmpassword']")).sendKeys(FakePassword);	// Enter a different value From TestData Sheet16 so confirm fails
		Thread.sleep(1000);																		// Pause 1 seconds for page update
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		String SS = "ResetPassword37a_";														// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Select Log On Button
		// <input type="submit" class="ym-button ym-danger" value="Log&nbsp;On">
		driver.findElement(By.xpath("//input[@type='submit']")).click();						// Click the Log On Button			
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/resetpassword/"); // Ensure the correct page is displayed in a sensible time
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "LogOn37a_";																		// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Close the Browser and kill task (Postcondition)
		driver.quit();																			// Close the Browser
		ReportText = "4) Closed Browser";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console*
    	}
	}