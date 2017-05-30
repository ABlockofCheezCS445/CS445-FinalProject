/***************************************************************
* File: CS445FinalProject.java
* Authors: Sofia Barraza, Shaylyn Wetts, Christopher Sanchez, Mario Garcia.
* Class: CS445 - Computer Graphics
*
* assignment: Final Project - Checkpoint Assignment # 3
* date last modified: 5/29/2017
*
* purpose: Main output program, where we set up the RenderEngine, initialize it, 
* and run it by pushing our meshes into it for drawing.
*
****************************************************************/
package cs445finalproject;

import cs445finalproject.physics.BoxCollider;
import cs445finalproject.physics.PhysicsEngine;
import cs445finalproject.physics.RigidBody;
import java.util.ArrayList;
import java.util.Random;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL33;


public class CS445FinalProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RenderEngine engine = new RenderEngine(/*640, 480*/);
        PhysicsEngine.StartPhysics();
        Camera camera = new Camera();
        
        // Initialize the engine.
        engine.initialize();
        engine.start();
        
        engine.setMainCamera(camera);
        camera.setPosition(new Vector3(-20.0f, 50.0f, 10.0f));

        // Load up starting stuff...
        
        Light light = new Light();
        light.nameTag = "MC Ren";
        light.position = new Vector3(-30.0f, 50.0f, -70.0f);
        light.initialize();
        
        Chunk chunk = new Chunk();
        chunk.nameTag = "Dr. Dre";
        chunk.initialize();
        
        ArrayList<Cube> cubes = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 50; ++i) {
            Cube c = new Cube();
            c.position = new Vector3(r.nextFloat() * 100f - 80f, r.nextFloat() * 200f, r.nextFloat() * 100f - 80f);
            c.initialize();
            
            PhysicsEngine.push(c.getRigidBody());
            
            cubes.add(c);
        }

        float t = 0.0f;
        while (engine.isRunning()) {
            // Update info.
            FatherTime.updateTime();
            PhysicsEngine.runCalculations();
            engine.update();
            
            // Push Mesh calls in here.
            engine.push(chunk);
            engine.push(light);
            
            for (int i = 0; i < cubes.size(); ++i) {
                engine.push(cubes.get(i));
            }
            
            // Start the rendering!
            engine.render();
            
            // Light movement.
            light.position = new Vector3(light.position.x, light.position.y, light.position.z + (float )Math.sin(t));
            t += 1.0f * FatherTime.deltaTime();
        }
        
        engine.cleanUp();
        PhysicsEngine.Destroy();
        System.out.println("Powered by: " + RenderEngine.ENGINE_NAME);
        System.out.println("Physics provided by: " + PhysicsEngine.ENGINE_NAME);
    }
}
