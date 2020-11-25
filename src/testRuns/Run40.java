package testRuns;

import java.io.ByteArrayOutputStream;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import appModule.CreateBundle_Action;
import appModule.Login_Action;
import appModule.Output;
import appModule.ScreenShot_Action;

import utility.Constant;
import utility.ExcelUtils; 

public class Run40 
	{
    public static void main(String[] args) throws Exception 
    	{   	
    	// Launch Chrome (Precondition)
    	String exePath = "C:\\Memos\\chromedriver.exe";												// Set variable to path where Chrome.exe is stored
    	System.setProperty("webdriver.chrome.driver", exePath);										// Set Path to open Chrome from
    	WebDriver driver = new ChromeDriver();														// Open a new instance of Chrome
    	String ReportText = "1) Run34 Chrome Launched";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
        
    	//This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet3");			// Look in the Excel sheet for test data
        
       	// Open the login website using the Url (Precondition)
    	driver.get(Constant.URL);																	// Open the login website using the Url
		ReportText = "2) " + Constant.URL + " Website Opened";										// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
    			
		// 4.0 Login using the appModule Login_Action
		Login_Action.Execute(driver);																// Login using the appModule Login_Action
		
		// Test if Login occurred
		String whatiscurrenturl = driver.getCurrentUrl();											// Set variable to the current web address
		String testurl1 = "https://services-sandbox.hopewiser.com/hostedmgr/home";					// Set Variable to path
		String testurl2 = "https://services-sandbox.hopewiser.com/hostedmgr/logon?error=true";		// Set Variable to path
		if (whatiscurrenturl.contains(testurl1))													// Test if Home page exists
			{
			ReportText = "3) Login Passed: " + whatiscurrenturl;									// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
			String SS = "LoginPass39_";																// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl.contains(testurl2))												// Test if logon page exists
				{
				ReportText = "3) Login Failed: " + whatiscurrenturl;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginFail39_";															// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			else
				{
				ReportText = "3) Unknown Error: " + whatiscurrenturl;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				String SS = "LoginError39_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}
		
		// 6.3 <a href="/hostedmgr/acct">Accounts</a>	
    	driver.findElement(By.xpath("//a[@href='/hostedmgr/acct']")).click();						// Click the Your Account link
    	Thread.sleep(3000);																			// Pause 3 seconds for page update
		ReportText = "4) Click the Account link";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		String SS = "AccountsPage39_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
    	// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method (Precondition)
    	ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet42");		// Look in the Excel sheet for test data
		String sAccountName = ExcelUtils.getCellData(1, 1);											// Get Account Name from TestData1.xlsx

		// Search for HopewiserAccountQA
		// <input id="textfilteraccount-table" type="text" title="Searches each table cell. Supports multi-character (*) and single character (?) wildcards">				
		driver.findElement(By.xpath("//input[@id='textfilteraccount-table']")).sendKeys(sAccountName);  // Enter the user name
		Thread.sleep(3000);																   			// Pause 3 seconds for page update
		ReportText = "5) Searched for " + sAccountName;												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountSearchPage39_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select HopewiserAccountQA link from the list
		//<span style="color:black;background-color:yellow">HopewiserAccountTest</span>   //*[@id="account-table"]/tbody/tr[26]/td[1]/a/span/span  
		driver.findElement(By.xpath("//tbody/tr[26]/td[1]/a/span/span")).click();					// Click the First Account in the table				
		Thread.sleep(3000);																			// Print validation text to console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "AccountDetailsPage39_";																// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		// Select Bundles tab
		//*[@id="main"]/div/div/section[2]/article/div/div[1]/ul/li[4]/a
		driver.findElement(By.xpath("//div/div/section[2]/article/div/div[1]/ul/li[4]/a")).click(); // Click Bundles tab
		Thread.sleep(3000);																   			// Pause 3 seconds for page update
		ReportText = "6) Select Bundle from table";													// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		// Select Bundles Create New Button
		//<a href="/hostedmgr/acct/319/bund/create" class="ym-button ym-small">Create&nbsp;New</a>
		driver.findElement(By.xpath("//a[@class='ym-button ym-small']")).click();  					// Click the Create New Button
		Thread.sleep(3000);																   			// Pause 3 seconds for page update
		ReportText = "7) Create New Button clicked";												// Set up report text variable
		Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
		SS = "CreateBundlePage39_";																	// Create variable to help name the Screenshot
		ScreenShot_Action.Execute(driver, SS);														// Create Screenshot using appModule.ScreenShot_Action
		
		int PlusDays = 8;																			// Set number of days to add to todays date
		// Create Bundle using the appModule CreateBundle_Action
    	ExcelUtils.setExcelFile(Constant.Path_TestData1 + Constant.File_TestData1,"Sheet42");		// Look in the Excel sheet for test data
		CreateBundle_Action.Execute(driver,PlusDays);												// Create Bundle using the appModule CreateBundle_Action
		
		// Test if Create occurred
		String whatiscurrenturl2 = driver.getCurrentUrl();											// Set variable to the current web address
		String testurlC1 = "https://services-sandbox.hopewiser.com/hostedmgr/acct/459/cost/706/bund";// Set variable to path
		String testurlC2 = "/bund/create";															// Set variable to path
		if (whatiscurrenturl2.equals(testurlC1))													// Test if bund page exists
			{
			ReportText = "8) Create Bundle Passed: " + whatiscurrenturl2;							// Set up report text variable
			Output.FilePlusConsole(ReportText); 													// Print validation text to File and Console
			// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results		
			SS = "BundlePass39_";																	// Create variable to help name the Screenshot
			ScreenShot_Action.Execute(driver, SS);													// Create Screenshot using appModule.ScreenShot_Action
			}
		else
			{	
			if (whatiscurrenturl2.contains(testurlC2))												// Test if create page exists
				{
				ReportText = "8) Create Bundle Failed: " + whatiscurrenturl2;						// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results		
				SS = "CreateBundleFail39_";															// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			else
				{
				ReportText = "8) Unknown Error: " + whatiscurrenturl2;								// Set up report text variable
				Output.FilePlusConsole(ReportText); 												// Print validation text to File and Console
				// Create Screenshot png file in C:\Users\Maxine\Documents\Automation results
				SS = "CreateBundleError39_";														// Create variable to help name the Screenshot
				ScreenShot_Action.Execute(driver, SS);												// Create Screenshot using appModule.ScreenShot_Action
				System.exit(1);																		// Drop out of script
				}
			}
		
		// 8.1 Use Soap UI to test Bank coder		
    	//driver.get("http://addrsvr2.hopewiser.com:8080/soapbankcoder/soapbankcoder.wsdl");			// Open the login website using the Url
		//ReportText = "9) http://addrsvr2.hopewiser.com:8080/soapbankcoder/soapbankcoder.wsdl Website Opened"; // Set up report text variable
		//Output.FilePlusConsole(ReportText); 														// Print validation text to File and Console
		
		/*try
			{
			SoapUITestCaseRunner soapUITestCaseRunner = new SoapUITestCaseRunner();
			soapUITestCaseRunner.setProjectFile(“src/test/resources/WeatherSoapTest-soapui-project.xml”);
			soapUITestCaseRunner.setProjectProperties(prop);
			soapUITestCaseRunner.setTestSuite(“TestSuite1″);
			soapUITestCaseRunner.setTestCase(“TestCase1″);
			soapUITestCaseRunner.run();
			} 
		catch (Exception e) 
			{
			System.err.println(“checking” + zipCode + ” failed!”);
			failureCount++;
			zipCodes.append(zipCode + ” [” + city +”] “);
			e.printStackTrace();
			}
		finally
			{
			totalCount++;
			}*/
		
		MessageFactory factory = MessageFactory.newInstance();														// Initiate Message Factory
		SOAPMessage message = factory.createMessage();																// Create the message
		SOAPPart soapPart = message.getSOAPPart();																	// Initiate SOAP Part specifically for Envelope
		SOAPEnvelope Envelope = soapPart.getEnvelope();																// Initiate <Envelope> line into the message
		SOAPHeader Header = Envelope.getHeader();
		SOAPBody Body = Envelope.getBody();
		Envelope.addNamespaceDeclaration("soapenv","http://schemas.xmlsoap.org/soap/envelope/");					// Setup the Name space of soapenv
		Envelope.addNamespaceDeclaration("oas", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd"); // Setup the Name space of oas
		Envelope.addNamespaceDeclaration("soap", "http://hopewiser/soapbankcoder");									// Setup the Name space of soap
			QName NameS1 = new QName("soapenv");																	// Setup the Name space of soapenv
			SOAPHeaderElement HeaderEl = Header.addHeaderElement(NameS1);																	// Initiate <Header> line into the message
				NameS1 = new QName("oas");																			// Setup the Name space of oas
				SOAPElement Security = HeaderEl.addChildElement(NameS1);										// Add a Child Element to the Header section called <Security>
					SOAPElement UsernameToken = Security.addChildElement(NameS1);									// Add a Child Element to the <Security> section called <UsernameToken>
						// Enter Username
						UsernameToken.addTextNode("HopewiserAdmin");												// Add a Text Node to the <UsernameToken> section
						// Enter Password
						UsernameToken.addTextNode("hopewiser");														// Add a Text Node to the <UsernameToken> section	
			NameS1 = new QName("soapenv");																			// Setup the Name space of soapenv
			SOAPBodyElement BodyEl = Body.addBodyElement(NameS1);													// Initiate <Header> line into the message
				NameS1 = new QName("soap");																			// Setup the Name space of soap
				SOAPElement ValidateReturnDetailRequest = BodyEl.addChildElement(NameS1);								// Add a Child Element to the Header section called <Security>
					// Enter the invalid Sort code and Account Number
					ValidateReturnDetailRequest.addTextNode("20513223537846");										// Add a Text Node to the <ValidateReturnDetailRequest> section
					// Enter the dataset to use
					ValidateReturnDetailRequest.addTextNode("uk-vl-bankcode");										// Add a Text Node to the <ValidateReturnDetailRequest> section
					
		SOAPConnectionFactory SCF = SOAPConnectionFactory.newInstance();											// Initiate Message Factory line
		SOAPConnection connection = SCF.createConnection();															// Initiate Connection for the Message Factor
		URL endpoint = new URL("http://hopewiser/soapbankcoder");													// Set up endpoint variable to state where the message is sent to.

		// Print the message that has been built
		ByteArrayOutputStream out = new ByteArrayOutputStream();													// Initiate the ability to read ByteArrayOutputStream which is the message type
		message.writeTo(out);																						// Write the current message to the Out variable
		String strMsg = new String(out.toByteArray());																// Change ByteArrayOutputStream to a string
		ReportText = strMsg;																						// Set up report text variable
		Output.FilePlusConsole(ReportText); 																		// Print validation text to File and Console

		// Print the response message
		//connection.call(message, endpoint);																		// Send the message via the connection.
		SOAPMessage response = connection.call(message, endpoint);													// Send the message via the connection and accept the response.
		ByteArrayOutputStream out2 = new ByteArrayOutputStream();													// Initiate the ability to read ByteArrayOutputStream which is the message type
		response.writeTo(out2);																						// Write the current message to the Out variable
		strMsg = new String(out2.toByteArray());																	// Change ByteArrayOutputStream to a string
		ReportText = ("10 " + strMsg);																				// Set up report text variable
		Output.FilePlusConsole(ReportText); 																		// Print validation text to File and Console
		
		connection.close();																							// End the connection
		
		//Format of valid response
		/*<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
  			<soapenv:Header/>
  			<soapenv:Body>
    			<hpw:ValidateReturnDetailResponse xmlns:hpw="http://hopewiser/soapbankcoder">
      				<hpw:StatusCode>2</hpw:StatusCode>
      				<hpw:StatusText>Account valid.</hpw:StatusText>
      				<hpw:ValidityFlag>Y</hpw:ValidityFlag>
      				<hpw:BankDetail>Y</hpw:BankDetail>
        				<hpw:Item name="RecordType">1</hpw:Item>
        				<hpw:Item name="Sortcode">074456</hpw:Item>
        				<hpw:Item name="BICBank"/>
        				<hpw:Item name="BICBranch"/>
        				<hpw:Item name="SubBranchSuffix">00</hpw:Item>
        				<hpw:Item name="ShortBranchTitle">FlexAccount (NGC)</hpw:Item>
        				<hpw:Item name="ShortNameOfOwningBank">NATIONWIDE BLDG SCTY</hpw:Item>
        				<hpw:Item name="FullNameOfOwningBank1">NATIONWIDE BUILDING SOCIETY</hpw:Item>
        				<hpw:Item name="FullNameOfOwningBank2"/>
        				<hpw:Item name="CHAPSStatus">I</hpw:Item>
        				<hpw:Item name="FPSStatus">M</hpw:Item>
        				<hpw:Item name="FullBranchTitle1">FlexAccount (NGC)</hpw:Item>
        				<hpw:Item name="FullBranchTitle2"/>
        				<hpw:Item name="FullBranchTitle3"/>
        				<hpw:Item name="Address1">P.O. Box 8888</hpw:Item>
        				<hpw:Item name="Address2">Nationwide Hse</hpw:Item>
        				<hpw:Item name="Address3">Pipers Way</hpw:Item>
        				<hpw:Item name="Address4"/>
        				<hpw:Item name="AddressTown">Swindon L</hpw:Item>
        				<hpw:Item name="AddressCounty">Wilts.</hpw:Item>
        				<hpw:Item name="AddressOutcode">SN38</hpw:Item>
        				<hpw:Item name="AddressIncode">1NW</hpw:Item>
        				<hpw:Item name="Telephone1STD">0800</hpw:Item>
        				<hpw:Item name="Telephone1">302011</hpw:Item>
        				<hpw:Item name="Telephone2STD"/>
        				<hpw:Item name="Telephone2"/>
      				</hpw:BankDetail>
    			</hpw:ValidateReturnDetailResponse>
  			</soapenv:Body>
		</soapenv:Envelope>*/
		
		// 8.1 script not finished yet
		
    	// Close the Browser (Postcondition)
    	// driver.quit();																							// Close the Browser
		// ReportText = "9) Closed Browser";																		// Set up report text variable
		// Output.FilePlusConsole(ReportText); 																		// Print validation text to File and Console
    	}
	}