package com.uiAutomatiom.helper.window;

import java.util.Set;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import com.uiAutomatiom.helper.logger.LogHelper;

public class WindowHelper {

	private Logger log = LogHelper.getLogger(WindowHelper.class);
	private WebDriver driver;

	public WindowHelper(WebDriver driver) {
		this.driver = driver;
	}
	/*
	 * This method will switch to parent window
	 */

	public void switchToParentWindow() {
		log.info(" switching to parent window ");
		try {
			driver.switchTo().defaultContent();
			log.info(" switched to parent window ");
		} catch (Exception e) {
			log.info("Unable to locate parent window " + e.getStackTrace());
		}
	}

	/*
	 * This method switch to child window based on index
	 */
	public void switchToWindow(int index) {
		try {
			Set<String> windows = driver.getWindowHandles();
			int i = 1;
			for (String window : windows) {
				if (i == index) {
					log.info("switched to " + index + " window");
					driver.switchTo().window(window);
				} else {
					i++;
				}
			}
		} catch (NoSuchWindowException e) {
			log.info("Unable to locate window with index " + index + e.getStackTrace());
		} catch (Exception e) {
			log.info("Unable to locate window with id " + index + e.getStackTrace());
		}
	}

	/*
	 * 
	 * This method will close all tabbed window and switched to main window.
	 */
	public void closeAllTabsSwitchToTheMainWindow() {
		String mainWindow = "";
		try {

			mainWindow = driver.getWindowHandle();
			Set<String> windows = driver.getWindowHandles();
			for (String window : windows) {
				if (!window.equalsIgnoreCase(mainWindow)) {
					driver.close();
				}
			}
			driver.switchTo().window(mainWindow);
			log.info("switched to main window");
		} catch (NoSuchWindowException e) {

			log.info("Unable to locate  " + mainWindow + "or child window " + e.getStackTrace());
		} catch (Exception e) {
			log.info("unable to locate element " + e.getStackTrace());
		}
	}

	public void navigateBack() {
		log.info("Navigating browser back");
		driver.navigate().back();
	}

	public void navigateForword() {
		log.info("Navigating browser forword");
		driver.navigate().forward();
	}

	public void pageRefresh() {
		log.info("Re freshing the page");
		driver.navigate().refresh();
	}
}
