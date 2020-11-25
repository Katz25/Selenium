package testRuns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Keys;

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
public class Run14 
	{
    public static void main(String[] args) throws Exception 
    	{		
    	// Launch Chrome  (Precondition)
    	String exePath = "C:\\Selenium\\chromedriver.exe";											// Set Variable for path Where Chrome.exe is stored
    	System.setProperty("webdriver.chrome.driver", exePath);										// Set Path to open Chrome from
    	WebDriver driver = new ChromeDriver();														// Open a new instance of Chrome
		String ReportText = "1) Run14 Chrome Launched";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method  (Precondition)  	
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet4");			// Look in the Excel sheet for test data
    	
    	// Open the login website using the Url (Precondition)
    	driver.get(Constant.URL);																	// Open the login website using the Url
		ReportText = "2) " + Constant.URL + " Website Opened";										// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	
    	// 4.0 Login To Address Server in the Cloud
		Login_Action.Execute(driver);																// Login using the appModule Login_Action
		
		// Test if Login occurred
		String whatiscurrenturl = driver.getCurrentUrl();											// Set variable to the current web address
		String testurl1 = "https://services-sandbox.hopewiser.com/hostedmgr/home";					// Set variable to path
		String testurl2 = "https://services-sandbox.hopewiser.com/hostedmgr/logon";					// Set variable to path
		if (whatiscurrenturl.equals(testurl1))														// Test if Home page exists
			{
			ReportText = "3) Login Passed: " + whatiscurrenturl;									// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "LoginPass14_";																// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl.contains(testurl2))												// Test if logon page exists
				{
				ReportText = "3) Login Failed: " + whatiscurrenturl;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginFail14_";															// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			else
				{
				ReportText = "3) Unknown Error" + whatiscurrenturl;									// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginError14_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}
    	
    	// 6.0 Select the Shop link    	
    	driver.findElement(By.xpath("//a[@href='/hostedmgr/shop']")).click();						// Click the Shop Link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/shop");			// Ensure the correct page is displayed in a sensible time
    	
    	// Select the UK Postal Address File Buy button   	
    	driver.findElement(By.xpath("//a[@href='/hostedmgr/shop/plans/3']")).click();				// Click the Buy button
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/shop/plans/3"); 	// Ensure the correct page is displayed in a sensible time
    	 	
    	// Select 500 clicks from the UK PAF - External User dropdown
    	new Select(driver.findElement(By.id("vbundleList0.bundle.clicks"))).selectByVisibleText("500"); // Enter value of 500
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	
    	// Select 500 clicks from the UK PAF - Internal User dropdown
    	new Select(driver.findElement(By.id("vbundleList1.bundle.clicks"))).selectByVisibleText("500"); // Enter value of 500
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	
		String whatiscurrenturl3 = driver.getCurrentUrl();											// Set variable to the current web address
		ReportText = "4) This page is: " + whatiscurrenturl3;										// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		String SS = "CreatedClicks14_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
    	
    	// Add to basket
		//*[@id="shop"]/div/p/input  //<input type="submit" class="ym-button ym-danger" value="Add&nbsp;to&nbsp;Basket">
        driver.findElement(By.tagName("body")).sendKeys(Keys.END);									// Scroll to the end of the page
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "LowerCreatedClicks14_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/shop/plans/");	// Ensure the correct page is displayed in a sensible time
		
		try
			{
	    	driver.findElement(By.xpath("//input[@class='ym-button ym-danger']")).click(); 			// Click Add to Basket Button
			}
		catch (Exception e)
			{
			driver.findElement(By.xpath("//input[@type='submit']")).click(); 						// Click Add to Basket Button
			}
			
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/shop/basket"); 	// Ensure the correct page is displayed in a sensible time
		ReportText = "5) Add to basket button clicked";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	
    	// Checkout
		driver.findElement(By.xpath("//a[contains(.,'Out')]")).click();								// Click the Check Out button on https://services-sandbox.hopewiser.com/hostedmgr/shop/basket			
		Timeloop.Execute(driver, "https://uk-sandbox.hopewiser.com/payment/order/"); 				// Ensure the correct page is displayed in a sensible time
    	whatiscurrenturl = driver.getCurrentUrl();													// Set variable to the current web address
    	ReportText = "6) Returns to Your Basket: " + whatiscurrenturl;								// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "YourBasket14_";																		// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
    	// Change Name to QA Run 14
    	//<input id="contactName" name="contactName" type="text" value="QA Run 13.3">
    	driver.findElement(By.xpath("//input[@id='contactName']")).clear();							// Clear the Name field on https://services-sandbox.hopewiser.com/payment/order/**
    	driver.findElement(By.xpath("//input[@id='contactName']")).sendKeys("QA Run 14");			// Edit the name from QA Run 13.3 t0 QA Run 14
    	
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
    	driver.findElement(By.xpath("//textarea[@cols='36']")).sendKeys("Merlin court, Atlantic street, Altrincham"); // Enter the address into the field
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
        //<option value="0">Cotton Traders Ltd,Merlin Court,Atlantic Street,ALTRINCHAM,Cheshire,WA14 5NL</option><option value="1">Hopewiser Ltd,Merlin Court,Atlantic Street,ALTRINCHAM,Cheshire,WA14 5NL</option><option value="2">Matrix Telematics Ltd,Merlin Court,Atlantic Street,ALTRINCHAM,Cheshire,WA14 5NL</option><option value="3">No 3 Investments Ltd,Merlin Court,Atlantic Street,ALTRINCHAM,Cheshire,WA14 5NL</option><option value="4">Northern Monitoring Services,Merlin Court,Atlantic Street,ALTRINCHAM,Cheshire,WA14 5NL</option><option value="5">T I C Group Ltd,Merlin Court,Atlantic Street,ALTRINCHAM,Cheshire,WA14 5NL</option></select>
    	driver.findElement(By.xpath("//select[@id='searchresult']")).sendKeys("H"+ Keys.RETURN); 	// Select Hopewiser Ltd from the list
    	
    	// Proceed to Payment
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "ProceedtoPayment14_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		driver.findElement(By.xpath("//input[@value='Proceed to Payment']")).click();  				// Click the Proceed button
		Timeloop.Execute(driver, "https://uk-sandbox.hopewiser.com/payment/order/");				// Ensure the correct page is displayed in a sensible time
    	
    	// Test with WorldPay
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "TestWithWorldPay14_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		//<INPUT type=submit value="Test with WorldPay">
    	driver.findElement(By.xpath("//input[@value='Test with WorldPay']")).click(); 				// Click the Test with WorldPay button
    	Timeloop.Execute(driver, "https://secure-test.worldpay.com/wcc/purchase");					// Ensure the correct page is displayed in a sensible time
 
    	// Worldpay
		ReportText = "7) Worldpay Test Mode Page Opened";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "WorldpayTestModePage14_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		// 6.0 Finished Worldpay uses a I'm not a robot feature so using Mimic instead
    	
    	// 6.9 Mimic the World Pay Payment details ensure U: is accessible   		
    	driver.get("file://U:/mflook/frufai/ASiC%20Regression%20Pack/payment-resp.htm"); 			// Open the directory before you open a file or test will crash
		Thread.sleep(3000);																   			// Pause 3 seconds for page update
		//Timeloop.Execute(driver, "U:\\mflook\\frufai\\ASiC Regression Pack\\payment-resp.htm"); 	// (IE)Ensure the correct page is displayed in a sensible time a sensible time
		ReportText = "8) Mimic File Opened";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "WorldpayCallbackResponse14_";															// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Fill in the Worldpay details		
		// Select Transaction Status
		try
			{
			new Select(driver.findElement(By.name("transStatus"))).selectByVisibleText("Yes"); 		// Click the Yes drop down option
			Thread.sleep(3000);																		// Pause 3 seconds for page update
			}
		catch (Exception e)
			{
			// Mimic the World Pay Payment details ensure U: is accessible   		
    		driver.get("file://D:/payment-resp.htm"); 												// The directory was not open so the test crashed
    		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/shop/plans/"); // Ensure the correct page is displayed in a sensible time
			ReportText = "8.1) Warning Second Mimic File Opened";									// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "WorldpayCallbackResponse14_";														// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			new Select(driver.findElement(By.name("transStatus"))).selectByVisibleText("Yes"); 		// Click the Yes drop down option
			Thread.sleep(3000);																		// Pause 3 seconds for page update
			}
    	
    	// Cart ID
    	driver.findElement(By.xpath("//input[@id='cartId']")).sendKeys("942ec268-bb79-4d42-8251-5de905e52a31"); // Enter a valid Cart Id
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	
    	// Transaction ID
    	driver.findElement(By.xpath("//input[@id='transId']")).sendKeys("3070771749");  			// Enter a valid Transaction Id
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	
    	// Select Test Mode
    	new Select(driver.findElement(By.name("testMode"))).selectByVisibleText("Test");  			// Select test mode drop down option
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	
    	// Select MC Redirect URL
    	new Select(driver.findElement(By.name("MC_redirectUrl"))).selectByVisibleText("Complete"); 	// Select Complete from the drop down
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	
		ReportText = "9) Submit Worldpay Callback Response";										// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "SubmitWorldpayCallbackResponse14_";													// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		//<input type="submit" class="submit" value="Submit">
		driver.findElement(By.xpath("//input[@class='submit']")).click(); 							// Click the Submit button
		//<a href="">here</a>
		driver.findElement(By.xpath("//a[contains(.,'here')]")).click(); 							// Click the here link
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "CheckOutComplete14_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		//<a href="/hostedmgr/" class="ym-button">Your Account</a>
		driver.findElement(By.className("ym-button")).click();										// Click the Your Account button
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/home");			// Ensure the correct page is displayed in a sensible time
		ReportText = "10) Click the Your Account Button";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountDetails14_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Get Clicks Remaining current value for comparison later
		//WebElement td_text2 = driver.findElement(By.xpath("//html/body/div/div/div/section[2]/article/div/form/div/div/div[2]/div/div/table[1]/tbody/tr/td[4]")); // Get to first line of the table 4th item
		WebElement td_text2 = driver.findElement(By.xpath("//html/body/div/div/div/section[2]/article/div/form/div/div/div[2]/div/div/table[1]/tbody/tr[2]/td[4]")); // Get to second line of the table 4th item
		String Clicks_Remaining = td_text2.getText();												// Get the text from this cell
		int Click_Remaining_Num1 = Integer.parseInt(Clicks_Remaining);								// Convert Clicks_Remaining text value into a number
    	ReportText = "11) Clicks Remaining: " + Clicks_Remaining;									// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
    	// Launch Chrome (Precondition)
    	System.setProperty("webdriver.gecko.driver", exePath);										// Set Path to open Chrome from
    	WebDriver Mimic = new ChromeDriver();														// Open a new instance of FirefoxDriver
    	ReportText = "12) Run14 Second Chrome Launched";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
    	// Use the Fast Address Mimic url
    	String user = "QAtest-su-ha1";																// Set variable to user name
    	String password = Constant.Password;														// Set variable to standard password
    	Mimic.get("https://addrsvr2.hopewiser.com/address-search/");								// Open the website
    	Timeloop.Execute(Mimic, "https://addrsvr2.hopewiser.com/address-search/");					// Ensure the correct page is displayed in a sensible time
		ReportText = "13) Mimic Website Opened";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	
		Mimic.findElement(By.id("username")).clear(); 												// Clear the edit box
		Mimic.findElement(By.id("username")).sendKeys(user); 										// Enter the User Name
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	
    	Mimic.findElement(By.id("password")).clear(); 												// Clear the edit box
    	Mimic.findElement(By.id("password")).sendKeys(password); 									// Enter the Password
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	
    	Mimic.findElement(By.xpath("//button[contains(.,'Sign In')]")).click(); 					// Click Sign In button
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
		ReportText = "14) Signed In as: " + user;													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	
        // Choose the correct Dataset   	
    	new Select(Mimic.findElement(By.id("dataset"))).selectByVisibleText("uk-rm-paf-internal"); 	// Select uk-rm-paf-internal from the dataset drop down
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	
    	// Search for an address  	
    	Mimic.findElement(By.id("input")).clear(); 													// Clear the edit box
    	Mimic.findElement(By.id("input")).sendKeys("6,m337ug"); 									// Enter the Premises number and the postcode
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	
    	// Find address
    	Mimic.findElement(By.xpath("//button[contains(.,'Find Address')]")).click(); 				// Click Find Address button
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	
    	// Click Select button
    	Mimic.findElement(By.xpath("//button[contains(.,'Select')]")).click(); 						// Click Select button
    	Timeloop.Execute(Mimic, "https://addrsvr2.hopewiser.com/address-search/");					// Ensure the correct page is displayed in a sensible time
		ReportText = "15) Address is selected";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "Select14_";																			// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Close the Browser and kill task (Postcondition)
		Mimic.quit();																				// Close the Browser
		ReportText = "16) Closed Browser";															// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Get Clicks Remaining current value for comparison
		driver.navigate().refresh();																// Refresh the page to update the Clicks remaining value
		Thread.sleep(3000);																			// Wait for refesh to complete
		//WebElement td_text4 = driver.findElement(By.xpath("//html/body/div/div/div/section[2]/article/div/form/div/div/div[2]/div/div/table[1]/tbody/tr/td[4]")); // Get to first line of the table 4th item
		WebElement td_text4 = driver.findElement(By.xpath("//html/body/div/div/div/section[2]/article/div/form/div/div/div[2]/div/div/table[1]/tbody/tr[2]/td[4]")); // Get to second line of the table 5th item
		String Clicks_Remaining2 = td_text4.getText();												// Get the text from this cell
		int Click_Remaining_Num2 = Integer.parseInt(Clicks_Remaining2);								// Convert Clicks_Remaining text value into a number
    	ReportText = "17) Clicks Remaining: " + Clicks_Remaining2;									// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		int result2 = (Click_Remaining_Num2-Click_Remaining_Num1);									// Calculate clicks used
    	ReportText = "18) Clicks Remaining: " + result2;											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// 4.1 Log Off
		driver.findElement(By.xpath("//a[@href='/hostedmgr/secure/logout']")).click(); 				// Click the Log Off link
		ReportText = "19) Logged off";																// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Close the Browser and kill task (Postcondition)
    	driver.quit();																				// Close the Browser
		ReportText = "20) Closed Browser";															// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	}
	}