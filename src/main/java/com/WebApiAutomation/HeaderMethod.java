package com.WebApiAutomation;

import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;

public class HeaderMethod {

	
	static String getHeader(CloseableHttpResponse response, String headerName) {

		Header[] headers = response.getAllHeaders();

		List<Header> httpHeaders = Arrays.asList(headers);

		String returnHeader = "";

		for (Header header : httpHeaders) {

			if (headerName.equalsIgnoreCase(header.getName())) {
				returnHeader = header.getValue();
			}
		}
		
		if(returnHeader.isEmpty()) {
			throw new RuntimeException("Didn't find the header: " + headerName); 
		}

		return returnHeader; 
	}
}
