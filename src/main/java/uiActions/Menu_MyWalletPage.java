package uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase.TestBase;

public class Menu_MyWalletPage extends TestBase{
	public static final Logger log= Logger.getLogger(Menu_MyWalletPage.class.getName());
	WebDriver driver;
	
	public String MyWalletURL ="http://wallet.btc.btccom-autotest.blocktrail.com/#/wallet";
	
	@FindBy(xpath="//span[contains(text(),'My Wallet')]")
	WebElement myWalletMenu;
	
	@FindBy(xpath="//span[contains(text(),'My Wallet')]")
	WebElement myWalletPageTile;
	
	@FindBy(xpath="//h1[contains(text(),'No transactions, yet')]")
	WebElement NoTransYet;
	
	@FindBy(xpath="//a[contains(text(),'Funding your wallet')]")
	WebElement FundyourWalletLink;
	
	public Menu_MyWalletPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickWallet()
	{	
		myWalletMenu.click();
	}
	
	public String verifyNoTransactionYet() {
		return NoTransYet.getText();
	}
	
	public void FundyourWalletLink() {
		FundyourWalletLink.click();
	}
	
}
