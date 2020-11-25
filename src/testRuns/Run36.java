package testRuns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import appModule.Output;
import appModule.ScreenShot_Action;
import appModule.Timeloop;
import utility.Constant;
import utility.ExcelUtils; 
/*
 * Author: Maxine Flook
 * Selenium tests V1
 */
public class Run36 
	{
    public static void main(String[] args) throws Exception 
    	{   	
    	// Launch Chrome (Precondition)
    	String exePath = "C:\\Selenium\\chromedriver.exe";										// Set variable to path where Chrome.exe is stored
    	System.setProperty("webdriver.chrome.driver", exePath);									// Set Path to open Chrome from
    	WebDriver driver = new ChromeDriver();													// Open a new instance of Chrome
		String ReportText = "1) Run36 Chrome Launched";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet16");	// Look in the Excel sheet for test data
		String sUserName = ExcelUtils.getCellData(1, 1);		
        
       	// Open the login website using the Url (Precondition)
    	driver.get(Constant.URL);																// Open the login website using the Url
		ReportText = "2) " + Constant.URL + " Website Opened";									// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		String SS = "LoginPage36_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// 4.2 Select the Forgotten your password? link
		//<a href="/hostedmgr/forgottenpassword;jsessionid=4BAE5EE62DBC47A7C6ED3EDF4C7ACC42.addrsvr2">Forgotten your password?</a> //*[@id="main"]/div/div/section[2]/article/div/form/p[1]/a
		driver.findElement(By.xpath("//a[contains(.,'Forgotten your password?')]")).click();	// Click the Forgotten your password? link			
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/forgottenpassword;jsessionid=");	// Ensure the correct page is displayed in a sensible time
		ReportText = "3) Forgotten Password link clicked";										// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
    	// Enter User Name
		// <input id="login" name="login" required="required" type="text" value="">
		driver.findElement(By.xpath("//input[@id='login']")).sendKeys(sUserName);				// Enter a value From TestData Sheet16			
		Thread.sleep(1000);																		// Pause 1 seconds for page update
		
		// Enter email
		// <input id="notifyemail" name="notifyemail" required="required" type="email" value="">
		driver.findElement(By.xpath("//input[@id='notifyemail']")).sendKeys("qateam@hopewiser.com"); // Enter the Test team email
		Thread.sleep(1000);																		// Pause 1 seconds for page update
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "ForgottenPassword36_";															// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Click Continue Button
		// <input type="submit" class="ym-button ym-danger" value="Continue">
		driver.findElement(By.xpath("//input[@value='Continue']")).click();						// Click the Continue Button			
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/forgottenpassword-success");	// Ensure the correct page is displayed in a sensible time
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "ForgottenPasswordResult36_";														// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Test if title of page is https://services-sandbox.hopewiser.com/hostedmgr/forgottenpassword-success 
		String whatiscurrenturl = driver.getCurrentUrl();										// Set variable to the current web address
		String testurl1 = "https://services-sandbox.hopewiser.com/hostedmgr/forgottenpassword-success";	// Set variable to path
		if (whatiscurrenturl.contains(testurl1))												// Test if Success page exists
			{
			ReportText = "3.0) Forgotten Password Passed";										// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console	
			ReportText = "3.1) Please wait for the Hopewiser Forgotten Password email.";		// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "ForgottenPassword36_";														// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver,SS);												// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{
			ReportText = "3.0) Forgotten Password Failed";										// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "FailedPassword36_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver,SS);												// Create Screenshot using appModule.ScreenShot_Action
			System.exit(1);																		// Drop out of script
			}
		
		// Close the Browser and kill task (Postcondition)
    	driver.quit();																			// Close the Browser
		ReportText = "4) Closed Browser";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
    	}
	}