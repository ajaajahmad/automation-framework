package com.automation.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.base.BasePage;

public class CartPage extends BasePage {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	@FindBy(css = ".totalRow button")
	WebElement checkoutButton;

	public boolean verifyProducDisplay(String productName) {
		boolean match = cartProducts.stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
		return match;
	}

	public void goToCheckout() {
		checkoutButton.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
	}

}
