package com.epam.seleniumwebdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SortedByPopularityPage extends PageFactory {
    public static final String SORT_BY_POPULARITY_ITEM_XPATH = "//*[@id=\"__layout\"]/div/div[1]/div[2]/div/div[3]/div[3]/div[1]/div";
    public static final String GO_TO_CART_BUTTON_XPATH = "/html/body/div[4]/div/div/div[3]/div/div/div[1]/div[2]/button[2]";
    private final WebDriver driver;
    @FindBy(how = How.XPATH, using = SORT_BY_POPULARITY_ITEM_XPATH)
    public WebElement sortByPopularityItem;
    public String expectedUrl = "https://allo.ua/ua/products/notebooks/apple-macbook-air-13-m1-mgn63-space-grey.html";
    @FindBy(how = How.ID, using = "product-buy-button")
    public WebElement buyButton;
    @FindBy(how = How.XPATH, using = GO_TO_CART_BUTTON_XPATH)
    public WebElement goToCartButton;

    public SortedByPopularityPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }
}
