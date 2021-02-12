package org.apache.maven.plugins.REALM;

import java.io.File;
import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;

//import com.thoughtworks.selenium.webdriven.commands.Type;

public class Generic {

	public static String JavaScriptPageLoad(WebDriver Driver) {

//		JavascriptExecutor js = ((JavascriptExecutor) Driver); 
//		String pageLoadValue = (String) js.executeScript("function pageLoad(){ return 'Page Successfully Loaded:'}" + " return window.onload = pageLoad()");
		JavascriptExecutor js = ((JavascriptExecutor) Driver);
		String readyState = (String) js.executeScript("return document.readyState");
		System.out.println("Ready State - " + readyState);
		return readyState;
	}

	// =====================Method to wait for the page to load fully by retrieving
	// the page source content and checking for the required text=============
	public static boolean PageLoad(WebDriver Driver, String RequiredText) throws InterruptedException {

		int loopCounter = 0;
		boolean checkFlag = false;
		boolean waitFlag = true;

		while (checkFlag == false) {
			Thread.sleep(1000);
			String allContent = Driver.getPageSource();
			if (allContent.contains(RequiredText)) {
				break;
			}

			loopCounter++;
			if (loopCounter > 200) {
				checkFlag = true;
				waitFlag = false;
			}
		}
		return waitFlag;
	}

	// ===========================Method to wait until the loading icon image become
	// invisble==============================================
	public static boolean loadingIconCheck(WebDriver Driver, String objRepo_value) throws InterruptedException {

		boolean waitloadimageflag = false;
		boolean loadflag = true;
		int loopCounter = 0;

		while (waitloadimageflag == false) {
			Thread.sleep(1000);
			System.out.println("loopcounter=====" + loopCounter);
			Thread.sleep(2000);
			boolean isDisplayed = Driver.findElement(By.xpath(objRepo_value)).isDisplayed();
			Thread.sleep(1000);

			if (isDisplayed == false) {
				waitloadimageflag = true;
				return loadflag;
			}

			String style = Driver.findElement(By.xpath(objRepo_value)).getAttribute("style");
			if (style.contains("none")) {
				waitloadimageflag = true;
				Thread.sleep(2000);
			}
		}
		loopCounter++;

		if (loopCounter > 200) {
			waitloadimageflag = true;
			loadflag = false;
		}
		return loadflag;

	}

	// =====================================Method to perform input operations on
	// Web app page================================
	public static boolean genericInputOperation(WebDriver Driver, String objectType, String objectValue,
			String objectOperation, String objectInputValue) {

		boolean inputOperationflag = true;

		try {
			// System.out.println("Inside generic input");
			// Creating a web element object based on the object type passed
			WebElement genericWb = null;
			if (objectType.equalsIgnoreCase("id")) {
				genericWb = Driver.findElement(By.id(objectValue));
			} else if (objectType.equalsIgnoreCase("xpath")) {
				genericWb = Driver.findElement(By.xpath(objectValue));
			} else if (objectType.equalsIgnoreCase("name")) {
				genericWb = Driver.findElement(By.name(objectValue));
			}

			// Checking for object is displayed or enabled
			if (genericWb.isDisplayed() == false || genericWb.isEnabled() == false) {
				inputOperationflag = false;
				return inputOperationflag;
			}

			// Performing the operation based on the object operation value passed
			if (objectOperation.equalsIgnoreCase("click")) {
				genericWb.click();
				Thread.sleep(2000);
			} else if (objectOperation.equalsIgnoreCase("clear")) {
				genericWb.clear();
			} else if (objectOperation.equalsIgnoreCase("type")) {
				System.out.println("inside type");
				genericWb.sendKeys(objectInputValue);
			} else if (objectOperation.equalsIgnoreCase("Key") || objectOperation.equalsIgnoreCase("Keys")) {
				if (objectInputValue.equalsIgnoreCase("TAB")) {
					genericWb.sendKeys(Keys.TAB);
				} else if (objectInputValue.equalsIgnoreCase("Enter")) {
					genericWb.sendKeys(Keys.ENTER);
				}
			}

		} catch (Exception e) {

			return false;
		}

		return inputOperationflag;

	}

	// ==============================Method to fetch the value of object dispayed on
	// web app page========================================
	public static String genericFetchValue(WebDriver Driver, String objectType, String objectValue, String propertyName,
			String fetchType) {

		boolean fetchValueflag = true;
		String fetchValue = null;

		try {
			// Creating a web element object based on the object type passed
			WebElement genericWb = null;
			if (objectType.equalsIgnoreCase("id")) {
				genericWb = Driver.findElement(By.id(objectValue));
			} else if (objectType.equalsIgnoreCase("xpath")) {
				genericWb = Driver.findElement(By.xpath(objectValue));
			} else if (objectType.equalsIgnoreCase("name")) {
				genericWb = Driver.findElement(By.name(objectValue));
			}

			// Checking for object is displayed or enabled
			if (genericWb.isDisplayed() == false || genericWb.isEnabled() == false) {
				fetchValueflag = false;
			}

			// Fetching the value based on fetch type value passed
			if (fetchValueflag == true) {
				if (fetchType.equalsIgnoreCase("getAttribute")) {
					fetchValue = genericWb.getAttribute(propertyName);
				} else if (fetchType.equalsIgnoreCase("getText")) {
					fetchValue = genericWb.getText();
				}

			}

		} catch (Exception e) {
			// TODO: handle exception

		}
		return fetchValue;

	}

