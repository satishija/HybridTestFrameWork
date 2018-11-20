package com.uiAutomatiom.TestBase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.uiAutomatiom.helper.browerConfigurations.BrowserType;
import com.uiAutomatiom.helper.browerConfigurations.ChromeBrowser;
import com.uiAutomatiom.helper.browerConfigurations.FireFoxBrowser;
import com.uiAutomatiom.helper.browerConfigurations.IExploreBrowser;
import com.uiAutomatiom.helper.browerConfigurations.config.ObjectReader;
import com.uiAutomatiom.helper.browerConfigurations.config.PropertyReader;
import com.uiAutomatiom.helper.logger.LogHelper;
import com.uiAutomatiom.helper.wait.WaitHelper;
import com.uiAutomatiom.resource.ResourceHelper;
import com.uiAutomatiom.utils.ExtentManager;

public class TestBase {

	private static Logger log = LogHelper.getLogger(TestBase.class);
	public  WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static File reportDirectory;

	@BeforeSuite
	public void beforeSuite() {
		extent = ExtentManager.getInstance();
	}

	@BeforeClass
	public void beforeClass() {
		test = extent.createTest(getClass().getSimpleName());
	}

	@BeforeTest
	public void beforeTest() throws Exception {
		// ConfigReader reader = new PropertyReader();
		ObjectReader.reader = new PropertyReader();
		reportDirectory = new File(ResourceHelper.getResourcePath("\\src\\main\\resources\\screenShots"));
		setUpDriver(ObjectReader.reader.getBrowerType());
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		System.out.println("Login test Test base method");
		test.log(Status.INFO, method.getName() + " test started");

	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getThrowable());
			String imagePath = captureScreen(result.getTestName(), driver);
			test.addScreenCaptureFromPath(imagePath);
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName() + "is PASS");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getThrowable());
		}
		extent.flush();
	}

	@AfterTest
	public void afterTest() {
		/*if (driver == null) {
			driver.quit();
		}*/
		driver.close();
		driver.quit();
	}

	public WebDriver getBrowerObject(BrowserType btype) throws Exception {

		try {

			switch (btype) {
			case Chrome:
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
 				ChromeOptions options = chrome.chromeOptions();
				return chrome.getChromeDriver(options);
			
			case Firefox:
				FireFoxBrowser firefox = FireFoxBrowser.class.newInstance();
				FirefoxOptions foptions = firefox.getFireFoxOptions();
				return firefox.getFireFoxDriver(foptions);
			case Iexplorer:
				IExploreBrowser ie = IExploreBrowser.class.newInstance();
				InternetExplorerOptions cap = ie.getIExplorerCapabilities();
				return ie.getIEDriver(cap);
			default:
				throw new Exception("Driver not found " + btype.name());
			}
		} catch (Exception e) {
			log.info(e.getMessage());
			throw (e);

		}
	}

	public void setUpDriver(BrowserType btype) throws Exception {
		driver = getBrowerObject(btype);
		log.info("Initiaization of driver " + driver.hashCode());
		WaitHelper wait = new WaitHelper(driver);
		wait.setImplicitWait(ObjectReader.reader.getImplicitWait(), TimeUnit.SECONDS);
		wait.pageloadTimeOut(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public String captureScreen(String fileName, WebDriver driver) {
		if (driver == null) {
			log.info("driver is null");
			return null;
		}
		if (fileName == "") {
			fileName = "Blank";
		}
		File destFile = null;
		Calendar calander = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File screFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			destFile = new File(
					reportDirectory + "/ " + fileName + "_" + formatter.format(calander.getTime()) + ".png");
			Files.copy(screFile.toPath(), destFile.toPath());
			Reporter.log("<a href =' " + destFile.getAbsolutePath() + " '>< img src= ' " + destFile.getAbsolutePath()
					+ " 'height='100' width='100' /></a>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}

	public void navigateScreen(WebDriver driver) {
		log.info("capturing UI navigation screens...");
		String screen = captureScreen("", driver);
		try {
			test.addScreenCaptureFromPath(screen);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void logExtentReport(String s1) {
		test.log(Status.INFO, s1);
	}

	public void getApplicationUrl(String url) {
		driver.get(url);
	}
}
