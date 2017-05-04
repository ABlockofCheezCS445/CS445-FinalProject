/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        
        Cube cube = new Cube();
        cube.initialize();
        
        while (engine.isRunning()) {
            FatherTime.updateTime();
            
            engine.update();
            // Push Mesh calls in here.
            engine.push(cube);
            
            engine.render();
        }
        
        engine.cleanUp();
    }
    
}
