package com.WebApiAutomation;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Get401 {

	public static final String BaseEndpoint = "https://api.github.com/";
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
	
	@DataProvider
	
	private Object[][] endpoints(){
		return new Object[][] {
			{"user"},
			{"user/followers"},
			{"notifications"}
			
		};
		
	}
	
	@Test(dataProvider = "endpoints")

	public void userReturns401(String endpoints) throws Exception, IOException {

		HttpGet get = new HttpGet(BaseEndpoint + endpoints);

		response = client.execute(get);

		int actualStatus = response.getStatusLine().getStatusCode();

		System.out.println(actualStatus);

		assertEquals(actualStatus, 401);

	}

	/*@Test(dataProvider = "endpoints")

	public void userFollowersReturns401(String endpoints) throws Exception, IOException {

		HttpGet get = new HttpGet(BaseEndpoint + endpoints);

		response = client.execute(get);

		int actualStatus = response.getStatusLine().getStatusCode();

		System.out.println(actualStatus);

		assertEquals(actualStatus, 401);

	}
	
	@Test(dataProvider = "endpoints")

	public void notificationReturns401(String endpoints) throws Exception, IOException {

		HttpGet get = new HttpGet(BaseEndpoint + endpoints);

		response = client.execute(get);

		int actualStatus = response.getStatusLine().getStatusCode();

		System.out.println(actualStatus);

		assertEquals(actualStatus, 401);

	}*/
}
