/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs445finalproject.physics;

import cs445finalproject.Vector3;

/**
 *
 * @author alexa
 */
public class RigidBody {
    // Mass in kilograms.
    private float mass;
    
    public Vector3 position;
    public Vector3 force;
    
    
    public RigidBody(float m) {
        mass = m;
    }
    
    public RigidBody() {
        this(150.0f);
    }
    
    public float getMass() {
        return mass;
    }
}
