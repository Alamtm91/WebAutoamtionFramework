package com.ui.tests;

import static com.constants.Browser.CHROME;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Browser;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.ui.pages.HomePage;
import com.utilty.BrowserUtility;
import com.utilty.LamdaTestUtility;
import com.utilty.LoggerUtility;

public class TestBase {

	protected HomePage homepage;
	Logger logger = LoggerUtility.getLogger(this.getClass());
	private boolean isLambdaTest = false;
	private boolean isHeadless = true;

	@Parameters({"browser","isLambdaTest","isHeadless"})
	@BeforeMethod(description = "Load the Homepage of the WebSite")
	public void setup(
			@Optional("chrome") String browser, 
			@Optional("false")boolean isLambdaTest, 
			@Optional("true") boolean isHeadless,ITestResult result) {
		
		this.isLambdaTest=isLambdaTest;
		WebDriver lamdadriver;
		if (isLambdaTest) {
			lamdadriver = LamdaTestUtility.intializeLamdaTestSession("chrome", result.getMethod().getMethodName());
			homepage = new HomePage(CHROME, isHeadless);
		} else {
			logger.info("Load the Homepage of the Website");
			homepage = new HomePage(CHROME, false);
		}
	}

	public BrowserUtility getInsance() {
		return homepage;
	}
	
	public void tearDown() {
		if(isLambdaTest) {
			LamdaTestUtility.quitSession();
		}else {
			homepage.getDriver().quit();
		}
	}

}
