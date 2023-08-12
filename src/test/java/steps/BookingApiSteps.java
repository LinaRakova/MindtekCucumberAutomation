package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.response.Response;
import org.junit.Assert;
import pogo.Booking;
import pogo.Bookingdates;
import utilities.ConfigReader;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BookingApiSteps {

    Response response;
    Map<String, Object> data;
    Map<String, Object> updatedData;
    String id;
    Booking bookingPayLoad;
    Bookingdates bookingdates;

    @Given("user creates booking with POST api call with data")
    public void user_creates_booking_with_post_api_call_with_data(io.cucumber.datatable.DataTable dataTable) {

       data = dataTable.asMap(String.class, Object.class);
        String firstName=data.get("firstname").toString();
        String lastName=data.get("lastname").toString();
        String totalPrice=data.get("totalprice").toString();
        String checkIn=data.get("checkin").toString();
        String checkout=data.get("checkout").toString();
        String additionalNeeds=data.get("additionalneeds").toString();

        bookingPayLoad=new Booking();
        bookingPayLoad.setFirstname(firstName);
        bookingPayLoad.setLastname(lastName);
        bookingPayLoad.setTotalprice(Integer.parseInt(totalPrice));
        bookingPayLoad.setDepositpaid(true);
        bookingPayLoad.setAdditionalneeds(additionalNeeds);
        bookingdates = new Bookingdates();
        bookingdates.setCheckin(checkIn);
        bookingdates.setCheckout(checkout);
        bookingPayLoad.setBookingdates(bookingdates);

        response=given().baseUri(ConfigReader.getProperty("BookingAPIBaseUrl"))
                .and().accept("application/json")
                .and().contentType("application/json")
                .and().body(bookingPayLoad)   // POJO -> Json ==>SERIALIZATION
                .and().log().all()
                .when().post("/booking");
     //   response.then().log().all();
        id = response.body().jsonPath().getString("bookingid");
    }

    @When("user gets created booking with GET api call")
    public void user_gets_created_booking_with_get_api_call() {

        response=given().baseUri(ConfigReader.getProperty("BookingAPIBaseUrl"))
                .and().accept("application/json")
                .and().log().all()
                .when().get("/booking/"+id);
        response.then().log().all();
    }
    @Then("user validates {int} status code")
    public void user_validates_status_code(Integer statusCode) {
        response.then().statusCode(statusCode);

    }
    @Then("user validates created data matches with response of get call")
    public void user_validates_created_data_matches_with_response_of_get_call() {

        String firstName=response.body().jsonPath().getString("firstname");
        String lastName=response.body().jsonPath().getString("lastname");
        String totalprice=response.body().jsonPath().getString("totalprice");
        String checkin=response.body().jsonPath().getString("bookingdates.checkin");
        String checkout=response.body().jsonPath().getString("bookingdates.checkout");
        String additionalneeds=response.body().jsonPath().getString("additionalneeds");

        Assert.assertEquals(firstName, data.get("firstname").toString());
        Assert.assertEquals(lastName, data.get("lastname").toString());
        Assert.assertEquals(totalprice, data.get("totalprice").toString());
        Assert.assertEquals(checkin, data.get("checkin").toString());
        Assert.assertEquals(checkout, data.get("checkout").toString());
        Assert.assertEquals(additionalneeds, data.get("additionalneeds").toString());
    }

    @When("user updates booking with PUT api call with data")
    public void user_updates_booking_with_put_api_call_with_data(io.cucumber.datatable.DataTable dataTable) {

        updatedData= dataTable.asMap(String.class, Object.class);
        String name = updatedData.get("firstname").toString();
        String checkin = updatedData.get("checkin").toString();
        String checkout = updatedData.get("checkout").toString();

        bookingPayLoad.setFirstname(name);
        bookingdates.setCheckin(checkin);
        bookingdates.setCheckout(checkout);
        bookingPayLoad.setBookingdates(bookingdates);

        response=given().baseUri(ConfigReader.getProperty("BookingAPIBaseUrl"))
                .and().accept("application/json")
                .and().contentType("application/json")
                .and().header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .and().body(bookingPayLoad)
                .and().log().all()
                .when().put("/booking/"+id);
        response.then().log().all();
    }

    @When("user gets updated booking with GET api call")
    public void user_gets_updated_booking_with_get_api_call() {
        response=given().baseUri(ConfigReader.getProperty("BookingAPIBaseUrl"))
                .and().accept("application/json")
                .and().log().all()
                .when().get("/booking/"+id);
        response.then().log().all();
    }
    @Then("user validates updated data matches with response of get call")
    public void user_validates_updated_data_matches_with_response_of_get_call() {

        Booking bookingResponseBody=response.body().as(Booking.class);  // Json -> POJO ==> DESERIALIZATION

        Assert.assertEquals(updatedData.get("firstname").toString(), bookingResponseBody.getFirstname());
        Assert.assertEquals(updatedData.get("checkin").toString(), bookingResponseBody.getBookingdates().getCheckin());
        Assert.assertEquals(updatedData.get("checkout").toString(), bookingResponseBody.getBookingdates().getCheckout());
    }
}