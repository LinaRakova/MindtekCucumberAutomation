package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.WebordersLoginPage;
import pages.WebordersMainPage;
import pages.WebordersOrderPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;
import java.util.Map;

public class WebOrdersSteps {
    WebDriver driver = Driver.getDriver();
    WebordersLoginPage loginPage = new WebordersLoginPage();
    WebordersMainPage mainPage = new WebordersMainPage();
    WebordersOrderPage orderPage = new WebordersOrderPage();

    List<Map<String, Object>> data;

    @Given("User navigates to application")
    public void user_navigates_to_application() {
        // Write code here that turns the phrase above into concrete actions
        driver.get(ConfigReader.getProperty("WebOrdersURL"));
    }

    @When("User logs in with username {string} and password {string}")
    public void user_logs_in_with_username_and_password(String userName, String password) {
        // Write code here that turns the phrase above into concrete actions
        loginPage.userNameInput.sendKeys(userName);
        loginPage.passwordInput.sendKeys(password);
        loginPage.loginBtn.click();
    }

    @Then("User is successfully logged in and lands on the homepage")
    public void user_is_successfully_logged_in_and_lands_on_the_homepage() {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals("Web Orders", driver.getTitle());
    }

    @Then("User validates error message {string}")
    public void user_validates_error_message(String expectedErrorMsg) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(expectedErrorMsg,loginPage.errorMsg.getText());
    }

    @When("User clicks on Order tab")
    public void user_clicks_on_order_tab() {
        mainPage.orderTab.click();
    }

    @When("User selects product {string}, enters quantity {int}")
    public void user_selects_product_enters_quantity(String productName, Integer quantity) {
        BrowserUtils.selectDropDownByValue(orderPage.productDropDwn, productName);
        orderPage.quantityOfProduct.clear();
        orderPage.quantityOfProduct.sendKeys(quantity.toString());
        orderPage.calculateBtn.click();
    }
    @Then("User validates the price is correctly calculated according to quantity {int}")
    public void user_validates_the_price_is_correctly_calculated_according_to_quantity(Integer quantity) {
        int expectedTotal;
        int actualTotal= Integer.parseInt(orderPage.total.getAttribute("value"));
        int priceInt = Integer.parseInt(orderPage.pricePerUnit.getAttribute("value"));
        int discountInt = Integer.parseInt(orderPage.discount.getAttribute("value"));

        if(quantity>=10) {
            expectedTotal = priceInt * quantity - (priceInt * quantity) * discountInt / 100;
        }
        else{
            expectedTotal = priceInt * quantity;
        }
        Assert.assertEquals(actualTotal,expectedTotal);
    }

    @When("User creates order with data")
    public void user_creates_order_with_data(DataTable dataTable) {

        data = dataTable.asMaps(String.class, Object.class);
        for(Map map : data) {
            BrowserUtils.selectDropDownByValue(orderPage.productDropDwn, map.get("PRODUCT").toString());
            orderPage.quantityOfProduct.sendKeys(map.get("QUANTITY").toString());
            orderPage.inputName.sendKeys(map.get("CUSTOMER NAME").toString());
            orderPage.inputStreet.sendKeys(map.get("STREET").toString());
            orderPage.inputCity.sendKeys(map.get("CITY").toString());
            orderPage.inputState.sendKeys(map.get("STATE").toString());
            orderPage.inputZip.sendKeys(map.get("ZIP").toString());
            orderPage.visaCardBtn.click();
            orderPage.inputCardNum.sendKeys(map.get("CARD NUM").toString());
            orderPage.inputExpDate.sendKeys(map.get("EXP DATE").toString());
            orderPage.processBtn.click();
            user_validate_success_message("New order has been successfully added.");
        }
    }

    @Then("User validate success message {string}")
    public void user_validate_success_message(String expectedMsg) {
        Assert.assertEquals(expectedMsg,orderPage.successMessage.getText());
    }

    @Then("User validates created order is in the list of all orders")
    public void user_validates_created_order_is_in_the_list_of_all_orders() {

        mainPage.viewAllOrdersBtn.click();
        Assert.assertEquals(data.get(1).get("CUSTOMER NAME").toString(), mainPage.firstRowOrder.get(1).getText());
    }
}
