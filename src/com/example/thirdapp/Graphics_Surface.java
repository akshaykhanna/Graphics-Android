package com.example.thirdapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnTouchListener;

public class Graphics_Surface extends Activity implements OnTouchListener
{
   AnimationSurface sv;
   float x,y,sx,sy,ex,ey,dx,dy,scaledx,scaledy,animx,animy;
   MediaPlayer bounceSound;
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//For fullscreen view
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		sv=new AnimationSurface(this);
		sv.setOnTouchListener(this);
		setContentView(sv);
		bounceSound=MediaPlayer.create(Graphics_Surface.this, R.raw.ball_bounce);
		sx=sy=ex=ey=dx=dy=scaledx=scaledy=animx=animy=0;
		
		
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
	public boolean onTouch(View arg0, MotionEvent arg1) {
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
			sx=arg1.getX();
			sy=arg1.getY();
			ex=ey=0;
			break;
		case MotionEvent.ACTION_UP:
			ex=arg1.getX();
			ey=arg1.getY();
			dx=ex-sx;
			dy=ey-sy;
			scaledx=dx/40;
			scaledy=dy/40;
			animx=0;animy=0;
			bounceSound.start();
			break;
		}
		return true;
		
		
	}
	public class AnimationSurface extends SurfaceView implements Runnable 
	{
	    SurfaceHolder ourHolder;
	    Thread ourThread;
	    boolean isRunning=false;
	    
	    Bitmap ball=BitmapFactory.decodeResource(getResources(), R.drawable.ball);
	    Bitmap mark=BitmapFactory.decodeResource(getResources(), R.drawable.mark);
	    //Bitmap bs=BitmapFactory.decodeResource(getResources(), R.drawable.blue_sky);
		public AnimationSurface(Context context)
		{
			super(context);
		    ourHolder=getHolder();
	
			
		}
	    
		public void run() 
		{
			while(isRunning)
			{
				if(!ourHolder.getSurface().isValid())
					continue;
				Canvas canvas=ourHolder.lockCanvas();
				//draw background on screen
				//canvas.drawBitmap(bs,0,0, null);
				canvas.drawARGB(230,70,240,140);
				if(sx!=0&&sy!=0)
				{
					
					canvas.drawBitmap(mark, sx-(mark.getWidth()/2), sy-(mark.getHeight()/2), null);
				}
				if(ex!=0&&ey!=0)
				{
					
					canvas.drawBitmap(mark, ex-(mark.getWidth()/2), ey-(mark.getHeight()/2), null);
					canvas.drawBitmap(ball, x-(ball.getWidth()/2)-animx, y-(ball.getHeight()/2)-animy, null);
				}
				else 
				if(x!=0&&y!=0)
				{
					
					canvas.drawBitmap(ball, x-(ball.getWidth()/2), y-(ball.getHeight()/2), null);
				}
				ourHolder.unlockCanvasAndPost(canvas);
				animx+=scaledx;
				animy+=scaledy;
				
			}
			
		}

		public void pause() {
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
			bounceSound.release();
			
			
		}

		
		public void resume() {
			// TODO Auto-generated method stub
			isRunning=true;
			ourThread=new Thread(this);
			ourThread.start();
		}

	}

	
	
	

}
