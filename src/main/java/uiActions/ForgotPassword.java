package uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase.TestBase;

public class ForgotPassword extends TestBase{
	public static final Logger log= Logger.getLogger(SetUp_SignInPage.class.getName());
	WebDriver driver;
	
	public String invalidKey ="blablabla let go for a ride :D";
	public String newPassword = "Yello@123";
	public String successPwdRecoverMesg = "You've changed your password successfully.";
	
	@FindBy(xpath="//textarea[@name='inputERS']")
	WebElement resetPasswrd;
	
	@FindBy(xpath="//h3[contains(text(),'Recovery Error')]")
	WebElement recoveryErrorMesg;
	
	@FindBy(xpath="//a[contains(text(),'OK')]")
	WebElement Okbutton;
	
	@FindBy(xpath="//input[@placeholder='New password']")
	WebElement EnterNewPwd;
	
	@FindBy(xpath="//input[@placeholder='Repeat new password']")
	WebElement EnterNewPwdConfirm;
	
	@FindBy(xpath="//a[contains(text(),'Save additional backup page (PDF)')]")
	WebElement clickbkUpbutton;
	
	@FindBy(xpath="//h3[contains(text(),' changed your password successfully.')]")
	WebElement successPwdRecoverd;
	
	@FindBy(xpath="//button[contains(text(),'Back to login')]")
	WebElement bkToLogin;
	
	public ForgotPassword(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

	public void enterEmail(String email) {
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
	}
	
	public void newPassword(String pwd) {
		EnterNewPwd.sendKeys(pwd);
	}
	
	public void newPasswordConfirm(String pwd) {
		EnterNewPwdConfirm.sendKeys(pwd);
	}
	
	public void clickContinue() {
		driver.findElement(By.xpath("//button[contains(text(),'Continue')]")).click();
	}
	
	public void encryptedRecoverySecret(String key) {
		resetPasswrd.click();
		resetPasswrd.clear();
		resetPasswrd.sendKeys(key);
	}
	
	public String recoveryError() {
		return recoveryErrorMesg.getText();
	}
	
	public void clickOkbutton() {
		Okbutton.click();
	}
	
	public void downloadbkkey() {
		clickbkUpbutton.click();
	}
	
	public String verifySuccessMsg() {
		return successPwdRecoverd.getText();
	}
	
	public void  clickbkToLoginButton() {
		bkToLogin.click();
	}
}
