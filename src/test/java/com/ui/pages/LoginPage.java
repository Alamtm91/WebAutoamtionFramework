package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utilty.BrowserUtility;

public class LoginPage extends BrowserUtility{

	private static final By EMAIL_TEXT_BOX_LOCATOR = By.id("email");
	private final static By PASSWORD_TEXT_BOX_LOCATOR = By.id("passwd");
	private static final By SUBMIT_BUTTON_LOCATOR=By.id("SubmitLogin");
	
	
	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	 public MyAccountPage doLoginwith(String emailAddress, String Password) {
		 enterText(EMAIL_TEXT_BOX_LOCATOR, emailAddress);
		 enterText(PASSWORD_TEXT_BOX_LOCATOR, Password);
		 clickOn(SUBMIT_BUTTON_LOCATOR);
		 MyAccountPage account = new MyAccountPage(getDriver());
		 return account;
				 
	 }
}
