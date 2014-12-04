package com.parleys.training.webdriver.home;

import com.parleys.training.webdriver.pages.HomePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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

    @Test
    public void whenWeSearchForShoesItshouldDisplayResultsAboutShoes() {
        driver.findElement(By.id("search-query")).sendKeys("shoes", Keys.ENTER);
    }

    @Test
    public void shouldBeAbleToFilterByShippingCountry() {
        driver.findElement(By.id("search-query")).sendKeys("shoes", Keys.ENTER);
        new Select(driver.findElement(By.id("ship-to-select"))).selectByVisibleText("France");
    }

    @Test
    public void shouldBeAbleToRegister() {

        driver.findElement(By.id("register")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("register-tab")));

        driver.findElement(By.name("first_name")).sendKeys("John");
        driver.findElement(By.name("last_name")).sendKeys("Smart");
        driver.findElement(By.id("male")).click();
        driver.findElement(By.id("etsy_finds")).click();

        driver.findElement(By.id("register_button")).click();

        assertThat(driver.findElement(By.name("first_name"))
                          .getAttribute("value")).isEqualTo("John");
        assertThat(driver.findElement(By.name("last_name"))
                          .getAttribute("value")).isEqualTo("Smart");

        String selectedGender = "";
        for(WebElement radioButton : driver.findElements(By.name("gender"))) {
            if (radioButton.isSelected()) {
                selectedGender = radioButton.getAttribute("value");
                break;
            }
        }

        assertThat(selectedGender).isEqualTo("male");
        assertThat(driver.findElement(By.id("etsy_finds")).isSelected()).isTrue();
    }

    @Test
    public void shouldShowThreeTrustArguments() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        List<String> arguments = homePage.getTrustArguments();

        assertThat(arguments).containsExactly("Satisfied Customers",
                                               "Passionate Sellers",
                                               "Secure Transactions");
    }

    @After
    public void closeDriver() {
        driver.quit();
    }

}
