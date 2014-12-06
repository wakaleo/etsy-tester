package com.parleys.training.webdriver.search;

import com.parleys.training.webdriver.pages.HomePage;
import com.parleys.training.webdriver.pages.ItemDetailsPage;
import com.parleys.training.webdriver.pages.SearchResultsPage;
import junitparams.JUnitParamsRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class SearchByKeywordTest {

    WebDriver driver;

    @Before
    public void openEtsyHomePage() {
        driver = new FirefoxDriver();
        driver.get("http://www.etsy.com");
    }

    @After
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void shouldOpenCorrespondingItemFromSearchList() {
        driver.get("http://www.etsy.com");
        driver.findElement(By.id("search-query")).sendKeys("wool");
        driver.findElement(By.cssSelector("button[value='Search']")).click();
        driver.findElements(By.cssSelector(".listing-card")).get(3)
                .findElement(By.tagName("a")).click();
        assertThat(driver.findElement(By.id("listing-page-cart"))
                .findElement(By.tagName("h1")).getText().toLowerCase()).contains("wool");
    }

    @Test
    public void shouldOpenCorrespondingItemFromSearchListUsingPageObjects() {

        driver.get("http://www.etsy.com");

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.searchForWithKeys("wool");

        SearchResultsPage searchResultsPage = PageFactory.initElements(driver, SearchResultsPage.class);
        searchResultsPage.selectItem(4);

        ItemDetailsPage detailsPage = PageFactory.initElements(driver, ItemDetailsPage.class);
        String itemName = detailsPage.getItemName();

        assertThat(itemName.toLowerCase()).contains("wool");
    }


    @Test
    public void searchUsingEnterInsteadOfTheSearchButton() {

        driver.get("http://www.etsy.com");

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.searchForWithKeys("wool");

        SearchResultsPage searchResultsPage = PageFactory.initElements(driver, SearchResultsPage.class);
        searchResultsPage.selectItem(4);

        ItemDetailsPage detailsPage = PageFactory.initElements(driver, ItemDetailsPage.class);
        String itemName = detailsPage.getItemName();

        assertThat(itemName.toLowerCase()).contains("wool");

    }
}
