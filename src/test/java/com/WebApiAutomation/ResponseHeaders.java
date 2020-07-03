package com.WebApiAutomation;

import static org.testng.Assert.assertEquals;


import java.util.List;
import java.io.IOException;
import java.util.Arrays;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ResponseHeaders {

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

	public void contetnTypeIsJson() throws IOException {

		HttpGet get = new HttpGet(BaseEndpoint);

		response = client.execute(get);

		Header contentType = response.getEntity().getContentType();
		// System.out.println(response.getStatusLine());

		assertEquals(contentType.getValue(), "application/json; charset=utf-8");

		ContentType ct = ContentType.getOrDefault(response.getEntity());

		assertEquals(ct.getMimeType(), "application/json");

	}

	@Test

	public void serverIsGithub() throws IOException {

		HttpGet get = new HttpGet(BaseEndpoint);

		response = client.execute(get);

		
		String headerValue = BaseCalass.getHeader(response, "Server");
		
		assertEquals(headerValue, "GitHub.com");

	}



}
