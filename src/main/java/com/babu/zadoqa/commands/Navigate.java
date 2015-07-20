package com.babu.zadoqa.commands;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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
    
    public static void switchtoDefaultFrame(WebDriver driver) 
    {
    	driver.switchTo().defaultContent();
    }
    
///////FTPS///Frame by Xpath///Gobi.E//////////// 
    public static void switchToFrame(WebDriver driver,WebElement element) {
    	try {
    		driver.switchTo().frame(element);
    	} catch (NoSuchFrameException e) {
    		System.out.println("Unable to locate frame with Xpath " + element
    				+ e.getStackTrace());
    	} 
    }
    
///////FTPS///Page Down///Gobi.E//////////// 
    public static void pagedown(WebDriver driver) 
    {
    	JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("window.scrollBy(0,250)", "");
    }

///////FTPS///Page End///Gobi.E//////////// 
    public static void Pageend(WebDriver driver) 
    {
    	Actions actionObject = new Actions(driver);
    	actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
    }

///////FTPS///Page Maximize///Gobi.E//////////// 
    public static void Pagemaximize(WebDriver driver) 
    {
    	driver.findElement(By.tagName("body")).sendKeys(Keys.F11);
    }

}
