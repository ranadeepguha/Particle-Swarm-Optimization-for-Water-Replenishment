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
public class DestinationCities {
  
    int cityID;
    int destinationX;
    int destinationY;
    int destinationZ;
            
    int waterOffered;

    
    DestinationCities(int cityID)
    {
        this.cityID=cityID;
    }
    
    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public int getDestinationX() {
        return destinationX;
    }

    public void setDestinationX(int destinationX) {
        this.destinationX = destinationX;
    }

    public int getDestinationY() {
        return destinationY;
    }

    public void setDestinationY(int destinationY) {
        this.destinationY = destinationY;
    }

    public int getDestinationZ() {
        return destinationZ;
    }

    public void setDestinationZ(int destinationZ) {
        this.destinationZ = destinationZ;
    }

    

   
    
    public int getWaterOffered() {
        return waterOffered;
    }

    public void setWaterOffered(int waterOffered) {
        this.waterOffered = waterOffered;
    }
    
    
    
}
