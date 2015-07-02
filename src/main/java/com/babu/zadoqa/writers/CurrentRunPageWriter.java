package com.babu.zadoqa.writers;


import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.babu.zadoqa.ZadoReports;
import com.babu.zadoqa.enums.ReportLabels;
import com.babu.zadoqa.utils.Attributes;
import com.babu.zadoqa.utils.Directory;
import com.babu.zadoqa.utils.TestParameters;
import com.babu.zadoqa.utils.Utils;

public class CurrentRunPageWriter
  extends ReportsPage
{
  public static void menuLink(PrintWriter paramPrintWriter, int paramInt)
  {
    paramPrintWriter.println("\n            <tr id=\"container\">\n                <td id=\"menu\">\n                    <ul> \n");
    paramPrintWriter.println(" <li class=\"menuStyle\"><a href=\"../../index.html\" >Index</a></li><li style=\"padding-top: 4px;\"><a href=\"../ConsolidatedPage.html\" >Consolidated Page</a></li>\n");
    if (paramInt == 1) {
      paramPrintWriter.println("\n <li class=\"menuStyle\"><a href=\"" + Directory.RUNName + paramInt + Directory.SEP + "CurrentRun.html\" >" + "Run " + paramInt + " </a></li>\n");
    } else {
      for (int i = 1; i <= paramInt; i++)
      {
        if (i == paramInt)
        {
          paramPrintWriter.println("\n <li style=\"padding-top: 4px;padding-bottom: 4px;\"><a href=\"" + Directory.RUNName + i + Directory.SEP + "CurrentRun.html\" >" + "Run " + i + " </a></li>\n");
          break;
        }
        paramPrintWriter.println("\n <li class=\"menuStyle\"><a href=\"" + Directory.RUNName + i + Directory.SEP + "CurrentRun.html\" >" + "Run " + i + " </a></li>\n");
      }
    }
    paramPrintWriter.println("\n                    </ul>\n                </td>\n\n");
  }
  
  public static String getExecutionTime(ITestResult testResult)
  {
    long l = testResult.getEndMillis() - testResult.getStartMillis();
    if (l > 1000L)
    {
      l /= 1000L;
      return l + " Sec";
    }
    return l + " Milli Sec";
  }
  
  public static String getExecutionTime(long paramLong1, long paramLong2)
  {
    long l = paramLong2 - paramLong1;
    if (l > 1000L)
    {
      l /= 1000L;
      return l + " Sec";
    }
    return l + " Milli Sec";
  }
  
  public static void content(PrintWriter printWriter, List<ITestResult> passedList, List<ITestResult> failedList, List<ITestResult> skippedList, List<ITestResult> passedConfig, List<ITestResult> failedConfig, List<ITestResult> skippedConfig, int runCount, long exectution, long executionTime)
  {
    int i = passedList.size() + failedList.size() + skippedList.size();
    printWriter.println("<td id=\"content\">\n                    <div class=\"info\">\n                        The following pie chart demonstrates the percentage of Passed, Failed and Skipped Test Cases<br/>\n                        Time Taken for Executing below Test Cases: <b>" + getExecutionTime(exectution, executionTime) + "</b> <br/>\n" + "                        Current Run Number: <b>Run " + runCount + "</b>\n" + "                    </div>\n" + "<div class=\"info\">" + "<br/>" + "<b>Run Description</b><br/><br/>" + ZadoReports.currentRunDescription + "</div>" + "                    <div>\n" + "                        <div class=\"chartStyle summary\" style=\"width: 32%;background-color: #3B9C9C;\">\n" + "                            <b>Summary</b><br/><br/>\n" + "                            <table>\n" + "                            <tr>\n" + "                                <td>Execution Date</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + Utils.getCurrentTime() + "</td>\n" + "                            </tr>\n" + "                            <tr>\n" + "                                <td>Total Test Cases</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + i + "</td>\n" + "                            </tr>\n" + "                            <tr>\n" + "                                <td>Passed</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + passedList.size() + "</td>\n" + "                            </tr>\n" + "                            \n" + "                            <tr>\n" + "                                <td>Failed</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + failedList.size() + "</td>\n" + "                            </tr>\n" + "\n" + "                            <tr>\n" + "                                <td>Skipped</td>\n" + "                                <td>&nbsp;&nbsp;:&nbsp;&nbsp;</td>\n" + "                                <td>" + skippedList.size() + "</td>\n" + "                            </tr>\n" + "                        </table> \n" + "                        </div>" + "                        <div class=\"chartStyle\" style=\"text-align: left;margin-left: 30px;float: left;width: 60%;\">                        \n" + "                            <div id=\"chart\" style=\"height:300px;color:black;\"></div>\n" + "                        </div>\n" + "                    </div>\n" + "                    <div>\n");
    if (Directory.recordSuiteExecution) {
      printWriter.println("<p id=\"showmenu\">Click Me to Show/Hide the Execution Video</p><div id=\"video\" class=\"video\"><object classid=\"clsid:9BE31822-FDAD-461B-AD51-BE1D1C159921\" codebase=\"http://downloads.videolan.org/pub/videolan/vlc/latest/win32/axvlc.cab\" width=\"400\" height=\"300\" id=\"vlc\" events=\"True\">  <param name=\"Src\" value=\"Recording" + Directory.SEP + "ATU_CompleteSuiteRecording" + ".mov\"></param>" + "  <param name=\"ShowDisplay\" value=\"True\" ></param>" + "    <param name=\"AutoLoop\" value=\"no\"></param>" + "    <param name=\"AutoPlay\" value=\"no\"></param>" + "    <embed type=\"application/x-google-vlc-plugin\" name=\"vlcfirefox\" autoplay=\"no\" loop=\"no\" width=\"99%\"  height=\"100%\" target=\"" + "Recording" + Directory.SEP + "ATU_CompleteSuiteRecording" + ".mov\"></embed>" + " </object>" + "</div>");
    } else {
      printWriter.println("<p id=\"showmenu\">No Video Recording Available</p>");
    }
    printWriter.println("<div style=\"float:left;  color: #585858; font-size: 14px;\">\t<select id=\"tcFilter\" class=\"filter\">\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"all\">All Methods</option>\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"tests\">Test Methods</option>\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"pass\">Passed Test Cases</option>\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"fail\">Failed Test Cases</option>\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"skip\">Skipped Test Cases</option>\n\t\t\t\t\t\t<option class=\"filterOption\" value=\"config\">Configuration Methods</option>\n\t\t\t\t\t</select>\tFilter Methods &nbsp;</div>");
    printWriter.println("<div style=\"float:left;  color: #585858; font-size: 14px;\">\t<select id=\"suiteFilter\" class=\"filter\">\n<option class=\"filterOption\" value=\"all\">All Suites</option>\n");
    Iterator<String> localIterator = Attributes.getSuiteNameMapperMap().keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = localIterator.next();
      printWriter.println("<option class=\"filterOption\" value=\"" + Attributes.getSuiteNameMapperMap().get(str) + "\">" + str + "</option>\n");
    }
    printWriter.println("</select>Filter Suites&nbsp;&nbsp;</div>");
    printWriter.println("                        <table id=\"tableStyle\" class=\"chartStyle\" style=\"height:50px; float: left\">\n                            <tr>\n                                <th>Suite Name</th>\n                                <th>Module Name</th>\n                                                               <th>Test Case Name</th>\n<th>Iteration</th>                                <th>Time</th>\n                                <th style=\"width: 7%\">Status</th>\n                            </tr>\n");
    writePassedData(printWriter, passedList, runCount);
    writeFailedData(printWriter, failedList, runCount);
    writeSkippedData(printWriter, skippedList, runCount);
    writePassedData(printWriter, passedConfig, runCount);
    writeFailedData(printWriter, failedConfig, runCount);
    writeSkippedData(printWriter, skippedConfig, runCount);
    printWriter.print("                        </table>\n                    </div>\n                </td>\n            </tr>");
  }
  
  private static void writePassedData(PrintWriter paramPrintWriter, List<ITestResult> testResultList, int paramInt)
  {
    String str = "pass";
    Iterator<ITestResult> localIterator = testResultList.iterator();
    while (localIterator.hasNext())
    {
      ITestResult localITestResult = localIterator.next();
      str = "pass " + getSuiteNameMapper(localITestResult);
      if (!localITestResult.getMethod().isTest()) {
        str = "config " + getSuiteNameMapper(localITestResult);
      }
      paramPrintWriter.print("                            <tr class=\"all " + str + "\">\n" + "                                <td><a href=\"" + getTestCaseHTMLPath(localITestResult, paramInt) + "\">" + getSuiteName(localITestResult) + "</a></td>\n" + "                                <td><a href=\"" 
      + getTestCaseHTMLPath(localITestResult, paramInt) + "\">" + getModuleName(localITestResult) + "</a></td>\n"  + "                                <td><a href=\"" + getTestCaseHTMLPath(localITestResult, paramInt) + "\">" 
	      + getTestCaseName(localITestResult) + "</a></td>\n" + "                                <td><a href=\"" + getTestCaseHTMLPath(localITestResult, paramInt) 
	      + "\">" + getIteration(localITestResult) + "</a></td>\n" + "                                <td><a href=\"" + getTestCaseHTMLPath(localITestResult, paramInt) + "\">" + getExecutionTime(localITestResult) + "</a></td>\n" 
	      + "                                <td><img  style=\"border: none; width: 25px\" src=\"../../HTML_Design_Files/IMG/pass.png\"></td>\n" + "                            </tr>\n");
    }
  }
  
  private static void writeFailedData(PrintWriter paramPrintWriter, List<ITestResult> paramList, int paramInt)
  {
    String str = "fail";
    Iterator<ITestResult> localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      ITestResult localITestResult = localIterator.next();
      str = "fail " + getSuiteNameMapper(localITestResult);
      if (!localITestResult.getMethod().isTest()) {
        str = "config " + getSuiteNameMapper(localITestResult);
      }
      paramPrintWriter.print("                            <tr class=\"all " + str + "\">\n" + "                                <td><a href=\"" 
      + getTestCaseHTMLPath(localITestResult, paramInt) + "\">" + getSuiteName(localITestResult) + "</a></td>\n" 
	      + "                              <td><a href=\"" + getTestCaseHTMLPath(localITestResult, paramInt) + "\">" 
      + getModuleName(localITestResult) + "</a></td>\n"  + "                                <td><a href=\"" 
      + getTestCaseHTMLPath(localITestResult, paramInt) + "\">" + getTestCaseName(localITestResult) + "</a></td>\n" 
	      + "                                <td><a href=\"" + getTestCaseHTMLPath(localITestResult, paramInt) 
	      + "\">" + getIteration(localITestResult) + "</a></td>\n" + "                                <td><a href=\"" 
	      + getTestCaseHTMLPath(localITestResult, paramInt) + "\">" + getExecutionTime(localITestResult) + "</a></td>\n" 
	      + "                                <td><img  style=\"border: none;width: 25px\" src=\"../../HTML_Design_Files/IMG/fail.png\"></td>\n" + "                            </tr>\n");
    }
  }
  
  private static void writeSkippedData(PrintWriter paramPrintWriter, List<ITestResult> paramList, int paramInt)
  {
    String str = "skip";
    Iterator<ITestResult> localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      ITestResult localITestResult = localIterator.next();
      str = "skip " + getSuiteNameMapper(localITestResult);
      if (!localITestResult.getMethod().isTest()) {
        str = "config " + getSuiteNameMapper(localITestResult);
      }
      paramPrintWriter.print("                            <tr class=\"all " + str + "\">\n" + "                                <td><a href=\"" 
      + getTestCaseHTMLPath(localITestResult, paramInt) + "\">" + getSuiteName(localITestResult) + "</a></td>\n" 
	      + "                                  <td><a href=\"" + getTestCaseHTMLPath(localITestResult, paramInt) 
	      + "\">" + getModuleName(localITestResult) + "</a></td>\n"  + "                                <td><a href=\"" 
	      + getTestCaseHTMLPath(localITestResult, paramInt) + "\">" + getTestCaseName(localITestResult) + "</a></td>\n" 
	      + "                                <td><a href=\"" + getTestCaseHTMLPath(localITestResult, paramInt) + "\">" 
	      + getIteration(localITestResult) + "</a></td>\n" + "                                <td><a href=\"" 
	      + getTestCaseHTMLPath(localITestResult, paramInt) + "\">" + getExecutionTime(localITestResult) + "</a></td>\n" 
	      + "                                <td><img  style=\" border: none;width: 25px\" src=\"../../HTML_Design_Files/IMG/skip.png\"></td>\n" + "                            </tr>\n");
    }
  }
  
  public static String getSuiteName(ITestResult testResult)
  {
    return testResult.getTestContext().getSuite().getName();
  }
  
  public static String getSuiteNameMapper(ITestResult testResult)
  {
    return Attributes.getSuiteNameMapper(testResult.getTestContext().getSuite().getName());
  }
  
  public static String getTestCaseHTMLPath(ITestResult testResult, int paramInt)
  {
    String str1 = testResult.getAttribute("reportDir").toString();
    int i = (Directory.RUNName + paramInt).length();
    String str2 = str1.substring(str1.indexOf(Directory.RUNName + paramInt) + (i + 1));
    return str2 + Directory.SEP + getTestCaseName(testResult) + ".html";
  }
  
  public static String getModuleName(ITestResult testResult)
  {
      TestParameters params = ((TestParameters)testResult.getParameters()[0]);
      return params.getModuleName();
  }
  
  public static String getClassName(ITestResult testResult)
  {
    return testResult.getTestClass().getRealClass().getSimpleName();
  }
  
  public static String getIteration(ITestResult testResult)
  {
    return testResult.getAttribute("iteration").toString();
  }
  
  public static String getTestCaseName(ITestResult testResult)
  {
    TestParameters params = ((TestParameters)testResult.getParameters()[0]);
    System.out.println(params.getTestCaseName());
    return params.getTestCaseName();
  }
  
  public static String getReportDir(ITestResult testResult)
  {
    String suiteName = testResult.getTestContext().getSuite().getName();
    String testName = testResult.getTestContext().getCurrentXmlTest().getName();
    String str3 = testResult.getTestClass().getName().replace(".", Directory.SEP);
    String methodName = testResult.getMethod().getMethodName();
    methodName = methodName + "_Iteration" + testResult.getMethod().getCurrentInvocationCount();
    String str5 = suiteName + Directory.SEP + testName + Directory.SEP + str3 + Directory.SEP + methodName;
    return str5;
  }
  
  public static String getMethodType(ITestResult testResult)
  {
    ITestNGMethod localITestNGMethod = testResult.getMethod();
    if (localITestNGMethod.isAfterClassConfiguration()) {
      return "After Class";
    }
    if (localITestNGMethod.isAfterGroupsConfiguration()) {
      return "After Groups";
    }
    if (localITestNGMethod.isAfterMethodConfiguration()) {
      return "After Method";
    }
    if (localITestNGMethod.isAfterSuiteConfiguration()) {
      return "After Suite";
    }
    if (localITestNGMethod.isAfterTestConfiguration()) {
      return "After Test";
    }
    if (localITestNGMethod.isBeforeClassConfiguration()) {
      return "Before Class";
    }
    if (localITestNGMethod.isBeforeGroupsConfiguration()) {
      return "Before Groups";
    }
    if (localITestNGMethod.isBeforeMethodConfiguration()) {
      return "Before Method";
    }
    if (localITestNGMethod.isBeforeSuiteConfiguration()) {
      return "Before Suite";
    }
    if (localITestNGMethod.isBeforeTestConfiguration()) {
      return "Before Test";
    }
    if (localITestNGMethod.isTest()) {
      return "Test Method";
    }
    return "Unknown";
  }
  
  public static void header(PrintWriter paramPrintWriter)
  {
    paramPrintWriter.println("<!DOCTYPE html>\n\n<html>\n    <head>\n        <title>Current Run Reports</title>\n\n        <link rel=\"stylesheet\" type=\"text/css\" href=\"../../HTML_Design_Files/CSS/design.css\" />\n        <link rel=\"stylesheet\" type=\"text/css\" href=\"../../HTML_Design_Files/CSS/jquery.jqplot.css\" />\n\n        <script type=\"text/javascript\" src=\"../../HTML_Design_Files/JS/jquery.min.js\"></script>\n        <script type=\"text/javascript\" src=\"../../HTML_Design_Files/JS/jquery.jqplot.min.js\"></script>\n        <!--[if lt IE 9]>\n        <script language=\"javascript\" type=\"text/javascript\" src=\"../../HTML_Design_Files/JS/excanvas.js\"></script>\n        <![endif]-->\n\n        <script language=\"javascript\" type=\"text/javascript\" src=\"../../HTML_Design_Files/JS/jqplot.pieRenderer.min.js\"></script>\n        <script type=\"text/javascript\" src=\"pieChart.js\"></script>\n");
    paramPrintWriter.print("<script language=\"javascript\" type=\"text/javascript\">$(document).ready(function() { $(\".video\").hide();$(\"#showmenu\").show(); $('#showmenu').click(function(){ $('.video').toggle(\"slide\"); }); });</script><style>#showmenu{text-align:center; padding-top:350px;color: #585858; font-size: 14px;}#video{height: 550px;    margin-top: 5px;    width: 97%;    border-style: solid;    border-width: 1px;    border-color: #21ABCD;    /* Shadow for boxes */    -moz-box-shadow: 0 0 10px #CCCCCC;    -ms-box-shadow: 0 0 10px #CCCCCC;    -webkit-box-shadow: 0 0 10px #CCCCCC;    box-shadow: 0 0 10px #CCCCCC;    zoom: 1;    filter: progid:DXImageTransform.Microsoft.Shadow(Color=#cccccc, Strength=2, Direction=0),        progid:DXImageTransform.Microsoft.Shadow(Color=#cccccc, Strength=2, Direction=90),        progid:DXImageTransform.Microsoft.Shadow(Color=#cccccc, Strength=2, Direction=180),        progid:DXImageTransform.Microsoft.Shadow(Color=#cccccc, Strength=2, Direction=270);    background-color: white;}</style>");
    paramPrintWriter.println("<script language=\"javascript\" type=\"text/javascript\">\n$(document).ready(function() {\n\t$('#tcFilter').on('change',function(){\n    if($(this).val()=='pass'){\n        $('.pass').show();\n\t\t$('.fail').hide();\n\t\t$('.skip').hide();\n\t\t$('.config').hide();\n    }\n\tif($(this).val()=='fail'){\n        $('.pass').hide();\n\t\t$('.fail').show();\n\t\t$('.skip').hide();\n\t\t$('.config').hide();\n    }\n\t\n\tif($(this).val()=='skip'){\n        $('.pass').hide();\n\t\t$('.fail').hide();\n\t\t$('.skip').show();\n\t\t$('.config').hide();\n    }\n\t\nif($(this).val()=='tests'){ $('.pass').show(); $('.fail').show(); $('.skip').show(); $('.config').hide(); }\tif($(this).val()=='config'){\n        $('.pass').hide();\n\t\t$('.fail').hide();\n\t\t$('.skip').hide();\n\t\t$('.config').show();\n    }\n\t\n\tif($(this).val()=='all'){\n        $('.pass').show();\n\t\t$('.fail').show();\n\t\t$('.skip').show();\n\t\t$('.config').show();\n    }\n  });\n});       \n</script>");
    paramPrintWriter.print("<script language=\"javascript\" type=\"text/javascript\">$(document).ready(function() {\t$('#suiteFilter').on('change',function(){if($(this).val()=='all'){      $('.all').show();  }");
    Iterator<String> localIterator = Attributes.getSuiteNameMapperMap().keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      paramPrintWriter.print("if($(this).val()=='" + (String)Attributes.getSuiteNameMapperMap().get(str) + "'){" + "      $('.all').hide();" + "$('." + (String)Attributes.getSuiteNameMapperMap().get(str) + "').show();" + "" + " }");
    }
    paramPrintWriter.println("  }); });</script>");
    paramPrintWriter.println("    </head>\n    <body>\n        <table id=\"mainTable\">\n            <tr id=\"header\" >\n                <td id=\"logo\"><img src=\"../../HTML_Design_Files/IMG/" + ReportLabels.ZADO_LOGO.getLabel() + "\" alt=\"Logo\" height=\"70\" width=\"140\" /> " + "<br/>" + "                <td id=\"headertext\">\n" + "                    " + ReportLabels.HEADER_TEXT.getLabel() + "<div style=\"padding-right:20px;float:right\"><img src=\"../../HTML_Design_Files/IMG/" + ReportLabels.PROJ_LOGO.getLabel() + "\" height=\"70\" width=\"140\" /> </i></div>" + "</td>\n" + "\n" + "            </tr>");
  }
}

