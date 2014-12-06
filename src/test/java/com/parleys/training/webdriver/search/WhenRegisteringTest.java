package com.parleys.training.webdriver.search;

import com.parleys.training.webdriver.pages.HomePage;
import com.parleys.training.webdriver.pages.RegisterPage;
import net.thucydides.core.annotations.Managed;
import net.thucydides.junit.runners.SerenityRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
public class WhenRegisteringTest {

    @Managed
    WebDriver driver;

    HomePage homePage;
    RegisterPage registerPage;

    @Before
    public void openEtsyHomePage() {
        homePage.open();
    }

    @Test
    public void mustEnterEmailAndPassword() {

        homePage.register();
        registerPage.waitForRegistrationPanel();
        registerPage.setFirstName("John");
        registerPage.setLastName("Smart");
        registerPage.setGender("Male");
        registerPage.register();
        assertThat(registerPage.getErrorMessages()).isNotEmpty();
    }

    @Test
    public void shouldBeAbleToRegisterUsingFacebook() {
        homePage.register();
        registerPage.waitForRegistrationPanel();
    }

}
