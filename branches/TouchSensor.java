package codesource;


import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;

public class TouchSensor{
	
    EV3TouchSensor sensor = new EV3TouchSensor(SensorPort.S1);
    SampleProvider sp = sensor.getTouchMode();
   
    public boolean estActif()
    {
       float [] sample = new float[sp.sampleSize()];  // on cree un tableau qui va stocker les valeures contenue dans sp.
       sp.fetchSample(sample, 0); // on recupere les valeures.
       if (sample[0] == 0)
           return false;
       else
           return true;
    }
    /*public static void main(String [] arg) {
    	
    	TouchSensor s = new TouchSensor();
    	Ramassage ram=new Ramassage();
    	
    	
    	if(s.estActif()) {
    		System.out.println("palet détecté");
    		Delay.msDelay(1500);
    		ram.carry();
    		ram.drop();
    		}
    }*/
}