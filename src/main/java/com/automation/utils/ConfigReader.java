package com.automation.utils;

import com.automation.constants.FrameworkConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static final Properties properties = loadConfig();

    /**
     * Loads base config.properties then overlays the active environment file on top,
     * so environment-specific values always win over base defaults.
     */
    private static Properties loadConfig() {
        Properties props = new Properties();

        // 1. Load base defaults
        try (FileInputStream fis = new FileInputStream(FrameworkConstants.CONFIG_FILE_PATH)) {
            props.load(fis);
            logger.info("Base config loaded from {}", FrameworkConstants.CONFIG_FILE_PATH);
        } catch (IOException e) {
            throw new RuntimeException("Base config not found: " + FrameworkConstants.CONFIG_FILE_PATH, e);
        }

        // 2. Overlay environment-specific config (e.g. environments/qa.properties)
        java.io.File envFile = new java.io.File(FrameworkConstants.ENV_CONFIG_FILE_PATH);
        if (envFile.exists()) {
            try (FileInputStream fis = new FileInputStream(envFile)) {
                props.load(fis);
                logger.info("Environment config '{}' loaded and overlaid", FrameworkConstants.ENVIRONMENT);
            } catch (IOException e) {
                logger.warn("Could not load env config {}: {}", FrameworkConstants.ENV_CONFIG_FILE_PATH, e.getMessage());
            }
        } else {
            logger.warn("No environment config found for '{}', using base config only", FrameworkConstants.ENVIRONMENT);
        }

        return props;
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
