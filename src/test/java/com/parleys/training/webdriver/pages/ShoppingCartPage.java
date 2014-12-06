package com.parleys.training.webdriver.pages;

import com.google.common.base.Function;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * Created by john on 18/11/14.
 */
public class ShoppingCartPage extends PageObject {

    @FindBy(css = ".shipping .currency-value")
    WebElement shippingCost;

    public String getItemName() {
        return $(".listing-page-cart h1").getText();
    }

    public void addToCart() {
        $(".add-to-cart-form").then(".btn-transaction").click();
    }

    public BigDecimal getShippingCost() {
        return new BigDecimal(shippingCost.getText());
    }

    public void shipTo(String destination) {
        $(".estimate-shipping").click();

        $("#estimate-country").selectByVisibleText(destination);

        $(".estimate-shipping-submit").click();

        new FluentWait(getDriver()).pollingEvery(500, TimeUnit.MILLISECONDS)
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
        return $(".shipping-destination").getText();
    }
}
