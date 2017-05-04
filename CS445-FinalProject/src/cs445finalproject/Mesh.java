/***************************************************************
* File: Mesh.java
* Authors: Mario Garcia, [plz put yo names here]
* Class: CS 141 – Programming and Problem Solving
*
* assignment: Final Project
* date last modified: 5/4/2017
*
* purpose: Standard mesh abstract object. This object holds the transform, as 
* well as the position, of meshes in world space. The renderer uses this as
* render commands.
*
****************************************************************/
package cs445finalproject;

import java.util.ArrayList;
import java.util.List;


/**
 * Mesh is the overall render command that is used by the RenderEngine, which
 * tells our engine how to draw our object. It also contains data about the
 * transformation of this object as well, or whether or not to render it onto
 * the screen
 */
public abstract class Mesh {
    /**
     * The position of the Mesh object in world space coordinates.
     */
    public Vector3 position;
    
    /**
     * The scale of the Mesh object.
     */
    public Vector3 scale;
    
    /**
     * The rotation of the object in world space.
     */
    public Vector3 rotation;
    
    /**
     * Checks if this mesh object is renderable. If true, the render engine will
     * draw this mesh onto the screen. If false, the render engine will ignore
     * this mesh object.
     */
    public boolean renderable;
    
    Mesh() {
        renderable = true;
        position = new Vector3();
        scale = new Vector3(1.0f, 1.0f, 1.0f);
        rotation = new Vector3(0.0f, 0.0f, -1.0f);
    }
    
    
    /**
     * method: draw
     * purpose: Tells the RenderEngine how to draw this mesh object. No need to 
     * perform any translation calls to the renderer unless needed.
     */
    public abstract void draw();
    
    /**
     * method: initialize
     * purpose: describes how to initialize our mesh object.
     */
    public abstract void initialize();
}
