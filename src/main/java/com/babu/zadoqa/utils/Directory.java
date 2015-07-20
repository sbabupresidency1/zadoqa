package com.babu.zadoqa.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;

import com.babu.zadoqa.ZadoReports;
import com.babu.zadoqa.enums.RecordingFor;
import com.babu.zadoqa.enums.ReportLabels;
import com.babu.zadoqa.exceptions.ZadoReporterException;
import com.babu.zadoqa.listeners.ZadoReportsListener;
import com.babu.zadoqa.writers.HTMLDesignFilesWriter;

public class Directory {
    
    static Logger log = Logger.getLogger(Directory.class.getName());
    public static final String CURRENTDir = System.getProperty("user.dir");
    public static final String SEP = System.getProperty("file.separator");
    public static String REPORTSDIRName = "Zado Reports";
    public static String REPORTSDir = CURRENTDir + SEP + REPORTSDIRName;
    public static String RESULTSDir = REPORTSDir + SEP + "Results";
    public static String HTMLDESIGNDIRName = "HTML_Design_Files";
    public static String HTMLDESIGNDir = REPORTSDir + SEP + HTMLDESIGNDIRName;
    public static String CSSDIRName = "CSS";
    public static String CSSDir = HTMLDESIGNDir + SEP + CSSDIRName;
    public static String IMGDIRName = "IMG";
    public static String IMGDir = HTMLDESIGNDir + SEP + IMGDIRName;
    public static String JSDIRName = "JS";
    public static String JSDir = HTMLDESIGNDir + SEP + JSDIRName;
    public static String RUNName = "Run_";
    public static String RUNDir = RESULTSDir + SEP + RUNName;
    public static String SETTINGSFile = RESULTSDir + SEP
	    + "Settings.properties";
    public static final char JS_SETTINGS_DELIM = ';';
    public static final String REPO_DELIM = "##@##@##";
    public static final char JS_FILE_DELIM = ',';
    public static final String MENU_LINK_NAME = "Run ";
    public static final String SCREENSHOT_TYPE = "PNG";
    public static final String SCREENSHOT_EXTENSION = ".PNG";
    private static String logo = null;
    public static String SCREENSHOT_DIRName = "img";
    public static boolean generateConfigReports = true;
    public static boolean generateExcelReports = false;
    public static boolean takeScreenshot = false;
    public static boolean continueExecutionAfterStepFailed = false;
    public static boolean recordExecutionAvailable = false;
    public static boolean recordSuiteExecution = false;
    public static boolean recordTestMethodExecution = false;
    public static final String MAIN_RECORD_FILE_NAME = "ATU_CompleteSuiteRecording";

    public static void init() throws ZadoReporterException {
	if (getCustomProperties() != null) {
	    log.info("Reading from  custom properties");
	    Properties localProperties = new Properties();
	    try {
		localProperties.load(new FileReader(getCustomProperties()));
		String reportsDir = localProperties.getProperty("zado.reports.dir")
			.trim();
		String headerText = localProperties.getProperty(
			"zado.proj.header.text").trim();
		logo = localProperties.getProperty("zado.proj.header.logo")
			.trim();
		String projectDescription = localProperties.getProperty(
			"zado.proj.description").trim();
		String takeScreenShot = localProperties.getProperty(
			"zado.reports.takescreenshot").trim();
		String configReports = localProperties.getProperty(
			"zado.reports.configurationreports").trim();
		String excelReport = localProperties.getProperty("zado.reports.excel")
			.trim();
		String contExectution = localProperties.getProperty(
			"zado.reports.continueExecutionAfterStepFailed").trim();
		String recordExecution = localProperties.getProperty(
			"zado.reports.recordExecution").trim();
		try {
		    if ((headerText != null) && (headerText.length() > 0)) {
			ReportLabels.HEADER_TEXT.setLabel(headerText);
		    }
		    if ((takeScreenShot != null) && (takeScreenShot.length() > 0)) {
			try {
			    takeScreenshot = Boolean.parseBoolean(takeScreenShot);
			} catch (Exception localException1) {
			}
		    }
		    if ((configReports != null) && (configReports.length() > 0)) {
			try {
			    generateConfigReports = Boolean.parseBoolean(configReports);
			} catch (Exception localException2) {
			}
		    }
		    if ((excelReport != null) && (excelReport.length() > 0)) {
			try {
			    generateExcelReports = Boolean.parseBoolean(excelReport);
			} catch (Exception localException3) {
			}
		    }
		    if ((contExectution != null) && (contExectution.length() > 0)) {
			try {
			    continueExecutionAfterStepFailed = Boolean
				    .parseBoolean(contExectution);
			} catch (Exception localException4) {
			}
		    }
		    if ((recordExecution != null) && (recordExecution.length() > 0)) {
			try {
			    RecordingFor localRecordingFor = RecordingFor
				    .valueOf(recordExecution.toUpperCase());
			    if (localRecordingFor == RecordingFor.SUITE) {
				recordSuiteExecution = true;
			    } else if (localRecordingFor == RecordingFor.TESTMETHOD) {
				recordTestMethodExecution = true;
			    }
			} catch (Throwable localThrowable) {
			}
		    }
		    if ((projectDescription != null) && (projectDescription.length() > 0)) {
			ZadoReports.indexPageDescription = projectDescription;
		    }
		    if ((reportsDir != null) && (reportsDir.length() > 0)) {
			REPORTSDir = reportsDir;
			REPORTSDIRName = new File(REPORTSDir).getName();
			RESULTSDir = REPORTSDir + SEP + "Results";
			HTMLDESIGNDIRName = "HTML_Design_Files";
			HTMLDESIGNDir = REPORTSDir + SEP + HTMLDESIGNDIRName;
			CSSDIRName = "CSS";
			CSSDir = HTMLDESIGNDir + SEP + CSSDIRName;
			IMGDIRName = "IMG";
			IMGDir = HTMLDESIGNDir + SEP + IMGDIRName;
			JSDIRName = "JS";
			JSDir = HTMLDESIGNDir + SEP + JSDIRName;
			RUNName = "Run_";
			RUNDir = RESULTSDir + SEP + RUNName;
			SETTINGSFile = RESULTSDir + SEP + "Settings.properties";
		    }
		} catch (Exception localException5) {
		    throw new ZadoReporterException(localException5.toString());
		}
	    } catch (FileNotFoundException localFileNotFoundException) {
		throw new ZadoReporterException(
			"The Path for the Custom Properties file could not be found");
	    } catch (IOException localIOException) {
		throw new ZadoReporterException(
			"Problem Occured while reading the Zado Reporter Config File");
	    }
	}
    }

