package codesource;

import lejos.hardware.motor.EV3LargeRegulatedMotor; 
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Motor {
	
	RegulatedMotor motorG = new EV3LargeRegulatedMotor(MotorPort.C);   // Assignation du moteur gauche au port C
	RegulatedMotor motorD = new EV3LargeRegulatedMotor(MotorPort.A);   // Assignation du moteur droit au port A
	
	public Motor() {
		motorG.setSpeed(400);  // Assigne 400deg/s au moteur gauche
		motorD.setSpeed(402);  // Assigne 400deg/s au moteur droit
		motorG.synchronizeWith(new RegulatedMotor[] {motorD});
		//motorG.startSynchronization();
	}
	
	public void Straight() {   // Le robot avance
		motorG.startSynchronization();
		motorG.forward();
		motorD.forward();
		motorG.endSynchronization();
	}
	
	public void Back() {       // Le robot recule
		motorG.startSynchronization();
		motorG.backward();
		motorD.backward();
		Delay.msDelay(1000);
		motorG.endSynchronization();
	}
	
	public void Right() {      // Le robot tourne à droite
		motorG.forward();      
		motorD.stop();         
		Delay.msDelay(700);    // Durée de la rotation : 0.75s
	}
	public void Right(long d) {
		motorG.forward();      
		motorD.stop();         
		Delay.msDelay(d);
	}
	
	public void Left() {       // Le robot tourne à gauche
		motorG.stop();
		motorD.forward();
		Delay.msDelay(700);
	}
	public void Left(long d) {       
		motorG.stop();
		motorD.forward();
		Delay.msDelay(d);
	}
	public void TurnAround() { // Le robot fait demi-tour
		motorG.backward();      // .rotate(180);
		motorD.forward();     // .rotate(180);
		Delay.msDelay(900);
	}
	
	public void Stop() { // Le robot fait demi-tour
		motorG.startSynchronization();
		motorD.stop();
		motorG.stop();
		motorG.endSynchronization();
	}	
	
	/*public static void main(String[] args) {  
		Motor test=new Motor();
		test.Straight();
		Delay.msDelay(2000);
		test.TurnAround();
		test.Straight();
		//test.Right();
		//test.Left();
		//test.Back();
		//Delay.msDelay(1500);
	}*/
}