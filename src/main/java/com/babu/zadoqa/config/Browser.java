package com.babu.zadoqa.config;

import org.openqa.selenium.WebDriver;

/**
 * Main interface for the Browser Configuration
 * 
 * @author david.sathiyaraja
 * 
 */
public interface Browser {

	public WebDriver getDriver();

	public String getBrowserName();

	public String getVersion();

}
