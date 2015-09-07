package com.example.thirdapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Data extends Activity implements OnClickListener
{
    
	Button bSa,bSafr;
	EditText etSend;
	TextView tvGet;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get);
		intitialiseVari();
		
	}
	private void intitialiseVari() 
	{
		// TODO Auto-generated method stub
		bSa=(Button)findViewById(R.id.bSA);
		bSafr=(Button)findViewById(R.id.bSAFR);
		etSend=(EditText)findViewById(R.id.etSend);
		tvGet=(TextView )findViewById(R.id.tvGot);
		bSa.setOnClickListener(this);
		bSafr.setOnClickListener(this);
		
		
	}
	@Override
	public void onClick(View v) 
	{
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.bSA:
			String bread=etSend.getText().toString();
			Bundle sendData=new Bundle();
			sendData.putString("key_data",bread);
			Intent startOC=new Intent(Data.this,OpenedClass.class);
			startOC.putExtras(sendData);
			startActivity(startOC);
			
			break;
		case R.id.bSAFR:
			Intent startOCFR=new Intent(Data.this,OpenedClass.class);
			startActivityForResult(startOCFR,0);
			break;
		}
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}
 
	
}
