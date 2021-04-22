package codesource;

import lejos.hardware.motor.EV3LargeRegulatedMotor; 

import lejos.hardware.port.MotorPort;

import lejos.utility.Delay;

public class  Ramassage {

	

	EV3LargeRegulatedMotor motorR ;

	

	public Ramassage(MotorPort B) {

		motorR = new EV3LargeRegulatedMotor(MotorPort.B);

		motorR.setSpeed(200);

	}

	public void carry() {		// la fonction carry() serre la pince en fesant tourner le moteur en avant

		motorR.forward();

		Delay.msDelay(300);

		

	}

	public void drope() {		// drope() fait l'inversse 

		motorR.backward();

		Delay.msDelay(300);

	}

}
