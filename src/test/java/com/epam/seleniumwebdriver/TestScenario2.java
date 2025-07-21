package com.epam.seleniumwebdriver;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class TestScenario2 {

    static WebDriver driver;

    public static String stringExpectedTitle;
    public static String stringActualTitle;

    @AfterAll
    public static void closeDriver() {
        driver.quit();
    }

    @BeforeEach
    public void createDriver() {
        driver = new ChromeDriver();
        driver.get("https://allo.ua");
        driver.manage().window().maximize();
    }

    public void defineExpectedElement(String xpath) {
        WebElement expectedElement = driver.findElement(By.xpath(xpath));
        String stringExpectedTitle = expectedElement.getAttribute("title");
        System.out.println("Expected Title: " + stringExpectedTitle);
        TestScenario2.stringExpectedTitle = stringExpectedTitle;
    }

    public void defineActualElement(String xpath) {
        WebElement actualElement = driver.findElement(By.xpath(xpath));
        String stringActualTitle = actualElement.getText();
        System.out.println("Actual Title: " + stringActualTitle);
        TestScenario2.stringActualTitle = stringActualTitle;
    }

    @Test
    void scenario2() {
        MainPage mainPage = new MainPage(driver);
        SortedByPhoneNamePage sortedByNamePage = new SortedByPhoneNamePage(driver);
        CartPage cartPage = new CartPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        Actions actions = new Actions(driver);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MainPage.LOCATION_BUTTON_XPATH)));

        mainPage.typeInputLocation("Харків");
        mainPage.searchBar.click();
        mainPage.searchBar.sendKeys("Pixel 9 pro");
        mainPage.searchBar.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.urlToBe(sortedByNamePage.sortedByPhoneNamePageUrl));

        actions.moveToElement(sortedByNamePage.sortSpace).perform();
        sortedByNamePage.sortByExpensive.click();
        wait.until(ExpectedConditions.urlToBe(sortedByNamePage.sortedByMostExpensiveToCheapestPageUrl));

        defineExpectedElement(sortedByNamePage.expectedTitlePhoneXpath);

        sortedByNamePage.mostExpensiveItem.click();
        wait.until(ExpectedConditions.urlToBe(sortedByNamePage.mostExpensiveItemPage));

        sortedByNamePage.buyButton.click();

        wait.until(ExpectedConditions.visibilityOf(sortedByNamePage.goToCart));
        sortedByNamePage.goToCart.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sortedByNamePage.actualTitlePhoneXpath)));
        defineActualElement(sortedByNamePage.actualTitlePhoneXpath);

        assertEquals(TestScenario2.stringExpectedTitle, TestScenario2.stringActualTitle,
                "Expected element: " + TestScenario2.stringExpectedTitle + " Actual element: " + TestScenario2.stringActualTitle);

        cartPage.closeButton.click();
        sortedByNamePage.searchBar.click();

        sortedByNamePage.searchBar.sendKeys("Зарядний пристрій pixel 30W");
        sortedByNamePage.searchBar.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOf(sortedByNamePage.sortSpace));
        actions.moveToElement(sortedByNamePage.sortSpace).perform();
        sortedByNamePage.sortByCheap.click();

        System.out.println();

        wait.until(ExpectedConditions.visibilityOf(sortedByNamePage.cheapestCharger));
        defineExpectedElement(SortedByPhoneNamePage.EXPECTED_TITLE_CHEAPEST_CHARGER_XPATH);

        wait.until(ExpectedConditions.urlToBe(sortedByNamePage.sortedByChargerPhonePageUrl));
        sortedByNamePage.cheapestCharger.click();

        wait.until(ExpectedConditions.urlToBe(sortedByNamePage.expectedChargerUrl));
        sortedByNamePage.buyButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sortedByNamePage.actualTitleChargerXpath)));
        defineActualElement(sortedByNamePage.actualTitleChargerXpath);
        assertEquals(stringExpectedTitle, stringActualTitle, "Expected element: " + stringExpectedTitle
                + " Actual element: " + stringActualTitle);
    }
}
