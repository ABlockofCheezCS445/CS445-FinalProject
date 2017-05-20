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
    public float width;
    public float height;
    public float depth;
    public RigidBody rigidbody;
    
    public BoxCollider(RigidBody body, float width, float height, float depth) {
        this.height = height;
        this.width = width;
        this.rigidbody = body;
        this.depth = depth;
        body.collider = this;
    }
    
    public BoxCollider(RigidBody body, float width, float height) {
        this(body, width, height, 2.0f);
    }
    
    
    public BoxCollider(RigidBody body, float width) {
        this(body, width, 2.0f, 2.0f);
    }
    
    public BoxCollider(RigidBody body) {
        this(body, 2.0f, 2.0f, 2.0f);
    }

    @Override
    public boolean hasCollided(Collider collider) {
        switch(collider.getColliderShape()) {
            case BOX: 
            {
                BoxCollider box = (BoxCollider )collider;
                // AABB.
                float halfWidth = width / 2;
                float halfHeight = height / 2;
                float halfDepth = depth / 2;
                
                float halfWidthOther = box.width / 2;
                float halfHeightOther = box.height / 2;
                float halfDepthOther = box.depth / 2;
                
                // AABB1
                float xMin = rigidbody.position.x - halfWidth;
                float yMin = rigidbody.position.y - halfHeight;
                float zMin = rigidbody.position.z - halfDepth;
                
                float xMax = rigidbody.position.x + halfDepth;
                float yMax = rigidbody.position.y + halfHeight;
                float zMax = rigidbody.position.z + halfDepth;
                
                float x1Min = box.rigidbody.position.x - halfWidthOther;
                float y1Min = box.rigidbody.position.y - halfHeightOther;
                float z1Min = box.rigidbody.position.z - halfDepthOther;
                
                float x1Max = box.rigidbody.position.x + halfWidthOther;
                float y1Max = box.rigidbody.position.y + halfHeightOther;
                float z1Max = box.rigidbody.position.z + halfDepthOther;
                
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
