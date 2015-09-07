package com.example.thirdapp;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.content.Intent;
import android.content.SharedPreferences;

public class OpenedClass extends Activity implements OnClickListener, OnCheckedChangeListener
{
	RadioGroup rg; TextView tvQ,tvA;Button bR;String setText;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		intialise();
		getQuestion();
		SharedPreferences gp=PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String et=gp.getString("name", "Akshay is....");
		String value=gp.getString("list", "4");
		if(value.equalsIgnoreCase("1"))
		 tvQ.setText(et);
		
		
	}

	private void getQuestion() //getting string from Data class and setting it as a question
	{
	  Bundle getData=getIntent().getExtras();
	  String getBread=getData.getString("key_data");
	  tvQ.setText(getBread);
		
	}

	private void intialise() {
		// TODO Auto-generated mIethod stub
		tvQ=(TextView)findViewById(R.id.tvQuestion);
		tvA=(TextView)findViewById(R.id.tvAnswer);
		bR=(Button)findViewById(R.id.bReturn);
		bR.setOnClickListener(this);
		rg=(RadioGroup)findViewById(R.id.rgAnswer);
		rg.setOnCheckedChangeListener(this);
	    
		
		
		
	}

	@Override
	public void onClick(View arg0) 
	{
		// TODO Auto-generated method stub
		if(arg0.getId()==R.id.bReturn)
		{
			Intent person=new Intent();
			Bundle backPack= new Bundle();
			backPack.putString("answer", setText);
			
			setResult(RESULT_OK,person);
			
			
	      }   
		}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1)
	{
		// TODO Auto-generated method stub
		switch(arg1)
		{
		case R.id.rGoli:
			setText="Goli mar bhaje pe";
			
			
			break;
        case R.id.rMiss:
        setText="Fit hai bosss";
			break;
        case R.id.rHookah:
        	setText="Doop maarrr";
			break;
		}
		tvA.setText(setText);// here first 'setText'(black one) is method and second 'seText'(blue one) is a variable
	}
	

	
}
