package uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testBase.TestBase;

public class HomePage extends TestBase{
	public static final Logger log= Logger.getLogger(HomePage.class.getName());
	WebDriver driver;
	
	public String MywalletTitle ="Transactions";
	public String sendpageTitle ="Send";
	public String receivepageTitle ="Receive";
	public String buyBCHpageTitle ="Buy rBitcoin Cash";
	public String buyBTCpageTitle ="Buy rBitcoin";
	public String settingpageTitle ="Settings";	
	public String SelectRBitcoin = "rBitcoin";
	public String SelectRBitcoinCash = "rBitcoin Cash";	
	public String wrongPasswordmatch = "The passwords don't match";
	public String settingURL = "http://wallet.btc.btccom-autotest.blocktrail.com/#/wallet/settings/profile";
	
	@FindBy(xpath="//a[contains(text(),'Logout')]")
	WebElement Logout;

	@FindBy(xpath="//h1[@class='ng-binding']")
	WebElement availbleBitcoin;


	@FindBy(xpath="//div[starts-with(@class,'wallets-list dropdown')]")
	WebElement selectBTCorBCH;
	
	@FindBy(xpath="//h1[@class='pageTitle ng-binding']")
	WebElement HomePageTitle;
	
	@FindBy(xpath="//small[@class='ng-binding']")
	WebElement BTCequaltAmt;

	public void SelectBTC() {
		selectBTCorBCH.click();
	}
	
	public double availbeBalance() {
		String ShownBTC = availbleBitcoin.getText();
		String arr[] = ShownBTC.split(" ", 2);

		String firstStr = arr[0];  
		String theRestStr = arr[1];    
		double BeforeTransc = Double.parseDouble(firstStr);
		log.info("Available Balnace" + BeforeTransc);
		return BeforeTransc;
	}
	
	public double BTCEqualentValue() {
		String ActualAmount12 = BTCequaltAmt.getText();
		String equalentamount = ActualAmount12.substring(1);
		double equlBTC = Double.parseDouble(equalentamount);
		log.info("Available equalent BTC Amount" + equlBTC);
		return equlBTC;
	}
	
	public HomePage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public void Logout() {
		Logout.click();
	}
	
	public void verifyLogoutbutton() {
		Logout.isDisplayed();
	}
	
	public String getPageTitle() {
		return HomePageTitle.getText();
	}
}
