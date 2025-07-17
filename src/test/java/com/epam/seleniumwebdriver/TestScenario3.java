package com.epam.seleniumwebdriver;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class TestScenario3 {
    public static String stringExpectedMessage = "Нажаль, нічого не знайдено.";
    public static String stringActualMessage;
    static WebDriver driver;

    @AfterAll
    public static void closeDriver() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

    @BeforeEach
    public void createDriver() {
        driver = new ChromeDriver();
        driver.get("https://allo.ua");
        driver.manage().window().maximize();
    }

    public void defineActualMessage(String xpath) {
        WebElement actualMessageElement = driver.findElement(By.xpath(xpath));
        String message = actualMessageElement.getText();
        System.out.println("Actual Message: " + message);
        TestScenario3.stringActualMessage = message;
    }

    @Test
    void scenario3() {
        MainPage mainPage = new MainPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MainPage.LOCATION_BUTTON_XPATH)));

        mainPage.typeInputLocation("Одеса");
        mainPage.searchBar.click();
        mainPage.searchBar.sendKeys("No kia 3310");
        mainPage.searchBar.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(mainPage.emptyCatalogXpath)));
        defineActualMessage(mainPage.emptyCatalogXpath);
        assertEquals(stringExpectedMessage, stringActualMessage, "Expected message: " + stringExpectedMessage
                + " Actual message: " + stringActualMessage);
    }
}