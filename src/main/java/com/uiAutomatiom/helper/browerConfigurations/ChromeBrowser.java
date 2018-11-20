package com.uiAutomatiom.helper.browerConfigurations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.uiAutomatiom.resource.ResourceHelper;

public class ChromeBrowser {

	public ChromeOptions chromeOptions() {

		ChromeOptions option = new ChromeOptions();
		option.addArguments("- -test-type");
		option.addArguments("- -disable-popup-blocking");
		DesiredCapabilities chrome = DesiredCapabilities.chrome();
		chrome.setJavascriptEnabled(true);
		option.setCapability(ChromeOptions.CAPABILITY, chrome);

		if (System.getProperty("os.name").contains("Linux")) {
			option.addArguments("- -headless", "window-size=1024,768", "- -no-sandbox");
		}
		return option;

	}

	public WebDriver  getChromeDriver(ChromeOptions option) {

		/*if (System.getProperty("os.name").contains("Mac")) {
			// Use executable jar not .exe
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath(""));
			//return new ChromeDriver(cap);
		} else if (System.getProperty("os.name").contains("Window")) {
			System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("\\src\\main\\resources\\configfile\\Drivers\\chromedriver.exe"));
			return new ChromeDriver(option);
		} else if (System.getProperty("os.name").contains("Linux")) {
			//System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("/use/bin/chrome"));
			//return new ChromeDriver(cap);
		} else {

			return null;

		} */

	System.setProperty("webdriver.chrome.driver", ResourceHelper.getResourcePath("\\src\\main\\resources\\configfile\\Drivers\\chromedriver.exe"));
	return new ChromeDriver(option);
}
}
