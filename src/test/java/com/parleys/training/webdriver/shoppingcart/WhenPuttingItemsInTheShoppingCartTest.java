package com.parleys.training.webdriver.shoppingcart;

import com.parleys.training.webdriver.pages.HomePage;
import com.parleys.training.webdriver.pages.ItemDetailsPage;
import com.parleys.training.webdriver.pages.SearchResultsPage;
import com.parleys.training.webdriver.pages.ShoppingCartPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by john on 26/11/14.
 */
public class WhenPuttingItemsInTheShoppingCartTest {

    WebDriver driver;

    @Before
    public void openEtsyHomePage() {
        driver = new ChromeDriver();
        driver.get("http://www.etsy.com");
    }

    @After
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void shouldUpdateShippingPriceForDifferentDestinationCountries() {

        // Given I have displayed item details page
        ItemDetailsPage detailsPage = showDetailsPageForItem(4, "docking station");
        detailsPage.addToCart();

        // When I choose to ship to the UK
        ShoppingCartPage shoppingCartPage = PageFactory.initElements(driver, ShoppingCartPage.class);
        shoppingCartPage.shipTo("United Kingdom");
        String initialDestination = shoppingCartPage.getShippingDestination();
        assertThat(initialDestination).isEqualTo("(To United Kingdom)");

        // And then I choose to ship to Fiji
        shoppingCartPage.shipTo("Fiji");
        String newDestination = shoppingCartPage.getShippingDestination();

        // Then the destination should be updated
        assertThat(newDestination).isEqualTo("(To Fiji)");
    }

    private ItemDetailsPage showDetailsPageForItem(int itemNumber, String keywords) {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.searchForWithKeys(keywords);

        SearchResultsPage searchResultsPage = PageFactory.initElements(driver, SearchResultsPage.class);
        searchResultsPage.selectItem(itemNumber);

        return PageFactory.initElements(driver, ItemDetailsPage.class);
    }
}
