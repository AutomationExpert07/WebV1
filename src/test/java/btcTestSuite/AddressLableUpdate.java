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
import uiActions.GenericFunctions;
import uiActions.HomePage;
import uiActions.Menu_SendPage;
import uiActions.Menu_SettingPage;
import uiActions.SetUp_RegisterPage;
import uiActions.SetUp_SignInPage;

public class AddressLableUpdate extends TestBase{
	public static final Logger log= Logger.getLogger(AddressLableUpdate.class.getName());
	SetUp_RegisterPage registerpage;
	HomePage homePage;
	SetUp_SignInPage signInPage;
	TestData testData;
	Menu_SettingPage settingpage;
	Menu_SendPage sendpage;
	GenericFunctions genericFunct;
	
	
	@BeforeTest
	public void setUp() throws IOException {
	log.info("initialzing all the variable and methods");
	init();
	}
	
	
	@Test
	public void checkExchange() throws InterruptedException {
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
				
				log.info("_____________At Home Page______________");
				
				settingpage = new Menu_SettingPage(driver);
				settingpage.clickSetting();
				List<WebElement> button = driver.findElements(By.xpath("//div[@class='iit-10 dropdown']//button[@type='button']"));
				button.get(0).click();
				List<WebElement> Currency = driver.findElements(By.xpath("//ul[@role='menu']//li[@role='menuitem']"));
				System.out.println(Currency.get(1).getText());
				Currency.get(1).click();
				driver.findElement(By.xpath("//button[contains(text(),'Save Settings')]")).click();
				Thread.sleep(2000L);
				sendpage = new Menu_SendPage(driver);
				sendpage.ClickSendwithoutMessage();
			//	sendpage.ClickSendMenu();
				Thread.sleep(2000L);
				sendpage.RecipientAdd(testData.xAddress);
				sendpage.EnterAmount(testData.Amount);
				Thread.sleep(2000L);
				String convertedAmt = sendpage.CovertedCurrecyAmount();
				System.out.println(convertedAmt);
				Thread.sleep(2000L);
				Assert.assertEquals(convertedAmt, "$ 1000.01");
				
				
				settingpage.clickSetting();
				List<WebElement> button1 = driver.findElements(By.xpath("//div[@class='iit-10 dropdown']//button[@type='button']"));
				button1.get(0).click();
				List<WebElement> Currency1 = driver.findElements(By.xpath("//ul[@role='menu']//li[@role='menuitem']"));
				System.out.println(Currency1.get(11).getText());
				Currency1.get(11).click();
				driver.findElement(By.xpath("//button[contains(text(),'Save Settings')]")).click();
				sendpage.ClickSendwithoutMessage();
				sendpage.RecipientAdd(testData.xAddress);
				sendpage.EnterAmount(testData.Amount);
				String convertedAmt1 = sendpage.CovertedCurrecyAmount();
				System.out.println(convertedAmt1);
				Assert.assertEquals(convertedAmt1, "£ 5000.06");
				
			
				settingpage.clickSetting();
				List<WebElement> button2 = driver.findElements(By.xpath("//div[@class='iit-10 dropdown']//button[@type='button']"));
				button2.get(0).click();
				List<WebElement> Currency2 = driver.findElements(By.xpath("//ul[@role='menu']//li[@role='menuitem']"));
				System.out.println(Currency2.get(0).getText());
				Currency2.get(0).click();
				driver.findElement(By.xpath("//button[contains(text(),'Save Settings')]")).click();
				sendpage.ClickSendwithoutMessage();
				sendpage.RecipientAdd(testData.xAddress);
				sendpage.EnterAmount(testData.Amount);
				String convertedAmt2 = sendpage.CovertedCurrecyAmount();
				System.out.println(convertedAmt2);
				Assert.assertEquals(convertedAmt2, "€ 900.1");
			}
			else if(innerhtml.contentEquals(homePage.SelectRBitcoin))
			{
				//element.toString();	
				element.click();
				System.out.println("values from dropdonw is "+innerhtml);
				
				log.info("_____________At Home Page______________");
				settingpage = new Menu_SettingPage(driver);
				settingpage.clickSetting();
				
/*				List<WebElement> button = driver.findElements(By.xpath("//div[@class='iit-10 dropdown']//button[@type='button']"));
				button.get(0).click();*/
				genericFunct = new GenericFunctions(driver);
				genericFunct.ClickCurrencyDropdown();
				List<WebElement> Currency = driver.findElements(By.xpath("//ul[@role='menu']//li[@role='menuitem']"));
				System.out.println(Currency.get(1).getText());
				Currency.get(1).click();
				driver.findElement(By.xpath("//button[contains(text(),'Save Settings')]")).click();
				
				sendpage = new Menu_SendPage(driver);
				sendpage.ClickSendMenu();
				sendpage.RecipientAdd(testData.xAddress);
				sendpage.EnterAmount(testData.Amount);
				String convertedAmt = sendpage.CovertedCurrecyAmount();
				System.out.println(convertedAmt);
				Assert.assertEquals(convertedAmt, "$ 10000.11");
				
				settingpage.clickSetting();
/*				List<WebElement> button1 = driver.findElements(By.xpath("//div[@class='iit-10 dropdown']//button[@type='button']"));
				button1.get(0).click();*/
				genericFunct.ClickCurrencyDropdown();
				List<WebElement> Currency1 = driver.findElements(By.xpath("//ul[@role='menu']//li[@role='menuitem']"));
				System.out.println(Currency1.get(11).getText());
				Currency1.get(11).click();
				driver.findElement(By.xpath("//button[contains(text(),'Save Settings')]")).click();
				sendpage.ClickSendwithoutMessage();
				sendpage.RecipientAdd(testData.xAddress);
				sendpage.EnterAmount(testData.Amount);
				String convertedAmt1 = sendpage.CovertedCurrecyAmount();
				log.info("_____________At Home Page______________"+convertedAmt1);
				//System.out.println(convertedAmt1);
				Assert.assertEquals(convertedAmt1, "£ 50000.55");
				
				
				//+++++++++++++++++++++++++++++++++++++++++++++
				settingpage.clickSetting();
				List<WebElement> button2 = driver.findElements(By.xpath("//div[@class='iit-10 dropdown']//button[@type='button']"));
				button2.get(0).click();
				List<WebElement> Currency2 = driver.findElements(By.xpath("//ul[@role='menu']//li[@role='menuitem']"));
				System.out.println(Currency2.get(0).getText());
				Currency2.get(0).click();
				driver.findElement(By.xpath("//button[contains(text(),'Save Settings')]")).click();
				sendpage.ClickSendwithoutMessage();
				sendpage.RecipientAdd(testData.xAddress);
				sendpage.EnterAmount(testData.Amount);
				String convertedAmt2 = sendpage.CovertedCurrecyAmount();
				System.out.println(convertedAmt2);
				Assert.assertEquals(convertedAmt2, "€ 9000.99");
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
