package com.uiAutomatiom.testScript;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.uiAutomatiom.TestBase.TestBase;
import com.uiAutomatiom.helper.browerConfigurations.config.ObjectReader;
import com.uiAutomatiom.helper.logger.LogHelper;
import com.uiAutomatiom.pageObejects.LogInPageObject;

public class LoginTest  extends TestBase {
	
	private final Logger log = LogHelper.getLogger(LoginTest .class);
	
	@BeforeMethod
	public void beforeMethod(){
		System.out.println("Login test before method");
	}
	
	@Test(description= "Login test")
	public void  LoginTest  (){
		getApplicationUrl(ObjectReader.reader.getUrl());
		LogInPageObject login = new LogInPageObject (driver);
		login.enterFirstName();
		login.enterLastName();
		
		
	}
	

}
