package btcTestSuite;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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
	public void setUp() throws IOException {
	log.info("initialzing all the variable and methods");
	init();
	}
	
	
	@Test(priority=1)
	public void createWallet() throws InterruptedException {
		signInPage = new SignInPage(driver);
		signInPage.SignIn(username, password);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		homePage = new HomePage(driver);
		homePage.verifyLogoutbutton();
		Assert.assertEquals(driver.getCurrentUrl(), registerpage.HomeWalletPage);
		
	//	Select bitCoinType = new Select(driver.findElement(By.xpath("//span[@class='text ng-binding']")));
	//	WebElement btcType = driver.findElement(By.xpath("//span[(@class='caret')]"));
		driver.findElement(By.xpath("//div[starts-with(@class,'wallets-list dropdown')]")).click();
		//driver.findElement(By.xpath("//a[@class='pointer ng-binding'][1]")).click();
		
		List<WebElement> dd_menu = driver.findElements(By.xpath("//ul[@class='dropdown-menu']//li/a"));
		
		for(WebElement element:dd_menu)
		{
			String innerhtml=element.getAttribute("innerHTML");
			innerhtml= innerhtml.trim();
			if(innerhtml.contentEquals("tBTC (0.0985 tBTC) mywallet-2674b907dc5de7bc"))
			{
				//element.toString();
				
				element.click();
				System.out.println("values from dropdonw is "+innerhtml);
				break;
				
			}
			
		}
		
		/*for(int i=0;i<dd_menu.size();i++) {
			WebElement element=dd_menu.get(i);
			String innerhtml=element.getAttribute("innerHTML");
			innerhtml= innerhtml.trim();
			if(innerhtml.contentEquals("tBTC (0.0985 tBTC) mywallet-2674b907dc5de7bc"))
			{
				element.toString();
				
				element.click();
				break;
			}
			System.out.println("values from dropdonw is "+innerhtml);
			
		}*/
			
	//	Select bitCoinType = new Select(btcType);
	//	bitCoinType.selectByIndex(0);
		
	//	Select bitCoinType = new Select(driver.findElement(By.xpath("//span[starts-with(@class,'text ng-binding')]")));
		
	//	bitCoinType.selectByIndex(1);
		
		// Object selecttype = driver.findElement(By.xpath("//button[@type='button']")).click();;
		 
	//	Select bitCoinType = new Select(driver.findElement(By.xpath("//button[@type='button']")));
	//	bitCoinType.selectByIndex(1);
		log.info("_____________Home URL is been verified______________");
	}
	
	@AfterClass
	public void endTest() {
		//driver.quit();
	}

}
