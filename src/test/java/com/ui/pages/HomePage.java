package com.ui.pages;

import org.openqa.selenium.By;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utilty.BrowserUtility;
import com.utilty.JsonUtility;
import com.utilty.PropertiesUtil;

public final class HomePage extends BrowserUtility {

	private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),\"Sign in\")]");

	public HomePage(Browser browserName, boolean isHeadLess) {
		super(browserName,isHeadLess);
		goToWebsite(JsonUtility.readJson(QA));
	}

	public LoginPage goToLoginPage() {

		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginpage = new LoginPage(getDriver());
		return loginpage;
	}
}
