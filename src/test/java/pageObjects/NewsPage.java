package pageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.ExcelUtils;

public class NewsPage extends BasePage{

	public WebDriver driver;
	public int headerscount=0;
	public int tooltipcount=0;
	public int tooltipFivecount=0;
	public LinkedHashSet<String> headerslist = new LinkedHashSet<String>(); 
    public LinkedHashSet<String> tooltipslist = new LinkedHashSet<String>();
    public LinkedHashSet<String> contentslist = new LinkedHashSet<String>();
	
	public NewsPage(WebDriver driver){
		super(driver);
	}
	 //Identifying List of all Headers
	@FindBy(xpath="//*[@data-automation-id='newsItemTitle']")List<WebElement> headers;    
	//Identifying List of all Contents
	@FindBy(xpath="//*[@data-automation-id='newsItemDescription']") List<WebElement> contents;   
	
	//Identifying List of all Headers
	@FindBy(xpath="//*[@data-automation-id='newsItemTitle']")List<WebElement> headersFive;     
	//Identifying List of all Contents
	@FindBy(xpath="//*[@data-automation-id='newsItemDescription']") List<WebElement> contentsFive;   
	
	public void newsHeader(WebDriver driver) throws InterruptedException{
		Thread.sleep(5000);
		boolean flag=true;
		
		//Collection of headers and tooltip till end of the page
        while((headers.size()>0 && flag==true)){					
        	WebElement seventh=headers.get((headers.size())-1);
    		for(int i=0;i<headers.size();i++){
        		
        		headerslist.add(headers.get(i).getText());
        		tooltipslist.add(headers.get(i).getAttribute("title"));
        		contentslist.add(contents.get(i).getText());
            }
    		
    		//Scroll till the last element visiblity
    		js.executeScript("arguments[0].scrollIntoView();",seventh);      
            Thread.sleep(5000);
            
          //close loop if we find last news
            if(seventh.getAttribute("title").equals(p.getProperty("LastNews"))){       
        		
        		flag=false;
        		continue;
        	}
            headers = driver.findElements(By.xpath("//*[@data-automation-id='newsItemTitle']"));
            contents = driver.findElements(By.xpath("//*[@data-automation-id='newsItemDescription']")); 
        }
	}
	
	
	
	public boolean headersCompare(ArrayList<String> firstPageHeader) throws IOException{
		
		//Compare First page Headers and second page headers
		for(int i=0;i<firstPageHeader.size();i++) {				
			
			if(headerslist.contains(firstPageHeader.get(i))){
				headerscount++;
			}
		}
		if(headerscount>0) {
			//(count)+ headers present in the second page
			
			if(browserName.equals("chrome")) {
				
				ExcelUtils.setCellData(filepath,"Sheet1",12,5,headerscount+" first page headers matches with the SecondPage headers");
				
				
			}
			if(browserName.equals("microsoftedge")) {
				
				ExcelUtils.setCellDataXls(filepath2,"Sheet1",12,5,headerscount+" first page headers matches with the SecondPage headers");
				
				
			}
			
			return true;
		}
		else {
			
			return false;
		}
	}
	
	public boolean headersTooltipComparep2() throws IOException{
		
		List<String> temp_headers=new ArrayList<String>(headerslist);
		List<String> temp_tooltip=new ArrayList<String>(tooltipslist);
		//Compare Second page Headers and Tooltips
		for(int i=0;i<temp_headers.size();i++){					
			
			if((temp_headers.get(i)).equals(temp_tooltip.get(i))){
				
				tooltipcount++;
			}
			
		}
		if(tooltipcount>0){
			
			if(browserName.equals("chrome")) {
				
				ExcelUtils.setCellData(filepath,"Sheet1",13,5,tooltipcount+" Second page headers matches with the SecondPage tooltips");
				
				
			}
			if(browserName.equals("microsoftedge")) {
				
				ExcelUtils.setCellDataXls(filepath2,"Sheet1",13,5,tooltipcount+" Second page headers matches with the SecondPage tooltips");
				
				
			}
			
			return true;
		}
		else {
			
			return false;
		}
	}
	
