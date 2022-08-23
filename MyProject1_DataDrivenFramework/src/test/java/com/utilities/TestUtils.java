package com.utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.base.TestBase;

public class TestUtils extends TestBase{

	@DataProvider(name = "dp")
	public Object[][] getData(Method m){
		
		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		
		Object[][] data = new Object[rows-1][cols];
		
		for(int row = 2; row <= rows; row++) {
			for(int col = 0; col < cols; col++) {
				data[row-2][col] = excel.getCellData(sheetName, col, row);
			}
		}
		return data;
	}
	
}
