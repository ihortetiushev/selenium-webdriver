package com.epam.seleniumwebdriver.testscenario;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.epam.seleniumwebdriver.utils.*;
import static org.assertj.core.api.Assertions.*;

import java.time.Duration;

public class BuyTheMostPopularLaptopTestCase {

    static WebDriver driver;

    public static String stringExpectedTitle;
    public static String stringActualTitle;
    public static String expectedProductQuantity = "3";

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
    void BuyTheMostPopularLaptopPositiveTest() {
        MainPage mainPage = new MainPage(driver);
        SortedByPopularityPage popularityPage = new SortedByPopularityPage(driver);
        CartPage cartPage = new CartPage(driver);

        Actions actions = new Actions(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(mainPage.locationButton));

        mainPage.typeInputLocation("Київ");
        mainPage.catalogButton.click();

        wait.until(ExpectedConditions.visibilityOf(mainPage.laptopsPcTabletsCategory));
        mainPage.laptopsPcTabletsCategory.click();

        mainPage.laptopsPcTabletsCategoryAppleSelector.click();

        actions.moveToElement(mainPage.sortButton).perform();
        mainPage.sortByPopularityButton.click();

        wait.until(ExpectedConditions.visibilityOf(popularityPage.expectedLaptop));
        stringExpectedTitle = popularityPage.expectedLaptop.getAttribute("title");

        wait.until(ExpectedConditions.visibilityOf(popularityPage.sortByPopularityItem));
        popularityPage.sortByPopularityItem.click();

        wait.until(ExpectedConditions.urlToBe(popularityPage.expectedUrl));

        popularityPage.buyButton.click();

        wait.until(ExpectedConditions.visibilityOf(popularityPage.goToCartButton));
        popularityPage.goToCartButton.click();

        wait.until(ExpectedConditions.visibilityOf(cartPage.actualElement));
        stringActualTitle = cartPage.actualElement.getText();

        assertThat(stringActualTitle)
                .as("Text '%s' is not as expected '%s'", stringActualTitle, stringExpectedTitle)
                .isEqualTo(stringExpectedTitle);

        wait.until(ExpectedConditions.elementToBeClickable(cartPage.addQuantity));
        cartPage.addQuantity.click();
        wait.until(ExpectedConditions.elementToBeClickable(cartPage.addQuantity));
        cartPage.addQuantity.click();

        wait.until(ExpectedConditions.attributeContains(cartPage.productQuantity, "value", "3"));
        assertThat(cartPage.productQuantity.getAttribute("value"))
                .as("Text '%s' is not as expected '%s'", stringActualTitle, stringExpectedTitle)
                .isEqualTo(expectedProductQuantity);
    }
}
