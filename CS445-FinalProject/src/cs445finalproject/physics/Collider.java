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
    public class EndPoint {
        public Collider collider;
        public float value;
        public boolean isMin;
        
        public EndPoint(Collider coll, float value, boolean min) {
            collider = coll;
            this.value = value;
            isMin = min;
        }
    }
    
    public enum ColliderShape { BOX, SPHERE, CYLINDER };
    
    public boolean hasCollided(Collider collider);
    
    public ColliderShape getColliderShape();
}
