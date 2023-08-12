package utilities;

import io.restassured.response.Response;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class RestAPICompanies {
    public static void main(String[] args) {
                /*
        Make get yard api call.
        Request:
           1. URL -> Base URL & Endpoint   -> http://3.137.169.132/en-us/api/v2/companies/39/
           2. Headers -> Authorisation(Token), Accept(application/json)
           3. Method -> Get
        Response:
           1. Status code
           2. Json Data
         */

        given().baseUri("http://3.137.169.132/en-us/api/v2")
                .and().header("Authorization", "Token 9d3994dd2afd7d1d8ae9ecf4d77e45932bb210d6")
                .and().header("Accept", "application/json")
                .when().get("/companies/39/")
                .then().log().all();

//        System.out.println(response.statusCode());
//        System.out.println(response.body().asString());

        /*
        POST call
            Request:
                1. URL -> Base URL & Endpoint   -> http://3.137.169.132/en-us/api/v2/companies/39/
                2. Headers -> Authorisation(Token), Accept(application/json), Content-Type(application/json)
                3. Payload/Body - > json
                4. Method -> POST
            Response:

         */

        Random random = new Random();
        long mcInput = random.nextLong();
        mcInput = Math.abs(mcInput);

        mcInput = mcInput % 10000000000L;

        int dotInput = random.nextInt(900000);

        Response postResponse = given().baseUri("http://3.137.169.132/en-us/api/v2")
                .and().header("Authorization", "Token 9d3994dd2afd7d1d8ae9ecf4d77e45932bb210d6")
                .and().accept("application/json")
                .and().contentType("application/json")
                .and().body("{\n" +
                        "  \"company_name\": \"Lina Test\",\n" +
                        "  \"company_type\": \"broker company\",\n" +
                        "  \"status\": \"active\",\n" +
                        "  \"mc_number\": \""+mcInput+"\",\n" +
                        "  \"dot_number\": \""+dotInput+"\",\n" +
                        "  \"ifta\": false,\n" +
                        "  \"address\": \"321 My ave\",\n" +
                        "  \"apt_suite_company_co\": null,\n" +
                        "  \"city\": \"CHicago\",\n" +
                        "  \"state\": \"IL\",\n" +
                        "  \"zip_code\": \"66667\",\n" +
                        "  \"insurance\": \"TestProd\",\n" +
                        "  \"producer_address\": \"321 Your ave\",\n" +
                        "  \"producer_apt_suite_company_co\": null,\n" +
                        "  \"producer_city\": \"Austin\",\n" +
                        "  \"producer_state\": \"TX\",\n" +
                        "  \"producer_zip_code\": \"67466\",\n" +
                        "  \"policy_effective_day\": null,\n" +
                        "  \"policy_expiration\": null,\n" +
                        "  \"type_of_insurance\": null,\n" +
                        "  \"automobile_liability\": \"\",\n" +
                        "  \"num_of_truck_insured\": null,\n" +
                        "  \"policy_number\": null,\n" +
                        "  \"employer_id_num\": \"87-7635242\",\n" +
                        "  \"billing_address\": null,\n" +
                        "  \"bank_name\": null,\n" +
                        "  \"routing_number\": null,\n" +
                        "  \"account_number\": null,\n" +
                        "  \"president_full_name\": null,\n" +
                        "  \"trucks_in_fleet\": null,\n" +
                        "  \"scac_code\": \"\",\n" +
                        "  \"other_licenses\": false,\n" +
                        "  \"license_name\": null,\n" +
                        "  \"incorporated_in\": null,\n" +
                        "  \"warning\": \"\",\n" +
                        "  \"notes\": null,\n" +
                        "  \"company_picture\": [],\n" +
                        "  \"company_documents\": [],\n" +
                        "  \"contacts\": [\n" +
                        "    {\n" +
                        "      \"phone\": \"983-735-3422\",\n" +
                        "      \"ext\": \"\",\n" +
                        "      \"contact_name\": \"\",\n" +
                        "      \"email\": \"test@gmail.com\",\n" +
                        "      \"fax\": \"\",\n" +
                        "      \"producer_phone\": \"786-757-6521\",\n" +
                        "      \"producer_phone_ext\": \"\",\n" +
                        "      \"producer_contact_name\": \"\",\n" +
                        "      \"producer_email\": \"prod@gmail.com\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"phone_number\": [\n" +
                        "    {\n" +
                        "      \"phone\": \"983-735-3422\",\n" +
                        "      \"ext\": \"\",\n" +
                        "      \"contact_name\": \"\",\n" +
                        "      \"email\": \"test@gmail.com\",\n" +
                        "      \"fax\": \"\",\n" +
                        "      \"producer_phone\": \"786-757-6521\",\n" +
                        "      \"producer_phone_ext\": \"\",\n" +
                        "      \"producer_contact_name\": \"\",\n" +
                        "      \"producer_email\": \"prod@gmail.com\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"fax_number\": [\n" +
                        "    {\n" +
                        "      \"fax\": \"\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"email_number\": [\n" +
                        "    {\n" +
                        "      \"email\": \"test@gmail.com\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"producer_email_number\": [\n" +
                        "    {\n" +
                        "      \"producer_email\": \"prod@gmail.com\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"producer_phone_number\": [\n" +
                        "    {\n" +
                        "      \"producer_phone\": \"786-757-6521\",\n" +
                        "      \"producer_phone_ext\": \"\",\n" +
                        "      \"producer_contact_name\": \"\"\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")
                .when().post("/companies/");

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


    }
}
