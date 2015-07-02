package com.babu.zadoqa.commands;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

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

}
