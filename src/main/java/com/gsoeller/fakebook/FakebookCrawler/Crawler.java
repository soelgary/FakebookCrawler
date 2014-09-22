package com.gsoeller.fakebook.FakebookCrawler;

import java.io.IOException;
import java.net.UnknownHostException;

import com.gsoeller.fakebook.Http.HttpClient;
import com.gsoeller.fakebook.Http.HttpRequest;


/**
 * Hello world!
 *
 */
public class Crawler 
{
    public static void main( String[] args ) throws UnknownHostException, IOException
    {
        System.out.println( "Hello World!" );
        HttpClient httpClient = new HttpClient();
        HttpRequest request = new HttpRequest.HttpRequestBuilder("http://cs5700f14.ccs.neu.edu", httpClient).setPath("/fakebook/").build();
        request.sendRequest();
        request.sendRequest();
        httpClient.disconnect();
    }
}
