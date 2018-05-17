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
public class Position {
 
 
    int particleX;
    int particleY;
    int particleZ;

    public Position(int particleX, int particleY,int particleZ)
    {
        this.particleX=particleX;
        this.particleY=particleY;
        this.particleZ=particleZ;
    }
    
    public int getParticleX() {
        return particleX;
    }

    public void setParticleX(int particleX) {
        this.particleX = particleX;
    }

    public int getParticleY() {
        return particleY;
    }

    public void setParticleY(int particleY) {
        this.particleY = particleY;
    }

    public int getParticleZ() {
        return particleZ;
    }

    public void setParticleZ(int particleZ) {
        this.particleZ = particleZ;
    }
    
    
    

}
