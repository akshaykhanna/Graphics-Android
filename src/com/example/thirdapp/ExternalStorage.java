package com.example.thirdapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.FROYO)
@SuppressLint("NewApi")
public class ExternalStorage extends Activity implements OnItemSelectedListener, OnClickListener
{
      TextView canRead,canWrite;
      Button Save,Confirm;
      EditText storeText;
      private String state;
      boolean canW,canR;
      Spinner sp;File fPath=null, file=null;
      String[] path={"Music","Pictures","Downloads"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.external_storage);
		intialise();
		
		checkState();
		
	}

	private void checkState() {
		// TODO Auto-generated method stub
		state=Environment.getExternalStorageState();
		if(state.equals(Environment.MEDIA_MOUNTED))
		{
			//if MediaMouted then we can Read and Write in SD card
			canWrite.setText("True");
			canRead.setText("True");
			canR=canW=true;
		}
		else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
		{
			//If Mounted Read only (in this case) then we can only read data from sd card but cannot write in it
			canWrite.setText("False");
			canRead.setText("True");
			canR=true;
			canW=false;
			
		}
		else
		{
			canWrite.setText("False");
			canRead.setText("False");
			canR=canW=false;
		}
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(ExternalStorage.this,android.R.layout.simple_spinner_item,path);
		sp.setAdapter(adapter);
		sp.setOnItemSelectedListener(this);
		fPath=null;
	}

	private void intialise() {
		// TODO Auto-generated method stub
		canWrite=(TextView)findViewById(R.id.tvSavedE) ;
		canRead=(TextView)findViewById(R.id.tvLoadedE);
		sp=(Spinner)findViewById(R.id.spinner1);
		Save=(Button)findViewById(R.id.bNowSave);
		Confirm=(Button)findViewById(R.id.bConfirm);
		storeText=(EditText)findViewById(R.id.etTextExternal);
		Confirm.setOnClickListener(this);
		Save.setOnClickListener(this);
		
	}

	@SuppressLint("NewApi")
	@TargetApi(Build.VERSION_CODES.FROYO)
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		int p=sp.getSelectedItemPosition();
		switch(p)
		{  //fPath will store the location of folder in which new file is to be saved
		case 0:
			fPath=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			break;
		case 1:
			fPath=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			break;
		case 2:
			fPath=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			break;
		}
			
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		checkState();
		switch(arg0.getId())
		{
		case R.id.bNowSave:
			String FileNameHolder=storeText.getText().toString();
			file=new File(fPath,FileNameHolder+".png");
			checkState();
			if(canR==canW==true)
			{
				InputStream is=getResources().openRawResource(R.drawable.football);
				try {
					OutputStream os=new FileOutputStream(file);
					byte[] data=new byte[is.available()];
					is.read();
					os.write(data);
					os.close();
					Toast t=Toast.makeText(ExternalStorage.this,"File has been saved", Toast.LENGTH_LONG);
					t.show();
					
					//Code for Media Scanning or SD Card Scan used when a file is added or removed from SD card
					MediaScannerConnection.scanFile(ExternalStorage.this, 
							new String[] {file.toString()}, 
							null,
							new MediaScannerConnection.OnScanCompletedListener() {
								
								@Override
								public void onScanCompleted(String arg0, Uri arg1) {
									// TODO Auto-generated method stub
									Toast t=Toast.makeText(ExternalStorage.this, "Scan Complete",Toast.LENGTH_SHORT);
									t.show();
								}
							});
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			break;
		case R.id.bConfirm:
			//Save button will appear on screen when confirm button is pressed
			Save.setVisibility(View.VISIBLE);
			break;
		}
	}

	
	

}
