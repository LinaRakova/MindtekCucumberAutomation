package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.ConfigReader;
import utilities.JDBCutils;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AddYardStepsApi {

    String yardId;
    List<Map<String,Object>> dbData;
    Response response;

    @Given("user creates yard with post yard call with data")
    public void user_creates_yard_with_post_yard_call_with_data(io.cucumber.datatable.DataTable dataTable) {
        /*
        POST yard:
            1. URL
            2. Headers - > Authorization, Accept, Content-type
            3. Payload ->
            3. Method -> POST

        Response:
            1.
            2.
         */

        Map<String, Object> data = dataTable.asMap(String.class, Object.class);

        String name = data.get("location").toString();
        String status = data.get("status").toString();
        String address = data.get("address").toString();

        response =given().baseUri(ConfigReader.getProperty("ElarURL"))
                .and().header("Authorization", "Token 9d3994dd2afd7d1d8ae9ecf4d77e45932bb210d6")
                .and().accept("application/json")
                .and().contentType("application/json")
                .and().body("{\n" +
                        "  \"location\": \""+name+"\",\n" +
                        "  \"status\": \""+status+"\",\n" +
                        "  \"address\": \""+address+"\",\n" +
                        "  \"apt_suite_company_co\": \"\",\n" +
                        "  \"city\": \"CHicago\",\n" +
                        "  \"state\": \"IL\",\n" +
                        "  \"zip_code\": \"11223\",\n" +
                        "  \"spots\": \"111\",\n" +
                        "  \"warning\": \"\",\n" +
                        "  \"notes\": \"\",\n" +
                        "  \"yard_picture\": [],\n" +
                        "  \"contacts\": [],\n" +
                        "  \"service_phone_number\": [\n" +
                        "    {\n" +
                        "      \"service_phone\": \"\",\n" +
                        "      \"service_contact_name\": \"\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"owner_phone_number\": [\n" +
                        "    {\n" +
                        "      \"owner_phone\": \"\",\n" +
                        "      \"owner_contact_name\": \"\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")
                .and().log().all()       // logs (prints) request details
                .when().post("/yards/");
        response.then().log().all();     // logs (prints) response details
        response.then().statusCode(201);

        yardId = response.body().jsonPath().getString("id");
        System.out.println("Yard id is: "+yardId);

    }

    @When("user connects to database")
    public void user_connects_to_database() {
        JDBCutils.establishConnection("Elar");
        dbData=JDBCutils.runQuery("SELECT * FROM core_yard WHERE id='"+yardId+"'");
        System.out.println("yard details from dataBase: "+dbData);


    }

    @Then("user validates created yard is persisted in DB")
    public void user_validates_created_yard_is_persisted_in_db() {
        String apiYardName = response.body().jsonPath().getString("location");
        String apiStatus = response.body().jsonPath().getString("status");
        String apiAddress = response.body().jsonPath().getString("address");

        Assert.assertEquals(apiYardName, dbData.iterator().next().get("location").toString());
        Assert.assertEquals(apiStatus, dbData.get(0).get("status").toString());
        Assert.assertEquals(apiAddress, dbData.get(0).get("address").toString());

    }
}
