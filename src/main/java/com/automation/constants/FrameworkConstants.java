package com.automation.constants;

public final class FrameworkConstants {

    private FrameworkConstants() {}

    private static final String PROJECT_PATH = System.getProperty("user.dir");

    public static final String CONFIG_FILE_PATH = PROJECT_PATH + "/src/main/resources/config/config.properties";
    public static final String TEST_DATA_PATH = PROJECT_PATH + "/src/test/resources/testdata/saucedemo-test-data.properties";
    public static final String FEATURE_FILES_PATH = PROJECT_PATH + "/src/test/resources/features";

    public static final String EXTENT_REPORT_PATH = PROJECT_PATH + "/test-output/extent-reports/";
    public static final String ALLURE_RESULTS_PATH = PROJECT_PATH + "/test-output/allure-results/";
    public static final String SCREENSHOT_PATH = PROJECT_PATH + "/test-output/screenshots/";

    public static final int EXPLICIT_WAIT_TIMEOUT = 20;
    public static final int PAGE_LOAD_TIMEOUT = 30;
    public static final int IMPLICIT_WAIT_TIMEOUT = 10;

    public static final String EXTENT_REPORT_NAME = "TestExecutionReport.html";
    public static final String EXTENT_DOCUMENT_TITLE = "Automation Test Results";
    public static final String EXTENT_REPORT_TITLE = "E2E Test Execution Report";
}
