package com.ywg.httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClient {
	private static  RequestConfig requestConfig;
	private static PoolingHttpClientConnectionManager connectPool=null;
	
	static {
		requestConfig=RequestConfig.custom()
					.setConnectionRequestTimeout(60000)
					.setConnectTimeout(10000)
					.setSocketTimeout(60000).build();
	}
	@Test
	public void name() {
		initConnectPool();
		Thread[] threads=new Thread[50];
		for (int i = 0; i < threads.length; i++) {
			threads[i]=new Thread(new  Runnable() {
				@Override
				public void run() {
					get();
				}
			});
		}
		
		for (int j = 0; j < threads.length; j++) {
			System.out.println(j);
			threads[j].start();
		}
		
	}
	
	public void initConnectPool() {
		ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();  
        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();  
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()  
                .register("http", plainsf)  
                .register("https", sslsf) 
                .build();
        connectPool=new PoolingHttpClientConnectionManager(registry);
        connectPool.setDefaultMaxPerRoute(50);
        connectPool.setMaxTotal(200);        
	}
	@Test
	public void get() {
		CloseableHttpClient httpClient=HttpClients.custom()
				.setConnectionManager(connectPool)
				.setDefaultRequestConfig(requestConfig)
				.build();
		System.out.println(connectPool.getTotalStats());
		URI uri=null;
		try {
			uri = new URI("http://localhost:8080/springweb/get?key=123");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpGet get=new HttpGet(uri);
		get.setHeader("User-Agent", "ywg");
		CloseableHttpResponse response=null;
		try {
			long time=System.currentTimeMillis();
			response =httpClient.execute(get);
			System.out.println(System.currentTimeMillis()-time);
			if (response.getStatusLine().getStatusCode()==HttpStatus.SC_OK) {
//				HttpEntity entity=response.getEntity();
//				String string=EntityUtils.toString(entity);
//				System.out.println(string);
			}
			response.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
