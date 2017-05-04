/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
}
