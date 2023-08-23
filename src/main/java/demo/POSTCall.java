package demo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import pojo.Person;


public class POSTCall {
	
	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
       // String requestBody = "{\"name\":\"John\",\"job\":\"developer\"}";
        
        
        Person person = new Person();
        person.setName("John");
        person.setJob("developer");

        // Serialize the Person object to JSON
        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(person);
        
    
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://reqres.in/api/users"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

}
