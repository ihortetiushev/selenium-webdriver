package com.epam.seleniumwebdriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends PageFactory {

    private final WebDriver driver;

    public static final String LOCATION_BUTTON_XPATH = "//div[@class='mh-loc']//button[@class='mh-button']";

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
    String emptyCatalogXpath = "//*[contains(@class, 'v-catalog__empty') and contains(text(), 'Нажаль, нічого не знайдено')]";
    @FindBy(how = How.CLASS_NAME, using = "ct-button")
    WebElement catalogButton;
    @FindBy(how = How.ID, using = "search-form__input")
    WebElement searchBar;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
    }

    public void typeInputLocation(String location) {
        locationButton.click();

        switch (location) {
            case ("Харків"):
                locationKharkiv.click();
                break;

            case ("Київ"):
                locationKyiv.click();
                break;

            case ("Одеса"):
                locationOdesa.click();
                break;
        }
    }
}
