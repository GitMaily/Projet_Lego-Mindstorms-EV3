package codesource;

import lejos.hardware.motor.EV3LargeRegulatedMotor; 
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class  Ramassage {
	
	EV3LargeRegulatedMotor motorR ;
	
	public Ramassage(MotorPort A) {
		motorR = new EV3LargeRegulatedMotor(A);
		motorR.setspeed(200);
	}
	public void carry() {		// la fonction carry() serre la pince en fesant tourner le moteur en avant
		motorD.forward();
		Delay.msDelay(300);
		
	}
	public void drope() {		// drope() fait l'inversse 
		motorD.backward();
		Delay.msDelay(300);
	}

}
