package com.uiAutomatiom.helper.select;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uiAutomatiom.helper.logger.LogHelper;

public class DropDownHelper {
	private static Logger log = LogHelper.getLogger(DropDownHelper.class);
	private WebDriver driver;

	public DropDownHelper(WebDriver driver) {
		this.driver = driver;
		log.info("DropDownHelper object created..");
	}

	public void selectUsingValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
		log.info(value + " is selected in the " + element.toString() + " the drop down ");
	}

	public void selectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
		log.info("selectUsingIndex  and index is  " + index);
	}

	public void selectUsingVisibleText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
		log.info("selectUsingVisibleText  and  text  is  " + text);
	}

	public void deSelectUsingValue(WebElement element, String text) {
		Select select = new Select(element);
		select.deselectByValue(text);
		log.info("deSelectUsingValue  and  text  is  " + text);

	}

	public void deSelectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.deselectByIndex(index);
		log.info("deSelectUsingIndex  and index is  " + index);

	}

	public void deSelectUsingVisibleText(WebElement element, String text) {
		Select select = new Select(element);
		select.deselectByVisibleText(text);
		log.info("deSelectUsingVisible  and index is  " + text);
	}

	public String getFirstSelectedOption(WebElement element) {
		Select select = new Select(element);
		WebElement selectedOption = select.getFirstSelectedOption();
		log.info("first selected options is " + selectedOption);
		return selectedOption.getText();
	}

	public List<String> getAllDropDownValues(WebElement element) {
		Select select = new Select(element);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();
		for (WebElement ele : elementList) {
			String ss = ele.getText();
			log.info("values in the list " + ss);
			valueList.add(ss);
		}
		return valueList;
	}

}
