/***************************************************************
* File: Camera.java
* Authors: Sofia Barraza, Shaylyn Wetts, Christopher Sanchez, Mario Garcia.
* Class: CS445 - Computer Graphics
*
* assignment: Final Project - Checkpoint Assignment # 2
* date last modified: 5/8/2017
*
* purpose: Camera object that keeps track of the view, projection, and clip
* spaces in rendering.
*
****************************************************************/
package cs445finalproject;

import static org.lwjgl.opengl.GL11.*;

/**
 * Camera implementation:
 *       up      front
 *         |   /
 *         |  / 
 *         | /
 *          C ---- right
 * 
 * Our vectors in local space judge how our camera moves throughout world
 * space coordinates.
 * @author alexa
 */
public class Camera {
    public enum Movement { LEFT, RIGHT, UP, DOWN, FORWARD, BACK };
    /**
     * The current position of the camera in world space. Our Y value is 
     * flipped for some reason in OpenGL 1.1, so we need to un-flip that 
     * in order to get the true position of our camera in world coordinates.
     */
    private Vector3 position;
    
    /**
     * Front vector of this camera in model space.
     */
    private Vector3 front;
    
    /**
     * Right vector of this camera in model space.
     */
    private Vector3 right;
    
    /**
     * Up vector of this camera in model space.
     */
    private Vector3 up;
    
    /**
     * Speed of the camera.
     */
    private float speed;
    private float lastX, lastY;
    private float pitch;
    private float yaw;
    private boolean firstMouse;
    
    Camera() {
        firstMouse = true;
        position = new Vector3();
        front = new Vector3(0.0f, 0.0f, -1.0f);
        right = new Vector3(1.0f, 0.0f, 0.0f);
        up = new Vector3(0.0f, 1.0f, 0.0f);
        speed = 10.0f;
        pitch = 0.0f;
        yaw = -90.0f;
        lastX = lastY = 0.0f;
    }
    
    /**
     * method: setPosition.
     * purpose: Sets the position of the camera.
     */
    public void setPosition(Vector3 pos) {
        position = pos;
    }
    
    /**
     * method: setSpeed.
     * purpose: Set the speed of this camera when moving around.
     */
    public void setSpeed(float newSpeed) {
        speed = newSpeed;
    }
    
    /**
     * Update the camera. This is where movement and look rotations are updated.
     */
    public void update() {
        // Rotate about a hyper sphere.
        front.x = (float )(Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)));
        front.y = (float )(Math.sin(Math.toRadians(pitch)));
        front.z = (float )(Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)));
        front = front.normalize();
        
        // Find our right and up vectors of the camera's local space.
        right = front.cross(new Vector3(0.0f, 1.0f, 0.0f)).normalize();
        up = right.cross(front).normalize();
        
        // Change our view and projection matrices.
        // our yaw is projected differently in OpenGL 1.1, so we need to compensate.
        glRotatef(pitch, 1.0f, 0.0f, 0.0f);   
        glRotatef(yaw - 90.0f, 0.0f, 1.0f, 0.0f);    
        glTranslatef(position.x, position.y, position.z);
    }
    
    
    /**
     * method: move
     * purpose: moves the camera position based on where you wish.
     */
    public void move(Movement movement) {
        float velocity = speed * (float )FatherTime.deltaTime();
        switch (movement) {
            case LEFT:
            {
                Vector3 r = right.multiply(velocity);
                position = position.subtract(r);
            } break;
            case RIGHT:
            {
                Vector3 r = right.multiply(velocity);
                position = position.add(r);
            } break;
            case UP:
            {
                // NOTE(): this is unethical.
                Vector3 u = up.multiply(velocity);
                position = position.subtract(u);
            } break;
            case DOWN:
            {
                // NOTE(): this is unethical.
                Vector3 u = up.multiply(velocity);
                position = position.add(u);
            } break;
            case FORWARD:
            {
                Vector3 f = front.multiply(velocity);
                position = position.add(f);
            } break;
            case BACK: 
            {
                Vector3 f = front.multiply(velocity);
                position = position.subtract(f);
            } break;
            default: break;
        }
    }
    
    /**
     * method: look
     * purpose: Sets the look values for the camera.
     */
    public void look(float x, float y, boolean constrainPitch) {
        if (firstMouse) {
            lastX = x;
            lastY = y;
            firstMouse = false;
        }
        float xoffset = x - lastX;
        float yoffset = lastY - y;
        lastX = x;
        lastY = y;
        
        yaw += xoffset;
        pitch += yoffset;
        
        if (constrainPitch) {
            if (pitch > 89.0f) {
                pitch = 89.0f;
            }
            if (pitch < -89.0f) {
                pitch = -89.0f;
            }
        }
    }

    /**
     * method: getPosition
     * purpose: Returns the proper position of the camera in world coordinates.
     */
    public Vector3 getPosition() {
        return new Vector3(position.x, -position.y, position.z);
    }
    
    public float getSpeed() {
        return speed;
    }
}
