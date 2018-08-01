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
		simulation.simulate(45, 10);
		
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
		fb.getVariable("speed").defuzzify();
		fb.getVariable("angle").defuzzify();
//
//		// Print ruleSet
		System.out.println(fb);
		System.out.println("Speed: " + fb.getVariable("speed").getValue());

	}

}
