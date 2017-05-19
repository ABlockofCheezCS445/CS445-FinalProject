/***************************************************************
* File: CS445FinalProject.java
* Authors: Sofia Barraza, Shaylyn Wetts, Christopher Sanchez, Mario Garcia.
* Class: CS445 - Computer Graphics
*
* assignment: Final Project - Checkpoint Assignment # 2
* date last modified: 5/8/2017
*
* purpose: Main output program, where we set up the RenderEngine, initialize it, 
* and run it by pushing our meshes into it for drawing.
*
****************************************************************/
package cs445finalproject;

import cs445finalproject.physics.PhysicsEngine;
import cs445finalproject.physics.RigidBody;
import org.lwjgl.opengl.GL33;

/**
 *
 * @author alexa
 */
public class CS445FinalProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RenderEngine engine = new RenderEngine(640, 480);
        PhysicsEngine.StartPhysics();
        Camera camera = new Camera();
        
        // Initialize the engine.
        engine.initialize();
        engine.start();
        
        engine.setMainCamera(camera);
        camera.setPosition(new Vector3(-20.0f, 50.0f, 10.0f));

        Cube cube = new Cube();
        cube.nameTag = "Ice Cube";
        cube.position = new Vector3(-20.0f, 50.0f, 0.0f);
        cube.initialize();
        //cube.showLocalSpace = true;
        //cube.rotation.x = 45.0f;
        
        Chunk chunk = new Chunk();
        chunk.nameTag = "Dr. Dre";
        chunk.initialize();
        
        RigidBody cubebody = new RigidBody(cube);
        RigidBody chunkbody = new RigidBody(chunk, 1000.0f);
        chunkbody.kinetic = true; // set to true to prevent physics calculations.
        

        while (engine.isRunning()) {
            FatherTime.updateTime();
            PhysicsEngine.runCalculations();
            engine.update();
            
            // Push Mesh calls in here.
            engine.push(cube);
            engine.push(chunk);
            
            // Start the rendering!
            engine.render();
        }
        
        engine.cleanUp();
        PhysicsEngine.Destroy();
        System.out.println("Powered by: " + RenderEngine.ENGINE_NAME);
        System.out.println("Physics provided by: " + PhysicsEngine.ENGINE_NAME);
    }
    
}
