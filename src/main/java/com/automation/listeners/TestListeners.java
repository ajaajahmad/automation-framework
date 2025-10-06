package com.automation.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.automation.utils.ExtentReportManager;
import com.automation.utils.ScreenshotManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestListeners extends ScreenshotManager implements ITestListener {

	ExtentTest test;
	ExtentReports extent = ExtentReportManager.getReportObject();

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped: " + result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Test Failed but within success percentage: " + result.getName());
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		System.out.println("Test Failed with Timeout: " + result.getName());
		// Treat as normal failure
		onTestFailure(result);
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Test Suite Started: " + context.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test Suite Finished: " + context.getName());
	}

}
