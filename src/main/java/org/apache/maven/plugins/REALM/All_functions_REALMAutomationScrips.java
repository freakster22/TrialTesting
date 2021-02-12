package org.apache.maven.plugins.REALM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class All_functions_REALMAutomationScrips {

	// ============TC:Login==============Method for Login to Audit management

	public static boolean flipkartLogin(WebDriver Driver, String sheetName, String testScenarioName) throws Throwable

	{

		boolean loginFlag = true;
		try {

			// opening the browser and navigating to URL
			Driver.navigate().to(Constants.AppURL);
			Driver.manage().window().maximize();

			ExcelReadnew ReadExcel = new ExcelReadnew();

			// Getting xpath values from excel sheet
			String[] obj_Username = ReadExcel.getObjRepositoryValue("Username");
			String[] obj_Password = ReadExcel.getObjRepositoryValue("Password");
			String[] obj_Login = ReadExcel.getObjRepositoryValue("Login");
			String[] searchBox = ReadExcel.getObjRepositoryValue("Searchbox");
			String[] obj_Search = ReadExcel.getObjRepositoryValue("SearchIcon");
			String[] sortBy = ReadExcel.getObjRepositoryValue("Sort");
			String[] obj_item = ReadExcel.getObjRepositoryValue("Item");
			String Username = ReadExcel.getCellValue(sheetName, testScenarioName, "UserName");
			String Password = ReadExcel.getCellValue(sheetName, testScenarioName, "Password");
			String searchText = ReadExcel.getCellValue(sheetName, testScenarioName, "Keyword");
			String price = "";

			String windowTitle = Driver.getTitle();

			Thread.sleep(5000);
			System.out.println(Username);
			// Calling the generciInputOperation function to enter userid ,pwd and submit
			// the form to login in to audit App.Getting field values from Constants.java
			// file
			Generic.genericInputOperation(Driver, obj_Username[0], obj_Username[1], "type", Username);

			Thread.sleep(3000);

			Generic.genericInputOperation(Driver, obj_Password[0], obj_Password[1], "type", Password);

			Generic.genericInputOperation(Driver, obj_Login[0], obj_Login[1], "click", "");
			Thread.sleep(5000);

			if (windowTitle.contains("Online Shopping Site")) {
				ReportingScript.updateTestStepResult("Step-1",
						"Login to Flipkart.com Application for URL --> " + Constants.AppURL,
						"User Should Login Successfully", "User Logged in", "Pass");

				Generic.genericInputOperation(Driver, searchBox[0], searchBox[1], "type", searchText);
				Generic.genericInputOperation(Driver, obj_Search[0], obj_Search[1], "click", "");
				Thread.sleep(5000);
				Generic.genericInputOperation(Driver, sortBy[0], sortBy[1], "click", "");
				Thread.sleep(5000);
				if (Driver.getTitle().contains(searchText)) {
					ReportingScript.updateTestStepResult("Step-2", "Search Item Page for " + searchText,
							"A search Page Displaying Items", "Items Visible", "Pass");
					price = Generic.genericFetchValue(Driver, obj_item[0], obj_item[1], "", "getText");
					if (!price.equals("")) {
						System.out.println("Price: " + price);
						ReportingScript.updateTestStepResult("Step-3", "Getting the price for item: " + searchText,
								"Price FOUND", price, "Pass");
					} else {
						ReportingScript.updateTestStepResult("Step-3", "Getting the price for item: " + searchText,
								"Price NOT FOUND ", "N/A", "FAILED");
					}
					Thread.sleep(5000);

				} else {
					ReportingScript.updateTestStepResult("Step-2", "Search Item Page for " + searchText,
							"A search Page Displaying Items", "Items Not Visible", "Failed");
					System.exit(0);
				}
			} else {
				ReportingScript.updateTestStepResult("Step-1",
						"Login to Flipkart.com Application for URL --> " + Constants.AppURL,
						"User Should Login Successfully", "User Not Logged in", "Fail");
				System.exit(0);
			}

			return loginFlag;

		} catch (Exception e) {
			loginFlag = false;
			String Error = e.getMessage();
			ReportingScript.updateTestStepResult("Step", "Exception Occurred", "Exception Occurred", Error, "Fail");
			return loginFlag;

		}
	}

	public static void logout(WebDriver Driver) throws Throwable {

		Generic.genericInputOperation(Driver, "xpath", "//a[@title='Logout']", "Click", "");
		Thread.sleep(4000);
		boolean SynbtnFlag = Driver.findElement(By.xpath("//a[contains(text(), 'Log back in')]")).isDisplayed();

		if (SynbtnFlag == true) {
			ReportingScript.updateTestStepResult("Step--->", "Log Out ", "User Should able to log out Successfully",
					"User successfully logged out", "Pass");
		} else {
			ReportingScript.updateTestStepResult("Step--->", "Log Out ", "User Should able to log out Successfully",
					"User successfully logged out", "Fail");
		}
		/// Driver.navigate().to(Constants.AppURL);
	}

	public static void mailCount(WebDriver Driver, String sheetName, String testScenarioName) throws Throwable {

		Driver.navigate().to(Constants.TestURL);
		ExcelReadnew ReadExcel = new ExcelReadnew();

		String email = ReadExcel.getCellValue(sheetName, testScenarioName, "UserName");
		String password = ReadExcel.getCellValue(sheetName, testScenarioName, "Password");
		String obj_User[] = ReadExcel.getObjRepositoryValue("Email");
		String obj_Pass[] = ReadExcel.getObjRepositoryValue("Password");
		String obj_Next[] = ReadExcel.getObjRepositoryValue("Next");
		String obj_category[] = ReadExcel.getObjRepositoryValue("Category");
		Thread.sleep(3000);

		if ("Gmail".equalsIgnoreCase(Driver.getTitle())) {
			ReportingScript.updateTestStepResult("Step-1",
					"Login to Gmail Application for URL --> " + Constants.TestURL, "User Should be in Gmail Webpage",
					"User navigated", "Pass");
			System.out.println("Username: " + email);
			Generic.genericInputOperation(Driver, obj_User[0], obj_User[1], "type", email);
			Generic.genericInputOperation(Driver, obj_Next[0], obj_Next[1], "click", "");
			Thread.sleep(5000);

			Generic.genericInputOperation(Driver, obj_Pass[0], obj_Pass[1], "type", password);
			Generic.genericInputOperation(Driver, obj_Next[0], obj_Next[1], "click", "");
			Thread.sleep(10000);

			if (Driver.getTitle().contains("Inbox")) {
				ReportingScript.updateTestStepResult("Step-2", "Login to Google for Account--> " + email,
						"Inbox of the user should be visible", "Inbox Page is displayed", "PASS");

				String primaryFlag = Generic.genericFetchValue(Driver, obj_category[0], obj_category[1],
						"aria-selected", "getAttribute");
				if ("True".equalsIgnoreCase(primaryFlag)) {
					GmailLogin.getCount(Driver);
				} else {
					Generic.genericInputOperation(Driver, obj_category[0], obj_category[1], "click", "");
					Thread.sleep(5000);
					GmailLogin.getCount(Driver);
				}
				ReportingScript.updateTestStepResult("Step-3", "Counting the mails", "Display of Total no. of mails",
						"Num of mails are displayed", "PASS");

			} else
				ReportingScript.updateTestStepResult("Step-2", "Login to Google for Account--> " + email,
						"Inbox of the user should be visible", "Inbox Page is NOT displayed", "FAIL");
		} else
			ReportingScript.updateTestStepResult("Step-1",
					"Login to Gmail Application for URL --> " + Constants.TestURL, "User Should be in Gmail Webpage",
					"User not navigated", "Fail");
	}

	public static void loanCalculator(WebDriver Driver, String sheetName, String testScenarioName) throws Throwable {

		Driver.navigate().to(Constants.LoanURL);
		ExcelReadnew ReadExcel = new ExcelReadnew();

		String[] objAmount = ReadExcel.getObjRepositoryValue("Amount");
		String[] objInterest = ReadExcel.getObjRepositoryValue("Interest");
		String[] objTime = ReadExcel.getObjRepositoryValue("Time");
		String[] objLoanEMI = ReadExcel.getObjRepositoryValue("LoanEMI");
		String finalEMI = "";

		if (Driver.getTitle().contains("EMI Calculator")) {
			ReportingScript.updateTestStepResult("Step-1",
					"Login to Gmail Application for URL --> " + Constants.LoanURL, "User Should be in Gmail Webpage",
					"User navigated", "Pass");
			Generic.genericInputOperation(Driver, objAmount[0], objAmount[1], "clear", "");
			Generic.genericInputOperation(Driver, objAmount[0], objAmount[1], "type", "8000000");
			Generic.genericInputOperation(Driver, objInterest[0], objInterest[1], "clear", "");
			Driver.findElement(By.xpath(objInterest[1])).sendKeys(Keys.CONTROL + "a");
			Driver.findElement(By.xpath(objInterest[1])).sendKeys("1" + "6");

			Thread.sleep(3000);
			// Generic.genericInputOperation(Driver, objInterest[0], objInterest[1], "type",
			// "7.5");
			Generic.genericInputOperation(Driver, objTime[0], objTime[1], "clear", "");
			Thread.sleep(3000);
			Generic.genericInputOperation(Driver, objTime[0], objTime[1], "type", "8");
			Thread.sleep(5000);
			Generic.genericInputOperation(Driver, objLoanEMI[0], objLoanEMI[1], "click", "");
			Thread.sleep(5000);
			finalEMI = Generic.genericFetchValue(Driver, objLoanEMI[0], objLoanEMI[1], "", "getText");
			if (!finalEMI.equals("")) {
				ReportingScript.updateTestStepResult("Step-2", "User should get Loan Amount", "Loan Amount:", finalEMI,
						"Pass");
			} else {
				ReportingScript.updateTestStepResult("Step-2", "User should get Loan Amount", "Loan Amount:", "N/A",
						"Fail");
			}

		} else {
			ReportingScript.updateTestStepResult("Step-1",
					"Login to Gmail Application for URL --> " + Constants.LoanURL, "User Should be in Gmail Webpage",
					"User not navigated", "Fail");
		}
	}

	public static void addProduct(WebDriver Driver, String sheetName, String testScenarioName) throws Throwable {
		Driver.navigate().to(Constants.addURL);
		//Driver.manage().window().maximize();
		ExcelReadnew ReadExcel = new ExcelReadnew();
		if (Driver.getTitle().contains("Computers")) {
			ReportingScript.updateTestStepResult("Step-1",
					"Login to Gmail Application for URL --> " + Constants.LoanURL, "User Should be in Gmail Webpage",
					"User navigated", "Pass");}
		
		String product = ReadExcel.getCellValue(sheetName, testScenarioName, "Keyword");
		System.out.println("Product: "+product);
		String obj_SearchText[] = ReadExcel.getObjRepositoryValue("SearchText");//text field
		String obj_Search[] = ReadExcel.getObjRepositoryValue("Search");//search icon
		String obj_Add[] = ReadExcel.getObjRepositoryValue("Add");//add button
		String obj_Name[] = ReadExcel.getObjRepositoryValue("Name");//Name field
		String obj_DateIntro[] = ReadExcel.getObjRepositoryValue("DateIntro");
		String obj_DateDisconti[] = ReadExcel.getObjRepositoryValue("DateDisconti");
		String obj_Create[] = ReadExcel.getObjRepositoryValue("Create");
		System.out.println("Going to generic Op");
		Generic.genericInputOperation(Driver, obj_SearchText[0], obj_SearchText[1], "type", product);
		Generic.genericInputOperation(Driver, obj_Search[0], obj_Search[1], "click", "");
		System.out.println("Typing done");
		Thread.sleep(3000);
		/*WebElement wb = (WebElement) Driver.findElements(By.linkText("Acer-Nitro"));
		

		if (wb.isDisplayed()) {
			System.out.println("Element found using text");
			// Driver.close();
		}

		else {
			System.out.println("Element not found-1");*/
			Generic.genericInputOperation(Driver, obj_Add[0], obj_Add[1], "click", "");
			Thread.sleep(3000);

			Generic.genericInputOperation(Driver, obj_Name[0], obj_Name[1], "type", "Acer-Nitro");
			Generic.genericInputOperation(Driver, obj_DateIntro[0], obj_DateIntro[1], "type", "2018-08-15");
			Generic.genericInputOperation(Driver, obj_DateDisconti[0], obj_DateDisconti[1], "type", "");

			Select drpComp = new Select(Driver.findElement(By.name("company")));
			drpComp.selectByVisibleText("Zemmix");
			Thread.sleep(3000);
			Generic.genericInputOperation(Driver, obj_Create[0], obj_Create[1], "click", "");
			Thread.sleep(5000);
			
		Generic.genericInputOperation(Driver, obj_SearchText[0], obj_SearchText[1], "type", product);
		Generic.genericInputOperation(Driver, obj_Search[0], obj_Search[1], "click", "");
		//wb = Driver.findElement(By.xpath("//*[text()='Acer-Nitro']"));
		//wb = Driver.findElements(By.linkText("Acer-Nitro"));
		//if (wb.isDisplayed()) {
		//	System.out.println("Element found using text");
			Driver.quit();

	}
}