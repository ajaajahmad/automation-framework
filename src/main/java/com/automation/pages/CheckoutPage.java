package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.automation.base.BasePage;

public class CheckoutPage extends BasePage {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement selectCountry;

	@FindBy(css = ".actions a")
	WebElement submitButton;

	By results = By.cssSelector("section[class*='ta-results']");

	public void selectCountry(String countryName) {
		Actions action = new Actions(driver);
		action.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
	}

	public ConfirmationPage submitOrder() {
		Actions action = new Actions(driver);
		action.scrollToElement(submitButton).perform();
		submitButton.click();
		return new ConfirmationPage(driver);
	}

}
