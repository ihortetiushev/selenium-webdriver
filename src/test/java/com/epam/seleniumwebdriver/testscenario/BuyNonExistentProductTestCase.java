package com.epam.seleniumwebdriver.testscenario;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.epam.seleniumwebdriver.utils.*;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class BuyNonExistentProductTestCase {

    static WebDriver driver;

    public static String stringExpectedMessage = "Нажаль, нічого не знайдено.";
    public static String stringActualMessage;

    @BeforeEach
    public void createDriver() {
        driver = new ChromeDriver();
        driver.get("https://allo.ua");
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void closeDriver() {
        driver.quit();
    }

    @Test
    void buyNonExistentProductNegativeTest() {
        MainPage mainPage = new MainPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.visibilityOf(mainPage.locationButton));

        mainPage.selectLocation(City.findByCityName("Одеса"));

        mainPage.searchBar.click();
        mainPage.searchBar.sendKeys("No kia 3310");
        mainPage.searchBar.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOf(mainPage.emptyCatalogXpath));

        stringActualMessage = mainPage.emptyCatalogXpath.getText();

        assertThat(stringActualMessage)
                .as("Text '%s' is not as expected '%s'", stringActualMessage, stringExpectedMessage)
                .isEqualTo(stringExpectedMessage);
    }
}