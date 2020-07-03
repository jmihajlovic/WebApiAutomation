package com.WebApiAutomation;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import static entities.User.ID;
import static entities.User.LOGIN;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

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

public class BodyTestWithSimpleMap {

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

		String jsonBody = EntityUtils.toString(response.getEntity());

		JSONObject jsonObject = new JSONObject(jsonBody);

		String loginValue = (String) getValueFor(jsonObject, LOGIN);

		System.out.println(loginValue);

		AssertJUnit.assertEquals(loginValue, "andrejss88");

	}

	@Test

	public void returnCorrectId() throws IOException {

		HttpGet get = new HttpGet(BaseEndpoint + "users/andrejss88");
		response = client.execute(get);

		String jsonBody = EntityUtils.toString(response.getEntity());

		JSONObject jsonObject = new JSONObject(jsonBody);

		int loginValue = (Integer) getValueFor(jsonObject, ID);

		System.out.println(loginValue);

		AssertJUnit.assertEquals(loginValue, 11834443);

	}

	private Object getValueFor(JSONObject jsonObject, String key) {

		return jsonObject.get(key);
	}

}
