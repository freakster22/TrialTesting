package org.apache.maven.plugins.REALM;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class Constants {
	
	public static WebDriver Drivers = null;
	
	public static String projectrootPath="D:\\DOCS\\Zensar\\REALM_Automation";
	public static String filePath = projectrootPath+"\\src\\excelfiles\\TestData.xlsx";
	public static String testfilePath = projectrootPath+"\\src\\excelfiles\\Valid docs1.xlsx";
	public static String reportFilePath = "";
	public static String executionSummaryFilePath = "D:\\DOCS\\Zensar\\REALM_Automation\\Results\\ExecutionSummary.html";
			
	public static String chromeDriverPath= projectrootPath+"\\src\\Drivers\\chromedriver.exe";
	public static String ieDriverPath= projectrootPath+"\\src\\Drivers\\IEDriverServer.exe";
	public static String ffDriverPath= projectrootPath+"\\src\\Drivers\\geckodriver.exe";
	
	public static XSSFWorkbook  workbook = null;
	public static XSSFSheet sheet = null;
	public static XSSFRow row = null;
	public static XSSFCell cell = null;
	

	
	public static String relativePath = "";
	public static String resultHtmlPath = "";
	public static String resultBasePath = "Results";
	public static String screenshotPath = projectrootPath+"\\Screenshots";
	public static String baseResultRelativePath = "Results";
	public static String baseScreenshotRelativePath = "Results";
	
	public static String AppURL = "https://www.flipkart.com";
	public static  String TestURL = "http://mail.google.com";
	public static String LoanURL = "https://emicalculator.net";
	public static String addURL = "https://computer-database.gatling.io/computers";
	
	//public static String SkipURL = "https://sso-nprd.cisco.com/autho/forms/CECLogin.html";
	public static String UploadFile="";
	
	public static String testCaseResult = "Pass";
	public static int stepCountIncrement = 0;
	public static int passedStepCount = 0;
	public static int failedStepCount = 0;
	public static int warningStepCount = 0;
	public static long exceutionStartTime;
	public static long totalExecutionTime;
	public static long suiteExecutionStartTime;
	public static long totalSuiteExecutionTime;
	public static int totalTestCaseCount = 0;
	public static int passedTestCaseCount = 0;
	public static int failedTestCaseCount = 0;
	public static int warningTestCaseCount =0;
	
	public static String applicationName = "REALM Application";
	public static String testEnvironment = "Stage->"+LoanURL;
	public static String executionRelease = "1.0";
	public static String testingCycle = "Stage";
	
	// FOR Email Functionality

	public static String UserID = "aranwar";
	public static String Password = "";
	public static String EmailTo = "aranwar@cisco.com";
	
	
}
