/***************************************************************
* File: BoxCollider.java
* Authors: Sofia Barraza, Shaylyn Wetts, Christopher Sanchez, Mario Garcia.
* Class: CS445 - Computer Graphics
*
* assignment: Final Project - Checkpoint Assignment # 3
* date last modified: 5/18/2017
*
* purpose: Collision detection object used on rigid bodies for detecting
* a collision with another object.
*
****************************************************************/
package cs445finalproject.physics;

/**
 *
 * TODO(): Will be implmenting AABB collision detection.
 */
public class BoxCollider {
    public float width;
    public float height;
    
    public BoxCollider(float width, float height) {
        this.height = height;
        this.width = width;
    }
    
    public BoxCollider(float width) {
        this(width, 0.0f);
    }
    
    public BoxCollider() {
        this(0.0f, 0.0f);
    }
}
