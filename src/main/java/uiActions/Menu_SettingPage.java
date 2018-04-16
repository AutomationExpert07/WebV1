package uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testBase.TestBase;

public class Menu_SettingPage extends TestBase{
	public static final Logger log= Logger.getLogger(Menu_SettingPage.class.getName());
	WebDriver driver;
	
	public String settingURL = "http://wallet.btc.btccom-autotest.blocktrail.com/#/wallet/settings/profile";
	
	@FindBy(xpath="//span[contains(text(),'Settings')]")
	WebElement setting;
	
	@FindBy(xpath="//input[@id='email']")
	WebElement getEmail;
	
	public Menu_SettingPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickSetting() {
	setting.click();
	}
	
	public String captureUserEmailID(){
		String uresEmail= getEmail.getAttribute("value");
		return uresEmail;

	}

}
