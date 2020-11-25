package testRuns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import appModule.CreatePlan_Action;
import appModule.Delete_Action;
import appModule.Login_Action;
import appModule.Output;
import appModule.ScreenShot_Action;
import appModule.Timeloop;

import pageObject.CreateDataset;

import utility.Constant;
import utility.ExcelUtils;
/*
 * Author: Maxine Flook
 * Selenium tests V1
 * Test currently needs to login with the superusers password to see all of the datasets features which should also be available on Hopewiser Admin Users but arn't.
 */
public class Run25 
	{
    public static void main(String[] args) throws Exception 
    	{   	
    	// Launch Chrome (Precondition)
    	String exePath = "C:\\Selenium\\chromedriver.exe";										// Set variable for path where Chrome.exe is stored
    	System.setProperty("webdriver.chrome.driver", exePath);									// Set Path to open Chrome from
    	WebDriver driver = new ChromeDriver();													// Open a new instance of Chrome
		String ReportText = "1) Run25 Chrome Launched";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
        
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"SuperUser");	// Look in the Excel sheet for test data
        
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
			String SS = "LoginPass25_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl.contains(testurl2))											// Test if logon page exists
				{
				ReportText = "3) Login Failed: " + whatiscurrenturl;							// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginFail25_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				}
			else
				{
				ReportText = "3) Unknown Error: " + whatiscurrenturl;							// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginError25_";													// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				}
			}				

        // 9.1 Select the account link				
		driver.findElement(By.xpath("//a[@href='/hostedmgr/acct']")).click();					// Click the account link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/acct");		// Ensure the correct page is displayed in a sensible time
				
		// Search for Hopewiser Test
		driver.findElement(By.xpath("//input[@id='textfilteraccount-table']")).sendKeys("Hopewiser Test"); // Enter the user name
		Thread.sleep(3000);																   		// Pause 3 seconds for page update
		ReportText = "4) Searched for Hopewiser Test";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// Select Hopewiser Test from the table					
		driver.findElement(By.xpath("//span[contains(.,'Hopewiser Test')]")).click();			// Click on Hopewiser Test link	
		Timeloop.Execute(driver, "/details");													// Ensure the correct page is displayed in a sensible time
	
		// Select Bundles tab
		//*[@id="main"]/div/div/section[2]/article/div/div[1]/ul/li[4]/a
		driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[4]/a")).click(); // Click Bundles tab
		Timeloop.Execute(driver, "/bund");														// Ensure the correct page is displayed in a sensible time
		ReportText = "5) Select Bundle from table";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// Select the most recent Bundle 
		//*[@id="bundle-table"]/tbody/tr[1]/td[1]/a
		driver.findElement(By.xpath("//tbody/tr[1]/td[1]/a")).click();  						// Click Users tab
		Timeloop.Execute(driver, "/details");													// Ensure the correct page is displayed in a sensible time
		whatiscurrenturl = driver.getCurrentUrl();												// Set variable to the current web address
		ReportText = "6) Bundle Opened: " + whatiscurrenturl;									// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		String SS = "OpenBundle25_";															// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the Cost Centre link
		//<a title="View Cost Centre Details" href="/hostedmgr/acct/248/cost/360/details">Cost Centre Test</a>		//*[@id="main"]/div/div/section[2]/article/div/div[2]/div[4]/label[2]/a
		driver.findElement(By.partialLinkText("costcentre")).click();  							// Click Cost Centre Test link
		Timeloop.Execute(driver, "/details");													// Ensure the correct page is displayed in a sensible time
		whatiscurrenturl = driver.getCurrentUrl();												// Set variable to the current web address
		ReportText = "7) Cost Centre Opened: " + whatiscurrenturl;								// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "OpenCostCentre25_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Select Users tab from Cost Centre page
		//*[@id="main"]/div/div/section[2]/article/div/div[1]/ul/li[4]/a
		driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[4]/a")).click(); // Click Users tab
		Timeloop.Execute(driver, "/user");														// Ensure the correct page is displayed in a sensible time
		whatiscurrenturl = driver.getCurrentUrl();												// Set variable to the current web address
		ReportText = "8) Users Opened: " + whatiscurrenturl;									// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "OpenUsersTab25_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Search for User in Account Users Table
		// <input id="textfilteruser-table" type="text" title="Searches each table cell. Supports multi-character (*) and single character (?) wildcards">
		driver.findElement(By.xpath("//input[@id='textfilteruser-table']")).sendKeys("AutomationUser");  // Enter the user name
		Thread.sleep(3000);																   		// Pause 3 seconds for page update
		ReportText = "9) Searched for User";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// Select User link from table
		//*[@id="user-table"]/tbody/tr[1]/td[1]/a/span/span  //<span style="color:black;background-color:yellow">HopewiseradminQA</span>  //*[@id="user-table"]/tbody/tr[2]/td[1]/a/span/span
		driver.findElement(By.xpath("//tbody/tr[1]/td[1]/a/span/span")).click(); 				// Click User link
		Timeloop.Execute(driver, "/details");													// Ensure the correct page is displayed in a sensible time
		
		// Select Hopewiser Test from the table
		driver.findElement(By.partialLinkText("Hopewiser Test")).click();  						// Click Hopewiser Test link
		Timeloop.Execute(driver, "/details");													// Ensure the correct page is displayed in a sensible time
		whatiscurrenturl = driver.getCurrentUrl();												// Set variable to the current web address
		ReportText = "10) Account Opened: " + whatiscurrenturl;									// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "OpenAccount25_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action

		// 13.0 Select the Datasets link
		// <a href="/hostedmgr/dataset">Datasets</a> 		//*[@id="t-maf"]/em/a
		driver.findElement(By.xpath("//a[@href='/hostedmgr/dataset']")).click();  				// Click the Datasets link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/dataset");	// Ensure the correct page is displayed in a sensible time
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "DatasetsPage25_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Select Create New button
		// <a href="/hostedmgr/dataset/create" class="ym-button">Create&nbsp;New</a>
		driver.findElement(By.xpath("//a[@href='/hostedmgr/dataset/create']")).click();  		// Click the Create New Button
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/dataset/create"); // Ensure the correct page is displayed in a sensible time
		
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet31");	// Look in the Excel sheet for test data
		String sMAF = ExcelUtils.getCellData(1, 1);												// Get data from spreadsheet
		String sDataset = ExcelUtils.getCellData(1, 3);											// Get data from spreadsheet
		String sDescription = ExcelUtils.getCellData(1, 2);										// Get data from spreadsheet
    	
		// Enter MasterAddessFile Name in the Name box
		CreateDataset.txtbx_Name(driver).sendKeys(sMAF);  										// Enter the name
		// Set Dataset field
		new Select(CreateDataset.cbobx_Type(driver)).selectByVisibleText(sDataset);   			// Select PAF dataset (Address) from the dropdown
		// Enter Description in the Description box
		CreateDataset.txtbx_Description(driver).sendKeys(sDescription);  						// Enter the description
		SS = "CreateDatasetPage25_";															// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the Create button
		CreateDataset.btn_CreateBundle(driver).click();  										// Click the Create Button
    	Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/dataset");	// Ensure the correct page is displayed in a sensible time
		// Is an error displayed
		try
			{
			driver.findElement(By.xpath("//span[@id='name.errors']")).isDisplayed();			// Check for the Error text
			ReportText = "11) Fail - This Name is already in use."; 							// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			System.exit(1);																		// Drop out of script
			}
		catch (Exception e)
			{
			ReportText = "11) New MAF Reference created ";										// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console	
			}		
		SS = "ACDatasetPage25_";																// Create variable to help name the Screenshot (After Create Dataset Page)
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// 13.1 Select the Datasets link
		driver.findElement(By.xpath("//a[@href='/hostedmgr/dataset']")).click();  				// Click the Datasets link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/dataset");	// Ensure the correct page is displayed in a sensible time
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "DatasetsPage25_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Select newly created link from the Datasets page
		driver.findElement(By.partialLinkText(sMAF)).click();  									// Click new MAF link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/dataset/details/"); // Ensure the correct page is displayed in a sensible time
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "DatasetsDetailsPage25_";															// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action

		// Select the Edit Button from the Datasets Details page
		// <a href="/hostedmgr/dataset/edit/51" class="ym-button">Edit</a>
		driver.findElement(By.xpath("//a[contains(.,'Edit')]")).click();						// Click the Edit Button
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/dataset/edit/");	// Ensure the correct page is displayed in a sensible time
		ReportText = "12) Edit button selected ";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// Change MasterAddessFile Name in the Name box
		// <input id="name" name="name" required="required" type="text" value="">
		driver.findElement(By.xpath("//input[@id='name']")).clear();  							// Clear the name field
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys(sMAF + " Edit");  			// Edit the name
		
		// Select the Save button
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "EditDatasetPage25_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		// <input type="submit" class="ym-button ym-danger" value="Save">
		driver.findElement(By.xpath("//input[@value='Save']")).click();  						// Click the Save Button
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/dataset");	// Ensure the correct page is displayed in a sensible time
		ReportText = "13) Save button selected ";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// 13.2 Select the Datasets link
		driver.findElement(By.xpath("//a[@href='/hostedmgr/dataset']")).click();  				// Click the Datasets link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/dataset");	// Ensure the correct page is displayed in a sensible time
		
		// Select the newly created link from the Datasets page
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "DatasetsPage25_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		driver.findElement(By.partialLinkText(sMAF)).click();  									// Click the new MAF link
		Thread.sleep(3000);																   		// Pause 3 seconds for page update
		
		// Select the Delete Button from the Datasets Details page
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "DatasetsDetailsPage25_";															// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		//<a href="/hostedmgr/dataset/delete/51" class="ym-button">Delete</a>
		driver.findElement(By.xpath("//a[contains(.,'Delete')]")).click();						// Click the Delete Button
		Thread.sleep(3000);																   		// Pause 3 seconds for page update
		ReportText = "14) Delete button selected ";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// Select the Delete Button from the Delete Datasets page
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "DeleteDatasetsPage25_";															// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		// <input type="submit" class="ym-button ym-danger" value="Delete">
		driver.findElement(By.xpath("//input[@value='Delete']")).click();  						// Click the Delete Button
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/dataset");	// Ensure the correct page is displayed in a sensible time	
		ReportText = "15) Delete button selected";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "DatasetsPage25_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action*/
		
		// 14.0 Select the Plans link
		driver.findElement(By.xpath("//a[@href='/hostedmgr/plan']")).click();  					// Click the Plans link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/plan"); 		// Ensure the correct page is displayed in a sensible time
		ReportText = "16) Plans link selected";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "PlansPage25_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Select the Create New Button
		//<a href="/hostedmgr/plan/create" class="ym-button">Create&nbsp;New</a>
		driver.findElement(By.xpath("//a[@class='ym-button']")).click();  						// Click the Create New Button
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/plan/create"); // Ensure the correct page is displayed in a sensible time
		ReportText = "17) Create New Button clicked";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "CreatePlanPage25_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet37");	// Look in the Excel sheet for test data
		
		// Create a Plan using the appModule CreatePlan_Action
		CreatePlan_Action.Execute(driver, 8);													// Create a Plan using the appModule CreatePlan_Action
		
		// Test if Create occurred
		String whatiscurrenturl2 = driver.getCurrentUrl();										// Set variable to the current web address
		String testurl3 = "https://services-sandbox.hopewiser.com/hostedmgr/plan";				// Set variable to path
		String testurl4 = "https://services-sandbox.hopewiser.com/hostedmgr/plan/create";		// Set variable to path
		if (whatiscurrenturl2.equals(testurl4))													// Test if Create page exists
			{
			ReportText = "18) Create Plan Failed: " + whatiscurrenturl2;						// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "CreatePlanFail25_";															// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
			System.exit(1);																		// Drop out of script
			}
		else
			{	
			if (whatiscurrenturl2.equals(testurl3))												// Test if Plan page exists
				{
				ReportText = "18) Create Plan Passed: " + whatiscurrenturl2;					// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "CreatePlanPass25_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				}
			else
				{
				ReportText = "18) Unknown Error: " + whatiscurrenturl2;							// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "CreatePlanError25_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			}
				
		// 14.1 Select the Plans link
		driver.findElement(By.xpath("//a[@href='/hostedmgr/plan']")).click();  					// Click the Plans link
    	Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/plan");		// FF Ensure the correct page is displayed in a sensible time
		ReportText = "19) Plans link selected";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "PlansPage25_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		
		// Search for new Plan
		// Select HopewiserPlan from the table					
		driver.findElement(By.xpath("//a[contains(.,'HopewiserPlan')]")).click();				// Click on HopewiserPlan link	
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/plan");		// Ensure the correct page is displayed in a sensible time
		ReportText = "20) HopewiserPlan selected";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
				
		// Select the Edit Button from the Datasets Details page
		// <a href="/hostedmgr/plan/edit/63" class="ym-button">Edit</a>
		driver.findElement(By.xpath("//a[contains(.,'Edit')]")).click();						// Click the Edit Button
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/plan/edit/"); // Ensure the correct page is displayed in a sensible time
		ReportText = "21) Edit button selected ";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// Change Plan File Name in the Name box
		// <input id="name" name="name" required="required" type="text" value="HopewiserPlan">		
		WebElement TxtBoxContent = driver.findElement(By.xpath("//input[@id='name']"));  		// Setup getText
		String Txt = TxtBoxContent.getAttribute("value");										// Get the text in the field
		if ("HopewiserPlan".equals(Txt))														// Check the text matches the expected
			{
			ReportText = "22) Name check passed: " + Txt;										// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			}
		else
			{
			ReportText = "22) Name check failed: " + Txt;										// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			}	
		driver.findElement(By.xpath("//input[@id='name']")).clear();  							// Clear the Plan name field
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("HopewiserPlanEdit"); 		// Edit the name (Should not be hardcoded)
		
		//<textarea id="description" name="description" rows="6">Test Plan for Run25</textarea>
		TxtBoxContent = driver.findElement(By.xpath("//textarea[@id='description']"));      
		Txt = TxtBoxContent.getText();															// Get the text in the field
		if("Test Plan for Run25".equals(Txt))													// Check the text matches the expected
			{
			ReportText = "23) Description check passed: " + Txt;								// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			}
		else
			{
			ReportText = "23) Description check failed: " + Txt;								// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			}	
		driver.findElement(By.xpath("//textarea[@id='description']")).clear();  				// Clear the plan description field
		driver.findElement(By.xpath("//textarea[@id='description']")).sendKeys("Edited Test Plan for Run25");  // Enter the plan description
				
		//*[@id="plan"]/div/div[3]/label[2] 	<div class="ym-fbox ym-fbox-text"> <label>Dataset:</label> <label>UK Postal Address File</label>
		TxtBoxContent = driver.findElement(By.xpath("//div/div[3]/label[2]"));					// Setup getText
		Txt = TxtBoxContent.getText(); 															// Get the text in the field (Known fail work on later)	
		//Txt = TxtBoxContent.getAttribute("label"); 											// Get the text in the field (Known fail work on later)	
		if ("UK Postal Address File".equals(Txt))												// Check the text matches the expected
			{
			ReportText = "24) Dataset check passed: " + Txt;									// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			}
		else
			{
			ReportText = "24) Dataset check failed: " + Txt;									// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			}	
		
		//*[@id="plan"]/div/div[4]/label[2] <div class="ym-fbox ym-fbox-text"><label>Dataset Type (Service):</label<label>Address</label>    </div> //*[@id="plan"]/div/div[4]/label[2]
		TxtBoxContent = driver.findElement(By.xpath("//div/div[4]/label[2]"));					// Setup getText
		Txt = TxtBoxContent.getText();															// Get the text in the field (Known fail work on later)
		//Txt = TxtBoxContent.getAttribute("label");											// Get the text in the field (Known fail work on later)		
		if("Address".equals(Txt))																// Check the text matches the expected
			{
			ReportText = "25) Dataset Reference check passed: " + Txt;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			}
		else
			{
			ReportText = "25) Dataset Reference check failed: " + Txt;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			}	
		
		/*// duration can not be read by Selenium. value = ""
		//<input id="duration" name="duration" type="text" value=""> From Here
		String Months = "12";																	// Set up duration variable
		TxtBoxContent = driver.findElement(By.xpath("//input[@id='duration']"));				// Setup getText
		Txt = TxtBoxContent.getText();															// Get the text in the field
		if(Months.equals(Txt))																	// Check the text matches the expected (Fails No value is displayed in Txt)
			{
			ReportText = "26) Duration check passed: " + Txt;									// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			}
		else
			{
			ReportText = "26) Duration check failed: " + Txt;									// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			}	
		
		// AvailStart and AvailEnd can not be read by Selenium. value = ""
		//<input id="availstart" name="availstart" max="2018-10-09" required="required" type="date" value="2017-10-09">
		int today = Integer.parseInt(DateUtil.getDate());										// Get todays date
		TxtBoxContent = driver.findElement(By.xpath("//input[@id='availstart']"));				// Setup getText
		Txt = TxtBoxContent.getText();															// Get the text in the field
		if(Months.equals(Txt))																	// Check the text matches the expected (Fails No value is displayed in Txt)
			{
			ReportText = "27) Availablity Start check passed: " + Txt + today;					// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			}
		else
			{
			ReportText = "27) Availablity Start check failed: " + Txt + today;					// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			}
				
		//<input id="availend" name="availend" min="2017-10-09" type="date" value="2018-10-09">	
		int NextYear = today+1;																	// Get todays date + 1 year
		TxtBoxContent = driver.findElement(By.xpath("//input[@id='availend']"));				// Setup getText
		Txt = TxtBoxContent.getText();															// Get the text in the field
		if(Months.equals(Txt))																	// Check the text matches the expected (Fails No value is displayed in Txt)
			{
			ReportText = "28) Availablity End check passed: " + Txt + NextYear;					// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			}
		else
			{
			ReportText = "28) Availablity End check failed: " + Txt + NextYear;					// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			}	*/
		
		// <textarea id="pricing" name="pricing" rows="6"></textarea> To Here
		String Pricing = "vendors=Hopewiser+Royal Mail,500=30+5,1000=40+10,2500=75+25,5000=135+50,10000=250+1"; // Setup Pricing Equations todays date
		TxtBoxContent = driver.findElement(By.xpath("//textarea[@id='pricing']"));
		Txt = TxtBoxContent.getText();															// Get the text in the Pricing field
		if(Pricing.equals(Txt))																	// Check the text matches the expected
			{
			ReportText = "29) Pricing check passed: " + Txt;									// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			}
		else
			{
			ReportText = "29) Pricing check failed: " + Txt;									// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			}
		
		// Select the Save button
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "EditDatasetPage25_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
		// <input type="submit" class="ym-button ym-danger" value="Save">
		driver.findElement(By.xpath("//input[@value='Save']")).click();  						// Click the Save Button
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/plan");		// Ensure the correct page is displayed in a sensible time
		// Test if Save occurred
		String whatiscurrenturl3 = driver.getCurrentUrl();										// Set variable to the current web address
		String testurl5 = "https://services-sandbox.hopewiser.com/hostedmgr/plan";				// Set Variable to path
		String testurl6 = "https://services-sandbox.hopewiser.com/hostedmgr/plan/edit";			// Set Variable to path
		if (whatiscurrenturl3.contains(testurl6))												// Test if Plan page exists
			{
			ReportText = "30) Save Failed: " + whatiscurrenturl3;								// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "SaveFail25_";																	// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
			System.exit(1);																		// Drop out of script
			}
		else
			{	
			if (whatiscurrenturl3.contains(testurl5))											// Test if edit page exists
				{
				ReportText = "30) Save Passed: " + whatiscurrenturl3;							// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "SavePass25_";																// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				}
			else
				{
				ReportText = "30) Unknown Error: " + whatiscurrenturl3;							// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "SaveError25_";															// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			}
		
		// 14.2 Select the Plans link
		driver.findElement(By.xpath("//a[@href='/hostedmgr/plan']")).click();  					// Click the Plans link
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/plan"); 		// Ensure the correct page is displayed in a sensible time
		ReportText = "31) Plans link selected";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "PlansPage25_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action

		// Search for new Plan
		// Select Hopewiser Test from the table					
		driver.findElement(By.xpath("//a[contains(.,'HopewiserPlanEdit')]")).click();			// Click on HopewiserPlan link	
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/plan/details/"); // Ensure the correct page is displayed in a sensible time
		ReportText = "32) HopewiserPlan selected";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// 14.2 Delete the required User	
		Delete_Action.Execute(driver);															// Delete the required User	the appModule Delete_Action
			
		// Test if delete occurred
		String whatiscurrenturlD = driver.getCurrentUrl();										// Set variable to the current web address
		String testurlD1 = "https://services-sandbox.hopewiser.com/hostedmgr/plan";				// Set variable to path
		String testurlD2 = "https://services-sandbox.hopewiser.com/hostedmgr/plan/delete/";		// Set variable to path
		if (whatiscurrenturlD.contains(testurlD2))												// Test if home page exists
			{
			ReportText = "33) Delete Failed: " + whatiscurrenturlD;								// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			SS = "DeleteFail25_";																// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturlD.contains(testurlD1))											// Test if account page exists
				{	
				ReportText = "33) Delete Passed: " + whatiscurrenturlD;							// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "DeletePass25_";															// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				}
			else
				{
				ReportText = "33) Unknown Error: " + whatiscurrenturlD;							// Set up report text variable
				Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "DeleteError25_";															// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);											// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																	// Drop out of script
				}
			}
				
		// 4.1 Log Off
		driver.findElement(By.xpath("//a[@href='/hostedmgr/secure/logout']")).click(); 			// Click the Log Off link
		ReportText = "34) Logged off";															// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// Close the Browser and kill task (Postcondition)
    	driver.quit();																			// Close the Browser
		ReportText = "35) Closed Browser";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
    	}
	}