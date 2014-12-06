package com.parleys.training.webdriver.search;

import com.parleys.training.webdriver.pages.HomePage;
import net.thucydides.core.annotations.Managed;
import net.thucydides.junit.runners.SerenityRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
public class BrowseByCategorySerenityTest {

    @Managed(driver = "chrome")
    WebDriver driver;

    HomePage homePage;

    @Before
    public void openEtsyHomePage() {
        homePage.open();
    }

    @Test
    public void shouldDisplayBrowsingCategories() {
        List<String> menuEntries = homePage.getMenuEntries();
        assertThat(menuEntries).hasSize(12);
    }

}
