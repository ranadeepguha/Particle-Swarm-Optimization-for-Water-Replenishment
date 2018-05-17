    Water Replenishment Strategy using Particle Swarm Optimization
    

**Problem Statement**

The project is an extension of my previous project- ‘Integrated Water Replenishment and Management System’. 
The main idea behind the project was to create a sensor based system which would notify government officials when there is a chance of water shortage or droughts such that they can take necessary preventive actions. 
One of the replenishment strategies that was suggested was that of inter-state transportation of water. This project aims at solving the interstate transportation problem based on the distance of the different cities and the water that they can provide to replenish the city’s water reservoirs.


**Solution:**


The project uses particle swarm optimization to reach the fittest water source. The fitness of the water source is evaluated by the following function:
√((xs-xd)<sup>2</sup>+(ys-yd)<sup>2</sup>+(zs-zd)<sup>2</sup>)+(wo-wr)

where,
-   xs, ys, zs are the coordinates of the particle
-   xd,yd,zd are the coordinates of the destination cities offering water
-   wo is the water offered by the city
-   wr is the water required by the source city


The example code available on https://github.com/therealmanalu/pso-example-java was extended for solving this problem.

When the water level in the source city falls below the critical level, the sensor alerts other sensors placed around the city to initiate particles to search for water sources around it. The sensors placed in the other cities are taken in the 3-D coordinate system.
The swarm is then triggered from the source city towards the destination cities (those who have offered help). The particles in the swarm keep exchanging their relative position with each other and figure out which is the best route that they should take. The particles slowly converge towards the city which provides the optimal solution based on the above fitness function. 
Once the particle reaches, it checks if the water provided by the destination city would suffice the requirements of the source city. If it does, it triggers an alert and the interstate transportation is arranged to get water from that city.


In case, the water is not sufficient to fill the reservoirs, a fresh set of particles are spawned from this destination city, in search of the remaining water. The process continues until the required water is obtained from the neighboring cities.
The algorithm not only returns the best possible solution from one neighboring city, but also tackles the problem of shortest path, such that the cost of transportation is optimized. When more than one cities are involved in the process, it returns one possible optimum solution by which water could be brought back, with the help of swarm chaining.



![picture alt](https://s3.amazonaws.com/ranadeep.space/images/PSO+Formula.jpg"formula")

where 
-   w=0.784321;
-   c1=1.49618;
-   c2=1.49618;
-   ![picture alt](https://s3.amazonaws.com/ranadeep.space/images/PSOsymbol.jpg"symbol")
  = Random numbers




The below picture represents the pBest calculations with time::


![picture alt](https://s3.amazonaws.com/ranadeep.space/images/PSOsymbol.jpg"symbol")




    How to run the project?

1. Clone the repository
2. Open the code in Netbeans or any other editor
3. Run the project

  
    How to display the p-best or g-best graphs?

1. Open the code in Netbeans or any other editor
2. Right click on pBestChart.java or gBestChart.java
3. Click on Run File
