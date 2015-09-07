package com.example.thirdapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("NewApi")
public class InternalStorage extends Activity implements OnClickListener
{
	FileOutputStream fos;
	Button bSave,bLoad;
	EditText text;
	TextView loadText;
	String fileName="InternalFile";
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shared_prefs);
		intialise();
		bSave.setOnClickListener(this);
		bLoad.setOnClickListener(this);
		try {
			fos=openFileOutput(fileName,Context.MODE_PRIVATE);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			String getEt=text.getText().toString();
			//Alternate Method to save data in file.
			/*
			File f=new File(fileName);
			try {
				fos= new FileOutputStream(f);
				fos.write(getEt.getBytes());
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			//general method
			try {
				fos=openFileOutput(fileName,Context.MODE_PRIVATE);
				fos.write(getEt.getBytes());
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			loadText.setText("Data saved.");
			break;
		case R.id.bLoad:
		new diffIntent().execute(fileName);
		
			break;
		}
	}
	public class diffIntent extends AsyncTask<String,Integer,String>
	{
        ProgressDialog pd;
	    protected void onPreExecute()
	    { 
	    	//Example of setting something such as variable before main execution
	    	pd=new ProgressDialog(InternalStorage.this);
	    	pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	    	pd.setMax(100);
	    	pd.show();
	    	
	    }
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
		FileInputStream fis=null;
		String dataLoaded=null;
		for(int i=0;i<20;i++)
		{
			publishProgress(5);
			try {
				Thread.sleep(88);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		pd.dismiss();
	try {
		fis= openFileInput(fileName);
		byte[] barray2readData=new byte[fis.available()];
		while(fis.read(barray2readData)!=-1)
		{
			dataLoaded=new String(barray2readData);
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally
	{
	try {
		fis.close();
		return dataLoaded;// return vari dataLoaded would be setText in post execute method on screen
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
			return null;
			
		
		} 
		
	protected void onProgressbar(Integer... progress)	
	{
		//Will show the progress bar when loading saved data (when load button is pressed)
		pd.incrementProgressBy(progress[0]);
		
	}

	@Override
	protected void onPostExecute(String result) 
	{
		
		//post the final result on screen after execution completes
		loadText.setText(result);
	}
	
		}
	}

