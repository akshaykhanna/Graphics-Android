package com.example.thirdapp;

import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.view.Menu;

@TargetApi(Build.VERSION_CODES.CUPCAKE) public class OpenGLExCube extends Activity 
{
	
	GLSurfaceView glsv;
    @TargetApi(Build.VERSION_CODES.CUPCAKE) @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glsv = new GLSurfaceView(this);
        glsv.setRenderer(new GLRenderExC());
        setContentView(glsv);
      }
    
 @SuppressLint("NewApi") @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		glsv.onPause();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		glsv.onResume();
	}


    
}
