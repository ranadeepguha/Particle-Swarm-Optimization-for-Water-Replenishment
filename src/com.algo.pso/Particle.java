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
public class Particle {

    /**
     * @param args the command line arguments
     */
 
private Position position;
private Velocity velocity;
private double fitness;
private DestinationCities destination;


    public DestinationCities getDestination() {
        return destination;
    }

    public void setDestination(DestinationCities destination) {
        this.destination = destination;
    }




    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Velocity getVelocity() {
        return velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public double getFitness(SourceCity source, DestinationCities destination) {
        fitness=setFitness(source, destination);
        return fitness;
    }

    public double setFitness(SourceCity source, DestinationCities destination) {
     
   // int[] particlePosition = this.position.getPosition();
   int particleX=this.position.getParticleX();
   int particleY=this.position.getParticleY();
   int particleZ=this.position.getParticleZ();
   
   int sourceX=source.getSourceX();
   int sourceY=source.getSourceY();
   int sourceZ=source.getSourceZ();
   
   
   int destinationX=destination.getDestinationX();
   int destinationY=destination.getDestinationY();
   int destinationZ=destination.getDestinationZ();
   
//   
//   double distanceFromSourceToDest=(((sourceX-destinationX)*(sourceX-destinationX))
//            +((sourceY-destinationY)*(sourceY-destinationY))
//            +((sourceZ-destinationZ)*(sourceZ-destinationZ)))^(1/2);

   double distanceFromParticleToDest=(((particleX-destinationX)*(particleX-destinationX))
            +((particleY-destinationY)*(particleY-destinationY))
            +((particleZ-destinationZ)*(particleZ-destinationZ)))^(1/2);

   
           // System.out.println("Distance to Destination= "+distanceFromParticleToDest);
//    int distanceToDestination=distanceFromSourceToDest-particlePosition;
    int fulfilPromise =source.getWaterRequired()-this.destination.getWaterOffered();
    fitness=fulfilPromise*(distanceFromParticleToDest)/(1000000000*1000000000);
    //System.out.println("Fitness Value="+fitness);
    
    
    
    return fitness;
    }


    
    
    
}
