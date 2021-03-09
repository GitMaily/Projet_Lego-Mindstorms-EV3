package codesource;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class ColorSensor {
	
	/******************* ESSAYER DE FAIRE INT COULEUR POUR METTRE SWITCH DANS MOTOR *********************/

	//Attributs
	EV3ColorSensor colorSensor=new EV3ColorSensor(SensorPort.S3); // Assigne le capteur couleur au port 3
	SampleProvider colorProvider=colorSensor.getRGBMode();        // Déclaration du mode getRGB, et assignation de la couleur dans la variable
	float[] colorSample=new float[colorProvider.sampleSize()];    // Déclaration du tableau de taille 3 pour stocker les 3 valeurs des couleurs d'une ligne
	public String couleur;
	
	//Constructeurs
	public ColorSensor() {
		colorProvider.fetchSample(colorSample,0);  // Stock les 3 valeurs d'une couleur
		
		/*System.out.println("RGB="+colorSample[0]+"/"+colorSample[1]+"/"+colorSample[2]);  //Affiche les valeurs RGB de la couleur de la ligne
		Delay.msDelay(5000);*/
	}
	
	//Méthodes
	public void NewColor() {
		if((colorSample[0]>0.27) && (colorSample[0]<0.31) && (colorSample[1]>0.38) && (colorSample[1]<0.42) && (colorSample[2]>0.20) && (colorSample[2]<0.24)){
			System.out.println("BLANC"); // Affiche BLANC à l'écran lorsque le robot est sur une ligne de cette couleur
			couleur="BLANC";             // Stock la chaine "BLANC" dans la variable couleur
			Delay.msDelay(5000);  }      // Latence de 5sc, permettant d'afficher le message à l'écran pendant ce temps
		  
		if((colorSample[0]>0.01) && (colorSample[0]<0.07) && (colorSample[1]>0.15) && (colorSample[1]<0.21) && (colorSample[2]>0.09) && (colorSample[2]<0.15)){
			System.out.println("BLEU"); 
			couleur="BLEU";
			Delay.msDelay(5000);  }
		
		if((colorSample[0]>0.01) && (colorSample[0]<0.06) && (colorSample[1]>0.01) && (colorSample[1]<0.06) && (colorSample[2]>0.01) && (colorSample[2]<0.05)){
			System.out.println("NOIR"); 
			couleur="NOIR";
			Delay.msDelay(5000);  }
		
		if((colorSample[0]>0.03) && (colorSample[0]<0.09) && (colorSample[1]>0.17) && (colorSample[1]<0.23) && (colorSample[2]>0.01) && (colorSample[2]<0.07)){
			System.out.println("VERT"); 
			couleur="VERT";
			Delay.msDelay(5000);  }
		
		if((colorSample[0]>0.22) && (colorSample[0]<0.27) && (colorSample[1]>0.29) && (colorSample[1]<0.35) && (colorSample[2]>0.02) && (colorSample[2]<0.08)){
			System.out.println("JAUNE"); 
			couleur="JAUNE";
			Delay.msDelay(5000);  }
		
		if((colorSample[0]>0.13) && (colorSample[0]<0.19) && (colorSample[1]>0.02) && (colorSample[1]<0.08) && (colorSample[2]>0.01) && (colorSample[2]<0.05)){
			System.out.println("ROUGE"); 
			couleur="ROUGE";
			Delay.msDelay(5000);
	}
		
}
	public String setColor() {  // Utile lors de son appel dans la class Movement
		return couleur;
	}

	/*public void getColor() {       // Fait appel à la méthode getMotor() de la class Motor, pour y transférer la chaine couleur
		Movement Couleur=new Movement(); 
		Couleur.Direction(couleur);
	}

	/*public static void main(String[] args) {  // Appelle la class ColorSensor, donc getColor(), faisant appel a la class Motor
		new ColorSensor();
	}*/
}