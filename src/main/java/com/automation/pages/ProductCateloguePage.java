package com.automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.base.BasePage;

public class ProductCateloguePage extends BasePage {

	WebDriver driver;

	public ProductCateloguePage(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> productList;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By products = By.cssSelector(".mb-3");
	By addToCard = By.cssSelector(".card-body  button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(products);
		return productList;
	}

	public WebElement getProductByName(String productName) {

		WebElement prod = productList.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		return prod;
	}

	public void addProductToCard(String productName) {

		WebElement prod = getProductByName(productName);
		prod.findElement(addToCard).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);

	}

}
