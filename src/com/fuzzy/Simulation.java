package com.fuzzy;

public class Simulation {
	private double xA, yA, yC, time = 0, xR = 0, yR;
	private double vX, vY, x, y;
	
	private final double g = 9.8;

	
	Simulation(int xA, int yA, int yC) {
		this.xA = xA;
		this.yA = yA;
		this.yC = yC;
		y = yC;
	}
	
	public void simulate(int angle, int velocity) {
		while(y > 0) { //while dont heat the floor
			vX = velocity*Math.cos(angle);//calculate vx and vy
			vY = velocity*Math.sin(angle);
			x = vX*time; //calculate x coordinate 
			y = yC + vY*time - (g*(time*time))/2; //calculate y coordinate
			if(x == xA) { //check if yR for x coordinate equals xA
				yR = y;
			}
			time++; //increments time
		}
		xR = x;			
  	}
	
	public double getDX() {
		return xR - xA;
	}
	public double getDY() {
		return yR - yA;
	}
}
