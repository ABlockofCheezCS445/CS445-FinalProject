/***************************************************************
* File: Cube.java
* Authors: Sofia Barraza, Shaylyn Wetts, Christopher Sanchez, Mario Garcia.
* Class: CS445 - Computer Graphics
*
* assignment: Final Project - Checkpoint Assignment # 3
* date last modified: 5/8/2017
*
* purpose: The cube mesh object. This object identifies the cube and
* it's normalized size in local space.
*
****************************************************************/
package cs445finalproject;

import cs445finalproject.physics.BoxCollider;
import cs445finalproject.physics.RigidBody;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

/**
 *
 * @author alexa
 */
public class Cube extends Mesh {
    private int vbo, colorVBO;
    
    private RigidBody rigidbody;
    private BoxCollider collider;
    
    public Cube() {
        vbo = -1;
        colorVBO = -1;
    }

    
    /**
     * method: draw
     * purpose: Draws our cube on screen.
     */
    @Override
    public void draw() {
        GL11.glDisableClientState(GL_TEXTURE_COORD_ARRAY);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        GL11.glVertexPointer(3, GL_FLOAT, 0, 0l);
        
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, colorVBO);
        GL11.glColorPointer(3, GL_FLOAT, 0, 0l);
        
        glDrawArrays(GL_TRIANGLES, 0, 36);
        
        GL11.glEnable(GL_TEXTURE_COORD_ARRAY);
    }

    
    /**
     * method: initialize
     * purpose: Initialize our buffers for rendering first!
     */
    @Override
    public void initialize() {
        // TODO(Anyone): Subject to change to QUADS. However, rendering is much
        // more optimal with TRIANGLES instead.
        float[] vertices = new float[] {
            // Front
            -1.0f, -1.0f, 1.0f,
            -1.0f,  1.0f, 1.0f,
             1.0f,  1.0f, 1.0f,
             1.0f, 1.0f, 1.0f,
             1.0f, -1.0f, 1.0f,
             -1.0f, -1.0f, 1.0f,
            
             // right
             1.0f, -1.0f,  1.0f,
             1.0f,  1.0f,  1.0f,
             1.0f,  1.0f, -1.0f,
             1.0f,  1.0f, -1.0f,
             1.0f, -1.0f, -1.0f,
             1.0f, -1.0f,  1.0f,
             
             // back.
             1.0f, -1.0f, -1.0f,
             1.0f,  1.0f, -1.0f,
            -1.0f,  1.0f, -1.0f,
            -1.0f,  1.0f, -1.0f,
            -1.0f, -1.0f, -1.0f,
             1.0f, -1.0f, -1.0f,
             
            // left.
            -1.0f, -1.0f, -1.0f,
            -1.0f,  1.0f, -1.0f,
            -1.0f,  1.0f,  1.0f,
            -1.0f,  1.0f,  1.0f,
            -1.0f, -1.0f,  1.0f,
            -1.0f, -1.0f, -1.0f,
            
            // top.
            -1.0f, 1.0f, -1.0f,
             1.0f, 1.0f, -1.0f,
             1.0f, 1.0f,  1.0f,
             1.0f, 1.0f,  1.0f,
            -1.0f, 1.0f,  1.0f,
            -1.0f, 1.0f, -1.0f,
            
            // bottom
           -1.0f, -1.0f, -1.0f,
           -1.0f, -1.0f,  1.0f,
            1.0f, -1.0f,  1.0f,
            1.0f, -1.0f,  1.0f,
            1.0f, -1.0f, -1.0f,
           -1.0f, -1.0f, -1.0f
        };
        
        float[] colorVertices = new float[] {
            1.0f, 1.0f, 0.0f,
            1.0f, 1.0f, 0.0f,
            1.0f, 1.0f, 0.0f,
            
            1.0f, 1.0f, 0.0f,
            1.0f, 1.0f, 0.0f,
            1.0f, 1.0f, 0.0f,
            
            1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f,
            
            1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f,
            
            0.0f, 1.0f, 1.0f,
            0.0f, 1.0f, 1.0f,
            0.0f, 1.0f, 1.0f,
            
            0.0f, 1.0f, 1.0f,
            0.0f, 1.0f, 1.0f,
            0.0f, 1.0f, 1.0f,
            
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            
            0.0f, 1.0f, 0.0f, 
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
        };
        
        
        
        vbo = GL15.glGenBuffers();
        colorVBO = GL15.glGenBuffers();
        FloatBuffer flo = BufferUtils.createFloatBuffer(vertices.length);
        FloatBuffer color = BufferUtils.createFloatBuffer(colorVertices.length);
        
        flo.put(vertices);
        color.put(colorVertices);
        flo.flip();
        color.flip();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, flo, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, colorVBO);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, color, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        
        rigidbody = new RigidBody(this);
        collider = new BoxCollider(rigidbody);
    }   
    
    public RigidBody getRigidBody() {
        return rigidbody;
    }
}
