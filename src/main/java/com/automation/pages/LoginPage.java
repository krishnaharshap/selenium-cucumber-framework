package com.automation.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    // Page Elements using Page Factory
    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

    @FindBy(className = "login_logo")
    private WebElement appLogo;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify login page is displayed")
    public boolean isLoginPageDisplayed() {
        logger.info("Verifying login page is displayed");
        try {
            wait.until(ExpectedConditions.visibilityOf(appLogo));
            wait.until(ExpectedConditions.visibilityOf(loginButton));
            return appLogo.isDisplayed() && loginButton.isDisplayed();
        } catch (Exception e) {
            logger.error("Login page not displayed: {}", e.getMessage());
            return false;
        }
    }

    @Step("Enter username: {username}")
    public LoginPage enterUsername(String username) {
        logger.info("Entering username: {}", username);
        waitHelper.waitForElementVisible(usernameField);
        sendKeys(usernameField, username);
        return this;
    }

    @Step("Enter password")
    public LoginPage enterPassword(String password) {
        logger.info("Entering password");
        waitHelper.waitForElementVisible(passwordField);
        sendKeys(passwordField, password);
        return this;
    }

    @Step("Click login button")
    public ProductsPage clickLoginButton() {
        logger.info("Clicking login button");
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        jsClick(loginButton);
        // Login can fail intentionally (invalid credentials, locked-out user), leaving
        // the browser on the login page, so we can't assert a URL change here. Callers
        // verify the outcome themselves via isProductsPageDisplayed() or
        // isErrorMessageDisplayed(), both of which wait on their own.
        return new ProductsPage(driver);
    }

    @Step("Login with credentials - Username: {username}")
    public ProductsPage login(String username, String password) {
        logger.info("Performing login with username: {}", username);
        enterUsername(username);
        enterPassword(password);
        return clickLoginButton();
    }

    @Step("Get error message text")
    public String getErrorMessage() {
        logger.info("Getting error message");
        waitHelper.waitForElementVisible(errorMessage);
        return getText(errorMessage);
    }

    @Step("Verify error message is displayed")
    public boolean isErrorMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}