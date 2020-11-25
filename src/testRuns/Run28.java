package testRuns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import appModule.EditAccount_Action;
import appModule.EditYourDetails_Action;
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
public class Run28 
	{
    public static void main(String[] args) throws Exception 
    	{   	
    	// Launch Chrome (Precondition)
    	String exePath = "C:\\Selenium\\chromedriver.exe";											// Set Variable for path Where Chrome.exe is stored
    	System.setProperty("webdriver.chrome.driver", exePath);										// Set Path to open Chrome from
    	WebDriver driver = new ChromeDriver();														// Open a new instance of Chrome
		String ReportText = "1) Run28 Chrome Launched";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
        
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet8");			// Look in the Excel sheet for test data
        
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
			String SS = "LoginPass28_";																// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl.contains(testurl2))												// Test if logon page exists
				{
				ReportText = "3) Login Failed: " + whatiscurrenturl;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginFail28_";															// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			else
				{
				ReportText = "3) Unknown Error: " + whatiscurrenturl;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginError28_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}  	
		
		// 15.3 Select the Edit Account Details button 
		//<a href="/hostedmgr/account/319/edit" class="ym-button ym-small" style="width:10em">
		try
			{
			driver.findElement(By.xpath("//a[@href='/hostedmgr/account/319/edit']")).click();		// Click the Edit Account Details button
			}
		catch (Exception e)
			{
			driver.findElement(By.xpath("//a[@class='ym-button ym-small']")).click();				// Click the Edit Account Details button
			}
		ReportText = "5) The Edit Account Details button is clicked";								// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console		
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		String SS = "EditAccountPage28_";															// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method
    	ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet35");		// Look in the Excel sheet for test data
		
		// Edit the Account using the appModule EditAccount_Action
		EditAccount_Action.Execute(driver);															// Edit the Account using the appModule EditAccount_Action
		
		// Test if Edit occurred
		String whatiscurrenturl2 = driver.getCurrentUrl();											// Set variable to the current web address
		String testurlE1 = "https://services-sandbox.hopewiser.com/hostedmgr/home/319";				// Set variable to path
		String testurlE2 = "https://services-sandbox.hopewiser.com/hostedmgr/account/319/edit";		// Set variable to path
		if (whatiscurrenturl2.equals(testurlE1))													// Test if home page exists
			{
			ReportText = "6) Edit Account Passed: " + whatiscurrenturl2;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "EditAccountPass28_";																// Edit variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Edit Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl2.equals(testurlE2))												// Test if edit page exists
				{
				ReportText = "6) Edit Account Failed: " + whatiscurrenturl2;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "EditAccountFail28_";															// Edit variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Edit Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			else
				{
				ReportText = "6) Unknown Error: " + whatiscurrenturl2;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "EditAccountError28_";															// Edit variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Edit Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}		
		try
			{
			driver.findElement(By.xpath("//a[@href='/hostedmgr/account/319/edit']")).click();		// Click the Edit Account Details button
			}
		catch (Exception e)
			{
			driver.findElement(By.xpath("//a[@class='ym-button ym-small']")).click();				// Click the Edit Account Details button
			}
		ReportText = "7) The Edit Account Details button is clicked";								// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console		
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "EditAccountPage28_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
	
		// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method
		ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet36");		// Look in the Excel sheet for test data
	
		// Replace the value in the Hopewiser Test Account using the appModule EditAccount_Action
		EditAccount_Action.Execute(driver);															// Edit the Account using the appModule EditAccount_Action
	
		// Test if Edit replaced occurred
		String whatiscurrenturl3 = driver.getCurrentUrl();											// Set variable to the current web address
		String testurlE3 = "https://services-sandbox.hopewiser.com/hostedmgr/home";					// Set variable to path
		String testurlE4 = "https://services-sandbox.hopewiser.com/hostedmgr/account/319/edit";		// Set variable to path
		if (whatiscurrenturl3.contains(testurlE3))													// Test if home page exists
			{
			ReportText = "8) Edit Account Passed: " + whatiscurrenturl3;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "EditAccountPass28_";																// Edit variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Edit Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl2.contains(testurlE4))												// Test if edit page exists
				{
				ReportText = "8) Edit Account Failed: " + whatiscurrenturl3;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "EditAccountFail28_";															// Edit variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Edit Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			else
				{
				ReportText = "8) Unknown Error: " + whatiscurrenturl3;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "EditAccountError28_";															// Edit variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Edit Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}
		
    	// 11.0 Select the left hand Downloads link 
    	// <a href="/hostedmgr/download">Downloads</a>	
    	driver.findElement(By.xpath("//a[@href='/hostedmgr/download']")).click();					// Click the Downloads link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/download");		// Ensure the correct page is displayed in a sensible time
		ReportText = "9) Click the Downloads link";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "DownloadsPage28_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the Download FastaddressClient button
		//<a class="ym-button ym-primary" href="downloads/FastAddressClientSetup.zip"><i class="icon-download-alt"></i>&nbsp;&nbsp;Download FastAddressClient</a>
    	driver.findElement(By.xpath("//a[@href='downloads/FastAddressClientSetup.zip']")).click();	// Click the Downloads link
		Thread.sleep(3000);																   			// Pause 3 seconds for page update
		ReportText = "10) Click the Downloads FastAddressClientSetup Button";						// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Select the first here link
		//<a href="/products/fastaddressclient/">here</a>
    	driver.findElement(By.xpath("//a[@href='/products/fastaddressclient/']")).click();			// Click a here link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/products/fastaddressclient/"); // Ensure the correct page is displayed in a sensible time
		ReportText = "11) Click the here link";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AddressServerDesktopClient28_";														// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action

		// Select the back button to return to the Downloads page
		driver.navigate().back();																	// Click the browser back button
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/download");		// Ensure the correct page is displayed in a sensible time
		ReportText = "12) Click the browser back button";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "DownloadsPage28_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the second here link
		//<a href="downloads/FastAddressClient.pdf#pagemode=none&amp;zoom=100%">here</a> //*[@id="main"]/div/div/section[2]/article/div/p[6]/a
		driver.findElement(By.xpath("//a[contains(@href,'downloads/FastAddressClient.pdf')]")).click();	// Click a here link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/downloads/FastAddressClient.pdf#pagemode=none&zoom=100%"); // Ensure the correct page is displayed in a sensible time
		ReportText = "13) Click the here link";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "FastAddressClientGuide28_";															// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the back button to return to the Downloads page
		driver.navigate().back();																	// Click the browser back button
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/download");		// Ensure the correct page is displayed in a sensible time
		ReportText = "14) Click the browser back button";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "DownloadsPage28_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the Contact Us link
		//<a href="/home/contact-us/">contact us</a>
    	driver.findElement(By.xpath("//a[@href='/home/contact-us/']")).click();						// Click the Contact Us link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/home/contact-us/");		// Ensure the correct page is displayed in a sensible time
		String whatiscurrenturl5 = driver.getCurrentUrl();											// Set variable to the current web address
		ReportText = "15) Click the contact us link" + whatiscurrenturl5;							// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "ContactUs28_";																		// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the back button to return to the Downloads page
		driver.navigate().back();																	// Click the browser back button
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/download");		// Ensure the correct page is displayed in a sensible time
		ReportText = "16) Click the browser back button";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "DownloadsPage28_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
    	// 2.0 Select the left hand Your Details link
    	// <a href="/hostedmgr/account/319/cost/549/user/1986/details">Your&nbsp;Details</a> 		//*[@id="t-yourdetails"]/a
    	driver.findElement(By.xpath("//*[@id='t-yourdetails']")).click();							// Click the Your Details link
		Timeloop.Execute(driver, "/details");														// Ensure the correct page is displayed in a sensible time
		ReportText = "17) Click the Your Details link";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "YourDetailsPage28_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Test if Your Details Page opened
		String whatiscurrenturl6 = driver.getCurrentUrl();											// Set variable to the current web address
		String testurl4 = "details";																// Set Variable to path
		if (whatiscurrenturl6.contains(testurl4))													// Test if details page exists
			{
			ReportText = "18) Your Details Passed: " + whatiscurrenturl6;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "DownloadPass28_";																	// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{
			ReportText = "18) Unknown Error: " + whatiscurrenturl6;									// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "DownloadError28_";																// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			System.exit(1);																			// Drop out of script
			}  
		
		// Select the Edit button
		//<a href="/hostedmgr/account/319/cost/456/user/1986/edit" class="ym-button ym-small" style="width:10em"><div style="text-align:center">Edit</div></a> //*[@id="main"]/div/div/section[2]/article/div/div/div[2]/div/a[1]/div
    	driver.findElement(By.xpath("//a[contains(@href,'edit')]")).click();						// Click the Edit Button
		Timeloop.Execute(driver, "/edit");															// Ensure the correct page is displayed in a sensible time
		ReportText = "19) Click the Your Details Edit Button";										// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "EditYourDetailsPage28_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method
		ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet38");		// Look in the Excel sheet for test data
		
		// Replace the value in the Hopewiser Test Account using the appModule EditAccount_Action
		EditYourDetails_Action.Execute(driver);														// Edit the Account using the appModule EditYourDetails_Action
		
    	// 2.1 Select the left hand Your Details link (Step not required but add for stability)
    	// <a href="/hostedmgr/account/319/cost/549/user/1986/details">Your&nbsp;Details</a> 		//*[@id="t-yourdetails"]/a
    	driver.findElement(By.xpath("//*[@id='t-yourdetails']")).click();							// Click the Your Details link
		Timeloop.Execute(driver, "/details");														// Ensure the correct page is displayed in a sensible time
		ReportText = "20) Click the Your Details link";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "YourDetailsPage28_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action	
		
		// Select the Change Password button
		//<a href="/hostedmgr/account/319/cost/456/user/1986/password" class="ym-button ym-small" style="width:10em; margin:0.75em 0.75em 0 0"><div style="text-align:center">Change&nbsp;Password</div></a> //*[@id="main"]/div/div/section[2]/article/div/div/div[2]/div/a[3]/div
		driver.findElement(By.xpath("//a[contains(@href,'password')]")).click();					// Click the Change Password Button
		Timeloop.Execute(driver, "/password");														// Ensure the correct page is displayed in a sensible time
		ReportText = "21) Click the Your Details Change Password Button";							// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "ChangePasswordPage28_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Test if Change Your Password Page opened
		String whatiscurrenturl7 = driver.getCurrentUrl();											// Set variable to the current web address
		String testurl5 = "password";																// Set Variable to path
		if (whatiscurrenturl7.contains(testurl5))													// Test if details page exists
			{
			ReportText = "22) Login Passed: " + whatiscurrenturl7;									// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "DownloadPass28_";																	// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{
			ReportText = "22) Unknown Error: " + whatiscurrenturl7;									// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "DownloadError28_";																// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			System.exit(1);																			// Drop out of script
			}  
		
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet8");			// Look in the Excel sheet for test data
		String sOldPassword = ExcelUtils.getCellData(1, 2);											// Get the county name from TestData Sheet8
		String sNewPassword = ExcelUtils.getCellData(1, 3);											// Get the county name from TestData Sheet8
		
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
			ReportText = "23) Edit Account Passed: " + whatiscurrenturl8;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "EditAccountPass28_";																// Edit variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Edit Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl8.contains(testurlP2))												// Test if Password page exists
				{
				ReportText = "23) Edit Account Failed: " + whatiscurrenturl8;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "EditAccountFail28_";															// Edit variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Edit Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			else
				{
				ReportText = "23) Unknown Error: " + whatiscurrenturl8;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "EditAccountError28_";															// Edit variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Edit Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}
		
		driver.findElement(By.xpath("//a[@href='/hostedmgr/secure/logout']")).click(); 				// Click the Log Off link
		ReportText = "24) Logged off";																// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet13");			// Look in the Excel sheet for test data
    	
		// Login using the appModule Login_Action To prove password change worked
		Login_Action.Execute(driver);																// Login using the appModule Login_Action
		
		// Test if Login occurred
		String whatiscurrenturl9 = driver.getCurrentUrl();											// Set variable to the current web address
		String testurl6 = "https://services-sandbox.hopewiser.com/hostedmgr/home";					// Set Variable to path
		String testurl7 = "https://services-sandbox.hopewiser.com/hostedmgr/logon?error=true";		// Set Variable to path
		if (whatiscurrenturl9.contains(testurl6))													// Test if Home page exists
			{
			ReportText = "25) Login Passed: " + whatiscurrenturl9;									// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "LoginPass28_";																	// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl9.contains(testurl7))												// Test if logon page exists
				{
				ReportText = "25) Login Failed: " + whatiscurrenturl9;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "LoginFail28_";																// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			else
				{
				ReportText = "25) Unknown Error: " + whatiscurrenturl;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "LoginError28_";																// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}  	
		
    	// Select the left hand Your Details link
    	//<a href="/hostedmgr/account/319/cost/549/user/1986/details">Your&nbsp;Details</a> 		//*[@id="t-yourdetails"]/a
    	driver.findElement(By.xpath("//*[@id='t-yourdetails']")).click();							// Click the Your Details link
		Timeloop.Execute(driver, "/details");														// Ensure the correct page is displayed in a sensible time
		ReportText = "26) Click the Your Details link";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "YourDetailsPage28_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the Change Password button to return the Password to the start value
		//<a href="/hostedmgr/account/319/cost/456/user/1986/password" class="ym-button ym-small" style="width:10em; margin:0.75em 0.75em 0 0"><div style="text-align:center">Change&nbsp;Password</div></a> //*[@id="main"]/div/div/section[2]/article/div/div/div[2]/div/a[3]/div
		driver.findElement(By.xpath("//a[contains(@href,'password')]")).click();					// Click the Change Password Button
		Timeloop.Execute(driver, "/password");														// Ensure the correct page is displayed in a sensible time
		ReportText = "27) Click the Your Details Change Password Button";							// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "ChangePasswordPage28_";																// Create variable to help name the Screenshot
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
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "EditPasswordPage28_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Click Change Password Button
		// <input type="submit" class="ym-button ym-danger" value="Change&nbsp;Password">
		driver.findElement(By.xpath("//input[@class='ym-button ym-danger']")).click();				// Click the Change Password button
		Timeloop.Execute(driver, "/details");														// Ensure the correct page is displayed in a sensible time
		ReportText = "28) Clicked the Change Your Password, Change Password Button";				// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "YourDetailsPage28_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Test if Password was Changed
		String whatiscurrenturl10 = driver.getCurrentUrl();											// Set variable to the current web address
		String testurlP3 = "details";																// Set variable to path
		String testurlP4 = "password";																// Set variable to path
		if (whatiscurrenturl10.contains(testurlP3))													// Test if the Your Details page exists
			{
			ReportText = "29) Edit Account Passed: " + whatiscurrenturl10;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "EditAccountPass28_";																// Edit variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Edit Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl10.contains(testurlP4))												// Test if Password page exists
			{
				ReportText = "29) Edit Account Failed: " + whatiscurrenturl10;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "EditAccountFail28_";															// Edit variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Edit Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
			}
			else
				{
				ReportText = "29) Unknown Error: " + whatiscurrenturl10;							// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "EditAccountError28_";															// Edit variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Edit Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}
		
		// Select the left hand Your Details link
    	// <a href="/hostedmgr/account/319/cost/549/user/1986/details">Your&nbsp;Details</a> 		//*[@id="t-yourdetails"]/a
    	driver.findElement(By.xpath("//*[@id='t-yourdetails']")).click();							// Click the Your Details link
		Timeloop.Execute(driver, "/details");														// Ensure the correct page is displayed in a sensible time
		ReportText = "30) Click the Your Details link";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "YourDetailsPage28_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// 4.1 Log Off
		driver.findElement(By.xpath("//a[@href='/hostedmgr/secure/logout']")).click(); 				// Click the Log Off link
		ReportText = "31) Logged off";																// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Close the Browser and kill task (Postcondition)
    	driver.quit();																				// Close the Browser
		ReportText = "32) Closed Browser";															// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	}
	}