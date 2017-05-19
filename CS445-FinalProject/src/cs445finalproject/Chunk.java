/***************************************************************
* File: Chunk.java
* Authors: Sofia Barraza, Shaylyn Wetts, Christopher Sanchez, Mario Garcia.
* Class: CS445 - Computer Graphics
*
* assignment: Final Project - Checkpoint Assignment # 3
* date last modified: 5/18/2017
*
* purpose: Chunk defines the overall layout of a huge mass of
* cubes and their materials and textures. 
*
****************************************************************/
package cs445finalproject;

import cs445finalproject.noise.*;
import java.nio.FloatBuffer;
import java.util.Random;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL15;
import static org.lwjgl.opengl.GL15.*;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 * Chunk is exactly what it is. It is a large
 * mass of cubes, all wrapped up into one layout
 * to define our voxel map.
 */
public class Chunk extends Mesh {
    /**
     * The chunk size in cubes.
     */
    private static final int CHUNK_SIZE = 30;
    /**
     * The overall length of the cube.
     */
    private static final int CUBE_LENGTH = 2;
    
    /**
     * The block mass that defines this chunk. This is
     * where all of the blocks and their information go.
     */
    private Block[][][] Blocks;
    
    /**
     * OpenGL handles.
     */
    private int VBOVertexHandle;
    private int VBOColorHandle;
    private int VBOTextureHandle;
    
    /**
     * The texture of each cube in our chunk.
     */
    private Texture texture;

    /**
     * Random generator used for our chunk generation.
     */
    private Random r;
    
    /**
     * method: draw
     * purpose: draws our chunk into the render engine.
     */
    @Override
    public void draw() {
        texture.bind();
        glBindBuffer(GL_ARRAY_BUFFER, VBOTextureHandle);
        glBindTexture(GL_TEXTURE_2D, 1);
        glTexCoordPointer(2, GL_FLOAT, 0, 0L);
        
        
        glBindBuffer(GL_ARRAY_BUFFER, VBOVertexHandle);
        glVertexPointer(3, GL_FLOAT, 0, 0L);

        glBindBuffer(GL_ARRAY_BUFFER, VBOColorHandle);
        glColorPointer(3, GL_FLOAT, 0, 0L);
        glDrawArrays(GL_QUADS, 0,  
                CHUNK_SIZE * CHUNK_SIZE  * CHUNK_SIZE * 24);
    }

