package com.automation.stepdefinitions;

import com.automation.context.ScenarioContext;
import com.automation.utils.TestDataReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class CheckoutSteps {

    private static final Logger logger = LogManager.getLogger(CheckoutSteps.class);
    private final ScenarioContext ctx;

    public CheckoutSteps(ScenarioContext ctx) {
        this.ctx = ctx;
    }

    @Then("user should see {int} items in the cart")
    public void userShouldSeeItemsInTheCart(int expectedCount) {
        logger.info("Step: Verifying {} items in cart", expectedCount);
        Assert.assertEquals(ctx.getCheckoutPage().getCartItemsCount(), expectedCount,
                "Cart items count mismatch");
    }

    @When("user clicks on checkout button")
    public void userClicksOnCheckoutButton() {
        logger.info("Step: User clicks on checkout button");
        ctx.getCheckoutPage().clickCheckout();
    }

    @When("user enters first name {string}")
    public void userEntersFirstName(String firstName) {
        logger.info("Step: User enters first name: {}", firstName);
        ctx.getCheckoutPage().enterCheckoutInformation(TestDataReader.resolve(firstName), "", "");
    }

    @When("user enters checkout information with first name {string}, last name {string}, and postal code {string}")
    public void userEntersCheckoutInformation(String firstName, String lastName, String postalCode) {
        logger.info("Step: User enters checkout information");
        ctx.getCheckoutPage().enterCheckoutInformation(
                TestDataReader.resolve(firstName),
                TestDataReader.resolve(lastName),
                TestDataReader.resolve(postalCode));
    }

    @When("user clicks continue button")
    public void userClicksContinueButton() {
        logger.info("Step: User clicks continue button");
        ctx.getCheckoutPage().clickContinue();
    }

    @When("user clicks finish button")
    public void userClicksFinishButton() {
        logger.info("Step: User clicks finish button");
        ctx.getCheckoutPage().clickFinish();
    }

    @When("user completes the checkout with first name {string}, last name {string}, and postal code {string}")
    public void userCompletesTheCheckout(String firstName, String lastName, String postalCode) {
        logger.info("Step: User completes checkout process");
        ctx.getCheckoutPage().completeCheckout(
                TestDataReader.resolve(firstName),
                TestDataReader.resolve(lastName),
                TestDataReader.resolve(postalCode));
    }

    @Then("order should be confirmed successfully")
    public void orderShouldBeConfirmedSuccessfully() {
        logger.info("Step: Verifying order confirmation");
        Assert.assertTrue(ctx.getCheckoutPage().isOrderConfirmed(), "Order was not confirmed successfully");
    }

    @Then("user should see confirmation message {string}")
    public void userShouldSeeConfirmationMessage(String expectedMessage) {
        logger.info("Step: Verifying confirmation message");
        Assert.assertEquals(ctx.getCheckoutPage().getConfirmationMessage(),
                TestDataReader.resolve(expectedMessage), "Confirmation message mismatch");
    }

    @Then("cart page should be displayed")
    public void cartPageShouldBeDisplayed() {
        logger.info("Step: Verifying cart page is displayed");
        Assert.assertTrue(ctx.getCheckoutPage().isCartPageDisplayed(), "Cart page is not displayed");
    }
}
