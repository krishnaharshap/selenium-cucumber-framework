package com.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.automation.stepdefinitions"},
        // Default tag set in cucumber.properties; override via -Dcucumber.filter.tags=@Smoke
        plugin = {
                "pretty",
                "html:test-output/cucumber-reports/cucumber.html",
                "json:test-output/cucumber-reports/cucumber.json",
                "junit:test-output/cucumber-reports/cucumber.xml",
                "rerun:test-output/cucumber-reports/rerun.txt",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true,
        dryRun = false,
        publish = false
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
