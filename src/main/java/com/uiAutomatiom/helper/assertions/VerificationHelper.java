package com.uiAutomatiom.helper.assertions;

import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uiAutomatiom.helper.logger.LogHelper;

public class VerificationHelper {

	private  Logger log = LogHelper.getLogger(VerificationHelper.class);
	private WebDriver driver;

	public VerificationHelper(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info(" Element is displayed--" + element.getText());
			return true;
		} catch (NoSuchElementException e) {
			log.info("Element is not displayed or Hidden " + e.getCause());
			return false;
		}
	}

	public boolean isNotDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info(" Element present--" + element.getText());
			return false;
		} catch (NoSuchElementException e) {
			log.info(" Element not found or Hidden ");
			return true;
		}
	}

	public String getText(WebElement element) {
		if (null == element) {
			log.info("web element is null");
			return null;
		}
		boolean status = isDisplayed(element);
		if (status) {
			log.info("element text is " + element.getText());
			return element.getText();
		} else {
			return null;
		}
	}

}
