package com.uiAutomatiom.helper.listener;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.uiAutomatiom.helper.logger.LogHelper;

public class ExtentListener implements ITestListener {
	//public static ExtentReports extent;
	//public static ExtentTest test;
	private  Logger log = LogHelper.getLogger(ExtentListener.class);
	
	
	public void onTestStart(ITestResult result) {
	//	test.log(Status.INFO, result.getName() + " Started ..");
		Reporter.log(result.getMethod().getMethodName() + " test started");
		log.info(result.getMethod().getMethodName() + " test started");
	}

	public void onTestSuccess(ITestResult result) {
		//test.log(Status.PASS, result.getName() + " Passed ..");
		Reporter.log(result.getMethod().getMethodName() + " test success");
		log.info(result.getMethod().getMethodName() + " test success");
	}

	public void onTestFailure(ITestResult result) {
		//test.log(Status.FAIL, result.getName() + " " + result.getThrowable());
		Reporter.log(result.getMethod().getMethodName() + " test failed" + result.getThrowable());
		log.error(result.getMethod().getMethodName() + " test failed" + result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
		//test.log(Status.SKIP, result.getName() + " " + result.getThrowable());
		Reporter.log(result.getMethod().getMethodName() + " test skipped" + result.getThrowable());
		log.warn(result.getMethod().getMethodName() + " test skipped" + result.getThrowable());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		//extent = ExtentManager.getInstance();
		//test = extent.createTest(getClass().getSimpleName());
		Reporter.log(context.getCurrentXmlTest().getName() + " test started");
		log.info(context.getCurrentXmlTest().getName() + " test started");
	}

	public void onFinish(ITestContext context) {
		//extent.flush();
		Reporter.log(context.getName() + " test finished");
		log.info(context.getName() + " test finished");
	}

}
