package btcTestSuite;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import data.TestData;
import testBase.TestBase;
import uiActions.Menu_BuyPage;
import uiActions.HomePage;
import uiActions.Menu_MyWalletPage;
import uiActions.Menu_ReceivePage;
import uiActions.SetUp_RegisterPage;
import uiActions.Menu_SendPage;
import uiActions.Menu_SettingPage;
import uiActions.SetUp_SignInPage;

public class MyAddressLabel extends TestBase{
	public static final Logger log= Logger.getLogger(MyAddressLabel.class.getName());
	
	SetUp_RegisterPage registerpage;
	SetUp_SignInPage signInPage;
	HomePage homePage;
	Menu_MyWalletPage mywalletpage;
	Menu_SendPage sendpage;
	Menu_ReceivePage receivepage;
	Menu_BuyPage buypage;
	Menu_SettingPage settingpage;
	TestData testData;
	
	@BeforeTest
	public void setUp() throws IOException {
	log.info("initialzing all the variable and methods");
	init();
	}
	
	//TC objective Verify both the URL and the Page title of each menu when selected.
	
	@Test(priority=7)
	public void checkPageHeadingandURL() throws InterruptedException
	{
		testData = new TestData(driver);
		signInPage = new SetUp_SignInPage(driver);
		signInPage.SignIn(testData.existingUser, testData.password);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		homePage = new HomePage(driver);
		homePage.verifyLogoutbutton();
		
		homePage.SelectBTC();
		List<WebElement> dd_menu = driver.findElements(By.xpath("//ul[@class='dropdown-menu']//li/a"));
		for(WebElement element:dd_menu)
		{
			String innerhtml=element.getAttribute("innerHTML");
			innerhtml= innerhtml.trim();
			Thread.sleep(3000L);
			if(innerhtml.contentEquals(homePage.SelectRBitcoinCash))
			{
				homePage.SelectBTC();
				Thread.sleep(2000L);
				element.click();
				Thread.sleep(2000L);
				log.info("=============Values from dropdonw is "+innerhtml);
				Thread.sleep(2000L);
				mywalletpage = new Menu_MyWalletPage(driver);
				mywalletpage.clickWallet();
				Assert.assertEquals(driver.getCurrentUrl(), mywalletpage.MyWalletURL);
				Assert.assertEquals(homePage.getPageTitle(), homePage.MywalletTitle);
				log.info("Verified both, the URL and Page title for MyWallet");
				sendpage = new Menu_SendPage(driver);
				sendpage.ClickSendwithoutMessage();
			//	Thread.sleep(2000L);
				Assert.assertEquals(driver.getCurrentUrl(), sendpage.SendURL);
				Assert.assertEquals(homePage.getPageTitle(), homePage.sendpageTitle);
				log.info("Verified both, the URL and Page title for SendMenu");
				receivepage = new Menu_ReceivePage(driver);
				receivepage.ClickReceiveMenu();
				Assert.assertEquals(driver.getCurrentUrl(), receivepage.ReceiveURL);
				Assert.assertEquals(homePage.getPageTitle(), homePage.receivepageTitle);
				log.info("Verified both, the URL and Page title for receive page");
				buypage = new Menu_BuyPage(driver);
				buypage.clickBuyMenu();
				Assert.assertEquals(driver.getCurrentUrl(), buypage.buyURL);
				Assert.assertEquals(homePage.getPageTitle(), homePage.buyBCHpageTitle);
				log.info("Verified both, the URL and Page title for Buy Menu");
				settingpage = new Menu_SettingPage(driver);
				settingpage.clickSetting();
				Assert.assertEquals(driver.getCurrentUrl(), settingpage.settingURL);
				Assert.assertEquals(homePage.getPageTitle(), homePage.settingpageTitle);
				log.info("Verified both, the URL and Page title for Setting Page");
			}
			else if(innerhtml.contentEquals(homePage.SelectRBitcoin))
			{
				Thread.sleep(2000L);
				element.click();
				Thread.sleep(2000L);
				log.info("=============Values from dropdonw is "+innerhtml);
				Thread.sleep(2000L);
				mywalletpage = new Menu_MyWalletPage(driver);
				mywalletpage.clickWallet();
				Assert.assertEquals(driver.getCurrentUrl(), mywalletpage.MyWalletURL);
				Assert.assertEquals(homePage.getPageTitle(), homePage.MywalletTitle);
				log.info("Verified both, the URL and Page title for MyWallet");
				sendpage = new Menu_SendPage(driver);
				sendpage.ClickSendMenu();
			//	Thread.sleep(2000L);
				Assert.assertEquals(driver.getCurrentUrl(), sendpage.SendURL);
				Assert.assertEquals(homePage.getPageTitle(), homePage.sendpageTitle);
				log.info("Verified both, the URL and Page title for SendMenu");
				receivepage = new Menu_ReceivePage(driver);
				receivepage.ClickReceiveMenu();
				Assert.assertEquals(driver.getCurrentUrl(), receivepage.ReceiveURL);
				Assert.assertEquals(homePage.getPageTitle(), homePage.receivepageTitle);
				log.info("Verified both, the URL and Page title for receive page");
				buypage = new Menu_BuyPage(driver);
				buypage.clickBuyMenu();
				Assert.assertEquals(driver.getCurrentUrl(), buypage.buyURL);
				Assert.assertEquals(homePage.getPageTitle(), homePage.buyBTCpageTitle);
				log.info("Verified both, the URL and Page title for Buy Menu");
				settingpage = new Menu_SettingPage(driver);
				settingpage.clickSetting();
				Assert.assertEquals(driver.getCurrentUrl(), settingpage.settingURL);
				Assert.assertEquals(homePage.getPageTitle(), homePage.settingpageTitle);
				log.info("Verified both, the URL and Page title for Setting Page");
				}
		}
	}
	
