package com.example.thirdapp;

import java.util.Random;

import com.example.thirdapp.R.layout;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

@TargetApi(Build.VERSION_CODES.CUPCAKE) public class TextPlay extends Activity implements View.OnClickListener   
{

	ToggleButton tb1;
	Button b1;
	 EditText et1;
	 TextView tv1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.password);
		AkshayVariables();
		
		tb1.setOnClickListener(this); 
        
		 b1.setOnClickListener(this);
			
		
	}

	private void AkshayVariables() 
	{
		// TODO Auto-generated method stub
		b1=(Button) findViewById(R.id.bEnter);
		 tb1=(ToggleButton) findViewById(R.id.tbPass);
		 et1=(EditText) findViewById(R.id.etPassword);
		 tv1=(TextView) findViewById(R.id.tvStatus);
		
		
	}

	@SuppressLint("NewApi") @Override
	public void onClick(View v) {
		// TODO Auto-generated method stubs
		switch(v.getId())
		{
		case R.id.tbPass:
			
            if(tb1.isChecked())
            
            	et1.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);
            else
            	et1.setInputType(InputType.TYPE_CLASS_TEXT);
			break;
		case R.id.bEnter:
			// TODO Auto-generated method stub
			String check=et1.getText().toString();
			tv1.setText(check);
		if(check.equalsIgnoreCase("left"))
			tv1.setGravity(Gravity.LEFT);
		else if(check.equalsIgnoreCase("center"))
			tv1.setGravity(Gravity.CENTER);
		else if(check.equalsIgnoreCase("right"))
			tv1.setGravity(Gravity.RIGHT);
		else if(check.equalsIgnoreCase("bye"))
		     finish();
		else 
		{
		   Random crazy=new Random();
		   
		   tv1.setGravity(Gravity.CENTER);
		   tv1.setText("invalid");
		   tv1.setTextSize(crazy.nextInt(100));
		   tv1.setTextColor(Color.rgb(crazy.nextInt(265), crazy.nextInt(265), crazy.nextInt(265)));
		   switch(crazy.nextInt(3))
		   {
		   case 0:tv1.setGravity(Gravity.LEFT);
			   break;
		   case 1: tv1.setGravity(Gravity.CENTER);
		   break;
		   case 2: tv1.setGravity(Gravity.RIGHT) ;
		   break;
		   }
			break;
			
		}
		
		
	}

	

}}
