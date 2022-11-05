package com.qa.swiggy.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

public class TestUtil {
	
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;
	public static long EXPLICIT_WAIT = 5;
	
	public static String RandomStringGenerator() {
	    String generatedString = RandomStringUtils.randomAlphabetic(10);

	    return generatedString;
	}
	
	static Workbook book;
	static Sheet sheet;
	
	public static String TESTDATA_SHEET_PATH = "D:\\SimpliLearn\\Phase 2 Project\\"
			+ "E2EScenarios-POM\\TestDataFiles\\TestData_Location.xlsx";
	
	
	public static Object[][] getTestData(String sheetname) {
		
		FileInputStream file = null;
		
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		}
		catch(InvalidFormatException e) {
			e.printStackTrace();
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetname);
		Object [][] data = new Object [sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for (int i = 0; i<sheet.getLastRowNum(); i++) {
			for(int j = 0; j<sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i+1).getCell(j).toString();
			}
		}
		return data;
		
	}

}
