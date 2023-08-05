package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EtsyMainPage {

    WebDriver driver;
    public EtsyMainPage(){
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='global-enhancements-search-query']")
    public WebElement searchBar;

    @FindBy(xpath = "//ol[@data-results-grid-container]//h3")
    public List <WebElement> itemTitles;
    @FindBy(id = "search-filter-button")
    public WebElement allFiltersBtn;

    @FindBy(xpath = "//label[@for='price-input-4' and @class='wt-radio__label wt-display-inline']")
    public WebElement over1000Btn;

    @FindBy(xpath = "//button[@aria-label='Apply']")
    public WebElement applyBtn;

    @FindBy(xpath = "//span[@class='wt-text-strikethrough wt-text-grey']//span[@class='currency-value']")
    public List<WebElement> listOfPrices;

    @FindBy(xpath = "//li[@role='presentation' and @class='top-nav-item wt-pb-xs-2 wt-mr-xs-2 wt-display-flex-xs wt-align-items-center']")
    private List<WebElement> listOfModules;

    public void clickOnModule(String module){
        Map<String, WebElement> map = new HashMap<>();

        listOfModules.forEach(m -> map.put(m.getText(), m));

        map.get(module).click();

  /*      for(String key : map.keySet()){

            if(key.equalsIgnoreCase(module)) {
                map.get(key).click();
            }
        }*/
    }
}
