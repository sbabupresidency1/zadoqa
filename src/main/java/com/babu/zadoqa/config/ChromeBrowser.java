package com.babu.zadoqa.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Class to configure and get Details for Chrome Browser
 * 
 * @author david.sathiyaraja
 * 
 */
public class ChromeBrowser implements Browser {

	private WebDriver chromeDriver;

	public ChromeBrowser() {
		System.setProperty(ApplicationConstants.CHROME_DRIVER_NAME,
				ApplicationConstants.CHROME_DRIVER_PATH);
		chromeDriver = new ChromeDriver();
	}

	public String getBrowserName() {
		return ((RemoteWebDriver) chromeDriver).getCapabilities()
				.getBrowserName();
	}

	public String getVersion() {
		return ((RemoteWebDriver) chromeDriver).getCapabilities().getVersion();
	}

	public WebDriver getDriver() {
		return chromeDriver;
	}

}
