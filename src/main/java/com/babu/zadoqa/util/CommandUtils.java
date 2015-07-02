package com.babu.zadoqa.util;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.babu.zadoqa.commands.ElementActions;
import com.babu.zadoqa.commands.LocatorBy;
import com.babu.zadoqa.commands.Manipulation;
import com.babu.zadoqa.commands.Navigate;

public class CommandUtils {

    public WebElement element;

    public WebElement findElement(WebDriver driver, String locateBy,
	    String orLocator) {

	switch (locateBy) {
	case "ByID":
	    element = LocatorBy.locateById(driver, orLocator);
	    break;
	case "ByName":
	    element = LocatorBy.locateByName(driver, orLocator);
	    break;
	case "ByOrName":
	    element = LocatorBy.locateByOrName(driver, orLocator);
	    break;
	case "ByXPath":
	    element = LocatorBy.locateByXPath(driver, orLocator);
	    break;
	case "ByLinkText":
	    element = LocatorBy.locateByLinkText(driver, orLocator);
	    break;
	case "ByTagName":
	    element = LocatorBy.locateByTagName(driver, orLocator);
	    break;
	case "ByClassName":
	    element = LocatorBy.locateByClassName(driver, orLocator);
	    break;
	case "ByCssSelector":
	    element = LocatorBy.locateByCssSelector(driver, orLocator);
	    break;
	case "ByPartialLinkText":
	    element = LocatorBy.locateByPartialLinkText(driver, orLocator);
	    break;
	default:
	    break;
	}
	return element;
    }

    public Object executeAction(WebDriver driver, WebElement element,
	    String action, String inputData) {

	Object returnObj = null;
	switch (action) {

	case "Click":
	    Manipulation.click(element);
	    break;
	case "DoubleClick":
	    Manipulation.doubleClick(driver, element);
	    break;
	case "mouseOver":
	    Manipulation.mouseOver(driver, element);
	    break;
	case "mouseOverAndClick":
	    Manipulation.mouseOverAndClick(driver, element);
	    break;
	case "selectCheckBox":
	    Manipulation.selectCheckBox(element);
	    break;
	case "DeSelectCheckBox":
	    Manipulation.deSelectCheckBox(element);
	    break;
	case "selectByIndex":
	    Manipulation.selectByIndex(element, inputData);
	    break;
	case "selectByValue":
	    Manipulation.selectByValue(element, inputData);
	    break;
	case "deselectByIndex":
	    Manipulation.deselectByIndex(element, inputData);
	    break;
	case "deselectByValue":
	    Manipulation.deselectByValue(element, inputData);
	    break;
	case "ClickAt":
	    String[] coordinates = StringUtils.split(inputData, ",");
	    int x = new Integer(coordinates[0]);
	    int y = new Integer(coordinates[1]);
	    Manipulation.clickAt(driver, element, x, y);
	    break;
	case "Clear":
	    Manipulation.clear(element);
	    break;
	case "Submit":
	    Manipulation.submit(element);
	    break;
	case "Refresh":
	    Navigate.refreshPage(driver);
	    break;
	case "switchFrameByName":
	    Navigate.switchToFrame(driver, inputData);
	    break;
	case "switchFrameByXpath":
	Navigate.switchToFrameXpath(driver, inputData);
    break;
	case "switchFrameByIndex":
	    int index = new Integer(inputData);
	    Navigate.switchToFrame(driver, index);
	    break;
	case "switchToDefaultFrame":
	    Navigate.switchtoDefaultFrame(driver);
	    break;
	case "Back":
	    Navigate.goBack(driver);
	    break;
	case "Forward":
	    Navigate.goForward(driver);
	    break;
	case "alertOk":
	    returnObj = Navigate.alertOk(driver, element);
	    break;
	case "alertDismiss":
	    returnObj = Navigate.alertDismiss(driver, element);
	    break;
	case "promptBox":
	    returnObj = Navigate.promptBox(driver, element, inputData);
	    break;
	case "Type":
	    Manipulation.sendKeys(element, inputData);
	    break;
	case "getText":
	    returnObj = ElementActions.getText(element);
	    break;
	case "getAttribute":
	    returnObj = ElementActions.getAttribute(element, inputData);
	    break;
	case "getUrl":
	    Navigate.get(driver, inputData);
	    break;
	case "waitTime":
	    Navigate.waitTime(driver, inputData);
	    break;
	case "waitUntilElementPresent":
	    Navigate.waitUntilElementPresent(element);
	    break;
	case "Maximize":
	    Navigate.maximize(driver);
	    break;
	case "Close":
	    Navigate.close(driver);
	    break;
	case "getCurrentURL":
	    returnObj = Manipulation.getCurrentURL(driver);
	    break;
	default:
	    break;

	}

	return returnObj;
    }

}
