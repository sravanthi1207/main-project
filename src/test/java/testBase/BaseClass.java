package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseClass {
    //Declarations of Instances
	public  WebDriver driver;
	public Logger logger;
	public static TakesScreenshot takesScreenshot;
	public ArrayList<String> templist=new ArrayList<String>();
	 

	@BeforeClass(groups={"regression"})
	@Parameters({"browser","url"})
	
	//Driver initialization
	public void setup(String Browser,String URL)
	{
		 
		logger=LogManager.getLogger(this.getClass());
		
		if(Browser.equalsIgnoreCase("chrome")){
			driver=new ChromeDriver();
		}
		else if(Browser.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}
		
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		takesScreenshot = (TakesScreenshot) driver;
	}
	
	@AfterClass(groups= {"regression"})
	public void tearDown()
	{
		
		driver.quit();
	}
	
	
	public static String captureScreenShot(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		//Takes Screenshot
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);   
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname +"_" +timeStamp+ ".png";
		File targetFile=new File(targetFilePath); 
		
		sourceFile.renameTo(targetFile); 
			
		return targetFilePath;

	}
}
