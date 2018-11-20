package com.uiAutomatiom.helper.browerConfigurations;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.internal.ElementScrollBehavior;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.uiAutomatiom.resource.ResourceHelper;

public class IExploreBrowser {
	
	public InternetExplorerOptions getIExplorerCapabilities(){
		DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
		cap.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR, ElementScrollBehavior.BOTTOM);
		cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		cap.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		cap.setJavascriptEnabled(true);
		InternetExplorerOptions internetexplorerOption = new InternetExplorerOptions(cap);
		return internetexplorerOption;
	}
   
	public   WebDriver getIEDriver(InternetExplorerOptions cap) {
			System.setProperty("webdriver.ie.driver", ResourceHelper.getResourcePath(""));
            return new InternetExplorerDriver(cap);

		}

	
	}
	
	
	

