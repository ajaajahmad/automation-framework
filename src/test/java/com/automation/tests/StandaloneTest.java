package com.automation.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		driver.findElement(By.id("userEmail")).sendKeys("test.user@domain.com");
		driver.findElement(By.id("userPassword")).sendKeys("Asdf@123");
		driver.findElement(By.id("login")).click();

		List<WebElement> productList = driver.findElements(By.cssSelector(".mb-3"));

		productList.stream().filter(product -> product.getText().equals("ZARA COAT 3"));

	}
}
