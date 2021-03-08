package codesource;

import lejos.hardware.motor.EV3LargeRegulatedMotor; 
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class Motor extends ColorSensor{
		
	EV3LargeRegulatedMotor motorG = new EV3LargeRegulatedMotor(MotorPort.C);   // Assignation du moteur gauche au port C
	EV3LargeRegulatedMotor motorD = new EV3LargeRegulatedMotor(MotorPort.A);   // Assignation du moteur droit au port A
	
	
	public void getMotor(String couleur) {
		motorG.setSpeed(400);  // Assigne 400deg/s au moteur gauche
		motorD.setSpeed(400);
			
		motorG.forward();      // Le robot avance pendant 3sc
		motorD.forward();
		Delay.msDelay(3000);
	
		if(couleur=="BLANC") { // Lorsqu'il rencontre une ligne blanche, il tourne à gauche
			motorG.setSpeed(500);
			motorD.setSpeed(0);
			Delay.msDelay(750);	
		}
	}	
	
	/*public static void main(String[] args) {  
		new Motor();
	}*/
}