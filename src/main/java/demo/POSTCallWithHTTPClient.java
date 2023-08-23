package demo;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.EntityBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import pojo.Person;

public class POSTCallWithHTTPClient {

	public static void main(String[] args) throws IOException {
		HttpClient httpClient = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost("https://reqres.in/api/users");
		
		try {
			System.out.println("Executing request to URL: " + httpPost.getUri());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
				
		httpPost.setHeader("Content-Type", "application/json");
		//using json String:
//		String jsonPayload = "{\"name\":\"John\",\"job\":\"developer\"}";
		
		//using pojo:
		Person person = new Person();
        person.setName("John");
        person.setJob("developer");

        // Serialize the Person object to JSON
        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload = mapper.writeValueAsString(person);
		httpPost.setEntity(EntityBuilder.create().setText(jsonPayload).build());

		httpClient.execute(httpPost, response -> {
			int statusCode = response.getCode();
			if (statusCode == 201) {
				String responseBody = EntityUtils.toString(response.getEntity());
				System.out.println("POST Response: " + responseBody);
				
				//De-Serialize:
				ObjectMapper newMapper = new ObjectMapper();
				Person personResponse = newMapper.readValue(responseBody, Person.class);
                System.out.println("Person object: " + personResponse);
                String personId = personResponse.getId();
                System.out.println(personId);
                
                System.out.println("-----------------");
                
                //GET CALL:
        		HttpGet httpGet = new HttpGet("https://reqres.in/api/users/"+personId);
                try {
					System.out.println("Executing request to URL: " + httpGet.getUri());
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}

        		httpClient.execute(httpGet, getResponse -> {
        			int getStatusCode = getResponse.getCode();
        			if (getStatusCode == 200) {
        				String responseGETBody = EntityUtils.toString(getResponse.getEntity());
        				System.out.println("Response GET: " + responseGETBody);
        			} else {
        				System.out.println("GET Request failed with status code: " + getStatusCode);
        			}
        			return response;
        		});
                
			} else {
				System.out.println("POST Request failed with status code: " + statusCode);
			}
			return response;
		});
		

	}

}
