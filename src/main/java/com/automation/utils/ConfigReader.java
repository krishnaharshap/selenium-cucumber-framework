package com.automation.utils;

import com.automation.constants.FrameworkConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static Properties properties;

    static {
        try (FileInputStream fis = new FileInputStream(FrameworkConstants.CONFIG_FILE_PATH)) {
            // Using try-with-resources for automatic cleanup
            properties = new Properties();
            properties.load(fis);
            logger.info("Configuration properties loaded successfully");
        } catch (IOException e) {
            logger.error("Failed to load configuration file: " + e.getMessage());
            throw new RuntimeException("Configuration file not found at: " + FrameworkConstants.CONFIG_FILE_PATH);
        }
    }

    public static String getProperty(String key) {
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue.trim();
        }

        String value = properties.getProperty(key);
        if (value != null) {
            return value.trim();
        }
        logger.warn("Property key '{}' not found in config file", key);
        return null;
    }

    public static String getBrowser() {
        return getProperty("browser");
    }

    public static String getUrl() {
        return getProperty("url");
    }

    public static String getApiBaseUrl() {
        return getProperty("api.base.url");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless"));
    }

    public static int getImplicitWait() {
        String wait = getProperty("implicit.wait");
        return wait != null ? Integer.parseInt(wait) : FrameworkConstants.IMPLICIT_WAIT_TIMEOUT;
    }

    public static int getExplicitWait() {
        String wait = getProperty("explicit.wait");
        if (wait != null && !wait.isEmpty()) {
            try {
                return Integer.parseInt(wait);
            } catch (NumberFormatException e) {
                logger.warn("Invalid explicit.wait value '{}': {}, using default {}", 
                           wait, e.getMessage(), FrameworkConstants.EXPLICIT_WAIT_TIMEOUT);
                return FrameworkConstants.EXPLICIT_WAIT_TIMEOUT;
            }
        }
        return FrameworkConstants.EXPLICIT_WAIT_TIMEOUT;
    }
}
