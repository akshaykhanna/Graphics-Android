package com.example.thirdapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

public class pk extends Activity implements  OnTouchListener 
{
    AnimationS sv;
    float x,y,dx,dy,sdx,sdy,ex,ey,sy,sx,animx,animy;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//For fullscreen view
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	
			sv =new AnimationS(this);
			setContentView(sv);
		    sv.setOnTouchListener(this);
		    x=y=dx=dy=sdx=sdy=ex=ey=sy=sx=animx=animy=0;
			
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		sv.pause();
	}
	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		sv.resume();
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) 
	{
		// TODO Auto-generated method stub
		

		x=arg1.getX();
		y=arg1.getY();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		switch(arg1.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			dx=dy=sdx=sdy=ex=ey=sy=sx=animx=animy=0;
			sx=arg1.getX();
			sy=arg1.getY();
			break;
		
		case MotionEvent.ACTION_UP:
			ex=arg1.getX();
			ey=arg1.getY();
			dx=ex-sx;
			dy=ey-sy;
			sdx=dx/10;
			sdy=dy/10;
			
			break;
			
		}
	 return true;
	}
	
public class AnimationS extends SurfaceView implements Runnable
{

	SurfaceHolder ourHolder;
    Thread ourThread;
    boolean isRunning=false;
	
	Bitmap bs=BitmapFactory.decodeResource(getResources(), R.drawable.blue_sky);
	Bitmap ball=BitmapFactory.decodeResource(getResources(), R.drawable.ball);
	Bitmap i=BitmapFactory.decodeResource(getResources(), R.drawable.iphone);
	public AnimationS(pk pk) 
	{
		// TODO Auto-generated constructor stub
		super(pk);
		ourHolder=getHolder();
		
		
	}

	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		while(isRunning)
		{
			if(!ourHolder.getSurface().isValid())
				continue;
			Canvas c=ourHolder.lockCanvas();
			c.drawBitmap(bs,0, 0, null);
			if(ex!=0&&ey!=0)
			{
				c.drawBitmap(i,x-(i.getWidth()/2)+animx, y-(i.getHeight()/2)+animy, null);
				
			}
			else if(x!=0&&y!=0)
				c.drawBitmap(i, x-(i.getWidth()/2), y-(i.getHeight()/2), null);
		
			ourHolder.unlockCanvasAndPost(c);
			animx+=sdx;
			animy+=sdy;
		}
		
		
	}
	public void pause() 
	{
		// TODO Auto-generated method stub
		isRunning=false;
		while(true)
		{
			try {
				ourThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		}
		ourThread=null;
		
		
		
	}


	public void resume()
	{
		// TODO Auto-generated method stub
		isRunning=true;
		ourThread=new Thread(this);
		ourThread.start();
	}
	
}//End of Animation class



}
