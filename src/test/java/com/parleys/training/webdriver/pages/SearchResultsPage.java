package com.parleys.training.webdriver.pages;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by john on 18/11/14.
 */
public class SearchResultsPage extends PageObject {

    public void selectItem(int itemNumber) {
        findAll(".listing-card").get(itemNumber - 1).then("a").click();
    }
}
