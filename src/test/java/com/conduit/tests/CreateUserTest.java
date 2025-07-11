package com.conduit.tests;

import com.conduit.model.User;
import com.conduit.model.UserRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest {

    @Test
    public void createUserWithPojo() {
        // Generate a unique email
        String uniqueEmail = "user_" + UUID.randomUUID() + "@example.com";
        User user = new User("Ned" + UUID.randomUUID().toString().substring(0, 5), uniqueEmail, "W@lterWh1te");
        UserRequest userRequest = new UserRequest(user);

        RestAssured.baseURI = "https://conduit.mate.academy";

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(userRequest)
                .when()
                .post("/api/users/")
                .then()
                .log().ifValidationFails()
                .statusCode(200)  // Expecting Created
                .body("user.username", equalTo(user.getUsername().toLowerCase()))
                .body("user.email", equalTo(user.getEmail()))
                .extract().response();

        System.out.println("Response:\n" + response.asPrettyString());
    }
}
