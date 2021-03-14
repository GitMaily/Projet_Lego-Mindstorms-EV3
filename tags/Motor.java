package codesource;

import lejos.hardware.motor.EV3LargeRegulatedMotor; 
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class Motor {
	
	EV3LargeRegulatedMotor motorG = new EV3LargeRegulatedMotor(MotorPort.C);   // Assignation du moteur gauche au port C
	EV3LargeRegulatedMotor motorD = new EV3LargeRegulatedMotor(MotorPort.A);   // Assignation du moteur droit au port A
	
	public Motor() {
		motorG.setSpeed(400);  // Assigne 400deg/s au moteur gauche
		motorD.setSpeed(400);  // Assigne 400deg/s au moteur droit
	}
	
	public void Straight() {   // Le robot avance
		motorG.forward();
		motorD.forward();
	}
	
	public void Back() {       // Le robot recule
		motorG.backward();
		motorD.backward();
	}
	
	public void Right() {      // Le robot tourne à droite
		motorG.setSpeed(0);
		motorD.setSpeed(500);
		Delay.msDelay(750);    // Durée de la rotation : 0.75s
	}
	
	public void Left() {       // Le robot tourne à gauche
		motorG.setSpeed(500);
		motorD.setSpeed(0);
		Delay.msDelay(750);
	}
	public static void main(String[] args) {  
		Motor test=new Motor();
		test.Straight();
		Delay.msDelay(5000);
		test.Left();
		test.Right();
		test.Back();
		Delay.msDelay(3000);
	}
}