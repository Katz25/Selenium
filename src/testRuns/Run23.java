package testRuns;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import appModule.CreateCostCentre_Action;
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
public class Run23 
	{
    public static void main(String[] args) throws Exception 
    	{   	
    	// Launch Chrome (Precondition)
    	String exePath = "C:\\Selenium\\chromedriver.exe";										// Set Variable for path Where Chrome.exe is stored
    	System.setProperty("webdriver.chrome.driver", exePath);									// Set Path to open Chrome from
    	WebDriver driver = new ChromeDriver();													// Open a new instance of Chrome
		String ReportText = "1) Run23 Chrome Launched";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
    	
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet8");		// Look in the Excel sheet for test data
        
       	// Open the login website using the Url (Precondition)
    	driver.get(Constant.URL);																// Open the login website using the Url
		ReportText = "2) " + Constant.URL + " Website Opened";									// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
    			
		// 4.0 Login using the appModule Login_Action
		Login_Action.Execute(driver);															// Login using the appModule Login_Action
		
		// Test if Login occurred
		String whatiscurrenturl = driver.getCurrentUrl();										// Set variable to the current web address
		String testurl1 = "https://services-sandbox.hopewiser.com/hostedmgr/home";				// Set Variable to path
		String testurl2 = "https://services-sandbox.hopewiser.com/hostedmgr/logon?error=true";	// Set Variable to path
		if (whatiscurrenturl.contains(testurl1))												// Test if Home page exists
			{
			ReportText = "3) Login Passed: " + whatiscurrenturl;								// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "LoginPass23_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl.contains(testurl2))											// Test if logon page exists
				{			
				ReportText = "3) Login Failed: " + whatiscurrenturl;							// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginFail23_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			else
				{
				ReportText = "3) Unknown Error: " + whatiscurrenturl;							// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginError23_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			}
		
		// 5.0 Select the Edit Account Details button 
		//<a href="/hostedmgr/account/319/edit" class="ym-button ym-small" style="width:10em">
		try
			{
			driver.findElement(By.xpath("//a[@href='/hostedmgr/account/319/edit']")).click();	// Click the Edit Account Details button
			}
		catch (Exception e)
			{
			driver.findElement(By.xpath("//a[@class='ym-button ym-small']")).click();			// Click the Edit Account Details button
			}

		ReportText = "4) The Edit Account Details button is clicked";							// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		String SS = "EditAccountPage23_";														// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the Cost Centres tab
		//<a href="/hostedmgr/account/319/managecost">Cost Centres</a>
		driver.findElement(By.xpath("//a[contains(.,'Cost Centres')]")).click();				// Click the Cost Centres tab
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "CostCentresPage23_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the Create Cost Centre Button
		// <a class="ym-button ym-small" href="/hostedmgr/account/319/cost/create">Create&nbsp;Cost&nbsp;Centre</a>
		try
			{
			driver.findElement(By.xpath("//a[@href='/hostedmgr/account/319/cost/create']")).click(); // Click the Create Cost Centre button
			}
		catch (Exception e)
			{
			driver.findElement(By.xpath("//a[@class='ym-button ym-small']")).click();			// Click the Create Cost Centre button
			}

		ReportText = "5) The Create Cost Centre Button is clicked";								// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData1,"Sheet27");
    	
		// Create a Costcentre using the appModule CreateCostCentre_Action
    	CreateCostCentre_Action.Execute(driver);												// Create a Cost Centre using the appModule CreateCostCentre_Action

		// 5.1 Select the Cost Centre Rename button from the list
		//*[@id="main"]/div/div/section[2]/article/div/div[2]/table/tbody/tr[1]/td[2]/a[1]
		driver.findElement(By.xpath("//div/div/section[2]/article/div/div[2]/table/tbody/tr[1]/td[2]/a[1]")).click(); // Click the first Cost Centres Rename button from the table
		Timeloop.Execute(driver, "/rename");													// Ensure the correct page is displayed in a sensible time
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "RenameCostCentre23_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Enter a new cost centre name to prove a cost centre can be edited
		//*[@id="name"]
		String sCostCentre = ExcelUtils.getCellData(1, 3);										// Look in the Excel sheet for test data
		driver.findElement(By.xpath("//input[@id='name']")).clear();							// Clear the Cost centre Rename Editbox
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys(sCostCentre + " Edit");	// Enter a new cost centre name
		//<input type="submit" class="ym-button ym-danger" value="Save">
		try
			{
			driver.findElement(By.xpath("//input[@value='Save']")).click();						// Click the Save button
			}
		catch (Exception e)
			{
			driver.findElement(By.xpath("//input[@class='ym-button ym-danger']")).click();		// Click the Save button
			}
		
		// Test if edit occurred
		String whatiscurrenturlE = driver.getCurrentUrl();										// Set variable to the current web address
		String testurlE1 = "https://services-sandbox.hopewiser.com/hostedmgr/account/319/managecost"; // Set variable to path
		String testurlE2 = "https://services-sandbox.hopewiser.com/hostedmgr/account/319/cost/617/rename"; // Set variable to path
		if (whatiscurrenturlE.contains(testurlE1))												// Test if Account page exists
			{
			ReportText = "6) Edit Passed: " + whatiscurrenturlE;								// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "EditPass23";																	// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturlE.contains(testurlE2))											// Test if home page exists
				{	
				ReportText = "6) Edit Failed: " + whatiscurrenturlE;							// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "EditFail23";																// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			else
				{
				ReportText = "6) Unknown Error: " + whatiscurrenturlE;							// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "EditError23";																// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			}
		
		// 5.2 Select the Cost Centre Delete button from the list
		//*[@id="main"]/div/div/section[2]/article/div/div[2]/table/tbody/tr[1]/td[2]/a[2]
		driver.findElement(By.xpath("//div/div/section[2]/article/div/div[2]/table/tbody/tr[1]/td[2]/a[2]")).click(); // Click the first Cost Centre Delete button from the table
		Timeloop.Execute(driver, "/delete");													// Ensure the correct page is displayed in a sensible time
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "DeleteCostCentre23Edit_";															// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Delete the required Cost Centre
		// example: https://services-sandbox.hopewiser.com/hostedmgr/account/319/cost/618/delete
		String section = "DeleteCostCentre_";													// Identify the first delete button page
		ScreenShot_Action.Execute(driver, section);												// Delete Screenshot using appModule.ScreenShot_Action
		try
			{
			//<input type="submit" class="ym-button ym-danger" value="Delete">
			driver.findElement(By.xpath("//input[@value='Delete']")).click();					// Click the delete button
			}
		catch (Exception e)
			{
			driver.findElement(By.xpath("//input[@class='ym-button ym-danger']")).click();		// Click the delete button
			}
		Timeloop.Execute(driver, "/managecost");												// Ensure the correct page is displayed in a sensible time
	
		// Test if delete occurred
		String whatiscurrenturlD = driver.getCurrentUrl();										// Set variable to the current web address
		String testurlD1 = "https://services-sandbox.hopewiser.com/hostedmgr/account/319/managecost"; // Set variable to path (before & after delete pages are /acct/ pages
		if (whatiscurrenturlD.contains(testurlD1))												// Test if Account page exists
			{
			ReportText = "7) Delete Passed: " + whatiscurrenturlD;								// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "DeletePass23_";																// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
			try 
				{	
				driver.findElement(By.xpath("//span[contains(.,'Cost Centre Test23 Edit')]")).click(); // if Cost Centre Test23 Edit doesn't exist an exception is raised, Cost Centre Test should not exist
				}
			catch (Exception e)
				{
				ReportText = "8) Cost Centre Test is deleted";									// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				}
			ReportText = "9) if 8 is missing Cost Centre Test has not been deleted";			// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			}
		else
			{	
			ReportText = "7) Unknown Error: " + whatiscurrenturlD;								// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "DeleteError23_";																// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
			System.exit(1);																		// Drop out of script
			}
			    	
		// 12.2 Select the Return to Account Details
		// <a class="ym-button ym-small" href="/hostedmgr/home/319">Return&nbsp;to&nbsp;Account&nbsp;Details</a>	
		try
			{
			driver.findElement(By.xpath("//a[@href='/hostedmgr/home/319']")).click();			// Click the Return to Account Details button
			}
		catch (Exception e)
			{
			driver.findElement(By.xpath("//a[@class='ym-button ym-small']")).click();			// Click the Return to Account Details button
			}
		
		ReportText = "10) The Return to Account Details button is clicked";						// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/home/");		// Ensure the correct page is displayed in a sensible time
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountsPage23_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Get Click Usage current value, if value exists, can be found by clicking the View Usage button
		// <div style="text-align:center">View&nbsp;Usage</div> 								//*[@id="usageview"]/div
		driver.findElement(By.xpath("//a[@id='usageview']")).click();  							// Click the View Usage button
		Timeloop.Execute(driver, "/usage");														// Ensure the correct page is displayed in a sensible time
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "Usage23_";																		// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Set display to only display todays date this makes getting the text more reliable
        //<option value="Overview" selected="selected">Overview</option><option value="Detailed">Detailed</option> //<select id="planLevel" name="planLevel">
		driver.findElement(By.xpath("//select[@id='planLevel']")).sendKeys("Overview"); 		// Ensure the Information Level = Overview
        //<option value="Daily" selected="selected">Daily</option><option value="Monthly">Monthly</option> 	//<select id="planPeriod" name="planPeriod" size="1">
		driver.findElement(By.xpath("//select[@id='planPeriod']")).sendKeys("Daily"); 			// Ensure the Period = Daily
		LocalDate TodaysDate = LocalDate.now();													// The current date
 		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");				// Create object of SimpleDateFormat class and decide the format
 		String Date = dateFormat.format(TodaysDate);											// Now format the date
		ReportText = "11) Todays date " + Date;													// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
 		//<input name="planFrom" class="hasDatepicker" id="planFrom" type="date" value="2017-10-01">
		driver.findElement(By.xpath("//input[@id='planFrom']")).sendKeys(Date); 				// Enter todays date into the Change display information From edit box
		//<input id="planTo" name="planTo" type="date" value="2017-09-15">
		driver.findElement(By.xpath("//input[@id='planTo']")).sendKeys(Date); 					// Enter todays date into the Change display information To edit box
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "SetCDInfo23_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		//<input type="submit" class="ym-button ym-small" style="margin-top:1em;max-width:11em" value="Apply">
		driver.findElement(By.xpath("//input[@value='Apply']")).click();						// Click the Apply button		 																			
		Thread.sleep(3000);																		// Pause 3 seconds for page update
		SS = "CCUsageAccordion23_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// From the Usage Page, get Click Usage current value for comparison later
		// Open the costcentre Accordion
		// <a data-toggle="collapse" data-parent="#accordion" href="#collapse1" class="collapsed">Cost Centre: costcentre</a>
		driver.findElement(By.xpath("//a[contains(.,'Cost Centre: costcentre')]")).click();  	// Click the Cost Centre: costcentre header bar
		Thread.sleep(3000);																		// Pause 3 seconds for page update
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "CCUsageAccordion23_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Record the number of clicks from today
		//*[@id="plantab0"]/table/tbody/tr/td[2] 		//<td style="text-align:right">2</td>
		String Click_Usage1 = "0";																// Initiate Click_Usage1
		int Click_Usage_Num1 = 0;																// Initiate Click_Usage1
		try
			{
			WebElement td_CUsage = driver.findElement(By.xpath("//table/tbody/tr/td[2][@style='text-align:right']")); // Get the Click Usage value from the table
			Click_Usage1 = td_CUsage.getText();													// Get the text from this cell
			Click_Usage_Num1 = Integer.parseInt(Click_Usage1);									// Convert Click_Usage text value into a number
			ReportText = "12) Click Usage: " + Click_Usage1;									// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			}
		catch (Exception e)
			{
			Click_Usage_Num1 = Integer.parseInt(Click_Usage1);									// Convert Click_Usage text value into a number
			ReportText = "12) No clicks raised yet today";										// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			}
			
		// Select the Return to Account Details Button at the bottom of the Usage page
		SS = "LowerCreatedClicks23_";															// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
        Thread.sleep(5000);																   		// Pause 3 seconds for page update (Can have timing issues here)
		try
			{
			driver.findElement(By.xpath("//a[@href='/hostedmgr/home/319']")).click();			// Click the Return to Account Details button
			}
		catch (Exception e)
			{
			driver.findElement(By.xpath("//a[@class='ym-button ym-small']")).click();			// Click the Return to Account Details button
			}
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/home/");		// Ensure the correct page is displayed in a sensible time
		
		// From the Account Details Page, get Clicks Remaining current value for comparison later
		// Open the costcenter Accordion
		// <a data-toggle="collapse" data-parent="#accordion" href="#collapse1" class="collapsed">Cost Centre: costcentre</a>
		driver.findElement(By.xpath("//a[contains(.,'Cost Centre: costcentre')]")).click();  	// Click the Cost Centre: costcentre header bar
		Thread.sleep(3000);																		// Pause 3 seconds for page update
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "CCAccountAccordion23_";															// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Record the number of Remaining clicks
		//*[@id="collapse1"]/div/div/table[1]/tbody/tr[2]/td[4] //<td class="warnnone align-right">4282</td>
		WebElement td_text2 = driver.findElement(By.xpath("//div/div/table[1]/tbody/tr[2]/td[4][@class='warnnone align-right']")); // Get the value on second line of the table 4th item
		String Clicks_Remaining = td_text2.getText();											// Get the text from this cell
		int Click_Remaining_Num1 = Integer.parseInt(Clicks_Remaining);							// Convert Clicks_Remaining text value into a number
		ReportText = "13) Clicks Remaining: " + Clicks_Remaining;								// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// Launch Chrome (Precondition)
		WebDriver Mimic = new ChromeDriver();													// Open a new instance of Chrome
		ReportText = "14) Run23 Second Chrome Launched";										// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		    	
		// Use the Fast Address Mimic url  	
		String searchURL = "https://addrsvr2.hopewiser.com/address-search/";					// Set variable to path
		String password = Constant.Password;													// Set variable to password via the utility constant script
		Mimic.get(searchURL);																	// Open the login website using the Url
		Timeloop.Execute(Mimic, "https://addrsvr2.hopewiser.com/address-search/");				// Ensure the correct page is displayed in a sensible time
		ReportText = "15) " + searchURL + " Website Opened";									// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// 12.1 Sign In to the Fast Address Mimic
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet8");		// Look in the Excel sheet for test data
		String sName = ExcelUtils.getCellData(1, 1);											// Set variable to user name
		Mimic.findElement(By.xpath("//input[@id='username']")).sendKeys(sName); 				// Enter the User Name
		Mimic.findElement(By.xpath("//input[@id='password']")).sendKeys(password); 				// Enter the Password
		Thread.sleep(3000);																		// Pause 3 seconds for page update
		    	
		Mimic.findElement(By.xpath("//button[contains(.,'Sign In')]")).click();  				// Click Sign In button
		Thread.sleep(3000);																		// Pause 3 seconds for page update
		ReportText = "16) Signed In as " + sName;												// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		    	
		// Set Dataset field
		//<select id="dataset" onchange="dataset_onchange()"><option value="">Choose a Dataset...</option><option value="uk-rm-paf-external">uk-rm-paf-external</option><option value="uk-rm-paf-internal">uk-rm-paf-internal</option></select>
		new Select(Mimic.findElement(By.xpath("//select[@id='dataset']"))).selectByVisibleText("uk-rm-paf-internal"); // Select PAF dataset from the dropdown
		Thread.sleep(3000);																		// Pause 3 seconds for page update
		    	
		// Search for a address with house number and postcode
		Mimic.findElement(By.xpath("//input[@id='input']")).sendKeys("6,m337ug");  				// Enter the Premises number and the postcode
		Thread.sleep(3000);																		// Pause 3 seconds for page update
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AddressSearchPage23_";															// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(Mimic, SS);													// Create Screenshot using appModule.ScreenShot_Action
				
		// Find the address
		Mimic.findElement(By.xpath("//button[contains(.,'Find Address')]")).click(); 			// Click Find Address button
		Thread.sleep(3000);																		// Pause 3 seconds for page update
		    	
		// Select the address
		Mimic.findElement(By.xpath("//button[contains(.,'Select')]")).click(); 					// Click Select button
		Thread.sleep(3000);																		// Pause 3 seconds for page update
		ReportText = "17) Address is selected";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "Select23_";																		// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		    	
		// Close the Browser and kill task (Postcondition)
		Mimic.quit();																			// Close the Browser
		ReportText = "18) Closed Second Browser";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
        Thread.sleep(5000);																   		// Pause 3 seconds for page update (Can have timing issues here)
		
		// New Test section missing from the manual tests to refresh the page
		//<a href="/hostedmgr/account/319/cost/549/user/1893/details">Your&nbsp;Details</a>  	//*[@id="t-yourdetails"]/a
        try
			{
        	driver.findElement(By.partialLinkText("Details")).click();  						// Click the Your Details link
			}
        catch (Exception e)
        	{
        	driver.findElement(By.xpath("//*[@id='t-yourdetails']")).click();  					// Click the Your Details link
        	}
		driver.findElement(By.xpath("//div/div/section[2]/article/div/div/div[2]/div/a[4]/div")).click(); // Click the Your Account button
		
		// Select View Usage button
		driver.findElement(By.xpath("//a[@id='usageview']")).click();  							// Click the View Usage button
		Timeloop.Execute(driver, "/usage?");													// Ensure the correct page is displayed in a sensible time
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "Usage23_";																		// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Set display to only display todays date this makes getting the text more reliable
        //<option value="Overview" selected="selected">Overview</option><option value="Detailed">Detailed</option> //<select id="planLevel" name="planLevel">
		driver.findElement(By.xpath("//select[@id='planLevel']")).sendKeys("Overview"); 		// Ensure the Information Level = Overview
        //<option value="Daily" selected="selected">Daily</option><option value="Monthly">Monthly</option> //<select id="planPeriod" name="planPeriod" size="1">
		driver.findElement(By.xpath("//select[@id='planPeriod']")).sendKeys("Daily"); 			// Ensure the Period = Daily
 		//<input name="planFrom" class="hasDatepicker" id="planFrom" type="date" value="2017-10-01">
		driver.findElement(By.xpath("//input[@id='planFrom']")).sendKeys(Date); 				// Enter todays date into the Change display information From edit box
		//<input id="planTo" name="planTo" type="date" value="2017-09-15">
		driver.findElement(By.xpath("//input[@id='planTo']")).sendKeys(Date); 					// Enter todays date into the Change display information To edit box
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "SetCDInfo23_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		//<input type="submit" class="ym-button ym-small" style="margin-top:1em;max-width:11em" value="Apply">
		driver.findElement(By.xpath("//input[@value='Apply']")).click(); 						// Click the Apply button
		Thread.sleep(3000);																		// Pause 3 seconds for page update
			
		// Record the number of clicks from today
		//*[@id="plantab0"]/table/tbody/tr/td[2] 												//<td style="text-align:right">2</td>
		WebElement td_CUsage2 = driver.findElement(By.xpath("//table/tbody/tr/td[2][@style='text-align:right']")); // Get the Click Usage value from the table
		String Click_Usage2 = td_CUsage2.getText();												// Get the text from this cell
		int Click_Usage_Num2 = Integer.parseInt(Click_Usage2);									// Convert Click_Usage text value into a number
		ReportText = "19) Clicks Used Today: " + Click_Usage2;									// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			
		// Select the Return to Account Details Button at the bottom of the Usage page
		// <a class="ym-button ym-small" href="/hostedmgr/home/319">Return&nbsp;to&nbsp;Account&nbsp;Details</a>
		try
			{
			driver.findElement(By.xpath("//a[@href='/hostedmgr/home/319']")).click();			// Click the Return to Account Details button																						
			}
		catch (Exception e)
			{
			driver.findElement(By.xpath("//a[@class='ym-button ym-small']")).click();			// Click the Return to Account Details button																						// Click the Return to Account Details button
			}
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/home/");		// FF Ensure the correct page is displayed in a sensible time
		
		// Open the costcenter Accordion on the Account Details Page
		// <a data-toggle="collapse" data-parent="#accordion" href="#collapse1" class="collapsed">Cost Centre: costcentre</a>
		SS = "ReturntoAccount23_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		driver.findElement(By.xpath("//a[contains(.,'Cost Centre: costcentre')]")).click();  	// Click the Cost Centre: costcentre header bar
		Thread.sleep(3000);																		// Pause 3 seconds for page update
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "CCAccount2Accordion23_";															// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Get Clicks Remaining current value for comparison later
		//*[@id="collapse1"]/div/div/table[1]/tbody/tr[2]/td[4]
		WebElement td_text4 = driver.findElement(By.xpath("//div/div/table[1]/tbody/tr[2]/td[4][@class='warnnone align-right']")); // Get to second line of the table 4th item
		String Clicks_Remaining2 = td_text4.getText();											// Get the text from this cell
		int Click_Remaining_Num2 = Integer.parseInt(Clicks_Remaining2);							// Convert Clicks_Remaining text value into a number
		ReportText = "20) Clicks Remaining: " + Clicks_Remaining2;								// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		String whatiscurrenturl4 = driver.getCurrentUrl();										// Put current website address in variable.	
				
		// Test if click was added
		if (Click_Usage_Num1<Click_Usage_Num2)													// Test if Home page exists
			{
			ReportText = "21) ClicksIncreasedPassed: " + whatiscurrenturl4;						// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "ClicksReducedPass23_";														// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			ReportText = "21) ClicksIncreasedFailed: " + whatiscurrenturl4;						// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "ClicksReducedFail23_";														// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
			System.exit(1);																		// Drop out of script
			}
		
		int result1 = (Click_Usage_Num2-Click_Usage_Num1);										// Calculate clicks used
		ReportText = "22) Clicks used: " + result1;												// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
				
		// Test if click was removed
		if (Click_Remaining_Num1>Click_Remaining_Num2)											// Test if Home page exists
			{
			ReportText = "23) ClicksIncreasedPassed: " + whatiscurrenturl4;						// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "ClicksReducedPass23_";														// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			ReportText = "23) ClicksIncreasedFailed: " + whatiscurrenturl4;						// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "ClicksReducedFail23_";														// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
			System.exit(1);																		// Drop out of script
			}
		
		int result2 = (Click_Remaining_Num2-Click_Remaining_Num1);								// Calculate clicks used
		ReportText = "24) Clicks Remaining Reduced by: " + result2;								// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// 4.1 Log Off
		// <a href="/hostedmgr/secure/logout">Log&nbsp;Off</a>
		driver.findElement(By.xpath("//a[@href='/hostedmgr/secure/logout']")).click(); 			// Click the Log Off link
		ReportText = "25) Logged off";															// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// Close the Browser and kill task (Postcondition)
    	driver.quit();																			// Close the Browser
		ReportText = "26) Closed Browser";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console  	
		}
	}