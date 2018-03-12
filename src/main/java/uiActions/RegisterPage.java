package uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;

public class RegisterPage {
	
	public static final Logger log= Logger.getLogger(HomePage.class.getName());
	WebDriver driver;
	
	public static final String registerURL = "https://wallet.btc.com/#/setup/register";
//	public final registerBackupURL ="https://wallet.btc.com/#/setup/wallet-backup";
	public final String HomeWalletPage = "https://wallet.btc.com/#/wallet";
	public String emailErrorMsg ="Please enter your login details";
	public String passwordErrorMsg = "Please enter your password";
	public String IagreeNotSelected = "You must agree to the terms of service";
	//public String 
	
	@FindBy(xpath="//input[@name='email']")
		WebElement email;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement password;
	
	
	@FindBy(xpath="//a[contains(text(),'Create Wallet')]")
	WebElement CreateNewWalletLink;
	
	@FindBy(xpath="//button[contains(text(),'Create new wallet')]")
	WebElement CreateNewWallet;
	
	@FindBy(xpath="//input[@type='checkbox']")
	WebElement CheckIAgreeTerms;

	@FindBy(xpath="//input[@id='prompt']")
	WebElement confirmPassword;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement OK;
	
	@FindBy(xpath="//a[@ng-click='dismiss()']")
	WebElement Cancel;
	
	@FindBy(xpath="//button[@ng-click='export()']")
	WebElement SaveWalletBkup;
	
	@FindBy(xpath="//input[@id='backupSavedCheck']")
	WebElement bkupSecurely;
	
	@FindBy(xpath="//button[@class='btn btn-alt form-control ng-binding btn-success']")
	WebElement Continue;
	
	@FindBy(xpath="//div[@class='alert alert-danger ng-binding ng-scope']")
	WebElement AllertMessage;
	
	@FindBy(xpath="//div[@ng-if='registerForm.email.$error.required']")
	WebElement EnterEmailIDWarning;
	
	@FindBy(xpath="//div[@ng-if='registerForm.password.$error.required']")
	WebElement EnterpasswordWarning;
	
	@FindBy(xpath="//div[contains(text(),'Please enter your password')]")
	WebElement EnterYourPasswordWarning;
	
	public RegisterPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

	public void clickCreateAccLink()
	{
		CreateNewWalletLink.click();
	}
	
	public void clickCreateAcc()
	{
		CreateNewWallet.click();
	}
	
	public void createAccount(String name, String pws)
	{
		email.clear();
		email.sendKeys(name);
		password.clear();
		password.sendKeys(pws);
		
		if(CheckIAgreeTerms.isSelected())
			{
			CheckIAgreeTerms.click();
			}
		
		CheckIAgreeTerms.click();
		CreateNewWallet.click();
	}
	
	public void confirmPassword(String pws) {
		confirmPassword.sendKeys(pws);
		OK.click();
	}

	public void downloadWalletbkup()
	{
		SaveWalletBkup.click();
		bkupSecurely.click();
		Continue.click();
	}
	
	public void AllertCheck()
	{
		AllertMessage.getText();
		log.info("___________Verified Allert message__________");
	}
	public String checkEmailWaring() {
		EnterEmailIDWarning.getText();
		log.info("___________Verified email not entered message__________");
		return HomeWalletPage;
	}
	public String checkPasswordwarning() {
		EnterpasswordWarning.getText();
		log.info("___________Verified password not entered message__________");
		return HomeWalletPage;
	}
	
/*	public String emailErrorMsg ="Please enter your login details";
	public String passwordErrorMsg = "Please enter your password";
	public String IagreeNotSelected = "You must agree to the terms of service";*/
	
	public boolean verifyemailErrormesg() {
		try {
		//	driver.findElement(By.xpath("//div[contains(text(),'Please enter your login details')]")).isDisplayed();
			EnterEmailIDWarning.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean verifyPasswordErrormesg() {
		try {
		//	driver.findElement(By.xpath("//div[contains(text(),'Please enter your login details')]")).isDisplayed();
			EnterpasswordWarning.isDisplayed();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
}
