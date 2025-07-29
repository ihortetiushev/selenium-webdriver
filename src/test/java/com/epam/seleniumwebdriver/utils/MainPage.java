package com.epam.seleniumwebdriver.utils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends PageFactory {

    public static final String LOCATION_BUTTON_XPATH = "//div[@class='mh-loc']//button[@class='mh-button']";
    private final WebDriver driver;
    @FindBy(how = How.XPATH, using = LOCATION_BUTTON_XPATH)
    public WebElement locationButton;
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'geo__cities')]//*[@data-geo-select-city='Харків']")
    public WebElement locationKharkiv;
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'geo__cities')]//*[@data-geo-select-city='Київ']")
    public WebElement locationKyiv;
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'geo__cities')]//*[@data-geo-select-city='Одеса']")
    public WebElement locationOdesa;
    @FindBy(how = How.XPATH, using = "//a[\n" +
            "  contains(@href, '/planshety-i-gadzhety') and\n" +
            "  contains(normalize-space(string(.)), 'Ноутбуки, ПК та планшети')\n" +
            "]\n")
    public WebElement laptopsPcTabletsCategory;
    @FindBy(how = How.XPATH, using = "//li[@class='portal-group__item'][.//a[@href='https://allo" +
            ".ua/ua/products/notebooks/']]//a[@class='portal-card__link' and contains(text(), 'Apple')]\n")

    public WebElement laptopsPcTabletsCategoryAppleSelector;
    @FindBy(how = How.XPATH, using = "//span[text()='від дорогих до дешевих']")
    public WebElement sortButton;
    @FindBy(how = How.XPATH, using = "//ul[@class='sort-by__list']/li[@title='за популярністю']")
    public WebElement sortByPopularityButton;

    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'v-catalog__empty') and contains(text(), 'Нажаль, нічого не знайдено')]")
    public WebElement emptyCatalogXpath;

    @FindBy(how = How.CLASS_NAME, using = "ct-button")
    public
    WebElement catalogButton;
    @FindBy(how = How.ID, using = "search-form__input")
    public
    WebElement searchBar;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    public void selectLocation(City city) {
        locationButton.click();

        switch (city) {
            case Kharkiv:
                locationKharkiv.click();
                break;

            case Kyiv:
                locationKyiv.click();
                break;

            case Odesa:
                locationOdesa.click();
                break;
            default:
                throw new IllegalArgumentException("city " + city + " is not supported");
        }

    }

    public void searchProduct(String productName) {
        searchBar.click();
        searchBar.sendKeys(productName);
        searchBar.sendKeys(Keys.ENTER);
    }
}
