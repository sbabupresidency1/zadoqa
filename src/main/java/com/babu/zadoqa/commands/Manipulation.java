package com.babu.zadoqa.commands;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Manipulation {

    static Logger log = Logger.getLogger(Manipulation.class.getName());

    public static void click(WebElement webElement) {
	webElement.click();
    }

    public static void doubleClick(WebDriver driver, WebElement webElement) {
	try {
	    Actions action = new Actions(driver).doubleClick(webElement);
	    action.build().perform();

	} catch (StaleElementReferenceException e) {
	    log.info("Element is not attached to the page document "
		    + e.getStackTrace());
	} catch (NoSuchElementException e) {
	    log.info("Element " + webElement + " was not found in DOM "
		    + e.getStackTrace());
	} catch (Exception e) {
	    log.info("Element " + webElement + " was not clickable "
		    + e.getStackTrace());
	}
    }

    public static void clickAt(WebDriver driver, WebElement webElement, int x,
	    int y) {
	try {
	    Actions builder = new Actions(driver);
	    builder.moveToElement(webElement, x, y).click().build().perform();
	} catch (StaleElementReferenceException e) {
	    log.info("Element is not attached to the page document "
		    + e.getStackTrace());
	} catch (NoSuchElementException e) {
	    log.info("Element " + webElement + " was not found in DOM "
		    + e.getStackTrace());
	} catch (Exception e) {
	    log.info("Element " + webElement + " was not clickable "
		    + e.getStackTrace());
	}
    }

    public static void clear(WebElement webElement) {
	webElement.clear();
    }

    public static void sendKeys(WebElement webElement,
	    CharSequence... keysToSend) {
	webElement.sendKeys(keysToSend);
    }

    public static void submit(WebElement webElement) {
	webElement.submit();
    }

    public static void keyDown(Actions actions, Keys keys) {
	actions.keyDown(keys);
    }

    public static void keyUp(Actions actions, Keys keys) {
	actions.keyUp(keys);
    }

    public static String getCurrentURL(WebDriver driver) {
	return driver.getCurrentUrl();
    }

    public static String getTitle(WebDriver driver) {
	return driver.getTitle();
    }

    public static void mouseOver(WebDriver driver, WebElement webElement) {
	try {
	    Actions builder = new Actions(driver);
	    builder.moveToElement(webElement).build().perform();
	} catch (StaleElementReferenceException e) {
	    log.info("Element is not attached to the page document "
		    + e.getStackTrace());
	} catch (NoSuchElementException e) {
	    log.info("Element " + webElement + " was not found in DOM "
		    + e.getStackTrace());
	} catch (Exception e) {
	    log.info("Element " + webElement + " was not mouseOver "
		    + e.getStackTrace());
	}
    }

    public static void mouseOverAndClick(WebDriver driver, WebElement webElement) {
	try {
	    Actions builder = new Actions(driver);
	    builder.moveToElement(webElement).click().build().perform();
	} catch (StaleElementReferenceException e) {
	    log.info("Element is not attached to the page document "
		    + e.getStackTrace());
	} catch (NoSuchElementException e) {
	    log.info("Element " + webElement + " was not found in DOM "
		    + e.getStackTrace());
	} catch (Exception e) {
	    log.info("Element " + webElement + " was not mouseOver "
		    + e.getStackTrace());
	}
    }
    
    public static void selectCheckBox(WebElement element) {
	try {
	    if (element.isSelected()) {
		log.info("Checkbox: " + element + "is already selected");
	    } else {
		element.click();
	    }
	} catch (Exception e) {
	    log.info("Unable to select the checkbox: " + element);
	}

    }

    public static void deSelectCheckBox(WebElement element) {
	try {
	    if (element.isSelected()) {
		element.click();
	    } else {
		log.info("Checkbox: " + element + "is already deselected");
	    }
	} catch (Exception e) {
	    log.info("Unable to deselect checkbox: " + element);
	}
    }

    public static void selectByIndex(WebElement element, String inputData) {
	try {
	    Integer index = new Integer(inputData);
	    Select selectBox = new Select(element);
	    selectBox.selectByIndex(index);
	} catch (Exception e) {
	    log.info("Unable to select selectbox: " + element);
	}
    }

    public static void selectByValue(WebElement element, String inputData) {
    	try {
    		Select selectBox = new Select(element);
    		selectBox.selectByValue(inputData);
    	} catch (Exception e) {
    		log.info("Unable to select selectbox: " + element);
    	}
    }

    public static void deselectByIndex(WebElement element, String inputData) {
    	try {
    		Integer index = new Integer(inputData);
    		Select selectBox = new Select(element);
    		selectBox.deselectByIndex(index);
    	} catch (Exception e) {
    		log.info("Unable to select selectbox: " + element);
    	}
    }

    public static void deselectByValue(WebElement element, String inputData) {
    	try {
    		Select selectBox = new Select(element);
    		selectBox.deselectByValue(inputData);
    	} catch (Exception e) {
    		log.info("Unable to select selectbox: " + element);
    	}
    }

///////FTPS///Window Handling///Gobi.E//////////// 
    public static void getWindow(WebDriver driver, WebElement webElement) 
    {  
    	String one = driver.getWindowHandle();
    	try{Thread.sleep(3000);}catch(InterruptedException e){e.printStackTrace();}
    	click(webElement);
    	try{Thread.sleep(3000);}catch(InterruptedException e){e.printStackTrace();}
    	ArrayList<String> st=new ArrayList<String>(driver.getWindowHandles());
    	st.remove(one);
    	driver.switchTo().window(st.get(0));
    }
    
///////FTPS///Auto It///Gobi.E//////////// 
    public static void getAutoit(WebDriver driver, String inputData) 
    {  
    	try 
    	{
    		// String one = webElement.toString();
    		Runtime.getRuntime().exec(inputData);		 
    	} 
    	catch (IOException e1)
    	{
    		e1.printStackTrace();
    	}
    }
    
///////FTPS///Wait///Gobi.E//////////// 
    public static void wait(WebDriver driver,String inputData) 
    {
    	try {
    		int time = Integer.parseInt(inputData);
    		int seconds = time*1000;
    		Thread.sleep(seconds);
    	} catch (InterruptedException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
    
///////FTPS///Drag And Drop///Gobi.E////////////
    public static WebElement fromElement;
    public static void dragElement(WebDriver driver, WebElement webElement) 
    {
    	fromElement=webElement;
    }

    public static void dropElement(WebDriver driver, WebElement webElement) 
    {
    	Actions action = new Actions(driver);
    	Action dragDrop = action.dragAndDrop(fromElement, webElement).build();
    	dragDrop.perform(); 
    }
    
//////////FTPS///Verify Element is Present///Gobi.E////////
    public static void elementisPresent(WebDriver driver, WebElement webElement)
    {
    	if(webElement!= null){
    		System.out.println("Element is Present");
    	}else{
    		System.out.println("Element is Absent");
    	}
    }
//////////FTPS///Verify Element is Visible///Gobi.E////////    
    public static void elementisVisible(WebDriver driver, WebElement webElement)
    {
    	if(webElement.isDisplayed())
    	{
    		System.out.println("Element is Visible");
    	}
    	else
    	{
    		System.out.println("Element is InVisible");
    	}
    }
//////////FTPS///Verify Element is Enable///Gobi.E//////// 
    public static void elementisEnable(WebDriver driver, WebElement webElement)
    {
    	if(webElement.isEnabled())
    	{
    		System.out.println("Element is Enable");
    	}
    	else
    	{
    		System.out.println("Element is Disabled");
    	}
    }
//////////FTPS///Verify Text is Present///Gobi.E////////
    public static void testIsPresent(WebDriver driver, String inputData)
    {
    	if(driver.getPageSource().contains(inputData))
    	{
    		System.out.println("Text is present");
    	}
    	else
    	{
    		System.out.println("Text is absent");
    	}
    }
    
    
//////////FTPS///wait for  Element Present///Gobi.E////////
    public static void waitForElement(WebDriver driver, WebElement webElement)
    {
    	WebDriverWait wait = new WebDriverWait(driver, 15);
    	//wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@id='timeLeft']"), "Time left: 7 seconds"));
    	wait.until(ExpectedConditions.textToBePresentInElement(webElement, "Time left: 7 seconds"));
    	wait.until(ExpectedConditions.visibilityOf(webElement));
    	 
    }
//////////FTPS///wait for visibility Element///Gobi.E////////    
    public static void VisibilityElement(WebDriver driver, WebElement webElement)
    {
    	WebDriverWait wait = new WebDriverWait(driver, 15);
    	wait.until(ExpectedConditions.visibilityOf(webElement));
    	
    }

    	 

   

}
