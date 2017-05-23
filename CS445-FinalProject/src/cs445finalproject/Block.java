/***************************************************************
* File: Block.java
* Authors: Sofia Barraza, Shaylyn Wetts, Christopher Sanchez, Mario Garcia.
* Class: CS445 - Computer Graphics
*
* assignment: Final Project - Checkpoint Assignment # 3
* date last modified: 5/18/2017
*
* purpose: Block describes a chunk's material and type. It also keeps 
* track of locations and identifications.
*
****************************************************************/
package cs445finalproject;

import cs445finalproject.physics.BoxCollider;
import cs445finalproject.physics.PhysicsEngine;
import cs445finalproject.physics.RigidBody;

/**
 * Block is the fundamental building block of our voxel engine terrain for
 * Chunk.
 */
public class Block {
    private Mesh mesh;
    private RigidBody rigidbody;
    private BoxCollider collider;
    
    /**
     * Our block enum type, which defines how our textures for each block 
     * works.
     */
    public enum BlockType { 
        BlockType_Grass(0),
        BlockType_Sand(1),
        BlockType_Water(2),
        BlockType_Dirt(3),
        BlockType_Stone(4),
        BlockType_Bedrock(5);
        
        private int BlockID;
        
        /**
         * method: BlockType
         * purpose: constructor.
         */
        BlockType(int i) {
            BlockID = i;
        }
        
        /**
         * method: getID
         * purpose: Get the id of this blocktype.
         */
        public int GetID() {
            return BlockID;
        }
        
        /**
         * method: SetId
         * purpose: Sets the id of our block type.
         */
        public void SetID(int i) {
            BlockID = i;
        }
    };
    
    /**
     * The block type id.
     */
    private BlockType Type;
    /**
     * Check if this block is active.
     */
    private boolean isActive;
    /**
     * The block coordinates.
     */
    private Vector3 coords;
    
    /**
     * method: Block
     * purpose: The block constructors.
     */
    public Block(BlockType type) {
        coords = new Vector3();
        Type = type;
        isActive = true;
        mesh = new Cube();
        mesh.position = coords;
        mesh.initialize();
        
        rigidbody = new RigidBody(mesh);
        collider = new BoxCollider(rigidbody);
        rigidbody.kinematic = true;
    }
    
    /**
     * method: setCoords
     * purpose: Sets the coordinates of this block
     */
    public void setCoords(Vector3 newPos) {
        coords = newPos;
        mesh.position = coords;
    }
    
    /**
     * method: isActive
     * purpose: Checks of this block is active.
     */
    public boolean isActive() {
        return isActive;
    }
    
    /**
     * method: setActive
     * purpose: Sets the active status of this block.
     */
    public void setActive(boolean active) {
        isActive = active;
    }
    
    /**
     * method: getID
     * purpose: Get the id of this block.
     */
    public int getID() {
        return Type.GetID();
    }
    
    
    public RigidBody getRigidBody() {
        return rigidbody;
    }
}
