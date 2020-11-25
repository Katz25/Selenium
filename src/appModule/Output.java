package appModule;

import java.io.*;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class Output
	{
	//This Method
    public static void FilePlusConsole(String TextInput) throws Exception 
    	{
		// Copy Println to file
		boolean Append = true;
		//PrintStream fileStream = new PrintStream(new File("C:\\Users\\Maxine\\Documents\\Webdriver_ouptut.txt", Append));
		FileWriter fileStream = new FileWriter("C:\\Users\\User\\Documents\\Webdriver_ouptut.txt", Append); 	//Set where the file, to use can, be found, and set it to append all text to the end of the file
		System.out.println(TextInput); 																	// Print validation text to console
		fileStream.write(System.lineSeparator());
		fileStream.write(TextInput); 																	// Print validation text to file
		fileStream.close();																				// Close the output file
    	}    
	}