	//TC objective Verify the calculation of the Bitcoin when a trancation is made.
	
		@Test(priority=10)
		public void MyAddresslableUpdate() throws InterruptedException 
		{
			
	/*		testData = new TestData(driver);
			signInPage = new SetUp_SignInPage(driver);
			signInPage.SignIn(testData.existingUser, testData.password);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			homePage = new HomePage(driver);
			homePage.verifyLogoutbutton();*/
			
			homePage.SelectBTC();
			
			List<WebElement> dd_menu = driver.findElements(By.xpath("//ul[@class='dropdown-menu']//li/a"));
			for(WebElement element:dd_menu)
			{
				String innerhtml=element.getAttribute("innerHTML");
				innerhtml= innerhtml.trim();
				Thread.sleep(3000L);
				if(innerhtml.contentEquals(homePage.SelectRBitcoinCash))
				{
					receivepage.ClickReceiveMenu();
					Thread.sleep(3000L);
					driver.findElement(By.xpath("//button[contains(text(),'My addresses')]")).click();
					//driver.findElements(By.xpath("//button[contains(text(),'My addresses')]"));
					List<WebElement> list = driver.findElements(By.xpath("//a[@class='pointer-clickable']"));
					list.get(1).click();
					String Label = "NameUpdate";
					driver.findElement(By.xpath("//input[@id='prompt']")).sendKeys(Label);
					driver.findElement(By.xpath("//input[@value='OK']")).click();
					
					List<WebElement> updatedName = driver.findElements(By.xpath("//td[@class='colCurrLabel ng-binding']"));
					String FirstName =  list.get(1).getText();
					Assert.assertEquals(Label, FirstName);
					log.info("The name is updated with"+FirstName);
					
					/*String BitcoinCashAddress = receivepage.BTCAddress();
					log.info("The Bitcoin Address genereated is "+ BitcoinCashAddress);
					sendpage.ClickSendwithoutMessage();
					Thread.sleep(3000L);
					//if you want to send the bit coin to your own account Enable below line
					//sendpage.RecipientAdd(BitcoinCashAddress);
					
					// To send to External person use below address.
					sendpage.RecipientAdd(sendpage.xAddress);
					Thread.sleep(3000L);
					sendpage.EnterAmount(sendpage.Amount);
					sendpage.OptimalBCHFee();
					Thread.sleep(3000L);
					sendpage.SendButton();
					sendpage.BTCSentConfirmationMessage();
					sendpage.sendPasswordconfirmation(testData.password);
					sendpage.BCHconfirmTransactionID();
					sendpage.ValidatetheTransaction(homePage.availbeBalance(), sendpage.OptimalFee1, sendpage.Amount);*/
				}
				else if(innerhtml.contentEquals(homePage.SelectRBitcoin))
				{
					homePage.SelectBTC();
					Thread.sleep(2000L);
					
					driver.findElement(By.xpath("//button[contains(text(),'My addresses')]")).click();
					//driver.findElements(By.xpath("//button[contains(text(),'My addresses')]"));
					List<WebElement> list = driver.findElements(By.xpath("//a[@class='pointer-clickable']"));
					list.get(1).click();
					String Label = "NameUpdate";
					driver.findElement(By.xpath("//input[@id='prompt']")).sendKeys(Label);
					driver.findElement(By.xpath("//input[@value='OK']")).click();
					
					List<WebElement> updatedName = driver.findElements(By.xpath("//td[@class='colCurrLabel ng-binding']"));
					String FirstName =  list.get(1).getText();
					Assert.assertEquals(Label, FirstName);
					log.info("The name is updated with"+FirstName);
					
					/*
					element.click();
					Thread.sleep(2000L);
					receivepage.ClickReceiveMenu();
					Thread.sleep(2000L);
					
					driver.findElement(By.xpath("//button[contains(text(),'My addresses')]")).click();
					//driver.findElements(By.xpath("//button[contains(text(),'My addresses')]"));
					List<WebElement> list = driver.findElements(By.xpath("//a[@class='pointer-clickable']"));
					list.get(1).click();
					String Label = "NameUpdate";
					driver.findElement(By.xpath("//input[@id='prompt']")).sendKeys(Label);
					driver.findElement(By.xpath("//input[@value='OK']")).click();
					
					List<WebElement> updatedName = driver.findElements(By.xpath("//td[@class='colCurrLabel ng-binding']"));
					String FirstName =  list.get(1).getText();
					Assert.assertEquals(Label, FirstName);
					log.info("The name is updated with"+FirstName);
					String BitcoinAddress = receivepage.BTCAddress();
					log.info("The Bitcoin Address genereated is "+ BitcoinAddress);
					sendpage.ClickSendwithoutMessage();
					Thread.sleep(3000L);	
					//if you want to send the bit coin to your own account Enable below line
					//sendpage.RecipientAdd(BitcoinCashAddress);
					
					// To send to External person use below address.
					sendpage.RecipientAdd(sendpage.xAddress);
					Thread.sleep(3000L);
					sendpage.EnterAmount(sendpage.Amount);
					sendpage.OptimalBTCFee();
					Thread.sleep(3000L);
					sendpage.SendButton();
					sendpage.BTCSentConfirmationMessage();
					sendpage.sendPasswordconfirmation(testData.password);
					sendpage.BTCconfirmTransactionID();
					sendpage.ValidatetheTransaction(homePage.availbeBalance(), sendpage.OptimalFee1, sendpage.Amount);*/
					log.info("Lable check is done");
				}
			}
		}

	@AfterClass
	public void endTest() {
		driver.get("http://regtest-controls.btc.btccom-autotest.blocktrail.com/rBTC/generate-block");
		driver.get("http://regtest-controls.btc.btccom-autotest.blocktrail.com/rBCH/generate-block");
		driver.navigate().back();
		driver.navigate().back();
		driver.quit();
	}

}
