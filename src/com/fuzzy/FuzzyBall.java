package com.fuzzy;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;

public class FuzzyBall {
	public static void main(String[] args) throws Exception {
		
		//System.out.println("Cosseno: " +Math.cos());
		String filename = "tipper.fcl";
		FIS fis = FIS.load(filename, true);
		double dx, dy;
		double speedAd, angleAd;
		boolean hit = false;

		if (fis == null) {
			System.err.println("Can't load file: '" + filename + "'");
			System.exit(1);
		}

		Simulation simulation = new Simulation(9, 5, 1);
		double speed = 1, angle = Math.toRadians(10);
		hit = simulation.simulate(angle, speed);
		
		if(hit)
			System.out.println("\nHit!");
		//System.out.println(Math.cos(Math.toRadians(180)-angle));
	    int i = 1;
		while(!hit) {
//			System.out.println("Attempt " + i);
//			System.out.println("Inputs");
//			System.out.println("Speed: " +speed + " || Angle: " +Math.toDegrees(angle));
//			System.out.println();
			
			hit = simulation.simulate(angle, speed);
			dx = simulation.getDX();
			dy = simulation.getDY();
			
			if(hit) {
				System.out.println("\nHit!");
	
			}
			else {
		//		System.out.println(i + " " + simulation.getXr() + " " + simulation.getYnaHora());
				System.out.println("\nDiferences:");
				System.out.println("DX: " + dx + " || DY: " + dy);
				System.out.println();
	
				//		
				//		// Get default function block
				FunctionBlock fb = fis.getFunctionBlock(null);
				//
				//		// Set inputs
				fb.setVariable("dx", dx);
				fb.setVariable("dy", dy);
				//
				//		// Evaluate
				fb.evaluate();
				
				speedAd = fb.getVariable("speed").defuzzify();
				angleAd = fb.getVariable("angle").defuzzify();
				
				//System.out.println(angleAd);
				
				System.out.println("Outputs");
				System.out.println("Speed: " +speedAd + " || Angle: " + Math.toDegrees(angleAd));
				System.out.println();
				//
				//		// Show output variable's chart
				speed += speedAd;
				
			    angle += angleAd;
				
				if(angle > 1.57)
					angle = 1.57;
				else if(angle < 0)
					angle = 0;
			

			}
			
			i++;
		}

		//		// Print ruleSet
		//		System.out.println(fb);
		//		System.out.println("Speed: " + fb.getVariable("speed").getValue());

	}
	
}
