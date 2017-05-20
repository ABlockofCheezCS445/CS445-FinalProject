/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs445finalproject.physics;

/**
 *
 * @author alexa
 */
public interface Collider {
    public enum ColliderShape { BOX, SPHERE, CYLINDER };
    
    public boolean hasCollided(Collider collider);
    
    public ColliderShape getColliderShape();
}
