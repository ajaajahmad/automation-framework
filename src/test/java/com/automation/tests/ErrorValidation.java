package com.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;

public class ErrorValidation extends BaseTest {

	@Test
	public void errorValidation() {

		String productName = "ZARA COAT 3";
		landingPage.userLogin("test.user@domain.com", "Asdf@123");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}

}
