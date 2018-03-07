package uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	public static final Logger log= Logger.getLogger(SignInPage.class.getName());
	WebDriver driver;
	
	@FindBy(xpath="//a[contains(text(),'Logout')]")
	WebElement Logout;
	
	public HomePage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public void Logout() {
		Logout.click();
	}
	
	public void verifyLogoutbutton() {
		Logout.isDisplayed();
	}
	
	
	
	
}
