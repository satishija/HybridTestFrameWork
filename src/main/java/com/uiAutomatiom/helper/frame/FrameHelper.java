package com.uiAutomatiom.helper.frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.uiAutomatiom.helper.logger.LogHelper;


public class FrameHelper {

	private Logger log = LogHelper.getLogger(FrameHelper.class);

	private WebDriver driver;

	FrameHelper(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * this method will switchToframe based on frame index
	 * 
	 * @param frameIndex
	 */
	public void switchToFrame(int frameIndex) {
		try {

			driver.switchTo().frame(frameIndex);

			log.info("switched to " + frameIndex + " frame");

		} catch (NoSuchFrameException e) {
			log.info("Unable to locate frame with id " + frameIndex + e.getStackTrace());

		} catch (Exception e) {

			log.info("Unable to locate frame with id " + frameIndex + e.getStackTrace());
		}
	}

	/*
	 * this method will switchToFrame based on name
	 * 
	 * @param Name
	 */
	public void switchToFrame(String name) {
		try {
			driver.switchTo().frame(name);
			log.info("switched to " + name);
		} catch (NoSuchFrameException e) {
			log.info("Unable to locate frame with name " + name + e.getStackTrace());
		} catch (Exception e) {
			log.info("Unable to locate frame with id " + name + e.getStackTrace());
		}
	}

	/*
	 * this method switchToFrame based on WebElement
	 * 
	 * @param WebElement
	 */

	public void switchToFrame(WebElement element) {
		try {
			driver.switchTo().frame(element);
			log.info("Navigated to frame with element " + element.toString());
		} catch (NoSuchFrameException e) {
			log.info("Unable to locate frame with element " + element.toString() + e.getStackTrace());
		} catch (StaleElementReferenceException e) {
			log.info("Element with " + element.toString() + " is not attached to page document " + e.getStackTrace());
		} catch (Exception e) {
			log.info("Unable to navigate to frame with element  " + element.toString()
					+ " is not attached to page document " + e.getStackTrace());
		}

	}

	/*
	 * this method switchToDefaultFrame navigate back to mail web page from
	 * frame
	 * 
	 * @param
	 */

	public void switchToDefaultFrame() {
		try {
			driver.switchTo().defaultContent();
			log.info("Navigated back to webpage from frame");
		} catch (Exception e) {
			log.info(" Unable to navigate back to main web page from frame" + e.getStackTrace());
		}
	}

	/*
	 * this method used to switch multiple frames ,first it will switch to
	 * parent and then child.
	 * 
	 * @param parentFrame ,childFrame
	 */

	public void switchToframe(String parentFrame, String childFrame) {
		try {
			driver.switchTo().frame(parentFrame).switchTo().frame(childFrame);
			log.info("Navigated to innerframe with id " + childFrame + "which is present on frame with id"
					+ parentFrame);
		} catch (NoSuchFrameException e) {
			log.info("Unable to locate frame with id " + parentFrame + " or " + childFrame + e.getStackTrace());
		} catch (Exception e) {
			log.info("Unable to navigate to innerframe with id " + childFrame + "which is present on frame with id"
					+ parentFrame + e.getStackTrace());
		}

	}

}
