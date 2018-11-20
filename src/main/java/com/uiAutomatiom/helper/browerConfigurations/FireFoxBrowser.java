package com.uiAutomatiom.helper.browerConfigurations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.uiAutomatiom.resource.ResourceHelper;

public class FireFoxBrowser {

	public FirefoxOptions getFireFoxOptions() {

		DesiredCapabilities firefox = new DesiredCapabilities().firefox();
		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(true);

		firefox.setCapability(FirefoxDriver.PROFILE, profile);
		firefox.setCapability("marionette", true);
		FirefoxOptions firefoxOptions = new FirefoxOptions();

		if (System.getProperty("os.name").contains("Linux")) {
			firefoxOptions.addArguments("- -headless", "window-size=1024,768", "- -no-sandbox");
		}
		return firefoxOptions;
	}

	public WebDriver  getFireFoxDriver(FirefoxOptions fap) {

		if (System.getProperty("os.name").contains("Mac")) {
			// Use executable jar not .exe
			System.setProperty("webdriver.firefox.driver", ResourceHelper.getResourcePath(""));
			return new FirefoxDriver(fap);
		} else if (System.getProperty("os.name").contains("Window")) {
			System.setProperty("webdriver.firefox.driver", ResourceHelper.getResourcePath(""));
			return new FirefoxDriver(fap);
		} else if (System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.firefox.driver", ResourceHelper.getResourcePath("/use/bin/chrome"));
			return new FirefoxDriver(fap);
		} else {

			return null;

		}
	}
}
