package com.uiAutomatiom.helper.alert;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import com.uiAutomatiom.helper.logger.LogHelper;

public class AlertHelper {
	private static Logger log = LogHelper.getLogger(AlertHelper.class);

	private WebDriver driver;

	public AlertHelper(WebDriver driver) {
		this.driver = driver;
	}

	public Alert  getAlert() {
		log.info("alert test : " + driver.switchTo().alert().getText());
		Alert alert = driver.switchTo().alert();
		return alert;
	}

	public void acceptAlert() {
		getAlert().accept();
		log.info("alert accepted ...");
	}

	public void dismissAlert() {
		getAlert().dismiss();
		log.info("alert dismissed ...");
	}

	public String getAlertText() {
		String text = getAlert().getText();
		log.info("alert text : " + text);
		return text;

	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			log.info("alert is present..");
			return true;
		} catch (NoAlertPresentException e) {
			log.info("alert is not present.." + e.getCause());
			return false;
		}
	}

	public void acceptAlertIfPresent() {
		if (isAlertPresent()) {
			acceptAlert();
		} else {
			log.info("alert is not present..");
		}
	}
  
	public void dismissAlertIfPresent(){
		if (isAlertPresent()) {
			dismissAlert();
		} else {
			log.info("alert is not present..");
		}
	}
	
	public void sendDataInAlertBox(String text){
		if (isAlertPresent()){
			Alert alert = getAlert();
			alert.sendKeys(text);
			alert.accept();
			log.info(" text entered in alert box is  .." + text);
		}
		
	}
	
	
}
