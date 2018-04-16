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
import uiActions.HomePage;
import uiActions.Menu_SendPage;
import uiActions.Menu_SettingPage;
import uiActions.SetUp_RegisterPage;
import uiActions.SetUp_SignInPage;

public class CalculateExchangeRates2 extends TestBase{
	public static final Logger log= Logger.getLogger(CalculateExchangeRates2.class.getName());
	SetUp_RegisterPage registerpage;
	HomePage homePage;
	SetUp_SignInPage signInPage;
	TestData testData;
	Menu_SettingPage settingpage;
	Menu_SendPage sendpage;
	
	@BeforeTest
	public void setUp() throws IOException {
	log.info("initialzing all the variable and methods");
	init();
	}
	
	@Test
	public void coinCurrencyExchange() {
		log.info("___________createing Wallet__________");
		
		testData = new TestData(driver);
		signInPage = new SetUp_SignInPage(driver);
		signInPage.SignIn(testData.existingUser, testData.password);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		log.info("_____________At Home Page______________");
		settingpage = new Menu_SettingPage(driver);
		settingpage.clickSetting();
		List<WebElement> button = driver.findElements(By.xpath("//div[@class='iit-10 dropdown']//button[@type='button']"));
		button.get(0).click();
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
		System.out.println(convertedAmt1);
		
		//+++++++++++++++++++++++++++++++++++++++++++++++++
		/*settingpage.clickSetting();
		button.get(0).click();
		System.out.println(Currency.get(2).getText());
		Currency.get(2).click();
		sendpage.ClickSendwithoutMessage();
		sendpage.RecipientAdd(testData.xAddress);
		sendpage.EnterAmount(testData.Amount);
		String convertedAmt3 = sendpage.CovertedCurrecyAmount();
		System.out.println(convertedAmt2);
		
		settingpage.clickSetting();
		button.get(0).click();
		System.out.println(Currency.get(3).getText());
		Currency.get(3).click();
		
		
		testData = new TestData(driver);
		registerpage.createAccount(testData.username, testData.password);
		//registerpage.passwordCrack();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Assert.assertEquals(SetUp_RegisterPage.registerURL, "https://wallet.btc.com/#/setup/register");
		log.info("_____________Register URL is been verified______________");
		registerpage.confirmPassword(testData.password);
		registerpage.downloadWalletbkup();
		Assert.assertEquals(registerpage.HomeWalletPage, "https://wallet.btc.com/#/wallet");
		log.info("_____________Home URL is been verified______________");*/
		
		homePage = new HomePage(driver);
		homePage.Logout();
	}
	
	@AfterClass
	public void endTest() {
		driver.quit();
	}
}
