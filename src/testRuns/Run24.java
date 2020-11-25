package testRuns;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
public class Run24 
	{
    public static void main(String[] args) throws Exception 
    	{   	
    	// Launch Chrome (Precondition)
    	String exePath = "C:\\Selenium\\chromedriver.exe";											// Set Variable for path Where Chrome.exe is stored
    	System.setProperty("webdriver.chrome.driver", exePath);										// Set Path to open Chrome from
    	WebDriver driver = new ChromeDriver();														// Open a new instance of Chrome
		String ReportText = "1) Run24 Chrome Launched";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
        
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet9");			// Look in the Excel sheet for test data
        
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
			String SS = "LoginPass24_";																// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl.contains(testurl2))												// Test if logon page exists
				{
				ReportText = "3) Login Failed: " + whatiscurrenturl;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginFail24_";															// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			else
				{
				ReportText = "3) Unknown Error: " + whatiscurrenturl;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginError24_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}
		
		// 5.1 (completely changed from manual test) Check if the Edit Account Details button is disabled. This means a Cost Centre Admin can't rename its cost centre (this functionality has changed).
		//<a href="/hostedmgr/account/319/edit" class="ym-button ym-small" style="width:10em">
		WebElement RTADButton = driver.findElement(By.xpath("//a[@class='ym-button ym-small']"));	// Identify the Return to Account Details button
		Boolean isRTADDisabled = RTADButton.isEnabled();											// Check the Return to Account Details button
		if (isRTADDisabled == true)
			{
			ReportText = "4) The Return to Account Details button is disabled";						// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			}
		else
			{
			ReportText = "4) The Return to Account Details button is enabled";						// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			}
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		String SS = "AccountsPage24_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action

		// Get Click Usage current value, if value exists, can be found by clicking the View Usage button
		// <div style="text-align:center">View&nbsp;Usage</div> 									//*[@id="usageview"]/div
		driver.findElement(By.xpath("//a[@id='usageview']")).click();								// Click the View Usage button
		Timeloop.Execute(driver, "/usage?");														// Ensure the correct page is displayed in a sensible time
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "PUsage24_";																			// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Open Cost Centre: costcentre Accordion
		//<a data-toggle="collapse" data-parent="#accordion" href="#collapse1" class="collapsed">Cost Centre: costcentre</a>
		//driver.findElement(By.xpath("//a[contains(.,'Cost Centre: costcentre')]")).click();		// Click the Cost Centre: costcentre heading
		//Thread.sleep(3000);																		// Pause 3 seconds for page update
		
		// View User Usage Tab
		//<a href="#accessibletabscontent0-1">User Usage</a>
		driver.findElement(By.xpath("//a[contains(.,'User Usage')]")).click();						// Click the User Usage tab
		Timeloop.Execute(driver, "/usage?");														// Ensure the correct page is displayed in a sensible time
		SS = "UUsage24_";																			// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		//<a href="#accessibletabscontent0-0">Plan Usage</a>
		driver.findElement(By.xpath("//a[contains(.,'Plan Usage')]")).click();						// Click to return to the Plan Usage tab
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		
		// Set display to only display todays date this makes getting the text more reliable
        //<option value="Overview" selected="selected">Overview</option><option value="Detailed">Detailed</option> //<select id="planLevel" name="planLevel">
		driver.findElement(By.xpath("//select[@id='planLevel']")).sendKeys("Overview"); 			// Ensure the Information Level = Overview
        //<option value="Daily" selected="selected">Daily</option><option value="Monthly">Monthly</option> //<select id="planPeriod" name="planPeriod" size="1">
		driver.findElement(By.xpath("//select[@id='planPeriod']")).sendKeys("Daily"); 				// Ensure the Period = Daily
		LocalDate TodaysDate = LocalDate.now();														// The current date
 		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");					// Create object of SimpleDateFormat class and decide the format 
 		String Date = dateFormat.format(TodaysDate);												// Now format the date
 		//<input name="planFrom" class="hasDatepicker" id="planFrom" type="date" value="2017-10-01">
		driver.findElement(By.xpath("//input[@id='planFrom']")).sendKeys(Date); 					// Enter todays date into the Change display information From edit box
		//<input id="planTo" name="planTo" type="date" value="2017-09-15">
		driver.findElement(By.xpath("//input[@id='planTo']")).sendKeys(Date); 						// Enter todays date into the Change display information To edit box
		ReportText = "5) Todays date set: "+ Date;													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "SetCDInfo24_";																		// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		//<input type="submit" class="ym-button ym-small" style="margin-top:1em;max-width:11em" value="Apply">
		driver.findElement(By.xpath("//input[@value='Apply']")).click();							
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		SS = "CCUsageAccordion24_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
				
		// Record the number of clicks from today
		//*[@id="plantab0"]/table/tbody/tr/td[2] 		//<td style="text-align:right">2</td>
		int Click_Usage_Num1 = 0;																	// Initialise Click_usage_Num1 to 0
		try
			{
			WebElement td_CUsage = driver.findElement(By.xpath("//table/tbody/tr/td[2][@style='text-align:right']")); // Get the Click Usage value from the table
			String Click_Usage1 = td_CUsage.getText();												// Get the text from this cell
			Click_Usage_Num1 = Integer.parseInt(Click_Usage1);										// Convert Clicks_Usage text value into a number
			ReportText = "6) Click Usage: " + Click_Usage1;											// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			}
		catch (Exception e)
			{
			ReportText = "6) No clicks raised yet today";											// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			}
			
		// Select the Return to Account Details Button at the bottom of the Usage page   
		try
			{
			driver.findElement(By.xpath("//a[@href='/hostedmgr/home/319']")).click();				// Click the Return to Account Details button
			}
		catch (Exception e)
			{
			driver.findElement(By.xpath("//a[@class='ym-button ym-small']")).click();				// Setup button variable
			}
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/home/");			// Ensure the correct page is displayed in a sensible time
		ReportText = "7) Back to Account Details";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Open Cost Centre: costcentre Accordion
		//<a data-toggle="collapse" data-parent="#accordion" href="#collapse1" class="collapsed">Cost Centre: costcentre</a>
		//driver.findElement(By.xpath("//a[contains(.,'Cost Centre: costcentre')]")).click();		// Click the User Usage tab
		//Thread.sleep(3000);																		// Pause 3 seconds for page update
		
		// Record the number of Remaining clicks
		//*[@id="collapse1"]/div/div/table[1]/tbody/tr[2]/td[4] //<td class="warnnone align-right">4282</td>
		WebElement td_text2 = driver.findElement(By.xpath("//div/div/table[1]/tbody/tr[2]/td[4][@class='warnnone align-right']"));	// Get the value on second line of the table 4th item
		String Clicks_Remaining = td_text2.getText();												// get the text from this cell
		int Click_Remaining_Num1 = Integer.parseInt(Clicks_Remaining);								// convert Clicks_Remaining text value into a number
		ReportText = "8) Clicks Remaining: " + Clicks_Remaining;									// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Launch Chrome (Precondition)
		WebDriver Mimic = new ChromeDriver();														// Open a new instance of Chrome
		ReportText = "9) Run24 Second Chrome Launched";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		    	
		// Use the Fast Address Mimic url  	
		String searchURL = "https://addrsvr2.hopewiser.com/address-search/";						// Set variable to path
		String password = Constant.Password;														// Set variable to password via the utility constant script
		Mimic.get(searchURL);																		// Open the login website using the Url
		Timeloop.Execute(Mimic, "https://addrsvr2.hopewiser.com/address-search/");					// Ensure the correct page is displayed in a sensible time
		ReportText = "10) " + searchURL + " Website Opened";										// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// 12.1 Sign In to the Fast Address Mimic
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet9");			// Look in the Excel sheet for test data
		String sName = ExcelUtils.getCellData(1, 1);												// Set variable to user name
		Mimic.findElement(By.xpath("//input[@id='username']")).sendKeys(sName); 					// Enter the User Name
		Mimic.findElement(By.xpath("//input[@id='password']")).sendKeys(password); 					// Enter the Password
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		    	
		Mimic.findElement(By.xpath("//button[contains(.,'Sign In')]")).click();  					// Click Sign In button
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		ReportText = "11) Signed In as " + sName;													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		    	
		// Set Dataset field
		//<select id="dataset" onchange="dataset_onchange()"><option value="">Choose a Dataset...</option><option value="uk-rm-paf-external">uk-rm-paf-external</option><option value="uk-rm-paf-internal">uk-rm-paf-internal</option></select>
		new Select(Mimic.findElement(By.xpath("//select[@id='dataset']"))).selectByVisibleText("uk-rm-paf-internal");   // Select PAF dataset from the dropdown
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		    	
		// Search for a address with house number and postcode
		Mimic.findElement(By.xpath("//input[@id='input']")).sendKeys("6,m337ug");  					// Enter the Premises number and the postcode
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AddressSearchPage24_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(Mimic, SS);														// Create Screenshot using appModule.ScreenShot_Action
				
		// Find the address
		Mimic.findElement(By.xpath("//button[contains(.,'Find Address')]")).click(); 				// Click Find Address button
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		    	
		// Select the address
		Mimic.findElement(By.xpath("//button[contains(.,'Select')]")).click(); 						// Click Select button
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		ReportText = "12) Address is selected";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "Select24_";																			// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		    	
		// Close the Browser and kill task (Postcondition)
		Mimic.quit();																				// Close the Browser
		ReportText = "13) Closed Second Browser";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
				
		// New Test section missing from the manual tests to refresh the page
		//<a href="/hostedmgr/account/319/cost/549/user/1727/details">Your&nbsp;Details</a>
		driver.findElement(By.partialLinkText("Details")).click(); 									// Click the Your Details link
		driver.findElement(By.xpath("//div/div/section[2]/article/div/div/div[2]/div/a[4]/div")).click(); // Click the Your Account button
		    	  	
		// Get Click Usage current value, if value exists, can be found by clicking the View Usage button
		// <div style="text-align:center">View&nbsp;Usage</div> 									//*[@id="usageview"]/div
		driver.findElement(By.xpath("//a[@id='usageview']")).click();								// Click the View Usage button
		Timeloop.Execute(driver, "/usage?");														// Ensure the correct page is displayed in a sensible time
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "PUsage24_";																			// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// View User Usage Tab
		//<a href="#accessibletabscontent0-1">User Usage</a>
		driver.findElement(By.xpath("//a[contains(.,'User Usage')]")).click();						// Click the User Usage tab
		SS = "UUsage24_";																			// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		//<a href="#accessibletabscontent0-0"><span class="current-info">current tab: </span>Plan Usage</a>
		driver.findElement(By.xpath("//a[contains(.,'Plan Usage')]")).click();						// Click to return to the Plan Usage tab
		
		// Set display to only display todays date this makes getting the text more reliable
        //<option value="Overview" selected="selected">Overview</option><option value="Detailed">Detailed</option> //<select id="planLevel" name="planLevel">
		driver.findElement(By.xpath("//select[@id='planLevel']")).sendKeys("Overview"); 			// Ensure the Information Level = Overview
        //<option value="Daily" selected="selected">Daily</option><option value="Monthly">Monthly</option> //<select id="planPeriod" name="planPeriod" size="1">
		driver.findElement(By.xpath("//select[@id='planPeriod']")).sendKeys("Daily"); 				// Ensure the Period = Daily
 		//<input name="planFrom" class="hasDatepicker" id="planFrom" type="date" value="2017-10-01">
		driver.findElement(By.xpath("//input[@id='planFrom']")).sendKeys(Date); 					// Enter todays date into the Change display information From edit box
		//<input id="planTo" name="planTo" type="date" value="2017-09-15">
		driver.findElement(By.xpath("//input[@id='planTo']")).sendKeys(Date); 						// Enter todays date into the Change display information To edit box
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "SetCDInfo24_";																		// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		//<input type="submit" class="ym-button ym-small" style="margin-top:1em;max-width:11em" value="Apply">
		driver.findElement(By.xpath("//input[@value='Apply']")).click();							// Click the Apply button
		Thread.sleep(3000);																			// Pause 3 seconds for page update
			
		// Record the number of clicks from today
		//*[@id="plantab0"]/table/tbody/tr/td[2] 		//<td style="text-align:right">2</td>
		WebElement td_CUsage2 = driver.findElement(By.xpath("//table/tbody/tr/td[2][@style='text-align:right']")); // Get the Click Usage value from the table
		String Click_Usage2 = td_CUsage2.getText();													// Get the text from this cell
		int Click_Usage_Num2 = Integer.parseInt(Click_Usage2);										// Convert Clicks_Remaining text value into a number
		ReportText = "14) Clicks Used Today: " + Click_Usage2;										// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
			
		// Select the Return to Account Details Button at the bottom of the Usage page
		try
			{
			driver.findElement(By.xpath("//a[@href='/hostedmgr/home/319']")).click();				// Click the Return to Account Details button
			}
		catch (Exception e)
			{
			driver.findElement(By.xpath("//a[@class='ym-button ym-small']")).click();				// Setup button variable
			}
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/home/");			// Ensure the correct page is displayed in a sensible time
		ReportText = "15) Back to Account Details";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Open Cost Centre: costcentre Accordion
		//<a data-toggle="collapse" data-parent="#accordion" href="#collapse1" class="collapsed">Cost Centre: costcentre</a>
		//driver.findElement(By.xpath("//a[contains(.,'Cost Centre: costcentre')]")).click();		// Click the User Usage tab
		//Thread.sleep(3000);																		// Pause 3 seconds for page update
		
		// Get Clicks Remaining current value for comparison later
		//*[@id="collapse1"]/div/div/table[1]/tbody/tr[2]/td[4]
		WebElement td_text4 = driver.findElement(By.xpath("//div/div/table[1]/tbody/tr[2]/td[4][@class='warnnone align-right']")); // Get to second line of the table 4th item
		String Clicks_Remaining2 = td_text4.getText();												// Get the text from this cell
		int Click_Remaining_Num2 = Integer.parseInt(Clicks_Remaining2);								// Convert Clicks_Remaining text value into a number			
		ReportText = "16) Clicks Remaining: " + Clicks_Remaining2;									// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		String whatiscurrenturl4 = driver.getCurrentUrl();											// Put current website address in variable.	
				
		// Test if click was added
		if (Click_Usage_Num1<Click_Usage_Num2)														// Test if Home page exists
			{
			ReportText = "17) ClicksIncreasedPassed: " + whatiscurrenturl4;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "ClicksReducedPass24_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			ReportText = "17) ClicksIncreasedFailed: " + whatiscurrenturl4;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "ClicksReducedFail24_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			System.exit(1);																			// Drop out of script
			}
		
		int result1 = (Click_Usage_Num2-Click_Usage_Num1);											// Calculate clicks used
		ReportText = "18) Clicks used: " + result1;													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Test if click was removed
		if (Click_Remaining_Num1>Click_Remaining_Num2)												// Test if Home page exists
			{
			ReportText = "19) ClicksIncreasedPassed: " + whatiscurrenturl4;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "ClicksReducedPass24_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			ReportText = "19) ClicksIncreasedFailed: " + whatiscurrenturl4;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "ClicksReducedFail24_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			System.exit(1);																			// Drop out of script
			}
		
		int result2 = (Click_Remaining_Num2-Click_Remaining_Num1);									// Calculate clicks used
		ReportText = "20) Clicks used: " + result2;													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// 4.1 Log Off
		driver.findElement(By.xpath("//a[@href='/hostedmgr/secure/logout']")).click(); 				// Click the Log Off link
		ReportText = "21) Logged off";																// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	
		// Close the Browser and kill task (Postcondition)
    	driver.quit();																				// Close the Browser
		ReportText = "22) Closed Browser";															// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	}
	}