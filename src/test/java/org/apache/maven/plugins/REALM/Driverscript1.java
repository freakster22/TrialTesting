package org.apache.maven.plugins.REALM;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.lang.reflect.Method;

import org.apache.maven.plugins.REALM.Constants;
import org.apache.maven.plugins.REALM.ExcelReadnew;
import org.apache.maven.plugins.REALM.ReportingScript;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

//import com.thoughtworks.selenium.webdriven.commands.Type;

public class Driverscript1 {
	
	@Test
	public void driverScript() throws Throwable
	{


		System.out.println("Selenium ============= ");

		//Create a excel object and initialize all workbook elements
		ExcelReadnew readExcel = new ExcelReadnew();
		XSSFWorkbook workbook =Constants.workbook;
		XSSFSheet sheet = Constants.sheet;
		XSSFSheet sheet1 = Constants.sheet;
		XSSFRow row = Constants.row;
		XSSFCell cell = Constants.cell;
		String requiredMasterSheetName = "MainSheet";

		//Create WebDriver object and string variable to pass the browser if you want to execute in different browsers
		WebDriver Driver = null;
		
		String ExecutionBrowser = "Firefox";

		//Create a execution summary file	
		ReportingScript.createFile(Constants.executionSummaryFilePath,"YES");  

		//Add header to the execution summary file
		ReportingScript.createExecutionSummaryHeader();

		//Get the sheetCount to start iteration in test data excel
		int isheetCount = workbook.getNumberOfSheets();
		//For loop to get the MainSheet
		for(int isheet = 0; isheet<isheetCount; isheet++){
			String displayedSheetName = workbook.getSheetName(isheet);

			// Check if the Sheet name matches with the required one [ requiredMasterSheetName - Initialized above]
			if(displayedSheetName.equalsIgnoreCase(requiredMasterSheetName)){
				sheet1 = workbook.getSheet(displayedSheetName);                                            
				int rowCount = sheet1.getLastRowNum();

				//Iterate inside the MainSheet
				String orgName = "";
				for(int iRow =1; iRow<=rowCount ;iRow++){
					row = sheet1.getRow(iRow);
					String displayedFlag = row.getCell(0).getStringCellValue();

					if(displayedFlag.equalsIgnoreCase("Yes")){                                                           
						orgName = row.getCell(2).getStringCellValue();
						String orgSheetName = row.getCell(3).getStringCellValue();
		
						System.out.print("orgName" +orgName);
						System.out.print("orgSheetName" +orgSheetName);
						String folderPath = Constants.resultBasePath + orgName + "\\" ;
						ReportingScript.createFolder(folderPath, "Yes");

						//For loop to get the required sheet
						for(int itSheet = 0; itSheet<isheetCount; itSheet++){
							sheet = workbook.getSheet(orgSheetName);
							Constants.sheet = sheet;

							//For loop to execute inside the required Sheet
						int rowCountITSheet = sheet.getLastRowNum();
						for(int itRow=1; itRow<=rowCountITSheet; itRow++){
								row = sheet.getRow(itRow);
								String displayedITFlag = row.getCell(0).getStringCellValue();
								//Iterate through sheet if flag is set to Yes
								if(displayedITFlag.equalsIgnoreCase("Yes")){
									if(ExecutionBrowser.equalsIgnoreCase("Firefox")){											
										System.setProperty("webdriver.gecko.driver", Constants.ffDriverPath);
                     					Driver = new FirefoxDriver();
										//Driver.manage().window().maximize();
									} else if (ExecutionBrowser.equalsIgnoreCase("ie")){	
										System.setProperty("webdriver.ie.driver", Constants.ieDriverPath);
										Driver= new InternetExplorerDriver();	
									}else if (ExecutionBrowser.equalsIgnoreCase("Chrome")){	
										System.setProperty("webdriver.chrome.driver", Constants.chromeDriverPath);
										//ChromeOptions options = new ChromeOptions();
										//options.setExperimentalOption("useAutomationExtension", false);
										//options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation")); 
										Driver= new ChromeDriver();	
								}
								//	Driver.manage().window().maximize();
									//Associate the driver object to constant
									Constants.Drivers = Driver; 
									
									double testcaseid = row.getCell(4).getNumericCellValue();
									String scenarioName = row.getCell(2).getStringCellValue();
									String testCaseDescription = row.getCell(3).getStringCellValue();
									//String userRole = row.getCell(4).getStringCellValue();
									double testId = testcaseid;
									String testCaseID = scenarioName;
									String methodName = scenarioName;

									//increment the test case count
									Constants.totalTestCaseCount ++;
									Constants.stepCountIncrement = 0;
									Constants.testCaseResult = "Pass";
									Constants.passedStepCount = 0;
									Constants.failedStepCount = 0;
									Constants.warningStepCount = 0;

									String TCFilePath = Constants.resultBasePath + orgName + "\\" + scenarioName + ".html" ;
									//String TCFilePath = Constants.resultBasePath + scenarioName + ".html" ;
									Constants.reportFilePath = TCFilePath;
									Constants.relativePath = Constants.baseResultRelativePath + orgName + "//" + scenarioName + ".html" ;
									System.out.println("Constants.relativePath  - " +Constants.relativePath );

									//create tescase execution file
									ReportingScript.createFile(TCFilePath,"YES");     
									//Add header to testcase execution file
									ReportingScript.createTestCaseHeader(Constants.reportFilePath, Constants.applicationName, Constants.testEnvironment, Constants.executionRelease, Constants.testingCycle, testId, testCaseDescription);

									// Create parameter object for passing values to method
									Object[] objParams = {Driver,orgSheetName,scenarioName};
									Class<?> params[] = new Class[objParams.length];
									for (int i = 0; i < objParams.length; i++) {
										if (objParams[i] instanceof String) {
											params[i] = String.class;
										}else if (objParams[i] instanceof WebDriver) {
											params[i] = WebDriver.class;
										}else if (objParams[i] instanceof Integer) {
											params[i] = Integer.TYPE;     
										}       
									}

									// Provide Class Name with Package
									Class<?> className = Class.forName("org.apache.maven.plugins.REALM.All_functions_REALMAutomationScrips");

									// Create new object Instance for the above Class
									Object objNew = className.newInstance();

									// Methodname is Name of the function which is testscenario
									Method  invokeMethod = className.getDeclaredMethod(methodName, params);

									// The below code invoke/initiate the execution of the test script
									invokeMethod.invoke (objNew,objParams);	

									//Add the result of execution to testcase  executionfile
									ReportingScript.updateTestCaseSummary();

									//Add the summary of test case execution to testcase execution file
									ReportingScript.updateTestCaseExecutionSumary(Constants.totalTestCaseCount, testCaseID, testCaseDescription, Constants.testCaseResult);

									//Driver.quit();
									   try 
									    {
									       // Driver.close();
									       // Runtime.getRuntime().exec("taskkill /f /im firefox.exe ");

									    }
									    catch (Exception anException) 
									    {
									        anException.printStackTrace();
									    }
									   }

							}

							break;
						}
						//}
					}
				}
			}

		}
		//Add the total execution summary to execution summary file
		ReportingScript.updateExecutionSummary();
	}

	 @AfterTest
	   public void Email() throws Exception {
	      System.out.println("in afterTest");
	     // SendMail.SendMail(Constants.executionSummaryFilePath);
	   }


}

//For opening default browser
//ProfilesIni profile = new ProfilesIni();
//FirefoxProfile myprofile = profile.getProfile("default");
//For opening private browser
//FirefoxProfile myprofile = profile.getProfile("browser.privatebrowsing.autostart");
//Driver= new FirefoxDriver(myprofile);

