package com.conduit.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetCallsListTest {

    @Test
    public void getCallsList() {
        String apiKey = "uhA1KAyg0Ultns2icwdxHZxLZ15C6fH2";

        RestAssured.baseURI = "https://api.ringostat.net";

        Response response = RestAssured
                .given()
                .header("Auth-key", apiKey)
                .contentType(ContentType.JSON)
                .when()
                .get("/calls/list")
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("Calls List Response:\n" + response.asPrettyString());
    }
}
