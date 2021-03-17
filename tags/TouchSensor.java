import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;
import lejos.hardware.Device
import lejos.hardware.motor.BaseRegulatedMotor

public class TouchSensor
{
    EV3TouchSensor sensor;
    SampleProvider sp;

    public TouchSensor(Port port)				 // construire l'objet touchsensor et le regler sur la port du capteur.
    {
        sensor = new EV3TouchSensor(port);
        sp = sensor.getTouchMode();				// sp prend les valeures recupere du capteur par gettouchemode().
        										// getTouchemode() return une valeure int 0 ou 1 .
    }

    public boolean estActif()
    {
       float [] sample = new float[sp.sampleSize()];  // on cree un tableau qui va stocker les valeures contenue dans sp.

       sp.fetchSample(sample, 0); // on recupere les valeures.

       if (sample[0] == 0)
           return false;
       else
           return true;
    }
    public static void main(String []) {
    	
    	TouchSensor s = new TouchSensor(2);
    	
    	EV3MediumRegulatedMotor m1 = new EV3MediumRegulatedMotor(A);
    	EV3MediumRegulatedMotor m2 = new EV3MediumRegulatedMotor(B);
    	
    	while(!estActif) {
    		m1.forward();
    		m2.forward();
    	}
    	
    	m1.stop();
    	m2.stop();

    	}
    }

}
