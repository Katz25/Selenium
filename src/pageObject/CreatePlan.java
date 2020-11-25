package pageObject;

import org.openqa.selenium.*;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class CreatePlan 
	{
	private static WebElement element = null;														// Initiate Element

	public static WebElement txtbx_Name(WebDriver driver)
		{	
		//// <input id="name" name="name" required="required" type="text" value="">
		WebElement element = driver.findElement(By.xpath("//input[@id='name']"));					// Select the Plan name
		return element ;	
		}
	
	public static WebElement txtbx_Description(WebDriver driver)
		{	
		//<textarea id="description" name="description" rows="6"></textarea>
		WebElement element = driver.findElement(By.xpath("//textarea[@id='description']"));			// Select the Plan Combobox
		return element ;	
		}
	
	public static WebElement cbobx_MAF(WebDriver driver)
		{	
		//<select id="mafid" name="mafid" required="required"><option value="" selected="selected"></option><option value="3">UK Postal Address File</option><option value="8">UK Postal Address File + NSPD</option><option value="16">New Zealand Postal Address File</option><option value="17">UK Postal Address File with Names - BS Postcode Only</option><option value="15">Australian Postal Address File</option><option value="18">Switzerland Postal Data</option><option value="19">Germany Data Factory</option><option value="20">Netherlands Postal Data</option><option value="21">UK Test Xtra</option><option value="22">UK Test</option><option value="23">UK Test NC Extract</option><option value="24">UK National Canvasse with Names</option><option value="25">UK Consumer View</option><option value="29">UK Bankcode</option><option value="30">UK IVP Bankcode</option><option value="33">UK Street File</option><option value="35"> USA Zip Code File</option><option value="38">UK Postal Address File + Define Names</option><option value="41">Republic of Ireland Eircode Address File</option><option value="44">Eircode Test</option><option value="45">UK Postal Address File + Define Names (No Gazetteering) </option><option value="46">UK Postal Address File with Names</option><option value="47">Australian G-NAF</option><option value="48">Republic of Ireland Eircode Address Database</option>
		WebElement element = driver.findElement(By.xpath("//select[@id='mafid']"));					// Select the Dataset
		return element ;	
		}
	
	public static WebElement txtbx_sDatasetRef(WebDriver driver)
		{	
		//<input id="mafalias" name="mafalias" required="required" type="text" value="">		
		element = driver.findElement(By.xpath("//input[@id='mafalias']"));							// Select the Dataset Reference
		return element ;	
		}
    
    public static WebElement txtbx_Duration(WebDriver driver)
    	{
		//<input id="duration" name="duration" type="text" value="">	
		element = driver.findElement(By.xpath("//input[@id='duration']"));
		return element;
		}
    
    public static WebElement txtbx_AvailStart(WebDriver driver)
		{
		//<input id="availstart" name="availstart" required="required" type="date" value="">			
    	element = driver.findElement(By.xpath("//input[@id='availstart']"));
    	return element;
		}
    
    public static WebElement txtbx_AvailEnd(WebDriver driver)
		{
    	//<input id="availend" name="availend" type="date" value="">	
    	element = driver.findElement(By.xpath("//input[@id='availend']"));
    	return element;
		}
    
    public static WebElement txtbx_Calc(WebDriver driver)
		{
    	//<input id="calculator" name="calculator" required="required" type="text" value="">	
    	element = driver.findElement(By.xpath("//input[@id='calculator']"));
    	return element;
		}
    
    public static WebElement txtbx_Pricing(WebDriver driver)
		{
    	//<textarea id="pricing" name="pricing" rows="6"></textarea>	
    	element = driver.findElement(By.xpath("//textarea[@id='pricing']"));
    	return element;
		}
    
    public static WebElement btn_CreatePlan(WebDriver driver)
		{
    	// <input type="submit" class="ym-button ym-danger" value="Create">	
    	element = driver.findElement(By.xpath("//input[@value='Create']"));
    	return element;
		}
	}