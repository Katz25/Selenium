package testScripts;

import testRuns.Run1c;
import testRuns.Run2;
import testRuns.Run3;
import testRuns.Run30;
import testRuns.Run31;
import testRuns.Run32;
import testRuns.Run33;
import testRuns.Run34;
import testRuns.Run35;
import testRuns.Run36;
import testRuns.Run36a;
import testRuns.Run36b;
import testRuns.Run37a;
import testRuns.Run38;
//import testRuns.Run39;
import testRuns.Run4;
//import testRuns.Run40;
import testRuns.Run5;
import testRuns.Run6;
import testRuns.Run7;
import testRuns.Run8;
import testRuns.Run9;
import appModule.OpenPHPMyAdmin_Action;
import testRuns.Run1;
import testRuns.Run10;
import testRuns.Run11;
import testRuns.Run12;
import testRuns.Run13;
import testRuns.Run14;
import testRuns.Run15;
import testRuns.Run16;
import testRuns.Run17;
import testRuns.Run18;
import testRuns.Run19;
import testRuns.Run20;
import testRuns.Run21;
import testRuns.Run22;
import testRuns.Run23;
import testRuns.Run24;
import testRuns.Run25;
import testRuns.Run26;
import testRuns.Run27;
import testRuns.Run28;
import testRuns.Run29;
/*
 * Author Maxine Flook
 * Selenium tests V1
 */
public class RunTestSuite 
	{
	public static void main(String[] args) throws Exception 
		{		
		// This Test Suite script is written to quickly run the ASIC automated test suite on Chrome.
		OpenPHPMyAdmin_Action.main(args);	// Opens the PHPMyAdmin application and goes to the page to run the (testData) Delete_sql file, Completes
		Run36.main(args); 	// Request a New password (with HopewiserAdmin), Completes
		Run37a.main(args); 	// Confirm Password Negative test (with HopewiserAdmin), Completes
		Run36.main(args); 	// Request a New password (with HopewiserAdmin), Completes
		Run36a.main(args); 	// Try to activate the Forgotten Password (with HopewiserAdmin), Completes
		Run36.main(args); 	// Request a New password (with HopewiserAdmin), Completes
		Run36b.main(args); 	// Try to activate the Forgotten Password (with HopewiserAdmin), Completes
		Run1.main(args);	// Registers (QAReg-ca must have been deleted), Completes
		Run1c.main(args); 	// logs in (with QAReg-ca), Completes
		Run2.main(args);  	// logs in (with QAReg-ca), Creates (Qatest-su-ca), Completes
		Run3.main(args);  	// logs in (with QAReg-ca), Creates (Qatest-cca-ca), Completes
		Run4.main(args);  	// logs in (with QAReg-ca), Completes  
		Run5.main(args);  	// logs in (with Qatest-cca-cca), Completes 
		Run6.main(args);  	// logs in (with QAReg-ca), Completes 
		Run7.main(args);  	// logs in (with QAReg-ca), (Qatest-ca-ca must have been deleted), Completes 
		Run8.main(args);  	// logs in (with HopewiserAdmin) Search for (Hopewiser Test), (QAtest-su-ha must have been deleted), Completes
		Run9.main(args); 	// logs in (with HopewiserAdmin) Search for (Hopewiser Test), (QAtest-cca-ha must have been deleted), Completes
		Run10.main(args); 	// logs in (with HopewiserAdmin) Search for (Hopewiser Test), (QAtest-ca-ha must have been deleted), Completes
		Run11.main(args); 	// logs in (with HopewiserAdmin) Search for (Hopewiser Test), (QAtest-hu-ha must have been deleted), Completes
		Run12.main(args); 	// logs in (with HopewiserAdmin) Search for (Hopewiser Test), (QAtest-ha-ha1 must have been deleted), Completes
		Run13.main(args); 	// logs in (with HopewiserAdmin) Search for (Hopewiser Test), (QAtest-su-ha1 must have been deleted), Completes	
		Run14.main(args); 	// logs in (with Qatest-ha-cca), Completes
		Run15.main(args); 	// Uses address-search and logs in (with QAtest-ha-cca), Completes
		Run16.main(args); 	// Uses address-search and logs in (with QAtest-ha-ca), Completes
		Run17.main(args); 	// logs in (with QAtest-ha-ca), Completes
		Run18.main(args); 	// Uses address-search, logs in (with Qatest-ha-cca), Completes
		Run19.main(args); 	// Uses address-search, logs in (with QAtest-ha-ca), Completes
		Run20.main(args); 	// Uses address-search, logs in (with HopewiserAdmin), Completes
		Run21.main(args); 	// Uses address-search, logs in (with HopewiserAdmin), Completes
		Run22.main(args); 	// Uses address-search, logs in (with HopewiserAdmin), Completes
		Run23.main(args); 	// Uses address-search, logs in (with QAtest-ha-ca1), Completes
		Run24.main(args); 	// Uses address-search, logs in (with QAtest-ha-cca1), Completes
		Run25.main(args); 	// Uses address-search, logs in (with QAAutomation), Completes
		Run26.main(args); 	// Uses address-search, logs in (with QAAutomation), Completes
		Run27.main(args); 	// Uses address-search, logs in (with HopewiserAdmin), Completes
		Run28.main(args); 	// Uses address-search, logs in (with HopewiserAdmin), Completes
		Run29.main(args); 	// Uses address-search, logs in (with HopewiserAdmin), Completes
		Run30.main(args); 	// Uses address-search, logs in (with HopewiserAdmin), Completes
		Run31.main(args); 	// Uses address-search, logs in (with HopewiserAdmin), Completes
		Run32.main(args); 	// Uses address-search, logs in (with QAAutomation), Completes
		Run33.main(args); 	// Uses address-search, logs in (with QAAutomation), Completes
		Run34.main(args); 	// Uses address-search, logs in (with QAAutomation), Completes
		Run35.main(args); 	// Uses address-search, logs in (with QAAutomation), Completes
		Run38.main(args); 	// logs in (with HopewiserAdmin), Completes
		//Run39.main(args); // logs in (with HopewiserAdmin), Incomplete
		//Run40.main(args); // logs in (with HopewiserAdmin), Incomplete
		}
	}