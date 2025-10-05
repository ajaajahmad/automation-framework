package com.automation.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.automation.base.BaseTest;
import com.automation.pages.CartPage;
import com.automation.pages.CheckoutPage;
import com.automation.pages.ConfirmationPage;
import com.automation.pages.OrderPage;
import com.automation.pages.ProductCateloguePage;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(String email, String password, String productName)
			throws IOException, InterruptedException {

		ProductCateloguePage productCatelogue = landingPage.userLogin(email, password);
		List<WebElement> productList = productCatelogue.getProductList();
		productCatelogue.addProductToCard(productName);
		CartPage cartPage = productCatelogue.goToCardPage();

		boolean match = cartPage.verifyProducDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");

		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String confirmationMessage = confirmationPage.getConfirmationMessage();

		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));

	}

	@Test(dataProvider = "getData", dependsOnMethods = { "submitOrder" })
	public void orderHistory(String email, String password) {
		ProductCateloguePage productCatelogue = landingPage.userLogin(email, password);
		OrderPage orderPage = productCatelogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { "test.user@domain.com", "Asdf@123", "ZARA COAT 3" },
				{ "test.user2@domain.com", "Asdf@123", "ADIDAS ORIGINAL" } };
	}

}
