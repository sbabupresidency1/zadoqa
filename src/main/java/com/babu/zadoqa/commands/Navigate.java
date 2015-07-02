package com.babu.zadoqa.commands;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Navigate {

    public static void get(WebDriver driver, String url) {
	driver.get(url);
    }

    public static void close(WebDriver driver) {
   	driver.close();
    }
    
    public static void waitTime(WebDriver driver, String waitSeconds) {
	driver.manage().timeouts().implicitlyWait(Integer.parseInt(waitSeconds), TimeUnit.SECONDS);
    }

    public static void refreshPage(WebDriver driver) {
	driver.navigate().refresh();
    }

    public static void maximize(WebDriver driver) {
	driver.manage().window().maximize();
    }
    
    public static void goBack(WebDriver driver) {
	driver.navigate().back();
    }

    public static void goForward(WebDriver driver) {
	driver.navigate().forward();
    }

    public static void goTo(WebDriver driver, String url) {
	driver.navigate().to(url);
    }

    public static String alertOk(WebDriver driver, WebElement element) {
	element.click();
	Alert alert = driver.switchTo().alert();
	String alertText = alert.getText();
	alert.accept();
	return alertText;
    }

    public static String alertDismiss(WebDriver driver, WebElement element) {
	element.click();
	Alert alert = driver.switchTo().alert();
	String alertText = alert.getText();
	alert.dismiss();
	return alertText;
    }
    
    public static String promptBox(WebDriver driver, WebElement element, String inputData) {
   	element.click();
   	Alert alert = driver.switchTo().alert();
   	driver.switchTo().alert().sendKeys(inputData);
   	String alertText = alert.getText();
   	alert.accept();
   	return alertText;
    }
    
    public static void switchToFrame(WebDriver driver,String frameName) {
	try {
		driver.switchTo().frame(frameName);
	} catch (NoSuchFrameException e) {
		System.out.println("Unable to locate frame with Name " + frameName
				+ e.getStackTrace());
	} 
    }
    
    public static void switchToFrame(WebDriver driver,int frameIndex) {
	try {
		driver.switchTo().frame(frameIndex);
	} catch (NoSuchFrameException e) {
		System.out.println("Unable to locate frame with id " + frameIndex
				+ e.getStackTrace());
	} 
    }
    
    public static void switchtoDefaultFrame(WebDriver driver) {
	driver.switchTo().defaultContent();
    }
}