package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.TestBase;

public class BankManagerLoginTest extends TestBase{

	@Test
	public void loginAsBankManager() throws InterruptedException {
		
		click("bankManagerLogin_CSS");
		Assert.assertTrue(isElementPresent(By.cssSelector(objRepo.getProperty("addCustomer_CSS"))), "Not able to locate the add customer button");
	}
}
