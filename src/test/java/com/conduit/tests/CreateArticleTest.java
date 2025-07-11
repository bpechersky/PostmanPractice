package com.conduit.tests;

import com.conduit.model.Article;
import com.conduit.model.ArticleRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;

public class CreateArticleTest {

    @Test
    public void createArticle() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6OTA2ODgwLCJ1c2VybmFtZSI6ImtlbHZpbiIsImV4cCI6MTc1NzM4ODM4NSwiaWF0IjoxNzUyMjA0Mzg1fQ.4SCiDnMsKp1Dv_JYp_ArCo-Wn9H7oqUBcmNWEHvKCuo";

        Article article = new Article(
                "There is a Hell",
                "Believe Me I've Seen It",
                "There is a Heaven",
                Arrays.asList("Let's", "Keep", "it", "a", "Secret")
        );

        ArticleRequest articleRequest = new ArticleRequest(article);

        RestAssured.baseURI = "https://conduit.mate.academy";

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Token " + token)
                .body(articleRequest)
                .when()
                .post("/api/articles")
                .then()
                .statusCode(200)
                .body("article.title", equalTo(article.getTitle()))
                .body("article.description", equalTo(article.getDescription()))
                .extract().response();

        System.out.println("Response:\n" + response.asPrettyString());
    }
}
