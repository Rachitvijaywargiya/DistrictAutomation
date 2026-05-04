package org.district.automation.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtils {

    public static WebElement waitForElementToBeVisible(WebDriver driver, WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementToBeClickable(WebDriver driver, WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForPageLoad(WebDriver driver, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(webDriver -> {
            return String.valueOf(((org.openqa.selenium.JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState")).equals("complete");
        });
    }

    public static WebElement waitForRefreshedAndVisible(WebDriver driver, WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(
                ExpectedConditions.refreshed(
                        ExpectedConditions.visibilityOf(element)
                ));
    }
    public static void clickAndWaitForVisibility(
            WebDriver driver,
            WebElement clickable,
            WebElement visibleAfterClick,
            int timeoutInSeconds) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(clickable)).click();
        wait.until(ExpectedConditions.visibilityOf(visibleAfterClick));
    }

    public static List<WebElement> waitForAllElementsVisible(
            WebDriver driver,
            By locator,
            int timeoutInSeconds) {

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(timeoutInSeconds));

        wait.ignoring(StaleElementReferenceException.class);

        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public static void waitForTextToBePresent(
            WebDriver driver,
            By locator,
            String text,
            int timeout) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(timeout));

        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public static WebElement waitForElementToBeVisible(
            WebDriver driver,
            By locator,
            int timeoutInSeconds) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
