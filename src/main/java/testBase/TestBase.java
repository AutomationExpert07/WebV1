package testBase;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

import excelReader.ExcelReader;

public class TestBase 
{
	
	public static final Logger log= Logger.getLogger(TestBase.class.getName());
	
public WebDriver driver;
ExcelReader excel;
//String url ="https://wallet.btc.com/#/setup/register";
String url ="http://localhost:5000/#/setup/register";
String browser = "chrome";
//Listener lis;

public void init() {
	selectBrowser(browser);
	//Listener lis= new Listener();
	getUrl(url);
	String log4jConfPath="log4j.properties";
	PropertyConfigurator.configure(log4jConfPath);
}

public void selectBrowser(String browser) {
	if(browser.equalsIgnoreCase("chrome")) {
	//	System.setProperty("webdriver.chrome.driver","C:\\Selenium Drivers\\chromedriver_win32\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Driver/chromedriver.exe");
		log.info("creating object of"+browser);
		driver=new ChromeDriver();
	}
	else if(browser.equalsIgnoreCase("firefox")){
		driver=new FirefoxDriver();

	}
}

public void getUrl(String url) {
	log.info("opening the URL");
	driver.get(url);
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

/*public String[][] getData(String excelName, String sheetName){
	String path = System.getProperty("user.dir")+"/src/main/java/com/test/qa/Amazon/data/"+excelName;
	excel = new Excel_Reader(path);
	String[][] data = excel.getDataFromSheet(sheetName, excelName);
	return data;
}*/

public void getScreenShot(String name) throws IOException {
Calendar calandar = Calendar.getInstance();
SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyy_hh_mm_ss");

File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	try {
		String reportDirectory= new File(System.getProperty("user.dir")).getAbsolutePath() +"/src/main/java/com/test/qa/Amazon/screenShots/";
		File destFile = new File((String) reportDirectory + name + "_"+ formater.format(calandar.getTime())+".png");
		FileUtils.copyFile(scrFile,destFile);
		// This will help us to link the screen shot in testNG report
		Reporter.log("<a href='"+ destFile.getAbsolutePath()+"'> <img src='"+ destFile.getAbsolutePath()+"' height='100' width='100'/> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}




}