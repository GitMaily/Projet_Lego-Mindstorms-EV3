package codesource;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class  Ramassage {

    EV3LargeRegulatedMotor motorR ;

    public Ramassage() {

        motorR = new EV3LargeRegulatedMotor(MotorPort.B);
        motorR.setSpeed(1100);
    }

    public void carry() {        // la fonction carry() serre la pince en fesant tourner le moteur en avant

        motorR.backward();
        Delay.msDelay(1500);
    }

    public void drop() {        // drope() fait l'inversse 

        motorR.forward();
        Delay.msDelay(1500);
    }
}