package com.babu.zadoqa.datadriver;


import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.babu.zadoqa.ZadoReports;
import com.babu.zadoqa.enums.LogAs;
import com.babu.zadoqa.util.CommandUtils;

public class TestCaseRunner {

    public static void executeTestCase(WebDriver driver, List<CaseStep> steps) {
	Iterator<CaseStep> stepIterator = steps.iterator();

	while (stepIterator.hasNext()) {
	    CaseStep eachStep = stepIterator.next();
	    CommandUtils util = new CommandUtils();
	    WebElement element = null;

	    if (eachStep.getOrLocator()!=null) {
		
		try
		{
		element = util.findElement(driver, eachStep.getLocateBy(),
			eachStep.getOrLocator());
		}
		catch(NoSuchElementException exception)
		{
		    throw exception;
		}
	    }

	    Object returnObj = util.executeAction(driver, element, eachStep.getAction(),
		    eachStep.getInputData());
	    
	    ZadoReports.add(eachStep.getDescription(),eachStep.getInputData(), eachStep.getExpectedResult(),Objects.toString(returnObj, ""),LogAs.PASSED, null);
	}
    }

}