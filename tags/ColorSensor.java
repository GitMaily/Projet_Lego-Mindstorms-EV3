package codesource;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class ColorSensor {

	//Attributs
	EV3ColorSensor colorSensor=new EV3ColorSensor(SensorPort.S3); // Assigne le capteur couleur au port 3
	SampleProvider colorProvider=colorSensor.getRGBMode();        // Déclaration du mode getRGB, et assignation de la couleur dans la variable
	float[] colorSample=new float[colorProvider.sampleSize()];    // Déclaration du tableau de taille 3 pour stocker les 3 valeurs des couleurs d'une ligne
	
	//Constructeurs
	public ColorSensor() {
		colorProvider.fetchSample(colorSample,0);                 // Stock les 3 valeurs d'une couleur
		System.out.println("RGB="+colorSample[0]+"/"+colorSample[1]+"/"+colorSample[2]); // Test pour retourner la valeur d'une couleur
		
		/*if((colorSample[0]>xf) && (colorSample[0]<xf) && (colorSample[1]>xf) && (colorSample[1]<xf) && (colorSample[2]>xf) && (colorSample[2]<xf)){
		 * System.out.println("couleur1"); }
		 * 
		 * if((colorSample[0]>xf) && (colorSample[0]<xf) && (colorSample[1]>xf) && (colorSample[1]<xf) && (colorSample[2]>xf) && (colorSample[2]<xf)){
		 * System.out.println("couleur2"); }
		 */
	}
	
	public static void main(String[] args) {
		new ColorSensor();
	}
}
