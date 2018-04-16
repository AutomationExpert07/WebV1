package uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase.TestBase;

public class RegtestAutoTextPage extends TestBase{
	public static final Logger log= Logger.getLogger(RegtestAutoTextPage.class.getName());
	WebDriver driver;
	
	public String launchBTCRegtest = "http://regtest-controls.btc.btccom-autotest.blocktrail.com/rBTC";

	@FindBy(xpath="//input[@value='read last email']")
	WebElement sendEmail;
	
	@FindBy(xpath="//input[@placeholder='email address']")
	WebElement pwrdRecoveryEmailID;
	
	@FindBy(xpath="//span[contains(text(),'Change your password')]")
	WebElement ClickChangePawrd;
	
	public RegtestAutoTextPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public void launchRegTextAutoPage(String url) {
		driver.get(url);
		
	}
	
	public void recoveryEmail(String emailID) {
		pwrdRecoveryEmailID.sendKeys(emailID);
	}
	
	public void sendEmailButton() {
		sendEmail.click();
	}
	
	public void ClickChangepwrd() {
		ClickChangePawrd.click();
	}
}
