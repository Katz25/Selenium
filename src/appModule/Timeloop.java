package appModule;

import org.openqa.selenium.WebDriver;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class Timeloop 
	{
	public static void Execute(WebDriver driver,String TextInput) throws Exception
		{
		Boolean b = false;																	// Set boolean variable to false
		int i=0;																			// Initiate integer value at 0
		while(b==false)
			{
			Thread.sleep(100);																// Pause 0.1 seconds for page update
			String whatisAcounturl = driver.getCurrentUrl();								// Set variable to the current web address
			if (whatisAcounturl.contains(TextInput))
				{
				b = true;																	// Set variable b to true
				String ReportText = "T) "+TextInput+" Found";								// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				}
			else
				{
				i++;																		// Increase i by 1
				String ReportText = "T) "+TextInput+" unfound";								// Set up report text variable
				Output.FilePlusConsole(ReportText); 										// Print validation text to File and Console
				if (i==40)																	// Maximum 3 seconds
					{
					ReportText = "T) Timeout";												// Set up report text variable
					Output.FilePlusConsole(ReportText); 									// Print validation text to File and Console
					System.exit(1);															// Drop out of script
					}
				}
			}
		}
	}