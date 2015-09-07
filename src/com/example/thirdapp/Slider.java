package com.example.thirdapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SlidingDrawer;

import android.widget.SlidingDrawer.OnDrawerOpenListener;


@TargetApi(Build.VERSION_CODES.CUPCAKE)
public class Slider extends Activity implements OnClickListener, OnCheckedChangeListener, OnDrawerOpenListener
{
	
	SlidingDrawer sd; MediaPlayer openSound;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slider);
		Button h1=(Button)findViewById(R.id.button1);
		Button h2=(Button)findViewById(R.id.button2);
		Button h3=(Button)findViewById(R.id.button3);
		Button h4=(Button)findViewById(R.id.button4);
		CheckBox cb=(CheckBox)findViewById(R.id.cbSlideAble);
		sd=(SlidingDrawer)findViewById(R.id.slidingDrawer1);
		h1.setOnClickListener(this);
		h2.setOnClickListener(this);
		h3.setOnClickListener(this);
		h4.setOnClickListener(this);
		cb.setOnCheckedChangeListener(this);
		sd.setOnDrawerOpenListener(this);
		openSound.create(this, R.raw.bomb);
		
	}
	
	@TargetApi(Build.VERSION_CODES.CUPCAKE)
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.button1:
			sd.open();
			break;
		case R.id.button2:
			break;
		case R.id.button3:
			sd.toggle();
			break;
		case R.id.button4:sd.close();
			break;
		}
		
	}
	
	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub
		if(arg0.isChecked())
			sd.lock();
		else
			sd.unlock();
		
	}
	@Override
	public void onDrawerOpened() {
		// TODO Auto-generated method stub
		openSound.start();
		
	}

	
}
