package com.automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverManager {

    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {}

    public static synchronized WebDriver getDriver() {
        if (driver.get() == null) {
            logger.debug("WebDriver not initialized, initializing now");
            initializeDriver();
        } else {
            logger.debug("WebDriver already initialized, returning existing instance");
        }
        return driver.get();
    }

    public static boolean hasDriver() {
        return driver.get() != null;
    }

    private static void initializeDriver() {
        String browser = ConfigReader.getBrowser();
        boolean headless = ConfigReader.isHeadless();
        logger.info("Initializing {} driver (headless={})", browser, headless);

        WebDriver webDriver;
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver(buildChromeOptions(headless));
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver(buildFirefoxOptions(headless));
                if (!headless) {
                    webDriver.manage().window().maximize();
                }
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver(buildEdgeOptions(headless));
                break;

            default:
                logger.error("Invalid browser specified: {}", browser);
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.set(webDriver);
        logger.info("{} driver initialized successfully", browser);
    }

    private static ChromeOptions buildChromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        // Required for Linux / Docker / CI environments
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        } else {
            options.addArguments("--start-maximized");
        }
        return options;
    }

    private static FirefoxOptions buildFirefoxOptions(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("-headless");
            options.addArguments("--width=1920");
            options.addArguments("--height=1080");
        }
        return options;
    }

    private static EdgeOptions buildEdgeOptions(boolean headless) {
        EdgeOptions options = new EdgeOptions();
        // Required for Linux / Docker / CI environments
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        } else {
            options.addArguments("--start-maximized");
        }
        return options;
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            logger.info("Quitting driver");
            driver.get().quit();
            driver.remove();
        }
    }
}
