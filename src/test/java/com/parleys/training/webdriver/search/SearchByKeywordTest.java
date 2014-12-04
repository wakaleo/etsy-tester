package com.parleys.training.webdriver.search;

import com.opencsv.CSVReader;
import com.parleys.training.webdriver.pages.HomePage;
import com.parleys.training.webdriver.pages.ItemDetailsPage;
import com.parleys.training.webdriver.pages.SearchResultsPage;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class SearchByKeywordTest {

    WebDriver driver;

    String[] SSL_OPTIONS = {"--ssl-protocol=any"};

    @Before
    public void openEtsyHomePage() {
        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, SSL_OPTIONS);
        driver = new PhantomJSDriver(capabilities);
        driver.get("http://www.etsy.com");
    }

    @After
    public void closeDriver() {
        driver.quit();
    }

    @Test
    @Parameters({"wool", "cotton", "silk"})
    public void shouldFindMatchingItemsWhenSearchingByKeyword(String keyword) {
        driver.get("http://www.etsy.com");
        driver.findElement(By.id("search-query")).sendKeys(keyword);
        driver.findElement(By.cssSelector("button[value='Search']")).click();
        driver.findElements(By.cssSelector(".listing-card")).stream()
                .forEach(element -> assertThat(element.getText().contains(keyword)));
    }

    @Test
    @Parameters(method="loadSearchTestData")
    public void shouldFindMatchingItemsWhenSearchingByLotsOfKeywords(String keyword) {
        driver.get("http://www.etsy.com");
        driver.findElement(By.id("search-query")).sendKeys(keyword);
        driver.findElement(By.cssSelector("button[value='Search']")).click();
        driver.findElements(By.cssSelector(".listing-card")).stream()
                .forEach(element -> assertThat(element.getText().contains(keyword)));
    }

    private Object[] loadSearchTestData() throws IOException {
        CSVReader reader = new CSVReader(new FileReader("src/test/resources/searchdata.csv"));
        return reader.readAll().toArray(new Object[0]);
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
