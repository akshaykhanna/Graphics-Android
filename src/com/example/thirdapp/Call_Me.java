package com.example.thirdapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Call_Me extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Intent call=new Intent(android.content.Intent.ACTION_DIAL);
		call.putExtra(android.content.Intent.EXTRA_PHONE_NUMBER, "8860110036");
		startActivity(call);
	}
  
}
