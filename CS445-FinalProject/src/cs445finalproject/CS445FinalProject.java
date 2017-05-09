/***************************************************************
* File: CS445FinalProject.java
* Authors: Sofia Barraza, Shaylyn Wetts, Christopher Sanchez, Mario Garcia.
* Class: CS445 - Computer Graphics
*
* assignment: Final Project - Checkpoint Assignment # 1
* date last modified: 5/8/2017
*
* purpose: Main output program, where we set up the RenderEngine, initialize it, 
* and run it by pushing our meshes into it for drawing.
*
****************************************************************/
package cs445finalproject;

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
        Camera camera = new Camera();
        
        // Initialize the engine.
        engine.initialize();
        engine.start();
        
        engine.setMainCamera(camera);
        camera.setPosition(new Vector3(0.0f, 0.0f, 10.0f));

        Cube cube = new Cube();
        cube.initialize();
        cube.showLocalSpace = true;
        //cube.rotation.x = 45.0f;
       
        float t = 0f;
        while (engine.isRunning()) {
            FatherTime.updateTime();
            
            engine.update();
            // Push Mesh calls in here.
            engine.push(cube);
            cube.rotation.x = t;
            cube.rotation.z = t;
            engine.render();
            t += 100.0f * (float ) FatherTime.deltaTime();
        }
        
        engine.cleanUp();
    }
    
}
