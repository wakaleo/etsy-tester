package com.parleys.training.webdriver.pages;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by john on 23/11/14.
 */
public class RegisterPage {

    @FindBy(id="first-name")
    WebElement firstName;

    @FindBy(id="last-name")
    WebElement lastName;

    @FindBy(name="gender")
    List<WebElement> genderButtons;

    @FindBy(id="register_button")
    WebElement registerButton;

    @FindBy(css=".inline-input-error-message")
    List<WebElement> errorMessages;

    @CacheLookup
    @FindBy(id="facebook-register")
    WebElement registerUsingFacebook;

    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForRegistrationPanel() {
        Wait wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.visibilityOf(firstName));
    }

    public void setFirstName(String value) {
        firstName.sendKeys(value);
    }

    public void setLastName(String value) {
        lastName.sendKeys(value);
    }

    public void setGender(String value) {
        for(WebElement genderRadioButton : genderButtons) {
            if (genderRadioButton.getAttribute("value").equals(value)) {
                genderRadioButton.click();
                break;
            }
        }
    }

    public void register() {
        Wait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        registerButton.click();
    }

    public List<String> getErrorMessages() {

        return errorMessages.stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
    }
}
