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
	case "switchFrameByIndex":
	    int index = new Integer(inputData);
	    Navigate.switchToFrame(driver, index);
	    break;
///////FTPS///Frame by Xpath///Gobi.E//////////// 
	case "switchFrameByXpath":
		Navigate.switchToFrame(driver, element);
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
	case "SendKeys":
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
///////FTPS///Window Handling///Gobi.E////////////    
	case "getWindowHandle":
		Manipulation.getWindow(driver, element);
		break;
///////FTPS///Auto It///Gobi.E////////////    
	case "getAutoIt":
		Manipulation.getAutoit(driver, inputData);
		break;
///////FTPS///Page Down///Gobi.E////////////  
	case "pageDown":
		Navigate.pagedown(driver);
		break;
///////FTPS///Keyboard Page Up///Gobi.E////////////
	case "KeyboardPageUp":
		Navigate.keyboardPageUp(driver);
		break;
///////FTPS///Keyboard Page Down///Gobi.E////////////
	case "KeyboardPageDown":
		Navigate.keyboardPageDown(driver);
		break;
///////FTPS///Keyboard Page End//Gobi.E////////////   
	case "KeyboardEnd":
		Navigate.keyboardEnd(driver);
		break;	
///////FTPS///Keyboard Tab Button//Gobi.E////////////   		
	case "KeyboardTab":
		Navigate.keyboardTab(driver);
		break;		
///////FTPS///Keyboard F11//Gobi.E////////////   
	case "pageMaximize":
		Navigate.Pagemaximize(driver);
		break;	
///////FTPS///Keyboard Arrow Up///Gobi.E////////////		
	case "KeyboardArrowUp":
		Navigate.keyboardArrowUp(driver);
		break;	
///////FTPS///Keyboard Arrow Down///Gobi.E////////////		
	case "KeyboardArrowDown":
		Navigate.keyboardArrowDown(driver);
		break;	
///////FTPS///Keyboard Arrow Left///Gobi.E////////////
	case "KeyboardArrowLeft":
		Navigate.keyboardArrowLeft(driver);
		break;	
///////FTPS///Keyboard Arrow Right///Gobi.E////////////		
	case "KeyboardArrowRight":
		Navigate.keyboardArrowRight(driver);
		break;			
///////FTPS///Drag And Drop//Gobi.E////////////   
	case "Drag":
		Manipulation.dragElement(driver, element);
		break;
	case "Drop":
		Manipulation.dropElement(driver, element);
		break;
///////FTPS///Wait//Gobi.E////////////   
	case "wait":
		Manipulation.wait(driver, inputData);
		break;
///////FTPS///verify Element present//Gobi.E////////////   
	case "verifyElementisPresent":
		Manipulation.elementisPresent(driver, element);
		break;
///////FTPS///verify Element is Visible//Gobi.E////////////  		
	case "verifyElementisVisible":
		Manipulation.elementisVisible(driver, element);
		break;
///////FTPS///verify Element is Enable//Gobi.E////////////  
	case "verifyElementisEnable":
		Manipulation.elementisEnable(driver, element);
		break;
///////FTPS///verify Text is present//Gobi.E////////////  
	case "verifyTextisPresent":
		Manipulation.testIsPresent(driver, inputData);
		break;			
///////FTPS///Wait for element//Gobi.E////////////   
	case "waitForElementPresent":
		Manipulation.waitForElement(driver, element);
		break;
///////FTPS///Wait for visibility element//Gobi.E//////////// 		
	case "visibilityOfElement":
		Manipulation.VisibilityElement(driver, element);
		break;
///////FTPS///delete All Cookies//Gobi.E////////////   
	case "deleteAllCookies":
		Navigate.deleteallCookies(driver);
		break;	
///////FTPS///Navigate to URL//Gobi.E///////////   
	case "navigateToURL":
		Navigate.navigateUrl(driver,inputData);
		break;	
///////FTPS///Taking Screen Shots//Gobi.E///////////   
	case "takeScreeShot":
		Navigate.screenShot(driver,inputData);
		break;	
///////FTPS///Alert Generation//Gobi.E///////////   
	case "generateAlert":
		Navigate.alertGenerate(driver,inputData);
		break;	
		
		
///////FTPS// Size //Gobi.E///////////   
		case "getSize":
			returnObj = ElementActions.getText(element);
			break;		
		
		
		
		
		
		
		
	}

	return returnObj;
    }

}
