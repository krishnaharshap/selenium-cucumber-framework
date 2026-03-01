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
        try {
            if (scenario.isFailed()) {
                logger.error("Scenario FAILED: {}", scenario.getName());
                byte[] screenshot = ScreenshotUtil.captureScreenshotAsBytes(driver);
                if (screenshot.length > 0) {
                    Allure.addAttachment("Failed Screenshot", "image/png", 
                                       new ByteArrayInputStream(screenshot), "png");
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
}
