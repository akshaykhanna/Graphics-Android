package com.example.thirdapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

@TargetApi(Build.VERSION_CODES.CUPCAKE)
@SuppressLint("NewApi")
public class Accelerometer extends Activity implements SensorEventListener
{
	SensorManager sm;
	float x,y,senX,senY;
	Bitmap ball;
	AnimationSurface osv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//For fullscreen view
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
				
		sm=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
		if(sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size()!=0)
		{
			Sensor s=sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL );
		}
		x=y=senX=senY=0;
		osv=new AnimationSurface(this);
        setContentView(osv);
		
	}

	
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		sm.unregisterListener(this);
		osv.pause();
		super.onPause();
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		osv.resume();
		super.onResume();
	}




	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void onSensorChanged(SensorEvent e) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(16);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		senX=e.values[0];
		senY=e.values[1];
		
		
	}




	public class AnimationSurface extends SurfaceView implements Runnable 
	{
	    SurfaceHolder ourHolder;
	    Thread ourThread;
	    boolean isRunning=false;
	    
	    Bitmap ball=BitmapFactory.decodeResource(getResources(), R.drawable.ball);
	    Bitmap mark=BitmapFactory.decodeResource(getResources(), R.drawable.mark);
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
				canvas.drawRGB(25, 150, 50);
				float centerX=canvas.getWidth()/2;
				float centerY=canvas.getHeight()/2;
				
				
			    canvas.drawBitmap(ball, centerX-(ball.getWidth()/2)+senX*30, centerY-(ball.getHeight()/2)+senY*30, null);
			    ourHolder.unlockCanvasAndPost(canvas);
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
			
			
			
		}

		public void resume() {
			// TODO Auto-generated method stub
			isRunning=true;
			ourThread=new Thread(this);
			ourThread.start();
		}
	}//End of AnimationSurface class
			
	
	
}//end of Main Accelerometer class
