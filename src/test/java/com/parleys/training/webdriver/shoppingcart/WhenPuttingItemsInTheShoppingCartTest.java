package com.parleys.training.webdriver.shoppingcart;

import com.parleys.training.webdriver.pages.HomePage;
import com.parleys.training.webdriver.pages.ItemDetailsPage;
import com.parleys.training.webdriver.pages.SearchResultsPage;
import com.parleys.training.webdriver.pages.ShoppingCartPage;
import net.thucydides.core.annotations.Managed;
import net.thucydides.junit.runners.SerenityRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by john on 26/11/14.
 */
@RunWith(SerenityRunner.class)
public class WhenPuttingItemsInTheShoppingCartTest {

    @Managed
    WebDriver driver;

    HomePage homePage;
    ItemDetailsPage detailsPage;
    ShoppingCartPage shoppingCartPage;
    SearchResultsPage searchResultsPage;

    @Before
    public void openEtsyHomePage() {
        homePage.open();
    }

    @Test
    public void shouldUpdateShippingPriceForDifferentDestinationCountries() {

        // Given I have displayed item details page
        showDetailsPageForItem(4, "docking station");
        detailsPage.addToCart();

        // When I choose to ship to the UK
        shoppingCartPage.shipTo("United Kingdom");
        String initialDestination = shoppingCartPage.getShippingDestination();
        assertThat(initialDestination).isEqualTo("(To United Kingdom)");

        // And then I choose to ship to Fiji
        shoppingCartPage.shipTo("Fiji");
        String newDestination = shoppingCartPage.getShippingDestination();

        // Then the destination should be updated
        assertThat(newDestination).isEqualTo("(To Fiji)");
    }

    private void showDetailsPageForItem(int itemNumber, String keywords) {
       homePage.searchForWithKeys(keywords);
       searchResultsPage.selectItem(itemNumber);
    }
}
