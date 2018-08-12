package com.fuzzy;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;

public class FuzzyBall {
	public static void main(String[] args) throws Exception {
		String filename = "tipper.fcl";
		FIS fis = FIS.load(filename, true);
		double dx, dy;
		double speedAd, angleAd;
		boolean hited = false;

		if (fis == null) {
			System.err.println("Can't load file: '" + filename + "'");
			System.exit(1);
		}

		Simulation simulation = new Simulation(10, 5, 1);
		double speed = 5, angle = Math.toDegrees(45);
		int i = 1;
		while(!hited) {
			System.out.println("Tentativa " + i);
			System.out.println("Entradas");
			System.out.println("Speed: " +speed + " || Angle: " +angle);
			System.out.println();
			
			hited = simulation.simulate(angle, speed);
			
			if(hited)
				System.out.println("Acertô, miserave");
			else {

				dx = simulation.getDX();
				dy = simulation.getDY();
				System.out.println("Diferenças:");
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
				
				System.out.println("Ajustes");
				System.out.println("Velocidade: " +speedAd + " || Ajuste ângulo: " + angleAd);
				System.out.println();
				//
				//		// Show output variable's chart
				speed += speedAd;
				angle += angleAd;
				
				/*if(angle > 90)
					angle = 90;
				else if(angle < 0)
					angle = 0;
				
				if(speed > 20)
					speed = 20;
				else if(speed < 1)
					speed = 1;*/
			}
			
			i++;
		}

		//		// Print ruleSet
		//		System.out.println(fb);
		//		System.out.println("Speed: " + fb.getVariable("speed").getValue());

	}

}
