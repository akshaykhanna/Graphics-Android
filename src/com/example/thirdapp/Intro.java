package com.example.thirdapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;


public class Intro extends Activity 
{

	MediaPlayer c;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.intro);
		
		c=MediaPlayer.create(Intro.this,R.raw.coin);
		
		//getting preference for finding music checkbox is ticked on off by user
		SharedPreferences getP=PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		boolean music=getP.getBoolean("music", true);
		
		if(music)
		c.start();
		
		Thread timer= new Thread()
		{

			@Override
			public void run() 
			{
				// TODO Auto-generated method stub
				
				try
				{
					sleep(2000);
					
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
					
				}
				finally
				{
					Intent sta2= new Intent("com.example.thirdapp.MENU");
					startActivity(sta2);
					
				}
			
				
				
			}
		
		};
		timer.start();
		
		
		
		
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
		c.release();
		
	}
	

}
