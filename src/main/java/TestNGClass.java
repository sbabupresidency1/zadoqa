import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.babu.zadoqa.ZadoReports;
import com.babu.zadoqa.config.ChromeBrowser;
import com.babu.zadoqa.config.FirefoxBrowser;
import com.babu.zadoqa.datadriver.CaseStep;
import com.babu.zadoqa.datadriver.TestCaseRunner;
import com.babu.zadoqa.enums.LogAs;
import com.babu.zadoqa.listeners.ConfigurationListener;
import com.babu.zadoqa.listeners.MethodListener;
import com.babu.zadoqa.listeners.ZadoReportsListener;
import com.babu.zadoqa.reports.CaptureScreen;
import com.babu.zadoqa.reports.CaptureScreen.ScreenshotOf;
import com.babu.zadoqa.util.ExcelUtils;
import com.babu.zadoqa.utils.TestParameters;

@Listeners({ ConfigurationListener.class, ZadoReportsListener.class, 
	MethodListener.class })
public class TestNGClass{
    private WebDriver driver;
    
    @BeforeTest
    @DataProvider(name = "data")
    public static Iterator<Object[]> testCaseProvider() {
	List<Object[]> data = new ArrayList<Object[]>();
	ExcelUtils utils = new ExcelUtils();
	Collection<File> testCaseList = utils.readTestCaseFiles("C:\\Softwares\\Babu\\testcase");
	Iterator<File> testCaseItr = testCaseList.iterator();
	
	while(testCaseItr.hasNext())
	{
	    TestParameters params = new TestParameters();
	    File tcFileName = testCaseItr.next();
	    params.setBrowserName("firefox");
	    params.setTestCaseFileName(tcFileName);
	    params.setTestCaseName(FilenameUtils.getBaseName(tcFileName.getName()));
	    params.setModuleName(tcFileName.getParentFile().getName());
	    params.setOrSheetFileName(new File("C:\\Softwares\\Babu\\testcase\\ORSheet.xlsx"));
	    data.add(new Object[]{params});
	}
	
	return data.iterator();
    }
    
    @Test(dataProvider = "data",enabled=true)
    public void launchapp(TestParameters params) {
	
	ExcelUtils utils = new ExcelUtils();
	try {
	    	if(params.getBrowserName().equals("chrome")){
	    	    driver = new ChromeBrowser().getDriver();
	    	}
	    	else if(params.getBrowserName().equals("firefox")){
	    	    driver = new FirefoxBrowser().getDriver();
	    	}
	    	
		ZadoReports.setWebDriver(driver);
		
		try {
		    List<CaseStep> steps = utils.readTestCase(params.getTestCaseFileName(), params.getOrSheetFileName());
		    TestCaseRunner.executeTestCase(driver, steps);
		} catch (NoSuchElementException e) {
		    ZadoReports.add("Failed to find Element", LogAs.FAILED, new CaptureScreen(
	                    ScreenshotOf.BROWSER_PAGE));
		    driver.close();
		    driver.quit();
		    throw e;
		}
		
		driver.close();
		driver.quit();
		
	} catch (InvalidFormatException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
}