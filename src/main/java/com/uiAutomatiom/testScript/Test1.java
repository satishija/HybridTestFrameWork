package com.uiAutomatiom.testScript;

import org.testng.annotations.Test;

import com.uiAutomatiom.TestBase.TestBase;
import com.uiAutomatiom.helper.assertions.AssertionHelper;

public class Test1 extends TestBase {
	
	@Test
	public void testLogin(){
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		driver.get("https://www.facebook.com/");
		captureScreen("my test", driver);
	}

	
}
