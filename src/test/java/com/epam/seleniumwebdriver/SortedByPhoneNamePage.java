package com.epam.seleniumwebdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SortedByPhoneNamePage extends PageFactory {
    public static final String GO_TO_CART = "/html/body/div[4]/div/div/div[3]/div/div/div/div[2]/button[2]";
    static final String SORT_SPACE_XPATH = "//*[@id='__layout']/div/div[1]/div[2]/div/div[2]/div[1]/div[1]/div[1]/div";
    static final String SORT_SPACE2_XPATH = "//*[@id=\"__layout\"]/div/div[1]/div[2]/div/div[2]/div[1]/div/div[1]/div/span";
    static final String EXPECTED_TITLE_CHEAPEST_CHARGER_XPATH = "//*[@id=\"__layout\"]/div/div[1]/div[2]/div/div[2]/div[2]/div[1]/div/div[3]/a";
    private final WebDriver driver;
    @FindBy(how = How.XPATH, using = SORT_SPACE_XPATH)
    public WebElement sortSpace;
    @FindBy(how = How.XPATH, using = SORT_SPACE2_XPATH)
    public WebElement sortSpace2;
    @FindBy(how = How.XPATH, using = "//*[@id=\"__layout\"]/div/div[1]/div[2]/div/div[2]/div[1]/div[1]/div[1]/div/ul/li[2]")
    public WebElement sortByExpensive;
    @FindBy(how = How.XPATH, using = "//*[@id=\"__layout\"]/div/div[1]/div[2]/div/div[2]/div[1]/div/div[1]/div/ul/li[1]")
    public WebElement sortByCheap;
    @FindBy(how = How.XPATH, using = "//*[@id=\"__layout\"]/div/div[1]/div[2]/div/div[2]/div[3]/div[1]/div")
    public WebElement mostExpensiveItem;
    @FindBy(how = How.ID, using = "product-buy-button")
    public WebElement buyButton;
    @FindBy(how = How.XPATH, using = GO_TO_CART)
    public WebElement goToCart;
    @FindBy(how = How.ID, using = "search-form__input")
    public WebElement searchBar;
    @FindBy(how = How.XPATH, using = EXPECTED_TITLE_CHEAPEST_CHARGER_XPATH)
    public WebElement cheapestCharger;
    String sortedByPhoneNamePageUrl = "https://allo.ua/ua/catalogsearch/result/index/cat-48/?q=pixel%209%20pro";
    String sortedByMostExpensiveToCheapestPageUrl = "https://allo.ua/ua/catalogsearch/result/index/cat-48/dir-desc/order-price/?q=pixel%209%20pro";
    String mostExpensiveItemPage = "https://allo.ua/ua/products/mobile/smartfon-google-pixel-9-pro-fold-16-512gb-obsidian-eu-usa-global-version" +
            "-ga05799-us-51638.html";
    String expectedTitlePhoneXpath = "//*[@id='__layout']/div/div[1]/div[2]/div/div[2]/div[3]/div[1]/div/div[3]/a";
    String actualTitlePhoneXpath = "/html/body/div[4]/div/div/div[3]/div/div[1]/div/div/div/ul/li/div/div/div[2]/div[1]/a/p/span";
    String actualTitleChargerXpath = "/html/body/div[4]/div/div/div[3]/div/div[1]/div/div/div/ul/li[2]/div/div/div[2]/div[1]/a/p/span";
    String expectedChargerUrl = "https://allo.ua/ua/zarjadnye-ustrojstva/zarjadnoe-ustrojstvo-google-pixel-charger-30w.html";

    public SortedByPhoneNamePage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }
}
