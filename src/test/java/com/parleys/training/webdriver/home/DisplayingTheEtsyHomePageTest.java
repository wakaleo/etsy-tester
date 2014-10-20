package com.parleys.training.webdriver.home;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class DisplayingTheEtsyHomePageTest {

    WebDriver driver;

    @Before
    public void openEtsyHomePage() {
        driver = new FirefoxDriver();
        driver.get("http://www.etsy.com");
    }

    @Test
    public void shouldShowRecentFavoritesSection() {
        String trendingTitle = driver.findElement(By.cssSelector("#trending h2")).getText();
        assertThat(trendingTitle).isEqualTo("Recent Favourites");
    }

    @After
    public void closeDriver() {
        driver.quit();
    }

}
