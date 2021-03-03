package codesource;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class ColorSensor {

	EV3ColorSensor colorSensor; //Variable globale 
	SampleProvider colorProvider; //Mode sp�cifique du capteur couleur
	float[] colorSample; //Cr�ation du tableau de type float

	//Constructeurs 
	public ColorSensor() {
		Port p3=LocalEV3.get().getPort("P3"); //Avoir le port auquel le capteur est associ�
		colorSensor=new EV3ColorSensor(p3); //Assignation du capteur couleur au port
		colorProvider=colorSensor.getRGBMode(); //Assigne la couleur de la ligne, 8 unique colors (NONE, BLACK, BLUE, GREEN, YELLOW, RED, WHITE, BROWN)
		colorSample=new float[colorProvider.sampleSize()]; //Assigne la taille de l'�chantillon, soit le fournisseur couleur
		
		
		while(colorSample!=null) { 
			colorProvider.fetchSample(colorSample,0);
			System.out.println(colorProvider);
		}
		
		colorSensor.close(); 
	}
	
	public static void main(String[] args) {
		new ColorSensor();
	}
}