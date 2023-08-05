package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.EtsyMainPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EtsySteps {
    WebDriver driver = Driver.getDriver();
    EtsyMainPage mainPage = new EtsyMainPage();
    List<Boolean> validationOfTitleResults;

    @Given("User navigates to Etsy application")
    public void user_navigates_to_etsy_application() {
        driver.get(ConfigReader.getProperty("EtsyURL"));
    }

    @When("User searches for {string}")
    public void user_searches_for(String itemName) {

        mainPage.searchBar.sendKeys(itemName + Keys.ENTER);
    }
    @Then("User validates search result contains")
    public void user_validates_search_result_contains(DataTable dataTable) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 15);
        wait.until(ExpectedConditions.visibilityOfAllElements(mainPage.itemTitles));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        boolean isFound = false;
        List<String> keywords = dataTable.asList();
        for(WebElement item : mainPage.itemTitles) {
            String itemDescription = item.getText();
            for (int i = 0; i < keywords.size(); i++) {
                if(itemDescription.toLowerCase().contains(keywords.get(i))){
                    isFound = true;
                }
                else{
                    System.out.println(itemDescription + " doesn't contain " + keywords.get(i));
                }
            }
            Assert.assertTrue(isFound);
        }
    }
    @When("User selects price range over {int}")
    public void user_selects_price_range_over(Integer overPrice) {
        mainPage.allFiltersBtn.click();
        BrowserUtils.waitSeconds(2);
        BrowserUtils.scrollDown(mainPage.over1000Btn);
        BrowserUtils.waitForElementToBeClickable(mainPage.over1000Btn);
        mainPage.over1000Btn.click();
        mainPage.applyBtn.click();
        BrowserUtils.waitSeconds(3);
    }

    @Then("User validates price range for items over {int}")
    public void user_validates_price_range_for_items_over(Integer priceOver) {
        for(WebElement element : mainPage.listOfPrices){
            String priceInfo = element.getText().replace(",", "");
            System.out.println(priceInfo);
            double price = (Double.parseDouble(priceInfo));
            Assert.assertTrue(price>=priceOver);
        }
    }


    @When("User clicks on Module")
    public void user_clicks_on_module(io.cucumber.datatable.DataTable dataTable) {

        List<Map<String,String>> modulesWithTitles = dataTable.asMaps();

        validationOfTitleResults = new ArrayList<Boolean>();

        for(Map<String, String> map :modulesWithTitles){
            String module = map.get("Module");
            mainPage.clickOnModule(module);

            String title = map.get("Title");
            boolean isValid = driver.getTitle().contains(title);
            validationOfTitleResults.add(isValid);
        }

       // listOfModules.forEach(module -> mainPage.clickOnModule(module));
    }

    @Then("User validates Title of the page is correct")
    public void user_validates_the_title_of_the_page_is_correct() {

        validationOfTitleResults.forEach(Assert::assertTrue);
    }
    //    String actualTitle = driver.getTitle();
    //    List<String> listOfTitles = dataTable.asList();
    //    listOfTitles.forEach(expectedTitle -> Assert.assertTrue(expectedTitle.contains(actualTitle)));

       // System.out.println(actualTitle);
       // Assert.assertTrue(actualTitle.contains(title));

}