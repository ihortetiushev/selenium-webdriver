package com.epam.seleniumwebdriver.utils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class Search {
    public void searchProduct(WebElement webElement, String productName) {
        webElement.click();
        webElement.sendKeys(productName);
        webElement.sendKeys(Keys.ENTER);
    }
}
