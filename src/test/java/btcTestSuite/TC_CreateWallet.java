package btcTestSuite;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import testBase.TestBase;
import uiActions.HomePage;
import uiActions.RegisterPage;

public class TC_CreateWallet extends TestBase{
	
	RegisterPage registerpage;
	HomePage homePage;
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	String s="sohaibcool"+ timeStamp; 
	String username="Sohaibcool"+timeStamp+"@gmail.com";
	String password="Allah@123";
	String JunkUserid ="so!#$%^&*@blocktrail.com";
	String blank="";
	
	@BeforeTest
	public void setUp() {
	log.info("initialzing all the variable and methods");
	init();
	}
	
/*	TC Objective: Verify successful creating a Create Wallet
	===================================================
	1. Launch the Browser
	2. Navigate to "Create Wallet"
	3. Click on "Create Wallet" button
	4. Enter the Email ID and Password
	5. Check box the "terms of service"
	6. Click on "Create new wallet"

	Verification points
	---------------------------
	1. Verify if the 1st URL is an Internal Link or not?
	verify "Signout" element
	2. Verify if the 2nd URL is an External Link or not?

	Input Data/Test Data:
	1. user name:sohaibcool@gmail.com
	2. password: Allah*/
	
	@Test(priority=1)
	public void createWallet() {
		registerpage = new RegisterPage(driver);
		registerpage.createAccount(username, password);
		Assert.assertEquals(RegisterPage.registerURL, "https://wallet.btc.com/#/setup/register");
		log.info("_____________Register URL is been verified______________");
		registerpage.confirmPassword(password);
		registerpage.downloadWalletbkup();
		Assert.assertEquals(registerpage.HomeWalletPage, "https://wallet.btc.com/#/wallet");
		log.info("_____________Home URL is been verified______________");
		homePage = new HomePage(driver);
		homePage.Logout();
	}
	
	/*	TC Objective: Verify error message for wrong email id while Creating Wallet
	====================================================================
	1. Launch the Browser
	2. Navigate to "BTC.com"
	3. Click on "Create Wallet" button
	4. Enter Blank Email ID
	5. Enter correct password
	6. Check box the "terms of service"
	7. Click on "Create new wallet"

	Verification points
	---------------------------
	1. Verify email warning message

	Input Data/Test Data:
	---------------------
	1. user name:blank
	2. password: */
	
	@Test(priority=2)
	public void withBlankUserID() {
		registerpage = new RegisterPage(driver);
		registerpage.clickCreateAccLink();
		registerpage.createAccount(blank, password);
		registerpage.verifyemailErrormesg();
	}

	/*	TC Objective: Verify error message for wrong password while Creating Wallet
	====================================================================
	1. Launch the Browser
	2. Navigate to "BTC.com"
	3. Click on "Create Wallet" button
	4. Enter Email ID
	5. Enter blank password
	6. Check box the "terms of service"
	7. Click on "Create new wallet"

	Verification points
	---------------------------
	1. Verify warning message shown on password

	Input Data/Test Data:
	---------------------
	1. user name:sohaibcool@gmail.com
	2. password: blank
	}*/
	
	@Test(priority=3)
	public void withBlankPassword() {
		registerpage = new RegisterPage(driver);
		registerpage.clickCreateAccLink();
		registerpage.createAccount(username, blank);
		registerpage.verifyPasswordErrormesg();
}
	
	/*	TC Objective: Verify error message with both userid and password as blank
	=======================================================================
	1. Launch the Browser
	2. Navigate to "BTC.com"
	3. Click on "Create Wallet" button
	4. Enter Email ID
	5. Enter blank password
	6. Check box the "terms of service"
	7. Click on "Create new wallet"

	Verification points
	---------------------------
	1. Verify warning message shown on password

	Input Data/Test Data:
	---------------------
	1. user name:blank
	2. password: blank
	}*/
	
	@Test(priority=4)
	public void withBlankUserandPassword() {
		registerpage = new RegisterPage(driver);
		registerpage.clickCreateAcc();
		registerpage.createAccount(blank, blank);
		registerpage.verifyPasswordErrormesg();
		registerpage.verifyPasswordErrormesg();
}
	
	/*	TC Objective: Verify error message with junk userid and password as blank
	=======================================================================
	1. Launch the Browser
	2. Navigate to "BTC.com"
	3. Click on "Create Wallet" button
	4. Enter Email ID
	5. Enter blank password
	6. Check box the "terms of service"
	7. Click on "Create new wallet"

	Verification points
	---------------------------
	1. Verify warning message shown on password

	Input Data/Test Data:
	---------------------
	1. user name:abce!#$%^&@blocktrail.com
	2. password: !@#$%^&12

	}*/
	
	@Test(priority=5)
	public void withJunkUserandPassword() {
		registerpage = new RegisterPage(driver);
		registerpage.clickCreateAcc();
		registerpage.createAccount(JunkUserid, password);
		registerpage.verifyPasswordErrormesg();
		registerpage.verifyPasswordErrormesg();
}
	
	/*@AfterClass
	public void endTest() {
		driver.quit();
	}*/
}
