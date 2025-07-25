package com.epam.seleniumwebdriver.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends PageFactory {

    private final WebDriver driver;

    //public static String actualElementXpath = "//div[@class='title']/a[@target]";
    @FindBy(how = How.XPATH, using = "//*[name()='svg'][contains(@class, 'vi') and contains(@class, 'plus')]")
    public WebElement addQuantity;
    @FindBy(how = How.XPATH, using = "//*[@class='v-modal__close-btn']")
    public WebElement closeButton;

    @FindBy(how = How.XPATH, using = "//div[@class='title']/a[@target]")
    public WebElement actualElement;

    @FindBy(how = How.XPATH, using = "//*[@class='qty__count']//*[@class='input']")
    public WebElement productQuantity;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }
}
