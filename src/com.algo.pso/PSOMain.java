/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo_scratch_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


/**
 *
 * @author Ranadeep Guha
 */
public class PSOMain implements PSOConstants{

ArrayList<Particle> swarm=new ArrayList<Particle>();
Random rand = new Random();
static int HIGHEST_VELOCITY=100;
static int LOWEST_VELOCITY=0;
private DestinationCities city;
private double[] pBest = new double[SWARM_SIZE];
private ArrayList<Position> pBestLocation = new ArrayList<Position>();
private double gBest;
private Position gBestLocation;
private float[] fitnessValueList = new float[SWARM_SIZE];
private float[] newLocation;
private float[] globalBest;

private int noOfCities=50;
private int maxCoordinates=50;
private ArrayList<DestinationCities> destCities=new ArrayList<>();
private SourceCity source;
static int sourceX;
static int sourceY;
static int sourceZ;
int distanceTravelled;
DestinationCities gBestDestination;
int waterRequiredBySource;
static int cityID=0;
private static ArrayList<DestinationCities> toTravel=new ArrayList<>();

//static double w=0.784321;


        


PSOMain()
{
                sourceX=ThreadLocalRandom.current().nextInt(1, maxCoordinates);
                sourceY=ThreadLocalRandom.current().nextInt(1, maxCoordinates);
                sourceZ=ThreadLocalRandom.current().nextInt(1, maxCoordinates);
                waterRequiredBySource=ThreadLocalRandom.current().nextInt(10000, 1000000);
                source=new SourceCity(sourceX, sourceY, sourceZ);
                source.setWaterRequired(waterRequiredBySource);
}

PSOMain(int sourceX, int sourceY, int sourceZ, int waterRequiredBySource)
{
                source=new SourceCity(sourceX, sourceY, sourceZ);
                this.waterRequiredBySource=waterRequiredBySource;
                

}
 public void initializeSwarm() {
		Particle p;
                System.out.println("In initialize swarm:");
                System.out.println("Value of sourceX "+sourceX);
                System.out.println("Value of sourceY "+sourceY);
                System.out.println("Value of sourceZ "+sourceZ);
                System.out.println("Water required by source is "+waterRequiredBySource);
//                
                System.out.println("Initializing Swarm");
                source=new SourceCity(sourceX, sourceY, sourceZ);
                source.setWaterRequired(waterRequiredBySource);
                for(int i=0; i<SWARM_SIZE; i++) {
			p = new Particle();

                        int particlePosX=ThreadLocalRandom.current().nextInt(1, maxCoordinates);
                        int particlePosY=ThreadLocalRandom.current().nextInt(1, maxCoordinates);
                        int particlePosZ=ThreadLocalRandom.current().nextInt(1, maxCoordinates);
		
                        // randomize location inside a space defined in Problem Set
                      Position location = new Position(particlePosX, particlePosY, particlePosZ);    
                      //  Position location = new Position(sourceX, sourceY, sourceZ);
			          
		    
                    int velocityX=ThreadLocalRandom.current().nextInt(LOWEST_VELOCITY, HIGHEST_VELOCITY);
                    int velocityY=ThreadLocalRandom.current().nextInt(LOWEST_VELOCITY, HIGHEST_VELOCITY);
                    int velocityZ=ThreadLocalRandom.current().nextInt(LOWEST_VELOCITY, HIGHEST_VELOCITY);
//                    
//                    Velocity velocity = new Velocity(0, 0, 0);
                    Velocity velocity=new Velocity(velocityX, velocityY, velocityZ);
                    p.setPosition(location);
                    p.setVelocity(velocity);
                   
                    swarm.add(p);
                    cityID++;
                 //   System.out.println("City ID "+cityID);
                    populateDestinationCityWaterOffered(p,cityID);
		}
                
             printDestinationCityDetails();
	}

    public float[] getFitnessValueList() {
        return fitnessValueList;
    }

    public void setFitnessValueList(float[] fitnessValueList) {
        this.fitnessValueList = fitnessValueList;
    }

 
 

public void updateFitnessList()
{
		for(int i=0; i<SWARM_SIZE; i++) {
                    fitnessValueList[i] = (float)swarm.get(i).getFitness(source, swarm.get(i).getDestination());
                    
                //   System.out.println("Fitness of "+i+"is "+swarm.get(i).getFitness(source, swarm.get(i).getDestination()));
                }
}   


    public int bestFit(float[] fitness)
{
    double minValue = fitness[0];
    int pos=0;
    for (int i = 1; i < fitness.length; i++) {
        if (fitness[i] < minValue) {
            minValue = fitness[i];
            pos=i;
        }
    }
    return pos;
    
}
    
