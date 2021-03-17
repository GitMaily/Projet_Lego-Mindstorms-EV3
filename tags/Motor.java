package codesource;

import lejos.hardware.motor.EV3LargeRegulatedMotor; 
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class Motor {
	
	EV3LargeRegulatedMotor motorG = new EV3LargeRegulatedMotor(MotorPort.C);   // Assignation du moteur gauche au port C
	EV3LargeRegulatedMotor motorD = new EV3LargeRegulatedMotor(MotorPort.A);   // Assignation du moteur droit au port A
	
	public Motor() {
		motorG.setSpeed(400);  // Assigne 400deg/s au moteur gauche
		motorD.setSpeed(402);  // Assigne 400deg/s au moteur droit
	}
	
	public void Straight(int s) {   // Le robot avance
		motorG.forward();
		motorD.forward();
		Delay.msDelay(s);
	}
	
	public void Back() {       // Le robot recule
		motorG.backward();
		motorD.backward();
		Delay.msDelay(1500);
	}
	
	public void Right() {      // Le robot tourne à droite
		motorG.forward();      // motorG.rotate(180);
		motorD.stop();         // motorD.rotate(0);
		Delay.msDelay(750);    // Durée de la rotation : 0.75s
	}
	
	public void Left() {       // Le robot tourne à gauche
		motorG.stop();
		motorD.forward();
		Delay.msDelay(750);
	}
	
	public void TurnAround() { // Le robot fait demi-tour
		motorG.forward();      // .rotate(180);
		motorD.backward();     // .rotate(180);
		Delay.msDelay(900);
	}
	
	public void Stop() { // Le robot fait demi-tour
		motorG.stop();
		motorD.stop();
	}	
	
	public static void main(String[] args) {  
		Motor test=new Motor();
		test.Straight(1000);
		test.TurnAround();
		test.Straight(1000);
		//test.Right();
		//test.Left();
		//test.Back();
		//Delay.msDelay(1500);
	}
}