package com.gsoeller.fakebook.Http;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;

import com.google.common.collect.Maps;

public class HttpRequest {
	private final HttpMethod method;
	private final String host;
	private final int port;
	private final String path;
	private final HashMap<String, String> headers;
	
	private static final String VERSION = "HTTP/1.1";
	private static final String USER_AGENT = "Fakebook Crawler";
	
	private final HttpClient httpClient;
	
	private HttpRequest(HttpRequestBuilder builder) {
		this.method = builder.method;
		this.host = builder.host;
		this.port = builder.port;
		this.headers = builder.headers;
		this.path = builder.path;
		this.httpClient = builder.httpClient;
	}
	
	public String buildRequest() {
		String initialRequestLine = String.format("%s %s %s\r\n", method.name(), path, VERSION);
		String userAgentLine = String.format("User-Agent: %s\r\n", USER_AGENT);
		String hostLine = String.format("Host: %s\r\n", host);
		String headers = "";
		for(String header: this.headers.keySet()) {
			headers += String.format("%s: %s\r\n", header, this.headers.get(header));
		}
		return initialRequestLine + userAgentLine + hostLine + headers;
	}
	
	public void sendRequest() {
		try {
			httpClient.connect();
			String response = httpClient.sendRequest(this);
			System.out.println(response);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static class HttpRequestBuilder {
		private final String host;
		private final HttpClient httpClient;
		
		private String path = "/";
		private int port = 80;
		private HttpMethod method = HttpMethod.GET;
		private HashMap<String, String> headers = Maps.newHashMap();
		
		public HttpRequestBuilder(String host, HttpClient httpClient) {
			this.host = host;
			this.httpClient = httpClient;
		}
		
		public HttpRequestBuilder setPath(String path) {
			this.path = path;
			return this;
		}
		
		public HttpRequestBuilder addHeader(String headerName, String headerValue) {
			this.headers.put(headerName, headerValue);
			return this;
		}
		
		public HttpRequestBuilder setPort(int port) {
			this.port = port;
			return this;
		}
	
		
		public HttpRequestBuilder setMethod(HttpMethod method) {
			this.method = method;
			return this;
		}
		
		public HttpRequest build() {
			return new HttpRequest(this);
		}
	}
}
