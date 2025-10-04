package com.automation.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.automation.pages.CartPage;
import com.automation.pages.LandingPage;
import com.automation.pages.ProductCateloguePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest {

	public static void main(String[] args) throws InterruptedException {

		String productName = "ZARA COAT 3";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		ProductCateloguePage productCatelogue = landingPage.userLogin("test.user@domain.com", "Asdf@123");
		List<WebElement> productList = productCatelogue.getProductList();
		productCatelogue.addProductToCard(productName);
		CartPage cartPage = productCatelogue.goToCardPage();

		boolean match = cartPage.verifyProducDisplay(productName);
		Assert.assertTrue(match);
		cartPage.goToCheckout();

		Actions action = new Actions(driver);

		action.sendKeys(driver.findElement(By.cssSelector("S")), "india").build()
				.perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section[class*='ta-results']")));

		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(".actions a")));

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".actions a")));
		driver.findElement(By.cssSelector(".actions a")).click();

		WebElement confirmationText = driver.findElement(By.cssSelector(".hero-primary"));

		Assert.assertTrue(confirmationText.getText().equalsIgnoreCase("Thankyou for the order."));

		Thread.sleep(2000);

		driver.quit();
	}

}
