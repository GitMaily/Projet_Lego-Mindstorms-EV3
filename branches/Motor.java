package codesource;

import lejos.hardware.motor.EV3LargeRegulatedMotor; 
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Motor {
	
	RegulatedMotor motorG = new EV3LargeRegulatedMotor(MotorPort.C);   // Assignation du moteur gauche au port C
	RegulatedMotor motorD = new EV3LargeRegulatedMotor(MotorPort.A);   // Assignation du moteur droit au port A
	
	public Motor() {
		motorG.setSpeed(415);  // Assigne 400deg/s au moteur gauche
		motorD.setSpeed(402);  // Assigne 400deg/s au moteur droit
		motorG.synchronizeWith(new RegulatedMotor[] {motorD});
		//motorG.startSynchronization();
	}
	
	public void setMotorGSpeed(int s) {
        motorG.setSpeed(s);
    }
    public void setMotorDSpeed(int s) {
        motorD.setSpeed(s);
    }
	
	public void Straight() {   // Le robot avance
		motorG.startSynchronization();	// DEBUT SYNCHRONISATION DES 2 ROUES
		motorG.forward();
		motorD.forward();
		motorG.endSynchronization();	// FIN SYNCHRONISATION DES 2 ROUES	
	}
	
	public void Back() {       // Le robot recule
		motorG.startSynchronization();
		motorG.backward();
		motorD.backward();
		Delay.msDelay(1000);
		motorG.endSynchronization();
	}
	
	public void Right() {      // Le robot tourne � droite
		motorG.forward();      
		motorD.stop();         
		Delay.msDelay(700);    // Dur�e de la rotation : 0.7s
	}
	
	public void Right(double i) {      // Le robot tourne � droite
		motorG.forward();      
		motorD.stop();         
		Delay.msDelay((long)i);    
	}
	
	public void Left() {       // Le robot tourne � gauche
		motorG.stop();
		motorD.forward();
		Delay.msDelay(700);
	}
	
	public void Left(double i) {       
		motorG.setSpeed(300);
		motorD.forward();
		Delay.msDelay((long) i);
	}
	
	public void TurnAround() { // Le robot fait demi-tour
		motorG.backward();      // .rotate(180);
		motorD.forward();     // .rotate(180);
		Delay.msDelay(970);
	}
	
	public void Stop() { // Le robot fait demi-tour
		motorG.startSynchronization();
		motorD.stop();
		motorG.stop();
		motorG.endSynchronization();
	}	
	
	public static void main(String[] args) {  
		Motor test=new Motor();
		test.Straight();
		Delay.msDelay(2000);
	}
}