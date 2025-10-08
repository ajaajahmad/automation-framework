package com.automation.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryManager implements IRetryAnalyzer {

	int count = 0;
	int maxTry = 1;

	@Override
	public boolean retry(ITestResult result) {

		if (count < maxTry) {
			count++;
			return true;
		}

		return false;
	}

}
