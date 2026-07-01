package com.automation.stepdefinitions;

import com.automation.context.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class ProductSteps {

    private static final Logger logger = LogManager.getLogger(ProductSteps.class);
    private final ScenarioContext ctx;

    public ProductSteps(ScenarioContext ctx) {
        this.ctx = ctx;
    }

    @When("user adds {string} to cart")
    public void userAddsProductToCart(String productName) {
        logger.info("Step: User adds '{}' to cart", productName);
        ctx.getProductsPage().addProductToCart(productName);
    }

    /** Generic step — works for any product name on any e-commerce AUT */
    @When("user adds {string} to cart")
    public void userAddsProductToCart(String productName) {
        logger.info("Step: User adds '{}' to cart", productName);
        productsPage.addProductToCart(productName);
    }

    // SauceDemo-specific convenience steps kept for backward compatibility
    @When("user adds Sauce Labs Backpack to cart")
    public void userAddsSauceLabsBackpackToCart() {
        userAddsProductToCart("Sauce Labs Backpack");
    }

    @When("user adds Sauce Labs Bike Light to cart")
    public void userAddsSauceLabsBikeLightToCart() {
        userAddsProductToCart("Sauce Labs Bike Light");
    }

    @When("user removes Sauce Labs Backpack from cart")
    public void userRemovesSauceLabsBackpackFromCart() {
        logger.info("Step: User removes Sauce Labs Backpack from cart");
        ctx.getProductsPage().removeBackpackFromCart();
    }

    @When("user clicks on shopping cart icon")
    public void userClicksOnShoppingCartIcon() {
        logger.info("Step: User clicks on shopping cart icon");
        ctx.getProductsPage().clickShoppingCart();
    }

    @Then("cart badge should show {string} items")
    public void cartBadgeShouldShowItems(String expectedCount) {
        logger.info("Step: Verifying cart badge shows {} items", expectedCount);
        Assert.assertEquals(ctx.getProductsPage().getCartItemCount(), expectedCount,
                "Cart item count mismatch");
    }

    @Then("user should see {int} products on the page")
    public void userShouldSeeProductsOnThePage(int expectedCount) {
        logger.info("Step: Verifying {} products are displayed", expectedCount);
        Assert.assertEquals(ctx.getProductsPage().getProductCount(), expectedCount,
                "Product count mismatch");
    }

    @Then("item should be added to cart successfully")
    public void itemShouldBeAddedToCartSuccessfully() {
        logger.info("Step: Verifying item is added to cart");
        Assert.assertTrue(ctx.getProductsPage().isItemAddedToCart(), "Item was not added to cart");
    }
}
