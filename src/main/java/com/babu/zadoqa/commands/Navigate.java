package com.babu.zadoqa.commands;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
    
///////FTPS///Keyboard Page Up///Gobi.E//////////// 
    public static void keyboardPageUp(WebDriver driver) 
    {
    	Actions actionObject = new Actions(driver);
    	actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_UP).perform();    	
    }

///////FTPS///Keyboard Page Down///Gobi.E//////////// 
    public static void keyboardPageDown(WebDriver driver) 
    {
    	Actions actionObject = new Actions(driver);
    	actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_DOWN).perform();    	
    }
    
///////FTPS///Keyboard Page End///Gobi.E//////////// 
    public static void keyboardEnd(WebDriver driver) 
    {
    	Actions actionObject = new Actions(driver);
    	actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
    }

///////FTPS///Keyboard Tab Button///Gobi.E//////////// 
    public static void keyboardTab(WebDriver driver) 
    {
    	Actions actionObject = new Actions(driver);
    	actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).perform();
    }
    
///////FTPS///Keyboard Arrow Up///Gobi.E//////////// 
    public static void keyboardArrowUp(WebDriver driver) 
    {
    	Actions actionObject = new Actions(driver);
    	actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.ARROW_UP).perform();
    }
    
///////FTPS///Keyboard Arrow Down///Gobi.E////////////
    public static void keyboardArrowDown(WebDriver driver) 
    {
    	Actions actionObject = new Actions(driver);
    	actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.ARROW_DOWN).perform();
    }
    
///////FTPS///Keyboard Arrow Left///Gobi.E////////////
    public static void keyboardArrowLeft(WebDriver driver) 
    {
    	Actions actionObject = new Actions(driver);
    	actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.ARROW_LEFT).perform();
    }
    
///////FTPS///Keyboard Arrow Right///Gobi.E////////////
    public static void keyboardArrowRight(WebDriver driver) 
    {
    	Actions actionObject = new Actions(driver);
    	actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.ARROW_RIGHT).perform();
    }
    
///////FTPS///Page Maximize///Gobi.E//////////// 
    public static void Pagemaximize(WebDriver driver) 
    {
    	driver.findElement(By.tagName("body")).sendKeys(Keys.F11);
    }
    
///////FTPS///Delete All Cookies///Gobi.E//////////// 
  public static void deleteallCookies(WebDriver driver) 
  {
	  driver.manage().deleteAllCookies();  	
  }
  
///////FTPS///Navigate URL///Gobi.E//////////// 
  public static void navigateUrl(WebDriver driver,String inputData) 
  {
	  driver.navigate().to(inputData);  	
  }

///////FTPS///Take Screen Shots///Gobi.E//////////// 
  public static void screenShot(WebDriver driver,String inputData) 
  {
	  File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  try {	FileUtils.copyFile(screenshot, new File(inputData));	} catch (IOException e) { e.printStackTrace();
	  } 	
  }
  
///////FTPS///Alert Generation///Gobi.E//////////// 
  public static void alertGenerate(WebDriver driver,String inputData) 
  {
	  
	  JavascriptExecutor javascript = (JavascriptExecutor) driver;
	  javascript.executeScript("alert('"+inputData+"');");  
	  try {	Thread.sleep(3000);	} catch (InterruptedException e) { e.printStackTrace();	}
	
  }


}
