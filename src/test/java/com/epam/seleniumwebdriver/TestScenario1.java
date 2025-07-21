package com.epam.seleniumwebdriver;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

public class TestScenario1 {

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
        TestScenario1.stringExpectedTitle = stringExpectedTitle;
    }

    public void defineActualElement(String xpath) {
        WebElement actualElement = driver.findElement(By.xpath(xpath));
        String stringActualTitle = actualElement.getText();
        System.out.println("Actual Title: " + stringActualTitle);
        TestScenario1.stringActualTitle = stringActualTitle;
    }

    @Test
    void scenario1() {
        MainPage mainPage = new MainPage(driver);
        SortedByPopularityPage popularityPage = new SortedByPopularityPage(driver);
        CartPage cartPage = new CartPage(driver);

        Actions actions = new Actions(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(mainPage.locationButton));

        mainPage.typeInputLocation("Київ");
        mainPage.catalogButton.click();

        wait.until(ExpectedConditions.visibilityOf(mainPage.laptopsPcTabletsCategory));
        mainPage.laptopsPcTabletsCategory.click();

        mainPage.laptopsPcTabletsCategoryAppleSelector.click();

        actions.moveToElement(mainPage.sortButton).perform();
        mainPage.sortByPopularityButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SortedByPopularityPage.expectedLaptopXpath)));
        defineExpectedElement(SortedByPopularityPage.expectedLaptopXpath);

        wait.until(ExpectedConditions.visibilityOf(popularityPage.sortByPopularityItem));
        popularityPage.sortByPopularityItem.click();

        wait.until(ExpectedConditions.urlToBe(popularityPage.expectedUrl));

        popularityPage.buyButton.click();

        wait.until(ExpectedConditions.visibilityOf(popularityPage.goToCartButton));
        popularityPage.goToCartButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CartPage.actualElementXpath)));
        defineActualElement(CartPage.actualElementXpath);

        assertEquals(stringExpectedTitle, stringActualTitle, "Expected element: " + stringExpectedTitle
                + " Actual element: " + stringActualTitle);

        wait.until(ExpectedConditions.elementToBeClickable(cartPage.addQuantity));
        cartPage.addQuantity.click();
        wait.until(ExpectedConditions.elementToBeClickable(cartPage.addQuantity));
        cartPage.addQuantity.click();
    }
}
