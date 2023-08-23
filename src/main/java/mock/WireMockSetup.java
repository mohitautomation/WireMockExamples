package mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;

public class WireMockSetup {
    private static WireMockServer server;
    public static final int WIREMOCK_PORT = 8085; // Set your desired port

    public static void createWireMockServer() {
        server = new WireMockServer(8085);
        configureFor("localhost", 8085);
        server.start();
    }

    public static void stopWireMockServer(){
        server.stop();
    }


}