    public void populateDestinationCityWaterOffered(Particle p, int cityID)
    {
        
           DestinationCities city=new DestinationCities(cityID);
           city.destinationX=ThreadLocalRandom.current().nextInt(maxCoordinates, maxCoordinates+100);
           city.destinationY=ThreadLocalRandom.current().nextInt(maxCoordinates, maxCoordinates+100);
           city.destinationZ=ThreadLocalRandom.current().nextInt(maxCoordinates, maxCoordinates+100);
           city.setWaterOffered(ThreadLocalRandom.current().nextInt(0, 1000000));
           p.setDestination(city);
           destCities.add(city);
        }

    public float[] getNewLocX() {
        return newLocation;
    }

    
    
     
    
        
    
	public void execute() {
		initializeSwarm();
		updateFitnessList();
		
		for(int i=0; i<SWARM_SIZE; i++) {
			pBest[i] = fitnessValueList[i];
			pBestLocation.add(swarm.get(i).getPosition());
                      //  System.out.println("Fitness of the swarm"+ i+"is "+fitnessValueList[i]);
		}
		          
		int t = 0;
		while(t < MAX_ITERATION) {
			// step 1 - update pBest
			for(int i=0; i<SWARM_SIZE; i++) {
				if(fitnessValueList[i] < pBest[i]) {
					pBest[i] = fitnessValueList[i];
					pBestLocation.set(i, swarm.get(i).getPosition());
				}
			}
				
			// step 2 - update gBest
			int bestParticleIndex = bestFit(fitnessValueList);
			if(t == 0 || fitnessValueList[bestParticleIndex] < gBest)
                        
                      {
				gBest = fitnessValueList[bestParticleIndex];
				gBestLocation = swarm.get(bestParticleIndex).getPosition();
                                gBestDestination=swarm.get(bestParticleIndex).getDestination();
			}
			
                        int w= 1 - ((int)(t) / MAX_ITERATION) * (1 - 0);
			double r1 = ThreadLocalRandom.current().nextInt(0, 1);
			double r2 = ThreadLocalRandom.current().nextInt(0, 1);
//			
                                
			for(int i=0; i<SWARM_SIZE; i++) {
				
				Particle p = swarm.get(i);
				
				// step 3 - update velocity
				
                                int nVelX=(int) ((w * p.getVelocity().getVelocityX()) + 
                                        (r1 * C1) * (pBestLocation.get(i).getParticleX() - p.getPosition().getParticleX()) +
                                        (r2 * C2) * (gBestLocation.getParticleX() - p.getPosition().getParticleX()));
				
                                
                                int nVelY=(int) ((w * p.getVelocity().getVelocityY()) + 
                                        (r1 * C1) * (pBestLocation.get(i).getParticleY()- p.getPosition().getParticleY()) +
                                        (r2 * C2) * (gBestLocation.getParticleY() - p.getPosition().getParticleY()));
				
                                int nVelZ=(int) ((w * p.getVelocity().getVelocityZ()) + 
                                        (r1 * C1) * (pBestLocation.get(i).getParticleZ()- p.getPosition().getParticleZ()) +
                                        (r2 * C2) * (gBestLocation.getParticleZ() - p.getPosition().getParticleZ()));

                                
                                Velocity vel = new Velocity(nVelX,nVelY,nVelZ);
				p.setVelocity(vel);
				
				// step 4 - update location
                                int newLocX=p.getPosition().getParticleX()+nVelX;
                                int newLocY=p.getPosition().getParticleY()+nVelY;
                                int newLocZ=p.getPosition().getParticleZ()+nVelZ;
                          //     System.out.println("The new Location is "+newLocX);
                                 
                                 
				Position pos = new Position(newLocX, newLocY, newLocZ);
				p.setPosition(pos);
                        //        System.out.println("The particle"+i+"is now in the position"+"("+newLocX+","+newLocY+","+newLocZ+")");
                      //           System.out.println("PBest of the particle "+i+" is "+pBest[i]);
//                                System.out.println("Distance of particle "+i+"from destination is "+distanceToGBestDestination(p, gBestLocation));
//                                newLocation = new float[1];
//                                newLocation[0]=(float) distanceToGBestDestination(p, gBestLocation);
////        
                                 

                            newLocation = new float[1];
                            newLocation[0]=(float) pBest[i];
        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        }
			
                       System.out.println("The global Best is "+gBest);
                       globalBest = new float[1];
                       globalBest[0]=(float) gBest;
            //           
			
//			System.out.println("ITERATION " + t + ": ");
//			System.out.println("     Best X: " + gBestLocation.getParticleX());
//			System.out.println("     Best Y: " + gBestLocation.getParticleY());
//                        System.out.println("     Best Z: " + gBestLocation.getParticleZ());
    			//System.out.println("     Value: " + evaluate(gBestLocation, gBestDestination));
//                       
                        evaluate(gBestLocation, gBestDestination);
			t++;
			updateFitnessList();
		}
		
                
		System.out.println("\nSolution found at iteration " + (t - 1) + ", the solutions is:");
		System.out.println(" Destination ID " + gBestDestination.getCityID()+" which offers "+gBestDestination.getWaterOffered());
                  System.out.println("Water required by Source is "+source.getWaterRequired());
                    System.out.println("Water offered is "+gBestDestination.getWaterOffered());
                    toTravel.add(gBestDestination);
               
//                    
                if(source.getWaterRequired()>=gBestDestination.getWaterOffered() && source.getWaterRequired()>=0)
                {

                    System.out.println("Enters the condition since water required by source is "+source.getWaterRequired()+
                            " and water offered by destination is "+gBestDestination.getWaterOffered());
                    
                    System.out.println("Water required by Source is "+source.getWaterRequired());
                    System.out.println("Water offered is "+gBestDestination.getWaterOffered());
                
                    System.out.println("-------------------");
                    
                    System.out.println("Needs more water");
                    waterRequiredBySource-=gBestDestination.getWaterOffered();
                    sourceX=gBestDestination.getDestinationX();
                    sourceY=gBestDestination.getDestinationY();
                    sourceZ=gBestDestination.getDestinationZ();

                    
                    System.out.println("-------------------");
                    new PSOMain(sourceX,sourceY,sourceZ,waterRequiredBySource).execute(); 
                    destCities=new ArrayList<>();
                    
                    
                }
                



              //  viewPath();
        }


   
    public int distanceToGBestDestination(Particle p, Position gBestLocation)
    {
        int locationX= gBestLocation.getParticleX();
        int locationY= gBestLocation.getParticleY();
        int locationZ= gBestLocation.getParticleZ();
        
        int particleX= p.getPosition().getParticleX();
        int particleY= p.getPosition().getParticleY();
        int particleZ= p.getPosition().getParticleZ();
        
        
//        int destinationX= destination.getDestinationX();
//        int destinationY= destination.getDestinationY();
//        int destinationZ= destination.getDestinationZ();
//        
        int distanceToDestination= (int)(((particleX-locationX)*(particleX-locationX))+((particleY-locationY)*(particleY-locationY))
                                +((particleZ-locationZ)*(particleZ-locationZ)))^(1/2);
        
        return  distanceToDestination;
    }
        
        
    public int evaluate(Position gBestLocation,DestinationCities destination)
    {
     
          int locationX= gBestLocation.getParticleX();
          int locationY= gBestLocation.getParticleY();
          int locationZ= gBestLocation.getParticleZ();
        
        double distanceTravelledByParticle=(((sourceX-locationX)*(sourceX-locationX))
            +((sourceY-locationY)*(sourceY-locationY))
            +((sourceZ-locationZ)*(sourceZ-destination.destinationZ)))^(1/2);

          
        double distanceFromSourceToDest=(((sourceX-destination.getDestinationX())*(sourceX-destination.getDestinationX()))
            +((sourceY-destination.getDestinationY()))*(sourceY-destination.getDestinationY()))
            +((sourceZ-destination.getDestinationZ())*(sourceZ-destination.getDestinationZ()))^(1/2);


        if(distanceTravelledByParticle >= distanceFromSourceToDest && source.getWaterRequired()>=destination.getWaterOffered())
        {
//            System.out.println("Water required by source"+source.getWaterRequired());
//            System.out.println("Water offered by destination"+destination.getWaterOffered());
//            
            
            return destination.cityID;
        }
 
    else
            return 0;
    }  

    private void printDestinationCityDetails() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //System.out.println("Water Required by the source city is "+waterRequiredBySource);
    for(DestinationCities city: destCities)
    {
        
        double distanceFromSourceToDest=(((sourceX-city.getDestinationX())*(sourceX-city.getDestinationX()))
            +((sourceY-city.getDestinationY()))*(sourceY-city.getDestinationY()))
            +((sourceZ-city.getDestinationZ())*(sourceZ-city.getDestinationZ()))^(1/2);


        
        System.out.println("****************************************************");
        System.out.println("Destination City ID ="+city.cityID);
        System.out.println("Destination City Water Offered ="+city.waterOffered);
        System.out.println("Distance From Source="+distanceFromSourceToDest);
        System.out.println("****************************************************");
    }
    
    
    }

    public void viewPath() {
        
    System.out.println("**********Best Route***********");
    int size=0;
    for(DestinationCities city: toTravel)
    {
        size++;
        if(size<toTravel.size())
        {
        System.out.print(city.cityID+" --> ");
        }
        else
        {
        System.out.print(city.cityID);
        }
    }
        System.out.println();
    System.out.println("*******************************");
    
    }
  


}













































