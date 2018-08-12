package com.fuzzy;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;

public class FuzzyBall {
	public static void main(String[] args) throws Exception {
		String filename = "tipper.fcl";
		FIS fis = FIS.load(filename, true);
		double dx, dy;

		if (fis == null) {
			System.err.println("Can't load file: '" + filename + "'");
			System.exit(1);
		}

		Simulation simulation = new Simulation(3, 2, 1);
		double speed = 10, angle = Math.toRadians(45);
		int i = 0;
		while(i < 10) {
			System.out.println("Velocidade: " +speed + "Angle: " +angle);

			

			simulation.simulate(angle, speed);

			dx = simulation.getDX();
			dy = simulation.getDY();
			System.out.println(dx);
			System.out.println(dy);

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
			//
			//		// Show output variable's chart
			speed = fb.getVariable("speed").defuzzify();
			angle = fb.getVariable("angle").defuzzify();

			System.out.println("Velocidade: " +speed + "Angle: " +angle);
		}

		//		// Print ruleSet
		//		System.out.println(fb);
		//		System.out.println("Speed: " + fb.getVariable("speed").getValue());

	}

}
