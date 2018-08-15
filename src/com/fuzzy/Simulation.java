package com.fuzzy;

public class Simulation {
	private double xA, yA, yC, time, xR, yR;
	private double vX, vY, x = 0, y;
	private double radius = 1;
	
	private final double g = 9.8;

	
	Simulation(int xA, int yA, int yC) {
		this.xA = xA;
		this.yA = yA;
		this.yC = yC;
	}
	double yNaHora = 0; //TODO
	public boolean simulate(double angle, double velocity) {
		
		time = 0;
		double targetTime = 0;
		yR = 0;
		
		y = yC;
		//double hmax = (velocity*velocity*Math.sin(angle)*Math.sin(angle))/(2*g);
		//System.out.println("Vy: " +hmax);
		
		while(y > 0) { //while don't hit the floor
			
			vX = velocity*Math.cos(angle);//calculate vx and vy
			vY = velocity*Math.sin(angle);
			
			x = vX*time; //calculate x coordinate 
			y = yC + vY*time - (g*(time*time))/2; //calculate y coordinate
			
			//System.out.println("X: " + x + " | Y: " + y);
			
			/*if(x < (xA + radius) && x > (xA - radius)) //check if yR for x coordinate equals xA
			{
				yR = y;
				System.out.println("O Y na hora" +y);
			}  */
			
			if(xA < xR) {
				targetTime = xA/vX;
				yR = yC + vY*targetTime - (g*(targetTime*targetTime))/2; 
				//System.out.println(yR);
			} else {
				yR = 0;
			}
			
			
			if(y > (yA - radius) && y < (yA + radius) && x < (xA + radius) && x > (xA - radius)) {
				//System.out.println("X: " + x + " | Y: " + y);
				return true;
				
			}
			
		

			xR = vX*((2*velocity*Math.sin(angle))/g);
				
			
			time = time + 0.1; //increments time
		}
		//xR = x;	//TODO
		return false;
  	}
	public double getYnaHora() {
		return yNaHora;
	}
	public double getYr() {
		return yR;
	}
	public double getXa() {
		return xA;
	}

	public double getDX() {
		return xR - xA;
	}
	public double getDY() {
		return yR - yA;
	}
}
