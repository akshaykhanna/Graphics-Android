package com.example.thirdapp;

import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.os.SystemClock;

public class GLRenderExC implements Renderer {
   Random crazy;
   GLTriangleEX tri;
   GLCubeEx cube;
   public GLRenderExC()
   {
	   
	   cube=new GLCubeEx();
   }
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig  eglc) {// Just like oncreate() method, called only once
		// TODO Auto-generated method stub
		
		gl.glDisable(GL10.GL_DITHER); //Boost performance
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
		gl.glClearColor(.2f,.9f, .4f, .5f); //set initial background color on creation :rgb & alpha(opacity)
		gl.glClearDepthx(1);

	}
	@Override
	public void onDrawFrame(GL10 gl) {// called many times or with every screen refresh
		// TODO Auto-generated method stub
		gl.glDisable(GL10.GL_DITHER);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT); //setting mask of GL10 which is buffer to handle color and hadle depth
        
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity(); //Zoom
        GLU.gluLookAt(gl, 0, 0, -5, 0, 0, 0, 0, 2, 0);
       
        long time=SystemClock.uptimeMillis() %4000l; //rotate cube in accordance with system time
        float angle=0.09f*(int)time; // angle of rotation would change according to time
                     //angle,x,y,z-axis for rotation
        gl.glRotatef(angle, 0, 2, 1); 
        
       
        cube.draw(gl);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) { // called when orientation is changed
		// TODO Auto-generated method stub
       gl.glViewport(0, 0, width, height);
       float ratio=(float) width/height;
       gl.glMatrixMode(GL10.GL_PROJECTION);
       gl.glLoadIdentity();             //Near Far
       gl.glFrustumf(-ratio, ratio, -1, 1, 1, 25);
	}

	

}
