package BTC.BTCcom;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import testBase.TestBase;
import uiActions.SignInPage;

public class TC001 extends TestBase{
	
	
	SignInPage signIn;
	String UserName = "Sohaibcool@gmail.com";
	String password="Allah@123";
	String blank ="";
	

	
	@BeforeTest
	public void setUp() {
		log.info("initialzing all the variable and methods");
		init();	
	}
	
	@Test(priority=1)
	public void TC0001() throws InterruptedException {
		signIn = new SignInPage(driver);
		log.info("________________Started execution__________");
		signIn.SignIn(UserName, password);
		//assert.assertEquals(arg0, arg1);	
	}
	
	@Test
	public void VerifywithInvalidData() throws InterruptedException
	{
		signIn.SignIn(UserName, blank);
	}

	@AfterClass
	public void endTest() {
		driver.quit();
	}
	
}
