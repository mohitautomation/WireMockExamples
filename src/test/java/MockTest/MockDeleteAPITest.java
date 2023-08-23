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

public class MockDeleteAPITest {

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
    public void testDeleteDummyUser() {
        APIMocks.deleteDummyUser(); // Stub the DELETE endpoint

        Response response = RestAssured
                .given()
                .when()
                .delete("/api/users");

        response.then()
                .statusCode(204); // No content response
    }

}
