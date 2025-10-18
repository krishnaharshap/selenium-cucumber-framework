package com.automation.stepdefinitions;

import com.automation.pages.CheckoutPage;
import com.automation.pages.ProductsPage;
import com.automation.utils.DriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class ProductSteps {

    private static final Logger logger = LogManager.getLogger(ProductSteps.class);
    private ProductsPage productsPage;
    private CheckoutPage checkoutPage;

    public ProductSteps() {
        this.productsPage = new ProductsPage(DriverManager.getDriver());
    }

    @When("user adds Sauce Labs Backpack to cart")
    public void userAddsSauceLabsBackpackToCart() {
        logger.info("Step: User adds Sauce Labs Backpack to cart");
        productsPage.addBackpackToCart();
    }

    @When("user adds Sauce Labs Bike Light to cart")
    public void userAddsSauceLabsBikeLightToCart() {
        logger.info("Step: User adds Sauce Labs Bike Light to cart");
        productsPage.addBikeLightToCart();
    }

    @When("user removes Sauce Labs Backpack from cart")
    public void userRemovesSauceLabsBackpackFromCart() {
        logger.info("Step: User removes Sauce Labs Backpack from cart");
        productsPage.removeBackpackFromCart();
    }

    @When("user clicks on shopping cart icon")
    public void userClicksOnShoppingCartIcon() {
        logger.info("Step: User clicks on shopping cart icon");
        checkoutPage = productsPage.clickShoppingCart();
    }

    @Then("cart badge should show {string} items")
    public void cartBadgeShouldShowItems(String expectedCount) {
        logger.info("Step: Verifying cart badge shows {} items", expectedCount);
        String actualCount = productsPage.getCartItemCount();
        Assert.assertEquals(actualCount, expectedCount,
                "Cart item count mismatch");
    }

    @Then("user should see {int} products on the page")
    public void userShouldSeeProductsOnThePage(int expectedCount) {
        logger.info("Step: Verifying {} products are displayed", expectedCount);
        int actualCount = productsPage.getProductCount();
        Assert.assertEquals(actualCount, expectedCount,
                "Product count mismatch");
    }

    @Then("item should be added to cart successfully")
    public void itemShouldBeAddedToCartSuccessfully() {
        logger.info("Step: Verifying item is added to cart");
        Assert.assertTrue(productsPage.isItemAddedToCart(),
                "Item was not added to cart");
    }
}