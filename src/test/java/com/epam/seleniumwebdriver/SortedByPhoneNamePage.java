package com.epam.seleniumwebdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SortedByPhoneNamePage extends PageFactory {

    private final WebDriver driver;

    static final String EXPECTED_TITLE_CHEAPEST_CHARGER_XPATH = "//*[contains(@class, 'product-card__content')]//*[@title='Зарядний пристрій Google Pixel Charger 30W']";
    @FindBy(how = How.XPATH, using = "//*[@class='sort-by__select']")
    public WebElement sortSpace;
    @FindBy(how = How.XPATH, using = "//*[@class='sort-by__list']//li[text()='від дорогих до дешевих']")
    public WebElement sortByExpensive;
    @FindBy(how = How.XPATH, using = "//*[@class='sort-by__list']//li[text()='від дешевих до дорогих']")
    public WebElement sortByCheap;
    @FindBy(how = How.XPATH, using = "//*[@class='products-layout__container products-layout--grid']/div[1]")
    public WebElement mostExpensiveItem;
    @FindBy(how = How.ID, using = "product-buy-button")
    public WebElement buyButton;
    @FindBy(how = How.XPATH, using = "//*[@class='related-products__button a-button a-button--outline a-button--primary']")
    public WebElement goToCart;
    @FindBy(how = How.ID, using = "search-form__input")
    public WebElement searchBar;
    @FindBy(how = How.XPATH, using = EXPECTED_TITLE_CHEAPEST_CHARGER_XPATH)
    public WebElement cheapestCharger;
    String sortedByPhoneNamePageUrl = "https://allo.ua/ua/catalogsearch/result/index/cat-48/?q=pixel%209%20pro";
    String sortedByChargerPhonePageUrl = "https://allo.ua/ua/catalogsearch/result/index/dir-asc/order-price" +
            "/?q=%D0%97%D0%B0%D1%80%D1%8F%D0%B4%D0%BD%D0%B8%D0%B9%20%D0%BF%D1%80%D0%B8%D1%81%D1%82%D1%80%D1%96%D0%B9%20pixel%2030W";
    String sortedByMostExpensiveToCheapestPageUrl = "https://allo.ua/ua/catalogsearch/result/index/cat-48/dir-desc/order-price/?q=pixel%209%20pro";
    String mostExpensiveItemPage = "https://allo.ua/ua/products/mobile/smartfon-google-pixel-9-pro-xl-16-1tb-obsidian-ga05910-us-ga05910-us-51559.html";
    String expectedTitlePhoneXpath = "//*[contains(@class, 'product-card__content')]//a[@href='https://allo.ua/ua/products/mobile" +
            "/smartfon-google-pixel-9-pro-xl-16-1tb-obsidian-ga05910-us-ga05910-us-51559.html' and @title]";
    String actualTitlePhoneXpath ="//a[@target=\"_self\" and @href='https://allo.ua/ua/products/mobile" +
            "/smartfon-google-pixel-9-pro-xl-16-1tb-obsidian-ga05910-us-ga05910-us-51559.html']//*[@class=\"wrap\"]";
    String actualTitleChargerXpath = "//a[@target=\"_self\" and @href='https://allo" +
            ".ua/ua/zarjadnye-ustrojstva/zarjadnoe-ustrojstvo-google-pixel-charger-30w.html']//*[@class=\"wrap\"]";
    String expectedChargerUrl = "https://allo.ua/ua/zarjadnye-ustrojstva/zarjadnoe-ustrojstvo-google-pixel-charger-30w.html";

    public SortedByPhoneNamePage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }
}
