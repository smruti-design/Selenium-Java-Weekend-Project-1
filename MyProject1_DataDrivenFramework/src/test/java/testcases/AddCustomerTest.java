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
		
		driver.findElement(By.cssSelector(objRepo.getProperty("addCustomer"))).click();
		logger.info("Clicked on the add customer button");
		driver.findElement(By.cssSelector(objRepo.getProperty("firstName"))).sendKeys(firstName);
		logger.info("Entered the first name");
		driver.findElement(By.cssSelector(objRepo.getProperty("lastName"))).sendKeys(lastName);
		logger.info("Entered the last name");
		driver.findElement(By.cssSelector(objRepo.getProperty("postCode"))).sendKeys(postCode);
		logger.info("Entered the post code");
		driver.findElement(By.cssSelector(objRepo.getProperty("addBtn"))).click();
		logger.info("Clicked on the add button");
		
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
