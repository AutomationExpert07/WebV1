package data;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import testBase.TestBase;

public class TestData extends TestBase {

	public String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	public String existingUser = "sohaibcool10@gmail.com";
	public String username="Sohaibcool"+timeStamp+"@gmail.com";
	public String password="Hello@123";
	public double Amount =1;
	public String xAddress ="2NCj5iUvuVxfWBNV64kGBeqHiNZBJEqqQtX";
	public String Transactions;
	public String s="sohaibcool"+ timeStamp; 
	public String JunkUserid ="so!#$%^&*!#$%^&*:@?uyfgwuihfwefhpowefhjpewhfpiwhfpowehfpoweihfowehifo;weifhpioef@blocktrail.com";
	public String blank="";
	public String confrimWrongPsw = "Yello@123";
	public String NewPassword= "Yello@123";
	
	public TestData(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
}
