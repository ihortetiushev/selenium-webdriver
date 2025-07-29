package com.epam.seleniumwebdriver.testscenario;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.epam.seleniumwebdriver.utils.*;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class BuyProductByNameTestCase {

    static WebDriver driver;

    public static String stringExpectedTitle;
    public static String stringActualTitle;

    @BeforeAll
    public static void createDriver() {
        driver = new ChromeDriver();
        driver.get("https://allo.ua");
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void closeDriver() {
        driver.quit();
    }


    @Test
    void buyProductByNamePositiveTest() {
        MainPage mainPage = new MainPage(driver);
        SortedByPhoneNamePage sortedByNamePage = new SortedByPhoneNamePage(driver);
        CartPage cartPage = new CartPage(driver);
        Search search = new Search();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        Actions actions = new Actions(driver);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MainPage.LOCATION_BUTTON_XPATH)));

        mainPage.selectLocation(City.findByCityName("Харків"));
        search.searchProduct(mainPage.searchBar, "Pixel 9 pro");

        wait.until(ExpectedConditions.urlToBe(sortedByNamePage.sortedByPhoneNamePageUrl));

        actions.moveToElement(sortedByNamePage.sortSpace).perform();
        sortedByNamePage.sortByExpensive.click();
        wait.until(ExpectedConditions.urlToBe(sortedByNamePage.sortedByMostExpensiveToCheapestPageUrl));

        stringExpectedTitle = sortedByNamePage.expectedPhone.getAttribute("title");
        sortedByNamePage.expectedPhone.click();

        wait.until(ExpectedConditions.urlToBe(sortedByNamePage.mostExpensiveItemPage));

        sortedByNamePage.buyButton.click();

        wait.until(ExpectedConditions.visibilityOf(sortedByNamePage.goToCart));
        sortedByNamePage.goToCart.click();

        wait.until(ExpectedConditions.visibilityOf(sortedByNamePage.actualPhone));

        stringActualTitle = sortedByNamePage.actualPhone.getText();
        assertThat(stringActualTitle)
                .as("Text '%s' is not as expected '%s'", stringActualTitle, stringExpectedTitle)
                .isEqualTo(stringExpectedTitle);

        cartPage.closeButton.click();

        search.searchProduct(sortedByNamePage.searchBar, "Зарядний пристрій pixel 30W");

        wait.until(ExpectedConditions.visibilityOf(sortedByNamePage.sortSpace));
        actions.moveToElement(sortedByNamePage.sortSpace).perform();
        sortedByNamePage.sortByCheap.click();

        wait.until(ExpectedConditions.visibilityOf(sortedByNamePage.expectedCharger));
        stringExpectedTitle = sortedByNamePage.expectedCharger.getAttribute("title");

        wait.until(ExpectedConditions.urlToBe(sortedByNamePage.sortedByChargerPhonePageUrl));
        sortedByNamePage.expectedCharger.click();

        wait.until(ExpectedConditions.urlToBe(sortedByNamePage.expectedChargerUrl));
        sortedByNamePage.buyButton.click();
        wait.until(ExpectedConditions.visibilityOf(sortedByNamePage.actualTitleCharger));
        stringActualTitle = sortedByNamePage.actualTitleCharger.getText();

        assertThat(stringActualTitle)
                .as("Text '%s' is not as expected '%s'", stringActualTitle, stringExpectedTitle)
                .isEqualTo(stringExpectedTitle);
    }
}
