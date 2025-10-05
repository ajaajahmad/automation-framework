package com.automation.tests;

import java.io.IOException;
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
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.pages.CartPage;
import com.automation.pages.CheckoutPage;
import com.automation.pages.ConfirmationPage;
import com.automation.pages.LandingPage;
import com.automation.pages.ProductCateloguePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {

	@Test
	public void submitOrder() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		ProductCateloguePage productCatelogue = landingPage.userLogin("test.user@domain.com", "Asdf@123");
		List<WebElement> productList = productCatelogue.getProductList();
		productCatelogue.addProductToCard(productName);
		CartPage cartPage = productCatelogue.goToCardPage();

		boolean match = cartPage.verifyProducDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");

		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String confirmationMessage = confirmationPage.getConfirmationMessage();

		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));

	}

}
