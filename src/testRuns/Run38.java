package testRuns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import appModule.Output;
import appModule.ScreenShot_Action;
import appModule.Timeloop;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class Run38 
	{
    public static void main(String[] args) throws Exception 
    	{   	
		// Launch Chrome (Precondition)
		String exePath = "C:\\Selenium\\chromedriver.exe";										// Set variable to path where Chrome.exe is stored
		System.setProperty("webdriver.chrome.driver", exePath);									// Set Path to open Chrome from
		WebDriver driver = new ChromeDriver();													// Open a new instance of Chrome
		String ReportText = "1) Run38 Chrome Launched";											// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		
		// Open the login website using the incorrect Url (Precondition)
		String HForgottenPassword = "https://services-sandbox.hopewiser.com/hostedmgr/resetpassword/f3b048dd-a9d1-4fc4-b360-283cab367a62"; // Set up the first page to open
		driver.get(HForgottenPassword);															// Open the login website using the Url
		Timeloop.Execute(driver, "https://services-sandbox.hopewiser.com/hostedmgr/resetpassword/"); // Ensure the correct page is displayed in a sensible time
		ReportText = "2) " + HForgottenPassword + " Website Opened";							// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		String SS = "ResetPWError_";															// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action

		// Check if the Request a new link exists 
		//*[@id="main"]/div/div/section[2]/article/div/p/a   <a href="/hostedmgr/forgottenpassword;jsessionid=389AF703E4ABE2ACD60B2C0A5DCF46C3.addrsvr2">Request a new link</a>
    	try
			{
    		driver.findElement(By.partialLinkText("Request")).click();							// Click on Request a new link
			ReportText = "3) The correct page has been opened";									// Set up report text variable
			Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
			}
    	catch (Exception e)
			{
    		ReportText = "3) The Reset Password Page with an error message is not displayed";	// Set up report text variable
    		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
    		System.exit(1);																		// Drop out of script
			}
    	
		// Close the Browser and kill task (Postcondition)
		driver.quit();																			// Close the Browser
		ReportText = "4) Closed Browser";														// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console*
    	}
	}