import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class Ultrasonicsensor {
	
	EV3UltrasonicSensor sensor;
	SampleProvider sp,dis;
	
	
	public Ultrasonicsensor(Port port){					//construire l'objet touchsensor et le regler sur la port du capteur.
		
		sensor = new EV3UltrasonicSensor(port);
		sp 	   = sensor.getListenMode();
		dis = sensor.getDistanceMode();
	}
	/* on utilise getlistmode car elle perme de detecter un autre sensor ultrasonic et pas just un objet 
	 * ce qui nous perme de distinguer facilement le robot adverse et evite les confusion entre robot et palets*/
	
	public boolean detect() {
		
	       float [] sample = new float[sp.sampleSize()];  // on cree un tableau qui va stocker les valeures contenue dans sp.

	       sp.fetchSample(sample, 0); // on recupere les valeures.

	       if (sample[0] == 1)
	           return true;
	       else
	           return false;
		
	}
	public double distance() {     // la fonction return  la distance en metre
		 
		double 	dism;
		
	       float [] sample = new float[dis.sampleSize()];  

	       dis.fetchSample(sample, 0);
	       dism = sample[0];
	       
	       return dism ;
	}
	
	
	

}
