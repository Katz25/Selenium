package pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class Activate {

	private static WebElement element = null;
	
	//<input id="login" name="login" required="required" type="text" value="" maxlength="250">
    public static WebElement txtbx_UserName(WebDriver driver){    	
    	element = driver.findElement(By.xpath("//input[@id='login']"));    	 
        return element;
    }
    
    //<input id="password" name="password" required="required" type="password" value="" maxlength="50">
    public static WebElement txtbx_Password(WebDriver driver){  	 
        element = driver.findElement(By.xpath("//input[@id='password']"));
        return element;
    }
    
	//<input type="submit" class="ym-button ym-danger" value="Activate">
    public static WebElement btn_LogIn(WebDriver driver){   	 
        element = driver.findElement(By.xpath("//input[@class='ym-button ym-danger']"));
        return element;    
	}

}