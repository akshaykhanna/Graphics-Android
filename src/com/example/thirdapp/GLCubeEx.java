package com.example.thirdapp;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class GLCubeEx {

	private float vertices[]={-1.0f, -1.0f, -1.0f,
            1.0f, -1.0f, -1.0f,
            1.0f,  1.0f, -1.0f,
            -1.0f, 1.0f, -1.0f,
            -1.0f, -1.0f,  1.0f,
            1.0f, -1.0f,  1.0f,
            1.0f,  1.0f,  1.0f,
            -1.0f,  1.0f,  1.0f
};
private FloatBuffer vertBuff; //Buffers are used to render things on screen
private short pIndex[]={//converting cube or drawing cube by using 12 triangles as android does not support direct method to draw cube
		 0, 4, 5, 0, 5, 1,
         1, 5, 6, 1, 6, 2,
         2, 6, 7, 2, 7, 3,
         3, 7, 4, 3, 4, 0,
         4, 7, 6, 4, 6, 5,
         3, 0, 1, 3, 1, 2
};
private ShortBuffer pBuff;
public GLCubeEx()
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
gl.glEnable(GL10.GL_CULL_FACE);/*since a 3d triangle has 4 face (imagine a prism) and we need to remove(cull) 
//the back face as that face should not appear while viewing cube */
gl.glCullFace(GL10.GL_BACK);

gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);// enable us to use vertex array
gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertBuff); //since working with 3D, therefore 3 instead of 2
gl.glDrawElements(GL10.GL_TRIANGLE_FAN, pIndex.length, GL10.GL_UNSIGNED_SHORT, pBuff);
gl.glDisableClientState(GL10.GL_VERTEX_ARRAY); //disable the use of vertex array
gl.glDisable(GL10.GL_CULL_FACE); //disable the cull face mode as the cube is drawn we don't need it anymore

}
}
