package com.automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CheckoutPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(className = "complete-header")
    private WebElement orderConfirmationHeader;

    @FindBy(className = "summary_total_label")
    private WebElement totalPrice;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify cart page is displayed")
    public boolean isCartPageDisplayed() {
        logger.info("Verifying cart page is displayed");
        return pageTitle.isDisplayed() && getText(pageTitle).equals("Your Cart");
    }

    @Step("Get cart items count")
    public int getCartItemsCount() {
        int count = cartItems.size();
        logger.info("Cart items count: {}", count);
        return count;
    }

    @Step("Click checkout button")
    public CheckoutPage clickCheckout() {
        logger.info("Clicking checkout button");
        click(checkoutButton);
        return this;
    }

    @Step("Enter checkout information - First Name: {firstName}, Last Name: {lastName}, Postal Code: {postalCode}")
    public CheckoutPage enterCheckoutInformation(String firstName, String lastName, String postalCode) {
        logger.info("Entering checkout information");
        sendKeys(firstNameField, firstName);
        sendKeys(lastNameField, lastName);
        sendKeys(postalCodeField, postalCode);
        return this;
    }

    @Step("Click continue button")
    public CheckoutPage clickContinue() {
        logger.info("Clicking continue button");
        click(continueButton);
        return this;
    }

    @Step("Get total price")
    public String getTotalPrice() {
        String price = getText(totalPrice);
        logger.info("Total price: {}", price);
        return price;
    }

    @Step("Click finish button")
    public CheckoutPage clickFinish() {
        logger.info("Clicking finish button");
        click(finishButton);
        return this;
    }

    @Step("Verify order confirmation")
    public boolean isOrderConfirmed() {
        logger.info("Verifying order confirmation");
        return orderConfirmationHeader.isDisplayed() &&
                getText(orderConfirmationHeader).equals("Thank you for your order!");
    }

    @Step("Get confirmation message")
    public String getConfirmationMessage() {
        return getText(orderConfirmationHeader);
    }

    @Step("Complete checkout process")
    public CheckoutPage completeCheckout(String firstName, String lastName, String postalCode) {
        logger.info("Completing checkout process");
        clickCheckout();
        enterCheckoutInformation(firstName, lastName, postalCode);
        clickContinue();
        clickFinish();
        return this;
    }
}