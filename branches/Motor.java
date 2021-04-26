package codesource;

import lejos.hardware.motor.EV3LargeRegulatedMotor; 
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Motor {
	
	RegulatedMotor motorG = new EV3LargeRegulatedMotor(MotorPort.C);   // Assignation du moteur gauche au port C
	RegulatedMotor motorD = new EV3LargeRegulatedMotor(MotorPort.A);   // Assignation du moteur droit au port A
	
	public Motor() {
		motorG.setSpeed(300);  // Assigne 415deg/s au moteur gauche
		motorD.setSpeed(302);  // Assigne 402deg/s au moteur droit
		motorG.synchronizeWith(new RegulatedMotor[] {motorD});
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
	
	public void Right() {      // Le robot tourne à droite
		motorG.forward();      
		motorD.stop();         
		Delay.msDelay(700);    // Durée de la rotation : 0.7s
	}
	
	public void Right(double i) {      // Le robot tourne à droite
		motorG.forward();      
		motorD.stop();         
		Delay.msDelay((long)i);    
	}
	
	public void Left() {       // Le robot tourne à gauche
		motorG.stop();
		motorD.forward();
		Delay.msDelay(700);
	}
	
	public void Left(double i) {       
		motorG.stop();
		motorD.forward();
		Delay.msDelay((long) i);
	}
	
	public void TurnAround() { // Le robot fait demi-tour
		motorG.backward();     
		motorD.forward();    
		Delay.msDelay(1000);
	}
	
	public void Stop() { // Le robot fait demi-tour
		motorG.startSynchronization();
		motorD.stop();
		motorG.stop();
		motorG.endSynchronization();
	}	
}