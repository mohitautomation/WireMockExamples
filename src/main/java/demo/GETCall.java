package demo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class GETCall {

	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException  {
		
		HttpRequest request = HttpRequest.newBuilder()
							.uri(new URI("https://reqres.in/api/users?page=2"))
							.GET()
							.build();
		
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
		System.out.println(response.statusCode());
		System.out.println(response.body());
		
		
		
        
        } 
    }


