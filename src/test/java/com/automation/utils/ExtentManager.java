package com.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.automation.constants.FrameworkConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class ExtentManager {

    private static final Logger logger = LogManager.getLogger(ExtentManager.class);
    private static final String EXTENT_PROPERTIES_PATH =
            System.getProperty("user.dir") + "/src/test/resources/extent.properties";
    private static final ExtentReports INSTANCE = createInstance();

    private ExtentManager() {}

    public static ExtentReports getInstance() {
        return INSTANCE;
    }

    private static ExtentReports createInstance() {
        Properties props = loadExtentProperties();

        String reportPath = System.getProperty("user.dir")
                + "/test-output/extent-reports/" + FrameworkConstants.EXTENT_REPORT_NAME;

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle(
                props.getProperty("extent.document.title", FrameworkConstants.EXTENT_DOCUMENT_TITLE));
        sparkReporter.config().setReportName(
                props.getProperty("extent.report.name", FrameworkConstants.EXTENT_REPORT_TITLE));
        sparkReporter.config().setTheme(
                "dark".equalsIgnoreCase(props.getProperty("extent.reporter.spark.theme"))
                        ? Theme.DARK
                        : Theme.STANDARD);

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Environment", FrameworkConstants.ENVIRONMENT);
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));

        logger.info("Extent report will be written to {}", reportPath);
        return extent;
    }

    private static Properties loadExtentProperties() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(EXTENT_PROPERTIES_PATH)) {
            props.load(fis);
        } catch (IOException e) {
            logger.warn("extent.properties not found at {}, using FrameworkConstants defaults", EXTENT_PROPERTIES_PATH);
        }
        return props;
    }
}
