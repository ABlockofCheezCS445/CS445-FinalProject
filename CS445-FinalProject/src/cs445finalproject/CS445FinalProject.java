/***************************************************************
* File: CS445FinalProject.java
* Authors: Mario Garcia, [plz put yo names here]
* Class: CS 445
*
* assignment: Final Project
* date last modified: 5/4/2017
*
* purpose: Main output program, where we set up the RenderEngine, initialize it, 
* and run it by pushing our meshes into it for drawing.
*
****************************************************************/
package cs445finalproject;

/**
 *
 * @author alexa
 */
public class CS445FinalProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RenderEngine engine = new RenderEngine();
        Camera camera = new Camera();
        
        // Initialize the engine.
        engine.initialize();
        engine.start();
        
        Vector3 n = new Vector3(100f, 100f, 100f).normalize();
        
        engine.setMainCamera(camera);
        camera.setPosition(new Vector3(0.0f, 0.0f, 10.0f));
        
        Vector3 a = new Vector3(0.0f, 0.0f, 0.0f);
        Vector3 b = new Vector3(10.0f, 0.0f, 0.0f);
        Cube cube = new Cube();
        cube.initialize();
        
        float t = 0.0f;
        while (engine.isRunning()) {
            FatherTime.updateTime();
            
            engine.update();
            // Push Mesh calls in here.
            engine.push(cube);
            // Just testing functionality...
            cube.position = Vector3.lerp(a, b, FatherTime.smoothstep(0.0f, 1.0f, t));   
            System.out.println("Cube pos: x=" + cube.position.x + " y=" + cube.position.y + " z=" + cube.position.z);
            
            engine.render();
            t += 0.01f;
        }
        
        engine.cleanUp();
    }
    
}
