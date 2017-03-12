package com.mail.server.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.testng.annotations.Test;

public class MailServerLoginTests extends TestBase {

	@Test(enabled = true)
	public void succsedLoginToMailServer_InboxUserValue() throws Throwable {

		navigateToMainMailServerPage();
		loginToMailServer();
		String userInfo = getInboxUserInfo();
		String expectedUserInfo = "Tarazevich, Georgy";

		assertThat(userInfo, equalTo(expectedUserInfo));
	}

	@Test(enabled = false)
	public void succsedLoginToMailServer_PaneLauncherValue() throws Throwable {

		navigateToMainMailServerPage();

		loginToMailServer();
		
		String userInfo = getUserInfoFromPersonaPaneLauncher();
		String expectedUserInfo = " ";

		assertThat(userInfo, equalTo(expectedUserInfo));
	}

}
