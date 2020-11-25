package appModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import appModule.Output;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class OpenPHPMyAdmin_Action 
	{
	public static void main(String[] args) throws Exception 
		{
		// Launch Chrome
		String exePath = "C:\\Selenium\\chromedriver.exe";								// Set Variable for path Where Chrome.exe is stored
		System.setProperty("webdriver.chrome.driver", exePath);							// Set Path to open Chrome from
		WebDriver driver = new ChromeDriver();											// Open a new instance of Chrome
				
		// Open the website
		driver.get("https://intranet.hopewiser.com/phpmyadmin"); 						// Open the PHPMyAdmin login page using the Url		
				
		// Login to phpMyAdmin	
		//<input name="pma_username" class="textfield" id="input_username" type="text" size="24" value="">
		driver.findElement(By.id("input_username")).click(); 							// Find Username editbox and click to focus	
		driver.findElement(By.id("input_username")).sendKeys("qa"); 					// Find Username editbox and enter 'qa'			
		
		//<input name="pma_password" class="textfield" id="input_password" type="password" size="24" value="">
		driver.findElement(By.id("input_password")).click(); 							// Find Password editbox		
		driver.findElement(By.id("input_password")).sendKeys("qa1234"); 				// Find Password edit box and Enter qa1234		 

		driver.findElement(By.id("select_server")).sendKeys("addrsvr2.hopewiser.com"); 	// Find server drop down box and enter 'addrsvr2.hopewiser.com'
				
		driver.findElement(By.id("input_go")).click(); 									// Click the Go button
		Thread.sleep(3000); 															// Pause 3 seconds for page update	
		
		// Test if title of page is addrsvr2.hopewiser.com
		String whatiscurrenturl = driver.getCurrentUrl();								// Get the current url	
		Boolean iselementpresent = driver.findElements(By.linkText("addrsvr2.hopewiser.com")).size()!= 0;		
		if (iselementpresent = true)
			{
			System.out.println("PHPMyAdmin Opened: " + whatiscurrenturl); 				// Print text to console 
			}
		else
			{
			System.out.println("PHPMyAdmin Not Opened: "+ whatiscurrenturl); 			// Print text to console 
			}
		
		System.out.println(iselementpresent); 											// Print true or false text to console 
		
		//driver.findElement(By.xpath("//img[@title='SQL']")).click(); 					// Click the SQL tab
		driver.findElement(By.xpath("//img[@title='Import']")).click(); 				// Click the Import tab
		Thread.sleep(3000); 															// Pause 3 seconds for page update	
		
		// Select the the Choose File button
		//<input type="file" name="import_file" id="input_import_file"> 				//*[@id="input_import_file"]
		driver.findElement(By.id("input_import_file")).click(); 						// Click the Choose File button
			
		// When Open Pop-up appears, select C:\Users\ user\eclipse-workspace\AsicOnlineIE\src\testData\Delete_sql and click Open		
		Runtime.getRuntime().exec("C:\\Users\\user\\Documents\\Open_Script.exe"); 		// Line which runs the AutoIT script
		Thread.sleep(3000); 															// Pause 3 seconds for page update	
		
		//<input id="buttonGo" type="submit" value="Go">
		driver.findElement(By.id("buttonGo")).click(); 									// Click the Go button
		
		// Close the Browser (Postcondition)
		driver.quit();																	// Close the Browser
		String ReportText = "Closed PHP Browser";										// Set up report text variable
		Output.FilePlusConsole(ReportText); 											// Print validation text to File and Console
		}
	}