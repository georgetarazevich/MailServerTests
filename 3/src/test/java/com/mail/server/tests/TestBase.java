package com.mail.server.tests;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

public class TestBase {
	private String baseUrl = "https://webmail.itransition.com";
	private WebDriver driver;

	public void loginToMailServerAndWait(String userName, String password,
			String waitForElement) {

		WebElement usernameWebElement = driver.findElement(By
				.xpath(".//*[@id='username']"));
		usernameWebElement.sendKeys(userName);

		WebElement passwordWebElement = driver.findElement(By
				.xpath(".//*[@id='password']"));
		passwordWebElement.sendKeys(password);

		WebElement submitCredsWebElement = driver.findElement(By
				.xpath(".//*[@id='SubmitCreds']"));
		submitCredsWebElement.click();

		waitForElementToBeClickable(waitForElement);
	}

	public String getInboxUserInfo() {

		return driver.findElement(
				By.xpath("//span[@title='Tarazevich, Georgy']")).getText();
	};

	public String getPaneLauncherUserInfo(String waitForPaneLauncher)
			throws Throwable {

		Thread.sleep(40000);
		driver.findElement(By.xpath(waitForPaneLauncher)).click();
		return driver.findElement(
				By.xpath("//span[text()='g.tarazevich@a1qa.com']")).getText();
	}

	public void navigateToMainMailServerPage() {
		try {
			driver.get(baseUrl);
		} catch (Exception e) {
		}
	}

	private void waitForElementToBeClickable(String xPathElement) {

		(new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(2, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class))
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath(xPathElement)));

	}

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@DataProvider(name = "InboxUserTrueValue()")
	public Object[][] InboxUserTrueValue() {
		return new Object[][] { { "itransition\\g.tarazevich", "123456", "Tarazevich, Georgy" } };
	}

}
