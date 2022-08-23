package testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.utilities.TestUtils;

public class OpenAccountTest extends TestBase{
	
	@Test(dataProviderClass = TestUtils.class,dataProvider="dp")
	public void openAccountTest(String customer, String currency) {
		
		click("openAccount_CSS");
		select("customer_CSS", customer);
		select("currency_CSS", currency);
		
		click("process_CSS");
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		
	}
	
	
}
