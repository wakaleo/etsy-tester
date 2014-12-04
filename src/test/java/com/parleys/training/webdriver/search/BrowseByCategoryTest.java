package com.parleys.training.webdriver.search;

import com.parleys.training.webdriver.pages.HomePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BrowseByCategoryTest {

    WebDriver driver;

    String[] SSL_OPTIONS = {"--ssl-protocol=any"};

    @Before
    public void openEtsyHomePage() {

        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, SSL_OPTIONS);
        driver = new PhantomJSDriver(capabilities);
        driver.get("https://www.etsy.com");

    }

    @After
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void shouldDisplayBrowsingCategories() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        List<String> menuEntries = homePage.getMenuEntries();

        assertThat(menuEntries).hasSize(12);
    }

}
