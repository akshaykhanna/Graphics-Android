package com.example.thirdapp;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

public class SoundSound extends Activity implements OnClickListener, OnLongClickListener
{
    SoundPool sP;
    MediaPlayer mp;
    int bomb=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View v= new View(this); 
	    setContentView(v);
	    v.setOnClickListener(this);
	    v.setOnLongClickListener(this);
	    
	    sP=new SoundPool(5,AudioManager.STREAM_MUSIC,0);
	    bomb=sP.load(this,R.raw.bomb,1);
	    
	    mp=MediaPlayer.create(this, R.raw.tune);
	    
	
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mp.release();
		sP.release();
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(bomb!=0)
		sP.play(bomb, 1, 1,0, 0, 1);
	}
	@Override
	public boolean onLongClick(View arg0) {
		// TODO Auto-generated method stub
		mp.start();
		return false;
	}

	
}
