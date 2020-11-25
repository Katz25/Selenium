package testRuns;

import java.util.List;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import appModule.CreateBundle_Action;
import appModule.Login_Action;
import appModule.Output;
import appModule.ScreenShot_Action;
import appModule.SearchAddresses_Action;
import appModule.Timeloop;

import utility.Constant;
import utility.ExcelUtils;
/*
 * Author: Maxine Flook
 * Selenium tests V1
 */
public class Run41 
	{
	public static void main(String[] args) throws Exception 
		{
		// Variable Section
		int HowManyClicks = 10;																			// How many clicks to remove from the bundle using Search Adresses_Action
		String Dataset = ("uk-rm-paf-internal");														// Set the Dataset to uk-rm-paf-internal
		//String Dataset = ("uk-rm-paf-external");														// if required set the Dataset to uk-rm-paf-external

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// Start the Tomcat web server
		Runtime.getRuntime().exec("C:\\tomcat7\\apache-tomcat-7.0.85\\StartTomcat.bat"); 				// Line which runs the StartTomcat batch file
		Thread.sleep(3000);																				// Pause 3 seconds for page update
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// Launch Chrome (Precondition)		
		String exePath = "C:\\Selenium\\chromedriver.exe";												// Set Variable for path Where Chrome.exe is stored
		System.setProperty("webdriver.chrome.driver", exePath);											// Set Path to open Chrome from
		WebDriver driver = new ChromeDriver();															// Open a new instance of Chrome
		String ReportText = "1) Run41 Chrome Launched";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 															// Print validation text to File and Console
			
		// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)				
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"SuperUser"); 			// Look in the Excel sheet for test data
		
		// Remove all clicks from User: HopewiserQA, Costcentre: default
		try
			{
			SearchAddresses_Action.Execute(driver,HowManyClicks,Dataset);
    		ReportText = "2) Removed clicks";															// Set up report text variable
    		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
			}
    	catch (Exception e)
			{
    		ReportText = "2) All clicks have been removed from User: HopewiserQA, Costcentre: default";	// Set up report text variable
    		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    		driver.quit();																				// Close the Browser
			}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
       	// Open the login website using the Url to create a bundle
		WebDriver driver2 = new ChromeDriver();															// Open a new instance of Chrome
		driver2.get(Constant.URL);																		// Open the login website using the Url
		ReportText = "3) " + Constant.URL + " Website Opened";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 															// Print validation text to File and Console
    			
		// 4.0 Login using the appModule Login_Action
		Login_Action.Execute(driver2);																	// Login using the appModule Login_Action
		
		// Test if Login occurred
		String whatiscurrenturl = driver2.getCurrentUrl();												// Set variable to the current web address
		String testurl1 = "https://services-sandbox.hopewiser.com/hostedmgr/home";						// Set Variable to path
		String testurl2 = "https://services-sandbox.hopewiser.com/hostedmgr/logon?error=true";			// Set Variable to path
		if (whatiscurrenturl.contains(testurl1))														// Test if Home page exists
			{
			ReportText = "4) Login Passed: " + whatiscurrenturl;										// Set up report text variable
			Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "LoginPass32_";																	// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver2, SS);														// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl.contains(testurl2))													// Test if logon page exists
				{
				ReportText = "4) Login Failed: " + whatiscurrenturl;									// Set up report text variable
				Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginFail32_";																// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver2, SS);													// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																			// Drop out of script
				}
			else
				{
				ReportText = "4) Unknown Error: " + whatiscurrenturl;									// Set up report text variable
				Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginError32_";															// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver2, SS);													// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																			// Drop out of script
				}
			}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Create a bundle
		// 6.3 <a href="/hostedmgr/acct">Accounts</a>	
    	driver2.findElement(By.xpath("//a[@href='/hostedmgr/acct']")).click();							// Click the left hand Accounts link
		Timeloop.Execute(driver2, "https://services-sandbox.hopewiser.com/hostedmgr/acct");				// Ensure the correct page is displayed in a sensible time
		ReportText = "5) Click the Account link";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 															// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		String SS = "AccountsPage32_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver2, SS);															// Create Screenshot using appModule.ScreenShot_Action
		
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet40");			// Look in the Excel sheet for test data
		String sAccountName = ExcelUtils.getCellData(1, 1);												// Get Account Name from TestData1.xlsx 
		//String sDataset = ExcelUtils.getCellData(1, 5);												// Get Dataset name from TestData1.xlsx 

		// Search for HopewiserQAAutomation
		//<input id="textfilteraccount-table" type="text" title="Searches each table cell. Supports multi-character (*) and single character (?) wildcards">				
		driver2.findElement(By.xpath("//input[@id='textfilteraccount-table']")).sendKeys(sAccountName); 	// Enter the company name
		Thread.sleep(3000);																   				// Pause 3 seconds for page update
		ReportText = "6) Searched for " + sAccountName;													// Set up report text variable
		Output.FilePlusConsole(ReportText); 															// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountSearchPage32_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver2, SS);															// Create Screenshot using appModule.ScreenShot_Action
		
		// Select HopewiserQAAutomation link from the list
		//<span style="color:black;background-color:yellow">HopewiserAccountTest</span>   				//*[@id="account-table"]/tbody/tr[32]/td[1]/a/span/span 
		driver2.findElement(By.xpath("//span[contains(.,'HopewiserQAAutomation')]")).click();			// Click the HopewiserQAAutomation Account in the table
		Timeloop.Execute(driver2, "/details");															// Ensure the correct page is displayed in a sensible time
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountDetailsPage32_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver2, SS);															// Create Screenshot using appModule.ScreenShot_Action
		
		// Select Bundles tab
		//*[@id="main"]/div/div/section[2]/article/div/div[1]/ul/li[4]/a
		driver2.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[4]/a")).click(); 	// Click Bundles tab
		Timeloop.Execute(driver2, "/bund");																// Ensure the correct page is displayed in a sensible time
		ReportText = "7) Select Bundle from table";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 															// Print validation text to File and Console
		
		// Select Bundles Create New Button
		//<a href="/hostedmgr/acct/319/bund/create" class="ym-button ym-small">Create&nbsp;New</a>
		driver2.findElement(By.xpath("//a[@class='ym-button ym-small']")).click();  						// Click the Create New Button
		Timeloop.Execute(driver2, "/bund/create");														// Ensure the correct page is displayed in a sensible time
		ReportText = "8) Create New Button clicked";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 															// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "CreateBundlePage32_";																		// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver2, SS);															// Create Screenshot using appModule.ScreenShot_Action
		
		int PlusDays = 60;																				// Set number of days to add to todays date
		// Create Bundle using the appModule CreateBundle_Action
    	ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet40");			// Look in the Excel sheet for test data
		CreateBundle_Action.Execute(driver2,PlusDays);													// Create Bundle using the appModule CreateBundle_Action
		
		// Test if Create occurred
		String whatiscurrenturl2 = driver2.getCurrentUrl();												// Set variable to the current web address
		String testurlC1 = "https://services-sandbox.hopewiser.com/hostedmgr/acct/459/cost/706/bund";	// Set variable to path
		String testurlC2 = "/bund/create";																// Set variable to path
		if (whatiscurrenturl2.equals(testurlC1))														// Test if bund page exists
			{
			ReportText = "9) Create Bundle Passed: " + whatiscurrenturl2;								// Set up report text variable
			Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results		
			SS = "BundlePass32_";																		// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver2, SS);														// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl2.contains(testurlC2))													// Test if create page exists
				{	
				ReportText = "9) Create Bundle Failed: " + whatiscurrenturl2;							// Set up report text variable
				Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "CreateBundleFail32_";																// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver2, SS);													// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																			// Drop out of script
				}
			else
				{
				ReportText = "9) Unknown Error: " + whatiscurrenturl2;									// Set up report text variable
				Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "CreateBundleError32_";															// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver2, SS);													// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																			// Drop out of script
				}
			}
    	
    	// 12.3 Select Users tab to refresh the driver page
		//*[@id="main"]/div/div/section[2]/article/div/div[1]/ul/li[4]/a
		driver2.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[4]/a")).click(); 	// Click users tab
		Timeloop.Execute(driver2, "/user");																// Ensure the correct page is displayed in a sensible time
		ReportText = "10) Select Users Tab";															// Set up report text variable
		Output.FilePlusConsole(ReportText); 															// Print validation text to File and Console		
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// Open the login website using the Url to test if selected addresses can be found
		String API = Constant.PCIP+":8080/temp/TestSampleAutoc.html";
		driver2.get(API);																				// Open the AutoComplete Javascript Client Demo Page using the Url
		ReportText = "11) " + API + " Website Opened";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 															// Print validation text to File and Console			
		Timeloop.Execute(driver2, "/temp/TestSampleAutoc.html");										// Ensure the correct page is displayed in a sensible time
		
		// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)				
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData1,"Sheet43"); 			// Look in the Excel sheet for test data
		
		for (int i=12; i<=64; i++)																		// Start loop at 12, finish loop at 61 and increase i by 1 each loop
			{
			// This line gets the values from the test data Excel sheet, passing parameters (Row number, Column number) to utility.ExcelUtils.getCellData method.		
			String sPostCode = ExcelUtils.getCellData(i, 0);											// Select test data
			int j = 1;
			ReportText = " ";																			// Set up report text variable
			Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
			ReportText = "Postcode: "+sPostCode;														// Set up report text variable
			Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
			
			//<input id="inputaddr" size="70">
			driver2.findElement(By.xpath("//input[@id='inputaddr']")).clear();							// Empty the text field
			driver2.findElement(By.xpath("//input[@id='inputaddr']")).sendKeys(sPostCode);				// Enter address into text field
			Thread.sleep(2000);																			// Pause 2 seconds for page update
			SS = "AddressLookup41_"+i;																	// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver2, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		    try
    			{
	    		//<ul id="ui-id-1" tabindex="0" class="ui-menu ui-widget ui-widget-content ui-autocomplete ui-front" style="display: block; top: 396.25px; left: 11px; width: 521px;"><li class="ui-menu-item"><a id="ui-id-84" tabindex="-1" class="ui-menu-item-wrapper">Kestrel Drive,Irlam,MANCHESTER,M44 6LX... <i>(17 more)</i></a></li></ul>
		    	//Select dropdown3 = new Select(driver2.findElement(By.xpath("//li/a/i"))); 			// Identify the first dropdown item in the list
		    	List<WebElement> list = (driver2.findElements(By.xpath("//li/a")));						// Get the full list of items in an array
		    	Iterator<WebElement> step = list.iterator();											// Select the each item in the array
		    	while(step.hasNext())
		    		{
		    		WebElement Item=step.next();														// increase the step variable
		    		String Laddress=Item.getText();														// Get the text from the item iin the array
		    		ReportText = j +")Address: " + Laddress;											// Set up report text variable
			    	Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
					j = j+1;
					}
		    	}
		    catch (Exception e)
		    	{
	    		ReportText = "Number of Addresses: 0 Found";											// Set up report text variable
	    		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		    	}
			}				

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// Remove all clicks from User: HopewiserQA, Costcentre: default
		try
			{
			SearchAddresses_Action.Execute(driver,HowManyClicks,Dataset);
    		ReportText = "12) Removed clicks";															// Set up report text variable
    		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
			}
    	catch (Exception e)
			{
    		ReportText = "12) All clicks have been removed from User: HopewiserQA, Costcentre: default";// Set up report text variable
    		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    		driver.quit();																				// Close the Browser
			}

		// Close the Browser and kill task (Postcondition)	
		driver2.quit();																					// Close the Browser
		ReportText = "13) Closed Browser";																// Set up report text variable
		Output.FilePlusConsole(ReportText); 															// Print validation text to File and Console						
		}
	}