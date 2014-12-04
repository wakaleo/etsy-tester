package com.parleys.training.webdriver.search;

import com.parleys.training.webdriver.pages.HomePage;
import com.parleys.training.webdriver.pages.RegisterPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class WhenRegisteringTest {

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
    public void mustEnterEmailAndPassword() {

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.register();

        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        registerPage.waitForRegistrationPanel();
        registerPage.setFirstName("John");
        registerPage.setLastName("Smart");
        registerPage.setGender("Male");
        registerPage.register();
        assertThat(registerPage.getErrorMessages()).isNotEmpty();
    }

    @Test
    public void shouldBeAbleToRegisterUsingFacebook() {

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.register();

        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        registerPage.waitForRegistrationPanel();
    }

}
