package com.fuzzy;

public class Simulation {
	private double xA, yA, yC, time, xR, yR;
	private double vX, vY, x = 0, y;
	private double radius = 1.5;
	
	private final double g = 9.8;

	
	Simulation(int xA, int yA, int yC) {
		this.xA = xA;
		this.yA = yA;
		this.yC = yC;
	}
	
	public boolean simulate(double angle, double velocity) {
		time = 0;
		xR = 0;
		yR = 0;
		
		y = yC;
		//double hmax = (velocity*velocity*Math.sin(angle)*Math.sin(angle))/(2*g);
		//System.out.println("Vy: " +hmax);
		
		while(y > 0) { //while don't hit the floor
			
			vX = velocity*Math.cos(angle);//calculate vx and vy
			vY = velocity*Math.sin(angle);
			x = vX*time; //calculate x coordinate 
			y = yC + vY*time - (g*(time*time))/2; //calculate y coordinate
			
			System.out.println("X: " + x + " | Y: " + y);
			
			if(x < (xA + radius) && x > (xA - radius)) //check if yR for x coordinate equals xA
				yR = y;
			
			if(y > (yA - radius) && y < (yA + radius) && x < (xA + radius) && x > (xA - radius))
				return true;
			
			time = time + 0.1; //increments time
		}
		xR = x;	//TODO
		return false;
  	}
	
	public double getDX() {
		return xR - xA;
	}
	public double getDY() {
		return yR - yA;
	}
}
