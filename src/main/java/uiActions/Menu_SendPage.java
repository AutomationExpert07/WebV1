package uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import testBase.TestBase;

public class Menu_SendPage extends TestBase{
	public static final Logger log= Logger.getLogger(Menu_SendPage.class.getName());
	WebDriver driver;
	SetUp_SignInPage signInPage;
	public String xAddress = "2NCj5iUvuVxfWBNV64kGBeqHiNZBJEqqQtX";
	public String SendURL = "http://wallet.btc.btccom-autotest.blocktrail.com/#/wallet/send";
	public double Amount =3;
	public double OptimalFee1;
	
	@FindBy(xpath="//a[@ng-click='dismiss()']")
	WebElement defaultWalletDismissButton;
	
	@FindBy(xpath="//span[contains(text(),'Send')]//parent::a[@ng-href='#/wallet/send']")
//	@FindBy(xpath="//span[contains(text(),'Send')]")
	WebElement sendMenu;
	
	@FindBy(xpath="//span[@ng-if='fees.optimal && displayFee']")
	WebElement optimalFee;
	
	@FindBy(xpath="//h1[@class='pageTitle ng-binding']")
	WebElement myWalletHomePageTitle;
	
	@FindBy(xpath="//button[@class='btn btn-lg btn-primary']")
	WebElement sendBTCButton;
	
	@FindBy(xpath="//span[@translate='SENDING_CONFIRM_MSG']")
	WebElement ckSentToRightID;
	
	@FindBy(xpath="//input[@type='password']")
	WebElement confirmPassword;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement submitConfirmBtn;
	
	@FindBy(xpath="//h4[contains(text(),' Transaction Sent.')]")
	WebElement transactionConfirmation;
	
	@FindBy(xpath="//span[@class='altCurrency ng-binding']")
	WebElement currencyAmt_ConvertedtoBTC;
	
	@FindBy(xpath="//input[@id='recipient']")
	WebElement sendingAddress;
	
	@FindBy(xpath="//input[@id='amount']")
	WebElement amountSent;
	
	@FindBy(xpath="//a[@target='_blank']")
	WebElement transactionID;
	
	@FindBy(xpath="//h1[@class='ng-binding']")
	WebElement availbleBitcoin;
	
	@FindBy(xpath="//span[@translate-value-network='rBCH']")
	WebElement rBCH;
	
	
	public Menu_SendPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public void ClickSendMenu() {
		sendMenu.click();
		if(defaultWalletDismissButton.isDisplayed())
		{
			defaultWalletDismissButton.click();
		} 
		else {
		System.out.println("Pop up not displayed.");
		}
	}	
	public void ClickSendwithoutMessage() {
		sendMenu.click();
	}
	
	public void RecipientAdd(String ReceipientAddress) {
		sendingAddress.sendKeys(ReceipientAddress);
	}
	
	public void EnterAmount(double amount) {
		amountSent.sendKeys(Double.toString(amount));
	}
	
	public double OptimalBTCFee() {
		//sendMenu.click();
		String OptimalAmount = optimalFee.getText();
		log.info("Optimal Amount is" + OptimalAmount);
		String OptimalAmount1 = OptimalAmount.replace(" rBTC", "");
		log.info("Optimal Amount after removeing rBTC" + OptimalAmount1);
		double OptimalFee = Double.parseDouble(OptimalAmount1);
		log.info("The Optimal Fee for the "+Amount+ " is " + OptimalFee);
		OptimalFee1 = OptimalFee;
		return OptimalFee;
	}
	
	public double OptimalBCHFee() {
		//sendMenu.click();
		String OptimalAmount = optimalFee.getText();
		log.info("Optimal Amount is" + OptimalAmount);
		String OptimalAmount1 = OptimalAmount.replace(" rBCH", "");
		log.info("Optimal Amount after removeing rBCH" + OptimalAmount1);
		double OptimalFee = Double.parseDouble(OptimalAmount1);
		log.info("The Optimal Fee for the "+Amount+ " is " + OptimalFee);
		OptimalFee1 = OptimalFee;
		return OptimalFee;
	}
	
	public String CovertedCurrecyAmount() {
		String convertedCurrencyAmount = currencyAmt_ConvertedtoBTC.getText();
		log.info("Converted Amount for the Selected Currecy type is " +convertedCurrencyAmount);
		return convertedCurrencyAmount;
	}
	
	public void SendButton() {
		sendBTCButton.click();
	}
	
	public boolean BTCSentConfirmationMessage() {
		boolean ConfirmMessage = ckSentToRightID.getText().contains(xAddress);
	//	log.info("send confirmation cointains the address"+ConfirmMessage);
		Assert.assertTrue(ConfirmMessage);
	//	String rBCHtext = rBCH.getAttribute("translate-value-network");	
	//	log.info("The transaction is completed and a confirmation message " +rBCHtext);
		
	//	boolean ConfirmMessageBCH = ckSentToRightID.getText().contains((CharSequence) rBCH);
	//	Assert.assertTrue(ConfirmMessageBCH);
		log.info("The transaction is completed and a confirmation message " +ConfirmMessage);
		return ConfirmMessage;
	}
	
	public void sendPasswordconfirmation(String password) {
		signInPage = new SetUp_SignInPage(driver);
		confirmPassword.sendKeys(password);
		//confirmPassword.sendKeys(password);
		submitConfirmBtn.click();
		log.info("Confirming the password to send the BitCoin to recipient address ");
	}
	
	public void BCHconfirmTransactionID() {
		String TransactionID = transactionID.getAttribute("href");
		String TransID = TransactionID.replace("https://www.blocktrail.com/tBCC/tx/", "");
		System.out.println(TransID);
		int TransIDLength = TransID.length();
		System.out.println("Transaction is success as it has 64 characters init :)" +TransIDLength);
		Assert.assertEquals(TransIDLength, 64);
		Assert.assertEquals(transactionConfirmation.getText(),"Transaction Sent.");
		submitConfirmBtn.click();
	}
	
	public void BTCconfirmTransactionID() {
		String TransactionID = transactionID.getAttribute("href");
		String TransID = TransactionID.replace("https://tchain.btc.com/", "");
		//String TransID = TransactionID.replace("https://www.blocktrail.com/tBCC/tx/", "");
		System.out.println(TransID);
		int TransIDLength = TransID.length();
		System.out.println("Transaction is success as it has 64 characters init :)" +TransIDLength);
		Assert.assertEquals(TransIDLength, 64);
		Assert.assertEquals(transactionConfirmation.getText(),"Transaction Sent.");
		submitConfirmBtn.click();
	}
	
	public void ValidatetheTransaction(double BeforeTransc, double OptimalFee,double Amount)
	{
		String AfterTransactionBTC = availbleBitcoin.getText();
		String arr1[] = AfterTransactionBTC.split(" ", 2);
		String firstWord = arr1[0];   //the
		String theRestword = arr1[1];     //quick brown fox
		
		double AfterTranscBTC1 = Double.parseDouble(firstWord);

		System.out.println(AfterTranscBTC1);
		
		double afterTransaction = BeforeTransc -(Amount +OptimalFee);
		System.out.println("The Transaction after flow is "+ afterTransaction);
	}

}
