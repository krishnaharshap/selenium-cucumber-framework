package com.automation.stepdefinitions;

import com.automation.pages.LoginPage;
import com.automation.pages.ProductsPage;
import com.automation.utils.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class LoginSteps {

    private static final Logger logger = LogManager.getLogger(LoginSteps.class);
    private LoginPage loginPage;
    private ProductsPage productsPage;

    public LoginSteps() {
        this.loginPage = new LoginPage(DriverManager.getDriver());
    }

    @Given("user is on the login page")
    public void userIsOnTheLoginPage() {
        logger.info("Step: User is on the login page");
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page is not displayed");
    }

    @When("user enters username {string}")
    public void userEntersUsername(String username) {
        logger.info("Step: User enters username: {}", username);
        loginPage.enterUsername(username);
    }

    @When("user enters password {string}")
    public void userEntersPassword(String password) {
        logger.info("Step: User enters password");
        loginPage.enterPassword(password);
    }

    @When("user clicks on login button")
    public void userClicksOnLoginButton() {
        logger.info("Step: User clicks on login button");
        productsPage = loginPage.clickLoginButton();
    }

    @When("user logs in with username {string} and password {string}")
    public void userLogsInWithUsernameAndPassword(String username, String password) {
        logger.info("Step: User logs in with username: {} and password", username);
        productsPage = loginPage.login(username, password);
    }

    @Then("user should be redirected to products page")
    public void userShouldBeRedirectedToProductsPage() {
        logger.info("Step: Verifying user is redirected to products page");
        Assert.assertTrue(productsPage.isProductsPageDisplayed(),
                "User is not redirected to products page");
    }

    @Then("user should see error message {string}")
    public void userShouldSeeErrorMessage(String expectedError) {
        logger.info("Step: Verifying error message is displayed");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message is not displayed");
        String actualError = loginPage.getErrorMessage();
        Assert.assertTrue(actualError.contains(expectedError),
                "Expected error: " + expectedError + " but got: " + actualError);
    }

    @Then("products page title should be {string}")
    public void productsPageTitleShouldBe(String expectedTitle) {
        logger.info("Step: Verifying products page title");
        String actualTitle = productsPage.getPageTitleText();
        Assert.assertEquals(actualTitle, expectedTitle,
                "Page title mismatch");
    }
}