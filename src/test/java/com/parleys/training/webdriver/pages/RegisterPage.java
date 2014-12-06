package com.parleys.training.webdriver.pages;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.webelements.RadioButtonGroup;
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

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

/**
 * Created by john on 23/11/14.
 */
public class RegisterPage extends PageObject {

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

    public void waitForRegistrationPanel() {
        waitFor("#register_button");
    }

    public void setFirstName(String value) {
        firstName.sendKeys(value);
    }

    public void setLastName(String value) {
        lastName.sendKeys(value);
    }

    public void setGender(String value) {
        inRadioButtonGroup("gender").selectByValue(value);
    }

    public void register() {
        registerButton.click();
    }

    public List<String> getErrorMessages() {
        return getDriver().findElements(By.cssSelector(".inline-input-error-message"))
                .stream()
                .map(element -> element.getText())
                .collect(Collectors.toList());
    }
}
