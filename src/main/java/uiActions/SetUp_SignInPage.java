package uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase.TestBase;

public class SetUp_SignInPage extends TestBase{
	public static final Logger log= Logger.getLogger(SetUp_SignInPage.class.getName());
	WebDriver driver;
	
/*	public String usrID ="sohaibcool11@gmail.com";
	public String password1="Hello@123";*/
	public String incorrectCrediencials = "Incorrect login details";
	
	@FindBy(xpath="//input[@name='username']")
	WebElement email;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath="//a[contains(text(),'Sign In')]")
	WebElement SignInLinkHeaderLink;
	
	@FindBy(xpath="//button[contains(text(),'Sign In')]")
	WebElement SignInButton;
	
	@FindBy(xpath="//h1['Sign In']")
	WebElement SignInHeaderText;
	
	@FindBy(xpath="//div[@class='alert alert-danger ng-binding ng-scope']")
	WebElement IncorrectLoginDetails;
	
	@FindBy(xpath="//div[@ng-if='loginForm.password.$error.required']")
	WebElement EnterpasswordWarning;
	
	@FindBy(xpath="//div[contains(text(),'Incorrect login details')]")
	WebElement incorrectCredintials;

	public SetUp_SignInPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public void SignIn(String userName, String Password){
		
		SignInLinkHeaderLink.click();
		log.info("----------Loggin Into BTC.com-------------");	
		email.clear();
		email.sendKeys(userName);
		password.clear();
		password.sendKeys(Password);
		SignInButton.click();
		log.info("----------At home page-------------");
	}	
	
	public void CheckPwswarningMesg() {
		EnterpasswordWarning.isDisplayed();
	}
	
	public void SignInLink() {
		driver.findElement(By.xpath("//a[@href='#/setup/login']")).click();
	}
	
	public void ForgotPassword() {
		driver.findElement(By.xpath("//a[@href='#/setup/forgot-password']")).click();
	}
	
	public String IncorrectLogin() {
		log.info("___________Verified message showen while entering incorrect Credentials__________");
		return incorrectCredintials.getText();
	}
}
