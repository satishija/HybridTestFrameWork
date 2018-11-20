package com.uiAutomatiom.resource;

import com.uiAutomatiom.helper.logger.LogHelper;

public class ResourceHelper {

//logger should not be used in this method because it wont create log object.
	public static String getResourcePath(String path) {

		String basePath = System.getProperty("user.dir");
		System.out.println(basePath);
		System.out.println(path);
		System.out.println(basePath + path);
		return  basePath + path;
	}
}
