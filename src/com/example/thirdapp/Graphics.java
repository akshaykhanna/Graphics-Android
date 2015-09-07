package com.example.thirdapp;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

public class Graphics extends Activity
{
    
	AnimationClass ourView;
	WakeLock wL;

	int idOfBMusic=0;;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//For fullscreen view
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//for wake lock : below command will switch on the lights and prevent locking of screen till infinity
		PowerManager pM=(PowerManager)getSystemService(Context.POWER_SERVICE);
		wL=pM.newWakeLock(PowerManager.FULL_WAKE_LOCK, "Niether off nor lock");
		
		ourView=new AnimationClass(this);
		setContentView(ourView);
		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//will end wake lock method when activity ends on pressing back button
		wL.release();
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//To start wake lock
		wL.acquire();
		
	}
	

}