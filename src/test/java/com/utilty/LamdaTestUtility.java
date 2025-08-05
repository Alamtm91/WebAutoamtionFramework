package com.utilty;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LamdaTestUtility {

	private static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
	private static ThreadLocal<WebDriver> driverlocal= new ThreadLocal<WebDriver>();
	private static ThreadLocal<DesiredCapabilities> capabiliteslocal = new ThreadLocal<DesiredCapabilities>();
	
	public static WebDriver intializeLamdaTestSession(String browser,String testName)  {
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", "127");
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("user", "alam300491qa");
        ltOptions.put("accessKey", "LT_bzzARGwVOQcMYvb8bQW1CztWzxHhb5XU2J5Q3CcMEeX8cyy");
        ltOptions.put("build", "Selenium 4");
        ltOptions.put("name", testName);
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "4.23.0");
        capabilities.setCapability("LT:Options", ltOptions);
        capabiliteslocal.set(capabilities);
        WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL(HUB_URL), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        driverlocal.set(driver);
        //System.out.println(driver);
        
        return driverlocal.get();
	}
	
	
	public static void quitSession() {
		if(driverlocal.get()!=null) {
			driverlocal.get().quit();
		}
	}
}