	// ===================================Method to perform select operation web app
	// page=============================================
	public static boolean genericSelectOperation(WebDriver Driver, String objectType, String objectValue,
			String objectSelectType, String ObjectSelectValue) {

		boolean selectOperationflag = true;
		Select genericSel = null;
		try {
			System.out.print("enter into select");
			// Creating a web element object based on the object type passed
			WebElement genericWb = null;
			if (objectType.equalsIgnoreCase("id")) {
				genericWb = Driver.findElement(By.id(objectValue));
			} else if (objectType.equalsIgnoreCase("xpath")) {
				genericWb = Driver.findElement(By.xpath(objectValue));
			} else if (objectType.equalsIgnoreCase("name")) {
				genericWb = Driver.findElement(By.name(objectValue));
			}

			genericSel = new Select(genericWb);

			// Checking for object is displayed or enabled
			if (genericWb.isDisplayed() == false || genericWb.isEnabled() == false) {
				selectOperationflag = false;
			}

			// Performing select operation based on select type values passed
			if (selectOperationflag == true) {
				if (objectSelectType.equalsIgnoreCase("index")) {
					int selectValue = Integer.valueOf(ObjectSelectValue);
					genericSel.selectByIndex(selectValue);
				} else if (objectSelectType.equalsIgnoreCase("value")) {
					String allText = genericWb.getText();
					String splitValues[] = allText.split("\n");
					int size = splitValues.length;
					for (int i = 0; i < size; i++) {
						String splitValue = splitValues[i];
						if (splitValue.equalsIgnoreCase(ObjectSelectValue)) {
							System.out.print(ObjectSelectValue);
							genericSel.selectByValue(ObjectSelectValue);
							String selectedValue = genericSel.getFirstSelectedOption().getText();
							if (selectedValue.equalsIgnoreCase(ObjectSelectValue)) {
								System.out.println("pass");
							} else {
								System.out.println("fail");
							}
							break;
						}
					}

				} else if (objectSelectType.equalsIgnoreCase("text")) {
					List<WebElement> allValues = genericSel.getOptions();
					int allSize = allValues.size();
					for (int isize = 0; isize < allSize; isize++) {
						String selectValue = genericSel.getOptions().get(isize).getText();
						if (selectValue.equalsIgnoreCase(ObjectSelectValue)) {
							genericSel.selectByVisibleText(ObjectSelectValue);
							String selectedValue = genericSel.getFirstSelectedOption().getText();
							if (selectedValue.equalsIgnoreCase(ObjectSelectValue)) {
								System.out.println("pass");
							} else {
								System.out.println("fail");
							}
							break;
						}

					}

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return selectOperationflag;

	}

	// ==============================Method to perform operations after clicking on
	// browse icon=====================================
	public static boolean browseIconClickOperation(WebDriver Driver, String inputValue1, String inputValue2,
			String[] obj_RepoPrjLeadResLabelPath, String[] obj_RepoPrjLeadFilterButton, String[] obj_RepoPrjLeadColPath,
			String[] obj_RepoPrjLeadcheckBox, String[] obj_RepoPrjLeadAddButton) throws InterruptedException {

		boolean prjLeadFlag = false;

		String obj_RepoPrjrowPath = "//tbody[@class='ppm_grid_content']//tr";
		String obj_RepoPrjProdPlatLabelPath = "//div[@class='ppm_filter_section']//td//label[text() ='Platform Family']";
		String obj_RepoLeadResLabelPath = obj_RepoPrjLeadResLabelPath[1];
		String idValue = Driver.findElement(By.xpath(obj_RepoLeadResLabelPath)).getAttribute("for");
		String obj_RepoProjectResId = "//input[@id='" + idValue + "']";
		System.out.println("obj_RepoProjectResId ===" + obj_RepoProjectResId);

		// Calling genericInputOperation function to enter value in the required field
		Generic.genericInputOperation(Driver, "xpath", obj_RepoProjectResId, "type", inputValue1);
		Thread.sleep(3000);

		// Calling genericInputOperation function to enter value in the required field
		// if the input value2 is not empty
		if (!inputValue2.isEmpty()) {
			String platidValue = Driver.findElement(By.xpath(obj_RepoPrjProdPlatLabelPath)).getAttribute("for");
			String obj_RepoProjectPlatId = "//input[@id='" + platidValue + "']";
			System.out.println("obj_RepoProjectResId ===" + obj_RepoProjectResId);
			genericInputOperation(Driver, "xpath", obj_RepoProjectPlatId, "type", inputValue2);
		}

		// Calling genericInputOperation function to click on button
		System.out.println("Before click on filter button--");

		Generic.genericInputOperation(Driver, "xpath", obj_RepoPrjLeadFilterButton[1], "click", "");
		Thread.sleep(4000);

		// Getting the list of filtered values
		List<WebElement> rowWb = Driver.findElements(By.xpath(obj_RepoPrjrowPath));
		int rowCount = rowWb.size();
		for (int iRow = 1; iRow <= rowCount; iRow++) {
			String obj_RepoColPathIter = obj_RepoPrjrowPath + "[" + iRow + "]" + obj_RepoPrjLeadColPath[1];
			WebElement colValueWB = Driver.findElement(By.xpath(obj_RepoColPathIter));
			String displayedResId = colValueWB.getText();
			System.out.println("displayedResName--" + displayedResId);

			// Clicking on check box if displayed value matches the required value
			if (displayedResId.equalsIgnoreCase(inputValue1)) {
				String obj_RepoCheckBox = obj_RepoPrjrowPath + "[" + iRow + "]" + obj_RepoPrjLeadcheckBox[1];
				WebElement checkWb = Driver.findElement(By.xpath(obj_RepoCheckBox));
				checkWb.click();
				Thread.sleep(2000);
				System.out.println("obj_RepoPrjLeadAddButton[1]====" + obj_RepoPrjLeadAddButton[1]);
				String formidValue = Driver.findElement(By.xpath(obj_RepoPrjLeadAddButton[1])).getAttribute("id");
				String obj_RepoAddbutton = obj_RepoPrjLeadAddButton[1] + "[@id= '" + formidValue
						+ "']//div[2]//div//button";
				System.out.println("obj_RepoAddbutton===" + obj_RepoAddbutton);

				// Clicking on Add button
				List<WebElement> addWb = Driver.findElements(By.xpath(obj_RepoAddbutton));
				int count = addWb.size();
				for (int icount = 0; icount < count; icount++) {
					String displayedlist = addWb.get(icount).getText();
					System.out.println("list==" + displayedlist);
					if (displayedlist.equalsIgnoreCase(" Add")) {
						addWb.get(icount).click();
						prjLeadFlag = true;
						break;
					}
				}

			}
		}
		return prjLeadFlag;

	}

	// =======================================Method to capture the screen shot of
	// failed operations========================
	public static String captureScreenShot() throws IOException, Exception {

		long TimeStamp = System.currentTimeMillis();
		String ScreenShotFileName = "SC_" + TimeStamp + ".png";
		String ScreenShotFilePath = Constants.screenshotPath + ScreenShotFileName;
		System.out.println("Inside generic function===" + ScreenShotFilePath);

		String ScreenShotFilePath1 = Constants.baseScreenshotRelativePath + ScreenShotFileName;
		System.out.println("Inside generic function ScreenShotFilePath1===" + ScreenShotFilePath1);

		try {

			Thread.sleep(1000);
			File destinationPath = new File(ScreenShotFilePath);
			TakesScreenshot screenshot = ((TakesScreenshot) Constants.Drivers);
			File srcPath = screenshot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcPath, destinationPath);

		} catch (IOException e) {
			System.out.println("Error in Screenshot Capturing");

		} catch (Exception e) {
			System.out.println("Error in Screenshot Capturing");
		}

		System.out.println(ScreenShotFilePath);
		System.out.println(ScreenShotFilePath1);
		return ScreenShotFilePath;
//      return ScreenShotFilePath1;
	}

	public static boolean genericvalidation(WebDriver Driver, String objectType, String objectValue,
			String objectOperation) {

		boolean inputOperationflag = true;

		try {
			// System.out.println("Inside generic input");
			// Creating a web element object based on the object type passed
			WebElement genericWb = null;
			if (objectType.equalsIgnoreCase("id")) {
				genericWb = Driver.findElement(By.id(objectValue));
			} else if (objectType.equalsIgnoreCase("xpath")) {
				genericWb = Driver.findElement(By.xpath(objectValue));
			} else if (objectType.equalsIgnoreCase("name")) {
				genericWb = Driver.findElement(By.name(objectValue));
			}

			// Checking for object is displayed

			if (objectOperation.equalsIgnoreCase("Displayed")) {
				inputOperationflag = genericWb.isDisplayed();

			} else if (objectOperation.equalsIgnoreCase("Enabled")) {
				inputOperationflag = genericWb.isEnabled();

			} else if (objectOperation.equalsIgnoreCase("Selected")) {
				inputOperationflag = genericWb.isSelected();

			}

		} catch (Exception e) {

			return inputOperationflag;
		}
		return inputOperationflag;

	}

}
