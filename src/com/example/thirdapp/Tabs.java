package com.example.thirdapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Tabs extends Activity implements OnClickListener
{
	TabHost th;TabSpec speccy;
	TextView time;
	long milli,stop,start;
	int second=0,minute=0,hrs=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		start=stop=0;
		
		//setting up buttons
		Button start=(Button)findViewById(R.id.bT1Start);
		Button stop=(Button)findViewById(R.id.bT1Stop);
		Button addTab=(Button)findViewById(R.id.bT3AddTab);
		time=(TextView)findViewById(R.id.tvTab1);
		addTab.setOnClickListener(this);
		start.setOnClickListener(this);
		stop.setOnClickListener(this);
		
		
		th=(TabHost)findViewById(R.id.tabhost);
		th.setup();
		//code for tab1
		speccy=th.newTabSpec("tag1");
		speccy.setContent(R.id.tab1);
		speccy.setIndicator("Stopwatch");
		th.addTab(speccy);
		
		//code for tab 2
		speccy=th.newTabSpec("tag2");
		speccy.setContent(R.id.tab2);
		speccy.setIndicator("Tab 2");
		th.addTab(speccy);
		
		//code for tab 3
		speccy=th.newTabSpec("tag3");
		speccy.setContent(R.id.tab3);
		speccy.setIndicator("Add more tabs");
		th.addTab(speccy);
		time.setText("Stopwatch time");
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.bT3AddTab:
			speccy=th.newTabSpec("tag4");
			speccy.setContent(new TabHost.TabContentFactory() {
				
				@Override
				public View createTabContent(String tag) {
					// TODO Auto-generated method stub
					TextView text= new TextView(Tabs.this);
					text.setText("You have created a new tab.");
					return text;
				}
			});
			speccy.setIndicator("New");
			th.addTab(speccy);
		break;
		case R.id.bT1Start:
			start=System.currentTimeMillis();
			
			break;
		case R.id.bT1Stop:
			if(start!=0)
			{
				stop=System.currentTimeMillis();
			milli=stop-start;
			second=(int)milli/100;
			minute=second/60;
			hrs=minute/60;
			milli=milli%100;
			second=second%60;
			minute=minute%60;
			time.setText(String.format("%02d:%03d:%02d:%02d",hrs,minute,second,milli));
			start=stop=0;
			}
			break;
			
		}
	}

}
