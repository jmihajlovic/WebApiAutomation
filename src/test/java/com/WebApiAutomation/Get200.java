package com.WebApiAutomation;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Get200 extends BaseClass{
	
	@Test

	public void baseUrlReturns200() throws Exception, IOException {

		HttpGet get = new HttpGet(BASE_ENDPOINT);

		response = client.execute(get);

		int actualStatus = response.getStatusLine().getStatusCode();

		System.out.println(actualStatus);

		Assert.assertEquals(actualStatus, 200);

	}

	@Test

	public void rateLimitReturns200() throws Exception, IOException {

		HttpGet get = new HttpGet(BASE_ENDPOINT + "rate_limit");

		response = client.execute(get);

		int actualStatus = response.getStatusLine().getStatusCode();

		System.out.println(actualStatus);

		Assert.assertEquals(actualStatus, 200);

	}

	@Test

	public void searchReposReturns200() throws Exception, IOException {

		HttpGet get = new HttpGet(BASE_ENDPOINT + "search/repositories?q=java");

		HttpResponse response = client.execute(get);

		int actualStatus = response.getStatusLine().getStatusCode();

		System.out.println(actualStatus);

		Assert.assertEquals(actualStatus, 200);

	}
	

}
