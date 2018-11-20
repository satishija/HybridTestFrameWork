package com.uiAutomatiom.helper.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.uiAutomatiom.resource.ResourceHelper;

public class LogHelper {

	private static boolean root = false;
	public static Logger getLogger(Class cls) {
		if (root) {
			return Logger.getLogger(cls);
		}
		PropertyConfigurator.configure(ResourceHelper.getResourcePath("\\src\\main\\resources\\configfile\\log4j.properties"));
		root = true;
		return Logger.getLogger(cls);
	}

	/*
	  public static void main(String[] args) { 
	 * Logger log =  LogHelper.getLogger(LogHelper.class);
	 *  log.info("Hi satish Here---");
	 * log.info("Hi satish Here---"); log.info("Hi satish Here---"); String
	 * pro=System.getProperty("user.dir"); System.out.println("property---->" +
	 * pro); }
	 */
}