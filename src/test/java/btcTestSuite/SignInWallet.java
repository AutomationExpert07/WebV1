package btcTestSuite;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import testBase.TestBase;
import uiActions.HomePage;
import uiActions.SetUp_RegisterPage;
import uiActions.SetUp_SignInPage;

public class SignInWallet extends TestBase{
	
	SetUp_RegisterPage registerpage;
	SetUp_SignInPage signInPage;
	HomePage homePage;
	String username="Sohaibcool10@gmail.com";
	String password="Hello@123";
	double Amount =1;
	String xAddress ="2NCj5iUvuVxfWBNV64kGBeqHiNZBJEqqQtX";
	String Transactions;
	
	@BeforeTest
	public void setUp() throws IOException {
	log.info("initialzing all the variable and methods");
	init();
	}
	
	
	@Test(priority=7)
	public void createWallet() throws InterruptedException {
		signInPage = new SetUp_SignInPage(driver);
		signInPage.SignIn(username, password);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		homePage = new HomePage(driver);
		homePage.verifyLogoutbutton();
	
	//	Assert.assertEquals(driver.getCurrentUrl(), registerpage.HomeWalletPage);
		
	//	Select bitCoinType = new Select(driver.findElement(By.xpath("//span[@class='text ng-binding']")));
	//	WebElement btcType = driver.findElement(By.xpath("//span[(@class='caret')]"));
		driver.findElement(By.xpath("//div[starts-with(@class,'wallets-list dropdown')]")).click();
		//driver.findElement(By.xpath("//a[@class='pointer ng-binding'][1]")).click();
		
		List<WebElement> dd_menu = driver.findElements(By.xpath("//ul[@class='dropdown-menu']//li/a"));
		for(WebElement element:dd_menu)
		{
			//driver.findElement(By.xpath("//div[starts-with(@class,'wallets-list dropdown')]")).click();
			Thread.sleep(3000L);
			String innerhtml=element.getAttribute("innerHTML");
			Thread.sleep(3000L);
			innerhtml= innerhtml.trim();
			Thread.sleep(3000L);
			if(innerhtml.contentEquals(homePage.SelectRBitcoinCash))
			{
				//element.toString();
				Thread.sleep(5000L);
				driver.findElement(By.xpath("//div[starts-with(@class,'wallets-list dropdown')]")).click();
				Thread.sleep(3000L);
				element.click();
				Thread.sleep(3000L);
				System.out.println("values from dropdonw is "+innerhtml);
				Thread.sleep(3000L);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//span[contains(text(),'My Wallet')]")).click();
			//	Assert.assertEquals(driver.getCurrentUrl(), "Transactions");
			//	Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='pageTitle ng-binding']")).getText(), "Transactions");
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//span[contains(text(),'Send')]")).click();
				
				Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='pageTitle ng-binding']")).getText(), "Send");
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//span[contains(text(),'Receive')]")).click();
				Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='pageTitle ng-binding']")).getText(), "Receive");
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//span[contains(text(),'Settings')]")).click();
				Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='pageTitle ng-binding']")).getText(), "Settings");
				
				//Sending Bit cash to own account
				driver.findElement(By.xpath("//span[contains(text(),'Receive')]")).click();
				Thread.sleep(3000L);
				String BitcoinCashAddress = driver.findElement(By.xpath("//p[@class='address ng-binding']")).getText();
				System.out.println("the address which i want to send is "+ BitcoinCashAddress);
				driver.findElement(By.xpath("//span[contains(text(),'Send')]")).click();
				Thread.sleep(3000L);
				driver.findElement(By.xpath("//input[@id='recipient']")).sendKeys(BitcoinCashAddress);
				//driver.findElement(By.xpath("//input[@id='recipient']")).sendKeys(xAddress);
				
				Thread.sleep(3000L);
				driver.findElement(By.xpath("//input[@id='amount']")).sendKeys(Double.toString(Amount));
				Thread.sleep(3000L);
				driver.findElement(By.xpath("//button[@class='btn btn-lg btn-primary']")).click();
				String ConfirmMessage = driver.findElement(By.xpath("//span[@translate='SENDING_CONFIRM_MSG']")).getText();
				driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
				driver.findElement(By.xpath("//input[@type='submit']")).click();
				String TransactionID = driver.findElement(By.xpath("//a[@target='_blank']")).getAttribute("href");
				String TransID = TransactionID.replace("https://www.blocktrail.com/tBCC/tx/", "");
				System.out.println(TransID);
				int Length = TransID.length();
				System.out.println("Transaction is success as it has 64 characters init :)" +Length);			
				Assert.assertEquals(Length, 64);
				
			//	Assert.assertEquals(driver.findElement(By.xpath("//h4[contains(text(),' Transaction Sent.')]")).getText()," Transaction Sent.");
				driver.findElement(By.xpath("//input[@type='submit']")).click();
				
