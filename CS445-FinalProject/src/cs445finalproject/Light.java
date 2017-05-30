/***************************************************************
* File: CS445FinalProject.java
* Authors: Sofia Barraza, Shaylyn Wetts, Christopher Sanchez, Mario Garcia.
* Class: CS445 - Computer Graphics
*
* assignment: Final Project - Checkpoint Assignment # 3
* date last modified: 5/20/2017
*
* purpose: Provides light to map
*
****************************************************************/
package cs445finalproject;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;


public class Light extends Mesh {
    private static int lightCount = 0;
    
    public Vector3 lightColor;
    private final int lightID;
    private FloatBuffer lightPosition;
    private FloatBuffer light;
    
    public Light() {
        lightID = lightCount++;
        lightColor = new Vector3(1.0f, 1.0f, 1.0f);
    }
    
    @Override
    public void draw() {
        glPushMatrix();
            glLight(GL_LIGHT0, GL_POSITION, lightPosition);
            glLight(GL_LIGHT0, GL_SPECULAR, light);
            glLight(GL_LIGHT0, GL_DIFFUSE, light);
            glLight(GL_LIGHT0, GL_AMBIENT, light);
        glPopMatrix();
    }

    @Override
    public void initialize() {
        glEnable(GL_LIGHT0 + lightID);
        lightPosition = BufferUtils.createFloatBuffer(4);
        lightPosition.put(-position.x).put(position.y).put(-position.z).put(1.0f).flip();
        
        light = BufferUtils.createFloatBuffer(4);
        light.put(lightColor.x).put(lightColor.y).put(lightColor.z).put(0.0f).flip();
    }
    
}
