package com.example.thirdapp;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Click_2_Stick extends Activity implements View.OnClickListener
{
   
	
	ImageView vi;ImageButton ci;Button sw;Intent ca;final static int cameraData=0;
	Bitmap bmp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);
		intialise_variable();
		InputStream is=getResources().openRawResource(R.drawable.startbg);
		bmp=BitmapFactory.decodeStream(is);
		
		
	}

	private void intialise_variable() 
	{
		// TODO Auto-generated method stub
		 vi=(ImageView) findViewById(R.id.ivClickedImage);
		 ci=(ImageButton)findViewById(R.id.ibClickImage);
		 sw=(Button)findViewById(R.id.bWallpaper);
		 ci.setOnClickListener(this);
		 sw.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) 
	{
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.ibClickImage:
		ca=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)	;
		startActivityForResult(ca,cameraData);
		break;
		case R.id.bWallpaper:
			
			try
			{
				getApplicationContext().setWallpaper(bmp);
			}catch(IOException e)
			{
				e.printStackTrace();
			}
		break;
		
		}
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK)
		{
			Bundle e=data.getExtras();
			bmp=(Bitmap) e.get("data");
			vi.setImageBitmap(bmp);
			
			
		}
	}

	
	
	
}
