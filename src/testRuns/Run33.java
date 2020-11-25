package testRuns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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
public class Run33 
	{
    public static void main(String[] args) throws Exception 
    	{   	
    	// Launch Chrome (Precondition)
    	String exePath = "C:\\Selenium\\chromedriver.exe";											// Set variable to path where Chrome.exe is stored
    	System.setProperty("webdriver.chrome.driver", exePath);										// Set Path to open Chrome from
    	WebDriver driver = new ChromeDriver();														// Open a new instance of Chrome
		String ReportText = "1) Run33 Chrome Launched";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
        
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"SuperUser");		// Look in the Excel sheet for test data
        
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
			String SS = "LoginPass33_";																// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl.contains(testurl2))												// Test if logon page exists
				{
				ReportText = "3) Login Failed: " + whatiscurrenturl;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginFail33_";															// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			else
				{
				ReportText = "3) Unknown Error: " + whatiscurrenturl;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginError33_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}
		
		// 6.3 <a href="/hostedmgr/acct">Accounts</a>	
    	driver.findElement(By.xpath("//a[@href='/hostedmgr/acct']")).click();						// Click the left hand Accounts link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/acct");			// Ensure the correct page is displayed in a sensible time
		ReportText = "4) Click the Account link";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		String SS = "AccountsPage33_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet41");		// Look in the Excel sheet for test data
		String sAccountName = ExcelUtils.getCellData(1, 1);											// Get Account Name from TestData1.xlsx 
		String sDataset = ExcelUtils.getCellData(1, 5);												// Get Dataset name from TestData1.xlsx 

		// Search for HopewiserQAAccount
		//<input id="textfilteraccount-table" type="text" title="Searches each table cell. Supports multi-character (*) and single character (?) wildcards">				
		driver.findElement(By.xpath("//input[@id='textfilteraccount-table']")).sendKeys(sAccountName); // Enter the company name
		Thread.sleep(3000);																   			// Pause 3 seconds for page update
		ReportText = "5) Searched for " + sAccountName;												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountSearchPage33_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select HopewiserQAAccount link from the list
		//<span style="color:black;background-color:yellow">HopewiserAccountTest</span>   			//*[@id="account-table"]/tbody/tr[26]/td[1]/a/span/span  
		//driver.findElement(By.xpath("//tbody/tr[32]/td[1]/a/span/span")).click();					// Click the First Account in the table	(tr number changes)
		driver.findElement(By.xpath("//span[contains(.,'HopewiserQAAutomation')]")).click();		// Click the First Account in the table (tr number changes)
		Timeloop.Execute(driver, "/details");														// FF Ensure the correct page is displayed in a sensible time
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountDetailsPage33_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select Bundles tab
		//*[@id="main"]/div/div/section[2]/article/div/div[1]/ul/li[4]/a
		driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[4]/a")).click(); // Click Bundles tab
		Timeloop.Execute(driver, "/bund");															// Ensure the correct page is displayed in a sensible time
		ReportText = "6) Select Bundle from table";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Select Bundles Create New Button
		//<a href="/hostedmgr/acct/319/bund/create" class="ym-button ym-small">Create&nbsp;New</a>
		driver.findElement(By.xpath("//a[@class='ym-button ym-small']")).click();  					// Click the Create New Button
		Timeloop.Execute(driver, "/bund/create");													// FF Ensure the correct page is displayed in a sensible time
		ReportText = "7) Create New Button clicked";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "CreateBundlePage33_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		int PlusDays = 8;																			// Set number of days to add to todays date
		// Create Bundle using the appModule CreateBundle_Action
    	ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet41");		// Look in the Excel sheet for test data
		CreateBundle_Action.Execute(driver,PlusDays);												// Create Bundle using the appModule CreateBundle_Action
		
		// Test if Create occurred
		String whatiscurrenturl2 = driver.getCurrentUrl();											// Set variable to the current web address
		String testurlC1 = "https://services-sandbox.hopewiser.com/hostedmgr/acct/459/cost/706/bund";// Set variable to path
		String testurlC2 = "/bund/create";															// Set variable to path
		if (whatiscurrenturl2.equals(testurlC1))													// Test if bund page exists
			{
			ReportText = "8) Create Bundle Passed: " + whatiscurrenturl2;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results		
			SS = "BundlePass33_";																	// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl2.contains(testurlC2))												// Test if create page exists
				{
				ReportText = "8) Create Bundle Failed: " + whatiscurrenturl2;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "CreateBundleFail33_";															// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			else
				{
				ReportText = "8) Unknown Error: " + whatiscurrenturl2;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "CreateBundleError33_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}
    	
    	// 12.3 Select Users tab to refresh the driver page
		//*[@id="main"]/div/div/section[2]/article/div/div[1]/ul/li[4]/a
		driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[4]/a")).click(); // Click users tab
		Timeloop.Execute(driver, "/user");															// FF Ensure the correct page is displayed in a sensible time
		ReportText = "9) Select Users Tab";															// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"SuperUser");		// Look in the Excel sheet for test data
    	int HowManyClicks = 1;																		// Set Variable HowManyClicks to 1.
		// Use 1 Click from the bundle
		SearchAddresses_Action.Execute(driver,HowManyClicks,sDataset);								// Remove 1 click using the appModule SearchAddresses_Action so the New Bundle can be seen in the Bundle Filter
	
		// Find the Bundle Number used (Automated test only)
		// Search for the user that used the click
		// <input id="textfilteruser-table" type="text" title="Searches each table cell. Supports multi-character (*) and single character (?) wildcards">		
		String sUserName = ExcelUtils.getCellData(1, 1);											// Set variable to user name
		driver.findElement(By.xpath("//input[@id='textfilteruser-table']")).sendKeys(sUserName);	// Enter User name into the Search table box
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	ReportText = "10) Searched for " + sUserName;												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "CCUserpage33_";																		// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
    	// Select QAAutomation from the table
		//*[@id="user-table"]/tbody/tr[2]/td[1]/a/span/span
    	driver.findElement(By.xpath("//tbody/tr[2]/td[1]/a/span/span")).click();					// Click on QAAutomation link	
		Timeloop.Execute(driver, "/details");														// FF Ensure the correct page is displayed in a sensible time
    	ReportText = "11) " + sUserName + " found";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "UserDetailsPage33_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the Usage tab	
    	//*[@id="main"]/div/div/section[2]/article/div/div[1]/ul/li[2]/a
    	driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[2]/a")).click(); // Click Usage tab
		Timeloop.Execute(driver, "/usage");															// Ensure the correct page is displayed in a sensible time
		
    	// Select External dataset
    	//<select id="filter-bundle-table-Address1"><option value="all">--All--</option><option value="uk-rm-paf-internal">uk-rm-paf-internal</option></select>
		Select sdataset = new Select(driver.findElement(By.xpath("//select[@id='filter-bundle-table-Address1']")));	// Identify the dropdown for the list
		sdataset.selectByVisibleText(sDataset);														// Select dataset
		
		// Get the last bundle number from the users bundle filter
    	//<select id="filter-bundle-table-Address2"><option value="all">--All--</option><option value="1798">1798</option><option value="1826">1826</option><option value="1851">1851</option><option value="1852">1852</option><option value="1853">1853</option></select>
		Select sdropdown = new Select(driver.findElement(By.xpath("//select[@id='filter-bundle-table-Address2']")));  // Identify the dropdown for the list
		List<WebElement> list = sdropdown.getOptions(); 											// Put all Options in the list array
		int numElements = (list.size())-1;															// Find How many items in list (-1 as size starts at 0 not 1)
		WebElement LastItem = (WebElement)list.get(numElements);									// Get last item from the array
		String replaced2 = LastItem.getText();														// Convert web element to string
    	ReportText = "12) Bundle: Items: " + numElements + " Select: " + replaced2;					// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Select the Bundles link
		//<a href="/hostedmgr/bund">Bundles</a>
    	driver.findElement(By.xpath("//a[@href='/hostedmgr/bund']")).click();						// Click the Bundle number link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/bund");			// Ensure the correct page is displayed in a sensible time
		ReportText = "13) Click the Bundles link";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "BundlePage33_";																		// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
    	// Search for User	
		//*[@id="textfilterbundle-table"]
		driver.findElement(By.xpath("//input[@id='textfilterbundle-table']")).sendKeys(replaced2);	// Enter Bundle Number into the Search table box
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	ReportText = "14) Searched for " + replaced2;												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Search for Bundle to be used for removing clicks
		//*[@id="bundle-table"]/tbody/tr[28]/td[1]/a/span/span
    	try
    		{
    		driver.findElement(By.xpath("//td[1]/a/span/span")).click();							// Click on QAAutomation link	
    		}
    	catch (Exception e)
    		{
			ReportText = "15) " + replaced2 + " doesn't exist";										// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			System.exit(1);																			// Drop out of script
    		}
		Timeloop.Execute(driver, "/details");														// Ensure the correct page is displayed in a sensible time
    	ReportText = "15) " + replaced2 + " found";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "UserDetailsPage33_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the Usage Tab
		//*[@id="main"]/div/div/section[2]/article/div/div[1]/ul/li[2]/a
    	driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[2]/a")).click(); // Click Usage tab
		Timeloop.Execute(driver, "/usage");															// Ensure the correct page is displayed in a sensible time
		ReportText = "16) Usage Tab clicked";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "BundleUsagepage33_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Get Click Usage current value for comparison later
		//*[@id="main"]/div/div/section[2]/article/div/div[2]/div[7]/label[2]
		WebElement td_text1 = driver.findElement(By.xpath("//div/div/section[2]/article/div/div[2]/div[7]/label[2]"));	// Get to the Click Usage value
		String Click_Usage = td_text1.getText();													// Get the text from this cell
		int Click_Usage_Num1 = Integer.parseInt(Click_Usage);										// Convert Click_Usage text value into a number
    	ReportText = "17) Clicks Used: " + Click_Usage;												// Set up report text variable (Should be 1)
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Get Clicks Remaining current value for comparison later
		//*[@id="main"]/div/div/section[2]/article/div/div[2]/div[8]/label[2]
		WebElement td_text2 = driver.findElement(By.xpath("//div/div/section[2]/article/div/div[2]/div[8]/label[2]")); // Get to the Clicks Remaining value
		String Clicks_Remaining = td_text2.getText();												// Get the text from this cell
		int Click_Remaining_Num1 = Integer.parseInt(Clicks_Remaining);								// Convert Clicks_Remaining text value into a number
		float Percent = ((Click_Remaining_Num1 * 95) / 100);										// Calculate how many clicks are needed to change text colour
		HowManyClicks = ((int)Math.ceil(Percent));													// Ensure this variable is a full number and rounded up
    	ReportText = "18) Clicks Remaining: " + Clicks_Remaining + " 95 Percent = " + HowManyClicks;// Set up report text variable
		Output.FilePlusConsole(ReportText);															// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "StartingClicks33_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Use 90 % of Clicks from the bundle
		SearchAddresses_Action.Execute(driver,HowManyClicks,sDataset);								// Remove 'HowManyClicks' clicks using the appModule SearchAddresses_Action
		    	
    	// New Test section missing from the manual tests
		//*[@id="main"]/div/div/section[2]/article/div/div[1]/ul/li[1]/a   //*[@id="main"]/div/div/section[2]/article/div/div[1]/ul/li[2]/a
    	driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[1]/a")).click();	// Click the Details tab to Refresh the Bundles page
    	driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[2]/a")).click();	// Click the Usage tab to Refresh the Bundles page
    	  
		// Get Click Usage current value for comparison
		WebElement td_text3 = driver.findElement(By.xpath("//div/div/section[2]/article/div/div[2]/div[7]/label[2]"));	// Get to second line of the table 4th item
		String Click_Usage2 = td_text3.getText();													// Get the text from this cell
		int Click_Usage_Num2 = Integer.parseInt(Click_Usage2);										// Convert Click_Usage text value into a number
    	ReportText = "19) Clicks Used: " + Click_Usage2;											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		//Get Clicks Remaining current value for comparison
		WebElement td_text4 = driver.findElement(By.xpath("//div/div/section[2]/article/div/div[2]/div[8]/label[2]"));	// Get to second line of the table 5th item
		String Clicks_Remaining2 = td_text4.getText();												// Get the text from this cell
		int Click_Remaining_Num2 = Integer.parseInt(Clicks_Remaining2);								// convert Clicks_Remaining text value into a number
    	ReportText = "20) Clicks Remaining: " + Clicks_Remaining2;									// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		String whatiscurrenturl4 = driver.getCurrentUrl();											// Put current website address in variable.			
		// Test if click was added
		if (Click_Usage_Num1<Click_Usage_Num2)														// Test if Home page exists
			{
	    	ReportText = "21) ClicksReducedPassed: " + whatiscurrenturl4;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "ClicksReducedPass33_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
	    	ReportText = "21) ClicksReducedFailed: " + whatiscurrenturl4;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "ClicksReducedPass33_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			System.exit(1);																			// Drop out of script
			}
		
		int result = (Click_Usage_Num2-Click_Usage_Num1);											// Calculate clicks used
    	ReportText = "22) Clicks used: " + result;													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Test if click was removed
		if (Click_Remaining_Num1>Click_Remaining_Num2)												// Test if Home page exists
			{
	    	ReportText = "23) Clicks Increased Passed: " + whatiscurrenturl4;						// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "ClicksReducedPass33_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
	    	ReportText = "23) Clicks Increased Failed: " + whatiscurrenturl4;						// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "ClicksReducedPass33_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			System.exit(1);																			// Drop out of script
			}

		int result2 = (Click_Remaining_Num2-Click_Remaining_Num1);									// Calculate clicks used
		ReportText = "24) Clicks Remaining Reduced by: " + result2;									// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Bundle Usage page Clicks remaining does not change colour (Should it?)
		// Check colour of the bundle on (Account Cost centre usage page) (not in manual test)
    	driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[1]/a")).click();	// Click the Details tab to Refresh the Bundles page
    	// Select the Cost Centre link
    	// <a title="View Cost Centre Details" href="/hostedmgr/acct/459/cost/706/details">Default</a>
    	driver.findElement(By.xpath("//a[contains(.,'Default')]")).click();							// Click the Cost centre link (Shouldn't be hard coded to default)	
    	// Select the Usage tab
    	// <a href="/hostedmgr/acct/459/cost/706/usage">Usage</a>  //*[@id="main"]/div/div/section[2]/article/div/div[1]/ul/li[2]/a
    	driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[2]/a")).click();	// Click the Usage tab
		// <td class="warnhigh">1</td> <td class="colouractive">ACTIVE</td>  //*[@id="bundle-table"]/tbody/tr[2]/td[8]
		SS = "UsageColourChange33_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
    	ReportText = "25) Check Screenshot manually to see the colour of the current Clicks Remaining, it should be Yellow";	// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
    	// Search for Bundle	
		//*[@id="textfilterbundle-table"]
		driver.findElement(By.xpath("//input[@id='textfilterbundle-table']")).sendKeys(replaced2);	// Enter User name into the Search table box
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update
    	ReportText = "26) Searched for " + replaced2;												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console

		// Find High Warning colour (Need more skills to drag the colour out of the style due to the ; in it.
    	/*try
    		{
    		//<span style="color:black;background-color:yellow">1926</span> //*[@id="bundle-table"]/tbody/tr[8]/td[3]/a/span/span (tr[8] keeps changing)
    		driver.findElement(By.xpath("//tbody/tr[8]td[3]/a/span/span[contains(@style='color:black;background-color:yellow')]"); // Click the Details tab to Refresh the Bundles page
	    	ReportText = "27) Usage High Warning found";											// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
    		}
    	catch (Exception e)
    		{
	    	ReportText = "27) Failure: Usage High Warning not found";								// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			System.exit(1);																			// Drop out of script
    		}*/
		
		// Use the rest of the Clicks from the bundle
		SearchAddresses_Action.Execute(driver,Click_Remaining_Num2,sDataset);						// Remove 'Click_Remaining_Num2' clicks using the appModule SearchAddresses_Action
	
		// Select Bundles tab
		//*[@id="main"]/div/div/section[2]/article/div/div[1]/ul/li[3]/a
		driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[3]/a")).click(); // Click Bundles tab
		Timeloop.Execute(driver, "/bund");															// FF Ensure the correct page is displayed in a sensible time
		ReportText = "27) Select Bundle from table";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
    	// Search for Bundle	
		//*[@id="textfilterbundle-table"]
		driver.findElement(By.xpath("//input[@id='textfilterbundle-table']")).clear();				// Clear the Search table box
		driver.findElement(By.xpath("//input[@id='textfilterbundle-table']")).sendKeys(replaced2);	// Enter Bundle name into the Search table box
    	Thread.sleep(3000);																   			// Pause 3 seconds for page update

    	ReportText = "28) Searched for " + replaced2;												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results 	
		SS = "ClicksRemaining33_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		//*[@id="bundle-table"]/tbody/tr[1]/td[1]/a <a title="View Bundle Details" href="/hostedmgr/acct/459/cost/706/bund/1931/details">1931</a> //*[@id="bundle-table"]/tbody/tr[3]/td[1]/a/span/span
		driver.findElement(By.partialLinkText(replaced2)).click();									// Clear the Search table box
		
		//<td class="colourexpired">EXPIRED</td> //*[@id="bundle-table"]/tbody/tr[11]/td[9] (tr[11]keeps changing) //*[@id="main"]/div/div/section[2]/article/div/div[2]/div[11]/label[2]
		WebElement Status = driver.findElement(By.xpath("//div/div/section[2]/article/div/div[2]/div[11]/label[2]"));	// Find Status
		String StatusVal = Status.getText();														// Get the Status value
    	// Has the Bundle Expired?
		if (StatusVal.contains("EXPIRED"))															// Check if bundle status = Expired in the table
			{
    		ReportText = "29) Bundle used and Expired";												// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			}
		else
			{
    		ReportText = "29) Failure: Expired not found" + StatusVal;								// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			System.exit(1);																			// Drop out of script
			}
		
		SS = "Expired33_";																			// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
    	// Checking that warning emails have been sent needs to be done manually at the moment
    	ReportText = "30) Check if 5% 'AddressServer in the Cloud Plan Expires Soon' email exists"; // Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		ReportText = "31) Check if 'AddressServer in the Cloud Plan has Expired' email exists";		// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// 4.1 Log Off
		driver.findElement(By.xpath("//a[@href='/hostedmgr/secure/logout']")).click(); 				// Click the Log Off link
		ReportText = "32) Logged off";																// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Close the Browser and kill task (Postcondition)
    	driver.quit();																				// Close the Browser
		ReportText = "33) Closed Browser";															// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	}
	}