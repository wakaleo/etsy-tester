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

    @FindBy(css=".listing-card")
    List<WebElement> listingCards;

    public void selectItem(int itemNumber) {
        listingCards.get(itemNumber - 1)
                     .findElement(By.tagName("a")).click();
    }
}
