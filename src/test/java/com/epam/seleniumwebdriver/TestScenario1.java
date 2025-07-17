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
    public static String stringExpectedTitle;
    public static String stringActualTitle;
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
    void scenario1() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        SortedByPopularityPage popularityPage = new SortedByPopularityPage(driver);
        CartPage cartPage = new CartPage(driver);

        Actions actions = new Actions(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MainPage.LOCATION_BUTTON_XPATH)));

        mainPage.typeInputLocation("Київ");
        mainPage.catalogButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MainPage.LAPTOPS_PC_TABLETS_CATEGORY_XPATH)));
        mainPage.laptopsPcTabletsCategory.click();

        mainPage.laptopsPcTabletsCategoryAppleSelector.click();

        actions.moveToElement(mainPage.sortButton).perform();
        mainPage.sortByPopularityButton.click();

        Thread.sleep(1000);

        defineExpectedElement("//*[@id=\"__layout\"]/div/div[1]/div[2]/div/div[3]/div[3]/div[1]/div//*[@title " +
                "and @href]");

        Thread.sleep(1500);

        popularityPage.sortByPopularityItem.click();

        wait.until(ExpectedConditions.urlToBe(popularityPage.expectedUrl));

        popularityPage.buyButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(SortedByPopularityPage.GO_TO_CART_BUTTON_XPATH)));

        popularityPage.goToCartButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div/div/div[3]/div/div[1]/div/div/div/ul/li/div/div" +
                "/div[2]/div[1]/a/p/span")));

        defineActualElement("/html/body/div[4]/div/div/div[3]/div/div[1]/div/div/div/ul/li/div/div" +
                "/div[2]/div[1]/a/p/span");

        assertEquals(stringExpectedTitle, stringActualTitle, "Expected element: " + stringExpectedTitle
                + " Actual element: " + stringActualTitle);

        cartPage.addQuantity.click();
        Thread.sleep(500);
        cartPage.addQuantity.click();
    }
}
