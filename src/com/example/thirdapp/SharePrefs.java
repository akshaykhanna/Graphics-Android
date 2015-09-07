package com.example.thirdapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharePrefs extends Activity implements OnClickListener
{
	SharedPreferences saveData;
	public static String fileName="MyFile",enterText,printText;
	Button bSave,bLoad;
	EditText text;
	TextView loadText;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shared_prefs);
		intialise();
		bSave.setOnClickListener(this);
		bLoad.setOnClickListener(this);
		//Created a file with file name "MyFile" and 0 repersent private mode
		saveData=getSharedPreferences(fileName,0);
		
	}
	private void intialise() 
	{
		// TODO Auto-generated method stub
		text=(EditText)findViewById(R.id.etSaveData);
		bSave=(Button)findViewById(R.id.bSave);
		bLoad=(Button)findViewById(R.id.bLoad);
		loadText=(TextView)findViewById(R.id.tvPrint);
		
		
	}
	@Override
	public void onClick(View arg0) 
	{
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.bSave:
			enterText=text.getText().toString();
			//refering or openning a file using editor
			SharedPreferences.Editor e=saveData.edit();
			e.putString("text1", enterText);
			e.commit();
			break;
		case R.id.bLoad:
			saveData=getSharedPreferences(fileName,0);
			printText=saveData.getString("text1", "Couldn't load text");
			loadText.setText(printText);	
			break;
		}
	}
	

}
