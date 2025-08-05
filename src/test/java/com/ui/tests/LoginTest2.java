package com.ui.tests;

import static com.constants.Browser.CHROME;

import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.HomePage;
import com.ui.pojo.User;
import com.utilty.LoggerUtility;


@Listeners(com.ui.listerner.TestListner.class)

public class LoginTest2 extends TestBase{

	HomePage homepage;
	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	
	
	@Test(description = "verify with the valid user is able to login into application", groups = {"e2e","sanity"}, 
			dataProviderClass = com.ui.dataProviders.LoginDataProvider.class, dataProvider = "loginTestDataProvider")
	public void loginTest(User user) {
		
		
		String username = homepage.goToLoginPage().doLoginwith(user.getEmailAddress(), user.getPassword()).getUserName();
		System.out.println(username);
		Assert.assertEquals(username, "Jatin Sharma");
		

	}

}
