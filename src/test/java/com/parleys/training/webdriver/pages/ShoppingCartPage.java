package com.parleys.training.webdriver.pages;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.*;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * Created by john on 18/11/14.
 */
public class ShoppingCartPage {

    @FindBys({@FindBy(id = "listing-page-cart"), @FindBy(tagName = "h1")})
    WebElement itemName;

    @FindBy(css = ".add-to-cart-form .btn-transaction")
    WebElement addToCartButton;

    @FindBy(css = ".shipping .currency-value")
    WebElement shippingCost;

    @FindBy(css = ".shipping-destination")
    WebElement shippingDestination;

    @FindBy(css = ".estimate-shipping")
    WebElement estimateShippingButton;

    @FindBy(css = ".estimate-shipping-submit")
    WebElement estimateShippingSubmitButton;

    @FindBy(id="estimate-country")
    WebElement destinationCountryList;

    private final WebDriver driver;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getItemName() {
        return itemName.getText();
    }

    public void addToCart() {
        addToCartButton.click();
    }

    public BigDecimal getShippingCost() {
        return new BigDecimal(shippingCost.getText());
    }

    public void shipTo(String destination) {
        estimateShippingButton.click();

        Wait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOf(destinationCountryList));
        new Select(destinationCountryList).selectByVisibleText(destination);

        estimateShippingSubmitButton.click();

        new FluentWait(driver).pollingEvery(500, TimeUnit.MILLISECONDS)
                .withTimeout(5, TimeUnit.SECONDS)
                .withMessage("Waiting for the spinner to disappear")
                .until(newPriceIsDisplayed());
    }

    private Function<WebDriver, Boolean> newPriceIsDisplayed() {
            return new Function<WebDriver, Boolean>() {

                @Override
                public Boolean apply(WebDriver driver) {
                    WebElement spinner = driver.findElement(By.cssSelector(".spinner"));
                    return !spinner.isDisplayed();
                }
            };
    }

    public String getShippingDestination() {
        return shippingDestination.getText();
    }
}
