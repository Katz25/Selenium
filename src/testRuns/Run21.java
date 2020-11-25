package testRuns;

import java.util.List;

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
public class Run21 
	{
    public static void main(String[] args) throws Exception 
    	{   	
    	// Launch Chrome (Precondition)
    	String exePath = "C:\\Selenium\\chromedriver.exe";											// Set Variable for path Where Chrome.exe is stored
    	System.setProperty("webdriver.chrome.driver", exePath);										// Set Path to open Chrome from
    	WebDriver driver = new ChromeDriver();														// Open a new instance of Chrome
		String ReportText = "1) Run21 Chrome Launched";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
        
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet3");
    			
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
			String SS = "LoginPass21_";																// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl.contains(testurl2))												// Test if logon page exists
				{
				ReportText = "3) Login Failed: " + whatiscurrenturl;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginFail21_";															// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			else
				{
				ReportText = "3) Unknown Error: " + whatiscurrenturl;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginError21_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}
		
		// 6.1 Click on the Shop link
		//<a href="/hostedmgr/shop">Shop</a>
		driver.findElement(By.xpath("//a[@href='/hostedmgr/shop']")).click();						// Click the Shop Link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/shop");			// Ensure the correct page is displayed in a sensible time
		
		// Click the UK Postal Address File
		//<a href="/hostedmgr/shop/plans/3">UK Postal Address File</a>
		driver.findElement(By.xpath("//a[@href='/hostedmgr/shop/plans/3']")).click();				// Select the PAF dataset Link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/shop/plans/3");	// Ensure the correct page is displayed in a sensible time  
		
		// Select a cost centre from the drop down
		new Select(driver.findElement(By.id("costid0"))).selectByVisibleText("Hopewiser Test - costcentre"); // Select Cost Centre from the dropdown
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		
		// Select a 500 clicks from the drop down
		new Select(driver.findElement(By.id("vbundleList0.bundle.clicks"))).selectByVisibleText("500"); // Select Number of clicks from the dropdown
		Thread.sleep(3000);																			// Pause 3 seconds for page update 
		
		// Select a cost centre from the drop down
		new Select(driver.findElement(By.id("costid1"))).selectByVisibleText("Hopewiser Test - costcentre"); // Select Cost Centre from the dropdown
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		
		// Select a 500 clicks from the drop down
		new Select(driver.findElement(By.id("vbundleList1.bundle.clicks"))).selectByVisibleText("500"); // Select Number of clicks from the dropdown
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		
		String whatiscurrenturl3 = driver.getCurrentUrl();											// Set variable to the current web address
		ReportText = "4) This page is: " + whatiscurrenturl3;										// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		String SS = "CreatedClicks21_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
    	// Add to basket  	
        driver.findElement(By.tagName("body")).sendKeys(Keys.END);									// Scroll to the end of the page
		SS = "LowerCreatedClicks14_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
        Thread.sleep(5000);																   			// Pause 3 seconds for page update (Can have timing issues here)
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
		
		// Add as Admin  
		//<a href="/hostedmgr/shop/admincomplete" class="ym-button ym-danger">Add&nbsp;as&nbsp;Admin</a>
		try
			{
			driver.findElement(By.xpath("//a[@href='/hostedmgr/shop/admincomplete']")).click();		// Click the Add as Admin button on https://services-sandbox.hopewiser.com/hostedmgr/shop/basket
			}
		catch (Exception e)
			{
			driver.findElement(By.xpath("//a[@class='ym-button ym-danger']")).click();				// Click the Add as Admin button problem at this point sometimes
			}
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/shop/thankyou");	// Ensure the correct page is displayed in a sensible time
    	whatiscurrenturl = driver.getCurrentUrl();													// Set variable to the current web address
		ReportText = "6) Admin Complete: " + whatiscurrenturl;										// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		SS = "Admin Complete21_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// 12.2 Select the Your Account button 
		//<a href="/hostedmgr/" class="ym-button">Your Account</a>
		driver.findElement(By.xpath("//a[@href='/hostedmgr/']")).click(); 							// Click the Your Account button
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/home");			// Ensure the correct page is displayed in a sensible time
		ReportText = "7) Click the Your Account Button";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountDetails21_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the left hand Accounts link 
		//<a href="/hostedmgr/acct">Accounts</a>
		driver.findElement(By.xpath("//a[@href='/hostedmgr/acct']")).click();						// Click the Your Account button
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/acct");			// Ensure the correct page is displayed in a sensible time
		ReportText = "8) Click the Account link";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AcountsPage21_";																		// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Search for HopewiserAccountTest
		//<input id="textfilteraccount-table" type="text" title="Searches each table cell. Supports multi-character (*) and single character (?) wildcards">				
		driver.findElement(By.xpath("//input[@id='textfilteraccount-table']")).sendKeys("Hopewiser Test");  // Enter the user name
		Thread.sleep(3000);																   			// Pause 3 seconds for page update
		ReportText = "9) Searched for Hopewiser Test";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Select Hopewiser Test link from the list				
		driver.findElement(By.xpath("//span[contains(.,'Hopewiser Test')]")).click();				// Click the user
		Timeloop.Execute(driver, "/details");														// Ensure the correct page is displayed in a sensible time
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountDetailsPage21_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the Usage Tab
		driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[2]/a")).click(); // Click the Usage tab
		Timeloop.Execute(driver, "/usage");															// FF Ensure the correct page is displayed in a sensible time
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountUsagePage21_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Get Click Usage current value for comparison later
		//*[@id="main"]/div/div/section[2]/article/div/table[1]/tbody/tr[2]/td[4]
		WebElement td_text1 = driver.findElement(By.xpath("//tbody/tr[2]/td[4]"));					// Get to second line of the table 4th item
		String Click_Usage = td_text1.getText();													// Get the text from this cell
		int Click_Usage_Num1 = Integer.parseInt(Click_Usage);										// Convert Click_Usage text value into a number
		ReportText = "10) Click Usage: " + Click_Usage;												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Get Clicks Remaining current value for comparison later
		WebElement td_text2 = driver.findElement(By.xpath("//tbody/tr[2]/td[5]"));					// Get to second line of the table 5th item
		String Clicks_Remaining = td_text2.getText();												// Get the text from this cell
		int Click_Remaining_Num1 = Integer.parseInt(Clicks_Remaining);								// Convert Clicks_Remaining text value into a number
		ReportText = "11) Clicks Remaining: " + Clicks_Remaining;									// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Launch Chrome (Precondition)
		System.setProperty("webdriver.chrome.driver", exePath);										// Set Path to open Chrome from
		WebDriver Mimic = new ChromeDriver();														// Open a new instance of Chrome
		ReportText = "12) Run21 Second Chrome Launched";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Use the Fast Address Mimic url  	
		String searchURL = "https://addrsvr2.hopewiser.com/address-search/";						// Set variable to path
		String password = Constant.Password;														// Set variable to password via the utility constant script
		Mimic.get(searchURL);																		// Open the login website using the Url
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		ReportText = "13) " + searchURL + " Website Opened";										// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// 12.1 Sign In to the Fast Address Mimic
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet11");			// Look in the Excel sheet for test data
		String sName = ExcelUtils.getCellData(1, 1);												// Set variable to user name
    	Mimic.findElement(By.id("username")).clear(); 												// Clear the edit box
		Mimic.findElement(By.id("username")).sendKeys(sName); 										// Enter the User Name
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		
    	Mimic.findElement(By.id("password")).clear(); 												// Clear the edit box
		Mimic.findElement(By.id("password")).sendKeys(password); 									// Enter the Password
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		
		Mimic.findElement(By.xpath("//button[contains(.,'Sign In')]")).click();  					// Click Sign In button
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		ReportText = "14) Signed In as " + sName;													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Set Dataset field
		//<select id="dataset" onchange="dataset_onchange()"><option value="">Choose a Dataset...</option><option value="uk-rm-paf-external">uk-rm-paf-external</option><option value="uk-rm-paf-internal">uk-rm-paf-internal</option></select>
		new Select(Mimic.findElement(By.xpath("//select[@id='dataset']"))).selectByVisibleText("uk-rm-paf-internal");   // Select PAF dataset from the dropdown
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		
		// Search for a address with house number and postcode
    	Mimic.findElement(By.id("input")).clear(); 													// Clear the edit box
		Mimic.findElement(By.id("input")).sendKeys("6,m337ug"); 									// Enter the Premises number and the postcode
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AddressSearchPage21_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(Mimic, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Find the address
		Mimic.findElement(By.xpath("//button[contains(.,'Find Address')]")).click(); 				// Click Find Address button
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		
		// Select the address
		Mimic.findElement(By.xpath("//button[contains(.,'Select')]")).click(); 						// Click Select button
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		ReportText = "15) Address is selected";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "Select21_";																			// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Close the Browser and kill task (Postcondition)
		Mimic.quit();																				// Close the Browser
		ReportText = "16) Closed Second Browser";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// New Test section missing from the manual tests
		driver.findElement(By.xpath("//div/div/section[2]/article/div/div/ul/li[5]/a")).click();	// Click the Users tab to Refresh the Account page
		driver.findElement(By.xpath("//div/div/section[2]/article/div/div/ul/li[2]/a")).click();	// Click the Usage tab to Refresh the Account page
		
		// Get Click Usage current value for comparison
		//*[@id="main"]/div/div/section[2]/article/div/table[1]/tbody/tr[3]/td[4]
		WebElement td_text3 = driver.findElement(By.xpath("//tbody/tr[2]/td[4]"));					// Get to second line of the table 4th item
		String Click_Usage2 = td_text3.getText();													// Get the text from this cell
		int Click_Usage_Num2 = Integer.parseInt(Click_Usage2);										// Convert Click_Usage text value into a number
		ReportText = "17) Clicks Used: " + Click_Usage2;											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Get Clicks Remaining current value for comparison
		//*[@id="main"]/div/div/section[2]/article/div/table[1]/tbody/tr[3]/td[5]
		WebElement td_text4 = driver.findElement(By.xpath("//tbody/tr[2]/td[5]"));					// Get to second line of the table 5th item
		String Clicks_Remaining2 = td_text4.getText();												// Get the text from this cell
		int Click_Remaining_Num2 = Integer.parseInt(Clicks_Remaining2);								// Convert Clicks_Remaining text value into a number
		ReportText = "18) Clicks Remaining: " + Clicks_Remaining2;									// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		String whatiscurrenturl4 = driver.getCurrentUrl();											// Put current website address in variable.			
		// Test if click was added
		if (Click_Usage_Num1<Click_Usage_Num2)														// Test if Home page exists
			{
			ReportText = "19) ClicksReducedPassed: " + whatiscurrenturl4;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "ClicksReducedPass21_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			ReportText = "19) ClicksReducedFailed: " + whatiscurrenturl4;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "ClicksReducedFail21_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			System.exit(1);																			// Drop out of script
			}
		
		int result = (Click_Usage_Num2-Click_Usage_Num1);											// Calculate clicks used
		ReportText = "20) Clicks used: " + result;													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Test if click was removed
		if (Click_Remaining_Num1>Click_Remaining_Num2)												// Test if Home page exists
			{
			ReportText = "21) ClicksIncreasedPassed: " + whatiscurrenturl4;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "ClicksReducedPass21_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			ReportText = "21) ClicksIncreasedFailed: " + whatiscurrenturl4;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "ClicksReducedFail21_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			System.exit(1);																			// Drop out of script
			}
		
		int result2 = (Click_Remaining_Num2-Click_Remaining_Num1);									// Calculate clicks used
		ReportText = "22) Clicks used: " + result2;													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// 3.4 Select Users tab
		//*[@id="main"]/div/div/section[2]/article/div/div/ul/li[5]/a
		driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[5]/a")).click(); // Click Users tab
		Thread.sleep(3000);																   			// Pause 3 seconds for page update
		
		// Search for User	
		//*[@id="textfilteruser-table"]
		driver.findElement(By.xpath("//input[@id='textfilteruser-table']")).sendKeys(sName);		// Enter User name into the Search table box
		Thread.sleep(3000);																   			// Pause 3 seconds for page update
		ReportText = "23) Searched for " + sName;													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Select HopewiserAdmin from the table
		//*[@id="user-table"]/tbody/tr[1]/td[1]/a/span
		driver.findElement(By.xpath("//tbody/tr[1]/td[1]/a/span")).click();							// Click on hopewiseradmin link (Can we stop this being hard coded?)		
		Thread.sleep(3000);																   			// Pause 3 seconds for page update
		ReportText = "24) " + sName + " found";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Select the Usage tab	
		//*[@id="main"]/div/div/section[2]/article/div/div[1]/ul/li[2]/a
		driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[2]/a")).click(); // Click the Usage tab
		Thread.sleep(3000);																   			// Pause 3 seconds for page update
		
		// Choose the first date option in the Month Filter
		//<select id="filter-bundle-table-Address0"><option value="all">--All--</option><option value="2017-09">2017-09</option></select>
		WebElement Address0 = driver.findElement(By.xpath("//select[@id='filter-bundle-table-Address0']"));
		driver.findElement(By.xpath("//select[@id='filter-bundle-table-Address0']")).sendKeys(Keys.DOWN);	// Click the down arrow key
		driver.findElement(By.xpath("//select[@id='filter-bundle-table-Address0']")).sendKeys(Keys.RETURN); // Click the enter key
		String vdropdown = Address0.getText();														// Get the text from this dropdown
		String replaced0 = vdropdown.replaceAll("--All--[\n\r]","");								// Remove the --All-- text from the dropdown information
		ReportText = "25) Bundle: " + replaced0;													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Choose the uk-rm-paf-internal option in the Dataset Reference Filter
		//<select id="filter-bundle-table-Address1"><option value="all">--All--</option><option value="uk-rm-paf-internal">uk-rm-paf-internal</option></select>	    
		WebElement Address1 = driver.findElement(By.xpath("//select[@id='filter-bundle-table-Address1']"));
		driver.findElement(By.xpath("//select[@id='filter-bundle-table-Address1']")).sendKeys("uk-rm-paf-internal"); // Select PAF dataset from the dropdown
		vdropdown = Address1.getText();																// Get the text from this dropdown
		String replaced1 = vdropdown.replaceAll("--All--[\n\r]","");								// Remove the --All-- text from the dropdown information
		ReportText = "26) Bundle: " + replaced1;													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Choose the last bundle number option in the Bundle Filter
		Select sdropdown = new Select(driver.findElement(By.xpath("//select[@id='filter-bundle-table-Address2']")));  // Identify the dropdown for the list
		List<WebElement> list = sdropdown.getOptions(); 											// Put all Options in the list array
		int numElements = (list.size())-1;															// Find How many items in list (-1 as size starts at 0 not 1)
		WebElement LastItem = (WebElement)list.get(numElements);									// Get last item from the array
		String replaced2 = LastItem.getText();														// Convert web element to string
    	ReportText = "27) Bundle: Items: " + numElements + " Select: " + replaced2;					// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// 7.0 Select the Your Account button <a href="/hostedmgr/" class="ym-button">Your Account</a>
		driver.findElement(By.xpath("//a[@href='/hostedmgr/']")).click(); 							// Click the Your Account button
		Thread.sleep(3000);																			// Pause 3 seconds for page update
		ReportText = "28) Click the Your Account Button";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountDetails21_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the left hand Accounts link <a href="/hostedmgr/acct">Accounts</a>
		driver.findElement(By.xpath("//a[@href='/hostedmgr/acct']")).click();						// Click the Your Account button
    	Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/acct");			// FF Ensure the correct page is displayed in a sensible time
		ReportText = "29) Click the Account link";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountsPage21_";																		// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Search for Hopewiser Test
		//<input id="textfilteraccount-table" type="text" title="Searches each table cell. Supports multi-character (*) and single character (?) wildcards">				
		driver.findElement(By.xpath("//input[@id='textfilteraccount-table']")).sendKeys("Hopewiser Test");  // Enter the user name
		Thread.sleep(3000);																   			// Pause 3 seconds for page update
		ReportText = "30) Searched for Hopewiser Test";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Select Hopewiser Test link from table				
		driver.findElement(By.xpath("//span[contains(.,'Hopewiser Test')]")).click();				// Click the user
		Timeloop.Execute(driver, "/details");														// Ensure the correct page is displayed in a sensible time
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountDetailsPage21_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select Bundle tab
		driver.findElement(By.xpath("//div/div/section[2]/article/div/div/ul/li[4]/a")).click();	// Click Bundle tab
		Thread.sleep(3000);																   			// Pause 3 seconds for page update
		
		// Search for Bundle	
		//<input id="textfilterbundle-table" type="text" title="Searches each table cell. Supports multi-character (*) and single character (?) wildcards">
		driver.findElement(By.xpath("//input[@id='textfilterbundle-table']")).sendKeys(replaced2);	// Enter User name into the Search table box
		Timeloop.Execute(driver, "/bund");															// FF Ensure the correct page is displayed in a sensible time
		ReportText = "31) Searched for " + replaced2;												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Select the bundle number from 3.4 which is stored in replaced2
		driver.findElement(By.xpath("//span[contains(.,'" + replaced2 + "')]")).click();			// Click on bundle link		    			
		Thread.sleep(3000);																   			// Pause 3 seconds for page update
		ReportText = "32) " + replaced2 + " found";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Select the Usage tab
		//*[@id="main"]/div/div/section[2]/article/div/table[1]/tbody/tr[3]/td[4]
		driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[2]/a")).click(); // Click Usage tab
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "BundleUsage21_";																		// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// 4.1 Log Off
		driver.findElement(By.xpath("//a[@href='/hostedmgr/secure/logout']")).click(); 				// Click the Log Off link
		ReportText = "33) Logged off";																// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Close the Browser and kill task (Postcondition)
    	driver.quit();																				// Close the Browser
		ReportText = "34) Closed Browser";															// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console	
    	}
	}