package utilities;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class RestAPITest {
    public static void main(String[] args){
        /*
        Make get yard api call.
        Request:
           1. URL -> Base URL & Endpoint   -> http://3.137.169.132/en-us/api/v2/yards/21/
           2. Headers -> Authorisation(Token), Accept(application/json)
           3. Method -> Get
        Response:
           1. Status code
           2. Json Data
         */

        given().baseUri("http://3.137.169.132/en-us/api/v2")
                .and().header("Authorization", "Token 9d3994dd2afd7d1d8ae9ecf4d77e45932bb210d6")
                .and().header("Accept", "application/json")
                .when().get("/yards/10/")
                .then().log().all();

//        System.out.println(response.statusCode());
//        System.out.println(response.body().asString());

        /*
        POST call
            Request:
                1. URL -> Base URL & Endpoint   -> http://3.137.169.132/en-us/api/v2/yards/21/
                2. Headers -> Authorisation(Token), Accept(application/json), Content-Type(application/json)
                3. Payload/Body - > json
                4. Method -> POST
            Response:

         */

        Response postResponse = given().baseUri("http://3.137.169.132/en-us/api/v2")
                .and().header("Authorization", "Token 9d3994dd2afd7d1d8ae9ecf4d77e45932bb210d6")
                .and().accept("application/json")
                .and().contentType("application/json")
                .and().body("{\n" +
                        "  \"location\": \"Lina2\",\n" +
                        "  \"status\": \"active\",\n" +
                        "  \"address\": \"123 MY ave\",\n" +
                        "  \"apt_suite_company_co\": \"\",\n" +
                        "  \"city\": \"Chicago\",\n" +
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
                .when().post("/yards/");

        System.out.println(postResponse.statusCode());
        System.out.println(postResponse.body().asString());


        /*
        Patch call
            Request:
                1. URL -> BaseURL + Endpoint
                2. Headers -> Authorisation(Token), Accept(application/json), Content-Type(application/json)
                3. Payload -> json
                4. Method -> PATCH
            Response:
                1. Status code -> 200
                2. Body -> json
         */

        Response updateResponce = given().baseUri("http://3.137.169.132/en-us/api/v2")
                .and().header("Authorization", "Token 9d3994dd2afd7d1d8ae9ecf4d77e45932bb210d6")
                .and().accept("application/json")
                .and().contentType("application/json")
                .and().body("{\n" +
                        "  \"id\": 55,\n" +
                        "  \"location\": \"Lina Mindtek 3\",\n" +
                        "  \"name\": \"Y55\",\n" +
                        "  \"status\": \"active\",\n" +
                        "  \"address\": \"321 MY ave\",\n" +
                        "  \"apt_suite_company_co\": \"\",\n" +
                        "  \"city\": \"CHicago\",\n" +
                        "  \"state\": \"IL\",\n" +
                        "  \"zip_code\": \"11223\",\n" +
                        "  \"spots\": 111,\n" +
                        "  \"warning\": \"\",\n" +
                        "  \"notes\": \"\",\n" +
                        "  \"contacts\": []\n" +
                        "}")
                .when().patch("/yards/55/");

        System.out.println(updateResponce.statusCode());
        System.out.println(updateResponce.body().asString());
    }
}