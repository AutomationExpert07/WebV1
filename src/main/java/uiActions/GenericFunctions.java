package uiActions;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase.TestBase;

public class GenericFunctions extends TestBase{
	public static final Logger log= Logger.getLogger(GenericFunctions.class.getName());
	WebDriver driver;
	
	@FindBy(xpath="//div[@class='iit-10 dropdown']//button[@type='button']")
	List<WebElement> SelectCurrency;
	
	
	
	public GenericFunctions(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public void launchApplication(String url) {
		driver.get(url);
		
	}
	
	public void ClickCurrencyDropdown() {
		//List<WebElement> button1 = (List<WebElement>) SelectCurrency
				SelectCurrency.get(0).click();
		//button1.get(0).click();
	}
	
	
	
	//public void 
	
}
