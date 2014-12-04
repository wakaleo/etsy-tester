package com.parleys.training.webdriver.pages;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.thoughtworks.selenium.webdriven.commands.WaitForCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * Created by john on 18/11/14.
 */
public class HomePage {

    WebDriver driver;

    @CacheLookup
    @FindBy(id="search-query")
    WebElement searchQuery;

    @FindBy(css = "button[value='Search']")
    WebElement searchButton;

    WebElement register;

    @FindBy(css=".browse-nav a.browse-dropdown-trigger")
    WebElement browseDropdown;

    @FindBy(css=".browse-nav li")
    List<WebElement> dropdownMenuEntries;

    @FindAll({@FindBy(css=".trust1 h3"), @FindBy(css=".trust2 h3"), @FindBy(css=".trust3 h3")})
    List<WebElement> trustArguments;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchFor(String keywords) {
        searchQuery.sendKeys(keywords);
        searchButton.click();
    }

    public void searchForWithKeys(String keywords) {
        searchQuery.clear();
        searchQuery.sendKeys(keywords);
        searchQuery.sendKeys(Keys.ENTER);
    }

    public void register() {
        register.click();
    }

    public List<String> getMenuEntries() {
        browseDropdown.click();

        List<String> menuEntries = Lists.newArrayList();
        for(WebElement dropdownEntry : dropdownMenuEntries) {
            menuEntries.add(dropdownEntry.getText());
        }
        return menuEntries;

    }

    private Function<WebDriver, Boolean> dropdownMenuEntriesAppear() {
        return new Function<WebDriver, Boolean>() {

            @Override
            public Boolean apply(WebDriver input) {
                return !driver.findElements(By.cssSelector(".browse-nav li")).isEmpty();
            }
        };
    }

    public List<String> getTrustArguments() {
        List<String> trustArgumentHeadings = Lists.newArrayList();
        for (WebElement trustArgument : trustArguments) {
            trustArgumentHeadings.add(trustArgument.getText());
        }
        return trustArgumentHeadings;
    }
}
