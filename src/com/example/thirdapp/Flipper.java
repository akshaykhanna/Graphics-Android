package com.example.thirdapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ViewFlipper;

public class Flipper extends Activity implements OnClickListener
{
   ViewFlipper f;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flipper);
		f=(ViewFlipper)findViewById(R.id.viewFlipper1);
		f.setOnClickListener(this);
		f.setFlipInterval(2000);
		f.startFlipping();
     }
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		f.showNext();
	}

}
