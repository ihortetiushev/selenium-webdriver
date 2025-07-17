package com.epam.seleniumwebdriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends PageFactory {
    public static final String LOCATION_BUTTON_XPATH = "//*[@id=\"__layout\"]/div/header/div[1]/div[1]/div[1]/button";
    public static final String LAPTOPS_PC_TABLETS_CATEGORY_XPATH = "//a[\n" +
            "  contains(@href, '/planshety-i-gadzhety') and\n" +
            "  contains(normalize-space(string(.)), 'Ноутбуки, ПК та планшети')\n" +
            "]\n";

    private final WebDriver driver;
    @FindBy(how = How.XPATH, using = LOCATION_BUTTON_XPATH)
    public WebElement locationButton;
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'geo__cities')]//*[@data-geo-select-city='Харків']")
    public WebElement locationKharkiv;
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'geo__cities')]//*[@data-geo-select-city='Київ']")
    public WebElement locationKyiv;
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'geo__cities')]//*[@data-geo-select-city='Одеса']")
    public WebElement locationOdesa;
    @FindBy(how = How.XPATH, using = LAPTOPS_PC_TABLETS_CATEGORY_XPATH)
    public WebElement laptopsPcTabletsCategory;
    @FindBy(how = How.XPATH, using = "//*[@id=\"__layout\"]/div/div[1]/div[2]/div[2]/div[2]/ul/li[1]/div/div/div[2]/div/ul/li[2]/a")
    public WebElement laptopsPcTabletsCategoryAppleSelector;
    @FindBy(how = How.XPATH, using = "//*[@id=\"__layout\"]/div/div[1]/div[2]/div/div[3]/div[1]/div[1]/div[1]/div/span")
    public WebElement sortButton;
    @FindBy(how = How.XPATH, using = "//ul[@class='sort-by__list']/li[@title='за популярністю']")
    public WebElement sortByPopularityButton;
    String emptyCatalogXpath = "//*[@id='__layout']/div/div[1]/div[2]/p";
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
