package com.example.thirdapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

public class AnimationClass extends View
{
	
    Bitmap football;float changingY;
    Typeface font;
	public AnimationClass(Context context) 
	{
		super(context);
		// TODO Auto-generated constructor stub
		football=BitmapFactory.decodeResource(getResources(), R.drawable.ball);
		changingY=0;
		//Adding font comics.ttf from asset folder in 'font' variable
		font=Typeface.createFromAsset(context.getAssets(), "comics.ttf");
	}
	@Override
	public void draw(Canvas canvas)
	{
		// TODO Auto-generated method stub
		super.draw(canvas);
		canvas.drawColor(Color.WHITE);
		
		Paint colorText= new Paint();
		colorText.setARGB(80, 25, 255, 10);
		colorText.setTextAlign(Align.CENTER);
		colorText.setTextSize(30);
		colorText.setTypeface(font);
		canvas.drawText("Akshay Khanna", canvas.getWidth()/2, (canvas.getHeight()/2)-50,colorText);
		
		canvas.drawBitmap(football, (getWidth()/2), changingY, null);
		if(changingY<getHeight())
			changingY+=1;
		else
			changingY=0;
		
		Rect Goal=new Rect();
		Goal.set(0, 200, canvas.getWidth(), 270);
		Paint colorG=new Paint();
		colorG.setColor(Color.BLACK);
		canvas.drawRect(Goal, colorG);
		invalidate();
	}
	

}
