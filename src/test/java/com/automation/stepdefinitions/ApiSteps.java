package com.automation.stepdefinitions;

import com.automation.context.ApiContext;
import com.automation.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class ApiSteps {

    private static final Logger logger = LogManager.getLogger(ApiSteps.class);

    /**
     * ApiContext is injected by Picocontainer once per scenario.
     * Add cucumber-picocontainer to pom.xml (done in feat/dependency-injection) to activate DI.
     * Without it, Cucumber creates ApiSteps via the no-arg constructor and ApiContext is owned here.
     */
    private final ApiContext apiCtx;

    public ApiSteps(ApiContext apiCtx) {
        this.apiCtx = apiCtx;
    }

    @Given("API base URL is configured")
    public void apiBaseUrlIsConfigured() {
        String baseUrl = ConfigReader.getApiBaseUrl();
        Assert.assertNotNull(baseUrl, "api.base.url is not configured in config.properties");
        apiCtx.setBaseUri(baseUrl);
        RestAssured.baseURI = baseUrl;
        logger.info("API base URI: {}", baseUrl);
    }

    @When("user sends a GET request to {string}")
    public void userSendsGetRequestTo(String endpoint) {
        logger.info("GET {}{}", apiCtx.getBaseUri(), endpoint);
        apiCtx.setLastResponse(
                RestAssured.given()
                        .accept(ContentType.JSON)
                        .when()
                        .get(endpoint)
        );
    }

    @When("user creates a post with title {string} and body {string}")
    public void userCreatesPostWithTitleAndBody(String title, String body) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", title);
        payload.put("body", body);
        payload.put("userId", 1);

        logger.info("POST /posts — title: {}", title);
        apiCtx.setLastResponse(
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON)
                        .body(payload)
                        .when()
                        .post("/posts")
        );
    }

    @Then("API response status code should be {int}")
    public void apiResponseStatusCodeShouldBe(int expectedStatus) {
        Assert.assertTrue(apiCtx.hasResponse(), "No API response captured — did you send a request?");
        int actual = apiCtx.getLastResponse().statusCode();
        logger.info("Response status: {}", actual);
        Assert.assertEquals(actual, expectedStatus, "Unexpected HTTP status code");
    }

    @Then("API response field {string} should be {string}")
    public void apiResponseFieldShouldBe(String jsonPath, String expectedValue) {
        Assert.assertTrue(apiCtx.hasResponse(), "No API response captured");
        Object actual = apiCtx.getLastResponse().jsonPath().get(jsonPath);
        Assert.assertEquals(String.valueOf(actual), expectedValue,
                "Unexpected value at JSON path: " + jsonPath);
    }
}
