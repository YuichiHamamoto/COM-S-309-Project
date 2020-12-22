package com.spring.backend.app;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ControllerEndpointTests {
    private static final String API_ROOT = "http://localhost:8080/api";

    @Test
    public void getUserNameTest() {
        Response response = RestAssured.get(API_ROOT + "/login/user");
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

    }
    @Test
    public void getUserFilesTest() {
        Response response = RestAssured.get(API_ROOT + "/files/user");
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }
    @Test
    public void getStatTests() {
        Response response = RestAssured.get(API_ROOT + "/stats/user");
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

    }
    @Test
    public void postUserNameTestNoBody() {
        LoginInfo info = new LoginInfo("user", "pass", "email");
        Response response = RestAssured.post(API_ROOT + "/login/addUser");
        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), response.getStatusCode());
    }
}
