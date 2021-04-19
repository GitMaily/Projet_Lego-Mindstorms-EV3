package codesource;

import lejos.utility.Delay;

public class Main extends Movement{

	public static void main(String[] args) {
		Movement robot=new Movement();
		String coul = couleur.getColor();
		
		if(coul.equals("JAUNE")) {
			while(coul.equals("JAUNE")) {
				motor.Straight();
				coul = couleur.getColor();
			}
			if(coul.equals("BLEU")) {
				motor.Straight();
				Delay.msDelay(400);
				robot.DirectionJauneSud();
			}
			if(coul.equals("VERT")) {
				motor.Straight();
				Delay.msDelay(400);
				robot.DirectionJauneNord();
			}
		}
	}

}
