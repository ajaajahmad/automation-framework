package com.automation.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.automation.pages.LandingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver intializeDriver() throws IOException {

		String filePath = System.getProperty("user.dir") + "//src//main//resources//GlobalData.properties";

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(filePath);
		prop.load(fis);
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			// FireFox
		} else if (browserName.equalsIgnoreCase("edge")) {
			// Edge
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		return driver;
	}

	@BeforeMethod
	public LandingPage setUp() throws IOException {
		driver = intializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();

		return landingPage;
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}
