package uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class SetUp_RegisterPage {
	
	public static final Logger log= Logger.getLogger(SetUp_RegisterPage.class.getName());
	WebDriver driver;
	
	public static final String registerURL = "https://wallet.btc.com/#/setup/register";
//	public final registerBackupURL ="https://wallet.btc.com/#/setup/wallet-backup";
	public final String HomeWalletPage = "https://wallet.btc.com/#/wallet";
	public String emailErrorMsg ="Please enter your login details";
	public String passwordErrorMsg = "Please enter your password";
	public String IagreeNotSelected = "You must agree to the terms of service";
	public String termsOfServiceURL = "https://btc.com/legal";
	public String wrongPasswordmatch = "The passwords don't match";
	
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
	
	@FindBy(xpath="//a[contains(text(),'terms of service')]")
	WebElement termsofservice;
	
	@FindBy(xpath="//div[@ng-if='errMsg']")
	WebElement passwordDontMatch;
	

	
	@FindBy(xpath="//span[@translate='PASSWORD_TIME_TO_CRACK' and contains(text(), 'estimated to take ')]")
	WebElement passwordStreght;
	
	public SetUp_RegisterPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

	public void ClickTermsofserviceLink() {
		termsofservice.click();
		log.info("___________clicking on terms of service link__________");
	}
	
	public String confirmWrongPassword() {
		passwordDontMatch.getText();
		log.info("___________Verified message showen while entering wrong password__________");
		return wrongPasswordmatch;
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
		this.email.clear();
		this.email.sendKeys(name);
		password.clear();
		this.password.sendKeys(pws);
		if(!(pws=="")) {
			passwordStreght.isDisplayed();
		}
		
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
		log.info("___________Downloading the private key pdf__________");
	}
	
	public void AllertCheck()
	{
		AllertMessage.getText();
		log.info("___________Verified Allert message__________");
	}
	public String checkEmailWaring() {
		log.info("___________Verified email not entered message while Singin In__________");
		return EnterEmailIDWarning.getText();
	}
	public String checkPasswordwarning() {
		log.info("___________Verified password not entered while creating Wallet message__________");
		return EnterpasswordWarning.getText();
	}
	
/*	public String emailErrorMsg ="Please enter your login details";
	public String passwordErrorMsg = "Please enter your password";
	public String IagreeNotSelected = "You must agree to the terms of service";*/
	
	public boolean verifyemailErrormesg() {
		try {
			EnterEmailIDWarning.isDisplayed();
			log.info("___________Verified email varification message is shown__________");
			return true;
		} catch (Exception e) {
			log.info("___________Verified email varification message is not shown__________");
			return false;
		}
	}
	
	public boolean verifyPasswordErrormesg() {
		try {
			EnterpasswordWarning.isDisplayed();
			log.info("___________Verified password varification message is shown__________");
			return true;
		} catch (Exception e) {
			log.info("___________Verified password varification message is shown__________");
			return false;
		}
	}
	
	public boolean passwordCrack() {
		try {
				passwordStreght.isDisplayed();
				return true;
			} catch (Exception e) {
				return false;
			}
	}
	public void log(String data){
		log.info(data);
		Reporter.log(data);
	}
	
}