				String ShownBTC = driver.findElement(By.xpath("//h1[@class='ng-binding']")).getText();
				String ActualBTC = ShownBTC.replace("Bitcoin Cash REGTEST", "");
			//	driver.get("http://regtest-controls.btc.btccom-autotest.blocktrail.com/rBCH/generate-block");
			//	driver.navigate().back();
				Thread.sleep(2000L);

			}
			else if(innerhtml.contentEquals(homePage.SelectRBitcoin))
			{
				//element.toString();	
				element.click();
				System.out.println("values from dropdonw is "+innerhtml);
				driver.findElement(By.xpath("//span[contains(text(),'My Wallet')]")).click();
				Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='pageTitle ng-binding']")).getText(), "Transactions");
				driver.findElement(By.xpath("//span[contains(text(),'Send')]")).click();	
				if(driver.findElement(By.xpath("//a[@ng-click='dismiss()']")).isDisplayed())
				{
				driver.findElement(By.xpath("//a[@ng-click='dismiss()']")).click();
				}
				Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='pageTitle ng-binding']")).getText(), "Send");
				driver.findElement(By.xpath("//span[contains(text(),'Receive')]")).click();
				Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='pageTitle ng-binding']")).getText(), "Receive");
				driver.findElement(By.xpath("//span[contains(text(),'Settings')]")).click();
				Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='pageTitle ng-binding']")).getText(), "Settings");

				//Store the Actual value before transaction.
				String ShownBTC = driver.findElement(By.xpath("//h1[@class='ng-binding']")).getText();
				double fees = 0.00009490;
				String arr[] = ShownBTC.split(" ", 2);

				String firstStr = arr[0];   //the
				String theRestStr = arr[1];     //quick brown fox
				double BeforeTransc = Double.parseDouble(firstStr);
				
				//Store the Converted currency before transaction.
				String ActualAmount12 = driver.findElement(By.xpath("//small[@class='ng-binding']")).getText();
				String abcde = ActualAmount12.substring(1);
				System.out.println("Actual Amount availble" + BeforeTransc);
				driver.findElement(By.xpath("//span[contains(text(),'Receive')]")).click();
				Thread.sleep(5000L);
				//Get the Address of own bitcoin address.
				String BitcoinCashAddress = driver.findElement(By.xpath("//p[@class='address ng-binding']")).getText();
				System.out.println("the address which i want to send is "+ BitcoinCashAddress);
				driver.findElement(By.xpath("//span[contains(text(),'Send')]")).click();
				//Thread.sleep(3000L);
				driver.findElement(By.xpath("//input[@id='recipient']")).sendKeys(BitcoinCashAddress);
				//driver.findElement(By.xpath("//input[@id='recipient']")).sendKeys(xAddress);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//input[@id='amount']")).sendKeys(Double.toString(Amount));
				//driver.findElement(By.xpath("//input[@id='amount']")).sendKeys(Amount);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
				//Get the Optimal fee
				String OptimalAmount = driver.findElement(By.xpath("//span[@ng-if='fees.optimal && displayFee']")).getText();
				String OptimalAmount1 = OptimalAmount.replace(" rBTC", "");
				double OptimalFee = Double.parseDouble(OptimalAmount1);
				System.out.println("the Otimal Amount Fee for"+ Amount +" is"+ OptimalFee);
				
				//BTC value after selecting the currency type
				String convertedAmount = driver.findElement(By.xpath("//span[@class='altCurrency ng-binding']")).getText();
				System.out.println("the converted Amount is"+ convertedAmount);
				
				
				driver.findElement(By.xpath("//button[@class='btn btn-lg btn-primary']")).click();
				String ConfirmMessage = driver.findElement(By.xpath("//span[@translate='SENDING_CONFIRM_MSG']")).getText();
				driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
				driver.findElement(By.xpath("//input[@type='submit']")).click();
				//Confirming the 64 charater transaction ID.
				String TransactionID = driver.findElement(By.xpath("//a[@target='_blank']")).getAttribute("href");
				String TransID = TransactionID.replace("https://tchain.btc.com/", "");
				System.out.println(TransID);
				int Length = TransID.length();
				System.out.println("Transaction is success as it has 64 characters init :)" +Length);
				Assert.assertEquals(Length, 64);
				//if (Length = )
				Assert.assertEquals(driver.findElement(By.xpath("//h4[contains(text(),' Transaction Sent.')]")).getText(),"Transaction Sent.");
				driver.findElement(By.xpath("//input[@type='submit']")).click();
				Thread.sleep(3000L);		
			//	driver.get("http://regtest-controls.btc.btccom-autotest.blocktrail.com/rBTC/generate-block");
			//	driver.navigate().back();
				Thread.sleep(3000L);
				String AfterTransactionBTC = driver.findElement(By.xpath("//h1[@class='ng-binding']")).getText();
				String arr1[] = AfterTransactionBTC.split(" ", 2);

				String firstWord = arr1[0];   //the
				String theRestword = arr1[1];     //quick brown fox
				
				double AfterTranscBTC1 = Double.parseDouble(firstWord);

				System.out.println(AfterTranscBTC1);
				
				double afterTransaction = BeforeTransc -(Amount +OptimalFee);
				System.out.println("The Transaction after flow is "+ afterTransaction);
				}	
		}
		log.info("_____________Home URL is been verified______________");
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
