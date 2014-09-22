package com.gsoeller.fakebook.Http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.common.net.InternetDomainName;

public class HttpClient {

	private static final InternetDomainName DOMAIN = InternetDomainName
			.from("gsoeller.com");
	private static final int PORT = 80;
	private static final String HOST = "cs5700f14.ccs.neu.edu";
	private Socket socket;
	private BufferedWriter wr;
	private BufferedReader br;
	
	public HttpClient() throws UnknownHostException, IOException {
		connect();
	}
	
	
	public void connect() throws UnknownHostException, IOException {
		socket = new Socket(HOST, PORT);
		//wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
		//br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public void disconnect() throws IOException {
		//wr.close();
		//br.close(); 
		socket.close();
	}
	
	@SuppressWarnings("deprecation")
	public String sendRequest(HttpRequest request) throws UnknownHostException, IOException { 
		//wr.write("GET /accounts/login/?next=/fakebook/ HTTP/1.1\r\nHost: " + HOST + "\r\nUser-Agent: Fakebook Crawler\r\n\r\n");
		//wr.write("Host: " + HOST + "\r\n");
		//wr.write("User-Agent: Fakebook Crawler\r\n");
		//wr.write("\r\n");
		//wr.flush();
		
		//String content = "";
		//String line;
		//while((line = br.readLine()) != null) {
			//content += line;
			//System.out.println(line);
		//}
		
		OutputStream outToServer = socket.getOutputStream();
		DataOutputStream out = new DataOutputStream(outToServer);
		//out.writeUTF("GET /accounts/login/?next=/fakebook/ HTTP/1.1\r\nHost: " + HOST + "\r\nUser-Agent: Fakebook Crawler\r\n\r\n");
		out.writeUTF(request.buildRequest());
		InputStream inFromServer = socket.getInputStream();
        DataInputStream in = new DataInputStream(inFromServer);
        String line;
        String content = "";
        while((line = in.readLine()) != null) {
        	content += line + "\n";
        }
        out.close();
        in.close();
		return content;
	}
}
