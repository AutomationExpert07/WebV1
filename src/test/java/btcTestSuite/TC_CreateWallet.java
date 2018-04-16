package btcTestSuite;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import data.TestData;
import testBase.TestBase;
import uiActions.HomePage;
import uiActions.SetUp_RegisterPage;

public class TC_CreateWallet extends TestBase{
	
	SetUp_RegisterPage registerpage;
	HomePage homePage;
	TestData testData;
	
	@BeforeTest
	public void setUp() throws IOException {
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
	
	@Test(priority=7)
	public void createWallet() {
		log.info("___________createing Wallet__________");
		registerpage = new SetUp_RegisterPage(driver);
		registerpage.clickCreateAccLink();
		testData = new TestData(driver);
		registerpage.createAccount(testData.username, testData.password);
		//registerpage.passwordCrack();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Assert.assertEquals(SetUp_RegisterPage.registerURL, "https://wallet.btc.com/#/setup/register");
		log.info("_____________Register URL is been verified______________");
		registerpage.confirmPassword(testData.password);
		registerpage.downloadWalletbkup();
		registerpage.deletePwdPdf();
		Assert.assertEquals(registerpage.HomeWalletPage, "https://wallet.btc.com/#/wallet");
		
		log.info("_____________Home URL is been verified______________");
		homePage = new HomePage(driver);
		homePage.Logout();
	}
	
	@Test(priority=1)
	public void ConfirmWithWorngPassword() {
		log.info("___________createing Wallet__________");
		registerpage = new SetUp_RegisterPage(driver);
		registerpage.clickCreateAccLink();
		testData = new TestData(driver);
		registerpage.createAccount(testData.username, testData.password);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		registerpage.confirmPassword(testData.confrimWrongPsw);
		Assert.assertEquals(registerpage.confirmWrongPassword(), "The passwords don't match");
	}
	
/*		TC Objective: Verify error message for wrong email id while Creating Wallet
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
		log.info("___________createing Wallet with Blank User ID__________");
		registerpage = new SetUp_RegisterPage(driver);
		registerpage.clickCreateAccLink();
		registerpage.createAccount(testData.blank, testData.password);
		registerpage.verifyemailErrormesg();
	}

/*		TC Objective: Verify error message for wrong password while Creating Wallet
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
		log.info("___________createing Wallet with Blank Password__________");
		registerpage = new SetUp_RegisterPage(driver);
		registerpage.clickCreateAccLink();
		registerpage.createAccount(testData.username, testData.blank);
		registerpage.verifyPasswordErrormesg();
}
	
/*		TC Objective: Verify error message with both userid and password as blank
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
		log.info("___________createing Wallet with Blank User ID and Blank Password__________");
		registerpage = new SetUp_RegisterPage(driver);
		registerpage.clickCreateAcc();
		registerpage.createAccount(testData.blank, testData.blank);
		registerpage.verifyPasswordErrormesg();
		registerpage.verifyPasswordErrormesg();
}
	
/*		TC Objective: Verify error message with junk userid and password as blank
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
	2. password: !@#$%^&12*/

//	}
	
	@Test(priority=5)
	public void withJunkLongUserandPassword() {
		log.info("___________createing Wallet with Speical Characters__________");
		registerpage = new SetUp_RegisterPage(driver);
		registerpage.clickCreateAcc();
		registerpage.createAccount(testData.JunkUserid, testData.password);
		registerpage.verifyemailErrormesg();
}
	
	/*		TC Objective: Verify termsOfService is navigating to right page
	=======================================================================
	*/
	@Test(priority=6)
	public void termsOfServiceLink() {
		log.info("___________Checking the link is navigating to right page__________");
		registerpage.ClickTermsofserviceLink();
		Iterator<String> itr = getAllWindows();
		String parentWindow = itr.next();
		String childWindow = itr.next();
		driver.switchTo().window(childWindow);
		Assert.assertEquals(driver.getCurrentUrl(), registerpage.termsOfServiceURL);
		driver.findElement(By.xpath("//h1[contains(text(),'BTC.COM – TERMS OF USE')]")).isDisplayed();
		driver.close();
		driver.switchTo().window(parentWindow);
	}
	
	@AfterClass
	public void endTest() {
	driver.quit();
	}
}
