package org.apache.maven.plugins.REALM;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;

public class ExcelReadnew {

	//======================Constructor to read file and create a workbook object==================
	public  ExcelReadnew() throws Throwable{
		
		String filePath = Constants.filePath;
		FileInputStream fileStream = new FileInputStream(filePath);
		
		XSSFWorkbook workbook = new XSSFWorkbook(fileStream);
		
		Constants.workbook = workbook;
		//System.out.println("Got the workbook");

	}

	//===============================Method to calculate total number of rows in sheet========================
	public int getrowCount(String sheetName) throws IOException{


		XSSFWorkbook workbook =Constants.workbook;
		XSSFSheet  sheet = Constants.sheet;
	

		int rowCount = -1; 
		int isheetCount =workbook.getNumberOfSheets();
	

		//Getting the sheet and calculating the row count
		for(int isheet = 0;isheet<isheetCount;isheet++){
			String displayedSheetName =workbook.getSheetName(isheet);
			if(displayedSheetName.equalsIgnoreCase(sheetName)){
				sheet = workbook.getSheet(sheetName);
				rowCount = sheet.getLastRowNum();
			}
		}
		
	

		return rowCount;

	}

    //==============================Method to calculate total number of columns in sheet============================
	public int getColumnCount(String sheetName){

		XSSFWorkbook workbook =Constants.workbook;
		XSSFSheet  sheet = Constants.sheet;

		int columnCount =-1;
		int isheetCount =workbook.getNumberOfSheets();

		//Getting the sheet and calculating the column count
		for(int isheet = 0;isheet<isheetCount;isheet++){
			String displayedSheetName =workbook.getSheetName(isheet);
			if(displayedSheetName.equalsIgnoreCase(sheetName)){
				sheet = workbook.getSheet(sheetName);
				columnCount = sheet.getRow(0).getLastCellNum();
			}
		}
		return columnCount;

	}

	//=========================Method to retrieve the column value by passing row value and Column Name===============================
	public String getCellValue(String sheetName,String rowValue, String columnName){


		XSSFWorkbook workbook =Constants.workbook;
		XSSFSheet  sheet = Constants.sheet;
		XSSFRow row = Constants.row;
		int testSceColNum =2;

		int rowCount = -1;
		int columnCount = -1;
		int isheetCount = workbook.getNumberOfSheets();
		String requiredCellValue ="";

		//Getting the sheet and calculating the row count
		for(int isheet = 0;isheet<isheetCount;isheet++){
			String displayedSheetName = workbook.getSheetName(isheet);
			if(displayedSheetName.equalsIgnoreCase(sheetName)){
				sheet = workbook.getSheet(sheetName);
				rowCount = sheet.getLastRowNum();
				columnCount = sheet.getRow(0).getLastCellNum();
				
				//Focusing the row to first row and getting column count by exact match 
				row = sheet.getRow(0);
				for(int iCol = 0;iCol<columnCount;iCol++){
					String displayedColumnName = row.getCell(iCol).getStringCellValue();
					if(displayedColumnName.equalsIgnoreCase(columnName)){
						columnCount = iCol;
						break;
					}  //end of 2nd if

				} // end of 2nd for
				if(columnCount != -1){

					//Getting the required cell value based on test scenario column value(row value) and column count of column name
					for(int iRow =1 ;iRow<rowCount;iRow++){
						row = sheet.getRow(iRow);
						String displayedColumnValue = row.getCell(testSceColNum).getStringCellValue();
						if(displayedColumnValue.equalsIgnoreCase(rowValue)){
							requiredCellValue = row.getCell(columnCount).getStringCellValue();	
							break;
						}// end of  4th if	 
					}//end of 3rd for
					break;
				} // end of 3rd if
				break;
			} // end of 1st if
		} //end of 1st for
		return  requiredCellValue;

	}

