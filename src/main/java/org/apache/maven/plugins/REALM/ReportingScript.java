package org.apache.maven.plugins.REALM;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReportingScript {
	
	//Function to Create a New file

	public static void createFile(String filePath, String deleteIfExists){
		
		try{
			File file = new File(filePath);
			
			//Delete an existing file if String passed along with path is "yes"
			if(deleteIfExists.equalsIgnoreCase("Yes")){
				if(file.exists()){
					file.delete();
					System.out.println("File Deleted Successfully");
				}
			}
			
			//Create a new file 
			if(!file.exists()){
				if(file.createNewFile()){
					if(file.exists()){
						System.out.println("Created File Successfully");
					}else{
						System.out.println("File Not Creates Successfully");
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
    public static void createFolder(String filePath,String deleteIfExists){
		
		try{
			File folder = new File(filePath);
			
			//Delete an existing folder if String passed along with path is "yes"
			if(deleteIfExists.equalsIgnoreCase("Yes")){
				if(folder.exists()){
					folder.delete();
					System.out.println("Folder Deleted Successfully");
				}
			}
			
			//Create a new file 
			if(!folder.exists()){
				if(folder.mkdir()){
					if(folder.exists()){
						System.out.println("Created Folder Successfully");
					}else{
						System.out.println("Folder Not Creates Successfully");
					}
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		
}
	//Constants for Report
	
	public void ConstantsforReport(){
		/*
		 String testCaseResult = Constants.testCaseResult;
		 int stepCountIncrement = Constants.stepCountIncrement;
		 int passedStepCount = Constants.passedStepCount;
		 int failedStepCount = Constants.failedStepCount;
		 int warningStepCount = Constants.warningStepCount;
		 */
	}
	
	//Create a Test Case header in file
	
	public static void createTestCaseHeader(String filePath,String applicationName,String testEnvironment,String executionRelease,String testingCycle,Double testCaseID,String testCaseDescription) throws IOException{
		
		//initializing the start time
		long excecutionStartTime = System.currentTimeMillis();
		Constants.exceutionStartTime = excecutionStartTime;
		
		//creating string buffer object and file object
		StringBuffer sbf = new StringBuffer();
		File file = new File(filePath);
		
		//Check for file exists and writing to the file
		if(file.exists()){
			System.out.println("Entered the file System");
			String fontName = "Verdana";
		    String fontSize = "smaller";
		    
		    String initialHeader = "<html><body style=background-color:F6F3A8;>";
    	    String appName = "<table border=2 style=background-color:C99FD5;border:10px;color:black;width:45%;border-collapse:collapse;><tr style=background-color:C99FD5;color:red;font-weight:bold;font-style:italic;font-family:"+ fontName +";font-size:small;width:45%;><th colspan=3;>"+ applicationName +"</th></tr>";
    	    String executionHeader = "<tr style=background-color:C99FD5;color:black;font-weight:bold;font-style:normal;font-family:"+ fontName +";font-size:"+ fontSize +";><th style=padding:5px;>Test Environment</th><th style=padding:5px;>Release Number</th><th style=padding:5px;>Execution Cycle</th></tr><tr><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize +";text-align:center;>"+ testEnvironment +"</td><td style=padding:3px;font-family:" + fontName +";font-size:"+ fontSize +";text-align:center;>"+ executionRelease + "</td><td style=padding:3px;font-family:"+ fontName + ";font-size:"+ fontSize +";text-align:center;>"+ testingCycle +"</td></tr></table>";
    		String tcIDDesc = "<table border=2 style=background-color:C8C6D2;border:5px;color:black;width:60%;border-collapse:collapse;><tr><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize +";text-align:left;font-weight:bold;font-style:italic;>Test Case ID</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize +";text-align:left;>"+ testCaseID + "</td></tr><tr><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize +";text-align:left;font-weight:bold;font-style:italic;>Test Case Description</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize +";text-align:left;>"+ testCaseDescription + "</td></tr></table>";
    		//String navigateLink= "<tr style=background-color:C99FD5;color:black;font-weight:bold;font-style:normal;font-family:"+ fontName +";font-size:"+ fontSize +";><a href="+ executionFilePath + " style=color:blue;>Navigate to Execution Summary</a></tr>";
    		String tcHeader = "<table border=2 style=background-color:72BEE4;border:2px;color:black;width:100%;border-collapse:collapse;><tr style=background-color:817E8A;color:white;font-weight:bold;font-style:italic;font-family:"+ fontName +";font-size:"+ fontSize +";><th style=padding:3px;width:5%>Step No</th><th style=padding:5px;>Step Description</th><th style=padding:3px;>Expected Result</th><th style=padding:5px;>Actual Result</th><th style=padding:3px;width:5%>Status</th></tr>";
    		//String fullString = initialHeader + appName + executionHeader + "<br>" + tcIDDesc + "<br>" + navigateLink + "<br>" +" <br>" +tcHeader;
    		String fullString = initialHeader + appName + executionHeader + "<br>" + tcIDDesc + "<br>" + tcHeader;		
    		sbf.append(fullString);
    		
    		FileWriter files = new FileWriter(filePath,true);
    		BufferedWriter bw = new BufferedWriter(files);
    		bw.write(sbf.toString());
    		bw.flush();
    		bw.close();
		    	
		}
		
	}
	
	//Update step results to the file
	
	public static void updateTestStepResult(String stepNo,String stepDescription,String expectedResult,String actualResult,String Status) throws Exception{
		
		String boldStyle = "normal";
		if(stepNo !=""){
			Constants.stepCountIncrement = Constants.stepCountIncrement +1;
			stepNo = "Step-" + Constants.stepCountIncrement;
			boldStyle = "bold"; 	 
		}
		
		//creating string buffer object and file object
		String filePath = Constants.reportFilePath;
		
		StringBuffer sbf = new StringBuffer();
		File file = new File(filePath);
		
		//Check for file exists and writing to the file
		if(file.exists()){
			System.out.println("Entered file ");
			String fontName = "Verdana";
			String fontSize = "smaller";
			String tcResult = null;
			String scFilePath = null;
			
			if(Status.equalsIgnoreCase("Pass")){
				 tcResult = "<tr><td style=padding:3px;font-weight:bold;font-family:"+ fontName +";font-size:"+ fontSize + ";>"+ stepNo +"</td><td style=padding:3px;font-weight:"+ boldStyle + ";font-family:"+ fontName +";font-size:"+ fontSize + ";>"+ stepDescription + "</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize + ";>"+ expectedResult + "</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize + ";>"+ actualResult +"</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize + ";font-weight:bold;color:green;>"+ Status +"</td></tr>";
				 Constants.passedStepCount=Constants.passedStepCount+1;
				 System.out.println("pass");
			}else if(Status.equalsIgnoreCase("Fail")){
				scFilePath= Generic.captureScreenShot();
				scFilePath = "..//"+ scFilePath;
				System.out.println("Screenshot Path ==== >" + scFilePath);
				tcResult = "<tr><td style=padding:3px;font-weight:bold;font-family:"+ fontName +";font-size:"+ fontSize + ";>"+ stepNo +"</td><td style=padding:3px;font-weight:"+ boldStyle + ";font-family:"+ fontName +";font-size:"+ fontSize + ";>"+ stepDescription + "</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize + ";>"+ expectedResult + "</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize + ";>"+ actualResult +"</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize + ";font-weight:bold;color:red;>"+"<a href="+ scFilePath + " style=color:red;>" + Status +"</a></td></tr>";
				Constants.testCaseResult = "Fail";
				Constants.failedStepCount = Constants.failedStepCount + 1;
			}else if(Status.equalsIgnoreCase("Warning")){
				scFilePath= Generic.captureScreenShot();
			    tcResult = "<tr><td style=padding:3px;font-weight:bold;font-family:"+ fontName +";font-size:"+ fontSize + ";>"+ stepNo +"</td><td style=padding:3px;font-weight:"+ boldStyle + ";font-family:"+ fontName +";font-size:"+ fontSize + ";>"+ stepDescription + "</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize + ";>"+ expectedResult + "</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize + ";>"+ actualResult +"</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize + ";font-weight:bold;color:red;>"+"<a href="+ scFilePath + " style=color:red;>" + Status +"</a></td></tr>";
			    Constants.testCaseResult = "Warning";
			    Constants.testCaseResult = "Pass";
				Constants.warningStepCount = Constants.warningStepCount + 1;
			}else{
				tcResult = "<tr><td style=padding:3px;font-weight:bold;font-family:"+ fontName +";font-size:"+ fontSize + ";>"+ stepNo +"</td><td style=padding:3px;font-weight:"+ boldStyle + ";font-family:"+ fontName +";font-size:"+ fontSize + ";>"+ stepDescription + "</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize + ";>"+ expectedResult + "</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize + ";>"+ actualResult +"</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize + ";font-weight:bold;>"+ Status +"</td></tr>";
			}
			String fullString = tcResult;
			sbf.append(fullString);
				    
			 FileWriter files = new FileWriter(filePath,true);
			 BufferedWriter bw = new BufferedWriter(files);
			 bw.write(sbf.toString());	
			 bw.flush();
			 bw.close();
			 
			 boolean fExists = file.exists();
			 if(fExists == false){
				 System.out.println("File Not Exists after Updating ");
			 }
			
		}
		
	}
	
	// update Test Case Summary to file
	public static void updateTestCaseSummary() throws IOException{
		
		int passCount = Constants.passedStepCount;
		int failCount = Constants.failedStepCount;
		int warningCount = Constants.warningStepCount;
		
		long testCaseExecutionEndTime =System.currentTimeMillis();
		long totalExecutionEndTime  = (testCaseExecutionEndTime -Constants.exceutionStartTime)/1000;
		Constants.totalExecutionTime = totalExecutionEndTime;
		
		//creating string buffer object and file object
		String filePath = Constants.reportFilePath;
		StringBuffer sbf = new StringBuffer();
		File file = new File(filePath);
		
		if(file.exists()){
			
			String fontName = "Verdana";
			String fontSize = "smaller";

			int intTotalCount = passCount + failCount + warningCount;
		    String closePreviousTable = "</table><br>";
			String executionSummary = "<table border=2 style=background-color:72BEE4;border:2px black;width:20%;border-collapse:collapse;><tr style=background-color:817E8A;color:white;font-weight:bold;font-style:italic;font-family:" + fontName + ";font-size:medium;><th colspan=2;>TestCase Execution Summary</th></tr>";
		    String totalCountHTML = "<tr><td style=padding:3px;font-family:" + fontName + ";font-size:" + fontSize + ";text-align:left;width:30%>Total Number of Steps</td><td style=padding:3px;font-family:" + fontName + ";font-size:" + fontSize + ";text-align:center;width:10%>"+intTotalCount+"</td></tr>" ;   
            String passCountHTML = "<tr><td style=padding:3px;font-family:" + fontName + ";font-size:" + fontSize + ";text-align:left;>No of Steps Passed</td><td style=padding:3px;font-family:" + fontName + ";font-size:" + fontSize + ";text-align:center;>"+passCount+"</td></tr>";
		    String failCountHTML = "<tr><td style=padding:3px;font-family:" + fontName + ";font-size:" + fontSize + ";text-align:left;>No of Steps Failed</td><td style=padding:3px;font-family:" + fontName + ";font-size:" + fontSize + ";text-align:center;>"+failCount+"</td></tr>";
			String warningCountHTML = "<tr><td style=padding:3px;font-family:" + fontName + ";font-size:" + fontSize + ";text-align:left;>No of Warning Steps</td><td style=padding:3px;font-family:" + fontName + ";font-size:" + fontSize + ";text-align:center;>"+warningCount+"</td></tr>";
		    String timeCountHTML = "<tr><td style=padding:3px;font-family:" + fontName + ";font-size:" + fontSize + ";text-align:left;>Total Execution Time (sec) </td><td style=padding:3px;font-family:" + fontName + ";font-size:" + fontSize + ";text-align:center;>"+totalExecutionEndTime+"</td></tr>";
		     
		    String fullString = closePreviousTable + executionSummary + totalCountHTML + passCountHTML + failCountHTML + warningCountHTML + timeCountHTML;
		    sbf.append(fullString);
		    
		    FileWriter files = new FileWriter(filePath,true);
			BufferedWriter bw = new BufferedWriter(files);
			bw.write(sbf.toString());	
			bw.flush();
			bw.close();
			 
			boolean fExists = file.exists();
			if(fExists == false){
				System.out.println("File Not Exists after Updating ");
			}
		    
		}
	}
	
	// create Execution summary header
	public static void createExecutionSummaryHeader() throws IOException{
		
		String executionSummaryFilePath = Constants.executionSummaryFilePath;
		String applicationName = Constants.applicationName;
		String testEnvironment = Constants.testEnvironment;
		String executionRelease = Constants.executionRelease;
		String testingCycle = Constants.testingCycle;	
		
		long suiteExecutionStartTime = System.currentTimeMillis();
		Constants.suiteExecutionStartTime = suiteExecutionStartTime;
		
		//create string buffer and file object
		StringBuffer sbf = new StringBuffer();
		File file = new File(executionSummaryFilePath);
		
		//Check for file exists and writing to the file
		if(file.exists()){
			
			String fontName = "Verdana";
			String fontSize = "smaller";
			String initialHeader = "<html><body style=background-color:F6F3A8;>";
			String appName = "<table border=2 style=background-color:C99FD5;border:10px;color:black;width:45%;border-collapse:collapse;><tr style=background-color:C99FD5;color:white;font-weight:bold;font-style:italic;font-family:"+ fontName +";font-size:small;width:45%;><th colspan=3;>"+ applicationName +"</th></tr>";
			String executionHeader = "<tr style=background-color:C99FD5;color:black;font-weight:bold;font-style:normal;font-family:"+ fontName +";font-size:"+ fontSize +";><th style=padding:5px;>Test Environment</th><th style=padding:5px;>Release Number</th><th style=padding:5px;>Execution Cycle</th></tr><tr><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize +";text-align:center;>"+ testEnvironment +"</td><td style=padding:3px;font-family:" + fontName +";font-size:"+ fontSize +";text-align:center;>"+ executionRelease + "</td><td style=padding:3px;font-family:"+ fontName + ";font-size:"+ fontSize +";text-align:center;>"+ testingCycle +"</td></tr></table>";
			String tcHeader = "<table border=2 style=background-color:72BEE4;border:2px;color:black;width:100%;border-collapse:collapse;><tr style=background-color:817E8A;color:white;font-weight:bold;font-style:italic;font-family:"+ fontName +";font-size:"+ fontSize +";><th style=padding:3px;width:10%>Test Case No</th><th style=padding:3px;>Test Case Name</th><th style=padding:5px;>Test Case Description</th><th style=padding:3px;width:5%>Status</th></tr>";
			String fullString = initialHeader + appName + executionHeader + "<br>" +  "<br>" + tcHeader;
			sbf.append(fullString);	
			
			FileWriter files = new FileWriter(executionSummaryFilePath,true);
			BufferedWriter bw = new BufferedWriter(files);
			
			bw.write(sbf.toString());
			bw.flush();
			bw.close();
			
		
			boolean fExists = file.exists();
			if(fExists == false){
				System.out.println("File Not Exists after Updating ");
			}
		}	
		
	}
	
	// create update testcase execution summary 
	public static void updateTestCaseExecutionSumary(int testCaseNo, String testCaseName, String testCaseDescription, String Status) throws IOException{

	//	String boldStyle = "normal";
		String executionSummaryFilePath = Constants.executionSummaryFilePath;
		
		//create string buffer and file object
		StringBuffer sbf = new StringBuffer();
		File file = new File(executionSummaryFilePath);
		
		//Check for file exists and writing to the file
		if(file.exists()){
			
			String fontName = "Verdana";
			String fontSize = "smaller";
			String tcResult = null;
			String scFilePath = Constants.relativePath;
			scFilePath = "..//"+ scFilePath;
			if(Status.equalsIgnoreCase("Pass")){
				tcResult = "<tr><td style=padding:3px;font-weight:bold;font-family:" + fontName +";font-size:"+ fontSize + ";>" + testCaseNo + "</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize + ";>"+ testCaseName + "</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize + ";>"+ testCaseDescription +"</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize + ";font-weight:bold;color:red;>"+"<a href=" + scFilePath + " style=color:green;>" + Status +"</a></td></tr>";
				Constants.passedTestCaseCount = Constants.passedTestCaseCount + 1;
			}else if(Status.equalsIgnoreCase("Fail")){
				
				System.out.println("Log updateTestCaseExecutionSumary - Fail case. Path: "+ scFilePath);
				tcResult = "<tr><td style=padding:3px;font-weight:bold;font-family:"+ fontName +";font-size:"+ fontSize + ";>" + testCaseNo + "</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize + ";>"+ testCaseName + "</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize + ";>"+ testCaseDescription +"</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize + ";font-weight:bold;color:red;>"+"<a href=" + scFilePath + " style=color:red;>" + Status +"</a></td></tr>";
				Constants.testCaseResult = "Fail";
				Constants.failedTestCaseCount = Constants.failedTestCaseCount + 1;
			}else if(Status.equalsIgnoreCase("Warning")){
				tcResult = "<tr><td style=padding:3px;font-weight:bold;font-family:"+ fontName +";font-size:"+ fontSize + ";>" + testCaseNo + "</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize + ";>"+ testCaseName + "</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize + ";>"+ testCaseDescription +"</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize + ";font-weight:bold;color:red;>"+"<a href=" + scFilePath + " style=color:orange;>" + Status +"</a></td></tr>";
				Constants.testCaseResult = "Pass";
				Constants.warningTestCaseCount = Constants.warningTestCaseCount + 1;
			}else{
				tcResult = "<tr><td style=padding:3px;font-weight:bold;font-family:"+ fontName +";font-size:"+ fontSize + ";>" + testCaseNo + "</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize + ";>"+ testCaseName + "</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize + ";>"+ testCaseDescription +"</td><td style=padding:3px;font-family:"+ fontName +";font-size:"+ fontSize + ";font-weight:bold;>"+ Status +"</td></tr>";
			}
			String fullString = tcResult;
			sbf.append(fullString);
			
			
			FileWriter files = new FileWriter(executionSummaryFilePath,true);
			BufferedWriter bw = new BufferedWriter(files);
			
			bw.write(sbf.toString());
			bw.flush();
			bw.close();
			
		
			boolean fExists = file.exists();
			if(fExists == false){
				System.out.println("File Not Exists after Updating ");
			}		                			
				
		}
		
	}
	
	//Create  Execution Summary 
	public static void updateExecutionSummary() throws IOException {
		
		int totalTestCaseCount = Constants.totalTestCaseCount;
		int passedTestCaseCount = Constants.passedTestCaseCount;
		int failedTestCaseCount = Constants.failedTestCaseCount;
		int warningTestCaseCount = Constants.warningTestCaseCount;
		
		totalTestCaseCount = passedTestCaseCount + failedTestCaseCount + warningTestCaseCount;
		
		long suiteExecutionEndTime =System.currentTimeMillis();
		long totalSuiteExecutionTime  = (suiteExecutionEndTime -Constants.suiteExecutionStartTime)/1000;
		Constants.totalSuiteExecutionTime = totalSuiteExecutionTime;
		String executionSummaryFilePath = Constants.executionSummaryFilePath;
		
		//creating string buffer object and file object
		//String filePath = Constants.reportFilePath;
		StringBuffer sbf = new StringBuffer();
		File file = new File(executionSummaryFilePath);
		
		//Check for file exists and writing to the file
		if(file.exists()){
			
			String fontName = "Verdana";
			String fontSize = "smaller";
							
							
			String closePreviousTable = "</table><br>";
			String executionSummary = "<table border=2 style=background-color:72BEE4;border:2px black;width:20%;border-collapse:collapse;><tr style=background-color:817E8A;color:white;font-weight:bold;font-style:italic;font-family:" + fontName + ";font-size:medium;><th colspan=2;>Execution Summary</th></tr>";
			String totalCount = "<tr><td style=padding:3px;font-family:" + fontName + ";font-size:" + fontSize + ";text-align:left;width:30%>Total Number of Test Cases</td><td style=padding:3px;font-family:" + fontName + ";font-size:" + fontSize + ";text-align:center;width:10%>"+ totalTestCaseCount + "</td></tr>";
			String passCountHTML = "<tr><td style=padding:3px;font-family:" + fontName + ";font-size:" + fontSize + ";text-align:left;>No of Passed Test Cases</td><td style=padding:3px;font-family:" + fontName + ";font-size:" + fontSize + ";text-align:center;>"+ passedTestCaseCount + "</td></tr>";
			String failCountHTML = "<tr><td style=padding:3px;font-family:" + fontName + ";font-size:" + fontSize + ";text-align:left;>No of Failed Test Cases</td><td style=padding:3px;font-family:" + fontName + ";font-size:" + fontSize + ";text-align:center;>" +failedTestCaseCount + "</td></tr>";
			String warningCountHTML = "<tr><td style=padding:3px;font-family:" + fontName + ";font-size:" + fontSize + ";text-align:left;>No of Warining Test Cases</td><td style=padding:3px;font-family:" + fontName + ";font-size:" + fontSize + ";text-align:center;>" + warningTestCaseCount + "</td></tr>";
			String timeCountHTML = "<tr><td style=padding:3px;font-family:" + fontName + ";font-size:" + fontSize + ";text-align:left;>Total Execution Time (sec)</td><td style=padding:3px;font-family:" + fontName + ";font-size:" + fontSize + ";text-align:center;>" + totalSuiteExecutionTime + "</td></tr>";
			
			String fullString = closePreviousTable + executionSummary + totalCount + passCountHTML + failCountHTML + warningCountHTML + timeCountHTML; 
			sbf.append(fullString);
			
			FileWriter files = new FileWriter(executionSummaryFilePath,true);
			BufferedWriter bw = new BufferedWriter(files);
			
			bw.write(sbf.toString());
			bw.flush();
			bw.close();
			
		
			boolean fExists = file.exists();
			if(fExists == false){
				System.out.println("File Not Exists after Updating ");
			}
			
		}
		
		
	}
	
	
}
