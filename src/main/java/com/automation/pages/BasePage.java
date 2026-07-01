package com.automation.pages;

import com.automation.utils.ConfigReader;
import com.automation.utils.WaitHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected final Logger logger = LogManager.getLogger(this.getClass());
    protected WebDriver driver;
    protected WaitHelper waitHelper;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()));
        PageFactory.initElements(driver, this);
    }

    protected void click(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            logger.info("Clicked on element: {}", locator);
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            logger.warn("Element click intercepted for {}, retrying with JavaScript", locator);
            WebElement element = driver.findElement(locator);
            jsClick(element);
        }
    }
    
    protected void click(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            logger.info("Clicked on element");
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            logger.warn("Element click intercepted, retrying with JavaScript click");
            jsClick(element);
        }
    }

    protected void sendKeys(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
        logger.info("Entered text '{}' in element: {}", text, locator);
    }

    protected void sendKeys(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
        logger.info("Entered text: {}", text);
    }

    protected String getText(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        String text = element.getText();
        logger.info("Retrieved text '{}' from element: {}", text, locator);
        return text;
    }

    protected String getText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        String text = element.getText();
        logger.info("Retrieved text: {}", text);
        return text;
    }

    protected boolean isElementDisplayed(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        logger.info("Scrolled to element");
    }

    protected void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
        logger.info("JavaScript click performed");
    }

    /**
     * Sets a field's value via the native property setter and dispatches input/change
     * events, instead of WebDriver's sendKeys. Some React-controlled inputs (e.g.
     * SauceDemo's checkout form) don't register keystrokes sent through WebDriver's
     * native key-event dispatch in headless Chrome, silently dropping them; the value
     * setter bypasses that and updates React's internal state directly.
     */
    protected void jsSendKeys(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script =
                "const el = arguments[0], val = arguments[1];" +
                "const proto = Object.getPrototypeOf(el);" +
                "const setter = Object.getOwnPropertyDescriptor(proto, 'value').set;" +
                "setter.call(el, val);" +
                "el.dispatchEvent(new Event('input', { bubbles: true }));" +
                "el.dispatchEvent(new Event('change', { bubbles: true }));";
        js.executeScript(script, element, text);
        logger.info("Entered text via JS setter: {}", text);
    }

    public String getPageTitle() {
        String title = driver.getTitle();
        logger.info("Page title: {}", title);
        return title;
    }

    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        logger.info("Current URL: {}", url);
        return url;
    }
}
