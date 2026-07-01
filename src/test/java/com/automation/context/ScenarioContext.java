package com.automation.context;

import com.automation.pages.CheckoutPage;
import com.automation.pages.LoginPage;
import com.automation.pages.ProductsPage;
import org.openqa.selenium.WebDriver;

/**
 * Shared state carrier for a single Cucumber scenario.
 *
 * Picocontainer creates ONE instance of this class per scenario and injects it into
 * every step-definition class that declares it as a constructor parameter. This
 * eliminates the need for each step class to create its own page objects and removes
 * the risk of two step classes holding different instances of the same page.
 *
 * Lifecycle: created fresh → populated by Hooks.setUp() → read by step classes → torn
 * down by Hooks.tearDown().
 */
public class ScenarioContext {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CheckoutPage checkoutPage;

    /** Called once by Hooks.setUp() after the driver is initialised. */
    public void initialise(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
        this.productsPage = new ProductsPage(driver);
        this.checkoutPage = new CheckoutPage(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public ProductsPage getProductsPage() {
        return productsPage;
    }

    public CheckoutPage getCheckoutPage() {
        return checkoutPage;
    }

    public boolean isInitialised() {
        return driver != null;
    }
}
