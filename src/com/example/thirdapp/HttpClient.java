package com.example.thirdapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HttpClient  extends Activity implements OnClickListener
{
     TextView http;
     EditText url;
     Button sub;
     String web="";
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.httpclienlayout);
		http=(TextView)findViewById(R.id.tvHC);
		intialise();
		sub.setOnClickListener(this);
		
		
		
		
	}
	private void intialise() {
		// TODO Auto-generated method stub
		sub=(Button)findViewById(R.id.bHttp);
		url=(EditText)findViewById(R.id.etUrl);
		
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		web=url.getText().toString();
		
		HttpInternet obj=new HttpInternet();
		try {
			String returnText=obj.getInternetData(web);
			http.setText(returnText);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
 
}
