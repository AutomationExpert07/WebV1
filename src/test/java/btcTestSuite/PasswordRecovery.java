package btcTestSuite;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import data.TestData;
import testBase.TestBase;
import uiActions.ForgotPassword;
import uiActions.HomePage;
import uiActions.SetUp_RegisterPage;
import uiActions.RegtestAutoTextPage;
import uiActions.Menu_SettingPage;
import uiActions.SetUp_SignInPage;

public class PasswordRecovery extends TestBase{
	
	SetUp_RegisterPage registerpage;
	HomePage homePage;
	Menu_SettingPage settingpage;
	SetUp_SignInPage signInpage;
	ForgotPassword forgotPassword;
	SetUp_SignInPage signInPage;
	TestData testData;
	RegtestAutoTextPage regtextpage;
	String ERS_TITLE = "Encrypted Recovery Secret";
	String WALLET_PUBKEY_TITLE = "BTC Wallet Public Key";
	String UniqueEmailID="";
	String ers1;
	
	@BeforeTest
	public void setUp() throws IOException {
	log.info("initialzing all the variable and methods");
	init();
	}
	
	@Test
	public void passwordRecovery() throws InvalidPasswordException, IOException, InterruptedException
	{
		log.info("___________createing Wallet__________");
		registerpage = new SetUp_RegisterPage(driver);
		registerpage.clickCreateAccLink();
		testData = new TestData(driver);
		registerpage.createAccount(testData.username, testData.password);
		registerpage.confirmPassword(testData.password);
		registerpage.downloadWalletbkup();
		
		//Read the Password recovery PDF file and extract the "Encrypted Recovery Secret" password
		PDDocument document = PDDocument.load(new File("C:\\Users\\sohaib\\Downloads\\BTC.com rBitcoin Wallet Recovery Backup Sheet - mywallet-shashashasha.pdf"));
		//PDDocument document = PDDocument.load(new File("C:\\Users\\sohaib\\Downloads\\BTC.com rBitcoin Wallet Recovery Backup Sheet - mywallet-shashashasha"));
		if (!document.isEncrypted()) {
		    PDFTextStripper stripper = new PDFTextStripper();
		    String text = stripper.getText(document);
		    log.info("___________Complete PDF :__________"+ text);
		    int ersPos = text.indexOf(ERS_TITLE);
		    int walletPubKeysPos = text.indexOf(WALLET_PUBKEY_TITLE);
		    log.info("___________ERS pos:__________"+ ersPos);
		    log.info("___________pubkey pos:__________"+ walletPubKeysPos);
		    String ers = text.substring(ersPos + ERS_TITLE.length(), walletPubKeysPos).trim();
		    log.info("_________Extracted Key is_____:\n"+ers);
		    ers1 = ers;
		}
		document.close();
		registerpage.deletePwdPdf();
		log.info("_____________At Home Page______________");
		settingpage = new Menu_SettingPage(driver);
		settingpage.clickSetting();
		settingpage = new Menu_SettingPage(driver);
		Thread.sleep(3000L);
		UniqueEmailID = settingpage.captureUserEmailID();
		log.info("Unique Email id is :"+UniqueEmailID);
		homePage = new HomePage(driver);
		homePage.Logout();
		signInpage = new SetUp_SignInPage(driver);
		Thread.sleep(3000L);
		signInpage.SignInLink();
		signInpage.ForgotPassword();
		forgotPassword = new ForgotPassword(driver);
		forgotPassword.enterEmail(UniqueEmailID);
		forgotPassword.clickContinue();
		Thread.sleep(5000L);
		regtextpage = new RegtestAutoTextPage(driver);
		regtextpage.launchRegTextAutoPage(regtextpage.launchBTCRegtest);
		regtextpage.recoveryEmail(UniqueEmailID);
		Thread.sleep(2000L);
		regtextpage.sendEmailButton();
		Thread.sleep(3000L);
		regtextpage.ClickChangepwrd();
		Thread.sleep(2000L);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Iterator<String> itr = getAllWindows();
		String parentWindow = itr.next();
		String childWindow = itr.next();
		driver.switchTo().window(childWindow);
		forgotPassword.encryptedRecoverySecret(forgotPassword.invalidKey);
		forgotPassword.clickContinue();
		Assert.assertEquals(forgotPassword.recoveryError(), "Recovery Error");
		log.info("___________Verifies the Revoery error message when wrong addresse is entered._____");
		forgotPassword.clickOkbutton();
		forgotPassword.encryptedRecoverySecret(ers1);
		Thread.sleep(2000L);
		forgotPassword.clickContinue();
		forgotPassword.newPassword(forgotPassword.newPassword);
		forgotPassword.newPasswordConfirm(forgotPassword.newPassword);
		forgotPassword.clickContinue();
		Thread.sleep(2000L);
		forgotPassword.downloadbkkey();
		Assert.assertEquals(forgotPassword.verifySuccessMsg(), forgotPassword.successPwdRecoverMesg);
		log.info("___________Verifies the Sucess message when after entering the Right Password recovery key._____");
		forgotPassword.clickbkToLoginButton();
		signInPage = new SetUp_SignInPage(driver);
		signInPage.SignIn(UniqueEmailID, testData.password);
	//	registerpage.confirmPassword(confrimWrongPsw);
		Assert.assertEquals(signInPage.IncorrectLogin(), "Incorrect login details");
		signInPage.SignIn(UniqueEmailID, testData.NewPassword);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		homePage = new HomePage(driver);
		homePage.verifyLogoutbutton();
		log.info("___________Verifies the Sucess Logging in with new password._____");
	}
	@AfterClass
	public void endTest() {
		driver.quit();
	}
}
