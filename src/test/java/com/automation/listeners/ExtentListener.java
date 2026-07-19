package com.automation.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.automation.utils.ExtentManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentListener implements ITestListener {

    private static final ExtentReports EXTENT = ExtentManager.getInstance();
    private static final ThreadLocal<ExtentTest> TEST = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        TEST.set(EXTENT.createTest(getTestMethodName(result)));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        TEST.get().log(Status.PASS, getTestMethodName(result) + " passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Failure screenshot is attached from the Cucumber @After hook (Hooks.tearDown),
        // which has scenario-level access to the WebDriver; this listener only sees the
        // TestNG method result.
        TEST.get().log(Status.FAIL, getTestMethodName(result) + " failed: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        TEST.get().log(Status.SKIP, getTestMethodName(result) + " skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        EXTENT.flush();
    }

    /** Exposes the current thread's in-progress test node so Hooks can attach scenario-level evidence. */
    public static ExtentTest getCurrentTest() {
        return TEST.get();
    }

    private String getTestMethodName(ITestResult result) {
        return result.getMethod().getConstructorOrMethod().getName();
    }
}
