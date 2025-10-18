package com.automation.stepdefinitions;

import com.automation.pages.CheckoutPage;
import com.automation.utils.DriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class CheckoutSteps {

    private static final Logger logger = LogManager.getLogger(CheckoutSteps.class);
    private CheckoutPage checkoutPage;

    public CheckoutSteps() {
        this.checkoutPage = new CheckoutPage(DriverManager.getDriver());
    }

    @Then("user should see {int} items in the cart")
    public void userShouldSeeItemsInTheCart(int expectedCount) {
        logger.info("Step: Verifying {} items in cart", expectedCount);
        int actualCount = checkoutPage.getCartItemsCount();
        Assert.assertEquals(actualCount, expectedCount,
                "Cart items count mismatch");
    }

    @When("user clicks on checkout button")
    public void userClicksOnCheckoutButton() {
        logger.info("Step: User clicks on checkout button");
        checkoutPage.clickCheckout();
    }

    @When("user enters first name {string}")
    public void userEntersFirstName(String firstName) {
        logger.info("Step: User enters first name: {}", firstName);
        checkoutPage.enterCheckoutInformation(firstName, "", "");
    }

    @When("user enters checkout information with first name {string}, last name {string}, and postal code {string}")
    public void userEntersCheckoutInformation(String firstName, String lastName, String postalCode) {
        logger.info("Step: User enters checkout information");
        checkoutPage.enterCheckoutInformation(firstName, lastName, postalCode);
    }

    @When("user clicks continue button")
    public void userClicksContinueButton() {
        logger.info("Step: User clicks continue button");
        checkoutPage.clickContinue();
    }

    @When("user clicks finish button")
    public void userClicksFinishButton() {
        logger.info("Step: User clicks finish button");
        checkoutPage.clickFinish();
    }

    @When("user completes the checkout with first name {string}, last name {string}, and postal code {string}")
    public void userCompletesTheCheckout(String firstName, String lastName, String postalCode) {
        logger.info("Step: User completes checkout process");
        checkoutPage.completeCheckout(firstName, lastName, postalCode);
    }

    @Then("order should be confirmed successfully")
    public void orderShouldBeConfirmedSuccessfully() {
        logger.info("Step: Verifying order confirmation");
        Assert.assertTrue(checkoutPage.isOrderConfirmed(),
                "Order was not confirmed successfully");
    }

    @Then("user should see confirmation message {string}")
    public void userShouldSeeConfirmationMessage(String expectedMessage) {
        logger.info("Step: Verifying confirmation message");
        String actualMessage = checkoutPage.getConfirmationMessage();
        Assert.assertEquals(actualMessage, expectedMessage,
                "Confirmation message mismatch");
    }

    @Then("cart page should be displayed")
    public void cartPageShouldBeDisplayed() {
        logger.info("Step: Verifying cart page is displayed");
        Assert.assertTrue(checkoutPage.isCartPageDisplayed(),
                "Cart page is not displayed");
    }
}