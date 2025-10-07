package com.automation.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

	public static ExtentReports getReportObject() {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String filePath = System.getProperty("user.dir") + "//reports//extent-reports//ExtentReport_" + timestamp
				+ ".html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results - Automation");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("QA", "Ajaaj Ahmad");

		return extent;
	}
}
