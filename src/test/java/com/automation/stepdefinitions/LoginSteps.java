package com.automation.stepdefinitions;

import com.automation.context.ScenarioContext;
import com.automation.utils.TestDataReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class LoginSteps {

    private static final Logger logger = LogManager.getLogger(LoginSteps.class);
    private final ScenarioContext ctx;

    public LoginSteps(ScenarioContext ctx) {
        this.ctx = ctx;
    }

    @Given("user is on the login page")
    public void userIsOnTheLoginPage() {
        logger.info("Step: User is on the login page");
        Assert.assertTrue(ctx.getLoginPage().isLoginPageDisplayed(), "Login page is not displayed");
    }

    @When("user enters username {string}")
    public void userEntersUsername(String username) {
        String resolved = TestDataReader.resolve(username);
        logger.info("Step: User enters username: {}", resolved);
        ctx.getLoginPage().enterUsername(resolved);
    }

    @When("user enters password {string}")
    public void userEntersPassword(String password) {
        logger.info("Step: User enters password");
        ctx.getLoginPage().enterPassword(TestDataReader.resolve(password));
    }

    @When("user clicks on login button")
    public void userClicksOnLoginButton() {
        logger.info("Step: User clicks on login button");
        ctx.getLoginPage().clickLoginButton();
    }

    @When("user logs in with username {string} and password {string}")
    public void userLogsInWithUsernameAndPassword(String username, String password) {
        String resolved = TestDataReader.resolve(username);
        logger.info("Step: User logs in with username: {}", resolved);
        ctx.getLoginPage().login(resolved, TestDataReader.resolve(password));
    }

    @Then("user should be redirected to products page")
    public void userShouldBeRedirectedToProductsPage() {
        logger.info("Step: Verifying user is redirected to products page");
        Assert.assertTrue(ctx.getProductsPage().isProductsPageDisplayed(),
                "User is not redirected to products page");
    }

    @Then("user should see error message {string}")
    public void userShouldSeeErrorMessage(String expectedError) {
        logger.info("Step: Verifying error message is displayed");
        Assert.assertTrue(ctx.getLoginPage().isErrorMessageDisplayed(), "Error message is not displayed");
        String actualError = ctx.getLoginPage().getErrorMessage();
        String resolvedError = TestDataReader.resolve(expectedError);
        Assert.assertTrue(actualError.contains(resolvedError),
                "Expected error: " + resolvedError + " but got: " + actualError);
    }

    @Then("products page title should be {string}")
    public void productsPageTitleShouldBe(String expectedTitle) {
        logger.info("Step: Verifying products page title");
        Assert.assertEquals(ctx.getProductsPage().getPageTitleText(),
                TestDataReader.resolve(expectedTitle), "Page title mismatch");
    }
}
