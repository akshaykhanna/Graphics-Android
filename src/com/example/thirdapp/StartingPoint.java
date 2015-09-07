package com.example.thirdapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartingPoint extends Activity {

   MediaPlayer g;
   
   Intent  a=new Intent("com.example.thirdapp.STARTINGPOINT"),b2=new Intent("com.example.thirdapp.MENU");
  
	Button add,sub,r,sqr,gm,exit; 
   int count;
   TextView tv;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_point);
        count=0;
        g=MediaPlayer.create(StartingPoint.this,R.raw.gayatri_mantra);
        add=(Button)findViewById(R.id.bAdd);
        sub=(Button)findViewById(R.id.bSub);
        r=(Button)findViewById(R.id.bRto0);
        sqr=(Button)findViewById(R.id.bsqr);
        gm=(Button)findViewById(R.id.bGM);
        exit=(Button)findViewById(R.id.bE);
        tv=(TextView)findViewById(R.id.tvDisplay);
        add.setOnClickListener(new Button.OnClickListener() 
        {
            public void onClick(View v) 
            {
            	
                 tv.setText("Your total is "+(++count));
            }
			
		 });
        sub.setOnClickListener(new Button.OnClickListener() 
        {
            public void onClick(View v) 
            {
            	
                 tv.setText("Your total is "+(--count));
            }
			
		 });
        r.setOnClickListener(new Button.OnClickListener() 
        {
            public void onClick(View v) 
            {
            	count=0;
                 tv.setText("Your total is "+count);
            }
			
		 });
        sqr.setOnClickListener(new Button.OnClickListener() 
        {
            public void onClick(View v) 
            {
            	
                count=count*count;
            	tv.setText("Your total is "+count);
            }
			
		 });
      gm.setOnClickListener(new Button.OnClickListener() 
        {
            public void onClick(View v) 
            {
            	g.start();
            	setContentView(R.layout.gm);
            	
               
            }
			
		 });
        
      exit.setOnClickListener(new Button.OnClickListener() 
      {
          public void onClick(View v) 
          {
         
        	  startActivity(b2) ;
          	
             
          }
			
		 });
      
        
    }
	  @Override
		protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			g.release();
			startActivity(b2) ;
			
			}


  
	

    }
    