    public static void mkDirs(String paramString) {
	File localFile = new File(paramString);
	if (!localFile.exists()) {
	    localFile.mkdirs();
	}
    }

    public static boolean exists(String paramString) {
	File localFile = new File(paramString);
	return localFile.exists();
    }

    public static void verifyRequiredFiles() throws ZadoReporterException {
	init();
	log.info("Setting Reports Dir---"+REPORTSDir);
	log.info("Setting Results Dir---"+RESULTSDir);
	mkDirs(REPORTSDir);
	if (!exists(RESULTSDir)) {
	    mkDirs(RESULTSDir);
	    SettingsFile.initSettingsFile();
	}
	if (!exists(HTMLDESIGNDir)) {
	    mkDirs(HTMLDESIGNDir);
	    mkDirs(CSSDir);
	    mkDirs(JSDir);
	    mkDirs(IMGDir);
	    HTMLDesignFilesWriter.writeCSS();
	    HTMLDesignFilesWriter.writeIMG();
	    HTMLDesignFilesWriter.writeJS();
	}
	if ((logo != null) && (logo.length() > 0)) {
	    String str = new File(logo).getName();
	    if (!new File(IMGDir + SEP + str).exists()) {
		copyImage(logo);
	    }
	    ReportLabels.PROJ_LOGO.setLabel(str);
	}
    }

    private static void copyImage(String paramString)
	    throws ZadoReporterException {
	File localFile = new File(paramString);
	if (!localFile.exists()) {
	    return;
	}
	FileImageInputStream localFileImageInputStream = null;
	FileImageOutputStream localFileImageOutputStream = null;
	try {
	    localFileImageInputStream = new FileImageInputStream(new File(
		    paramString));
	    localFileImageOutputStream = new FileImageOutputStream(new File(
		    IMGDir + SEP + localFile.getName()));
	    int i = 0;
	    while ((i = localFileImageInputStream.read()) >= 0) {
		localFileImageOutputStream.write(i);
	    }
	    localFileImageOutputStream.close();
	    return;
	} catch (Exception localException2) {
	} finally {
	    try {
		localFileImageInputStream.close();
		localFileImageOutputStream.close();
		localFile = null;
	    } catch (Exception localException4) {
		localFileImageInputStream = null;
		localFileImageOutputStream = null;
		localFile = null;
	    }
	}
    }

    private static String getCustomProperties() {
	return System.getProperty("zado.reporter.config");
    }

    public static String createTestRunDateTime() {
	Calendar cal = Calendar.getInstance();
	return DateFormatUtils.format(cal, "dd-MM-yy, hh.mm aa");
    }
    
    public static String getTestRunDateTime(int run) {
	try {
	    String testRun = SettingsFile.get("testRunDT");
	    String timeArray [] = testRun.split(";");
	    return timeArray[run-1];
	} catch (ZadoReporterException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return " ";
    }
}
