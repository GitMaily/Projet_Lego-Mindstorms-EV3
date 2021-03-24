package codesource;

import lejos.utility.Delay;

public class Movement {
	
	Motor motor=new Motor();
	ColorSensor col=new ColorSensor();
	TouchSensor touch=new TouchSensor();
	Ramassage ram=new Ramassage();
	UltraSonicSensor us=new UltraSonicSensor();
	
	public void Direction() {
		col.NewColor();
		String couleur=col.getColor();
		boolean pal=false;
		
		//ram.drop();
		
		while(!touch.estActif() && couleur.equals("JAUNE")) { // le robot avance tant que la couleur de la ligne est jaune et qu'il n'y a pas de palet
			motor.Straight();
		}
		while(touch.estActif() && couleur.equals("JAUNE")) {  // S'il détecte un palet, il s'arrete, ferme ses bras et se retourne
			motor.Stop();
			ram.carry();
			Delay.msDelay(700);
			motor.TurnAround();
			pal=true;
		}
		while(pal && !couleur.equals("BLANC")) {
			motor.Straight();
			if(couleur.equals("BLANC")) {
				break;
			}
		}
		if(couleur.equals("BLANC")) {
			motor.Stop();
			ram.drop();
			motor.Back();
		}
	}
	
	public static void main(String[] args) {
		Movement test=new Movement();
		test.Direction();
	}
}
