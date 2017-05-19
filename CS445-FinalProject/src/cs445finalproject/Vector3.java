/***************************************************************
* File: Vector3.java
* Authors: Sofia Barraza, Shaylyn Wetts, Christopher Sanchez, Mario Garcia.
* Class: CS445 - Computer Graphics
*
* assignment: Final Project - Checkpoint Assignment # 3
* date last modified: 5/18/2017
*
* purpose: Vector object used for the mathematics of the RenderEngine, as well
* as for our source code implementation.
*
****************************************************************/
package cs445finalproject;

/**
 *
 * @author alexa
 */
public class Vector3 {
    public float x;
    public float y;
    public float z;
    
    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Vector3(float x, float y) {
        this(x, y, 0.0f);
    }
    
    public Vector3(float x) {
        this(x, 0.0f, 0.0f);
    }
    
    public Vector3() {
        this(0.0f, 0.0f, 0.0f);
    }
    
    public float magnitude() {
        return (float )Math.sqrt((x * x) + (y * y) + (z * z));
    }
    
    /**
     * method: normalize.
     * purpose: Normalize our vector back to unit length. Useful for if you only need 
     * to worry about direction. Cases where this is useful, movement, since if
     * a direction vector keeps growing, our speed may decrease as a result. We
     * don't want that, so we normalize.
     */
    public Vector3 normalize() {
        Vector3 newVec = new Vector3(x, y, z);
        float mag = magnitude();
        if (mag > 0) {
            newVec = newVec.divide(mag);
        }
        return newVec;
    }
    
    
    public Vector3 divide(float scaler) {
        Vector3 v = new Vector3(x, y, z);
        v.x /= scaler;
        v.y /= scaler;
        v.z /= scaler;
        return v;
    }
    
    
    public Vector3 multiply(float scaler) {
        Vector3 v = new Vector3(x, y, z);
        v.x *= scaler;
        v.y *= scaler;
        v.z *= scaler;
        return v;
    }
    
    
    public Vector3 add(Vector3 other) {
        return new Vector3(x + other.x, y + other.y, z + other.z);
    }
    
    
    public Vector3 subtract(Vector3 other) {
        return new Vector3(x - other.x, y - other.y, z - other.z);
    }
   
    
    /**
     * method: dot
     * purpose: calculate the dot product between this vector and the other.
     */
    public float dot(Vector3 other) {
        return (x * other.x) + (y * other.y) + (z * other.z);
    }
    
    
    /**
     * method: cross
     * purpose: Serves as a cross product of this vector and the other vector.
     * Returns a new vector once completed.
     */
    public Vector3 cross(Vector3 other) {
        return new Vector3(
                (y * other.z) - (z * other.y),
                (z * other.x) - (x * other.z),
                (x * other.y) - (y * other.x)
        );
    }
    
    
    public Vector3 add(float scaler) {
        Vector3 v = new Vector3(x, y, z);
        v.x += scaler;
        v.y += scaler;
        v.z += scaler;
        return v;
    }
    
    
    public Vector3 subtract(float scaler) {
        Vector3 v = new Vector3();
        v.x -= scaler;
        v.y -= scaler;
        v.z -= scaler;
        return v;
    }
    
    public static Vector3 lerp(Vector3 a, Vector3 b, float t) {
        Vector3 aa = a.multiply(1 - t);
        Vector3 bb = b.multiply(t);
        return aa.add(bb);
    }
}
