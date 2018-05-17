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
public class Velocity {
 
int velocityX;
int velocityY;
int velocityZ;

public Velocity()
{}
public Velocity(int velocityX, int velocityY, int velocityZ)
{
    this.velocityX=velocityX;
    this.velocityY=velocityY;
    this.velocityZ=velocityZ;
}

    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public int getVelocityZ() {
        return velocityZ;
    }

    public void setVelocityZ(int velocityZ) {
        this.velocityZ = velocityZ;
    }



}
