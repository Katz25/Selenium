package appModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import utility.Constant;
import utility.ExcelUtils;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class SearchAddresses_Action 
	{	
	public static void Execute(WebDriver driver, int HowManyClicks , String Dataset) throws Exception
		{		
		// Launch Chrome (Precondition)
    	String exePath = "C:\\Selenium\\chromedriver.exe";											// Set variable to path where Chrome.exe is stored
    	System.setProperty("webdriver.chrome.driver", exePath);										// Set Path to open Chrome from
    	WebDriver Mimic = new ChromeDriver();														// Open a new instance of Chrome
    	String ReportText = "A1) Another Chrome Launched";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	
        // Use the Fast Address Mimic url  	
    	String searchURL = "https://addrsvr2.hopewiser.com/address-search/";						// Set variable to path
		String sName = ExcelUtils.getCellData(1, 1);												// Set variable for user name	
    	String password = Constant.Password;														// Set variable to password via the utility constant script
    	Mimic.get(searchURL);																		// Open the login website using the Url
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
    	ReportText = "A2) " + searchURL + ", Address Website Opened ";								// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	
    	// Sign In to the Fast Address Mimic
    	Mimic.findElement(By.xpath("//input[@id='username']")).sendKeys(sName); 					// Enter the User Name
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
    	
    	Mimic.findElement(By.xpath("//input[@id='password']")).sendKeys(password); 					// Enter the Password
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
    	
    	// Select the Sign in button
    	// <button type="button" id="btnLogon" style="width:8em" onclick="btnLogon_onclick()">Sign&nbsp;In</button> //*[@id="btnLogon"]
    	Mimic.findElement(By.xpath("//button[@id='btnLogon']")).click();  							// Click Sign In button
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
    	ReportText = "A3) Signed In as " + sName;													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    	
    	// Set Dataset field
    	//<select id="dataset" onchange="dataset_onchange()"><option value="">Choose a Dataset...</option><option value="uk-rm-paf-external">uk-rm-paf-external</option><option value="uk-rm-paf-internal">uk-rm-paf-internal</option></select>
		Select sdataset = new Select(Mimic.findElement(By.xpath("//select[@id='dataset']")));   	// Select PAF dataset from the dropdown
		sdataset.selectByVisibleText(Dataset);														// Select dataset
	    Thread.sleep(3000);																			// Pause 3 seconds for page update
    	
    	// Search for a address with house number and postcode
    	Mimic.findElement(By.xpath("//input[@id='input']")).sendKeys("6,m337ug");  					// Enter the Premises number and the postcode
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		String SS = "AddressSearchPage_";															// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(Mimic, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		int Cent = 100;
		for (int i = 0; i < HowManyClicks; i++)
			{
			try
				{
				// Find the address
				Mimic.findElement(By.xpath("//button[contains(.,'Find Address')]")).click(); 		// Click Find Address button
				Thread.sleep(2000);																	// Pause 2 seconds for page update
				// Select the address
				Mimic.findElement(By.xpath("//button[contains(.,'Select')]")).click(); 				// Click Select button
				Thread.sleep(2000);																	// Pause 2 seconds for page update
				if (Cent == i)
					{		   
					ReportText = "F) Clicks done and run out: " + i;								// Set up report text variable
					Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
					Cent = Cent+100;																// increase Cent by 100
				
					/*// Refresh original browser page
					//*[@id="main"]/div/div/section[2]/article/div/div[1]/ul/li[1]/a   //*[@id="main"]/div/div/section[2]/article/div/div[1]/ul/li[2]/a
		    		driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[1]/a")).click();	// Click the Details tab to Refresh the Bundles page
		    		driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[2]/a")).click();	// Click the Usage tab to Refresh the Bundles page*/
					}
				}
			catch (Exception e)
				{
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "PossMatchesErrorPage_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(Mimic, SS);												// Create Screenshot using appModule.ScreenShot_Action
				ReportText = "F) Clicks done: " + i;												// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				Mimic.findElement(By.xpath("//button[contains(.,'Cancel')]")).click(); 				// Click Select button
				Thread.sleep(2000);																	// Pause 2 seconds for page update
		    	// Close the Browser (Postcondition)
		    	Mimic.quit();																		// Close the Browser
		    	ReportText = "A5) Closed Address Search Browser";									// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console	
				}
			}
		}
	}