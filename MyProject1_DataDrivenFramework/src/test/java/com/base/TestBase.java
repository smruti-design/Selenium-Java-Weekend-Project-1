package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.utilities.ExcelReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	/*
	 * Initialise the below things
	 * Webdriver
	 * Properties
	 * Logs
	 * ExtentReport
	 * DB
	 * Excel
	 * Mail
	 */
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties objRepo = new Properties();
	public static FileInputStream fis;
	public static Logger logger = LogManager.getLogger(TestBase.class);
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"//src//test//resources//excel//testdata.xlsx");
	public static WebDriverWait wait;
	public static WebElement dropdown;
	
	@BeforeSuite
	public void setUp() {
		
		if(driver == null) {
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//properties//Config.properties");
			} catch (FileNotFoundException e) {				
				e.printStackTrace();
			}		
			try {
				config.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//properties//ObjectRepo.properties");
			} catch (FileNotFoundException e) {				
				e.printStackTrace();
			}			
			try {
				objRepo.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(config.getProperty("browser").equals("chrome")) {
				//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//src//test//resources//executables//chromedriver.exe");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
			
			driver.get(config.getProperty("testsiteurl"));
			logger.info("Opening the test site");
			driver.manage().window().maximize();
			logger.info("Maximizing the window size");
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitlyWait")), TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);
					
		}
		
	}
	
	public void click(String locator) {
		if(locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(objRepo.getProperty(locator))).click();
		}
		logger.info("clicked on "+locator);
	}
	
	public void type(String locator, String value) {
		if(locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(objRepo.getProperty(locator))).sendKeys(value);
		}
		logger.info("Entered the values "+ value + " on "+locator);
	}
	
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		}catch(NoSuchElementException e) {
			return false;
		}
	}
	
	public void select(String locator, String value) {
		if(locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(objRepo.getProperty(locator)));
		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		logger.info("Select on the dropdown value "+ value);
	}
	
	@AfterSuite
	public void tearDown() {
		if(driver != null) {			
			driver.quit();
			logger.info("Quiting the chrome driver");
		}
	}
}
