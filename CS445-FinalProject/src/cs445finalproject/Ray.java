/***************************************************************
* File: Ray.java
* Authors: Sofia Barraza, Shaylyn Wetts, Christopher Sanchez, Mario Garcia.
* Class: CS445 - Computer Graphics
*
* assignment: Final Project - Checkpoint Assignment # 3
* date last modified: 5/28/2017
*
* purpose: Ray mathematical object, used to represent an endpoint,
* and a direction, with infinite length.
*
****************************************************************/
package cs445finalproject;

/**
 *
 * @author alexa
 */
public class Ray {
    /**
     * The Starting point of this ray!
     */
    public Vector3 origin;
    
    
    /**
     * Direction of the ray. Must be normalized.
     */
    public Vector3 direction;
    
    public Ray(Vector3 origin, Vector3 direction) {
        this.origin = origin;
        this.direction = direction;
    }
    
    public Ray(Vector3 origin) {
        this(origin, new Vector3(1.0f));
    }
    
    public Ray() {
        this(new Vector3());
    }
    
    /**
     * Return a point along the ray path.
     * @param distance
     * @return 
     */
    public Vector3 getPoint(float distance) {
        return new Vector3();
    }
}
