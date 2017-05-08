/***************************************************************
* File: Mesh.java
* Authors: Sofia Barraza, Shaylyn Wetts, Christopher Sanchez, Mario Garcia.
* Class: CS445 - Computer Graphics
*
* assignment: Final Project - Checkpoint Assignment # 1
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
    public static final Vector3 FRONT = new Vector3(0.0f, 0.0f, -1.0f).normalize();
    public static final Vector3 UP = new Vector3(0.0f, 1.0f, 0.0f).normalize();
    public static final Vector3 RIGHT = new Vector3(1.0f, 0.0f, 0.0f).normalize();
    
    /**
     * The position of the Mesh object in world space coordinates.
     */
    public Vector3 position;
    
    /**
     * The scale of the Mesh object.
     */
    public Vector3 scale;
    
    /**
     * The front vector of the object in world space.
     */
    public Vector3 rotation;
    
    /**
     * Degree in radians to rotate the mesh object about the x axis.
     */
    private float deg;
    private Vector3 axis;
    
    /**
     * Checks if this mesh object is renderable. If true, the render engine will
     * draw this mesh onto the screen. If false, the render engine will ignore
     * this mesh object.
     */
    public boolean renderable;
    
    /**
     * local space coordinates are drawn if true.
     */
    public boolean showLocalSpace;
    
    Mesh() {
        renderable = true;
        position = new Vector3();
        scale = new Vector3(1.0f, 1.0f, 1.0f);
        axis = new Vector3(0.0f, 0.0f, 0.0f);
        showLocalSpace = false;
        rotation = FRONT;
        deg = 0.0f;
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
    
    
    /**
     * method: update.
     * purpose: updates the mesh object's transforms.
     */
    public void update() {
        // TODO(Garcia): Rotations work! BUT, they only account to the forward
        // vector! This can be problematic when trying to rotate one axis at a time.
        // Separate this!
        float val = rotation.dot(FRONT) / (FRONT.magnitude() * rotation.magnitude());
        deg = (float )Math.toDegrees(Math.acos(val));
        axis = rotation.cross(FRONT).normalize();
    }
    
    
    /**
     * method: rotationAxis
     * purpose: get the rotation axis of this mesh.
     */
    public Vector3 rotationAxis() {
        return axis;
    }
    
    /**
     * method: getRotationDeg
     * purpose: get the rotation degree.
     */
    public float getRotationDeg() {
        return deg;
    }
}
