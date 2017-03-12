package com.mail.server.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.testng.annotations.Test;

public class MailServerLoginTests extends TestBase {

	@Test(dataProvider = "InboxUserTrueValue", enabled = true)
	public void succsedLoginToMailServer_InboxUserValue(String userName, String password, String inboxUserValue ) throws Throwable {

		String waitForInboxUserValue = "//span[@title='" + inboxUserValue + "']";
		String expectedUserInfo = inboxUserValue;	
		
		navigateToMainMailServerPage();
		loginToMailServerAndWait(userName, password, waitForInboxUserValue);
		String userInfo = getInboxUserInfo();
		
		assertThat(userInfo, equalTo(expectedUserInfo));
	}

	@Test(enabled = false)
	public void succsedLoginToMailServer_PaneLauncherValue() throws Throwable {

		String userName = "itransition\\g.tarazevich";
		String password = "123456";
		String waitForPaneLauncher = ".//*[@id='O365_TopMenu']/div/div/div[1]/div[14]/button";
		String expectedUserInfo = "g.tarazevich@a1qa.com";
		
		navigateToMainMailServerPage();
		loginToMailServerAndWait(userName, password, waitForPaneLauncher);
		 
		String userInfo = getPaneLauncherUserInfo(waitForPaneLauncher );
		
		assertThat(userInfo, equalTo(expectedUserInfo));
	}

}
