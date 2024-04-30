package pageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import Utilities.ExcelUtils;
	 
	public class HomePage extends BasePage{
	 
		public WebDriver driver;
		public int tooltipsmatched=0;
		public ArrayList<String> listofheaders=new ArrayList<String>();
		public ArrayList<String> listoftooltip=new ArrayList<String>();
		
		public HomePage(WebDriver driver)
		{
			super(driver);
		}

	//Locating Help button (wait method)
	By help=By.xpath("//*[@id='O365_MainLink_Help']");  
	 
	//Locating username
	By usersname=By.xpath("//*[@id='mectrl_currentAccount_primary']");
	 
	//Locating Around Cognizant 
	By aroundcognizant=By.xpath("//*[@class='fontSizeXLarge']/strong");     

	//Locating Headers
	By headers1=By.cssSelector("a[style='-webkit-line-clamp: 2;']");   
	 
	@FindBy(xpath="//*[@id=\"O365_MainLink_Me\"]")
	WebElement userinformation;
	 
	@FindBy(xpath="//*[@id='mectrl_currentAccount_primary']") 
	WebElement username;
	 
	@FindBy(xpath="//*[@id='mectrl_currentAccount_secondary']") 
	WebElement userEmailid;
	 
	@FindBy(xpath="//span[@class='fontSizeXLarge']/strong") 
	WebElement aroundCognizant;
	 
	@FindBy(css="a[style='-webkit-line-clamp: 2;']") 
	List<WebElement> headers;
	 
	@FindBy(xpath="//*[@class=\"fontSizeMedium\"]/strong")
	WebElement seeAll;
	 
		public void userInfo() throws InterruptedException, IOException{

			//wait till help button displayed
			mywait.until(ExpectedConditions.elementToBeClickable(help));  
			Thread.sleep(5000);
			userinformation.click();

			//wait till user name displayed
			mywait.until(ExpectedConditions.visibilityOfElementLocated(usersname));  
			String Username=username.getText();
			String EmailId=userEmailid.getText();

			//Store data into respective Excel sheet			
			if(browserName.equals("chrome")){  
				ExcelUtils.setCellData(filepath, "Sheet1",8,5,Username);
				ExcelUtils.setCellData(filepath, "Sheet1",9,5,EmailId);
			}

			if(browserName.equals("microsoftedge")){
				ExcelUtils.setCellDataXls(filepath2, "Sheet1",8,5,Username);
				ExcelUtils.setCellDataXls(filepath2, "Sheet1",9,5,EmailId);
			}

		}

		public boolean aroundCognizant() throws InterruptedException, IOException{
			Thread.sleep(5000);

			//Verification of Around Cognizant is Displayed
			mywait.until(ExpectedConditions.visibilityOfElementLocated(aroundcognizant)); 

			boolean Result=aroundCognizant.isDisplayed();
			if(Result==true) {
				if(browserName.equals("chrome")) {

					//Store data into respective Excel sheet		
					ExcelUtils.setCellData(filepath, "Sheet1",10,5,"Present");   	
				}

				if(browserName.equals("microsoftedge")) {
					ExcelUtils.setCellDataXls(filepath2, "Sheet1",10,5,"Present");
				} 
			}

			//if Around Cognizant not found
			else {										 		
				if(browserName.equals("chrome")) {
					ExcelUtils.setCellData(filepath, "Sheet1",10,5,"Not Present");
				}
				if(browserName.equals("microsoftedge")) {
					ExcelUtils.setCellDataXls(filepath2, "Sheet1",10,5,"Not Present");
				}

			}

			// Scroll down till around cognizant text
			js.executeScript("arguments[0].scrollIntoView();",aroundCognizant);  
			Thread.sleep(5000);

			int Row=1;

			//Store headers and tooltips in respective Excel sheet	
			for(WebElement heading:headers){    		

				if(browserName.equals("chrome")) {

					ExcelUtils.setCellData(filepath, "Sheet2",Row,0,heading.getText());
					ExcelUtils.setCellData(filepath, "Sheet2",Row,1,heading.getAttribute("title"));

				}

				if(browserName.equals("microsoftedge")) {

					ExcelUtils.setCellDataXls(filepath2, "Sheet2",Row,0,heading.getText());
					ExcelUtils.setCellDataXls(filepath2, "Sheet2",Row,1,heading.getAttribute("title"));

				}

				//Store to List
				listofheaders.add(heading.getText());			
				listoftooltip.add(heading.getAttribute("title"));
				Row++;

			}

			return Result;

		}


		public boolean headersTooltipComparep1() throws IOException{

			//Compare the Headers with tooltip
			for(int i=0;i<listoftooltip.size();i++){		
				String tooltip=listoftooltip.get(i);
				if(listofheaders.contains(tooltip)){
					tooltipsmatched++;
				}
			}

			if(tooltipsmatched>0){
				
				//(count)+ headers matches with the toolTips
				if(browserName.equals("chrome")) {

					ExcelUtils.setCellData(filepath,"Sheet1",11,5,tooltipsmatched+" headers matches with the tooltips.");
				}

				if(browserName.equals("microsoftedge")) {
					ExcelUtils.setCellDataXls(filepath2,"Sheet1",11,5,tooltipsmatched+" headers matches with the tooltips.");
				}

				return true;
			}
			else {

				return false;
			}
		}

		public void clickSeeAll(){

			//wait until 'See All' option is clickable
			mywait.until(ExpectedConditions.elementToBeClickable(seeAll));  
			seeAll.click();

		}
	 
	}
