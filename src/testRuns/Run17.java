package testRuns;

import org.openqa.selenium.By;
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
public class Run17 
	{
    public static void main(String[] args) throws Exception 
    	{    	
    	// Launch Chrome (Precondition)
    	String exePath = "C:\\Selenium\\chromedriver.exe";											// Set Variable for path Where Chrome.exe is stored
    	System.setProperty("webdriver.chrome.driver", exePath);										// Set Path to open Chrome from
    	WebDriver driver = new ChromeDriver();														// Open a new instance of Chrome
		String ReportText = "1) Run17 Chrome Launched";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)    	
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet5");			// Look in the Excel sheet for test data
    	
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
    		String SS = "LoginPass17_";																// Create variable to help name the Screenshot
    		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
    		}
    	else
    		{	
    		if (whatiscurrenturl.equals(testurl2))													// Test if logon page exists
    			{
    			ReportText = "3) Login Failed: " + whatiscurrenturl;								// Set up report text variable
    			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
    			ReportText = "3.1) Please wait for the Hopewiser Account Activation email and use it to Activate the Account.";	// Set up report text variable
    			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
    			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
    			String SS = "LoginFail17_";															// Create variable to help name the Screenshot
    			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
    			System.exit(1);																		// Drop out of script
    			}
    		else
    			{
    			ReportText = "3) Unknown Error: " + whatiscurrenturl;								// Set up report text variable
    			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
    			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
    			String SS = "LoginError17_";														// Create variable to help name the Screenshot
    			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
   				System.exit(1);																		// Drop out of script
    			}
    		}
    	
    	// Open the Costcentre Accordion
    	//<a data-toggle="collapse" data-parent="#accordion" href="#collapse1">Cost Centre: costcentre</a>
		driver.findElement(By.xpath("//a[contains(.,'Cost Centre: costcentre')]")).click();			// Click the Cost Centre: costcentre heading
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
    	
    	//*[@id="fhome"]/table[1]/tbody/tr[1]/td[7]/input
    	driver.findElement(By.xpath("//table[1]/tbody/tr[1]/td[7]/input")).click(); 				// Select the 1st buy again check box <td class="table-input"><input type="checkbox" name="buyAgain" value="1114"></td>
       	Thread.sleep(1000);																			// Pause 1 seconds for page update
       	
       	//*[@id="fhome"]/table[1]/tbody/tr[2]/td[7]/input
    	driver.findElement(By.xpath("//table[1]/tbody/tr[2]/td[7]/input")).click(); 				// Select the 2nd buy again check box <td class="table-input"><input type="checkbox" name="buyAgain" value="1115"></td>
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
    	
    	// Buy more bundles of clicks   	
    	driver.findElement(By.xpath("//input[@value='Buy Again']")).click();						// Click on Buy Again button 
    	Thread.sleep(5000);																			// Pause 3 seconds for page update
		ReportText = "4) Buy Again button clicked";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		String SS = "Buy Again17_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
    	
    	// Click on Check Out button
    	// <a href="/hostedmgr/shop/payment" class="ym-button ym-primary">Check&nbsp;Out</a>
    	try
    		{
    		driver.findElement(By.xpath("//a[@class='ym-button ym-primary']")).click(); 			// Click the Check Out button on https://services-sandbox.hopewiser.com/hostedmgr/shop/basketdriver
    		ReportText = "5.0) Check Out button clicked";											// Set up report text variable
    		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
    		}
    	catch (Exception e)
    		{
    		driver.findElement(By.xpath("//a[@href='/hostedmgr/shop/payment']")).click();			// Click the Check Out button on https://services-sandbox.hopewiser.com/hostedmgr/shop/basketdriver
    		ReportText = "5.1) Check Out button clicked";											// Set up report text variable
    		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
    		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "Checkout17_";																		// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
    		}
    	
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
		ReportText = "6) Check Out button clicked";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "Checkout17_";																			// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
    	
    	// Clear the Contact name field then enter QA Run 13.1
    	driver.findElement(By.xpath("//input[@id='contactName']")).clear();							// Clear the Name edit box
    	driver.findElement(By.xpath("//input[@id='contactName']")).sendKeys("QA Run 13.1"); 		// Enter the Name
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
    	
    	// Clear the address field then enter Merlin court, Atlantic street, Altrincham		
    	driver.findElement(By.xpath("//textarea[@id='address']")).clear(); 							// Clear the Address edit box
    	driver.findElement(By.xpath("//textarea[@id='address']")).sendKeys("Merlin court, Atlantic street, Altrincham"); // Enter the Address
    	Thread.sleep(1000);																			// Pause 1 seconds for page update
    	
    	// Clear the postcode field then enter WA14 5NL
    	driver.findElement(By.xpath("//input[@id='postcode']")).clear();							// Clear the postcode edit box
    	driver.findElement(By.xpath("//input[@id='postcode']")).sendKeys("WA14 5NL"); 				// Enter the postcode
    	Thread.sleep(1000);																			// Pause 1 seconds for page update
    	
    	// Click the Proceed to Payment button
    	driver.findElement(By.xpath("//input[@value='Proceed to Payment']")).click(); 				// Click the Proceed to Payment button
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
		ReportText = "7) Proceed to Payment";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	
    	// Click the Test with WorldPay button
    	driver.findElement(By.xpath("//input[@value='Test with WorldPay']")).click(); 				// Click the Test with WorldPay button
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
		ReportText = "8) Test with WorldPay";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	
    	// 6.9 Mimic the World Pay Payment details ensure U: is accessible
		//driver.get("file:///D:/payment-resp.htm"); // Open the Worldpay File
    	//driver.get("file:///N:/Testers/FR/frufai/ASiC%20Regression%20Pack/AddressServer%20-%20From%20NPC140/payment-resp.htm"); // Open the Worldpay File
    	//file:///U:/frufai/ASiC%20Regression%20Pack/payment-resp.htm
    	//driver.get("file:///N:/Testers/FR/frufai/ASiC Regression Pack/AddressServer - From NPC140/payment-resp.htm"); // Open the Worldpay File
		driver.get("file:///U:/mflook/frufai/ASiC%20Regression%20Pack/payment-resp.htm"); 			// Open the Worldpay File
		Timeloop.Execute(driver, "file:///U:/mflook/frufai/ASiC%20Regression%20Pack/payment-resp.htm"); // Ensure the correct page is displayed in a sensible time
    	ReportText = "9) Mimic File Opened";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Fill in the details		
		new Select(driver.findElement(By.name("transStatus"))).selectByVisibleText("Yes");  		// Select Yes from the drop down
		Thread.sleep(2000);																			// Pause 2 seconds for page update
		
		driver.findElement(By.xpath("//input[@id='cartId']")).clear(); 								// Clear the edit box
		driver.findElement(By.xpath("//input[@id='cartId']")).sendKeys("942ec268-bb79-4d42-8251-5de905e52a31"); // Enter a valid Cart Id
		Thread.sleep(2000);																			// Pause 2 seconds for page update
		
		driver.findElement(By.xpath("//input[@id='transId']")).clear(); 							// Clear the edit box
		driver.findElement(By.xpath("//input[@id='transId']")).sendKeys("3070771749"); 				// Enter a valid Transaction Id
		Thread.sleep(2000);																			// Pause 3 seconds for page update
		
		new Select(driver.findElement(By.name("testMode"))).selectByVisibleText("Test");			// Select Test from the drop down
		Thread.sleep(2000);																			// Pause 3 seconds for page update
		
		new Select(driver.findElement(By.name("MC_redirectUrl"))).selectByVisibleText("Complete"); 	// Select Complete from the drop down
		Thread.sleep(2000);																			// Pause 3 seconds for page update
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "MimicPage17_";																		// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action		
		
		driver.findElement(By.xpath("//input[@class='submit']")).click();							// Click the submit button
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		ReportText = "10) Submit Worldpay Transaction";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		driver.findElement(By.xpath("//a[@title='View Your Account']")).click();					// Click the View Your Account link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/home");			// FF Ensure the correct page is displayed in a sensible time
		ReportText = "11) View Your Account";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Open the costcentre Accordion
		// <a data-toggle="collapse" data-parent="#accordion" href="#collapse1" class="collapsed">Cost Centre: costcentre</a>
		driver.findElement(By.xpath("//a[contains(.,'Cost Centre: costcentre')]")).click();  		// Click the Cost Centre: costcentre header bar
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "CCUsageAccordion17_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Get Clicks Remaining current value for comparison later
		///html/body/div/div/div/section[2]/article/div/form/div/div/div[2]/div/div/table[1]/tbody/tr[2]/td[5]
		//WebElement td_text2 = driver.findElement(By.xpath("//tbody/tr[2]/td[5]"));				// Get to second line of the table 5th item
		//WebElement td_text2 = driver.findElement(By.xpath("//html/body/div/div/div/section[2]/article/div/form/div/div/div[2]/div/div/table[1]/tbody/tr/td[4]")); // Get to first line of the table 4th item
		WebElement td_text2 = driver.findElement(By.xpath("//html/body/div/div/div/section[2]/article/div/form/div/div/div[2]/div/div/table[1]/tbody/tr[2]/td[4]")); // Get to second line of the table 5th item
		String Clicks_Remaining = td_text2.getText();												// Get the text from this cell
		int Click_Remaining_Num1 = Integer.parseInt(Clicks_Remaining);								// Convert Clicks_Remaining text value into a number
    	ReportText = "12) Clicks Remaining: " + Clicks_Remaining;									// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Launch Chrome (Precondition)
    	WebDriver Mimic = new ChromeDriver();														// Open a new instance of Chrome
		ReportText = "13) Run17 Second Firefox Launched";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
        // Use the Fast Address Mimic url
    	String searchURL = "https://addrsvr2.hopewiser.com/address-search/";						// Set variable to path
    	String user = "QAtest-su-ha1";																// Set variable to user name
    	String password = Constant.Password;
    	Mimic.get(searchURL);																		// Open the login website using the Url
		Timeloop.Execute(Mimic, "https://addrsvr2.hopewiser.com/address-search/");					// FF Ensure the correct page is displayed in a sensible time
		ReportText = "14) "+ searchURL +" Website Opened";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Log on to the Fast Address Mimic 
		Mimic.findElement(By.xpath("//input[@id='username']")).clear(); 							// Clear the edit box
		Mimic.findElement(By.xpath("//input[@id='username']")).sendKeys(user);  					// Enter the User Name (should be var)
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		
		Mimic.findElement(By.xpath("//input[@id='password']")).clear(); 							// Clear the edit box
		Mimic.findElement(By.xpath("//input[@id='password']")).sendKeys(password);					// Enter the Password
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		
		// Click the Sign In button
		Mimic.findElement(By.xpath("//button[contains(.,'Sign In')]")).click();						// Click the Sign In button
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		ReportText = "15) Signed in as QAtest-su-ha1";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Choose the right Dataset		    	
		new Select(Mimic.findElement(By.id("dataset"))).selectByVisibleText("uk-rm-paf-internal"); 	// Select uk-rm-paf-internal from the dataset dropdown
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		
		// Search for a post code	    	
		Mimic.findElement(By.xpath("//input[@id='input']")).clear(); 								// Clear the edit box
		Mimic.findElement(By.xpath("//input[@id='input']")).sendKeys("6,m337ug");  					// Enter the premise number and the postcode
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		
		// Find the address
		Mimic.findElement(By.xpath("//button[contains(.,'Find Address')]")).click();  				// Click the Find address button
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		
		// Select the address
		Mimic.findElement(By.xpath("//button[contains(.,'Select')]")).click();  					// Click Select button
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		ReportText = "16) Address is selected";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console		
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "Select17_";																			// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Close the Browser and kill task (Postcondition)
		Mimic.quit();																				// Close the Browser
		ReportText = "17) Closed Second Browser";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Open the costcenter Accordion
		// <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">Cost Centre: costcentre</a>
		driver.navigate().refresh();																// Refresh the page to update the Clicks remaining value
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/home");			// FF Ensure the correct page is displayed in a sensible time
		driver.findElement(By.xpath("//a[contains(.,'Centre:')]")).click();  						// Click the Cost Centre: costcentre header bar
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "CCUsageAccordion17_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Get Clicks Remaining current value for comparison
		WebElement td_text4 = driver.findElement(By.xpath("//html/body/div/div/div/section[2]/article/div/form/div/div/div[2]/div/div/table[1]/tbody/tr/td[4]")); // Get to first line of the table 4th item
		//WebElement td_text4 = driver.findElement(By.xpath("//html/body/div/div/div/section[2]/article/div/form/div/div/div[2]/div/div/table[1]/tbody/tr[2]/td[4]")); // Get to second line of the table 5th item
		String Clicks_Remaining2 = td_text4.getText();												// Get the text from this cell
		int Click_Remaining_Num2 = Integer.parseInt(Clicks_Remaining2);								// Convert Clicks_Remaining text value into a number
		ReportText = "18) Clicks Remaining: " + Clicks_Remaining2;									// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		int result2 = (Click_Remaining_Num2-Click_Remaining_Num1);									// Calculate clicks used
		ReportText = "19) Clicks Remaining used: " + result2;										// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// 4.1 Log Off
		driver.findElement(By.xpath("//a[@href='/hostedmgr/secure/logout']")).click(); 				// Click the Log Off link
		ReportText = "20) Logged off";																// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Close the Browser and kill task (Postcondition)
		driver.quit();																				// Close the Browser
		ReportText = "21) Closed Browser";															// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	}
	}