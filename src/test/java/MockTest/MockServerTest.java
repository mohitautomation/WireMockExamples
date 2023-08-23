package MockTest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.hamcrest.Matchers.equalTo;

import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import mock.APIMocks;
import mock.WireMockSetup;
import org.testng.annotations.*;

import com.github.tomakehurst.wiremock.WireMockServer;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class MockServerTest {
    //WireMockServer server;

    @BeforeTest
    public void mockSetup(){
//        server = new WireMockServer(8085);
//        configureFor("localhost", 8085);
//        server.start();
        WireMockSetup.createWireMockServer();
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8085;
    }

    @AfterTest
    public void tearDown() {
        WireMockSetup.stopWireMockServer();
    }

    @Test
    public void testMockGETAPI() throws Exception {
//        stubFor(get(urlEqualTo("/api/endpoint"))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", "application/json")
//                        .withBody("{\"message\": \"Hello, World!\"}")));

        APIMocks.getDummyUser();

        Response response = RestAssured.get("/api/endpoint");

        RestAssured.given()
                        .when()
                            .get("/api/endpoint")
                                .then().log().all()
                                    .body("name", equalTo("Naveen"));


        System.out.println(response.getBody().asString());

    }




}