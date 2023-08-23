package mock;

import com.github.tomakehurst.wiremock.client.WireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class APIMocks {

    public static void getDummyUser(){
        stubFor(get(urlEqualTo("/api/users"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"name\": \"Naveen\"}")));
    }

    public static void createDummyUser(){
        stubFor(post(urlEqualTo("/api/users"))
                .withHeader("Content-Type", WireMock.equalTo("application/json"))
                .withRequestBody(equalToJson("{\"name\": \"Naveen\"}"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"message\": \"Created!\"}")));
    }

    public static void getWithQueryParams() {
        stubFor(get(urlPathEqualTo("/api/users"))
                .withQueryParam("param", equalTo("value")) // Query parameter
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"result\": \"Query success!\"}")));
    }

    public static void updateDummyUser() {
        stubFor(put(urlEqualTo("/api/users"))
                .withHeader("Content-Type", WireMock.equalTo("application/json"))
                .withRequestBody(equalToJson("{\"name\": \"UpdatedName\"}"))
                .willReturn(aResponse()
                        .withStatus(200))); // No content response
    }

    public static void deleteDummyUser() {
        stubFor(delete(urlEqualTo("/api/users"))
                .willReturn(aResponse()
                        .withStatus(204))); // No content response
    }

}
