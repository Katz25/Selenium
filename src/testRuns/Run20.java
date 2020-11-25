package testRuns;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
public class Run20 
	{
    public static void main(String[] args) throws Exception 
    	{   	
		// Launch Chrome (Precondition)
		String exePath = "C:\\Selenium\\chromedriver.exe";											// Set variable to path where Chrome.exe is stored
		System.setProperty("webdriver.chrome.driver", exePath);										// Set Path to open Chrome from
		WebDriver driver = new ChromeDriver();														// Open a new instance of Chrome
    	String ReportText = "1) Run20 Chrome Launched";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
        
		// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet7");
		
		// Open the login website using the Url (Precondition)
		driver.get(Constant.URL);																	// Open the login website using the Url
    	ReportText = "2) " + Constant.URL + " Website Opened";										// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// 4.0 Login using the appModule Login_Action
		Login_Action.Execute(driver);																// Login using the appModule Login_Action = 3)
		
		// Test if Login occurred
		String whatiscurrenturl = driver.getCurrentUrl();											// Set variable to the current web address
		String testurl1 = "https://services-sandbox.hopewiser.com/hostedmgr/home";					// Set Variable to path
		String testurl2 = "https://services-sandbox.hopewiser.com/hostedmgr/logon?error=true";		// Set Variable to path
		if (whatiscurrenturl.contains(testurl1))													// Test if Home page exists
			{
	    	ReportText = "3) Login Passed: " + whatiscurrenturl;									// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "LoginPass20_";																// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl.contains(testurl2))												// Test if logon page exists
				{
		    	ReportText = "3) Login Failed: " + whatiscurrenturl;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginFail20_";															// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			else
				{
		    	ReportText = "3) Unknown Error: " + whatiscurrenturl;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginError20_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}
		
    	// 6.2 Click on the Shop link
		// <a href="/hostedmgr/shop">Shop</a>
    	driver.findElement(By.xpath("//a[@href='/hostedmgr/shop']")).click();						// Click the Shop Link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/shop");			// Ensure the correct page is displayed in a sensible time
    	
    	// Click the UK Postal Address File
    	//<a href="/hostedmgr/shop/plans/3">UK Postal Address File</a>
    	driver.findElement(By.xpath("//a[@href='/hostedmgr/shop/plans/3']")).click();				// Select the PAF dataset Link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/shop/plans/3");	// Ensure the correct page is displayed in a sensible time  	
    	
    	// Select a cost centre from the drop down
    	new Select(driver.findElement(By.id("costid0"))).selectByVisibleText("Hopewiser Test - costcentre");// Select Cost Centre from the dropdown
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
    	
    	// Select a 500 clicks from the drop down
    	new Select(driver.findElement(By.id("vbundleList0.bundle.clicks"))).selectByVisibleText("500");// Select Number of clicks from the dropdown
    	Thread.sleep(3000);																			// Pause 3 seconds for page update 
    	
    	// Select a cost centre from the drop down
    	new Select(driver.findElement(By.id("costid1"))).selectByVisibleText("Hopewiser Test - costcentre");// Select Cost Centre from the dropdown
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
    	
    	// Select a 500 clicks from the drop down
    	new Select(driver.findElement(By.id("vbundleList1.bundle.clicks"))).selectByVisibleText("500");// Select Number of clicks from the dropdown
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
    	
		String whatiscurrenturl3 = driver.getCurrentUrl();											// Set variable to the current web address
    	ReportText = "4) This page is: " + whatiscurrenturl3;										// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		String SS = "CreatedClicks20_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
    	
    	// Add to basket  	
        driver.findElement(By.tagName("body")).sendKeys(Keys.END);									// Scroll to the end of the page
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "LowerCreatedClicks14_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
        Thread.sleep(5000);																   			// Pause 5 seconds for page update (Can have timing issues here)
		try
			{
	    	driver.findElement(By.xpath("//input[@class='ym-button ym-danger']")).click(); 			// Click Add to Basket Button
			}
		catch (Exception e)
			{
			driver.findElement(By.xpath("//input[@type='submit']")).click(); 						// Click Add to Basket Button
			}
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/shop/basket");	// Ensure the correct page is displayed in a sensible time (Can have timing issues here)
    	ReportText = "5) Add to basket button clicked";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	
    	// Checkout  
    	//<a href="/hostedmgr/shop/payment" class="ym-button ym-primary">Check&nbsp;Out</a>
		try
			{
			driver.findElement(By.xpath("//a[contains(.,'Out')]")).click();							// Click the Check Out button on https://services-sandbox.hopewiser.com/hostedmgr/shop/basket
        	ReportText = "6.0) Check Out button clicked";											// Set up report text variable
    		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
    		SS = "Checkout20_";																		// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		catch (Exception e)
			{
			driver.findElement(By.xpath("//a[@href='/hostedmgr/shop/payment']")).click();			// Click the Check Out button on https://services-sandbox.hopewiser.com/hostedmgr/shop/basket
        	ReportText = "6.1) Check Out button clicked";											// Set up report text variable
    		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
    		}
		
		Timeloop.Execute(driver, "https://uk-sandbox.hopewiser.com/payment/order/");				// Ensure the correct page is displayed in a sensible time
    	whatiscurrenturl = driver.getCurrentUrl();													// Set variable to the current web address
    	ReportText = "7) Returns to Your Basket: " + whatiscurrenturl;								// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "YourBasket20_";																		// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
    	// Change Name to QA Run 20 on Payment Requested page
    	//<input id="contactName" name="contactName" type="text" value="QA Run 13.3">
    	driver.findElement(By.xpath("//input[@id='contactName']")).clear();							// Clear the Name field on https://services-sandbox.hopewiser.com/payment/order/**
    	driver.findElement(By.xpath("//input[@id='contactName']")).sendKeys("QA Run 20");			// Edit the name from QA Run 13.3 to QA Run 20
    	
    	// Fill in PO Number
    	//<input id="po" name="po" type="text" value="">
    	driver.findElement(By.xpath("//input[@id='po']")).clear();									// Clear the PO Number field
    	driver.findElement(By.xpath("//input[@id='po']")).sendKeys("12345");						// Enter 12345 as the PO Number = Purchase order Number

    	// Fill in Your Ref
    	//<input id="ref" name="ref" type="text" value="">
    	driver.findElement(By.xpath("//input[@id='ref']")).clear();									// Clear the Your Ref field
    	driver.findElement(By.xpath("//input[@id='ref']")).sendKeys("54321");						// Enter 54321 as the Your Ref = Purchase order Number
    	
    	// Fill in address
    	driver.findElement(By.xpath("//textarea[@cols='36']")).clear();  							// Clear the address field
    	driver.findElement(By.xpath("//textarea[@cols='36']")).sendKeys("Merlin court, Atlantic street, Altrincham");  // Enter the address into the field
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	
    	// Fill in Postcode
    	driver.findElement(By.xpath("//input[@id='postcode']")).clear(); 							// Clear the postcode field
    	driver.findElement(By.xpath("//input[@id='postcode']")).sendKeys("WA14 5NL"); 				// Enter the postcode WA14 5NL into the field
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	
    	// Open Find Postcode dialog.
    	//<a href="javascript:void(0)" id="FindAddr" style="display: inline-block;" class="jsLink" onclick="searchShow();">Find</a>
    	driver.findElement(By.partialLinkText("Find")).click();  									// Click the Find link
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	
    	//<select id="searchresult" size="5" style="">
    	driver.findElement(By.xpath("//select[@id='searchresult']")).sendKeys("H"+ Keys.RETURN); 	// Select Hopewiser Ltd from the list    	  	
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
    	SS = "ProceedtoPayment20_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
    	// Proceed to Payment 
		driver.findElement(By.xpath("//input[@value='Proceed to Payment']")).click();  				// Click the Proceed button
		Timeloop.Execute(driver, "https://uk-sandbox.hopewiser.com/payment/order/");				// Ensure the correct page is displayed in a sensible time
    	
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
    	SS = "TestWithWorldpay20_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
       	// Test with WorldPay
		driver.findElement(By.xpath("//input[@value='Test with WorldPay']")).click(); 				// Click the Test with WorldPay button
		Timeloop.Execute(driver, "https://secure-test.worldpay.com/wcc/purchase");					// FF Ensure the correct page is displayed in a sensible time
    	ReportText = "8) Worldpay Test Mode Page Opened";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "WorldpayTestModePage20_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		// 6.2 Finished, Worldpay uses an I'm not a robot feature so using Mimic instead
		   	
    	// 6.9 The World Pay Payment details ensure U: is accessible   	
		driver.get("file:///U:/mflook/frufai/ASiC%20Regression%20Pack/payment-resp.htm"); 			// Open the Worldpay File
		Timeloop.Execute(driver, "file:///U:/mflook/frufai/ASiC%20Regression%20Pack/payment-resp.htm"); // Ensure the correct page is displayed in a sensible time a sensible time
    	ReportText = "9) Address-Search page opened";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "WorldpayCallbackResponse20_";															// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Fill in the Mimic details
		// Select Transaction Status
		new Select(driver.findElement(By.name("transStatus"))).selectByVisibleText("Yes");			// Click the Yes drop down option
		
    	// Cart ID
		driver.findElement(By.xpath("//input[@id='cartId']")).clear(); 								// Clear the edit box	
    	driver.findElement(By.xpath("//input[@id='cartId']")).sendKeys("942ec268-bb79-4d42-8251-5de905e52a31"); // Enter a valid Cart Id
    	
    	// Transaction ID
    	driver.findElement(By.xpath("//input[@id='transId']")).clear(); 							// Clear the edit box	
    	driver.findElement(By.xpath("//input[@id='transId']")).sendKeys("3070771749");  			// Enter a valid Transaction Id
    	
    	// Select Test Mode
    	new Select(driver.findElement(By.name("testMode"))).selectByVisibleText("Test"); 			// Select the Test Mode drop down option
    	
    	// Select MC Redirect URL
    	new Select(driver.findElement(By.name("MC_redirectUrl"))).selectByVisibleText("Complete"); 	// Select Complete from the MC Redirect URL drop down
    	
    	ReportText = "10) Submit Worldpay Callback Response";										// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "SubmitWorldpayCallbackResponse 20_";													// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		//<input type="submit" class="submit" value="Submit">
		driver.findElement(By.xpath("//input[@class='submit']")).click(); 							// Click the Submit button
		//<a href="">here</a>
		driver.findElement(By.xpath("//a[contains(.,'here')]")).click(); 							// Click the here link
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
    	SS = "SubmitComplete20_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
				
		// 12.2 Select the Your Account button 
		// <a href="/hostedmgr/" class="ym-button">Your Account</a>
		driver.findElement(By.xpath("//a[@href='/hostedmgr/']")).click(); 							// Click the Your Account button
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/home");			// Ensure the correct page is displayed in a sensible time
    	ReportText = "11) Click the Your Account Button";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountDetails20_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
    	
    	// Select the left hand Accounts link 
    	// <a href="/hostedmgr/acct">Accounts</a>	
    	driver.findElement(By.xpath("//a[@href='/hostedmgr/acct']")).click();						// Click the Your Account link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/acct");			// Ensure the correct page is displayed in a sensible time
    	ReportText = "12) Click the Account link";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountsPage20_";																		// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
	
		// Search for Hopewiser Test
		// <input id="textfilteraccount-table" type="text" title="Searches each table cell. Supports multi-character (*) and single character (?) wildcards">				
		driver.findElement(By.xpath("//input[@id='textfilteraccount-table']")).sendKeys("Hopewiser Test");  // Enter the user name
		Thread.sleep(3000);															   				// Pause 3 seconds for page update
    	ReportText = "13) Searched for Hopewiser Test";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Select Hopewiser Test link from the list					
		driver.findElement(By.xpath("//span[contains(.,'Hopewiser Test')]")).click();				// Click the user
		Timeloop.Execute(driver, "/details");														// Ensure the correct page is displayed in a sensible time
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountDetailsPage20_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the Usage Tab
    	driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[2]/a")).click(); // Click Usage tab
		Timeloop.Execute(driver, "/usage");															// Ensure the correct page is displayed in a sensible time
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountUsagepage20_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Get Click Usage current value for comparison later
		WebElement td_text1 = driver.findElement(By.xpath("//tbody/tr[2]/td[4]"));					// Get to second line of the table 4th item
		String Click_Usage = td_text1.getText();													// Get the text from this cell
		int Click_Usage_Num1 = Integer.parseInt(Click_Usage);										// Convert Click_Usage text value into a number
    	ReportText = "14) Clicks Used: " + Click_Usage;												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Get Clicks Remaining current value for comparison later
		WebElement td_text2 = driver.findElement(By.xpath("//tbody/tr[2]/td[5]"));					// Get to second line of the table 5th item
		String Clicks_Remaining = td_text2.getText();												// Get the text from this cell
		int Click_Remaining_Num1 = Integer.parseInt(Clicks_Remaining);								// Convert Clicks_Remaining text value into a number
    	ReportText = "15) Clicks Remaining: " + Clicks_Remaining;									// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
    	// Launch Chrome (Precondition)
    	System.setProperty("webdriver.chrome.driver", exePath);										// Set Path to open Chrome from
    	WebDriver Mimic = new ChromeDriver();														// Open a new instance of Chrome
    	ReportText = "16) Run20 Second Chrome Launched";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	
        // Use the Fast Address Mimic url  	
    	String searchURL = "https://addrsvr2.hopewiser.com/address-search/";						// Set variable to path
    	String password = Constant.Password;														// Set variable to password via the utility constant script
    	Mimic.get(searchURL);																		// Open the login website using the Url
		Timeloop.Execute(Mimic, "https://addrsvr2.hopewiser.com/address-search/");					// Ensure the correct page is displayed in a sensible time
    	ReportText = "17) " + searchURL + " Website Opened";										// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	
    	// 12.1 Sign In to the Fast Address Mimic
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet7");			// Look in the Excel sheet for test data
		String sName = ExcelUtils.getCellData(1, 1);												// Set variable to user name
		//String user = "QAtest-ha-hu";																// Set variable to user name Should not be hard coded
    	Mimic.findElement(By.id("username")).clear(); 												// Clear the User Name
    	Mimic.findElement(By.id("username")).sendKeys(sName); 										// Enter the User Name
    	Thread.sleep(1000);																			// Pause 3 seconds for page update
    	
    	Mimic.findElement(By.id("password")).clear(); 												// Clear the Password
    	Mimic.findElement(By.id("password")).sendKeys(password); 									// Enter the Password
    	Thread.sleep(1000);																			// Pause 3 seconds for page update
    	
    	Mimic.findElement(By.xpath("//button[contains(.,'Sign In')]")).click();  					// Click Sign In button
    	Thread.sleep(1000);																			// Pause 3 seconds for page update
    	ReportText = "18) Signed In as " + sName;													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	
    	// Set Dataset field
    	//<select id="dataset" onchange="dataset_onchange()"><option value="">Choose a Dataset...</option><option value="uk-rm-paf-external">uk-rm-paf-external</option><option value="uk-rm-paf-internal">uk-rm-paf-internal</option></select>
    	new Select(Mimic.findElement(By.xpath("//select[@id='dataset']"))).selectByVisibleText("uk-rm-paf-internal"); // Select PAF dataset from the dropdown
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
    	
    	// Search for a address with house number and postcode
    	Mimic.findElement(By.id("input")).clear(); 													// Clear the User Name
    	Mimic.findElement(By.id("input")).sendKeys("6,m337ug"); 									// Enter the Premises number and the postcode
    	Thread.sleep(1000);																			// Pause 3 seconds for page update
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AddressSearchPage20_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(Mimic, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
    	// Find the address
		Mimic.findElement(By.xpath("//button[contains(.,'Find Address')]")).click(); 				// Click Find Address button
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
    	
    	// Select the address
    	Mimic.findElement(By.xpath("//button[contains(.,'Select')]")).click(); 						// Click Select button
		Thread.sleep(3000);																			// Pause 3 seconds for page update
    	ReportText = "19) Address is selected";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "Select20_";																			// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(Mimic, SS);														// Create Screenshot using appModule.ScreenShot_Action
    	
		// Close the Browser and kill task (Postcondition)
    	Mimic.quit();																				// Close the Browser
    	ReportText = "20) Closed Second Browser";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	
    	// New Test section missing from the manual tests
    	driver.findElement(By.xpath("//div/div/section[2]/article/div/div/ul/li[5]/a")).click(); 	// Click the Users tab to Refresh the Account page
    	driver.findElement(By.xpath("//div/div/section[2]/article/div/div/ul/li[2]/a")).click(); 	// Click the Usage tab to Refresh the Account page
    	  
		// Get Click Usage current value for comparison
		WebElement td_text3 = driver.findElement(By.xpath("//tbody/tr[2]/td[4]"));					// Get to second line of the table 4th item
		String Click_Usage2 = td_text3.getText();													// Get the text from this cell
		int Click_Usage_Num2 = Integer.parseInt(Click_Usage2);										// Convert Click_Usage text value into a number
    	ReportText = "21) Clicks Used: " + Click_Usage2;											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Get Clicks Remaining current value for comparison
		WebElement td_text4 = driver.findElement(By.xpath("//tbody/tr[2]/td[5]"));					// Get to second line of the table 5th item
		String Clicks_Remaining2 = td_text4.getText();												// Get the text from this cell
		int Click_Remaining_Num2 = Integer.parseInt(Clicks_Remaining2);								// Convert Clicks_Remaining text value into a number
    	ReportText = "22) Clicks Remaining: " + Clicks_Remaining2;									// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		String whatiscurrenturl4 = driver.getCurrentUrl();											// Put current website address in variable.			
		// Test if click was added
		if (Click_Usage_Num1<Click_Usage_Num2)														// Test if Home page exists
			{
	    	ReportText = "23) ClicksReducedPassed: " + whatiscurrenturl4;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "ClicksReducedPass20_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
	    	ReportText = "23) ClicksReducedFailed: " + whatiscurrenturl4;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "ClicksReducedPass20_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			System.exit(1);																			// Drop out of script
			}
		
		int result = (Click_Usage_Num2-Click_Usage_Num1);											// Calculate clicks used
    	ReportText = "24) Clicks used: " + result;													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Test if click was removed
		if (Click_Remaining_Num1>Click_Remaining_Num2)												// Test if Home page exists
			{
	    	ReportText = "25) Clicks Increased Passed: " + whatiscurrenturl4;						// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "ClicksReducedPass20_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
	    	ReportText = "25) Clicks Increased Failed: " + whatiscurrenturl4;						// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "ClicksReducedPass20_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			System.exit(1);																			// Drop out of script
			}
		
		int result2 = (Click_Remaining_Num2-Click_Remaining_Num1);									// Calculate clicks used
    	ReportText = "26) Clicks used: " + result2;													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// 3.4 Select Users tab
		//*[@id="main"]/div/div/section[2]/article/div/div/ul/li[5]/a
		driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[5]/a")).click(); // Click Users tab
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	
    	// Search for User	
		//*[@id="textfilteruser-table"]
		String sUserName = ExcelUtils.getCellData(1, 1);
		driver.findElement(By.xpath("//input[@id='textfilteruser-table']")).sendKeys(sUserName); 	// Enter User name into the Search table box
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	ReportText = "27) Searched for " + sUserName;												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
    	// Select QAtest-ha-hu from the table		
    	driver.findElement(By.xpath("//span[contains(.,'QAtest-ha-hu')]")).click();					// Click on QAtest-ha-hu link (Can we stop this being hard coded?)	
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	ReportText = "28) " + sUserName + " found";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		//Select the Usage tab	
    	//*[@id="main"]/div/div/section[2]/article/div/div[1]/ul/li[2]/a
    	driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[2]/a")).click(); // Click Usage tab
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	
    	// Choose the first date option in the Month Filter
    	//<select id="filter-bundle-table-Address0"><option value="all">--All--</option><option value="2017-09">2017-09</option></select>
    	WebElement Address0 = driver.findElement(By.xpath("//select[@id='filter-bundle-table-Address0']"));
    	driver.findElement(By.xpath("//select[@id='filter-bundle-table-Address0']")).sendKeys(Keys.DOWN); // Click the down arrow key
    	driver.findElement(By.xpath("//select[@id='filter-bundle-table-Address0']")).sendKeys(Keys.RETURN); // Click the enter key
		String vdropdown = Address0.getText();														// Get the text from this dropdown
		String replaced0 = vdropdown.replaceAll("--All--[\n\r]","");								// Remove the --All-- text from the dropdown information
    	ReportText = "29) Bundle: " + replaced0;													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
    	// Choose the uk-rm-paf-internal option in the Dataset Reference Filter
    	//<select id="filter-bundle-table-Address1"><option value="all">--All--</option><option value="uk-rm-paf-internal">uk-rm-paf-internal</option></select>
    	WebElement Address1 = driver.findElement(By.xpath("//select[@id='filter-bundle-table-Address1']"));
    	driver.findElement(By.xpath("//select[@id='filter-bundle-table-Address1']")).sendKeys("uk-rm-paf-internal"); // Select PAF dataset from the dropdown
		vdropdown = Address1.getText();																// Get the text from this dropdown
		String replaced1 = vdropdown.replaceAll("--All--[\n\r]","");								// Remove the --All-- text from the dropdown information
    	ReportText = "30) Bundle: " + replaced1;													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
    	// Choose the last bundle number option in the Bundle Filter
    	// <select id="filter-bundle-table-Address2"><option value="all">--All--</option><option value="1108">1108</option></select> value will change
    	WebElement Address2 = driver.findElement(By.xpath("//select[@id='filter-bundle-table-Address2']"));
    	driver.findElement(By.xpath("//select[@id='filter-bundle-table-Address2']")).sendKeys(Keys.DOWN); // Click the down arrow key
    	driver.findElement(By.xpath("//select[@id='filter-bundle-table-Address2']")).sendKeys(Keys.RETURN); // Click the enter key
		vdropdown = Address2.getText();																// Get the text from this dropdown
		String replaced2 = vdropdown.replaceAll("--All--[\n\r]","");								// Remove the --All-- text from the dropdown information
    	ReportText = "31) Bundle: " + replaced2;													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AddressUsage20_";																		// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
    	
		// 7.0 Select the Your Account button <a href="/hostedmgr/" class="ym-button">Your Account</a>
		driver.findElement(By.xpath("//a[@href='/hostedmgr/']")).click(); 							// Click the Your Account button
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
    	ReportText = "32) Click the Your Account Button";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountDetails20_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
    	// Select the left hand Accounts link <a href="/hostedmgr/acct">Accounts</a>
    	driver.findElement(By.xpath("//a[@href='/hostedmgr/acct']")).click();						// Click the Your Account button
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/acct");			// Ensure the correct page is displayed in a sensible time
    	ReportText = "33) Click the Account link";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "Acounts20_";																			// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Search for Hopewiser Test
		// <input id="textfilteraccount-table" type="text" title="Searches each table cell. Supports multi-character (*) and single character (?) wildcards">				
		driver.findElement(By.xpath("//input[@id='textfilteraccount-table']")).sendKeys("Hopewiser Test"); // Enter the user name
		Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	ReportText = "34) Searched for Hopewiser Test";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Select Hopewiser Test link from table				
		driver.findElement(By.xpath("//span[contains(.,'Hopewiser Test')]")).click();				// Click the user
		Thread.sleep(3000);																			// Print validation text to console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountDetails20_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select Bundle tab
    	driver.findElement(By.xpath("//div/div/section[2]/article/div/div/ul/li[4]/a")).click();	// Click Bundle tab
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	
    	// Search for Bundle	
    	//<input id="textfilterbundle-table" type="text" title="Searches each table cell. Supports multi-character (*) and single character (?) wildcards">
    	driver.findElement(By.xpath("//input[@id='textfilterbundle-table']")).sendKeys(replaced2);	//Enter User name into the Search table box
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	ReportText = "35) Searched for " + replaced2;												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
    	// Select the bundle number from 3.4 which is stored in replaced2
    	driver.findElement(By.xpath("//span[contains(.,'" + replaced2 + "')]")).click();			// Click on bundle link
    			
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	ReportText = "36) " + replaced2 + " found";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
    	// Select the Usage tab
    	driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[2]/a")).click(); // Click Usage tab
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "BundleUsage20_";																		// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// 4.1 Log Off
		driver.findElement(By.xpath("//a[@href='/hostedmgr/secure/logout']")).click(); 				// Click the Log Off link
		ReportText = "37) Logged off";																// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Close the Browser and kill task (Postcondition)
    	driver.quit();																				// Close the Browser
		ReportText = "38) Closed Browser";															// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	}
	}