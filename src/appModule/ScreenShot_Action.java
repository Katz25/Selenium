package appModule;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import utility.Date_TimeUtils;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class ScreenShot_Action 
	{
	public static void Execute(WebDriver driver, String location) throws Exception
		{
		// Take a Screenshot and put it in a png file in C:\Automation results\Screenshots 
		TakesScreenshot screen = (TakesScreenshot) driver;										// Create screenshot variable connected to the browser				
		File srcFile = screen.getScreenshotAs(OutputType.FILE);									// Create file variable with the picture details in
		String DateTime = Date_TimeUtils.getDateTime();											// Get the current time and date, in (dd//MM//yyyy HH:mm:ss) string format, by using Date_TimeUtils.getDateTime
		String Path = "C:\\Automation results\\Screenshots\\";									// Set the path for the screenshot to be placed
		String ReportText = "SS) " + Path + location + DateTime;								// Set up report text variable
		Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
		String pngfile = Path + location + DateTime+".png";										// Set path and name to put the screenshot in
		File destFile = new File(pngfile);  													// Create a png file
		FileUtils.copyFile(srcFile, destFile);													// Copy the Screenshot variable into the png file		
		}
	}