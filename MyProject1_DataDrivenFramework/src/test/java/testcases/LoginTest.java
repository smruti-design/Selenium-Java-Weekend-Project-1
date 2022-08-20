package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.base.TestBase;

public class LoginTest extends TestBase{

	@Test
	public void loginAsBankManager() throws InterruptedException {
		
		driver.findElement(By.cssSelector(objRepo.getProperty("bankManagerLogin"))).click();
		Thread.sleep(10000);
	}
}
