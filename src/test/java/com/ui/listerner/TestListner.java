package com.ui.listerner;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.tests.TestBase;
import com.utilty.BrowserUtility;
import com.utilty.ExtentReportedUtility;
import com.utilty.LoggerUtility;

public class TestListner implements ITestListener {

	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	//Job of ExtentSparkReporter class is provide you info of the file like Eg: style, look in html
	ExtentSparkReporter extentSparkReporter;
	ExtentReports extentReports;// job of this is heavy lifting of the file
	ExtentTest extentTest;// job of this is store info about the file

	public void onTestStart(ITestResult result) {
		logger.info(result.getMethod().getMethodName());
		logger.info(result.getMethod().getDescription());
		logger.info(Arrays.toString(result.getMethod().getGroups()));
		ExtentReportedUtility.createExtentTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		logger.info(result.getMethod().getMethodName() + " " + "PASSED");
		ExtentReportedUtility.getTest().log(Status.PASS, result.getMethod().getMethodName() + " " + "PASSED");
	}

	public void onTestFailure(ITestResult result) {
		logger.error(result.getMethod().getMethodName() + " " + "FAILED");
		logger.error(result.getThrowable().getMessage());
		ExtentReportedUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName() + " " + "FAILED");
		ExtentReportedUtility.getTest().log(Status.FAIL, result.getThrowable().getMessage());
		
		Object testclass = result.getInstance();
		BrowserUtility browerutility=((TestBase)testclass).getInsance();
		logger.info("Capturing Screenshot for the failed tests");
		String screenShotPath = browerutility.takeScreenShot(result.getMethod().getMethodName());
		logger.info("Attaching Screenshot for the failed tests into file");
		ExtentReportedUtility.getTest().addScreenCaptureFromPath(screenShotPath);
	}

	public void onTestSkipped(ITestResult result) {
		logger.warn(result.getMethod().getMethodName() + " " + "SKIPPED");
		ExtentReportedUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName() + " " + "SKIPPED");
	}

	public void onStart(ITestContext context) {
		logger.info("Test Suite Started");
		ExtentReportedUtility.setupSparkReporter("report.html");
	}

	public void onFinish(ITestContext context) {
		logger.info("Test Suite Completed");
		ExtentReportedUtility.flushReport();
	}

}
