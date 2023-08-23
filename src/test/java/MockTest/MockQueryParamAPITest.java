package MockTest;

import mock.WireMockSetup;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import static org.hamcrest.Matchers.equalTo;

import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import mock.APIMocks;
import mock.WireMockSetup;
import org.testng.annotations.*;
import io.restassured.response.Response;


public class MockQueryParamAPITest {

    @BeforeTest
    public void setUp() {
        WireMockSetup.createWireMockServer(); // Start WireMock server
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8085; // Set the WireMock server port
    }

    @AfterTest
    public void tearDown() {
        WireMockSetup.stopWireMockServer(); // Stop WireMock server
    }

    @Test
    public void testGetWithQueryParams() {
        APIMocks.getWithQueryParams(); // Stub the GET endpoint

        Response response = RestAssured
                .given()
                .queryParam("param", "value") // Set the query parameter
                .when()
                .get("/api/users");

        response.then()
                .statusCode(200)
                .contentType("application/json")
                .body("result", equalTo("Query success!"));
    }

}
