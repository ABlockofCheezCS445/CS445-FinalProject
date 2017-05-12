/***************************************************************
* File: Block.java
* Authors: Sofia Barraza, Shaylyn Wetts, Christopher Sanchez, Mario Garcia.
* Class: CS445 - Computer Graphics
*
* assignment: Final Project - Checkpoint Assignment # 2
* date last modified: 5/12/2017
*
* purpose: Block describes a chunk's material and type. It also keeps 
* track of locations and identifications.
*
****************************************************************/
package cs445finalproject;

/**
 *
 * @author alexa
 */
public class Block {
    public enum BlockType { 
        BlockType_Grass(0),
        BlockType_Sand(1),
        BlockType_Water(2),
        BlockType_Dirt(3),
        BlockType_Stone(4),
        BlockType_Bedrock(5);
        
        private int BlockID;
        
        BlockType(int i) {
            BlockID = i;
        }
        
        public int GetID() {
            return BlockID;
        }
        
        public void SetID(int i) {
            BlockID = i;
        }
    };
    
    
    private BlockType Type;
    private boolean isActive;
    private Vector3 coords;
    
    
    public Block(BlockType type) {
        Type = type;
    }
    
    public void setCoords(Vector3 newPos) {
        coords = newPos;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    public int getID() {
        return Type.GetID();
    }
}
