package com.uiAutomatiom.helper.browerConfigurations.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import com.uiAutomatiom.helper.browerConfigurations.BrowserType;
import com.uiAutomatiom.resource.ResourceHelper;

public class PropertyReader implements ConfigReader {
	private static FileInputStream file;
	public static Properties property;

	public PropertyReader() {

		try {
			String configFilePath = ResourceHelper.getResourcePath("\\src\\main\\resources\\configfile\\config.properties");
			file = new FileInputStream(new File(configFilePath));
			property = new Properties();
			property.load(file);

			 configFilePath = ResourceHelper.getResourcePath("\\src\\main\\resources\\configfile\\config.properties");
			file = new FileInputStream(new File(configFilePath));
			property = new Properties();
			property.load(file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getImplicitWait() {
		return Integer.parseInt(property.getProperty("implicitwait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(property.getProperty("explicitwait"));
	}

	public int getPageLoadTime() {
		return Integer.parseInt(property.getProperty("pageloadtime"));

	}

	public BrowserType getBrowerType() {
		return BrowserType.valueOf(property.getProperty("browerType"));
	}
	
	public String getUrl(){
		return property.getProperty("url");
	}

	 public String getFirstName(){
		 return property.getProperty("firstName");
	 }
	
	 public String getLastName(){
		 return property.getProperty("lastName");
	 }
	 
}
