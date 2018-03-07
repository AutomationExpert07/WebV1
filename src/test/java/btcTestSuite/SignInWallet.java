package btcTestSuite;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import testBase.TestBase;
import uiActions.HomePage;
import uiActions.RegisterPage;
import uiActions.SignInPage;

public class SignInWallet extends TestBase{
	
	RegisterPage registerpage;
	SignInPage signInPage;
	HomePage homePage;
	String username="Sohaibcool@gmail.com";
	String password="Allah@123";
	
	@BeforeTest
	public void setUp() {
	log.info("initialzing all the variable and methods");
	init();
	}
	
	
	@Test(priority=1)
	public void createWallet() throws InterruptedException {
		signInPage = new SignInPage(driver);
		signInPage.SignIn(username, password);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().wait(3000);
		Assert.assertEquals(driver.getCurrentUrl(), "https://wallet.btc.com/#/wallet");
		homePage = new HomePage(driver);
		homePage.verifyLogoutbutton();
		log.info("_____________Home URL is been verified______________");
	}
	
	@AfterClass
	public void endTest() {
		driver.quit();
	}

}
