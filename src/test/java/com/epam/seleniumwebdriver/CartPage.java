package com.epam.seleniumwebdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends PageFactory {
    private final WebDriver driver;
    @FindBy(how = How.XPATH, using = "//*[name()='svg'][contains(@class, 'vi') and contains(@class, 'plus')]")
    public WebElement addQuantity;
    @FindBy(how = How.XPATH, using = "/html/body/div[4]/div/div/div[1]")
    public WebElement closeButton;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }
}
