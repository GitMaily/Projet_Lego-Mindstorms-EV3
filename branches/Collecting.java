package codesource;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class  Collecting {

    EV3LargeRegulatedMotor motorR ;

    public Collecting() {

        motorR = new EV3LargeRegulatedMotor(MotorPort.B);
        motorR.setSpeed(1100);
    }

    public void carry() {        // la fonction carry() serre la pince en fesant tourner le moteur en avant

        motorR.backward();
        Delay.msDelay(1400);
    }

    public void drop() {        // drope() fait l'inversse 

        motorR.forward();
        Delay.msDelay(500);
    }
}