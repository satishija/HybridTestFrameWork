package com.uiAutomatiom.helper.wait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.uiAutomatiom.helper.logger.LogHelper;

public class WaitHelper {

	private Logger log = LogHelper.getLogger(WaitHelper.class);
	private WebDriver driver;

	public WaitHelper(WebDriver driver) {
		this.driver = driver;

	}

	

	/*
	 * Purpose: This means that we can tell Selenium that we would like it to
	 * wait for a certain amount of time before throwing an exception that it
	 * cannot find the element on the page. We should note that implicit waits
	 * will be in place for the entire time the browser is open. This means that
	 * any search for elements on the page could take the time the implicit wait
	 * is set for.
	 */

	public void setImplicitWait(long timeout, TimeUnit unit) {
		log.info("implicit wait set to " + timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit);

	}

	/*
	 * Purpose: Sets the amount of time to wait for a page load to complete
	 * before throwing an error. If the timeout is negative, page loads can be
	 * indefinite
	 * 
	 */
	public void pageloadTimeOut(long timeout, TimeUnit unit) {
		log.info("Page load time out value is set to " + timeout);
		driver.manage().timeouts().pageLoadTimeout(timeout, unit);
		log.info("page is loaded ");
	}

	/*
	 * Purpose: Sets the amount of time to wait for an asynchronous script to
	 * finish execution before throwing an error. If the timeout is negative,
	 * then the script will be allowed to run indefinitely.
	 */

	public void setScriptTimeOut(long timeout, TimeUnit unit) {
		log.info("Set script time out value is  set to " + timeout);
		driver.manage().timeouts().setScriptTimeout(timeout, unit);

	}

	private WebDriverWait getWait(int timeOutInSeconds, int poolingEveryInMilliSeconds) {

		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(poolingEveryInMilliSeconds, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}

	public void waitforElementWithPollingtime(WebElement element, int timeOutInSeconds,
			int poolingEveryInMilliSeconds) {
		log.info("waiting for  :  " + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = getWait(timeOutInSeconds, poolingEveryInMilliSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("Element is visible now");
	}

	public void waitforElementClickable(WebElement element, int timeOutInSeconds) {
		log.info("waiting for  :  " + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("Element is clickeble now");
	}

	public void waitForFrameToAvailableAndSwitchTo(WebElement element, int timeOutInSeconds) {

		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));

	}

	public void waitForTextToBePresentInElement(WebElement element, int timeOutInSeconds, String text) {

		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));

	}
	
	/*
	 * Fluent wait 
	 */
	
	public Wait<WebDriver> getfluentwait(int timeOutInSeconds, int poolingEveryInMilliSeconds){
		
		  Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOutInSeconds))
				.pollingEvery(Duration.ofSeconds(poolingEveryInMilliSeconds))
				.ignoring(NoSuchElementException.class);
		return fwait;
		
	}
	
	/*
	 * calling Fluent wait 
	 */
	public WebElement waitForElement(WebElement element, int timeOutInSeconds, int poolingEveryInMilliSeconds){
		
		  Wait<WebDriver> fwait = getfluentwait(timeOutInSeconds, poolingEveryInMilliSeconds);
		   fwait.until(ExpectedConditions.visibilityOf(element));
		   return element;
		
		
	}

}
