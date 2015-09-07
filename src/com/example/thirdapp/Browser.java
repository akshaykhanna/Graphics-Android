package com.example.thirdapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class Browser extends Activity implements OnClickListener 
{

	EditText url; Button go,home,forward,backward,reload;
	String site;WebView ourUrl;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//For full screen view
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.browser);
		
		intialise();
		// Referring webview for xml file and  referring its object with ourUrl
		ourUrl=(WebView)findViewById(R.id.wvBrowser);
		
		
			ourUrl.loadUrl("http://www.google.com");
			ourUrl.setWebViewClient(new client());
		
		ourUrl.getSettings().setJavaScriptEnabled(true);
		
		
		ourUrl.getSettings().setUseWideViewPort(true);
	    
		go.setOnClickListener(this);
		home.setOnClickListener(this);
		forward.setOnClickListener(this);
		backward.setOnClickListener(this);
		reload.setOnClickListener(this);
		
	}

	private void intialise() 
	{
		// TODO Auto-generated method stub
		url=(EditText)findViewById(R.id.etUrl);
		go=(Button)findViewById(R.id.bGo);
		home=(Button)findViewById(R.id.bHome);
		forward=(Button)findViewById(R.id.bForward);
		backward=(Button)findViewById(R.id.bBack);
		reload=(Button)findViewById(R.id.bReload);
	}

	@Override
	public void onClick(View arg0) 
	{
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.bGo:
			site=url.getText().toString();
			ourUrl.loadUrl(site);
			break;
		case R.id.bHome:
			ourUrl.loadUrl("http://www.google.com");
			break;
		case R.id.bForward:
			if(ourUrl.canGoForward())
				ourUrl.goForward();
			break;
		case R.id.bBack:
			if(ourUrl.canGoBack())
				ourUrl.goBack();
			break;
		}
	}
	



}

	