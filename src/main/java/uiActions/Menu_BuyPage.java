package uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase.TestBase;

public class Menu_BuyPage extends TestBase{
	public static final Logger log= Logger.getLogger(Menu_BuyPage.class.getName());
	WebDriver driver;
	
	public String buyURL = "http://wallet.btc.btccom-autotest.blocktrail.com/#/wallet/buy/choose";
	
	@FindBy(xpath="//span[contains(text(),'Buy')]")
	WebElement BuyMenu;
	
	public Menu_BuyPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickBuyMenu() {
		BuyMenu.click();
		log.info("Clicked on Buy Menu");
	}
	
	
}
