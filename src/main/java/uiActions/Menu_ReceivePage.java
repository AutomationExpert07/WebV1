package uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testBase.TestBase;

public class Menu_ReceivePage extends TestBase{
	public static final Logger log= Logger.getLogger(HomePage.class.getName());
	WebDriver driver;
	
	
	
	@FindBy(xpath="//span[contains(text(),'Receive')]")
	WebElement receiveMenu;
	
	@FindBy(xpath="//p[@class='address ng-binding']")
	WebElement userBTCAddress;
	
	public String ReceiveURL = "http://wallet.btc.btccom-autotest.blocktrail.com/#/wallet/receive";
	
	public Menu_ReceivePage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public void ClickReceiveMenu() {
		receiveMenu.click();		
	}
	
	public String BTCAddress() {
		String BitcoinCashAddress = userBTCAddress.getText();
		log.info("Available equalent BTC Amount" + BitcoinCashAddress);
		return BitcoinCashAddress;
	}
}
