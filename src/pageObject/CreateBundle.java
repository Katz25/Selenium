package pageObject;

import org.openqa.selenium.*;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class CreateBundle {
	
	private static WebElement element = null;														// Initiate Element

	public static WebElement cbobx_CC(WebDriver driver)
		{	
		//<select id="costid" name="costid"><option value="" selected="selected"></option><option value="549">costcentre</option><option value="456">Default</option><option value="599">Hopewiser Admin</option></select>0">
		WebElement element = driver.findElement(By.xpath("//select[@id='costid']"));				// Select the Cost Centre Edit box
		return element ;	
		}
	
	public static WebElement cbobx_Plan(WebDriver driver)
		{	
		//<select id="planid" name="planid" required="required"><option value="" selected="selected"></option><option value="4">UK PAF - External User</option><option value="5">UK PAF - Internal User</option><option value="6">UK PAF - Associated User</option><option value="7">UK PAF - Broker Licence</option><option value="8">UK PAF - Public Sector Licence</option><option value="9">UK PAF+NSPD - External User</option><option value="10">UK PAF+NSPD - Internal User</option><option value="11">UK PAF+NSPD - Associated User</option><option value="12">UK PAF+NSPD - Broker Licence</option><option value="13">UK PAF+NSPD - Public Sector Licence</option><option value="15">AUS PAF</option><option value="16">NZ PAF</option><option value="17">UK PAF with Names (BS Postcode Only)</option><option value="39">USA Zip Code</option><option value="19">DE PAF</option><option value="20">NL PAF</option><option value="21">UK Test Xtra</option><option value="18">CHE PAF</option><option value="23">UK Test NC Extract</option><option value="24">UK Test Blocked</option><option value="25">UK NatCan With Names</option><option value="26">UK Consumer View</option><option value="30">UK Bankcode</option><option value="31">UK IVP Bankcode</option><option value="34">UK Street - Internal User</option><option value="35">UK Street - External User</option><option value="41">UK PAF - Define</option><option value="43">Ireland Test</option><option value="44">IRL PAF</option><option value="47">Eircode Test</option><option value="48">UK PAF - Define (No Gazetteering)</option><option value="49">18577 Test Plan</option><option value="50">UK PAF+Names - Internal User</option><option value="51">AUS G-NAF</option><option value="52">Test plan AUS</option><option value="53">IRL ECAD</option><option value="55">UK PAF+NSPD - External User (July 2017 Price)</option></select>
		WebElement element = driver.findElement(By.xpath("//select[@id='planid']"));	   			// Select the Plan Combobox
		return element ;	
		}
	
	public static WebElement cbobx_BT(WebDriver driver)
		{	
		//<select id="test" name="test" required="required"> <option value="" selected="selected"></option><option value="0">Non-Test Bundle</option><option value="1">Test Bundle</option></select>
		WebElement element = driver.findElement(By.xpath("//select[@id='test']"));					// Select the Bundle Type Edit box
		return element ;	
		}
	
	public static WebElement txtbx_Clicks(WebDriver driver)
		{	
		//<input id="clicks" name="clicks" required="required" type="text" value="">
		WebElement element = driver.findElement(By.xpath("//input[@id='clicks']"));					// Select the Clicks Edit box
		return element ;	
		}
    
    public static WebElement btn_CreateBundle(WebDriver driver)
    	{
    	//<input type="submit" class="ym-button ym-danger" value="Create">
		element = driver.findElement(By.xpath("//input[@value='Create']"));							// Select the Create Button
		return element ;
    	}
		
	public static WebElement btn_SaveBundle(WebDriver driver)
		{
	    //<input type="submit" class="ym-button ym-danger" value="Save">
		element = driver.findElement(By.xpath("//input[@value='Save']"));							// Select the Save Button
		return element ;
		}
	}