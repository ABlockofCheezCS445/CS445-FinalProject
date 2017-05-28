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
 * TODO(): Will be implementing AABB collision detection.
 */
public class BoxCollider implements Collider {
    public EndPoint xNegWidth, xPosWidth;
    public EndPoint yNegHeight, yPosHeight;
    public EndPoint zNegDepth, zPosDepth;
    public RigidBody rigidbody;
    
    /**
     * Assigned local space.
     * @param body
     * @param xneg
     * @param xpos
     * @param yneg
     * @param ypos
     * @param zneg
     * @param zpos 
     */
    public BoxCollider(RigidBody body, float xneg, float xpos, 
            float yneg, float ypos, float zneg, float zpos) {
        xNegWidth = new EndPoint(this, xneg, true);
        xPosWidth = new EndPoint(this, xpos, false);
        yNegHeight = new EndPoint(this, yneg, true);
        yPosHeight = new EndPoint(this, ypos, false);
        zNegDepth = new EndPoint(this, zneg, true);
        zPosDepth = new EndPoint(this, zpos, false);
        this.rigidbody = body;
        body.collider = this;
    }
    
    public BoxCollider(RigidBody body) {
        this(body, -1.0f, 1.0f, -1.0f, 1.0f, -1.0f, 1.0f);
    }

    @Override
    public boolean hasCollided(Collider collider) {
        switch(collider.getColliderShape()) {
            case BOX: 
            {
                BoxCollider box = (BoxCollider )collider;
                
                // AABB1
                float xMin = rigidbody.position.x + xNegWidth.value;
                float yMin = rigidbody.position.y + yNegHeight.value;
                float zMin = rigidbody.position.z + zNegDepth.value;
                
                float xMax = rigidbody.position.x + xPosWidth.value;
                float yMax = rigidbody.position.y + yPosHeight.value;
                float zMax = rigidbody.position.z + zPosDepth.value;
                
                float x1Min = box.rigidbody.position.x + box.xNegWidth.value;
                float y1Min = box.rigidbody.position.y + box.yNegHeight.value;
                float z1Min = box.rigidbody.position.z + box.zNegDepth.value;
                
                float x1Max = box.rigidbody.position.x + box.xPosWidth.value;
                float y1Max = box.rigidbody.position.y + box.yPosHeight.value;
                float z1Max = box.rigidbody.position.z + box.zPosDepth.value;
                
                if (xMax < x1Min || xMin > x1Max) return false;    
                if (yMax < y1Min || yMin > y1Max) return false;
                if (zMax < z1Min || zMin > z1Max) return false;
                
                return true;
            }
            case SPHERE: 
            {
                
            }
            case CYLINDER: 
            {
                
            }   
            // We don't have mesh ;=;
            default: return false;
        }
    }

    @Override
    public ColliderShape getColliderShape() {
        return ColliderShape.BOX;
    }
}
