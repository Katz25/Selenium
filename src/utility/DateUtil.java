package utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class DateUtil 
	{	
	//This method is to turn the current time and date into a formatted string to return to the calling script 
	//From http://learn-automation.com/how-to-get-current-system-date-and-time-in-java-selenium/
    public static String getDate() throws Exception 
    	{
		try 
			{	
   	 		Date DateInst = new Date();													// Get current date time with Date()
   	 		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");					// Create object of SimpleDateFormat class and decide the format   	 
   	 		String DateTime = dateFormat.format(DateInst);								// Now format the date
   	 		return DateTime;															// Return the Date
			}
		catch (Exception e)
			{
			throw (e);
			}
    	}
	}