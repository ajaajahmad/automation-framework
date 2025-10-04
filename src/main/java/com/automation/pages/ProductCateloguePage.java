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

	By products = By.cssSelector(".mb-3");

	public List<WebElement> getProductList() {
		waitForElementToAppear(products);
		return productList;
	}

}
