package touchSensor;

import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;

import lejos.robotics.SampleProvider;

public class TouchSensor

{

    EV3TouchSensor sensor;

    SampleProvider sp;

    public TouchSensor(Port port)				 // construire l'objet touchsensor et le régler sur le port du capteur.

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

    public static void main(String [] arg) {

    	

    	TouchSensor s = new TouchSensor(SensorPort.S1);

    	if(s.estActif()) {

    		System.out.println("palets detecte");

    	}

    }
}