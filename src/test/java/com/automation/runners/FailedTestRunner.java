package com.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "@test-output/cucumber-reports/rerun.txt",
        glue = {"com.automation.stepdefinitions"},
        plugin = {
                "pretty",
                "html:test-output/cucumber-reports/failed-cucumber.html",
                "json:test-output/cucumber-reports/failed-cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "rerun:test-output/cucumber-reports/rerun.txt"
        },
        monochrome = true
)
public class FailedTestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
