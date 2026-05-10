package com.automation.stepdefinitions;

import com.automation.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class ApiSteps {

    private static final Logger logger = LogManager.getLogger(ApiSteps.class);
    private Response response;

    @Given("API base URL is configured")
    public void apiBaseUrlIsConfigured() {
        String baseUrl = ConfigReader.getApiBaseUrl();
        Assert.assertNotNull(baseUrl, "api.base.url is not configured");
        RestAssured.baseURI = baseUrl;
        logger.info("API base URL configured: {}", baseUrl);
    }

    @When("user sends a GET request to {string}")
    public void userSendsGetRequestTo(String endpoint) {
        logger.info("Sending GET request to {}", endpoint);
        response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get(endpoint);
    }

    @When("user creates a post with title {string} and body {string}")
    public void userCreatesPostWithTitleAndBody(String title, String body) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("title", title);
        payload.put("body", body);
        payload.put("userId", 1);

        logger.info("Sending POST request to /posts");
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post("/posts");
    }

    @Then("API response status code should be {int}")
    public void apiResponseStatusCodeShouldBe(int expectedStatusCode) {
        Assert.assertNotNull(response, "API response is not available");
        Assert.assertEquals(response.statusCode(), expectedStatusCode, "Unexpected API status code");
    }

    @Then("API response field {string} should be {string}")
    public void apiResponseFieldShouldBe(String fieldPath, String expectedValue) {
        Assert.assertNotNull(response, "API response is not available");
        Object actual = response.jsonPath().get(fieldPath);
        String actualValue = String.valueOf(actual);
        Assert.assertEquals(actualValue, expectedValue, "Unexpected value for field: " + fieldPath);
    }
}
