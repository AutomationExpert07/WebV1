package uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {

	public static final Logger log= Logger.getLogger(SignInPage.class.getName());
	WebDriver driver;
	
	//div[@class='alert alert-success']
	public LogoutPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
}
