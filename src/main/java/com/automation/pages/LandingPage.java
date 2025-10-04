package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.base.BasePage;

public class LandingPage extends BasePage {

	WebDriver driver;

	public LandingPage(WebDriver driver) {

		super(driver);
		// initialization
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPass;

	@FindBy(id = "login")
	WebElement loginButton;

	public ProductCateloguePage userLogin(String email, String password) {

		userEmail.sendKeys(email);
		userPass.sendKeys(password);
		loginButton.click();
		ProductCateloguePage productCatelogue = new ProductCateloguePage(driver);
		return productCatelogue;
	}

	public void goTo() {

		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}

}
