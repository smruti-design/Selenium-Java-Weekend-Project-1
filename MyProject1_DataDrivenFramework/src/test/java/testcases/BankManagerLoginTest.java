package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.TestBase;

public class BankManagerLoginTest extends TestBase{

	@Test
	public void loginAsBankManager() throws InterruptedException {
		
		driver.findElement(By.cssSelector(objRepo.getProperty("bankManagerLogin"))).click();
		logger.info("Clicked on the bankManagerLogin Button");
		Assert.assertTrue(isElementPresent(By.cssSelector(objRepo.getProperty("addCustomer"))), "Not able to locate the add customer button");
	}
}