    /**
     * method: initialize
     * purpose: initializes the chunk object, by generating the terrain and 
     * whatnot.
     */
    @Override
    public void initialize() {
        r = new Random();

        Blocks = new Block[CHUNK_SIZE][CHUNK_SIZE][CHUNK_SIZE];
        for (int x = 0; x < CHUNK_SIZE; ++x) {
            for (int y = 0; y < CHUNK_SIZE; ++y) {
                for (int z = 0; z < CHUNK_SIZE; ++z) {
                    if (r.nextFloat() > 0.7f) {
                        Blocks[x][y][z] = 
                                new Block(Block.BlockType.BlockType_Grass);
                    } else if (r.nextFloat() > 0.4f) {
                        Blocks[x][y][z] = 
                               new Block(Block.BlockType.BlockType_Dirt);
                    } else if (r.nextFloat() > 0.2f) {
                        Blocks[x][y][z] = 
                                new Block(Block.BlockType.BlockType_Water);
                    } else {
                        Blocks[x][y][z] = 
                                new Block(Block.BlockType.BlockType_Sand);
                    }
                }
            }
        }
        
        // Get our texture.
        try {
            texture = TextureLoader.getTexture("PNG", 
                    ResourceLoader.getResourceAsStream("terrain.png"));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        
        rebuildMesh(0f, 0f, 0f);
    }
    
    
    /**
     * method: rebuildMesh
     * purpose: rebuilds the giant mesh object from scratch.
     */
    public void rebuildMesh(float startX, float startY, float startZ) {
        SimplexNoise noise = new SimplexNoise(100, 0.45f, r.nextInt());
        
        position = new Vector3(startX, startY, startZ);
        VBOColorHandle = glGenBuffers();
        VBOVertexHandle = glGenBuffers();
        VBOTextureHandle = glGenBuffers();
        FloatBuffer VertexPositionData = 
                BufferUtils.createFloatBuffer(
                        (CHUNK_SIZE * CHUNK_SIZE * CHUNK_SIZE) * 6 * 12);
        FloatBuffer VertexColorData = 
                BufferUtils.createFloatBuffer(
                        (CHUNK_SIZE * CHUNK_SIZE * CHUNK_SIZE) * 6 * 12);
        FloatBuffer VertexTextureData =
                BufferUtils.createFloatBuffer(
                        (CHUNK_SIZE * CHUNK_SIZE * CHUNK_SIZE) * 6 * 12);
        for (float x = 0; x < CHUNK_SIZE; x += 1) {
            for (float z = 0; z < CHUNK_SIZE; z += 1) {
                float height = Math.abs(startY + (int )(CHUNK_SIZE * noise.getNoise((int )x, (int )z)) * 1);
                for (float y = 0; y <= height; y++) {
                    VertexPositionData.put(createCube(
                            (float )(startX + x * CUBE_LENGTH), 
                            (float )(y * CUBE_LENGTH + (int )(CHUNK_SIZE * 0.8f)),
                            (float )(startZ + z * CUBE_LENGTH)));
                    VertexColorData.put(createCubeVertexCol(
                            getCubeColor(Blocks[(int )x][(int )y][(int )z])));
                    VertexTextureData.put(createTexCube(
                            (float )0, (float )0, Blocks[(int )x][(int )y][(int )z]));
                    // TODO(): Check height and see if at some height n, we will
                    // add in a block material to define layers in our terrain.
                    // 
                }
            }
        }
        VertexColorData.flip();
        VertexPositionData.flip();
        VertexTextureData.flip();
        glBindBuffer(GL_ARRAY_BUFFER, VBOVertexHandle);
        glBufferData(GL_ARRAY_BUFFER, VertexPositionData, GL_STATIC_DRAW);
        
        glBindBuffer(GL_ARRAY_BUFFER, VBOColorHandle);
        glBufferData(GL_ARRAY_BUFFER, VertexColorData, GL_STATIC_DRAW);
        
        glBindBuffer(GL_ARRAY_BUFFER, VBOTextureHandle);
        glBufferData(GL_ARRAY_BUFFER, VertexTextureData, GL_STATIC_DRAW);
        
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }
    
    /**
     * method: createCubeVertexCol
     * purpose: Creates our cube colors for each block in the chunk. 
     */
    private float[] createCubeVertexCol(float[] CubeColorArray) {
        float[] cubeColors = new float[CubeColorArray.length * 4 * 6];
        for (int i = 0; i < cubeColors.length; ++i) {
            cubeColors[i] = CubeColorArray[i % CubeColorArray.length];
        }
        return cubeColors;
    }
    
    
    /**
     * method: createCube
     * purpose: Create our cube specifically.
     */
    private static float[] createCube(float x, float y, float z) {
        int offset = CUBE_LENGTH / 2;
        return new float[] {
        // TOP QUAD
        x + offset, y + offset, z,
        x - offset, y + offset, z,
        x - offset, y + offset, z - CUBE_LENGTH,
        x + offset, y + offset, z - CUBE_LENGTH,
        // BOTTOM QUAD
        x + offset, y - offset, z - CUBE_LENGTH,
        x - offset, y - offset, z - CUBE_LENGTH,
        x - offset, y - offset, z,
        x + offset, y - offset, z,
        // FRONT QUAD
        x + offset, y + offset, z - CUBE_LENGTH,
        x - offset, y + offset, z - CUBE_LENGTH,
        x - offset, y - offset, z - CUBE_LENGTH,
        x + offset, y - offset, z - CUBE_LENGTH,
        // BACK QUAD
        x + offset, y - offset, z,
        x - offset, y - offset, z,
        x - offset, y + offset, z,
        x + offset, y + offset, z,
        // LEFT QUAD
        x - offset, y + offset, z - CUBE_LENGTH,
        x - offset, y + offset, z,
        x - offset, y - offset, z,
        x - offset, y - offset, z - CUBE_LENGTH,
        // RIGHT QUAD
        x + offset, y + offset, z,
        x + offset, y + offset, z - CUBE_LENGTH,
        x + offset, y - offset, z - CUBE_LENGTH,
        x + offset, y - offset, z };
    }
    
    /**
     * method: getCubeColor
     * purpose: Get our cube color if there aren't any textures.
     */
    private float[] getCubeColor(Block block) {
        return new float[] { 1, 1, 1 };
    }
    
    /**
     * method: createTexCube
     * purpose: Create our texture coordinates for our blocks.
     */
    private float[] createTexCube(float x, float y, Block block) {
        float offset = (1024f/16)/1024f;
        switch (block.getID()) {
            // Grass
            case 0: return new float[] {
                // BOTTOM QUAD(DOWN=+Y)
                x + offset * 3, y + offset * 10,
                x + offset * 2, y + offset * 10,
                x + offset * 2, y + offset * 9,
                x + offset * 3, y + offset * 9,
                // TOP!
                x + offset * 3, y + offset * 1,
                x + offset * 2, y + offset * 1,
                x + offset * 2, y + offset * 0,
                x + offset * 3, y + offset * 0,
                // FRONT QUAD
                x + offset * 3, y + offset * 0,
                x + offset * 4, y + offset * 0,
                x + offset * 4, y + offset * 1,
                x + offset * 3, y + offset * 1,
                // BACK QUAD
                x + offset * 4, y + offset * 1,
                x + offset * 3, y + offset * 1,
                x + offset * 3, y + offset * 0,
                x + offset * 4, y + offset * 0,
                // LEFT QUAD
                x + offset * 3, y + offset * 0,
                x + offset * 4, y + offset * 0,
                x + offset * 4, y + offset * 1,
                x + offset * 3, y + offset * 1,
                // RIGHT QUAD
                x + offset * 3, y + offset * 0,
                x + offset * 4, y + offset * 0,
                x + offset * 4, y + offset * 1,
                x + offset * 3, y + offset * 1  
            }; 
            // Sand
            case 1: return new float[] {
                //Bottom
                x + offset * 2, y + offset * 1,
                x + offset * 3, y + offset * 1,
                x + offset * 3, y + offset * 2,
                x + offset * 2, y + offset * 2,

                //Top
                x + offset * 2, y + offset * 1,
                x + offset * 3, y + offset * 1,
                x + offset * 3, y + offset * 2,
                x + offset * 2, y + offset * 2,

                //Front
                x + offset * 2, y + offset * 1,
                x + offset * 3, y + offset * 1,
                x + offset * 3, y + offset * 2,
                x + offset * 2, y + offset * 2,

                //Back
                x + offset * 2, y + offset * 1,
                x + offset * 3, y + offset * 1,
                x + offset * 3, y + offset * 2,
                x + offset * 2, y + offset * 2,

                //Left
                x + offset * 2, y + offset * 1,
                x + offset * 3, y + offset * 1,
                x + offset * 3, y + offset * 2,
                x + offset * 2, y + offset * 2,

                //Right
                x + offset * 2, y + offset * 1,
                x + offset * 3, y + offset * 1,
                x + offset * 3, y + offset * 2,
                x + offset * 2, y + offset * 2
            };  
            // water
            case 2: return new float[] {
                //Bottom
                x + offset * 14, y + offset * 12,
                x + offset * 15, y + offset * 12,
                x + offset * 15, y + offset * 13,
                x + offset * 14, y + offset * 13,

                //Top
                x + offset * 14, y + offset * 12,
                x + offset * 15, y + offset * 12,
                x + offset * 15, y + offset * 13,
                x + offset * 14, y + offset * 13,

                //Front
                x + offset * 14, y + offset * 12,
                x + offset * 15, y + offset * 12,
                x + offset * 15, y + offset * 13,
                x + offset * 14, y + offset * 13,

                //Back
                x + offset * 14, y + offset * 12,
                x + offset * 15, y + offset * 12,
                x + offset * 15, y + offset * 13,
                x + offset * 14, y + offset * 13,

                //Left
                x + offset * 14, y + offset * 12,
                x + offset * 15, y + offset * 12,
                x + offset * 15, y + offset * 13,
                x + offset * 14, y + offset * 13,

                //Right
                x + offset * 14, y + offset * 12,
                x + offset * 15, y + offset * 12,
                x + offset * 15, y + offset * 13,
                x + offset * 14, y + offset * 13
            };                 
            // dirt
            case 3: return new float[] {
                //Bottom
                x + offset * 2, y + offset * 0,
                x + offset * 3, y + offset * 0,
                x + offset * 3, y + offset * 1,
                x + offset * 2, y + offset * 1,

                //Top
                x + offset * 2, y + offset * 0,
                x + offset * 3, y + offset * 0,
                x + offset * 3, y + offset * 1,
                x + offset * 2, y + offset * 1,

                //Front
                x + offset * 2, y + offset * 0,
                x + offset * 3, y + offset * 0,
                x + offset * 3, y + offset * 1,
                x + offset * 2, y + offset * 1,

                //Back
                x + offset * 2, y + offset * 0,
                x + offset * 3, y + offset * 0,
                x + offset * 3, y + offset * 1,
                x + offset * 2, y + offset * 1,

                //Left
                x + offset * 2, y + offset * 0,
                x + offset * 3, y + offset * 0,
                x + offset * 3, y + offset * 1,
                x + offset * 2, y + offset * 1,

                //Right
                x + offset * 2, y + offset * 0,
                x + offset * 3, y + offset * 0,
                x + offset * 3, y + offset * 1,
                x + offset * 2, y + offset * 1
            };     
            // stone
            case 4: return new float[] {
                //Bottom
                x + offset * 4, y + offset * 2,
                x + offset * 3, y + offset * 2,
                x + offset * 3, y + offset * 1,
                x + offset * 4, y + offset * 1,

                //Top
                x + offset * 4, y + offset * 2,
                x + offset * 3, y + offset * 2,
                x + offset * 3, y + offset * 1,
                x + offset * 4, y + offset * 1,

                //Front
                x + offset * 4, y + offset * 2,
                x + offset * 3, y + offset * 2,
                x + offset * 3, y + offset * 1,
                x + offset * 4, y + offset * 1,

                //Back
                x + offset * 4, y + offset * 2,
                x + offset * 3, y + offset * 2,
                x + offset * 3, y + offset * 1,
                x + offset * 4, y + offset * 1,

                //Left
                x + offset * 4, y + offset * 2,
                x + offset * 3, y + offset * 2,
                x + offset * 3, y + offset * 1,
                x + offset * 4, y + offset * 1,

                //Right
                x + offset * 4, y + offset * 2,
                x + offset * 3, y + offset * 2,
                x + offset * 3, y + offset * 1,
                x + offset * 4, y + offset * 1 
            };              
            // bedrock
            case 5: return new float[] {
                //Bottom
                x + offset * 4, y + offset * 2,
                x + offset * 5, y + offset * 2,
                x + offset * 5, y + offset * 3,
                x + offset * 4, y + offset * 3,

                //Top
                x + offset * 4, y + offset * 2,
                x + offset * 5, y + offset * 2,
                x + offset * 5, y + offset * 3,
                x + offset * 4, y + offset * 3,

                //Front
                x + offset * 4, y + offset * 2,
                x + offset * 5, y + offset * 2,
                x + offset * 5, y + offset * 3,
                x + offset * 4, y + offset * 3,

                //Back
                x + offset * 4, y + offset * 2,
                x + offset * 5, y + offset * 2,
                x + offset * 5, y + offset * 3,
                x + offset * 4, y + offset * 3,

                //Left
                x + offset * 4, y + offset * 2,
                x + offset * 5, y + offset * 2,
                x + offset * 5, y + offset * 3,
                x + offset * 4, y + offset * 3,

                //Right
                x + offset * 4, y + offset * 2,
                x + offset * 5, y + offset * 2,
                x + offset * 5, y + offset * 3,
                x + offset * 4, y + offset * 3 
            };                 
        }
        return null;
    }
}
