package pageObjects;

import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage{
	
	public WebDriver driver;
	public WebDriverWait mywait;
	public JavascriptExecutor js;
	public Properties p;
	public String browserName;
	public Capabilities cap;

	public String filepath=System.getProperty("user.dir")+"\\testDataChrome\\DataChrome.xlsx";

	public String filepath2=System.getProperty("user.dir")+"\\testDataEdge\\DataEdge.xls";
	
	
	
	
	public BasePage(WebDriver driver)
	{
		this.driver=driver; 
		mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
		js=(JavascriptExecutor) driver;
		// Initializing Page Factory Model
		PageFactory.initElements(driver,this);   
		
		//To get the Current Browser details 
		 cap = ((RemoteWebDriver) driver).getCapabilities();  
		 browserName= cap.getBrowserName().toLowerCase();
		
		
		FileReader file;
		try {
			//Import values from Property File
			file = new FileReader(".//src//test//resources//Prop.properties"); 
			p=new Properties();
			p.load(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
