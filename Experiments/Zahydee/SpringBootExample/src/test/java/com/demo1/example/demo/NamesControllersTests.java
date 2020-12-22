package com.demo1.example.demo;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;


import io.restassured.response.Response;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

class NamesControllersTests {
    private static final String API_ROOT = "http://localhost:8080/api/names";

    @Test
    public void getUserNameTest() {
        Response response = RestAssured.get(API_ROOT + "/1");
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals("Name1", response.getBody().asString());
    }
}
