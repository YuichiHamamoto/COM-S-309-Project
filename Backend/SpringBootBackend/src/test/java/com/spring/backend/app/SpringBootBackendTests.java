package com.spring.backend.app;

import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import io.restassured.response.Response;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;


class LoginInfoTests {
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getUserNameTest() {
		String response = "password";
		Assert.assertEquals(response, "password");
	}
}
