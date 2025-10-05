package com.automation.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.automation.base.BaseTest;
import com.automation.pages.CartPage;
import com.automation.pages.CheckoutPage;
import com.automation.pages.ConfirmationPage;
import com.automation.pages.OrderPage;
import com.automation.pages.ProductCateloguePage;
import com.automation.utils.JsonDataReader;

public class SubmitOrderTest extends BaseTest {

	// String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCateloguePage productCatelogue = landingPage.userLogin(input.get("email"), input.get("password"));
		List<WebElement> productList = productCatelogue.getProductList();
		productCatelogue.addProductToCard(input.get("product"));
		CartPage cartPage = productCatelogue.goToCardPage();

		boolean match = cartPage.verifyProducDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");

		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String confirmationMessage = confirmationPage.getConfirmationMessage();

		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));

	}

	@Test(dataProvider = "getData", dependsOnMethods = { "submitOrder" })
	public void orderHistory(HashMap<String, String> input) {
		ProductCateloguePage productCatelogue = landingPage.userLogin(input.get("email"), input.get("password"));
		OrderPage orderPage = productCatelogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(input.get("product")));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		JsonDataReader jsonReader = new JsonDataReader();
		List<HashMap<String, String>> data = jsonReader
				.getJsonDataToMap(System.getProperty("user.dir") + "/src/main/resources/testdata/PurchaseData.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

//	@DataProvider
//	public Object[][] getData() throws IOException {
//
//	return new Object[][]{{"test.user@domain.com","Asdf@123","ZARA COAT 3"},
//
//	{"test.user2@domain.com","Asdf@123","ADIDAS ORIGINAL"}};
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "test.user@domain.com");
//		map1.put("password", "Asdf@123");
//		map1.put("product", "ZARA COAT 3");
//
//		HashMap<String, String> map2 = new HashMap<String, String>();
//		map2.put("email", "test.user2@domain.com");
//		map2.put("password", "Asdf@123");
//		map2.put("product", "ADIDAS ORIGINAL");

}
