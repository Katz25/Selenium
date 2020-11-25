package appModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class Worldpay_Action 
	{
	public static void Execute(WebDriver driver) throws Exception
		{
		//Select Visa icon
		//<input name="op-DPChoose-VISA^SSL" type="image" src="/images/logos/VISA.gif" alt="Visa">
    	driver.findElement(By.xpath("//input[@alt='Visa']")).click(); 						// Click the Test with WorldPay button
    	Thread.sleep(3000);																	// Pause 3 seconds for page update
		
		String section = "Worldpay Secure Payment Page_";									// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, section);											// Create Screenshot using appModule.ScreenShot_Action
		String whatiscurrenturl4 = driver.getCurrentUrl();									// Set variable to the current web address
		String testurl9 = "https://secure-test.worldpay.com";								// Set variable to path
		if (whatiscurrenturl4.contains(testurl9))											// Test if Home page exists
			{
			System.out.println("8) Worldpay Exists: " + whatiscurrenturl4); 				// Print text to console
			//Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			section = "WorldpayPass_";														// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, section);										// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			System.out.println("8) Unknown Error: " + whatiscurrenturl4); 					// Print text to console
			//Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			section = "WorldpayError_";														// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, section);										// Create Screenshot using appModule.ScreenShot_Action
			}
		
		// Fill in Card number
		//<input type="text" id="cardNoInput" name="cardNoInput" tabindex="60" value="" maxlength="20" autocomplete="off">
    	driver.findElement(By.xpath("//input[@id='cardNoInput']")).clear(); 				// Clear the edit box		
		driver.findElement(By.xpath("//input[@id='cardNoInput']")).sendKeys("491760000000000");	// Enter Card number (get rid of hard code)
		
		// Fill in Security Code
		//<input type="text" id="cardCVV" name="cardCVV" tabindex="70" size="4" maxlength="4" autocomplete="off" value="">
    	driver.findElement(By.xpath("//input[@id='cardCVV']")).clear(); 					// Clear the edit box	
		driver.findElement(By.xpath("//input[@id='cardCVV']")).sendKeys("521");				// Enter Security Code (get rid of hard code)
		
		// Select suitable Month
		//<select name="cardExp.month" tabindex="110">		
		//new Select(driver.findElement(By.xpath("//select[@tabindex='cardExp.month']"))).selectByVisibleText("01"); // Select from the Expiry Date drop down (get rid of hard code)
		new Select(driver.findElement(By.xpath("//select[@name='cardExp.month']"))).selectByVisibleText("01"); // Select from the Expiry Date drop down (get rid of hard code)
		
		// Select suitable Year
		//<select name="cardExp.year" tabindex="120">
		new Select(driver.findElement(By.xpath("//select[@name='cardExp.year']"))).selectByVisibleText("2037"); // Select from the Expiry Date drop down (higher than the present year) (get rid of hard code)
		
		//Select I'm not a robot
		//<div class="recaptcha-checkbox-checkmark" role="presentation"></div>
		//driver.findElement(By.xpath("//div[@class='presentation']")).click(); 			// Tick I'm not a robot - doesn't work, I am a robot	
		//driver.findElement(By.xpath("//div[@role='recaptcha-checkbox-checkmark']")).click(); // Tick I'm not a robot - doesn't work, I am a robot
		
		// Make payment
		//<input name="op-PMMakePayment" id="op-PMMakePayment" tabindex="249" type="image" src="/images/buttons/wp_whtbg.gif" alt="Make payment">
		driver.findElement(By.xpath("//input[@alt='Make payment']")).click(); 				// Click Make payment button
		
		// Worldpay error
		//Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		section = "Invalid card number_";													// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, section);											// Create Screenshot using appModule.ScreenShot_Action
		
		//Cancel Worldpay transaction
		//<img src="/images/buttons/wp_whtbg.gif" alt="Cancel" style="border:0px;">
		driver.findElement(By.xpath("//img[@alt='Cancel']")).click(); 						// Click Cancel button
		section = "Cancellation_";															// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, section);											// Create Screenshot using appModule.ScreenShot_Action
		System.out.println("9) Worldpay Cancelled"); 										// Print text to console
    	String ReportText = "9) Worldpay Cancelled";										// Set up report text variable
		Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console	
		}	
	}