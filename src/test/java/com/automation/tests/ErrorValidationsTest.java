package com.automation.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.pages.CartPage;
import com.automation.pages.ProductCateloguePage;

public class ErrorValidationsTest extends BaseTest {

	@Test
	public void loginErrorValidation() {

		String productName = "ZARA COAT 3";
		landingPage.userLogin("test.user@domai.com", "Asdf@123");
		AssertJUnit.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
	}

	@Test
	public void productErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		ProductCateloguePage productCatelogue = landingPage.userLogin("test.user@domain.com", "Asdf@123");
		List<WebElement> productList = productCatelogue.getProductList();
		productCatelogue.addProductToCard(productName);
		CartPage cartPage = productCatelogue.goToCardPage();

		boolean match = cartPage.verifyProducDisplay("ZARA COAT 33");
		AssertJUnit.assertFalse(match);

	}

}