	public void newsDetailsPrint() throws IOException{
		int rowh=1;
		//Print the headers List data to excel
        for(String h:headerslist) {						
        	
        	
        	if(browserName.equals("chrome")) {
				
        		ExcelUtils.setCellData(filepath, "Sheet3", rowh,0,h);
					
			}
			if(browserName.equals("microsoftedge")) {
				
				ExcelUtils.setCellDataXls(filepath2, "Sheet3", rowh,0,h);
					
			}
        	
        	rowh++;
        }
        int rowt=1;
      //Print the tooltips List data to excel
        for(String t:tooltipslist) {				
        	
        	if(browserName.equals("chrome")) {
				
        		ExcelUtils.setCellData(filepath, "Sheet3", rowt,1,t);
					
			}
			if(browserName.equals("microsoftedge")) {
				
				ExcelUtils.setCellDataXls(filepath2, "Sheet3", rowt,1,t);
					
			}

        	
        	rowt++;
        }
        int rowc=1; 
        
      //Print the Content List data to excel
        for(String c:contentslist) {					
        	
        	if(browserName.equals("chrome")) {
				
        		ExcelUtils.setCellData(filepath, "Sheet3", rowc,2,c);
					
			}
			if(browserName.equals("emicrosoftedge")) {
				
				ExcelUtils.setCellDataXls(filepath2, "Sheet3", rowc,2,c);
					
			}

        	
        	rowc++;
        }
	}
	
	public boolean headersTooltipComparep2Five() throws IOException{
		
		//Compare Second page Headers and Tooltips
		for(int i=0;i<5;i++){	
			
			String hFive=headersFive.get(i).getText();
			String ttFive=headersFive.get(i).getAttribute("title");
			if(hFive.equals(ttFive)){
				
				tooltipFivecount++;
			}
			
		}
		if(tooltipFivecount>0){
			
			if(browserName.equals("chrome")) {
				
				ExcelUtils.setCellData(filepath,"Sheet1",14,5,tooltipFivecount+" Second page headers matches with the First Five SecondPage tooltips");
				
				
			}
			if(browserName.equals("microsoftedge")) {
				
				ExcelUtils.setCellDataXls(filepath2,"Sheet1",14,5,tooltipFivecount+" Second page headers matches with the First Five SecondPage tooltips");
				
				
			}
			
			return true;
		}
		else {
			
			return false;
		}
	}
	
	public void FivenewsDetailsPrint() throws IOException{
		
		int rowh=1;
		//Print the headers List data to excel
        for(int i=0;i<5;i++) {		
        	WebElement h=headersFive.get(i);
        	
        	if(browserName.equals("chrome")) {
				
        		ExcelUtils.setCellData(filepath, "Sheet4", rowh,0,h.getText());
					
			}
			if(browserName.equals("microsoftedge")) {
				
				ExcelUtils.setCellDataXls(filepath2, "Sheet4", rowh,0,h.getText());
					
			}
        	
        	rowh++;
        }
        int rowt=1;
      //Print the tooltip List data to excel
        for(int i=0;i<5;i++) {				
        	WebElement t=headersFive.get(i);
        	if(browserName.equals("chrome")) {
				
        		ExcelUtils.setCellData(filepath, "Sheet4", rowt,1,t.getAttribute("title"));
					
			}
			if(browserName.equals("microsoftedge")) {
				
				ExcelUtils.setCellDataXls(filepath2, "Sheet4", rowt,1,t.getAttribute("title"));
					
			}

        	
        	rowt++;
        }
        int rowc=1; 
      //Print the Content List data to excel
        for(int i=0;i<5;i++) {					
        	
        	WebElement c=contentsFive.get(i);
        	if(browserName.equals("chrome")) {
				
        		ExcelUtils.setCellData(filepath, "Sheet4", rowc,2,c.getText());
					
			}
			if(browserName.equals("microsoftedge")) {
				
				ExcelUtils.setCellDataXls(filepath2, "Sheet4", rowc,2,c.getText());
					
			}

        	
        	rowc++;
        }
		
	}
	
	
	
}
