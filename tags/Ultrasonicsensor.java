package codesource;

import lejos.hardware.port.SensorPort;

import lejos.hardware.sensor.EV3UltrasonicSensor;

import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class UltraSonicSensor {

    EV3UltrasonicSensor ultraSonicSensor=new EV3UltrasonicSensor(SensorPort.S2);
    SampleProvider sp = ultraSonicSensor.getListenMode();
    SampleProvider dis = ultraSonicSensor.getDistanceMode();

    /* on utilise getlistmode car elle perme de detecter un autre sensor ultrasonic et pas just un objet 

     * ce qui nous perme de distinguer facilement le robot adverse et evite les confusion entre robot et palets*/

    public boolean detect() {
           float [] sample = new float[sp.sampleSize()];  // on cree un tableau qui va stocker les valeures contenue dans sp.
           sp.fetchSample(sample, 0); // on recupere les valeures.
           if (sample[0] == 0) {
               return true;
           }
           else {
               return false;
           }
    }

    public double distance() {     // la fonction return  la distance en metre
        float [] sample = new float[dis.sampleSize()];
        dis.fetchSample(sample, 0);
        double dism = sample[0];
        return dism ;
    }
    
    public static void main (String [] args) {
        UltraSonicSensor s = new UltraSonicSensor();
        if (s.detect()) {
            System.out.println("robot detecte");
            System.out.println("la disctance est " +s.distance());
            Delay.msDelay(4000);
        }
    }
}