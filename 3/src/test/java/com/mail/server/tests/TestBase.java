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

public class TestBase {
	private String baseUrl = "https://webmail.itransition.com";
	private String userName = "itransition\\g.tarazevich";
	private String password = "123456";
	private WebDriver driver;

	public void loginToMailServer() {

		WebElement usernameWebElement = driver.findElement(By
				.xpath(".//*[@id='username']"));

		usernameWebElement.sendKeys(userName);

		WebElement passwordWebElement = driver.findElement(By
				.xpath(".//*[@id='password']"));

		passwordWebElement.sendKeys(password);

		WebElement submitCredsWebElement = driver.findElement(By
				.xpath(".//*[@id='SubmitCreds']"));

		submitCredsWebElement.click();

		(new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(2, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class))
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//span[@title='Tarazevich, Georgy']")));
	}

	public String getInboxUserInfo() {

		return driver.findElement(
				By.xpath("//span[@title='Tarazevich, Georgy']")).getText();
	};

	public void  openPersonaPaneLauncher() {
		
		
	}

	public String getUserInfoFromPersonaPaneLauncher() {
		return "";
	}

	public void navigateToMainMailServerPage() {
		try {
			driver.get(baseUrl);
		} catch (Exception e) {
		}
	}

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
