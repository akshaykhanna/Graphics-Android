package com.example.thirdapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQL_Lite extends Activity implements OnClickListener
{
	
    EditText etN,etH;
    Button bU,bV;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sql1);
		intialise();
	}
	private void intialise() {
		// TODO Auto-generated method stub
		etN=(EditText)findViewById(R.id.etName);
		etH=(EditText)findViewById(R.id.etHotness);
		bU=(Button)findViewById(R.id.bUpdateSql);
		bV=(Button)findViewById(R.id.bView);
		bU.setOnClickListener(this);
		bV.setOnClickListener(this);
	}
	@Override
	public void onClick(View arg0) 
	{
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
			case R.id.bUpdateSql:
				boolean didItWork=true;
		try
		{
				
			String name=etN.getText().toString();
			String hot=etH.getText().toString();
			HotnessLevel entry=new HotnessLevel(SQL_Lite.this);
			entry.write();
			entry.createEnteries(name,hot);
			entry.close();
		}
		catch(Exception e)
		{
			didItWork=false;
			Dialog d=new Dialog(this);
			d.setTitle("Unable to update");
			d.show();
			
		}
		finally
		{
		Dialog dd=new Dialog(this);
		dd.setTitle("DB Updated");
		
		//Creating new text view
		TextView tv=new TextView(this);
		tv.setText("Success");
		dd.setContentView(tv);
		dd.show();
		}
			break;
		case R.id.bView:
			Intent  i=new Intent("com.example.thirdapp.SqlView");
			startActivity(i);
			break;
		}
		
		
	}

}
