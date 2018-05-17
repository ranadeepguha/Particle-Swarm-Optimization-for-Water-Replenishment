/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo_scratch_1;

/**
 *
 * @author Ranadeep Guha
 */
public class SourceCity {
    
    int waterRequired=1000;
    int sourceX;
    int sourceY;
    int sourceZ;

    
    public SourceCity(int sourceX, int sourceY, int sourceZ)
    {
        this.sourceX=sourceX;
        this.sourceY=sourceY;
        this.sourceZ=sourceZ;
    }
    
    public int getSourceX() {
        return sourceX;
    }

    public void setSourceX(int sourceX) {
        this.sourceX = sourceX;
    }

    public int getSourceY() {
        return sourceY;
    }

    public void setSourceY(int sourceY) {
        this.sourceY = sourceY;
    }

    public int getSourceZ() {
        return sourceZ;
    }

    public void setSourceZ(int sourceZ) {
        this.sourceZ = sourceZ;
    }
    
    
    
    public int getWaterRequired() {
        return waterRequired;
    }

    public void setWaterRequired(int waterRequired) {
        this.waterRequired = waterRequired;
    }
    
    
}
