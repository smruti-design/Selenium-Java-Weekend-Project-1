package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


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
					
		}
		
	}
	
	@AfterSuite
	public void tearDown() {
		if(driver != null) {			
			driver.quit();
			logger.info("Quiting the chrome driver");
		}
	}
}
