package com.automation.utils;

import com.automation.constants.FrameworkConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class TestDataReader {

    private static final Logger logger = LogManager.getLogger(TestDataReader.class);
    private static final Properties TEST_DATA = loadTestData();

    private TestDataReader() {}

    public static String resolve(String valueOrKey) {
        if (valueOrKey == null || valueOrKey.isBlank()) {
            return valueOrKey;
        }
        return TEST_DATA.getProperty(valueOrKey, valueOrKey).trim();
    }

    public static String getRequired(String key) {
        String value = TEST_DATA.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Missing required test data key: " + key);
        }
        return value.trim();
    }

    private static Properties loadTestData() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(FrameworkConstants.TEST_DATA_PATH)) {
            properties.load(fis);
            logger.info("Test data loaded from {}", FrameworkConstants.TEST_DATA_PATH);
            return properties;
        } catch (IOException e) {
            logger.error("Failed to load test data file: {}", e.getMessage());
            throw new RuntimeException("Test data file not found at: " + FrameworkConstants.TEST_DATA_PATH, e);
        }
    }
}
