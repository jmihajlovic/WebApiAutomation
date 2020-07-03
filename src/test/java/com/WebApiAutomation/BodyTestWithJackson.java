package com.WebApiAutomation;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.NotFound;
import entities.RateLimit;
import entities.User;

import org.testng.Assert;
import org.testng.AssertJUnit;

import static entities.User.ID;
import static entities.User.LOGIN;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BodyTestWithJackson extends BaseCalass {

	String BaseEndpoint = "https://api.github.com/";
	CloseableHttpClient client;
	CloseableHttpResponse response;

	@BeforeMethod

	public void setup() {
		client = HttpClientBuilder.create().build();
	}

	@AfterMethod

	public void closeResources() throws IOException {
		client.close();
		response.close();
	}

	@Test

	public void returnCorrectLogin() throws IOException {

		HttpGet get = new HttpGet(BaseEndpoint + "users/andrejss88");
		
		response = client.execute(get);
		
		User user = unmarshall(response, User.class); 
		
		AssertJUnit.assertEquals(user.getLogin(), "andrejss88");
		
	}
	
	@Test

	public void returnCorrectId() throws IOException {

		HttpGet get = new HttpGet(BaseEndpoint + "users/andrejss88");
		
		response = client.execute(get);
		
		User user = unmarshallGeneric(response, User.class); 
		
		assertEquals(user.getId(), 11834443);
		
	}
	
	@Test

	public void notFoundMassageIsCorrect() throws IOException {

		HttpGet get = new HttpGet(BaseEndpoint + "nonexistingendpoint");
		
		response = client.execute(get);
		
		NotFound notFoundMassage = unmarshallGeneric(response, NotFound.class); 
		
		System.out.println(notFoundMassage.getMessage());
		
		assertEquals(notFoundMassage.getMessage(), "Not Found");
		
	}
	
	
	@Test

	public void correctRAteLimitsAreSet() throws IOException {

		HttpGet get = new HttpGet(BaseEndpoint + "rate_limit");
		
		response = client.execute(get);
		
		RateLimit notFoundMassage = unmarshallGeneric(response, RateLimit.class); 
		
		System.out.println(notFoundMassage.getCoreLimit());
		
		assertEquals(notFoundMassage.getCoreLimit(), 60);
		
		assertEquals(notFoundMassage.getSearchLimit(), "10");
		
	}

	private User unmarshall(CloseableHttpResponse response2, Class<User> clazz) throws IOException {
		String jsonBody = EntityUtils.toString(response2.getEntity()); 
		
		return new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.readValue(jsonBody, clazz); 
		
	}

	private <T> T unmarshallGeneric(CloseableHttpResponse response2, Class<T> clazz) throws IOException {
		String jsonBody = EntityUtils.toString(response2.getEntity()); 
		
		return new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				.readValue(jsonBody, clazz); 
		
	}
	

}
