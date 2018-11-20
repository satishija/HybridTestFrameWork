package com.uiAutomatiom.helper.browerConfigurations.config;

import com.uiAutomatiom.helper.browerConfigurations.BrowserType;

public interface ConfigReader {
	
	public int getImplicitWait();
	public int getExplicitWait();
	public int getPageLoadTime();
	public BrowserType getBrowerType();
	public String getFirstName();
	public String getUrl();
	public String getLastName();

}
