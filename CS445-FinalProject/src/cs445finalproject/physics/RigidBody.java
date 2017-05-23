/***************************************************************
* File: RigidBody.java
* Authors: Sofia Barraza, Shaylyn Wetts, Christopher Sanchez, Mario Garcia.
* Class: CS445 - Computer Graphics
*
* assignment: Final Project - Checkpoint Assignment # 3
* date last modified: 5/8/2017
*
* purpose: Rigidbody is the physical data holder of all forces acting on 
* an object. This is where we handle most of the data middleware between our
* meshes, and our physical calculations onto our objects.
*
****************************************************************/
package cs445finalproject.physics;

import cs445finalproject.Mesh;
import cs445finalproject.Vector3;

/**
 *
 * @author alexa
 */
public class RigidBody {
    // Mass in kilograms.
    public float mass;
    public Vector3 linearVelocity;
    
    /**
     * Current position of the rigid body.
     */
    public Vector3 position;
    public Vector3 force;
    public Mesh mesh;
    public Collider collider;
    
    public boolean kinematic = false;
    
    public RigidBody(Mesh observer, float m) {
        if (observer != null) {
            position = observer.position;
            mesh = observer;
        }
        mass = m;
        linearVelocity = new Vector3();
        force = new Vector3(0.0f, 0.0f, 0.0f);
    }
    
    public RigidBody(Mesh observer) {
        // Average mass of a human in kg.
        this(observer, 70.0f);
    }
    
    public float getMass() {
        return mass;
    }
    
    
    public Vector3 getPosition() {
        return position;
    }
    
    public void checkForMeshUpdates() {
        position = mesh.position;
    }
    
    public void update() {
        mesh.position = position;
    }
    
    public void addForce(Vector3 applied) {
        force = force.add(applied);
    }
}
