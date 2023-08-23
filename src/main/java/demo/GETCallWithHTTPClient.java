package demo;

import java.io.IOException;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

public class GETCallWithHTTPClient {

	public static void main(String[] args) throws IOException, ParseException {

		HttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("https://reqres.in/api/users?page=2");

		httpClient.execute(httpGet, response -> {
			int statusCode = response.getCode();
			if (statusCode == 200) {
				String responseBody = EntityUtils.toString(response.getEntity());
				System.out.println("Response: " + responseBody);
			} else {
				System.out.println("Request failed with status code: " + statusCode);
			}
			return response;
		});

	}
}

