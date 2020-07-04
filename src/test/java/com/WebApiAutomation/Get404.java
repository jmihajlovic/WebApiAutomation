package com.WebApiAutomation;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Get404 {

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

	@Test

	public void notEistingUrlReturns404() throws Exception, IOException {

		HttpGet get = new HttpGet(BaseEndpoint + "nonexistingurl");

		response = client.execute(get);

		int actualStatus = response.getStatusLine().getStatusCode();

		System.out.println(actualStatus);

		Assert.assertEquals(actualStatus, 404);

	}

}
