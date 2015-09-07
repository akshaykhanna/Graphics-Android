package com.example.thirdapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;

public class HttpInternet extends Activity 
{
 public String getInternetData(String web)throws Exception
 {
	 BufferedReader br=null;
	 String data=null;
	 try{
	HttpClient client=new DefaultHttpClient();
		
			
		
		URI website= new URI(web);
		HttpGet request= new HttpGet(); // Requesting site to let HttpGet get data.
		request.setURI(website);//Setting web site from which we want to request.
		HttpResponse response=client.execute(request);
		br=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));//converting recived data form website into some readable thing
		StringBuffer sb= new StringBuffer("");
		String l="";
		String nl=System.getProperty("line.separator");
		while((l=br.readLine())!=null)
		{
			sb.append(l+nl);
		}
		br.close();
		data=sb.toString();
		return data;
	 }
	 finally{
		 if(br !=null)
			try{
				br.close();
				return data;
			}
		 catch(Exception e){
			 e.printStackTrace();
		 }
	 }
 }
}
