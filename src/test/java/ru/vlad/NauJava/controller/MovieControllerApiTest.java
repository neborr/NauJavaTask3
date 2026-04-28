package ru.vlad.NauJava.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieControllerApiTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    void testGetMoviesList_Success() {
        given()
                .auth().preemptive().basic("123", "123456799")
                .log().all()
                .when()
                .get("/custom/movies/search")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    void testGetSession_NotFound() {
        given()
                .auth().preemptive().basic("123", "123456799")
                .log().all()
                .when()
                .get("/custom/movies/non-existent-path")
                .then()
                .log().all()
                .statusCode(404);
    }
}