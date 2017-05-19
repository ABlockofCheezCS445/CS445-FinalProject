/***************************************************************
* File: PhysicsEngine.java
* Authors: Sofia Barraza, Shaylyn Wetts, Christopher Sanchez, Mario Garcia.
* Class: CS445 - Computer Graphics
*
* assignment: Final Project - Checkpoint Assignment # 3
* date last modified: 5/18/2017
*
* purpose: The Engine responsible for calculating physical phenomena in our 
* render engine. This will be handled on a separate, controlled, thread 
* so that rendering is not affected.
*
****************************************************************/
package cs445finalproject.physics;

import cs445finalproject.FatherTime;
import cs445finalproject.Vector3;
import java.util.ArrayList;

/**
 *
 * @author alexa
 */
public class PhysicsEngine implements Runnable {
    public static final String ENGINE_NAME = "ManlyRussianWomenBear Physics";
    public static final String ENGINE_AUTH = "Somehthing";
    
    private static ArrayList<RigidBody> rigidBodies = new ArrayList<>();
    private static volatile boolean running = false;
    private static boolean checkBuffer = false;
    
    public static void StartPhysics() {
        if (!running) {
            Physics.initPhysics();
            PhysicsEngine physics = new PhysicsEngine();
            running = true;
            new Thread(physics).start();
        }
    }
    
    private PhysicsEngine() {
    }
    
    /**
     * method: run
     * purpose: Starts running the physics engine thread.
     */
    @Override
    public void run() {
        while (running) {
            if (checkBuffer) {
                for (int i = 0; i < rigidBodies.size(); ++i) {
                    RigidBody rigidbody = rigidBodies.get(i);
                    rigidbody.checkForMeshUpdates();
                    if (!rigidbody.kinetic) {
                        Calculate(rigidbody);
                        rigidbody.update();
                    }
                }
                checkBuffer = false;
            }
            
        }
        System.out.println("Physics Engine cleaned up!");
        System.out.println("Physics Engine Thread closing...");
    }
    
    public static void Destroy() {
        Physics.CleanUp();
        running = false;
    }
    
    public static void push(RigidBody rigidbody) {
        rigidBodies.add(rigidbody);
    }
    
    public static void runCalculations() {
        checkBuffer = true;
    }
    
    /**
     * method: Calculate
     * purpose: Performs calculations on the rigidbody.
     * @param rigidbody 
     */
    private static void Calculate(RigidBody rigidbody) {
        //
        // TODO(): this is where we will check if any collisions have occured?
        //
        System.out.println("Rigidbody " + rigidbody.mesh.nameTag + ": x=" + 
                + rigidbody.position.x + " y=" 
                + rigidbody.position.y + " z=" + rigidbody.position.z);
        Vector3 linearAcceleration = new Vector3(rigidbody.force.x / rigidbody.mass,
            rigidbody.force.y / rigidbody.mass, rigidbody.force.z / rigidbody.mass);
        linearAcceleration.y += Physics.WorldGravityConstant;
        
        rigidbody.linearVelocity.x += linearAcceleration.x * FatherTime.deltaTime();
        rigidbody.linearVelocity.y += linearAcceleration.y * FatherTime.deltaTime();
        rigidbody.linearVelocity.z += linearAcceleration.z * FatherTime.deltaTime();
        
        rigidbody.position.x += rigidbody.linearVelocity.x * FatherTime.deltaTime();
        rigidbody.position.y += rigidbody.linearVelocity.y * FatherTime.deltaTime();
        rigidbody.position.z += rigidbody.linearVelocity.z * FatherTime.deltaTime();
    }
}
