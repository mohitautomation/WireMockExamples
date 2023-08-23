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

public class MockPutAPITest {
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
    public void testUpdateDummyUser() {
        APIMocks.updateDummyUser(); // Stub the PUT endpoint

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body("{\"name\": \"UpdatedName\"}") // Set the request body
                .when()
                .put("/api/users");

        response.then()
                .statusCode(200); // No content response
    }


}
