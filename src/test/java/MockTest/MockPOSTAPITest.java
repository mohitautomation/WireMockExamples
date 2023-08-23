package MockTest;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import mock.APIMocks;
import mock.WireMockSetup;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.*;


public class MockPOSTAPITest {
    //WireMockServer server;

    @BeforeMethod
    public void mockSetup(){
//        server = new WireMockServer(8085);
//        configureFor("localhost", 8085);
//        server.start();
        WireMockSetup.createWireMockServer();
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8085;
    }

    @AfterMethod
    public void tearDown() {
        //server.stop();
        WireMockSetup.stopWireMockServer();
    }

    @Test
    public void testMockPostApi() {
//        stubFor(post(urlEqualTo("/api/endpoint"))
//                .withHeader("Content-Type", WireMock.equalTo("application/json"))
//                .withRequestBody(equalToJson("{\"name\": \"Naveen\"}"))
//                .willReturn(aResponse()
//                        .withStatus(201)
//                        .withHeader("Content-Type", "application/json")
//                        .withBody("{\"message\": \"Created!\"}")));
        APIMocks.createDummyUser();

        Response response = given()
                .contentType("application/json")
                .body("{\"name\": \"Naveen\"}") // Corrected request body
                .when()
                .post("/api/endpoint");

        response.then()
                .statusCode(201)
                .contentType("application/json")
                .body("message", equalTo("Created!"));

        System.out.println(response.getBody().asString());
    }
}
