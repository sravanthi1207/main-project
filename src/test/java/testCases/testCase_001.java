package testCases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.NewsPage;
import testBase.BaseClass;

public class testCase_001 extends BaseClass{
	
	
	
	@Test(priority=0,groups= {"regression"})
	public void firstPage(){
		 
		try { 
			captureScreenShot("1.start");
			logger.info("**** starting testCase_001 *****");
			logger.debug(".....application logs started.....");
			logger.info("First Page is Opened");
			
			HomePage hp=new HomePage(driver); 
			logger.info("View User Info Window");
			
			//To get the User info
			hp.userInfo();           
			captureScreenShot("2.user_info");
			logger.info("User Informations are stored in excel");
			
			logger.info("Checking aroundCognizant section");
		
			//Validates the presence of Around Cognizant
			boolean result1=hp.aroundCognizant();  
			captureScreenShot("3.around_cognizant");
			Assert.assertEquals(result1,true);
			
			logger.info("Comparing first page headers with tooltips");
		   
			//Compare the first page headers with its tooltips
			boolean result2=hp.headersTooltipComparep1(); 
			Assert.assertEquals(result2,true);
			
			templist=hp.listofheaders;
			
			logger.info("Click SeeAll");
			
			//Navigate to next page by clicking SeeAll
			hp.clickSeeAll();         
			captureScreenShot("4.see_all");
		}
		catch(Exception e) {
			
			logger.error("First Page test failed");
			e.printStackTrace();
			Assert.fail();
			
		}	
	}
	
	@Test(priority=1,groups= {"regression"})
	public void secondPage() throws InterruptedException, IOException {
		
		try {
			logger.info("Second Page is Opened");
			NewsPage np=new NewsPage(driver);
			captureScreenShot("5.second_page_opened");
			
			logger.info("Reading all the news");
			//Capturing the Second page headers and tooltips 
			np.newsHeader(driver);    
			 
			
			logger.info("Comparison of first and second page headers");
			 //Comparing first page headers and Second page headers
			boolean headcompare= np.headersCompare(templist);		     
			Assert.assertEquals(headcompare,true);
			
			logger.info("Comparison of second page headers with tooltips");
			captureScreenShot("6.Second_Page_End");
			//Comparing Second page headers and tooltips
			boolean headtipcompare= np.headersTooltipComparep2();		
			Assert.assertEquals(headtipcompare,true);
			
			logger.info("Comparison of second page first five headers with tooltips");
			
			//Comparing First Five headers and tooltips
			boolean fiveheaders=np.headersTooltipComparep2Five();          
			Assert.assertEquals(fiveheaders,true);
			
			logger.info("Printing all news details to excel");
			
			//Printing all News Details in Excel
			np.newsDetailsPrint();      
			
			logger.info("Printing all First Five news details to excel");
			
			//Printing first five News Details in Excel
			np.FivenewsDetailsPrint();     
		}
		catch(Exception e) {
			
			logger.error("Second Page test failed");
			e.printStackTrace();
			Assert.fail();
		}
		
		logger.debug(".....application logs end.....");
		logger.info("**** finished testCase_001 *****");
		
		System.out.println("THE END");
		
	}
	
	
}
