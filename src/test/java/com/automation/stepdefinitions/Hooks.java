package com.automation.stepdefinitions;

import com.automation.utils.ConfigReader;
import com.automation.utils.DriverManager;
import com.automation.utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);
    private WebDriver driver;

    @Before
    public void setUp(Scenario scenario) {
        logger.info("========================================");
        logger.info("Starting Scenario: {}", scenario.getName());
        logger.info("========================================");

        driver = DriverManager.getDriver();

        // Add explicit page load timeout
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        // Navigate to URL
        driver.get(ConfigReader.getUrl());
        logger.info("Navigated to URL: {}", ConfigReader.getUrl());

        // Wait for page load without hard-coded sleep
        waitHelper.waitForPageLoad();
        }

    @After
    public void tearDown(Scenario scenario) {
        logger.info("========================================");
        logger.info("Scenario Status: {}", scenario.getStatus());
        logger.info("========================================");

        if (scenario.isFailed()) {
            logger.error("Scenario Failed: {}", scenario.getName());

            // Capture screenshot for failed scenarios
            byte[] screenshot = ScreenshotUtil.captureScreenshotAsBytes(driver);

            // Attach to Cucumber report
            scenario.attach(screenshot, "image/png", scenario.getName());

            // Attach to Allure report
            Allure.addAttachment(scenario.getName(), new ByteArrayInputStream(screenshot));

            logger.info("Screenshot captured for failed scenario");
        }

        DriverManager.quitDriver();
        logger.info("Browser closed successfully");
    }
}
