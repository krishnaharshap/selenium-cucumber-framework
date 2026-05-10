package com.automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "inventory_item")
    private List<WebElement> productItems;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackpackButton;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    private WebElement addBikeLightButton;

    @FindBy(id = "remove-sauce-labs-backpack")
    private WebElement removeBackpackButton;

    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartIcon;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify products page is displayed")
    public boolean isProductsPageDisplayed() {
        logger.info("Verifying products page is displayed");
        return pageTitle.isDisplayed() && getText(pageTitle).equals("Products");
    }

    @Step("Get page title")
    public String getPageTitleText() {
        return getText(pageTitle);
    }

    @Step("Get total number of products")
    public int getProductCount() {
        try {
            // Wait for products to be visible
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("inventory_item")));
            int count = productItems.size();
            if (count == 0) {
                logger.warn("No products found on the page");
                throw new RuntimeException("Products not found on Products page");
            }
            logger.info("Total products found: {}", count);
            return count;
        } catch (Exception e) {
            logger.error("Failed to get product count: {}", e.getMessage());
            throw new RuntimeException("Unable to retrieve product count", e);
        }
    }

    @Step("Add Sauce Labs Backpack to cart")
    public ProductsPage addBackpackToCart() {
        logger.info("Adding Sauce Labs Backpack to cart");
        click(addBackpackButton);
        return this;
    }

    @Step("Add Sauce Labs Bike Light to cart")
    public ProductsPage addBikeLightToCart() {
        logger.info("Adding Sauce Labs Bike Light to cart");
        click(addBikeLightButton);
        return this;
    }

    @Step("Remove Sauce Labs Backpack from cart")
    public ProductsPage removeBackpackFromCart() {
        logger.info("Removing Sauce Labs Backpack from cart");
        click(removeBackpackButton);
        return this;
    }

    @Step("Get cart item count")
    public String getCartItemCount() {
        try {
            String count = getText(cartBadge);
            logger.info("Cart items count: {}", count);
            return count;
        } catch (Exception e) {
            logger.info("Cart is empty");
            return "0";
        }
    }

    @Step("Click shopping cart icon")
    public CheckoutPage clickShoppingCart() {
        logger.info("Clicking shopping cart icon");
        click(shoppingCartIcon);
        return new CheckoutPage(driver);
    }

    @Step("Verify item is added to cart")
    public boolean isItemAddedToCart() {
        try {
            return cartBadge.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