	//=================================Method to retrieve column value by passing row number and column name===========================
	public String getCellValue(String sheetName, int rowNumber, String columnName){

		XSSFWorkbook workbook =Constants.workbook;
		XSSFSheet  sheet = Constants.sheet;
		XSSFRow row = Constants.row;

		int columnCount = -1;
		int isheetCount = workbook.getNumberOfSheets();
		String requiredCellValue = "";

		//Getting the sheet and calculating the row count
		for(int isheet = 0;isheet<isheetCount;isheet++){
			String displayedSheetName = workbook.getSheetName(isheet);
			if(displayedSheetName.equalsIgnoreCase(sheetName)){
				sheet = workbook.getSheet(sheetName);
				
				//Focusing the row to first row and getting column count by exact match 
				row = sheet.getRow(0);
				columnCount = row.getLastCellNum();
				
				//Getting the required cell value based on rowNumber and columnName
				for(int iCol = 0;iCol<columnCount;iCol++){
					String displayedcolumnName = row.getCell(iCol).getStringCellValue();
					if(displayedcolumnName.equalsIgnoreCase(columnName)){
						row = sheet.getRow(rowNumber);
						requiredCellValue = row.getCell(iCol).getStringCellValue();
						break;
					}// end of 2nd if

				}// end of 2nd for

				break; 
			}//end of 1st if
		}// end of 1st for

		return requiredCellValue;	   

	}

	//=======================================Method to retrieve column value by row number and column number=========================
	public String getCellValue(String sheetName,int rowNumber,int columnNumber){

		XSSFWorkbook workbook =Constants.workbook;
		XSSFSheet  sheet = Constants.sheet;
		XSSFRow row = Constants.row;

		int isheetCount = workbook.getNumberOfSheets();
		String requiredCellValue ="";

		//Getting the sheet and calculating the row count
		for(int isheet = 0;isheet<isheetCount;isheet++){
			String displayedSheetName = workbook.getSheetName(isheet);
			
			//Getting the required cell value based on rowNumber and column number
			if(displayedSheetName.equalsIgnoreCase(sheetName)){
				sheet = workbook.getSheet(sheetName);
				row = sheet.getRow(rowNumber);
				requiredCellValue = row.getCell(columnNumber-1).getStringCellValue();
				break;
			} // end of 1st if		   

		}//end of 1st for
		return requiredCellValue;   
	}

	//=======================Method to get the object Repository values=========================================
	public String[] getObjRepositoryValue(String objectName){

		//String ObjectRepoSheetName = "ObjectRepository";
		String ObjectRepoSheetName = "reviewRepo";
		XSSFWorkbook workbook = Constants.workbook;
		XSSFSheet sheet = Constants.sheet;
		XSSFRow row= Constants.row;

		int rowCount = -1;
		int isheetCount = workbook.getNumberOfSheets();
		int objectType = 3;
		int objectValue = 4;
		String [] requiredObjRepoCellValue = new String [2];

		//Getting the sheet and calculating the row count
		for(int isheet = 0;isheet<isheetCount;isheet++){
			String displayedSheetName = workbook.getSheetName(isheet);
			if(displayedSheetName.equalsIgnoreCase(ObjectRepoSheetName)){
				sheet = workbook.getSheet(ObjectRepoSheetName);
				rowCount = sheet.getLastRowNum(); 
				
				//Getting the required cell value based on the ObjectName value 
				for(int iRow = 1;iRow<=rowCount;iRow++){
					row = sheet.getRow(iRow);
					String displayedColumnValue = row.getCell(2).getStringCellValue();
					if(displayedColumnValue.equalsIgnoreCase(objectName)){
						requiredObjRepoCellValue[0] = row.getCell(objectType).getStringCellValue();
						requiredObjRepoCellValue[1] = row.getCell(objectValue).getStringCellValue();
						break;					   
					}//end of 2nd if
				}// end of 2nd for
				break;
			}// end of 1st if
		}//end of 1st for

		return requiredObjRepoCellValue;

	}

	}
