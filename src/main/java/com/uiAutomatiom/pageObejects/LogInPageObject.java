package com.uiAutomatiom.pageObejects;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.uiAutomatiom.helper.browerConfigurations.config.ObjectReader;
import com.uiAutomatiom.helper.logger.LogHelper;
import com.uiAutomatiom.helper.wait.WaitHelper;

public class LogInPageObject {
	
	private static Logger log = LogHelper.getLogger(LogInPageObject .class);
	private WebDriver driver ;
	WaitHelper waitHelper;
	
	@FindBy (name="firstname")
    	WebElement fname;
    
	@FindBy (name="lastname")
	  WebElement lname;
	

	
	public LogInPageObject(WebDriver driver){
		this.driver =driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.pageloadTimeOut(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);
	}
	
	public void enterFirstName(){
		fname.sendKeys(ObjectReader.reader.getFirstName());
	}
	
	public void enterLastName(){
		lname.sendKeys(ObjectReader.reader.getLastName());
	}
	
}
