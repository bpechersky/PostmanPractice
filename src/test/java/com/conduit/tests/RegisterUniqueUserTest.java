package com.conduit.tests;

import com.conduit.model.User;
import com.conduit.model.UserRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;

public class RegisterUniqueUserTest {

    @Test
    public void registerUserWithUniqueEmailAndUsername() {
        // Generate unique values
        long timestamp = System.currentTimeMillis();
        String username = "roza" + timestamp;
        String email = "roza" + timestamp + "@gmail.com";
        String password = "roza12";

        // Create POJO payload
        User user = new User(username, email, password);
        UserRequest userRequest = new UserRequest(user);

        // Base URI
        RestAssured.baseURI = "https://conduit.mate.academy";

        // Send POST request to /api/users
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(userRequest)
                .when()
                .post("/api/users")
                .then()
                .statusCode(200) // or 201 if that's what the API returns
                .body("user.username", equalTo(username.toLowerCase())) // API lowercases usernames
                .body("user.email", equalTo(email))
                .extract().response();

        System.out.println("✅ Registered user: " + username);
        System.out.println("📧 Email: " + email);
        System.out.println("🔐 Token: " + response.path("user.token"));
    }
}
