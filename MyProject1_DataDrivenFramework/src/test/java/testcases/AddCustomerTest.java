package testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.TestBase;

public class AddCustomerTest extends TestBase{

	@Test(dataProvider="getData")
	public void addCustomer(String firstName, String lastName, String postCode, String alertMessage) {
		
		click("addCustomer_CSS");
		logger.info("Clicked on the add customer button");
		type("firstName_CSS",firstName);
		type("lastName_CSS",lastName);		
		type("postCode_CSS",postCode);
		click("addBtn_CSS");
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(alertMessage));
		alert.accept();
		
	}
	
	@DataProvider
	public Object[][] getData(){
		
		String sheetName = "AddCustomerTest";
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
