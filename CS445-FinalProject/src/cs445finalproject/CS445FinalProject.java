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

import cs445finalproject.physics.BoxCollider;
import cs445finalproject.physics.PhysicsEngine;
import cs445finalproject.physics.RigidBody;
import org.lwjgl.input.Keyboard;
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
        Cube cube2 = new Cube();
        cube.nameTag = "Ice Cube";
        cube.position = new Vector3(-20.0f, 50.0f, 0.0f);
        cube.initialize();
        
        cube2.position = new Vector3(-20.0f, 45.0f, 0.0f);
        cube2.initialize();
        //cube.showLocalSpace = true;
        //cube.rotation.x = 45.0f;
        
        Chunk chunk = new Chunk();
        chunk.nameTag = "Dr. Dre";
        chunk.initialize();
        
        RigidBody cubebody = new RigidBody(cube);
        RigidBody chunkbody = new RigidBody(chunk, 1000.0f);
        
        RigidBody cube2body = new RigidBody(cube2);
        cube2body.kinetic = true;
        
        BoxCollider cubeCollider = new BoxCollider(cubebody);
        BoxCollider cube2Collider = new BoxCollider(cube2body);
        chunkbody.kinetic = true; // set to true to prevent physics calculations.

        while (engine.isRunning()) {
            FatherTime.updateTime();
            PhysicsEngine.runCalculations();
            engine.update();
            
            // Push Mesh calls in here.
            engine.push(cube);
            engine.push(cube2);
            engine.push(chunk);
            
            // Test the physics engine cubes interacting with eachother.
            if (Keyboard.isKeyDown(Keyboard.KEY_H)) {
                cube2.position.x += 1.0f * FatherTime.deltaTime();
            }
            
            // Start the rendering!
            engine.render();
        }
        
        engine.cleanUp();
        PhysicsEngine.Destroy();
        System.out.println("Powered by: " + RenderEngine.ENGINE_NAME);
        System.out.println("Physics provided by: " + PhysicsEngine.ENGINE_NAME);
    }
    
}
