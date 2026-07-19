package com.automation.stepdefinitions;

import com.automation.context.ScenarioContext;
import com.automation.listeners.ExtentListener;
import com.automation.utils.ConfigReader;
import com.automation.utils.DriverManager;
import com.automation.utils.ScreenshotUtil;
import com.automation.utils.WaitHelper;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.util.Base64;

public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);
    private final ScenarioContext ctx;

    public Hooks(ScenarioContext ctx) {
        this.ctx = ctx;
    }

    @Before
    public void setUp(Scenario scenario) {
        logger.info("========================================");
        logger.info("Starting Scenario: {}", scenario.getName());
        logger.info("========================================");

        if (isApiScenario(scenario)) {
            logger.info("Skipping WebDriver setup for API scenario: {}", scenario.getName());
            return;
        }

        WebDriver driver = DriverManager.getDriver();
        ctx.initialise(driver);

        driver.get(ConfigReader.getUrl());
        logger.info("Navigated to: {}", ConfigReader.getUrl());
        new WaitHelper(driver).waitForPageLoad();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (isApiScenario(scenario)) {
            logger.info("Skipping WebDriver teardown for API scenario: {}", scenario.getName());
            return;
        }

        try {
            if (scenario.isFailed() && ctx.isInitialised()) {
                logger.error("Scenario FAILED: {}", scenario.getName());
                byte[] screenshot = ScreenshotUtil.captureScreenshotAsBytes(ctx.getDriver());
                if (screenshot.length > 0) {
                    Allure.addAttachment("Failed Screenshot", "image/png",
                            new ByteArrayInputStream(screenshot), "png");

                    ExtentTest extentTest = ExtentListener.getCurrentTest();
                    if (extentTest != null) {
                        extentTest.fail("Screenshot on failure")
                                .addScreenCaptureFromBase64String(Base64.getEncoder().encodeToString(screenshot));
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error during screenshot capture: {}", e.getMessage());
        } finally {
            try {
                DriverManager.quitDriver();
                logger.info("Driver quit successfully");
            } catch (Exception e) {
                logger.error("Error quitting driver: {}", e.getMessage());
            }
        }
    }

    private boolean isApiScenario(Scenario scenario) {
        return scenario.getSourceTagNames().contains("@API");
    }
}
