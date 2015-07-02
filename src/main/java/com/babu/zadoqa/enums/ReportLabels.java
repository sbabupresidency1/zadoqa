package com.babu.zadoqa.enums;

public enum ReportLabels
{
  HEADER_TEXT("Zado Reports"),  
  PASS("Passed"),  
  FAIL("Failed"), 
  SKIP("Skipped"),  
  X_AXIS("Run Number"),  
  Y_AXIS("TC Number"),  
  LINE_CHART_TOOLTIP("Test Cases"),  
  ZADO_LOGO("zado.jpg"),  
  PROJ_LOGO(""),  PROJ_CAPTION(""), 
  PIE_CHART_LABEL("Test Cases Percent Distribution"),  
  TC_INFO_LABEL("Requirement Coverage/Build Info/Cycle - Description");
  
  private String label;
  
  private ReportLabels(String paramString)
  {
    setLabel(paramString);
  }
  
  public String getLabel()
  {
    return this.label;
  }
  
  public void setLabel(String paramString)
  {
    this.label = paramString;
  }
}

