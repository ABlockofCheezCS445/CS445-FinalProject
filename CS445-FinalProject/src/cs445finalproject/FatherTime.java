/***************************************************************
* File: FatherTime.java
* Authors: Sofia Barraza, Shaylyn Wetts, Christopher Sanchez, Mario Garcia.
* Class: CS445 - Computer Graphics
*
* assignment: Final Project - Checkpoint Assignment # 1
* date last modified: 5/8/2017
*
* purpose: FatherTime is just a singleton that keeps track of the time
* between rendering frames and such. 
*
****************************************************************/
package cs445finalproject;

/**
 * Don't know why i called this father time... Keeps track of time and 
 * the time between rendering frames.
 */
public class FatherTime {
    /**
     * The time it took to render the last frame.
     */
    private static double lastTime = ((double)System.currentTimeMillis()) / 1000.0f;
    
    /**
     * Time rate between rendering current frame and last frame. 
     * currFrame - lastFrame.
     */
    private static double dt = 0.0;
    
    /**
     * method: deltaTime
     * purpose: Grabs hold of the delta time between rendering this frame and
     * last frame.
     */
    public static double deltaTime() {
        return dt;
    }
    
    /**
     * method: updateTime
     * purpose: Updates the delta time.
     */
    public static void updateTime() {
        double t = (double )System.currentTimeMillis() / 1000.0f;
        dt = t - lastTime;
        lastTime = t;
    }
    
    
    /**
     * method: smoothstep
     * purpose: Allows linear interpolation with a smoother traverse along
     * the line.
     */
    public static float smoothstep(float a0, float a1, float t) {
        t = (t - a0) / (a1 - a0);
        if (t > a1) {
            t = a1;
        } else if (t < a0) {
            t = a0;
        }
        return t * t * (3.0f - 2.0f * t);
    }
}
