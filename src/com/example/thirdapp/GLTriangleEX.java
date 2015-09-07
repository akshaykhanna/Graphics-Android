package com.example.thirdapp;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class GLTriangleEX 
{
	private float vertices[]={ 1f,1f,//point 0
			           1f,-1f,//point 1
			           -1f,-1f//point 2
			           //point 3
			
	};
   private FloatBuffer vertBuff; //Buffers are used to render things on screen
   private short pIndex[]={0,1,2};
   private ShortBuffer pBuff;
    public GLTriangleEX()
    {
    	ByteBuffer bBuff= ByteBuffer.allocateDirect(vertices.length*4);//setting up FloatBuffer
    	bBuff.order(ByteOrder.nativeOrder());
    	vertBuff=bBuff.asFloatBuffer();
    	vertBuff.put(vertices);
    	vertBuff.position(0);
    	
    	ByteBuffer bBPoint=ByteBuffer.allocateDirect(pIndex.length*2);//setting up ShortBuffer
    	bBPoint.order(ByteOrder.nativeOrder());
    	pBuff=bBPoint.asShortBuffer();
    	pBuff.put(pIndex);
    	pBuff.position(0);
    }
    public void draw(GL10 gl)
    {
    	gl.glFrontFace(GL10.GL_CW);//join points in 2d clockwise manner
    	gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);// enable us to use vertex array
    	gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertBuff);
    	gl.glDrawElements(GL10.GL_TRIANGLE_FAN, pIndex.length, GL10.GL_UNSIGNED_SHORT, pBuff);
    	gl.glDisableClientState(GL10.GL_VERTEX_ARRAY); //disable the use of vertex array
    	
    }
}
