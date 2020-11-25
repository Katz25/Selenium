package testRuns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
public class Run31 
	{
    public static void main(String[] args) throws Exception 
    	{   	
    	// Launch Chrome (Precondition)
    	String exePath = "C:\\Selenium\\chromedriver.exe";											// Set Variable for path where chromedriver.exe is stored
    	System.setProperty("webdriver.chrome.driver", exePath);										// Set Path to open Chrome from
    	WebDriver driver = new ChromeDriver();														// Open a new instance of Chrome
		String ReportText = "1) Run31 Chrome Launched";												// Set up report text variable
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
			String SS = "LoginPass31_";																// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl.contains(testurl2))												// Test if logon page exists
				{
				ReportText = "3) Login Failed: " + whatiscurrenturl;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginFail31_";															// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			else
				{
				ReportText = "3) Unknown Error: " + whatiscurrenturl;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginError31_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}  	
        
    	// 11.0 Select the left hand Downloads link 
    	// <a href="/hostedmgr/download">Downloads</a>	
    	driver.findElement(By.xpath("//a[@href='/hostedmgr/download']")).click();					// Click the Downloads link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/download");		// Ensure the correct page is displayed in a sensible time
		ReportText = "4) Click the Downloads link";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		String SS = "DownloadsPage31_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Test if Download opened
		String whatiscurrenturl4 = driver.getCurrentUrl();											// Set variable to the current web address
		String testurl3 = "https://services-sandbox.hopewiser.com/hostedmgr/download";				// Set Variable to path
		if (whatiscurrenturl4.contains(testurl3))													// Test if Home page exists
			{
			ReportText = "5) Login Passed: " + whatiscurrenturl4;									// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "DownloadPass31_";																	// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{
			ReportText = "5) Unknown Error: " + whatiscurrenturl4;									// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "DownloadError31_";																// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			System.exit(1);																			// Drop out of script
			}  	
		
		// Select the Download FastaddressClient button
		//<a class="ym-button ym-primary" href="downloads/FastAddressClientSetup.zip"><i class="icon-download-alt"></i>&nbsp;&nbsp;Download FastAddressClient</a>
    	driver.findElement(By.xpath("//a[@href='downloads/FastAddressClientSetup.zip']")).click();	// Click the Downloads link
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
		ReportText = "6) Click the Downloads FastAddressClientSetup Button";						// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		//Select the first here link
		//<a href="/products/fastaddressclient/">here</a>
    	driver.findElement(By.xpath("//a[@href='/products/fastaddressclient/']")).click();			// Click a here link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/products/fastaddressclient/"); // Ensure the correct page is displayed in a sensible time
		ReportText = "7) Click the here link";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AddressServerDesktopClient31_";														// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action

		// Select the back button to return to the Downloads page
		driver.navigate().back();																	// Click the browser back button
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/download");		// Ensure the correct page is displayed in a sensible time
		ReportText = "8) Click the browser back button";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "DownloadsPage31_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the second here link
		//<a href="downloads/FastAddressClient.pdf#pagemode=none&amp;zoom=100%">here</a> //*[@id="main"]/div/div/section[2]/article/div/p[6]/a
		driver.findElement(By.xpath("//a[contains(@href,'downloads/FastAddressClient.pdf')]")).click();	// Click a here link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/downloads/FastAddressClient.pdf#pagemode=none&zoom=100%"); // Ensure the correct page is displayed in a sensible time
		ReportText = "9) Click the here link";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "FastAddressClientGuide31_";															// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the back button to return to the Downloads page
		driver.navigate().back();																	// Click the browser back button
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/download");		// Ensure the correct page is displayed in a sensible time
		ReportText = "10) Click the browser back button";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "DownloadsPage31_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the Contact Us link
		//<a href="/home/contact-us/">contact us</a>
    	driver.findElement(By.xpath("//a[@href='/home/contact-us/']")).click();						// Click the Contact Us link
    	Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/home/contact-us/");		// Ensure the correct page is displayed in a sensible time
		String whatiscurrenturl5 = driver.getCurrentUrl();											// Set variable to the current web address
		ReportText = "11) Click the contact us link" + whatiscurrenturl5;							// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "ContactUs31_";																		// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the back button to return to the Downloads page
		driver.navigate().back();																	// Click the browser back button
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/download");		// Ensure the correct page is displayed in a sensible time
		ReportText = "12) Click the browser back button";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "DownloadsPage31_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
    	// 2.1 Select the left hand Your Details link (Step not required but add for stability)
    	// <a href="/hostedmgr/account/319/cost/549/user/1986/details">Your&nbsp;Details</a> 		//*[@id="t-yourdetails"]/a
    	driver.findElement(By.xpath("//*[@id='t-yourdetails']")).click();							// Click the Your Details link
    	Timeloop.Execute(driver, "/details");														// Ensure the correct page is displayed in a sensible time
		ReportText = "13) Click the Your Details link";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "YourDetailsPage31_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action	
		
		// Select the Change Password button
		//<a href="/hostedmgr/account/319/cost/456/user/1986/password" class="ym-button ym-small" style="width:10em; margin:0.75em 0.75em 0 0"><div style="text-align:center">Change&nbsp;Password</div></a> //*[@id="main"]/div/div/section[2]/article/div/div/div[2]/div/a[3]/div
		driver.findElement(By.xpath("//a[contains(@href,'password')]")).click();					// Click the Change Password Button
		Timeloop.Execute(driver, "/password");														// Ensure the correct page is displayed in a sensible time
		ReportText = "14) Click the Your Details Change Password Button";							// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "ChangePasswordPage31_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Test if Change Your Password Page opened
		String whatiscurrenturl7 = driver.getCurrentUrl();											// Set variable to the current web address
		String testurl5 = "password";																// Set Variable to path
		if (whatiscurrenturl7.contains(testurl5))													// Test if details page exists
			{
			ReportText = "15) Login Passed: " + whatiscurrenturl7;									// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "DownloadPass31_";																	// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{
			ReportText = "15) Unknown Error: " + whatiscurrenturl7;									// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "DownloadError31_";																// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			System.exit(1);																			// Drop out of script
			}  
		
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet3");		  // Look in the Excel sheet for test data
		String sOldPassword = ExcelUtils.getCellData(1, 2);										  // Get the Old Password from TestData Sheet3
		String sNewPassword = ExcelUtils.getCellData(1, 3);										  // Get the New Password from TestData Sheet3
		
		// Enter Old Password
		//<input id="oldpassword" name="oldpassword" required="required" type="password" value="" maxlength="50">
		driver.findElement(By.xpath("//input[@id='oldpassword']")).sendKeys(sOldPassword);			// Enter value for Old Password from TestData Sheet13
		
		// Enter New Password
		//<input id="password" name="password" required="required" type="password" value="" maxlength="50">
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(sNewPassword);				// Enter value for New Password from TestData Sheet13
		
		// Enter Confirm Password
		//<input id="confirmpassword" name="confirmpassword" required="required" type="password" value="" maxlength="50">
		driver.findElement(By.xpath("//input[@id='confirmpassword']")).sendKeys(sNewPassword);		// Enter value for Confirm Password from TestData Sheet13
		
		// Click Change Password Button
		//<input type="submit" class="ym-button ym-danger" value="Change&nbsp;Password">
		driver.findElement(By.xpath("//input[@class='ym-button ym-danger']")).click();				// Click the Change Password button
		Timeloop.Execute(driver, "/details");														// Ensure the correct page is displayed in a sensible time
		
		// Test if Password was Changed
		String whatiscurrenturl8 = driver.getCurrentUrl();											// Set variable to the current web address
		String testurlP1 = "details";																// Set variable to path
		String testurlP2 = "password";																// Set variable to path
		if (whatiscurrenturl8.contains(testurlP1))													// Test if the Your Details page exists
			{
			ReportText = "16) Edit Account Passed: " + whatiscurrenturl8;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "EditAccountPass31_";																// Edit variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Edit Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl8.contains(testurlP2))												// Test if Password page exists
			{
				ReportText = "16) Edit Account Failed: " + whatiscurrenturl8;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "EditAccountFail31_";															// Edit variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Edit Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
			}
			else
				{
				ReportText = "16) Unknown Error: " + whatiscurrenturl8;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "EditAccountError31_";															// Edit variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Edit Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}
		
		driver.findElement(By.xpath("//a[@href='/hostedmgr/secure/logout']")).click(); 				// Click the Log Off link
		ReportText = "17) Logged off";																// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet16");			// Look in the Excel sheet for test data
    	
		// Login using the appModule Login_Action To prove password change worked
		Login_Action.Execute(driver);																// Login using the appModule Login_Action
		
		// Test if Login occurred
		String whatiscurrenturl9 = driver.getCurrentUrl();											// Set variable to the current web address
		String testurl6 = "https://services-sandbox.hopewiser.com/hostedmgr/home";					// Set Variable to path
		String testurl7 = "https://services-sandbox.hopewiser.com/hostedmgr/logon?error=true";		// Set Variable to path
		if (whatiscurrenturl9.contains(testurl6))													// Test if Home page exists
			{
			ReportText = "18) Login Passed: " + whatiscurrenturl9;									// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "LoginPass31_";																	// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl9.contains(testurl7))												// Test if logon page exists
				{
				ReportText = "18) Login Failed: " + whatiscurrenturl9;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "LoginFail31_";																// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			else
				{
				ReportText = "18) Unknown Error: " + whatiscurrenturl;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "LoginError31_";																// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}  	
		
    	// Select the left hand Your Details link
    	// <a href="/hostedmgr/account/319/cost/549/user/1986/details">Your&nbsp;Details</a> 		//*[@id="t-yourdetails"]/a
    	driver.findElement(By.xpath("//*[@id='t-yourdetails']")).click();							// Click the Your Details link
    	Timeloop.Execute(driver, "/details");														// Ensure the correct page is displayed in a sensible time
		ReportText = "19) Click the Your Details link";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "YourDetailsPage31_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the Change Password button to return the Password to the start value
		//<a href="/hostedmgr/account/319/cost/456/user/1986/password" class="ym-button ym-small" style="width:10em; margin:0.75em 0.75em 0 0"><div style="text-align:center">Change&nbsp;Password</div></a> //*[@id="main"]/div/div/section[2]/article/div/div/div[2]/div/a[3]/div
		driver.findElement(By.xpath("//a[contains(@href,'password')]")).click();					// Click the Change Password Button
		Timeloop.Execute(driver, "/password");														// Ensure the correct page is displayed in a sensible time
		ReportText = "20) Click the Your Details Change Password Button";							// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "ChangePasswordPage31_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Enter Old Password
		//<input id="oldpassword" name="oldpassword" required="required" type="password" value="" maxlength="50">
		driver.findElement(By.xpath("//input[@id='oldpassword']")).sendKeys(sNewPassword);			// Enter value for Old Password from TestData Sheet13
		
		// Enter New Password
		//<input id="password" name="password" required="required" type="password" value="" maxlength="50">
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(sOldPassword);				// Enter value for New Password from TestData Sheet13
		
		// Enter Confirm Password
		//<input id="confirmpassword" name="confirmpassword" required="required" type="password" value="" maxlength="50">
		driver.findElement(By.xpath("//input[@id='confirmpassword']")).sendKeys(sOldPassword);		// Enter value for Confirm Password from TestData Sheet13
		SS = "EditPasswordPage31_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Click Change Password Button
		// <input type="submit" class="ym-button ym-danger" value="Change&nbsp;Password">
		driver.findElement(By.xpath("//input[@class='ym-button ym-danger']")).click();				// Click the Change Password button
		Timeloop.Execute(driver, "/details");														// Ensure the correct page is displayed in a sensible time
		ReportText = "21) Clicked the Change Your Password, Change Password Button";				// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		SS = "YourDetailsPage31_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Test if Password was Changed
		String whatiscurrenturl10 = driver.getCurrentUrl();											// Set variable to the current web address
		String testurlP3 = "details";																// Set variable to path
		String testurlP4 = "password";																// Set variable to path
		if (whatiscurrenturl10.contains(testurlP3))													// Test if the Your Details page exists
			{
			ReportText = "22) Edit Account Passed: " + whatiscurrenturl10;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "EditAccountPass31_";																// Edit variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Edit Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl10.contains(testurlP4))												// Test if Password page exists
			{
				ReportText = "22) Edit Account Failed: " + whatiscurrenturl10;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "EditAccountFail31_";															// Edit variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Edit Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
			}
			else
				{
				ReportText = "22) Unknown Error: " + whatiscurrenturl10;							// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "EditAccountError31_";															// Edit variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Edit Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}
		
		// Select the left hand Your Details link
    	// <a href="/hostedmgr/account/319/cost/549/user/1986/details">Your&nbsp;Details</a> 		//*[@id="t-yourdetails"]/a
    	driver.findElement(By.xpath("//*[@id='t-yourdetails']")).click();							// Click the Your Details link
    	Timeloop.Execute(driver, "/details");														// Ensure the correct page is displayed in a sensible time
		ReportText = "23) Click the Your Details link";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "YourDetailsPage31_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
    	
		// 4.1 Log Off
		driver.findElement(By.xpath("//a[@href='/hostedmgr/secure/logout']")).click(); 				// Click the Log Off link
		ReportText = "24) Logged off";																// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	
		// Close the Browser and kill task (Postcondition)
    	driver.quit();
		ReportText = "25) Closed Browser";															// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	}
	}