package com.automation.tests;

import org.testng.annotations.Test;
import java.io.IOException;
import java.lang.foreign.GroupLayout;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.automation.base.BaseTest;
import com.automation.pages.CartPage;
import com.automation.pages.ProductCateloguePage;

public class ErrorValidationsTest extends BaseTest {

	@Test (groups= {"ErrorValidation"})
	public void loginErrorValidation() {

		String productName = "ZARA COAT 3";
		landingPage.userLogin("test.user@domai.com", "Asdf@123");
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email  password.");
	}

	@Test
	public void productErrorValidation() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		ProductCateloguePage productCatelogue = landingPage.userLogin("test.user@domain.com", "Asdf@123");
		List<WebElement> productList = productCatelogue.getProductList();
		productCatelogue.addProductToCard(productName);
		CartPage cartPage = productCatelogue.goToCardPage();

		boolean match = cartPage.verifyProducDisplay("ZARA COAT 33");
		Assert.assertFalse(match);

	}

}
